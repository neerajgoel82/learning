package com.neerajgoel.kc;

import kyotocabinet.DB;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WriterTask implements Runnable {
    final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    SecureRandom rnd = new SecureRandom();
    Thread t;
    long startIndex = 0;
    long count =0 ;
    long runId = 0;
    long recordsLimit = 500000;
    double totalTime = 0 ;

    WriterTask(long startIndex, long count, long runId) {
        this.startIndex = startIndex;
        this.count = count;
        this.runId = runId;
    }

    String randomString( int len ){
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
