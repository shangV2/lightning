package com.lightning.crawler.taskfetcher;

import java.util.Properties;

import com.zhitianweilai.qing.crawler.task.DailyWebCrawlerTask;

public interface ITaskFetcher {

	public void initialize(Properties props);
	
	public DailyWebCrawlerTask fetch();
	
}
