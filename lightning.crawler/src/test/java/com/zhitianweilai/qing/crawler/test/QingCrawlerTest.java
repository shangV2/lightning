//package com.zhitianweilai.qing.crawler.test;
//
//import java.util.List;
//
//import org.junit.Test;
//
//import com.zhitianweilai.qing.crawler.QingCrawler;
//import com.zhitianweilai.qing.crawler.detail.impl.TianShanHtmlTemplateExtractor;
//import com.zhitianweilai.qing.crawler.detail.impl.TibetanCNHtmlTemplateExtractor;
//import com.zhitianweilai.qing.crawler.exception.QingCrawlerException;
//import com.zhitianweilai.qing.crawler.system.TemplatePage;
//import com.zhitianweilai.qing.url.TagUrlExtractor;
//import com.zhitianweilai.qing.url.UriDetail;
//import com.zhitianweilai.qing.utils.UrlDetailHelper;
//
//public class QingCrawlerTest {
//
//	
//	@Test
//	public void testCrawle() {
//
//		String url = "http://tb.tibet.cn/2011zt/2011ttzlxn/yp/index.htm";
//		
//		QingCrawler crawler = new QingCrawler();
//		try {
//			String content = crawler.crawle(url);
//			
//			TagUrlExtractor extractor = new TagUrlExtractor();
//			List<String> urls = extractor.extract(url, content);
//			
//			for ( String turl : urls ) {
//				System.out.println("url: " + turl);
//			}
//			
//		} catch (QingCrawlerException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Test
//	public void testTianShanCrawle() {
//		
//		String url = "http://news.ts.cn/content/2013-09/06/content_8666195.htm";
//		
//		QingCrawler crawler = new QingCrawler();
//		try {
//			String content = crawler.crawle(url);
//			System.out.println(content);
//			
//			TagUrlExtractor extractor = new TagUrlExtractor();
//			List<String> urls = extractor.extract(content);
//			
//			for ( String turl : urls ) {
//				System.out.println("url: " + turl);
//			}
//			
//			TianShanHtmlTemplateExtractor htmlExtractor = new TianShanHtmlTemplateExtractor();
//			TemplatePage textContent = htmlExtractor.extract(content);
//			
//			System.out.println("###################################");
//			System.out.println(textContent.getContent());
//			
//			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//			System.out.println(textContent.getTitle());
//			
//		} catch (QingCrawlerException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Test
//	public void testTibetanCrawle() {
//		
//		String url = "http://tb.tibet.cn/zt2009/09zltnn/xngs/200902/t20090211_452051.htm";
//		
//		QingCrawler crawler = new QingCrawler();
//		try {
//			String content = crawler.crawle(url);
//			System.out.println(content);
//			
//			TagUrlExtractor extractor = new TagUrlExtractor();
//			List<String> urls = extractor.extract(content);
//			
//			for ( String turl : urls ) {
//				System.out.println("url: " + turl);
//			}
//			
//			TibetanCNHtmlTemplateExtractor htmlExtractor = new TibetanCNHtmlTemplateExtractor();
//			TemplatePage textContent = htmlExtractor.extract(content);
//			
//			System.out.println("###################################");
//			System.out.println(textContent.getContent());
//			
//			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//			System.out.println(textContent.getTitle());
//			
//		} catch (QingCrawlerException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Test
//	public void testURL() {
//		String host = "http://tb.tibet.cn/2011zt/2011ttzlxn/yp/index.htm";
//		String url = "./201103/t20110303_944014.htm"; 
//
//		UriDetail uriDetail = UrlDetailHelper.parse(host);
//		String path = uriDetail.getPath();
//		if ( path != null ) {
//			while ( true ) {
//				if ( url.startsWith("./") ) {
//					url = url.substring(2);
//					int lastIndex = path.lastIndexOf('/');
//					if ( lastIndex != -1 ) {
//						path = path.substring(0, lastIndex);
//					}
//				} else if ( url.startsWith("../") ) {
//					url = url.substring(3);
//					int lastIndex = path.lastIndexOf('/');
//					if ( lastIndex != -1 ) {
//						path = path.substring(0, lastIndex);
//					}
//				} else {
//					break;
//				}
//			}
//			System.out.println("http://" + uriDetail.getHost() + "/" + path + "/" + url);
//		}
//	}
//	
//	
//	@Test
//	public void testUy() {
//
////		String url = "http://uy.ts.cn/";
//		String url = "http://uy.ts.cn/node_6641.htm";
//		
//		QingCrawler crawler = new QingCrawler();
//		try {
//			String content = crawler.crawle(url);
//			
//			TagUrlExtractor extractor = new TagUrlExtractor();
//			List<String> urls = extractor.extract(url, content);
//			
//			for ( String turl : urls ) {
//				System.out.println("url: " + turl);
//			}
//			
//		} catch (QingCrawlerException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//}
