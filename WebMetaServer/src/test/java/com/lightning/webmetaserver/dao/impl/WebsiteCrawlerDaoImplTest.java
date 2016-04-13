package com.lightning.webmetaserver.dao.impl;

import java.util.List;



import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lightning.webmetaserver.dao.WebsiteCrawlerDao;
import com.lightning.webmetaserver.dataobject.WebsiteCrawlerDO;

public class WebsiteCrawlerDaoImplTest {

	
	private WebsiteCrawlerDao dao = null;
	
	@Before
	public void setupCases() {
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"conf/application_context.xml");
		dao = (WebsiteCrawlerDao) applicationContext
				.getBean("websiteCrawlerDao");

	}
	
	@Test
	public void testInsertWebsiteCrawler() {

		cleanup();
		
		WebsiteCrawlerDO websiteCrawler = new WebsiteCrawlerDO();
		websiteCrawler.setContentRule("content_rule");
		websiteCrawler.setContentType(0);
		websiteCrawler.setCrawlerNum(100);
		websiteCrawler.setCrawlerSchedule(24);
		websiteCrawler.setCrawlerType(0);
		websiteCrawler.setSeeds("seeds list");
		websiteCrawler.setUrlRule("url rule");
		websiteCrawler.setUrlType(0);
		websiteCrawler.setWebsite("web site");
		websiteCrawler.setWebsiteType(0);

		dao.insertWebsiteCrawler(websiteCrawler);

	}
	
	@Test
	public void testExistWebsiteCrawler() {

		cleanup();
		
		String website = "website1";
		int websiteType = 0;
		// existWebsiteCrawler
		boolean res = dao.existWebsiteCrawler(website, websiteType);
		Assert.assertTrue(res == false);
		
		{
			WebsiteCrawlerDO websiteCrawler = new WebsiteCrawlerDO();
			websiteCrawler.setContentRule("content_rule");
			websiteCrawler.setContentType(0);
			websiteCrawler.setCrawlerNum(100);
			websiteCrawler.setCrawlerSchedule(24);
			websiteCrawler.setCrawlerType(0);
			websiteCrawler.setSeeds("seeds list");
			websiteCrawler.setUrlRule("url rule");
			websiteCrawler.setUrlType(0);
			websiteCrawler.setWebsite("web site");
			websiteCrawler.setWebsiteType(0);

			dao.insertWebsiteCrawler(websiteCrawler);
		}
		
		Assert.assertTrue(dao.existWebsiteCrawler("web site", 0) == true);
		
	}
	
	@Test
	public void testFetchWebsiteCrawler() {
		// queryWebsiteCrawlers
		
	}
	
	@Test
	public void testQueryWebsiteCrawlers() {
		
	}
	
	@Test
	public void testQueryWebsiteCrawlersWithNonTimestamp() {
		
		cleanup();
		{
			WebsiteCrawlerDO websiteCrawler = new WebsiteCrawlerDO();
			websiteCrawler.setContentRule("content_rule");
			websiteCrawler.setContentType(0);
			websiteCrawler.setCrawlerNum(100);
			websiteCrawler.setCrawlerSchedule(24);
			websiteCrawler.setCrawlerType(0);
			websiteCrawler.setSeeds("seeds list");
			websiteCrawler.setUrlRule("url rule");
			websiteCrawler.setUrlType(0);
			websiteCrawler.setWebsite("web site");
			websiteCrawler.setWebsiteType(0);

			dao.insertWebsiteCrawler(websiteCrawler);
		}
		
		int timestamp = 20140723;
		List<WebsiteCrawlerDO> crawlerDos = dao.queryWebsiteCrawlersWithNonTimestamp(timestamp, 0,  0, 10);
		for ( WebsiteCrawlerDO crawlerDo : crawlerDos ) {
//			System.out.println(crawlerDo.toString());
			System.out.println(crawlerDo.getUrlRule());
			System.out.println(crawlerDo.getContentRule());
		}
		
		timestamp = 24;
		List<WebsiteCrawlerDO> crawlerDos2 = dao.queryWebsiteCrawlersWithNonTimestamp(timestamp, 0, 0, 10);
		
		Assert.assertEquals(crawlerDos2.size(), 0);
		
	}
	
	@Test
	public void testUpdateScheduleForWebsiteCrawlers() {
		// updateScheduleForWebsiteCrawlers

		cleanup();

		String website = "cnbeta";
		int websiteType = 0;
		int timestamp = 20140725;
		
		{
			WebsiteCrawlerDO websiteCrawler = new WebsiteCrawlerDO();
			websiteCrawler.setContentRule("content_rule");
			websiteCrawler.setContentType(0);
			websiteCrawler.setCrawlerNum(100);
			websiteCrawler.setCrawlerType(0);
			websiteCrawler.setSeeds("seeds list");
			websiteCrawler.setUrlRule("url rule");
			websiteCrawler.setUrlType(0);
			websiteCrawler.setWebsite(website);
			websiteCrawler.setWebsiteType(websiteType);
			websiteCrawler.setCrawlerSchedule(timestamp);
			dao.insertWebsiteCrawler(websiteCrawler);
		}
		
		List<WebsiteCrawlerDO> wdos = dao.queryWebsiteCrawlers(websiteType, 0, 10);

		Assert.assertEquals(wdos.size(), 1);
		
		WebsiteCrawlerDO wcdo = wdos.get(0);
		
		// affect_row => 0,
		timestamp = 20140726;
		int res = dao.updateScheduleForWebsiteCrawler(websiteType, wcdo.getWebid(), timestamp);
		
		Assert.assertEquals(res, 1);
		
		res = dao.updateScheduleForWebsiteCrawler(websiteType, wcdo.getWebid(), timestamp);
		Assert.assertEquals(res, 0);
		
	}
	
	// *) ......
	public void cleanup() {
		dao.cleanup();
	}

	@After
	public void tearDown() {
		cleanup();
	}
	
}
