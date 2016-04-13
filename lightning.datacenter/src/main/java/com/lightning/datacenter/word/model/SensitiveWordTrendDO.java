package com.lightning.datacenter.word.model;

import java.io.Serializable;

public class SensitiveWordTrendDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2613457954933359174L;

	private int id;
	
	private String word;
	
	private int type;
	
	private String timestamp;
	
	private int value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	
}
