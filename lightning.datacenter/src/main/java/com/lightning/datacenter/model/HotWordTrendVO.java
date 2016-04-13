package com.lightning.datacenter.model;

import java.io.Serializable;

public class HotWordTrendVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8378910017306500268L;

	private String timestamp;
	
	private int freq;

	public HotWordTrendVO() {
	}

	public HotWordTrendVO(String timestamp, int freq) {
		this.timestamp = timestamp;
		this.freq = freq;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}
	
	
}

