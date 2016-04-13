package com.zhitianweilai.qing.template.engine;

import java.util.Map;
import java.util.TreeMap;

public class TagMatchCondition {

	private TagType tagType = TagType.HTML_TAG_NONE;
	
	private Map<String, String> attributes = new TreeMap<String, String>();

	public TagType getTagType() {
		return tagType;
	}

	public void setTagType(TagType tagType) {
		this.tagType = tagType;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	public void addAttribute(String key, String value) {
		attributes.put(key, value);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("<%s ", tagType.getValue()));
		for ( Map.Entry<String, String> entry : attributes.entrySet() ) {
			sb.append(String.format("%s='%s' ", entry.getKey(), entry.getValue()));
		}
		sb.append(" />");
		return sb.toString();
	}
	
	public String toXml(int indent) {
		StringBuilder sb = new StringBuilder();
		for ( int i = 0; i < indent; i++ ) {
			sb.append("\t");
		}
		sb.append("<").append(getTagType().getValue()).append(" ");
		Map<String, String> params = getAttributes();
		for ( Map.Entry<String, String> entry : params.entrySet() ) {
			sb.append(entry.getKey() + "=" + entry.getValue() + " ");
		}
		sb.append(">");
		return sb.toString();
	}
	
}

