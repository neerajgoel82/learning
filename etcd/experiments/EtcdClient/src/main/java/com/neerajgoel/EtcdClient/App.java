package com.neerajgoel.EtcdClient;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		DistributedConfigurationManager configManager = DistributedConfigurationManagerFactory
				.createInstance(DistributedConfigurationManagerType.ETCD_DISTRIBUTED_CONFIG_MANAGER);
		configManager.setConfig("foo", "bar");
		System.out.println(configManager.getConfig("foo"));
	}
}
