package com.neerajgoel.kc;

import kyotocabinet.DB;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WriterTaskDBSupplied implements Runnable {
    final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    SecureRandom rnd = new SecureRandom();
    Thread t;
    long startIndex = 0;
    long count =0 ;
    long runId = 0;
    long recordsLimit = 500000;
    DB db = null;

    WriterTaskDBSupplied(DB db, long startIndex, long count, long runId) {
        this.startIndex = startIndex;
        this.count = count;
        this.runId = runId;
        this.db = db;
    }

    String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }


    private void writeRecords() {
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
