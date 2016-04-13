package com.zhitianweilai.qing.template.engine;

import java.util.Map;

import org.htmlparser.Tag;

/**
 * 
 * static class for helper
 * 
 */
public class TagMatchConditionHelper {

	public static boolean isMatchCondition(Tag tag, TagMatchCondition condition) {
		TagType tagType = condition.getTagType();
		if (tagType.getValue().equalsIgnoreCase(tag.getTagName())) {
			Map<String, String> atrributes = condition.getAttributes();
			for (Map.Entry<String, String> entry : atrributes.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				String tavalue = tag.getAttribute(key);
				if ( !value.equalsIgnoreCase(tavalue) ) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
}
