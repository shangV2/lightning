package com.lightning.datacenter.model;

import java.io.Serializable;

public class ConsistentTopicTrendVO implements Serializable, Comparable<ConsistentTopicTrendVO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2911386371501478204L;

	private long topicId;
	
	private String timestamp;
	
	private int value;

	public long getTopicId() {
		return topicId;
	}

	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(ConsistentTopicTrendVO other) {
		return timestamp.compareTo(other.timestamp);
	}
	
}
