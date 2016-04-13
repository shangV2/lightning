package com.qing.guodu;

public class WebPage {
	
	private String title;
	
	private String url;
	
	private String content;
	
	private String timestamp;
	
	private String source;
	
	public WebPage() {
		
	}

	public WebPage(String title, String url, String content) {
		this.title = title;
		this.url = url;
		this.content = content;
	}
	
	public WebPage(String title, String url, String content, String timestamp) {
		super();
		this.title = title;
		this.url = url;
		this.content = content;
		this.timestamp = timestamp;
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
