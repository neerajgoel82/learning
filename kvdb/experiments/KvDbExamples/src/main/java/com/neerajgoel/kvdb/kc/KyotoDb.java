package com.neerajgoel.kvdb.kc;

import com.neerajgoel.kvdb.common.DbTypes;
import com.neerajgoel.kvdb.common.KvDb;
import kyotocabinet.DB;
import java.util.Map;

public class KyotoDb implements KvDb {
    DB db = null;

    public String getType() {
        return DbTypes.DB_KC;
    }

    public Object getDb() {
        if(null == db)
            throw new RuntimeException("DB not initialized");

        return db;
    }

    public void init(Map<String, Object> properties) {
        db = new DB();

        String path = (String)properties.get(KyotoDbProperties.DB_PATH);
        if (!db.open(path, DB.OWRITER | DB.OCREATE)) {
            System.err.println("open error: " + db.error());
            throw new RuntimeException("Could not open the db");
        }

    }

    public void cleanup() {
        if(null != db) {
            if (!db.close()) {
                System.err.println("close error: " + db.error());
                throw new RuntimeException("Could not close the db");
            }
        }
    }
}
