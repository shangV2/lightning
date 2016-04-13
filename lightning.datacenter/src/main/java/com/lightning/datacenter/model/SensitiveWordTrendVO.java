package com.lightning.datacenter.model;

import java.io.Serializable;

public class SensitiveWordTrendVO implements Serializable, Comparable<SensitiveWordTrendVO> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2922133255962341561L;

	private String word;
	
	private int type;
	
	private String timestamp;
	
	private int value;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
	public int compareTo(SensitiveWordTrendVO other) {
		return timestamp.compareTo(other.timestamp);
	}
	
}
