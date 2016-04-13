package com.zhitianweilai.qing.template.test;

import com.zhitianweilai.qing.crawler.QingCrawler;
import com.zhitianweilai.qing.crawler.exception.QingCrawlerException;
import com.zhitianweilai.qing.crawler.system.TemplatePage;
import com.zhitianweilai.qing.template.engine.HPHtmlTemplateExtractor;
import com.zhitianweilai.qing.template.engine.HPTagMatchEngine;
import com.zhitianweilai.qing.template.engine.SimpleTagMatchConditionParserImpl;

public class AppTest {
	
	
	public static void main(String[] args) {
	
		String url = "http://news.ts.cn/content/2013-09/06/content_8666195.htm";
		
		QingCrawler crawler = new QingCrawler();
		try {
			String content = crawler.crawle(url);
			System.out.println(content);
			
			HPTagMatchEngine engine = new HPTagMatchEngine();
			engine.setConditionParser(new SimpleTagMatchConditionParserImpl());
			engine.buildTagMatchCondition("<div class=\"title\" />", "<div class=\"txt link05\" >");
			
			HPHtmlTemplateExtractor extractor = new HPHtmlTemplateExtractor();
			extractor.setEngine(engine);
			
			TemplatePage page = extractor.extract(content);
			
			System.out.println("############################################################");
			
			System.out.println(page.getTitle());
			System.out.println("--------------------------------------------------------------");
			System.out.println(page.getContent());
			
		} catch (QingCrawlerException e) {
			e.printStackTrace();
		}
		
	}
	
}
