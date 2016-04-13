package com.zhitianweilai.qing.utils;


/**
 * 
 * @author renjie.rj
 *
 */
public class HtmlContentHelper {

	/**
	 * 
	 * @brief	
	 * 
	 * @param htmlContent
	 * @return
	 */
	public static String escape(String htmlContent) {
		
		htmlContent = htmlContent.replaceAll("&quot;", "\"");
		htmlContent = htmlContent.replaceAll("&amp;", "&");
		htmlContent = htmlContent.replaceAll("&lt;", "<");
		htmlContent = htmlContent.replaceAll("&gt;", ">");
		htmlContent = htmlContent.replaceAll("&nbsp;", " ");
		
		return htmlContent;
	}
	
}
