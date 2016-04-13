package com.lightning.crawler.msgstore.impl;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class SimplePartitioner implements Partitioner {
	public SimplePartitioner (VerifiableProperties props) {
    }
    public int partition(Object key, int numPartitions) {
		return (key.hashCode() & 0x0FFFFFFF) % numPartitions;
	}
}