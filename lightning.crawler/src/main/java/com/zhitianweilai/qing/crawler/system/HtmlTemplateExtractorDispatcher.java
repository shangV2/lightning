package com.zhitianweilai.qing.crawler.system;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HtmlTemplateExtractorDispatcher {

	private Map<String, HitStrategyPair> histStrategyPairMap = new ConcurrentHashMap<String, HitStrategyPair>();
	
	public void register(String website, HitStrategyPair pair) {
		histStrategyPairMap.put(website, pair);
	}
	
	public HitStrategyPair queryHitStrategyPair(String website) {
		return histStrategyPairMap.get(website);
	}
	
}
