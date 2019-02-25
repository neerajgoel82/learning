package com.neerajgoel.kc;

import kyotocabinet.*;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.*;


class WriterThread extends Thread {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    Thread t;
    long startIndex = 0;
    long count =0 ;
    long runId = 0;
    long recordsLimit = 500000;
    double totalTime = 0 ;

    WriterThread(long startIndex, long count, long runId) {
        this.startIndex = startIndex;
        this.count = count;
        this.runId = runId;
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

        DB[] dbs = new DB[totalDbs];

        for(int i = 0 ; i < totalDbs; i++) {
            // create the object
            DB db = new DB();

            // open the database
            if (!db.open("casket" + UUID.randomUUID() + ".kch", DB.OWRITER | DB.OCREATE)) {
                System.err.println("open error: " + db.error());
            }
            dbs[i] = db;
        }

        String generatedString = randomString(1000);
        System.out.println(generatedString);

        Long start = System.nanoTime();
        Map<String, String> values = new HashMap<String, String>();

        for(int i = 0 ; i < count;i++) {
            dbs[i%totalDbs].set("foo"  + i + startIndex+ "_" + runId, generatedString);
        }

        // store records
        /*if (!db.set("foo", "hop") ||
                !db.set("bar", "step") ||
                !db.set("baz", "jump")){
            System.err.println("set error: " + db.error());
        }

        // retrieve records
        String value = db.get("foo1");
        if (value != null){
            System.out.println(value);
        } else {
            System.err.println("set error: " + db.error());
        }*/

        // traverse records
        /*Cursor cur = db.cursor();
        cur.jump();
        String[] rec;
        int index = 0;
        while ((rec = cur.get_str(true)) != null) {
            index++;
            if(index % 500000 ==0)
                System.out.println(rec[0] + ":" + rec[1]);
        }
        cur.disable();*/

        // close the database
        for ( int i = 0 ; i < totalDbs ; i++) {
            if (!dbs[i].close()) {
                System.err.println("close error: " + dbs[i].error());
            }
        }

        totalTime = (System.nanoTime() - start)/1000000.0f;
    }

    public double getTotalTime() {
        return this.totalTime;
    }

    public void run() {
        try {
            writeRecords();
        }catch (Exception e) {
            System.out.println("Interrupted");
        }
    }
}

public class KyotoReadAndWrite {

   public static void main(String[] args) {

        long start = System.nanoTime();
        long totalRecords = 10000000;
        long numOfCores = 128;
        long runId = 1;
        ArrayList<WriterThread> threads = new ArrayList<WriterThread>();
        for(int i = 0 ; i < numOfCores; i++) {
            threads.add(new WriterThread( i * (totalRecords/numOfCores), (totalRecords/numOfCores), runId));
        }

        for ( int i = 0 ; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        double max = 0 ;
        for(int i=0; i < threads.size();i++)
       {
           if(max < threads.get(i).getTotalTime()) {
               max = threads.get(i).getTotalTime();
           }
       }
        System.out.println("Total time taken:" + max);
    }
}