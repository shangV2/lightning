package com.lightning.datacenter.model;

import java.io.Serializable;

public class HotWebEventVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6202971483235567263L;

	public String title; // required

	public String eventid; // required
	
	public long score; // required
	
	public HotWebEventVO() {
	}

	public HotWebEventVO(String title, String eventid, long score) {
		this.title = title;
		this.eventid = eventid;
		this.score = score;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}
	
}
