package com.neerajgoel.kc;

import kyotocabinet.DB;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class KyotoReadAndWriteExecutorServiceSingleDB {
   public static void main(String[] args) {
       long start = System.nanoTime();
       long totalRecords = 10000000;
       int numOfCores = 128;
       long runId = 1;
       try {
           // creates a thread pool with MAX_T no. of
           // threads as the fixed pool size(Step 2)
           ExecutorService pool = Executors.newFixedThreadPool(numOfCores);

           // create the object
           DB db = new DB();

           // open the database
           if (!db.open("casket.kcd", DB.OWRITER | DB.OCREATE)) {
               System.err.println("open error: " + db.error());
           }
           else {
               ArrayList<WriterTaskDBSupplied> writerTasks = new ArrayList<WriterTaskDBSupplied>();
               for (int i = 0; i < numOfCores; i++) {
                   WriterTaskDBSupplied task = new WriterTaskDBSupplied(db, i * (totalRecords / numOfCores), (totalRecords / numOfCores), runId);
                   writerTasks.add(task);
                   pool.execute(task);
               }

               pool.shutdown();
               pool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

               if (!db.close()) {
                   System.err.println("close error: " + db.error());
               }
           }
         }
         catch(Exception e) {
           e.printStackTrace();
         }

        System.out.println("Total time taken:" + (System.nanoTime()-start)/1000000.f);
    }
}