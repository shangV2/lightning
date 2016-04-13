package com.lightning.datacenter.word.model;

import java.io.Serializable;

public class SensitiveWordDO implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 1464064908930831728L;

	private int id;
	
	private String word;
	
	private int type;

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
	
}
