package com.lightning.datacenter.word.model;

import java.io.Serializable;

public class ConsistentTopicDO implements Serializable {

	/**
	 */
	private static final long serialVersionUID = -3751468092040436721L;

	private int id;

	private long topicId;

	private String topicName;

	private int type;

	private String words;

	private String startDate;

	private String endDate;

	private int percent;

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

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	@Override
	public String toString() {
		return String
				.format("[topic_id: %d, topic_name: %s, type: %d, words: %s, startDate: %s, endDate: %s, percent: %d]",
						topicId, topicName, type, words, startDate, endDate,
						percent);
	}

}
