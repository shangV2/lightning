package com.zhitianweilai.qing.template.test;

import com.zhitianweilai.qing.crawler.QingCrawler;
import com.zhitianweilai.qing.crawler.exception.QingCrawlerException;
import com.zhitianweilai.qing.crawler.system.TemplatePage;
import com.zhitianweilai.qing.template.engine.ASTHPHtmlTemplateExtractor;
import com.zhitianweilai.qing.template.engine.ASTMatchNodeParser;
import com.zhitianweilai.qing.template.engine.ORASTMatchNode;

public class AppTest5 {
	
	
	/*
	 * 		task.setTitleExpression("<tbody><div align=\"left\" class=\"style7\"> </div></tbody>");
		task.setContentExpression("<div id=\"cms_content_div\"> <span /> </div>");
		task.setTimeExpression("<tr><td height=\"40\" ><div align=\"centor\"> </div> </td> </tr>");	
	 */
	
	
	public static void main(String[] args) {
	
		String url = "http://uy.ts.cn/news/content/2014-09/12/content_374645.htm";
		
		QingCrawler crawler = new QingCrawler();
		try {
			String content = crawler.crawle(url);
			System.out.println(content);
			
			ASTMatchNodeParser parser = new ASTMatchNodeParser();
			ORASTMatchNode contentASTMatchNode = parser.parse("<div id=\"content_value\" ><p/></div>");
			
			ASTHPHtmlTemplateExtractor extractor = new ASTHPHtmlTemplateExtractor();
			extractor.setContentRootASTMatchNode(contentASTMatchNode);
			
			ORASTMatchNode titleASTMatchNode = parser.parse("<div id=\"content\"><div class=\"content\"><h2 /></div></div>");
			extractor.setTitleRootASTMatchNode(titleASTMatchNode);
			
			ORASTMatchNode timeASTMatchNode = parser.parse("<div class=\"times\" ><table><tr><td></td></tr></table></div>");
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
