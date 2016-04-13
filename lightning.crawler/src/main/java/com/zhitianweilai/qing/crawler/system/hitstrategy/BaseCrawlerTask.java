package com.zhitianweilai.qing.crawler.system.hitstrategy;

public class BaseCrawlerTask {
	
	private String url;
	
	private BaseCrawlerTaskType taskType;

	public BaseCrawlerTask() {
	}

	public BaseCrawlerTask(String url, BaseCrawlerTaskType taskType) {
		this.url = url;
		this.taskType = taskType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BaseCrawlerTaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(BaseCrawlerTaskType taskType) {
		this.taskType = taskType;
	}
	
}
