package com.lightning.datacenter.model;

import java.io.Serializable;

public class ConsistentTopicWebPageVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5834167811579339749L;

	private String url;
	
	private String source;
	
	private String title;
	
	private String timestamp;
	
	private String content;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
