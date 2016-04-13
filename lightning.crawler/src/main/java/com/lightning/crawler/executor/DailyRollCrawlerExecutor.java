package com.lightning.crawler.executor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lightning.crawler.persistent.IWebPageItemPersistent;
import com.lightning.crawler.persistent.impl.DefaultFilePersistent;
import com.lightning.crawler.utility.TimeUtility;
import com.zhitianweilai.qing.crawler.QingCrawler;
import com.zhitianweilai.qing.crawler.detail.model.HitUri;
import com.zhitianweilai.qing.crawler.exception.QingCrawlerException;
import com.zhitianweilai.qing.crawler.executor.model.WebPageItem;
import com.zhitianweilai.qing.crawler.system.IHtmlTemplateExtractor;
import com.zhitianweilai.qing.crawler.system.TemplatePage;
import com.zhitianweilai.qing.crawler.system.hitstrategy.BaseCrawlerTaskType;
import com.zhitianweilai.qing.crawler.task.DailyWebCrawlerTask;
import com.zhitianweilai.qing.manager.SingleBloomFilterDuplicateManager;
import com.zhitianweilai.qing.template.engine.ASTHPHtmlTemplateExtractor;
import com.zhitianweilai.qing.template.engine.ASTMatchNodeParser;
import com.zhitianweilai.qing.template.engine.HPHtmlTemplateExtractor;
import com.zhitianweilai.qing.template.engine.HPUrlRuleExtractor;
import com.zhitianweilai.qing.template.engine.ORASTMatchNode;
import com.zhitianweilai.qing.url.TagUrlExtractor;
import com.zhitianweilai.qing.utils.TimeHelper;
import com.zhitianweilai.qing.utils.UrlDetailHelper;

public class DailyRollCrawlerExecutor {

	private static Logger logger = LoggerFactory.getLogger("crawler_logger");
	
	private Queue<HitUri> queues = new ConcurrentLinkedQueue<HitUri>(new LinkedList<HitUri>());
	
	private int pageWouldCrawlerNumber = 0;
	private int pageHadCrawleredNumber = 0;
	
	private SingleBloomFilterDuplicateManager duplicateManager = new SingleBloomFilterDuplicateManager();
	
	private SingleBloomFilterDuplicateManager dailyBloomFilterManager = new SingleBloomFilterDuplicateManager();
	
	private IHtmlTemplateExtractor htmlPageTemplateExtractor = new HPHtmlTemplateExtractor();
	
	private HPUrlRuleExtractor urlExtractor = new HPUrlRuleExtractor();
	
	private DailyWebCrawlerTask task = null;
	
	private IWebPageItemPersistent persistent = new DefaultFilePersistent();
	
	public void execute(DailyWebCrawlerTask task) {
		
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
		duplicateManager.init((int)task.getPageMaxLimit());
		dailyBloomFilterManager.init((int)task.getPageMaxLimit());
		
		do {
			
			// *) 
			queues.add(new HitUri(task.getWebsite(), task.getSeedUrl(), BaseCrawlerTaskType.NAVIGATION_PAGE_TYPE));
			pageWouldCrawlerNumber = 1;
			pageHadCrawleredNumber = 0;
			
			// *) 
			while ( pageHadCrawleredNumber < task.getPageMaxLimit() && !queues.isEmpty() ) {
				HitUri uri = queues.poll();
				deliver(uri);
				pageHadCrawleredNumber++;
				
				TimeUtility.sleep(1000);
			}
			
			String timestamp = task.getTimestamp();
			
			String now = TimeHelper.currentDay();
			if ( !timestamp.equalsIgnoreCase(now) ) {
				break;
			}
			
			queues.clear();
			duplicateManager.clear();
			
			TimeUtility.sleep(1000 * 60 * 10);
			
		} while (true);
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
						// *) filter based on timestmap
						// *) base data
						if ( task.getTimestamp().equalsIgnoreCase(page.getTimestamp()) ) {
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
					}
					
					List<String> urls = tagUrlExtrator.extract(uri.getUrl(), content);
					List<HitUri> hitUris = urlExtractor.classify(urls);
					for ( HitUri turi : hitUris ) {
						this.postHitUrl(turi);
					}
					
					//@log
//					System.out.println(uri.getUrl() + "----->>>>" + page.getTitle());
					logger.info(uri.getUrl() + " -- @timestamp: " + page.getTimestamp() + " -- @title" + page.getTitle());
					
				} else if ( uri.getTaskType() == BaseCrawlerTaskType.NAVIGATION_PAGE_TYPE ) {
					List<String> urls = tagUrlExtrator.extract(uri.getUrl(), content);
					List<HitUri> hitUris = urlExtractor.classify(urls);
					for ( HitUri turi : hitUris ) {
						this.postHitUrl(turi);
					}
					
					//@log
//					System.out.println(uri.getUrl() + "----->>>>");
					logger.info(uri.getUrl() + "----->>>>");
					
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

		BaseCrawlerTaskType taskType = uri.getTaskType();
		if ( taskType == BaseCrawlerTaskType.ARTICLE_PAGE_TYPE ) {
			// *) 
			if ( !dailyBloomFilterManager.isDuplicatable(uri.getWebsite(), uri.getUrl()) ) {
				queues.offer(uri);
				pageWouldCrawlerNumber++;
				dailyBloomFilterManager.doDuplicate(uri.getWebsite(), uri.getUrl());
			}
		} else if ( taskType == BaseCrawlerTaskType.NAVIGATION_PAGE_TYPE ) {
			// *) 
			if ( !duplicateManager.isDuplicatable(uri.getWebsite(), uri.getUrl()) ) {
				queues.offer(uri);
				pageWouldCrawlerNumber++;
				duplicateManager.doDuplicate(uri.getWebsite(), uri.getUrl());
			}
		}
		
	}

	public IWebPageItemPersistent getPersistent() {
		return persistent;
	}

	public void setPersistent(IWebPageItemPersistent persistent) {
		this.persistent = persistent;
	}
	
}
