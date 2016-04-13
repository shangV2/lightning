package com.lightning.crawler.convertor;

import com.lighting.rpc.webmetaserver.model.WMSWebsiteType;

public class LanguageConvertor {
	
	public static WMSWebsiteType convert(int languageType) {
		switch(languageType) {
		case 0:
			return WMSWebsiteType.WMS_WEBSITE_ZH_CN;
		default:
			return WMSWebsiteType.WMS_WEBSITE_ZH_CN;
		}
	}
	
	public static int convert(WMSWebsiteType websiteType) {
		switch(websiteType) {
		case WMS_WEBSITE_ZH_CN:
			return 0;
		default:
			return 0;
		}
	}
	
}
