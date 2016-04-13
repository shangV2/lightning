package com.lightning.webmetaserver.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.lightning.webmetaserver.dao.WebsiteCrawlerDao;
import com.lightning.webmetaserver.dataobject.WebsiteCrawlerDO;
import com.lightning.webmetaserver.service.WebsiteCrawlerService;
import com.lightning.webmetaserver.utility.TimeUtility;

public class WebsiteCrawlerServiceImpl implements WebsiteCrawlerService {

	@Resource
	private WebsiteCrawlerDao websiteCrawlerDao;

	@Override
	public void addWebsiteCrawler(WebsiteCrawlerDO websiteCrawlerDO) {
		
		// *) 判断是否已存在该website的配置
		if (websiteCrawlerDao.existWebsiteCrawler(
				websiteCrawlerDO.getWebsite(),
				websiteCrawlerDO.getWebsiteType())) {
			// website站点已存在
			return;
		}
		// *) 添加到系统里面去
		websiteCrawlerDao.insertWebsiteCrawler(websiteCrawlerDO);
	
	}

	@Override
	public List<WebsiteCrawlerDO> queryWebsiteCrawlers(int websiteType, int pageno, int pagesize) {
		return websiteCrawlerDao.queryWebsiteCrawlers(websiteType, pageno, pagesize);
	}

	@Override
	public WebsiteCrawlerDO applyWebsiteCrawler(int websiteType) {
		
		int pagesize = 10;
		int pageno = 0;
		int timestamp = TimeUtility.getDailyValue();
		
		List<WebsiteCrawlerDO>  websiteCrawlerDos = websiteCrawlerDao.queryWebsiteCrawlersWithNonTimestamp(timestamp, websiteType, pageno, pagesize);
		if ( websiteCrawlerDos.size() == 0 ) {
			return null;
		}
		for (  WebsiteCrawlerDO crawlerDO : websiteCrawlerDos ) {
			int res = websiteCrawlerDao
					.updateScheduleForWebsiteCrawler(
							crawlerDO.getWebsiteType(), crawlerDO.getWebid(),
							timestamp);
			if ( res == 1 ) {
				return crawlerDO;
			}
		}
		return null;
	
	}

	@Override
	public void updateWebsiteCrawler(WebsiteCrawlerDO websiteCrawlerDO) {
		websiteCrawlerDao.updateWebsiteCrawler(websiteCrawlerDO);
	}
	
	@Override
	public void removeWebsiteCrawler(int websiteType, int webid) {
		websiteCrawlerDao.removeWebsiteCrawler(websiteType, webid);
	}

	
}
