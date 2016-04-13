package com.zhitianweilai.qing.crawler.task;

/**
 * 
 */
public class DailyWebCrawlerTask {

//	/**
//	 * webid		website id, 
//	 */
	private int webid;
	
	/**
	 * 
	 */
	private String website;
	
	/**
	 * 
	 */
	private int language;
	
	/**
	 * 
	 */
	private String seedUrl;
	
	/**
	 * 
	 */
	private String hostExpression;
	
	/**
	 * 
	 */
	private String navPageExpression;
	
	/**
	 * 
	 */
	private String conPageExpression;
	
	/**
	 * 
	 */
	private String titleExpression;
	
	/**
	 * 
	 */
	private String contentExpression;

	/**
	 * 
	 */
	private String timeExpression;
	
	/**
	 * 
	 */
	private long pageMaxLimit;

	/**
	 * 
	 */
	private String timestamp;
	
	public int getWebid() {
		return webid;
	}

	public void setWebid(int webid) {
		this.webid = webid;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	public String getHostExpression() {
		return hostExpression;
	}

	public void setHostExpression(String hostExpression) {
		this.hostExpression = hostExpression;
	}

	public String getNavPageExpression() {
		return navPageExpression;
	}

	public void setNavPageExpression(String navPageExpression) {
		this.navPageExpression = navPageExpression;
	}

	public String getConPageExpression() {
		return conPageExpression;
	}

	public void setConPageExpression(String conPageExpression) {
		this.conPageExpression = conPageExpression;
	}

	public String getTitleExpression() {
		return titleExpression;
	}

	public void setTitleExpression(String titleExpression) {
		this.titleExpression = titleExpression;
	}

	public String getContentExpression() {
		return contentExpression;
	}

	public void setContentExpression(String contentExpression) {
		this.contentExpression = contentExpression;
	}

	public String getTimeExpression() {
		return timeExpression;
	}

	public void setTimeExpression(String timeExpression) {
		this.timeExpression = timeExpression;
	}

	public long getPageMaxLimit() {
		return pageMaxLimit;
	}

	public void setPageMaxLimit(long pageMaxLimit) {
		this.pageMaxLimit = pageMaxLimit;
	}

	public String getSeedUrl() {
		return seedUrl;
	}

	public void setSeedUrl(String seedUrl) {
		this.seedUrl = seedUrl;
	}
	
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return String.format("[website: %s, language: %s, seedUrl: %s,"
				+ " hostExpression: %s, navPageExpression: %s, conPageExpression: %s, "
				+ "titleExpression: %s, contentExpression: %s, timeExpression: %s, "
				+ "pageMaxLimit: %d, timestamp: %s]", 
				website, language, seedUrl, 
				hostExpression, navPageExpression, conPageExpression,
				titleExpression, contentExpression, timeExpression,
				pageMaxLimit, timestamp);
	}
	
}
