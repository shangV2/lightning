package com.zhitianweilai.qing.template.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.htmlparser.Tag;

public class HPTagMatchEngine {
	
	private ITagMatchConditionParser conditionParser = null;

	private List<TagMatchCondition> contentConditions = new ArrayList<TagMatchCondition>();

	private List<TagMatchCondition> titleConditions = new ArrayList<TagMatchCondition>();
	
	/**
	 * 
	 * @param tag
	 * @return
	 */
	public boolean isPageContent(Tag tag) {
		if (tag == null) {
			return false;
		}
		for (TagMatchCondition condition : contentConditions) {
			if ( isMatchCondition(tag, condition) ) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isPageTitle(Tag tag) {
		if ( tag == null ) {
			return false;
		}
		for ( TagMatchCondition condition : titleConditions ) {
			if ( isMatchCondition(tag, condition) ) {
				return true;
			}
		}
		return false;
	}

	private boolean isMatchCondition(Tag tag, TagMatchCondition condition) {
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
	
	public void buildTagMatchCondition(String title, String content) {
		if ( conditionParser != null ) {
			TagMatchCondition titleCondition = conditionParser.parse(title);
			if ( titleCondition != null ) {
				titleConditions.add(titleCondition);
			}
			TagMatchCondition contentCondition = conditionParser.parse(content);
			if ( contentCondition != null ) {
				contentConditions.add(contentCondition);
			}
		}
	}

	public ITagMatchConditionParser getConditionParser() {
		return conditionParser;
	}

	public void setConditionParser(ITagMatchConditionParser conditionParser) {
		this.conditionParser = conditionParser;
	}
	
}


