package com.lightning.webmetaserver.dataobject.convertor;

import com.google.gson.Gson;
import com.lighting.rpc.webmetaserver.model.WMSCrawlerWebsite;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteContentRuleType;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteCrawlerType;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteType;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteUrlRuleType;
import com.lightning.webmetaserver.dataobject.WebsiteCrawlerDO;
import com.lightning.webmetaserver.utility.FastJsonUtility;

public class WebsiteCrawlerConvertor {

	public static WebsiteCrawlerDO convert(WMSCrawlerWebsite crawlerWebsite) {
	
		WebsiteCrawlerDO websiteCrawlerDO = new WebsiteCrawlerDO();
		websiteCrawlerDO.setWebsite(crawlerWebsite.getWebsite());
		if ( crawlerWebsite.isSetWebid() ) {
			websiteCrawlerDO.setWebid(crawlerWebsite.getWebid());
		}
			
		websiteCrawlerDO.setWebsiteType(crawlerWebsite.getWebsiteType().getValue());
		
		websiteCrawlerDO.setCrawlerType(crawlerWebsite.getCrawlerType().getValue());
		websiteCrawlerDO.setCrawlerSchedule(crawlerWebsite.getCrawlerSchedule());
		websiteCrawlerDO.setCrawlerNum(crawlerWebsite.getCrawlerNum());
		
		websiteCrawlerDO.setUrlType(crawlerWebsite.getUrlType().getValue());
		websiteCrawlerDO.setUrlRule(crawlerWebsite.getUrlRule());
		websiteCrawlerDO.setContentType(crawlerWebsite.getContentType().getValue());
		websiteCrawlerDO.setContentRule(crawlerWebsite.getContentRule());
		websiteCrawlerDO.setSeeds(new Gson().toJson(crawlerWebsite.getSeeds()));
		
		return websiteCrawlerDO;
	
	}

	public static WMSCrawlerWebsite convert(WebsiteCrawlerDO crawlerDO) {
		
		WMSCrawlerWebsite result = new WMSCrawlerWebsite();
		
		result.setUrlRule(crawlerDO.getUrlRule());
		result.setUrlType(WMSWebsiteUrlRuleType.WMS_WEBSITE_URL_RULE_HIT);

		result.setContentType(WMSWebsiteContentRuleType.WMS_WEBSITE_CONTENT_RULE_TEMPLATE_HPATH);
		result.setContentRule(crawlerDO.getContentRule());
		
		result.setCrawlerNum(crawlerDO.getCrawlerNum());
		result.setCrawlerSchedule(crawlerDO.getCrawlerSchedule());
		
		result.setSeeds(FastJsonUtility.getSeedUrls(crawlerDO.getSeeds()));
		
		result.setWebsite(crawlerDO.getWebsite());
		
		result.setWebsiteType(WMSWebsiteType.findByValue(crawlerDO.getWebsiteType()));
		result.setCrawlerType(WMSWebsiteCrawlerType.WMS_WEBSITE_CRAWLER_STAND_ALONE);
		result.setWebid(crawlerDO.getWebid());
		
		return result;
		
	}
	
	private static WMSWebsiteType convert2WebsiteType(int value) {
		switch(value) {
		case 0:
			return WMSWebsiteType.WMS_WEBSITE_ZH_CN;
		default:
			return WMSWebsiteType.WMS_WEBSITE_ZH_CN;
		}
	}
	
	private static WMSWebsiteCrawlerType convert2WebsiteCrawlerType(int value) {
		switch(value) {
		case 0:
			return WMSWebsiteCrawlerType.WMS_WEBSITE_CRAWLER_STAND_ALONE;
		case 1:
			return WMSWebsiteCrawlerType.WMS_WEBSITE_CRALWER_DISTRIBUTED;
		default:
			return WMSWebsiteCrawlerType.WMS_WEBSITE_CRAWLER_STAND_ALONE;
		}
	}
	
	private static WMSWebsiteUrlRuleType convert2WebsiteUrlRuleType(int value) {
		switch(value) {
		case 0:
			return WMSWebsiteUrlRuleType.WMS_WEBSITE_URL_RULE_HIT;
		default:
			return WMSWebsiteUrlRuleType.WMS_WEBSITE_URL_RULE_HIT;
		}
	}
	
	private static WMSWebsiteContentRuleType convert2WebsiteContentRuleType(int value) {
		switch(value) {
		case 0:
			return WMSWebsiteContentRuleType.WMS_WEBSITE_CONTENT_RULE_TEMPLATE_HPATH;
		default:
			return WMSWebsiteContentRuleType.WMS_WEBSITE_CONTENT_RULE_TEMPLATE_HPATH;
		}
	}
	
	
}
