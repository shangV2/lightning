package com.zhitianweilai.qing.crawler.system;

import com.zhitianweilai.qing.crawler.detail.HitUrlRuleExtractor;

public class HitStrategyPair {

	private HitUrlRuleExtractor urlRuleExtractor;
	
	private IHtmlTemplateExtractor htmlTemplateExtractor;

	public HitStrategyPair() {
	}

	public HitStrategyPair(HitUrlRuleExtractor urlRuleExtractor,
			IHtmlTemplateExtractor htmlTemplateExtractor) {
		this.urlRuleExtractor = urlRuleExtractor;
		this.htmlTemplateExtractor = htmlTemplateExtractor;
	}

	public HitUrlRuleExtractor getUrlRuleExtractor() {
		return urlRuleExtractor;
	}

	public void setUrlRuleExtractor(HitUrlRuleExtractor urlRuleExtractor) {
		this.urlRuleExtractor = urlRuleExtractor;
	}

	public IHtmlTemplateExtractor getHtmlTemplateExtractor() {
		return htmlTemplateExtractor;
	}

	public void setHtmlTemplateExtractor(
			IHtmlTemplateExtractor htmlTemplateExtractor) {
		this.htmlTemplateExtractor = htmlTemplateExtractor;
	}
	
}
