package com.qing.askengine.model;

import java.io.Serializable;

public class DigitalDateSplitTrendDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2241230349988855459L;

	private String date;
	
	private int smsfreq;
	
	private int webfreq;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getSmsfreq() {
		return smsfreq;
	}

	public void setSmsfreq(int smsfreq) {
		this.smsfreq = smsfreq;
	}

	public int getWebfreq() {
		return webfreq;
	}

	public void setWebfreq(int webfreq) {
		this.webfreq = webfreq;
	}
	
}
