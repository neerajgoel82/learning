package com.neerajgoel.EtcdClient;

public interface DistributedConfigurationManager {
	//synchronous interface 
	public void setConfig(String key, String value);

	public String getConfig(String key);
	
	//TODO: asynchronous interface
}
