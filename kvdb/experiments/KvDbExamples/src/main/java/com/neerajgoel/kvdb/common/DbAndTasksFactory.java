package com.neerajgoel.kvdb.common;
import com.neerajgoel.kvdb.kc.KyotoDb;
import com.neerajgoel.kvdb.kc.KyotoWriterTask;

public class DbAndTasksFactory {
    public static KvDb getDb(String dbType){
        if(dbType.equalsIgnoreCase(DbTypes.DB_KC)){
            return new KyotoDb();
        }
        else {
            throw new UnsupportedOperationException();
        }
    }

    public static Runnable getWriterTask(KvDb db, long startIndex, long count, long runId){
        if(db.getType().equalsIgnoreCase(DbTypes.DB_KC)){
            return new KyotoWriterTask(db, startIndex, count, runId);
        }
        else {
            throw new UnsupportedOperationException();
        }
    }

    public static Runnable getWriterTask(String dbType, long startIndex, long count, long runId){
        if(dbType.equalsIgnoreCase(DbTypes.DB_KC)){
            return new KyotoWriterTask(startIndex, count, runId);
        }
        else {
            throw new UnsupportedOperationException();
        }
    }
}
