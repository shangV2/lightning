package com.lightning.datacenter.model;

import java.io.Serializable;
import java.util.List;

public class TimeWordTrendVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7694724485680311219L;

	private String timestamp;
	
	private List<TimeWordInfoVO> wordInfos;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public List<TimeWordInfoVO> getWordInfos() {
		return wordInfos;
	}

	public void setWordInfos(List<TimeWordInfoVO> wordInfos) {
		this.wordInfos = wordInfos;
	}
	
	
}
