package com.zhitianweilai.qing.template.test;

import com.zhitianweilai.qing.crawler.QingCrawler;
import com.zhitianweilai.qing.crawler.exception.QingCrawlerException;
import com.zhitianweilai.qing.crawler.system.TemplatePage;
import com.zhitianweilai.qing.template.engine.ASTHPHtmlTemplateExtractor;
import com.zhitianweilai.qing.template.engine.ASTMatchNodeParser;
import com.zhitianweilai.qing.template.engine.ORASTMatchNode;

public class AppTest4 {
	
	
	public static void main(String[] args) {
	
		String url = "http://uy.ts.cn/node_6641.htm";
		
		QingCrawler crawler = new QingCrawler();
		try {
			String content = crawler.crawle(url);
			System.out.println(content);
			
			ASTMatchNodeParser parser = new ASTMatchNodeParser();
			ORASTMatchNode contentASTMatchNode = parser.parse("<div id=\"content_value\" > <p/> </div>");
			
			ASTHPHtmlTemplateExtractor extractor = new ASTHPHtmlTemplateExtractor();
			extractor.setContentRootASTMatchNode(contentASTMatchNode);
			
			ORASTMatchNode titleASTMatchNode = parser.parse(" <div class=\"content\"> <h2 /> </div>");
			extractor.setTitleRootASTMatchNode(titleASTMatchNode);
			
			ORASTMatchNode timeASTMatchNode = parser.parse("<div class=\"info link16\"><span is_filter='yes'/></div>");
			extractor.setTimeRootASTMatchNode(timeASTMatchNode);
			
			TemplatePage page = extractor.extract(content);
			
			System.out.println("############################################################");
			
			System.out.println(page.getTitle());
			System.out.println("--------------------------------------------------------------");
			System.out.println(page.getTimestamp());
			System.out.println("--------------------------------------------------------------");
			System.out.println(page.getContent());
			
			
		} catch (QingCrawlerException e) {
			e.printStackTrace();
		}
		
	}
	
}
