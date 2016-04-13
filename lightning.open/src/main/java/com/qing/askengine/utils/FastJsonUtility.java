package com.qing.askengine.utils;

import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class FastJsonUtility {

	public static final String HOST_EXPRESSION = "host_expression";
	public static final String NAV_PAGE_EXPRESSION = "nav_page_expression";
	public static final String CON_PAGE_EXPRESSION = "con_page_expression";

	public static final String TITLE_EXPRESSION = "title_expression";
	public static final String CONTENT_EXPRESSION = "content_expression";
	public static final String TIME_EXPRESSION = "time_expression";

	public static String toJson4UrlRule(String hostExpression,
			String navPageExpression, String conPageExpression) {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put(HOST_EXPRESSION, hostExpression);
		params.put(NAV_PAGE_EXPRESSION, navPageExpression);
		params.put(CON_PAGE_EXPRESSION, conPageExpression);
		JSONObject jso = new JSONObject(params);
		return jso.toJSONString();
	}

	public static String toJson4ContentRule(String titleExpression,
			String contentExpression, String timeExpression) {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put(TITLE_EXPRESSION, titleExpression);
		params.put(CONTENT_EXPRESSION, contentExpression);
		params.put(TIME_EXPRESSION, timeExpression);
		JSONObject jso = new JSONObject(params);
		return jso.toJSONString();
	}

	public static String getHostExpressionFromURLRule(String jsonText) {
		JSONObject jso = JSON.parseObject(jsonText);
		return jso.getString(HOST_EXPRESSION);
	}

	public static String getNavPageExpressionFromUrlRule(String jsonText) {
		JSONObject jso = JSON.parseObject(jsonText);
		return jso.getString(NAV_PAGE_EXPRESSION);
	}

	public static String getConPageExpressionFromUrlRule(String jsonText) {
		JSONObject jso = JSON.parseObject(jsonText);
		return jso.getString(CON_PAGE_EXPRESSION);
	}

	/**
	 * 对内容信息进行抽取, 标题
	 */
	public static String getTileExpressionFromContentRule(String jsonText) {
		JSONObject jso = JSON.parseObject(jsonText);
		return jso.getString(TITLE_EXPRESSION);
	}

	/**
	 * 对内容信息进行抽取, 正文
	 */
	public static String getContentExpressionFromContentRule(String jsonText) {
		JSONObject jso = JSON.parseObject(jsonText);
		return jso.getString(CONTENT_EXPRESSION);
	}

	/**
	 * 对内容信息进行抽取, 时间信息
	 */
	public static String getTimeExpressionFromContentRule(String jsonText) {
		JSONObject jso = JSON.parseObject(jsonText);
		return jso.getString(TIME_EXPRESSION);
	}

}