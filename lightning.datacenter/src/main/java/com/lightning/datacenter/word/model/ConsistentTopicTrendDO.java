package com.lightning.datacenter.word.model;

import java.io.Serializable;

public class ConsistentTopicTrendDO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5524136601755633721L;

	private int id;

	private long topicId;

	private String timestamp;

	private int value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	public String toString() {
		return String.format("[topicId: %d, timestamp: %s, value: %d]", topicId, timestamp, value);
	}
}
