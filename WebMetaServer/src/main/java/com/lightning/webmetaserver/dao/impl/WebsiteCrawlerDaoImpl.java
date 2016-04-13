package com.lightning.webmetaserver.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.lightning.webmetaserver.dao.WebsiteCrawlerDao;
import com.lightning.webmetaserver.dataobject.WebsiteCrawlerDO;

public class WebsiteCrawlerDaoImpl extends SqlMapClientDaoSupport implements
		WebsiteCrawlerDao {

	@Override
	public void insertWebsiteCrawler(WebsiteCrawlerDO websiteCrawler) {
		getSqlMapClientTemplate().insert("WebsiteCrawler.addWebsiteCrawler",
				websiteCrawler);
	}

//	@Override
//	public WebsiteCrawlerDO queryWebsiteCrawler(String website) {
//		return (WebsiteCrawlerDO) getSqlMapClientTemplate().queryForObject(
//				"WebsiteCrawler.queryWebsiteCrawler", website);
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WebsiteCrawlerDO> queryWebsiteCrawlers(int websiteType, int pageno, int pagesize) {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("websiteType", websiteType);
		params.put("start", pageno * pagesize);
		params.put("limit", pagesize);
		return (List<WebsiteCrawlerDO>)getSqlMapClientTemplate().queryForList("WebsiteCrawler.queryWebsiteCrawlers", params);
	}

	@Override
	public boolean existWebsiteCrawler(String website, int websiteType) {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("website", website);
		params.put("websiteType", websiteType);
		Object res = getSqlMapClientTemplate().queryForObject("WebsiteCrawler.existWebsiteCrawler", params);
		if ( res == null || !(res instanceof Integer) ) {
			return false;
		}
		return ((Integer)res).intValue() > 0;
	}
	
	@Override
	public void removeWebsiteCrawler(int websiteType, int webid) {
		// removeWebsiteCrawlerByWebid
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("webid", webid);
		params.put("websiteType", websiteType);
		getSqlMapClientTemplate().delete("WebsiteCrawler.removeWebsiteCrawlerByWebid", params);
	}

	@Override
	public void updateWebsiteCrawler(WebsiteCrawlerDO websiteCrawler) {
		
		Map<String, Object> params = new TreeMap<String, Object>();
		
		params.put("webid", websiteCrawler.getWebid());
		params.put("contentType", websiteCrawler.getContentType());
		params.put("contentRule", websiteCrawler.getContentRule());
		params.put("urlType", websiteCrawler.getUrlType());
		params.put("urlRule", websiteCrawler.getUrlRule());
		params.put("seeds", websiteCrawler.getSeeds());
		params.put("crawlerType", websiteCrawler.getCrawlerType());
		params.put("crawlerNum", websiteCrawler.getCrawlerNum());
//		params.put("crawlerSchedule", websiteCrawler.getCrawlerSchedule());
		params.put("website", websiteCrawler.getWebsite());
		params.put("websiteType", websiteCrawler.getWebsiteType());
		
		getSqlMapClientTemplate().update("WebsiteCrawler.updateWebsiteCrawler", params);
	}
	
	@Override
	public List<WebsiteCrawlerDO> queryWebsiteCrawlersWithNonTimestamp(
			int timestamp, int websiteType, int pageno, int pagesize) {
		// queryWebsiteCrawlersWithNonTimestamp
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("crawlerSchedule", timestamp);
		params.put("websiteType", websiteType);
		params.put("start", pageno);
		params.put("limit", pagesize);
		return (List<WebsiteCrawlerDO>)getSqlMapClientTemplate().queryForList("WebsiteCrawler.queryWebsiteCrawlersWithNonTimestamp", params);
	}

//	@Override
//	public int updateScheduleForWebsiteCrawlers(String website,
//			int websiteType, int timestamp) {
//		// updateScheduleForWebsiteCrawlers
//		Map<String, Object> params = new TreeMap<String, Object>();
//		params.put("website", website);
//		params.put("websiteType", websiteType);
//		params.put("crawlerSchedule", timestamp);
//		Integer res = (Integer)getSqlMapClientTemplate().update("WebsiteCrawler.updateScheduleForWebsiteCrawlers", params);
//		if ( res == null  ) {
//			return 0;
//		}
//		return res.intValue();
//	}
	
	@Override
	public int updateScheduleForWebsiteCrawler(int websiteType, int webid,
			int timestamp) {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("webid", webid);
		params.put("websiteType", websiteType);
		params.put("crawlerSchedule", timestamp);
		Integer res = (Integer)getSqlMapClientTemplate().update("WebsiteCrawler.updateScheduleForWebsiteCrawler", params);
		if ( res == null  ) {
			return 0;
		}
		return res.intValue();
	}
	
	@Override
	public void cleanup() {
		getSqlMapClientTemplate().delete("WebsiteCrawler.cleanupAllWebsiteCrawler");
	}



	// updateScheduleForWebsiteCrawlers
	
}
