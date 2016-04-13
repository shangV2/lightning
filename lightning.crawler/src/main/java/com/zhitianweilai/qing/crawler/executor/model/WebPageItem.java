package com.zhitianweilai.qing.crawler.executor.model;

import java.io.Serializable;

public class WebPageItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6269027618277756682L;

	private String url;
	
	private String source;
	
	private String title;
	
	private String timestamp;
	
	private String content;
	
	public WebPageItem() {
	}

	public WebPageItem(String url, String source, String title,
			String timestamp, String content) {
		this.url = url;
		this.source = source;
		this.title = title;
		this.timestamp = timestamp;
		this.content = content;
	}

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
