package com.neerajgoel.kvdb.common;

import java.util.Map;

public interface KvDb {
    public Object getDb();
    public String getType();
    public void init(Map<String, Object> properties);
    public void cleanup();
}
