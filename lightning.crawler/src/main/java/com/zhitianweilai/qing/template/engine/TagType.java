package com.zhitianweilai.qing.template.engine;

public enum TagType {

	HTML_TAG_NONE("none"),					// none
	HTML_TAG_DIV("div"),					// div
	HTML_TAG_P("p"),						// p
	HTML_TAG_TABLE("table"),				// table
	HTML_TAG_TD("td"),						// td
	HTML_TAG_TR("tr"),						// tr
	HTML_TAG_H1("h1"),
	HTML_TAG_H2("h2"),						// h2
	HTML_TAG_H3("h3"),
	HTML_TAG_DL("dl"),						// dl
	HTML_TAG_DT("dt"),						// dt
	HTML_TAG_SPAN("span"),					// span
	HTML_TAG_TITLE("title"),
	HTML_TAG_HEAD("head"),
	HTML_TAG_TBODY("tbody"),
	;

	private String name;
	
	private TagType(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return name;
	}
	
	public static TagType match(String tagName) {
		for ( TagType tag : TagType.values() ) {
			if ( tag.getValue().equalsIgnoreCase(tagName) ) {
				return tag;
			}
		}
		return TagType.HTML_TAG_NONE;
	}
	
	
}
