package com.neerajgoel.kc;

import kyotocabinet.DB;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


class WriterThreadSingleDir extends Thread {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    Thread t;
    long startIndex = 0;
    long count =0 ;
    long runId = 0;
    long recordsLimit = 500000;
    DB db = null;
    WriterThreadSingleDir(DB db, long startIndex, long count, long runId) {
        this.startIndex = startIndex;
        this.count = count;
        this.runId = runId;
        this.db =db;
        t = new Thread(this);
        System.out.println("New thread: " + t);
        t.start();
    }

    static String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }


    private void writeRecords() {

        int totalDbs = (int) (count / recordsLimit);

        if(count % recordsLimit != 0) totalDbs +=1;

        String generatedString = randomString(1000);
        System.out.println(generatedString);

        Long start = System.nanoTime();
        Map<String, String> values = new HashMap<String, String>();

        for(int i = 0 ; i < count;i++) {
            db.set("foo"  + i + startIndex+ "_" + runId, generatedString);
        }
    }
    public void run() {
        try {
            writeRecords();
        }catch (Exception e) {
            System.out.println("Interrupted");
        }
    }
}

public class KyotoReadAndWriteSingleDir {

   public static void main(String[] args) {

        long start = System.nanoTime();
        long totalRecords = 10000000;
        long numOfCores = 128;
        long runId = 1;
       DB db = new DB();

       // open the database
       if (!db.open("casketdb.kch", DB.OWRITER | DB.OCREATE)) {
           System.err.println("open error: " + db.error());
           System.exit(0);
       }

        ArrayList<Thread> threads = new ArrayList<Thread>();
        for(int i = 0 ; i < numOfCores; i++) {
            threads.add(new WriterThreadSingleDir( db,i * (totalRecords/numOfCores), (totalRecords/numOfCores), runId));
        }

        for ( int i = 0 ; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

       if (!db.close()) {
           System.err.println("close error: " + db.error());
       }

        System.out.println("Total time taken:" + (System.nanoTime()-start)/1000000.0f);
    }
}