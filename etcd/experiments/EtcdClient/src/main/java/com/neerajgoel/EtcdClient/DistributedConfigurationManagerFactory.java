package com.neerajgoel.EtcdClient;

public class DistributedConfigurationManagerFactory {
	public static DistributedConfigurationManager createInstance(DistributedConfigurationManagerType type) {
		DistributedConfigurationManager configManager = null;
		switch (type) {
		case ETCD_DISTRIBUTED_CONFIG_MANAGER:
			configManager = new EtcdDistributedConfigurationManager();
			break;
		}
		return configManager;
	}

	public static DistributedConfigurationManager createInstance(DistributedConfigurationManagerType type, String host,
			String port) {
		DistributedConfigurationManager configManager = null;
		switch (type) {
		case ETCD_DISTRIBUTED_CONFIG_MANAGER:
			configManager = new EtcdDistributedConfigurationManager(host, port);
			break;
		}
		return configManager;
	}
}
