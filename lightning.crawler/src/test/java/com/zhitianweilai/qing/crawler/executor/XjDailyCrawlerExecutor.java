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

public class XjDailyCrawlerExecutor {

	public static void main(String[] args) {

		WebCrawlerTask task = new WebCrawlerTask();

		task.setHostExpression("www.xjdaily.com.cn");
		task.setNavPageExpression("index\\.shtml$");
		task.setConPageExpression("/\\d+\\.shtml$");

		task.setLanguage(0);
		task.setWebsite("xjdaily");
		task.setSeedUrl("http://www.xjdaily.com.cn/");
		task.setPageMaxLimit(1000);

		task.setTitleExpression("<div id=\"ct_news\"><div class=\"section\"> <div class=\"main\"> <h1/> </div> </div></div>");
		task.setContentExpression("<div id=\"zoom\" class=\"news_zw\"> <p/> </div>");
		task.setTimeExpression("<span id=\"pubtime_baidu\" />");		

		// ============================================
		
		//
		CrawlerExecutor executor = new CrawlerExecutor();

		VariertyFileQueue vfq = new VariertyFileQueue("fqdata/xjdaily_2014-09-04-all.fmq");
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
