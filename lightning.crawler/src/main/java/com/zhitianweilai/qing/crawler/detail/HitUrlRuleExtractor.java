package com.zhitianweilai.qing.crawler.detail;

import java.util.List;

import com.zhitianweilai.qing.crawler.detail.model.HitUri;

public interface HitUrlRuleExtractor {
	
	public List<HitUri> classify(List<String> urls);

}

