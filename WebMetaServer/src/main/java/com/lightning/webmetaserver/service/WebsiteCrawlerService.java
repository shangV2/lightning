package com.lightning.webmetaserver.service;

import java.util.List;

import com.lightning.webmetaserver.dataobject.WebsiteCrawlerDO;

public interface WebsiteCrawlerService {

	void addWebsiteCrawler(WebsiteCrawlerDO websiteCrawlerDO);
	
	List<WebsiteCrawlerDO> queryWebsiteCrawlers(int websiteType, int pageno, int pagesize);
	
	WebsiteCrawlerDO applyWebsiteCrawler(int websiteType);
	
	void updateWebsiteCrawler(WebsiteCrawlerDO websiteCrawlerDO);
	
	void removeWebsiteCrawler(int websiteType, int webid);
	
}
