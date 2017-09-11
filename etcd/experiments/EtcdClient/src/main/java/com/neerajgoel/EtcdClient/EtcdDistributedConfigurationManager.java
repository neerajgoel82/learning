package com.neerajgoel.EtcdClient;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeoutException;

import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.responses.EtcdAuthenticationException;
import mousio.etcd4j.responses.EtcdException;
import mousio.etcd4j.responses.EtcdKeysResponse;

public class EtcdDistributedConfigurationManager implements DistributedConfigurationManager {
	EtcdClient etcdClient = null;

	EtcdDistributedConfigurationManager() {
		try {
			etcdClient = new EtcdClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	EtcdDistributedConfigurationManager(String host, String port) {
		try {
			String strUrl = "http://" + host + "/" + port;
			etcdClient = new EtcdClient(URI.create(strUrl));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setConfig(String key, String value) {
		if (etcdClient == null) {
			return; // [NG]: define what should happen here. Should we throw an
					// exception
		}
		try {
			etcdClient.put(key, value).send().get();
		} catch (EtcdException e) {
			// Do something with the exception returned by etcd
		} catch (IOException e) {
			// Exception happened in the retrieval. Do something with it.
		} catch (TimeoutException e) {
			// Timeout happened. Do something
		} catch (EtcdAuthenticationException e) {
			// Authentication exception. Do something
		}
	}

	public String getConfig(String key) {
		if (etcdClient == null) {
			return null; // [NG]: define what should happen here. Should we
							// throw an
			// exception
		}
		try {
			EtcdKeysResponse response = etcdClient.get(key).send().get();
			// Prints out: bar
			return response.node.value;
		} catch (EtcdException e) {
			// Do something with the exception returned by etcd
		} catch (IOException e) {
			// Exception happened in the retrieval. Do something with it.
		} catch (TimeoutException e) {
			// Timeout happened. Do something
		} catch (EtcdAuthenticationException e) {
			// Authentication exception. Do something
		}

		return null;
	}
}
