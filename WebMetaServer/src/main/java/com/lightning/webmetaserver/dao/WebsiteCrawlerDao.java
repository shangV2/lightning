package com.lightning.webmetaserver.dao;

import java.util.List;

import com.lightning.webmetaserver.dataobject.WebsiteCrawlerDO;

public interface WebsiteCrawlerDao {
	
	// =================================
	// for website crud
	
	public void insertWebsiteCrawler(WebsiteCrawlerDO websiteCrawler);
	
//	public WebsiteCrawlerDO queryWebsiteCrawler(String website);
	
	public List<WebsiteCrawlerDO> queryWebsiteCrawlers(int websiteType, int pageno, int pagesize);
	
	public boolean existWebsiteCrawler(String website, int websiteType);

	public void updateWebsiteCrawler(WebsiteCrawlerDO websiteCrawler);

	public void removeWebsiteCrawler(int websiteType, int webid);
	
	// ==================================
	// for task 
	
	public List<WebsiteCrawlerDO> queryWebsiteCrawlersWithNonTimestamp(int timestamp, int websiteType, int pageno, int pagesize);

	//	public int updateScheduleForWebsiteCrawlers(String website, int websiteType, int timestamp);
	public int updateScheduleForWebsiteCrawler(int websiteType, int webid, int timestamp);
	
	
	// =================================
	// for test
	// *) 
	public void cleanup();
	
}
