package com.zhitianweilai.qing.template.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import com.zhitianweilai.qing.crawler.detail.HitUrlRuleExtractor;
import com.zhitianweilai.qing.crawler.detail.model.HitUri;
import com.zhitianweilai.qing.crawler.system.hitstrategy.BaseCrawlerTaskType;
import com.zhitianweilai.qing.url.UriDetail;
import com.zhitianweilai.qing.utils.UrlDetailHelper;

public class HPUrlRuleExtractor implements HitUrlRuleExtractor {

	private String website;
	
	private List<String> hostRegulations = new ArrayList<String>();
	private List<Pattern> hostPatterns = new ArrayList<Pattern>();
	
	private List<String> navPagePostfixRegulations = new ArrayList<String>();
	private List<Pattern> navPagePatterns = new ArrayList<Pattern>();
	
	private List<String> conPagePostfixRegulations = new ArrayList<String>();
	private List<Pattern> conPagePatterns = new ArrayList<Pattern>();
	
	// -------------------------------------------------------
	
	public boolean build(String website, List<String> hosts, List<String> navigations, List<String> contents) {
		
		this.website = website;
		
		hostRegulations.addAll(hosts);
		for ( String regulation : hostRegulations ) {
			Pattern p = Pattern.compile(regulation);
			hostPatterns.add(p);
		}
		
		navPagePostfixRegulations.addAll(navigations);
		for ( String regulation : navPagePostfixRegulations ) {
			Pattern p = Pattern.compile(regulation);
			navPagePatterns.add(p);
		}
		
		conPagePostfixRegulations.addAll(contents);
		for ( String regulation : conPagePostfixRegulations ) {
			Pattern p = Pattern.compile(regulation);
			conPagePatterns.add(p);
		}
		return true;
		
	}
	
	/**
	 * 
	 * @brief	
	 * 
	 * @param urls
	 * @return
	 */
	@Override
	public List<HitUri> classify(List<String> urls) {
		
		List<HitUri> uris = new ArrayList<HitUri>();
		Iterator<String> iter = urls.iterator();
		while ( iter.hasNext() ) {
			String url = iter.next();
			UriDetail detail = UrlDetailHelper.parse(url);
			BaseCrawlerTaskType type = arbitrate(detail);
			switch(type) {
			case ARTICLE_PAGE_TYPE:
				uris.add(new HitUri(website, url, BaseCrawlerTaskType.ARTICLE_PAGE_TYPE));
				break;
			case NAVIGATION_PAGE_TYPE:
				uris.add(new HitUri(website, url, BaseCrawlerTaskType.NAVIGATION_PAGE_TYPE));
				break;
			}
		}
		return uris;
		
	}
	
	private BaseCrawlerTaskType arbitrate(UriDetail detail) {
		
		String schema = detail.getSchema();
		if ( schema == null || !"http".equals(schema) ) {
			return BaseCrawlerTaskType.INVALID_PAGE_TYPE;
		}

		String host = detail.getHost();
		String path = detail.getPath();
		if ( host == null || host.length() == 0 ) {
			return BaseCrawlerTaskType.INVALID_PAGE_TYPE;
		}
		
		if ( !satisfyHostPattern(host) ) {
			return BaseCrawlerTaskType.OTHER_PAGE_TYPE;
		}
		
		if ( path == null || path.length() == 0 ) {
			return BaseCrawlerTaskType.NAVIGATION_PAGE_TYPE;
		} else if ( path.endsWith("index.htm") || path.endsWith("index.html") ) {
			return BaseCrawlerTaskType.NAVIGATION_PAGE_TYPE;
		}
		
		if ( satisfyNavPagePattern(path) ) {
			return BaseCrawlerTaskType.NAVIGATION_PAGE_TYPE;
		}
		
		if ( satisfyConPagePattern(path) ) {
			return BaseCrawlerTaskType.ARTICLE_PAGE_TYPE;
		}
		
		return BaseCrawlerTaskType.OTHER_PAGE_TYPE;
	
	}

	private boolean satisfyHostPattern(String host) {
		for ( Pattern p : hostPatterns ) {
			if ( p.matcher(host).matches() ) {
				return true;
			}
		}
		return false;
	}
	
	private boolean satisfyNavPagePattern(String path) {
		if ( path == null || path.length() == 0 ) {
			return false;
		}
		for ( Pattern p : navPagePatterns ) {
			if ( p.matcher(path).matches() ) {
				return true;
			}
		}
		return false;
	}
	
	private boolean satisfyConPagePattern(String path) {
		if ( path == null || path.length() == 0 ) {
			return false;
		}
		for ( Pattern p : conPagePatterns ) {
			if ( p.matcher(path).find() ) {
				return true;
			}
		}
		return false;
	}
	
	
}
