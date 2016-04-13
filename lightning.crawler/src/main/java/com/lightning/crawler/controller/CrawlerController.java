package com.lightning.crawler.controller;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lightning.crawler.executor.DailyRollCrawlerExecutor;
import com.lightning.crawler.msgstore.IMessageStore;
import com.lightning.crawler.msgstore.MStoreConfig;
import com.lightning.crawler.taskfetcher.ITaskFetcher;
import com.lightning.crawler.utility.TimeUtility;
import com.zhitianweilai.qing.crawler.task.DailyWebCrawlerTask;


public class CrawlerController {

	private volatile boolean exitFlag = false;

	private ExecutorService workerPools = null;
	
	private Set<String> runTasksSet = Collections.synchronizedSet(new TreeSet<String>());
	
	private ITaskFetcher taskFetcher = null;
	
	private IMessageStore messageStore = null;
	
	public void initialize() {
		
		workerPools = Executors.newCachedThreadPool();
	}
	
	public void execute() {
		while ( !exitFlag ) {
			
			// 1). Fetch A WebCrawlerTask From MetaServer
			DailyWebCrawlerTask task = taskFetcher.fetch();
			if ( task != null ) {
				// 2). Execute A WebCrawlerTask
				String taskTimestampId = getTaskTimestampId(task);
				if (  !runTasksSet.contains(taskTimestampId) ) {
					runTasksSet.add(taskTimestampId);
					WebsiteTask webSiteTask = new WebsiteTask(task);
					webSiteTask.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
						@Override
						public void uncaughtException(Thread t, Throwable e) {
							WebsiteTask websiteTask = (WebsiteTask)t;
							websiteTask.cleanup();
						}
					});
					workerPools.submit(webSiteTask);
				}
			}
			TimeUtility.sleep(1000 * 10);
			
		}
	}

	public void close() {

		//*) messageStore exit safely......
		messageStore.close();
		
		// *) workerPools exit safely......
		workerPools.shutdown();
		while ( !workerPools.isTerminated() ) {
			try {
				workerPools.awaitTermination(1, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private class WebsiteTask extends Thread {
		
		private DailyWebCrawlerTask crawlerTask = null;

		public WebsiteTask(DailyWebCrawlerTask crawlerTask) {
			this.crawlerTask = crawlerTask;
		}

		@Override
		public void run() {
			DailyRollCrawlerExecutor executor = new DailyRollCrawlerExecutor();
			
			MStoreConfig config = new MStoreConfig();
			config.setWebid(crawlerTask.getWebid());
//			config.setLanguageType(crawlerTask.getLanguage());
			config.setTimestamp(crawlerTask.getTimestamp());
			
			executor.setPersistent(messageStore.createPersistent(config));
			executor.execute(crawlerTask);
			
			cleanup();
		}
		
		public void cleanup() {
			String taskTimestampId = getTaskTimestampId(crawlerTask);
			CrawlerController.this.runTasksSet.remove(taskTimestampId);
		}
		
	}

	public String getTaskTimestampId(DailyWebCrawlerTask task) {
		return task.getWebsite() + "_" + task.getTimestamp();
	}
	
	public ITaskFetcher getTaskFetcher() {
		return taskFetcher;
	}

	public void setTaskFetcher(ITaskFetcher taskFetcher) {
		this.taskFetcher = taskFetcher;
	}

	public IMessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(IMessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public static void main(String[] args) {
		
		DOMConfigurator.configure("conf/log/log4j.xml");
		
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"conf/application_context.xml");
		CrawlerController controller = (CrawlerController) applicationContext
				.getBean("appController");
		
		try {
			controller.initialize();
			controller.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
