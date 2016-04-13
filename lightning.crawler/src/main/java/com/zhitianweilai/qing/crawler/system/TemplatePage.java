package com.zhitianweilai.qing.crawler.system;

public class TemplatePage {
	
	private String title;
	
	private String content;
	
	private String timestamp;
	
	public TemplatePage() {
	}

	public TemplatePage(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	public TemplatePage(String title, String content, String timestamp) {
		this.title = title;
		this.content = content;
		this.timestamp = timestamp;
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
	
}

/*
 * 
 */

