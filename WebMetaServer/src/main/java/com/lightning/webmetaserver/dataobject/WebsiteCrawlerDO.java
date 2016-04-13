package com.lightning.webmetaserver.dataobject;

import java.io.Serializable;

public class WebsiteCrawlerDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -713192253130207645L;

//	1: required string website,
//	2: optional string startpage,
//	3: required WMSWebsiteType websiteType 
//					= WMSWebsiteType.WMS_WEBSITE_ZH_CN, 
//	4: required WMSWebsiteCrawlerType crawlerType 
//					= WMSWebsiteCrawlerType.WMS_WEBSITE_CRAWLER_STAND_ALONE,
//	5: required WMSWebsiteUrlRuleType urlType 
//					= WMSWebsiteUrlRuleType.WMS_WEBSITE_URL_RULE_HIT,
//	6: required string urlRule,
//	7: required WMSWebsiteContentRuleType contentType
//					= WMSWebsiteContentRuleType.WMS_WEBSITE_CONTENT_RULE_TEMPLATE_HPATH,
//	8: required string contentRule,
//	9: required list<string> seeds,
//	10: required i32 crawlerNum,
//	11: required i32 crawlerSchedule,
	
	private int webid;
	
	private String website;
	
	private int websiteType;
	
	private int crawlerType;
	
	private int urlType;
	
	private String urlRule;
	
	private int contentType;
	
	private String contentRule;
	
	private String seeds;
	
	private int crawlerNum;
	
	private int crawlerSchedule;

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

	public int getWebsiteType() {
		return websiteType;
	}

	public void setWebsiteType(int websiteType) {
		this.websiteType = websiteType;
	}

	public int getCrawlerType() {
		return crawlerType;
	}

	public void setCrawlerType(int crawlerType) {
		this.crawlerType = crawlerType;
	}

	public int getUrlType() {
		return urlType;
	}

	public void setUrlType(int urlType) {
		this.urlType = urlType;
	}

	public String getUrlRule() {
		return urlRule;
	}

	public void setUrlRule(String urlRule) {
		this.urlRule = urlRule;
	}

	public int getContentType() {
		return contentType;
	}

	public void setContentType(int contentType) {
		this.contentType = contentType;
	}

	public String getContentRule() {
		return contentRule;
	}

	public void setContentRule(String contentRule) {
		this.contentRule = contentRule;
	}

	public String getSeeds() {
		return seeds;
	}

	public void setSeeds(String seeds) {
		this.seeds = seeds;
	}

	public int getCrawlerNum() {
		return crawlerNum;
	}

	public void setCrawlerNum(int crawlerNum) {
		this.crawlerNum = crawlerNum;
	}

	public int getCrawlerSchedule() {
		return crawlerSchedule;
	}

	public void setCrawlerSchedule(int crawlerSchedule) {
		this.crawlerSchedule = crawlerSchedule;
	}
	
}
