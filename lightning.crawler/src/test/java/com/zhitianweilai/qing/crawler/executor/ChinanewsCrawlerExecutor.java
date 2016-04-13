package com.zhitianweilai.qing.crawler.executor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.google.protobuf.ByteString;
import com.lightning.common.filequeue.impl.FMQEncoder;
import com.lightning.common.filequeue.impl.VariertyFileQueue;
import com.lightning.crawler.persistent.IWebPageItemPersistent;
import com.lightning.web.proto.WebPageProto.WebPageMessage;
import com.zhitianweilai.qing.crawler.executor.model.WebPageItem;
import com.zhitianweilai.qing.crawler.task.WebCrawlerTask;

public class ChinanewsCrawlerExecutor {

	public static void main(String[] args) {

		WebCrawlerTask task = new WebCrawlerTask();

		task.setHostExpression("www.xj.chinanews.com");
		task.setNavPageExpression("/L_\\d+\\.htm$");
		task.setConPageExpression("/\\d+/\\d+/\\d+/\\d+\\.htm$");

		task.setLanguage(0);
		task.setWebsite("xjcn");
		task.setSeedUrl("http://www.xj.chinanews.com/Index.htm");
		task.setPageMaxLimit(1000);

		task.setTitleExpression("<div class=\"article_title\"></div>");
		task.setContentExpression("<div class=\"article_content\"> <div id=\"MyContent\"> <p/> </div> </div>");
		task.setTimeExpression("<div class=\"article_author\" />");		

		// ============================================
		
		//
		CrawlerExecutor executor = new CrawlerExecutor();

		VariertyFileQueue vfq = new VariertyFileQueue("datafqx/tianshan_2014-02-18-all.fmq");
		final VariertyFileQueue.Sink<WebPageMessage> sink = vfq.sink(new FMQEncoder<WebPageMessage>() {
			@Override
			public byte[] encode(WebPageMessage message) {
				return message.toByteArray();
			}
		});
		executor.setPersistent(new IWebPageItemPersistent() {
			
			private String timestamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//			private String timestamp = "2014-01-17";
			
			@Override
			public void persist(WebPageItem item) {
//				timestamp = "2014-02-06";
//				if ( timestamp.equalsIgnoreCase(item.getTimestamp()) ) {
					WebPageMessage.Builder builder = WebPageMessage.newBuilder();
					builder.setUrl(item.getUrl());
					builder.setTimestamp(item.getTimestamp());
					builder.setSource(ByteString.copyFromUtf8(item.getSource()));
					builder.setTitle(ByteString.copyFromUtf8(item.getTitle()));
					builder.setContent(ByteString.copyFromUtf8(item.getContent()));
					sink.write(builder.build());
//				}
			}

			@Override
			public void initialize(Properties props) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void cleanup() {
				// TODO Auto-generated method stub
				
			}
		});
		
		executor.execute(task);
		sink.close();
		
	}
	
}
