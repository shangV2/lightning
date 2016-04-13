package com.qing.askengine.model;

import java.io.Serializable;
import java.util.List;

public class DigitalDateTrendDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2241230349988855459L;

	private String date;
	
	private List<FreqWordDO> freqWordDOs;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<FreqWordDO> getFreqWordDOs() {
		return freqWordDOs;
	}

	public void setFreqWordDOs(List<FreqWordDO> freqWordDOs) {
		this.freqWordDOs = freqWordDOs;
	}
	
}
