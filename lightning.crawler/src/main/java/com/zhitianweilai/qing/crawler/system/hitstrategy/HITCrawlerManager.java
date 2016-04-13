//package com.zhitianweilai.qing.crawler.system.hitstrategy;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Queue;
//import java.util.concurrent.ConcurrentLinkedDeque;
//
//import com.zhitianweilai.qing.crawler.QingCrawler;
//import com.zhitianweilai.qing.crawler.detail.HitUrlRuleExtractor;
//import com.zhitianweilai.qing.crawler.detail.impl.TianShanHtmlTemplateExtractor;
//import com.zhitianweilai.qing.crawler.detail.impl.TianShanUrlRuleExtractor;
//import com.zhitianweilai.qing.crawler.detail.model.HitUri;
//import com.zhitianweilai.qing.crawler.exception.QingCrawlerException;
//import com.zhitianweilai.qing.crawler.system.HitStrategyPair;
//import com.zhitianweilai.qing.crawler.system.HtmlTemplateExtractorDispatcher;
//import com.zhitianweilai.qing.crawler.system.ICrawlerManager;
//import com.zhitianweilai.qing.crawler.system.IHtmlTemplateExtractor;
//import com.zhitianweilai.qing.crawler.system.TemplatePage;
//import com.zhitianweilai.qing.manager.BloomFilterDuplicateManager;
//import com.zhitianweilai.qing.url.TagUrlExtractor;
//import com.zhitianweilai.qing.utils.FileIOHelper;
//import com.zhitianweilai.qing.utils.UrlDetailHelper;
//
///**
// * 
// */
//public class HITCrawlerManager implements ICrawlerManager {
//
//	private HtmlTemplateExtractorDispatcher dispatcher = new HtmlTemplateExtractorDispatcher();
//	
//	private Queue<HitUri> queues = new ConcurrentLinkedDeque<HitUri>(new LinkedList<HitUri>());
//	
//	private BloomFilterDuplicateManager duplicateManager = new BloomFilterDuplicateManager();
//	
//	@Override
//	public void init() {
//		
//		
//		IHtmlTemplateExtractor htmlExtractor = new TianShanHtmlTemplateExtractor();
//		HitUrlRuleExtractor uriExtrator = new TianShanUrlRuleExtractor();
//		
//		HitStrategyPair pair = new HitStrategyPair(uriExtrator, htmlExtractor);
//		dispatcher.register("tianshan", pair);
//		
//		duplicateManager.init();
//		
//		postHitUrl(new HitUri("tianshan", "http://www.ts.cn/",
//				BaseCrawlerTaskType.NAVIGATION_PAGE_TYPE));
//		
//		
////		IHtmlTemplateExtractor htmlExtractor = new TibetanCNHtmlTemplateExtractor();
////		HitUrlRuleExtractor uriExtrator = new TibetanCNUrlRuleExtractor();
////		
////		HitStrategyPair pair = new HitStrategyPair(uriExtrator, htmlExtractor);
////		dispatcher.register("tibetancn", pair);
////		
////		duplicateManager.init();
////		
////		postHitUrl(new HitUri("tibetancn", "http://tb.tibet.cn/",
////				BaseCrawlerTaskType.NAVIGATION_PAGE_TYPE));
//				
//	}
//	
//	public void deliver(BaseCrawlerTask task) {
//		QingCrawler crawler = new QingCrawler();
//		try {
//			String content = crawler.crawle(task.getUrl());
//			if ( task.getTaskType() == BaseCrawlerTaskType.ARTICLE_PAGE_TYPE ) {
//				// article_page_type
//				
//			} else if ( task.getTaskType() == BaseCrawlerTaskType.NAVIGATION_PAGE_TYPE ) {
//				// navigation_page_type
//				
//			}
//		} catch (QingCrawlerException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void deliver(HitUri uri) {
//		
//		HitStrategyPair pair = dispatcher.queryHitStrategyPair(uri.getWebsite());
//		if ( pair == null ) {
//			return;
//		}
//		
//		IHtmlTemplateExtractor htmlExtractor = pair.getHtmlTemplateExtractor();
//		HitUrlRuleExtractor uriExtrator = pair.getUrlRuleExtractor();
//		TagUrlExtractor tagUrlExtrator = new TagUrlExtractor();
//		
//		String host = UrlDetailHelper.acquireHost(uri.getUrl());
//		if ( host == null ) {
//			return;
//		}
//		
//		QingCrawler crawler = new QingCrawler();
//		try {
//			String content = crawler.crawle(uri.getUrl());
//			if ( content != null ) {
//				if ( uri.getTaskType() == BaseCrawlerTaskType.ARTICLE_PAGE_TYPE ) {
//					TemplatePage page = htmlExtractor.extract(content);
//					// @modify
//					FileIOHelper.writeContentToFile("data/" + System.currentTimeMillis(), 
//							uri.getUrl().trim() + "\n" + page.getTitle().trim() + "\n" + page.getContent());	
//					
//					List<String> urls = tagUrlExtrator.extract(host, content);
//					List<HitUri> hitUris = uriExtrator.classify(urls);
//					for ( HitUri turi : hitUris ) {
//						this.postHitUrl(turi);
//					}
//					
//					//@log
//					System.out.println(uri.getUrl() + "----->>>>" + page.getTitle());
//					
//				} else if ( uri.getTaskType() == BaseCrawlerTaskType.NAVIGATION_PAGE_TYPE ) {
//					List<String> urls = tagUrlExtrator.extract(host, content);
//					List<HitUri> hitUris = uriExtrator.classify(urls);
//					for ( HitUri turi : hitUris ) {
//						this.postHitUrl(turi);
//					}
//					
//					//@log
//					System.out.println(uri.getUrl() + "----->>>>");
//				}
//			}
//		} catch(QingCrawlerException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void postHitUrl(HitUri uri) {
//		if ( !duplicateManager.isDuplicatable(uri.getWebsite(), uri.getUrl()) ) {
//			queues.offer(uri);
//			duplicateManager.doDuplicate(uri.getWebsite(), uri.getUrl());
//		}
//	}
//	
//	// @temp
//	public void startServer() {
//		new Thread() {
//			@Override
//			public void run() {
//				while ( true ) {
//					if ( !queues.isEmpty() ) {
//						HitUri uri = queues.poll();
//						deliver(uri);;
//					}
//					
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}.start();
//	}
//
//	
//	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//	public static void main(String[] args) {
//		
//		HITCrawlerManager crawlerManager = new HITCrawlerManager();
//		crawlerManager.init();
//		crawlerManager.startServer();
//		
//	}
//	
//}
