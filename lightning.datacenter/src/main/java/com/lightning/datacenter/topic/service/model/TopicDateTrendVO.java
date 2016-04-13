package com.lightning.datacenter.topic.service.model;

import java.io.Serializable;

public class TopicDateTrendVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2667319584955799373L;

	private String date;
	
	private int number;

	public TopicDateTrendVO() {
	}

	public TopicDateTrendVO(String date, int number) {
		this.date = date;
		this.number = number;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}
