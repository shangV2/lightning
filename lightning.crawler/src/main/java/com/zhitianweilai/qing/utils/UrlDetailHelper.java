package com.zhitianweilai.qing.utils;

import com.zhitianweilai.qing.url.UriDetail;

public class UrlDetailHelper {

	public static UriDetail parse(String url) {
		
		UriDetail detail = new UriDetail();
		// 1. schema
		int schemaIndex = 0;
		if ( (schemaIndex = url.indexOf("://")) != -1 ) {
			detail.setSchema(url.substring(0, schemaIndex));
		} else {
			return detail;
		}
		
		// 2. host
		int hostIndex = schemaIndex + 3; // skip "://"
		while ( hostIndex < url.length() ) {
			char ch = url.charAt(hostIndex);
			if ( ch == '/' || ch == '?' ) {
				break;
			}
			hostIndex++;
		}
		detail.setHost(url.substring(schemaIndex + 3, hostIndex));
		hostIndex++;
		
		if ( hostIndex < url.length() ) {
			detail.setPath(url.substring(hostIndex));
		}
		
		return detail;
		
	}
	
	public static String acquireHost(String url) {
		if ( url == null ) {
			return null;
		}
		if ( url.startsWith("http://") ) {
			int index = 7;
			while ( index < url.length() ) {
				char ch = url.charAt(index);
				if ( ch == '/' || ch == '?' ) {
					break;
				}
				index++;
			}
			return url.substring(0, index);
		} else {
			int index = 0;
			while ( index < url.length() ) {
				char ch = url.charAt(index);
				if ( ch == '/' || ch == '?' ) {
					break;
				}
				index++;
			}
			return url.substring(0, index);
		}
	}
	
}
