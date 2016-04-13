package com.lightning.crawler.convertor;

import com.lighting.rpc.webmetaserver.model.WMSCrawlerWebsite;
import com.lightning.crawler.utility.MetaServerJsonUtility;
import com.zhitianweilai.qing.crawler.task.DailyWebCrawlerTask;

public class WebMetaServerConvertor {

	public static DailyWebCrawlerTask convert(WMSCrawlerWebsite wmscw) {
		DailyWebCrawlerTask result = new DailyWebCrawlerTask();

//		result.setLangugeType(WebMetaServerConvertor.convert(wmscw
//				.getWebsiteType()));
		
		result.setLanguage(LanguageConvertor.convert(wmscw.getWebsiteType()));
		result.setWebsite(wmscw.getWebsite());
		result.setSeedUrl(wmscw.getSeeds().get(0));
//		result.setSeedUrls(wmscw.getSeeds());
		result.setPageMaxLimit(wmscw.getCrawlerNum());
		
		if (wmscw.isSetWebid()) {
			result.setWebid(wmscw.getWebid());
		}

		// *) 进行url的抽取
		result.setHostExpression(MetaServerJsonUtility.getHostExpressionFromURLRule(wmscw
				.getUrlRule()));
		result.setNavPageExpression(MetaServerJsonUtility
				.getNavPageExpressionFromUrlRule(wmscw.getUrlRule()));
		result.setConPageExpression(MetaServerJsonUtility
				.getConPageExpressionFromUrlRule(wmscw.getUrlRule()));

		// *) 进行正文内容的提取
		result.setContentExpression(MetaServerJsonUtility
				.getContentExpressionFromContentRule(wmscw.getContentRule()));
		result.setTitleExpression(MetaServerJsonUtility
				.getTileExpressionFromContentRule(wmscw.getContentRule()));
		result.setTimeExpression(MetaServerJsonUtility
				.getTimeExpressionFromContentRule(wmscw.getContentRule()));

		return result;
	}

}
