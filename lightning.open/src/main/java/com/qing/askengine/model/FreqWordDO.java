package com.qing.askengine.model;

import java.io.Serializable;

public class FreqWordDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7773829547627694013L;

	private String word;
	
	private int freq;

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
