package com.qing.askengine.utils;

import com.lighting.rpc.datacenter.model.DCCommonLanguge;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteType;
import com.qing.logiclayer.OpenCommonLanguge;

public class LanguageHelper {

	public static DCCommonLanguge convert(OpenCommonLanguge language) {
		if ( OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN == language ) {
			return DCCommonLanguge.DC_LAN_CHINESE_ZH_CN;
		} else if ( OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT == language ) {
			return DCCommonLanguge.DC_LAN_UYGHUR_DEFAULT;
		} else if ( OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT == language ) {
			return DCCommonLanguge.DC_LAN_TIBETAN_DEFAULT;
		}
		return DCCommonLanguge.DC_LAN_CHINESE_ZH_CN;
	}
	
	public static WMSWebsiteType convert2WebsitType(OpenCommonLanguge language) {
		if ( OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN == language ) {
			return WMSWebsiteType.WMS_WEBSITE_ZH_CN;
		}
		return WMSWebsiteType.WMS_WEBSITE_ZH_CN;
	}
	
}
