package com.qing.guodu;

import java.io.Serializable;

public class WebPageDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7254506265317917332L;

	private long docid;
	
	private String title;
	
	private String url;
	
	private String summary;

	public long getDocid() {
		return docid;
	}

	public void setDocid(long docid) {
		this.docid = docid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
