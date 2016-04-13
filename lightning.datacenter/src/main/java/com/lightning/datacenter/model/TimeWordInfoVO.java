package com.lightning.datacenter.model;

import java.io.Serializable;

public class TimeWordInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8056092586250747875L;

	private String word;
	
	private int freq;
	
	public TimeWordInfoVO() {
	}

	public TimeWordInfoVO(String word, int freq) {
		this.word = word;
		this.freq = freq;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}
	
	
}

