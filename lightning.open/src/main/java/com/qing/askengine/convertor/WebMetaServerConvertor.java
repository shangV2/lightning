package com.qing.askengine.convertor;

import com.lighting.rpc.webmetaserver.model.WMSCrawlerWebsite;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteContentRuleType;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteCrawlerType;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteType;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteUrlRuleType;
import com.qing.askengine.utils.FastJsonUtility;
import com.qing.logiclayer.HitWebisteCrawler;
import com.qing.logiclayer.OpenCommonLanguge;


// <<static class>>
public class WebMetaServerConvertor {

	
	public static boolean isValidate(WMSCrawlerWebsite wmscw) {
		return true;
	}
	
	/**
	 * 
	 * @param wmscw
	 * @return
	 */
	public static HitWebisteCrawler convert(WMSCrawlerWebsite wmscw) {
		HitWebisteCrawler result = new HitWebisteCrawler();
		
		result.setLangugeType(WebMetaServerConvertor.convert(wmscw.getWebsiteType()));
		result.setWebsite(wmscw.getWebsite());
		result.setSeedUrls(wmscw.getSeeds());
		result.setCrawlerNum(wmscw.getCrawlerNum());
		
		if ( wmscw.isSetWebid() ) {
			result.setWebid(wmscw.getWebid());
		}
		
		// *) 	进行url的抽取
		result.setHostUrlExp(FastJsonUtility.getHostExpressionFromURLRule(wmscw
				.getUrlRule()));
		result.setNavigationUrlExp(FastJsonUtility
				.getNavPageExpressionFromUrlRule(wmscw.getUrlRule()));
		result.setContentUrlExp(FastJsonUtility
				.getConPageExpressionFromUrlRule(wmscw.getUrlRule()));
		
		// *)	进行正文内容的提取
		result.setContentContentExp(FastJsonUtility
				.getContentExpressionFromContentRule(wmscw.getContentRule()));
		result.setTitleContentExp(FastJsonUtility
				.getTileExpressionFromContentRule(wmscw.getContentRule()));
		result.setTimeContentExp(FastJsonUtility
				.getTimeExpressionFromContentRule(wmscw.getContentRule()));
		
		return result;
	}
	
	public static WMSCrawlerWebsite convert(HitWebisteCrawler hwc) {
		
		WMSCrawlerWebsite result = new WMSCrawlerWebsite();
		
		if ( hwc.isSetWebid() ) {
			result.setWebid(hwc.getWebid());
		}
		result.setWebsite(hwc.getWebsite());
		result.setWebsiteType(convert(hwc.getLangugeType()));
		result.setSeeds(hwc.getSeedUrls());
		result.setCrawlerNum(hwc.getCrawlerNum());
		result.setCrawlerSchedule(0);
		result.setCrawlerType(WMSWebsiteCrawlerType.WMS_WEBSITE_CRAWLER_STAND_ALONE);
		
		result.setContentType(WMSWebsiteContentRuleType.WMS_WEBSITE_CONTENT_RULE_TEMPLATE_HPATH);
		result.setContentRule(FastJsonUtility.toJson4ContentRule(
					hwc.getTitleContentExp(), 
					hwc.getContentContentExp(), 
					hwc.getTimeContentExp()
				)
			);
		
		result.setUrlType(WMSWebsiteUrlRuleType.WMS_WEBSITE_URL_RULE_HIT);
		result.setUrlRule(FastJsonUtility.toJson4UrlRule(
					hwc.getHostUrlExp(), 
					hwc.getNavigationUrlExp(), 
					hwc.getContentUrlExp()
				)
			);
		return result;
		
	}
	
	public static OpenCommonLanguge convert(WMSWebsiteType type) {
		if ( type == WMSWebsiteType.WMS_WEBSITE_ZH_CN ) {
			return OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN;
		}
		// TODO
		return OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN;
	}
	
	public static WMSWebsiteType convert(OpenCommonLanguge type) {
		if ( type == OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN ) {
			return WMSWebsiteType.WMS_WEBSITE_ZH_CN;
		}
		return WMSWebsiteType.WMS_WEBSITE_ZH_CN;
	}
	
}
