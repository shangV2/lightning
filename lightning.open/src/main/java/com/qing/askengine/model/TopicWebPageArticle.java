package com.qing.askengine.model;

public class TopicWebPageArticle implements Comparable<TopicWebPageArticle> {

	private String url;
	private String title;
	private String timestamp;
	private String content;
	private String source;
	private double score;
	
	
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

	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	@Override
	public int compareTo(TopicWebPageArticle o) {
		if ( score < o.score ) return -1;
		else if ( score > o.score ) return 1;
		return 0;
	}
	
	public TopicWebPageArticle clone() {
		TopicWebPageArticle article = new TopicWebPageArticle();
		article.setContent(content);
		article.setTimestamp(timestamp);
		article.setTitle(title);
		article.setUrl(url);
		article.setSource(source);
		article.setScore(score);
		return article;
	}
	
}
