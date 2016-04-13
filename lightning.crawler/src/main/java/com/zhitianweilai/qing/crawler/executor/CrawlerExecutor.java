package com.zhitianweilai.qing.crawler.executor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.lightning.crawler.persistent.IWebPageItemPersistent;
import com.lightning.crawler.persistent.impl.DefaultFilePersistent;
import com.zhitianweilai.qing.crawler.QingCrawler;
import com.zhitianweilai.qing.crawler.detail.model.HitUri;
import com.zhitianweilai.qing.crawler.exception.QingCrawlerException;
import com.zhitianweilai.qing.crawler.executor.model.WebPageItem;
import com.zhitianweilai.qing.crawler.system.IHtmlTemplateExtractor;
import com.zhitianweilai.qing.crawler.system.TemplatePage;
import com.zhitianweilai.qing.crawler.system.hitstrategy.BaseCrawlerTaskType;
import com.zhitianweilai.qing.crawler.task.WebCrawlerTask;
import com.zhitianweilai.qing.manager.SingleBloomFilterDuplicateManager;
import com.zhitianweilai.qing.template.engine.ASTHPHtmlTemplateExtractor;
import com.zhitianweilai.qing.template.engine.ASTMatchNodeParser;
import com.zhitianweilai.qing.template.engine.HPHtmlTemplateExtractor;
import com.zhitianweilai.qing.template.engine.HPUrlRuleExtractor;
import com.zhitianweilai.qing.template.engine.ORASTMatchNode;
import com.zhitianweilai.qing.url.TagUrlExtractor;
import com.zhitianweilai.qing.utils.UrlDetailHelper;

public class CrawlerExecutor {

	private Queue<HitUri> queues = new ConcurrentLinkedQueue<HitUri>(new LinkedList<HitUri>());
	
	private int pageWouldCrawlerNumber = 0;
	private int pageHadCrawleredNumber = 0;
	
	private SingleBloomFilterDuplicateManager duplicateManager = new SingleBloomFilterDuplicateManager();
	
	private IHtmlTemplateExtractor htmlPageTemplateExtractor = new HPHtmlTemplateExtractor();
	
	private HPUrlRuleExtractor urlExtractor = new HPUrlRuleExtractor();
	
	private WebCrawlerTask task = null;
	
	private IWebPageItemPersistent persistent = new DefaultFilePersistent();
	
	public void execute(WebCrawlerTask task) {
		
		this.task = task;
		
		ASTMatchNodeParser parser = new ASTMatchNodeParser();
		ORASTMatchNode titleASTMatchNode = parser.parse(task.getTitleExpression());
		ORASTMatchNode contentASTMatchNode = parser.parse(task.getContentExpression());
		ORASTMatchNode timeASTMatchNode = parser.parse(task.getTimeExpression());
		
		ASTHPHtmlTemplateExtractor extractor = new ASTHPHtmlTemplateExtractor();
		extractor.setTitleRootASTMatchNode(titleASTMatchNode);
		extractor.setContentRootASTMatchNode(contentASTMatchNode);
		extractor.setTimeRootASTMatchNode(timeASTMatchNode);
		
		htmlPageTemplateExtractor = extractor;
		
		urlExtractor.build(task.getWebsite(), 
				Arrays.asList(new String[] {task.getHostExpression()}),
				Arrays.asList(new String[] {task.getNavPageExpression()}),
				Arrays.asList(new String[] {task.getConPageExpression()})
			);
		
		// *) bloomfilter
		duplicateManager.init();
		
		// *) 
		queues.add(new HitUri(task.getWebsite(), task.getSeedUrl(), BaseCrawlerTaskType.NAVIGATION_PAGE_TYPE));
		pageWouldCrawlerNumber = 1;
		pageHadCrawleredNumber = 0;
		
		// *) 
		while ( pageHadCrawleredNumber < task.getPageMaxLimit() && !queues.isEmpty() ) {
			HitUri uri = queues.poll();
			deliver(uri);
			pageHadCrawleredNumber++;
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void deliver(HitUri uri) {
		
		TagUrlExtractor tagUrlExtrator = new TagUrlExtractor();
		
		String host = UrlDetailHelper.acquireHost(uri.getUrl());
		if ( host == null ) {
			return;
		}
		
		QingCrawler crawler = new QingCrawler();
		try {
			String content = crawler.crawle(uri.getUrl());
			if ( content != null ) {
				if ( uri.getTaskType() == BaseCrawlerTaskType.ARTICLE_PAGE_TYPE ) {
					TemplatePage page = htmlPageTemplateExtractor.extract(content);
					
					if ( page.getTitle().trim().length() != 0 ) {
						WebPageItem item = new WebPageItem();
						item.setUrl(uri.getUrl().trim());
						item.setSource(task.getWebsite());
						item.setTitle(page.getTitle().trim());
						item.setTimestamp(page.getTimestamp());
						item.setContent(page.getContent());
						if ( persistent != null ) {
							persistent.persist(item);
						}
					}
					
					List<String> urls = tagUrlExtrator.extract(uri.getUrl(), content);
					List<HitUri> hitUris = urlExtractor.classify(urls);
					for ( HitUri turi : hitUris ) {
						this.postHitUrl(turi);
					}
					
					//@log
					System.out.println(uri.getUrl() + "----->>>>" + page.getTitle());
					
				} else if ( uri.getTaskType() == BaseCrawlerTaskType.NAVIGATION_PAGE_TYPE ) {
					List<String> urls = tagUrlExtrator.extract(uri.getUrl(), content);
					List<HitUri> hitUris = urlExtractor.classify(urls);
					for ( HitUri turi : hitUris ) {
						this.postHitUrl(turi);
					}
					
					//@log
					System.out.println(uri.getUrl() + "----->>>>");
				}
			}
		} catch(QingCrawlerException e) {
			e.printStackTrace();
		}
	}
	
	public void postHitUrl(HitUri uri) {

		// *)
		long maxLimit = task.getPageMaxLimit();
		if ( pageWouldCrawlerNumber > maxLimit ) { 
			return;
		}

		// *) 
		if ( !duplicateManager.isDuplicatable(uri.getWebsite(), uri.getUrl()) ) {
			queues.offer(uri);
			pageWouldCrawlerNumber++;
			duplicateManager.doDuplicate(uri.getWebsite(), uri.getUrl());
		}
		
	}

	public IWebPageItemPersistent getPersistent() {
		return persistent;
	}

	public void setPersistent(IWebPageItemPersistent persistent) {
		this.persistent = persistent;
	}
	
}
