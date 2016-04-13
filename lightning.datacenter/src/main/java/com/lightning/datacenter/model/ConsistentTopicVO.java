package com.lightning.datacenter.model;

import java.io.Serializable;
import java.util.List;

public class ConsistentTopicVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5224882908205817530L;

	private long topicId;
	
	private String topicName;
	
	private int type;
	
	private List<String> words;
	
	private String startDate;
	
	private String endDate;
	
	private int percent;

	public long getTopicId() {
		return topicId;
	}

	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<String> getWords() {
		return words;
	}

	public void setWords(List<String> words) {
		this.words = words;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}
	
}
