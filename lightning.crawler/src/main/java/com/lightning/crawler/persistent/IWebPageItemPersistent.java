package com.lightning.crawler.persistent;

import java.util.Properties;

import com.zhitianweilai.qing.crawler.executor.model.WebPageItem;

public interface IWebPageItemPersistent {

	public void initialize(Properties props);
	
	public void persist(WebPageItem item);
	
	public void cleanup();
	
}
