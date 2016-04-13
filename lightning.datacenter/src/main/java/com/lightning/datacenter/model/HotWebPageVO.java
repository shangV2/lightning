package com.lightning.datacenter.model;

import java.io.Serializable;

public class HotWebPageVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -186191772285692965L;

	public String url; // required

	public String title; // required

	public String content; // required

	public String timestamp; // required

	public String source; // required

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
