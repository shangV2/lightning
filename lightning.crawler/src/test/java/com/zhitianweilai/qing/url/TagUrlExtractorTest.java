package com.zhitianweilai.qing.url;

import java.util.List;

import org.junit.Test;

import com.zhitianweilai.qing.crawler.QingCrawler;
import com.zhitianweilai.qing.crawler.exception.QingCrawlerException;

public class TagUrlExtractorTest {

	@Test
	public void test() {
//		fail("Not yet implemented");
		
		String url = "http://tb.tibet.cn/2011zt/2011ttzlxn/xs/";
		
		QingCrawler crawler = new QingCrawler();
		
		TagUrlExtractor urlExtractor = new TagUrlExtractor();
		
		try {
			String content = crawler.crawle(url);
			List<String> childUrls = urlExtractor.extract(url, content);	
			for ( String curl : childUrls ) {
				System.out.println("child url : " + curl);
			}
		} catch (QingCrawlerException e) {
			e.printStackTrace();
		}
		
		
	}

}
