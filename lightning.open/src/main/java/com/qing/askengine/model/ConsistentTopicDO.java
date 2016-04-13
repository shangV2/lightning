package com.qing.askengine.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConsistentTopicDO implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 7045260242169129776L;

	private long topicId;
	
	private String topicName;
	
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
	
	public ConsistentTopicDO clone() {
		ConsistentTopicDO topic = new ConsistentTopicDO();
		topic.setTopicId(topicId);
		topic.setTopicName(topicName);
		topic.setWords(new ArrayList<String>(words));
		topic.setStartDate(startDate);
		topic.setEndDate(endDate);
		topic.setPercent(percent);
		return topic;
	}
	
}


