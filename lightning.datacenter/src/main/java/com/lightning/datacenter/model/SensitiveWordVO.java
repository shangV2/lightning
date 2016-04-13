package com.lightning.datacenter.model;

import java.io.Serializable;

public class SensitiveWordVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 911254126091693867L;

	private String word;
	
	private int type;

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
