package com.neerajgoel.kc;

import kyotocabinet.DB;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class KyotoReadAndWriteExecutorService {
   public static void main(String[] args) {
       long start = System.nanoTime();
       long totalRecords = 10000000;
       int numOfCores = 128;
       long runId = 1;
       try {
           // creates a thread pool with MAX_T no. of
           // threads as the fixed pool size(Step 2)
           ExecutorService pool = Executors.newFixedThreadPool(numOfCores);

           ArrayList<WriterTask> writerTasks = new ArrayList<WriterTask>();
           for (int i = 0; i < numOfCores; i++) {
               WriterTask task = new WriterTask(i * (totalRecords / numOfCores), (totalRecords / numOfCores), runId);
               writerTasks.add(task);
               pool.execute(task);
           }

           pool.shutdown();
           pool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
         }
         catch(Exception e) {
           e.printStackTrace();
         }

        System.out.println("Total time taken:" + (System.nanoTime()-start)/1000000.f);
    }
}