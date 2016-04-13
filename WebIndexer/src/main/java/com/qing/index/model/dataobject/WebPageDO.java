package com.qing.index.model.dataobject;

import java.io.Serializable;

public class WebPageDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7254506265317917332L;

	private int docid;
	
	private String title;
	
	private String url;
	
	private String summary;
	
	private String timestamp;
	
	private String source;

	public int getDocid() {
		return docid;
	}

	public void setDocid(int docid) {
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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}
