package com.zhitianweilai.qing.crawler.detail.model;

import com.zhitianweilai.qing.crawler.system.hitstrategy.BaseCrawlerTaskType;

/**
 * 
 * @brief	
 * 
 * @author renjie.rj
 *
 */
public class HitUri {

	private String website;
	
	private String url;
	
	private BaseCrawlerTaskType taskType;

	public HitUri() {
	}

	public HitUri(String website, String url, BaseCrawlerTaskType taskType) {
		this.website = website;
		this.url = url.trim();
		this.taskType = taskType;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url.trim();
	}

	public BaseCrawlerTaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(BaseCrawlerTaskType taskType) {
		this.taskType = taskType;
	}
	
}
