package com.lightning.datacenter.model;

import java.io.Serializable;

public class HotWordVO implements Serializable, Comparable<HotWordVO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4073583945916221029L;
	
	private String word;
	
	private int freq;

	public HotWordVO() {
	}

	public HotWordVO(String word, int freq) {
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

	@Override
	public int compareTo(HotWordVO o) {
		return freq - o.freq;
	}
	
}
