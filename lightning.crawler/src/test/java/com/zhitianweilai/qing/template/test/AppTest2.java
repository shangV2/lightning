package com.zhitianweilai.qing.template.test;

import java.util.TreeMap;

import com.zhitianweilai.qing.crawler.QingCrawler;
import com.zhitianweilai.qing.crawler.exception.QingCrawlerException;
import com.zhitianweilai.qing.crawler.system.TemplatePage;
import com.zhitianweilai.qing.template.engine.ASTHPHtmlTemplateExtractor;
import com.zhitianweilai.qing.template.engine.ASTMatchNode;
import com.zhitianweilai.qing.template.engine.ASTMatchNodeType;
import com.zhitianweilai.qing.template.engine.ORASTMatchNode;
import com.zhitianweilai.qing.template.engine.TagMatchCondition;
import com.zhitianweilai.qing.template.engine.TagType;

public class AppTest2 {
	
	
	public static void main(String[] args) {
	
		String url = "http://news.ts.cn/content/2013-09/06/content_8666195.htm";
		
		QingCrawler crawler = new QingCrawler();
		try {
			String content = crawler.crawle(url);
			System.out.println(content);
			
			TagMatchCondition c1 = new TagMatchCondition();
			c1.setTagType(TagType.HTML_TAG_DIV);
			c1.setAttributes(new TreeMap<String, String>() {{put("class", "txt link05");}});
			
			TagMatchCondition cf1 = new TagMatchCondition();
			cf1.setTagType(TagType.HTML_TAG_DIV);
			
			ASTMatchNode cp = new ASTMatchNode();
			TagMatchCondition c2 = new TagMatchCondition();
			c2.setTagType(TagType.HTML_TAG_P);
			c2.setAttributes(new TreeMap<String, String>());
			cp.setNodeType(ASTMatchNodeType.AST_MATCH_NODE_LEAF);
			cp.setCondition(c2);
			
			ASTMatchNode contentASTMatchNode = new ASTMatchNode();
			contentASTMatchNode.setNodeType(ASTMatchNodeType.AST_MATCH_NODE_LEAF);
			contentASTMatchNode.setCondition(c1);
			contentASTMatchNode.getFilterConditions().add(cf1);
			contentASTMatchNode.getChildrenNodes().add(cp);
			
			
			ASTMatchNode titleASTMatchNode = new ASTMatchNode();
			TagMatchCondition c3 = new TagMatchCondition();
			c3.setTagType(TagType.HTML_TAG_DIV);
			c3.setAttributes(new TreeMap<String, String>() {{put("class", "title");}});
			titleASTMatchNode.setCondition(c3);
			titleASTMatchNode.setNodeType(ASTMatchNodeType.AST_MATCH_NODE_LEAF);
			
			ASTHPHtmlTemplateExtractor extractor = new ASTHPHtmlTemplateExtractor();
			ORASTMatchNode or1 = new ORASTMatchNode();
			or1.getChildrenNodes().add(contentASTMatchNode);
			extractor.setContentRootASTMatchNode(or1);
			
			ORASTMatchNode or2 = new ORASTMatchNode();
			or2.getChildrenNodes().add(titleASTMatchNode);
			extractor.setTitleRootASTMatchNode(or2);
			
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
