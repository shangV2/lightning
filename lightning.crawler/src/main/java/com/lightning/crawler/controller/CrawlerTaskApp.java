package com.lightning.crawler.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.google.protobuf.ByteString;
import com.lightning.common.filequeue.impl.FMQEncoder;
import com.lightning.common.filequeue.impl.VariertyFileQueue;
import com.lightning.crawler.persistent.IWebPageItemPersistent;
import com.lightning.web.proto.WebPageProto.WebPageMessage;
import com.zhitianweilai.qing.crawler.executor.CrawlerExecutor;
import com.zhitianweilai.qing.crawler.executor.model.WebPageItem;
import com.zhitianweilai.qing.crawler.task.WebCrawlerTask;

public class CrawlerTaskApp {

	private List<WebCrawlerTask> webCrawlerTasks = new ArrayList<WebCrawlerTask>();
	
	private ExecutorService executorService = Executors.newCachedThreadPool();
	
	private String timestamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

	
	public void init() {
//		for ( WebCrawlerTask webCrawlerTask : webCrawlerTasks ) {
//			System.out.println(webCrawlerTask);
//		}
	}
	
	public void execute() {
		for ( WebCrawlerTask task : webCrawlerTasks ) {
			executorService.execute(new CrawlerTaskable(task));
		}
		executorService.shutdown();
		while ( !executorService.isTerminated() ) {
			try {
				executorService.awaitTermination(10, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<WebCrawlerTask> getWebCrawlerTasks() {
		return webCrawlerTasks;
	}

	public void setWebCrawlerTasks(List<WebCrawlerTask> webCrawlerTasks) {
		this.webCrawlerTasks = webCrawlerTasks;
	}

	class CrawlerTaskable implements Runnable {
		private WebCrawlerTask task;
		public CrawlerTaskable(WebCrawlerTask task) {
			this.task = task;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			String webfilename = task.getWebsite() + "_" + timestamp + ".fmq";
			
			CrawlerExecutor executor = new CrawlerExecutor();
			
			VariertyFileQueue vfq = new VariertyFileQueue("datafqx/" + webfilename);
			final VariertyFileQueue.Sink<WebPageMessage> sink = vfq.sink(new FMQEncoder<WebPageMessage>() {
				@Override
				public byte[] encode(WebPageMessage message) {
					return message.toByteArray();
				}
			});
			executor.setPersistent(new IWebPageItemPersistent() {
				@Override
				public void persist(WebPageItem item) {
//					if ( timestamp.equalsIgnoreCase(item.getTimestamp()) ) {
						WebPageMessage.Builder builder = WebPageMessage.newBuilder();
						builder.setUrl(item.getUrl());
						builder.setTimestamp(item.getTimestamp());
						builder.setSource(ByteString.copyFromUtf8(item.getSource()));
						builder.setTitle(ByteString.copyFromUtf8(item.getTitle()));
						builder.setContent(ByteString.copyFromUtf8(item.getContent()));
						sink.write(builder.build());
//					}
				}

				@Override
				public void initialize(Properties props) {
					
				}

				@Override
				public void cleanup() {
					
				}
			});
			
			executor.execute(task);
			sink.close();
			
		}
			
	}
	
	public static void main(String[] args) {
		
		// *) 
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"conf/application_context_tasks.xml");
		
		CrawlerTaskApp crawlerTaskApp = (CrawlerTaskApp) applicationContext.getBean("crawlerTaskApp");
		crawlerTaskApp.init();
		crawlerTaskApp.execute();
		
	}
	
}
