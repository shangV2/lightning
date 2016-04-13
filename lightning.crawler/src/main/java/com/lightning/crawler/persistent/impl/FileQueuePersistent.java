package com.lightning.crawler.persistent.impl;

import java.io.File;
import java.util.Properties;

import com.google.protobuf.ByteString;
import com.lightning.common.filequeue.impl.FMQEncoder;
import com.lightning.common.filequeue.impl.VariertyFileQueue;
import com.lightning.crawler.persistent.IWebPageItemPersistent;
import com.lightning.web.proto.WebPageProto.WebPageMessage;
import com.zhitianweilai.qing.crawler.executor.model.WebPageItem;

public class FileQueuePersistent implements IWebPageItemPersistent {

	private VariertyFileQueue vfq = null;
	private VariertyFileQueue.Sink<WebPageMessage> sink = null;
	
	@Override
	public void initialize(Properties props) {
		String filequeuePath = props.getProperty("filequeue_datapath", "filequeue");
		String filename = props.getProperty("filequeue_filename", System.currentTimeMillis() + ".fmq");
		String filequeueFilename = new File(filequeuePath, filename).getAbsolutePath();
		vfq = new VariertyFileQueue(filequeueFilename);
		sink = vfq.sink(new FMQEncoder<WebPageMessage>() {
			@Override
			public byte[] encode(WebPageMessage message) {
				return message.toByteArray();
			}
		});
	}

	@Override
	public void persist(WebPageItem item) {
		WebPageMessage.Builder builder = WebPageMessage.newBuilder();
		builder.setUrl(item.getUrl());
		builder.setTimestamp(item.getTimestamp());
		builder.setSource(ByteString.copyFromUtf8(item.getSource()));
		builder.setTitle(ByteString.copyFromUtf8(item.getTitle()));
		builder.setContent(ByteString.copyFromUtf8(item.getContent()));
		sink.write(builder.build());
	}

	@Override
	public void cleanup() {
		sink.close();
	}
	
}
