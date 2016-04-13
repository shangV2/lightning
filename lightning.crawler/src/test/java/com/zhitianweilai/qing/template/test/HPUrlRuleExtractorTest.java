package com.zhitianweilai.qing.template.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.zhitianweilai.qing.crawler.detail.model.HitUri;
import com.zhitianweilai.qing.crawler.system.hitstrategy.BaseCrawlerTaskType;
import com.zhitianweilai.qing.template.engine.HPUrlRuleExtractor;

public class HPUrlRuleExtractorTest {

	// -------------------------------------------
	
	@Test
	public void test1001() {
		
		HPUrlRuleExtractor extractor = new HPUrlRuleExtractor();
		List<String> hosts = Arrays.asList(new String[] {
			"[a-z]*\\.ts\\.cn",
		});
		List<String> navigations = Arrays.asList(new String[] {
			""
		});
		List<String> contents = Arrays.asList(new String[] {
			"content_(\\d)+.htm$"	
		});
		
		extractor.build("tianshan", hosts, navigations, contents);
		
		// ---------------------------------------------------------

		do {
			List<String> urls = Arrays.asList(new String[] { 
					"http://news.ts.cn/2013-09/11/index.htm", 
					"http://finance.ts.cn/",
					"http://culture.ts.cn/",
					"http://www.ts.cn/",
			});
			List<HitUri> uris = extractor.classify(urls);
			Assert.assertEquals(urls.size(), uris.size());
			for ( HitUri uri : uris ) {
				Assert.assertEquals(uri.getTaskType(), BaseCrawlerTaskType.NAVIGATION_PAGE_TYPE);
			}
		} while (false);

		do {
			List<String> urls = Arrays.asList(new String[] { 
					"http://news.ts.cn/content/2013-09/09/content_8677338.htm", 
					"http://news.ts.cn/content/2013-09/10/content_8678442.htm", 
			});
			List<HitUri> uris = extractor.classify(urls);
			Assert.assertEquals(uris.size(), urls.size());
			for ( HitUri uri : uris ) {
				Assert.assertEquals(uri.getTaskType(), BaseCrawlerTaskType.ARTICLE_PAGE_TYPE);
			}
		} while (false);
		
		do {
			List<String> urls = Arrays.asList(new String[] { 
					"http://www.baidu.com/",
					"http://news.ts.cn/asdfasdfsdf.htm"
			});
			List<HitUri> uris = extractor.classify(urls);
			Assert.assertEquals(uris.size(), 0);
		} while (false);
		
	}
	
	// -------------------------------------------
	
	@Test
	public void test1002() {
		
	}
	
}
