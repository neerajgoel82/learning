package com.neerajgoel.kvdb.common;


import com.neerajgoel.kvdb.kc.KyotoDbProperties;
import org.omg.CORBA.Any;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class WriterTest {

    private static Map<String, Object> getKcProperties() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(KyotoDbProperties.DB_PATH, "casket.kch");
        return properties;
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        long totalRecords = 100;
        int numOfCores = 128;
        long runId = 1;
        String dbType = DbTypes.DB_KC;
        Map<String, Object> properties = getKcProperties();

        KvDb db = DbAndTasksFactory.getDb(dbType);
        db.init(properties);

        try {
            // creates a thread pool with MAX_T no. of
            // threads as the fixed pool size(Step 2)
            ExecutorService pool = Executors.newFixedThreadPool(numOfCores);

            ArrayList<Runnable> writerTasks = new ArrayList<Runnable>();
            for (int i = 0; i < numOfCores; i++) {
                Runnable task = DbAndTasksFactory.getWriterTask (db,i * (totalRecords / numOfCores), (totalRecords / numOfCores), runId);
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