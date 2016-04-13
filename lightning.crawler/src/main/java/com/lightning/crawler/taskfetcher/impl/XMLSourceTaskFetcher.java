package com.lightning.crawler.taskfetcher.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.BeanUtils;

import com.lightning.crawler.taskfetcher.ITaskFetcher;
import com.zhitianweilai.qing.crawler.task.DailyWebCrawlerTask;
import com.zhitianweilai.qing.crawler.task.WebCrawlerTask;
import com.zhitianweilai.qing.utils.TimeHelper;

public class XMLSourceTaskFetcher implements ITaskFetcher {
	
	private List<WebCrawlerTask> webCrawlerTasks = new ArrayList<WebCrawlerTask>();
	
	private int indexer = 0;
	
	@Override
	public void initialize(Properties props) {
		
	}
	
	@Override
	public DailyWebCrawlerTask fetch() {
		if ( indexer + 1 >= webCrawlerTasks.size() ) {
			indexer = 0;
		}
		if ( indexer >= webCrawlerTasks.size()  ) {
			return null;
		}
		WebCrawlerTask task= webCrawlerTasks.get(indexer++);
		DailyWebCrawlerTask result = new DailyWebCrawlerTask();
		
		BeanUtils.copyProperties(task, result);
		result.setTimestamp(TimeHelper.currentDay());
		
		return result;
	}

	public List<WebCrawlerTask> getWebCrawlerTasks() {
		return webCrawlerTasks;
	}

	public void setWebCrawlerTasks(List<WebCrawlerTask> webCrawlerTasks) {
		this.webCrawlerTasks = webCrawlerTasks;
	}

}
