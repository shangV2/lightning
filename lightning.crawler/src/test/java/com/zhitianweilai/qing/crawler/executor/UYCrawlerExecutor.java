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

public class UYCrawlerExecutor {

	public static void main(String[] args) {

		WebCrawlerTask task = new WebCrawlerTask();

		task.setHostExpression("uy.ts.cn");
		task.setNavPageExpression("node_\\d+.htm$");
		task.setConPageExpression("content_\\d+.htm$");

		task.setLanguage(1);
		task.setWebsite("uyts");
		task.setSeedUrl("http://uy.ts.cn/");
		task.setPageMaxLimit(1000);

		task.setTitleExpression("<div id=\"content\"><div class=\"content\"><h2/></div></div>");
		task.setContentExpression("<div id=\"content_value\" ><p/></div>");
		task.setTimeExpression("<div class=\"times\" ><table><tr><td></td></tr></table></div>");		
		// ============================================
		
		//
		CrawlerExecutor executor = new CrawlerExecutor();

		VariertyFileQueue vfq = new VariertyFileQueue("fqdata/uy_2014-09-15-all.fmq");
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
