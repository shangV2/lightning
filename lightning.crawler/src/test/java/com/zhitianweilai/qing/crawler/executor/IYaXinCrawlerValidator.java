package com.zhitianweilai.qing.crawler.executor;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lightning.common.filequeue.impl.FMQDecoder;
import com.lightning.common.filequeue.impl.VariertyFileQueue;
import com.lightning.web.proto.WebPageProto.WebPageMessage;

public class IYaXinCrawlerValidator {

	
	public static void main(String[] args) {
		
		VariertyFileQueue vfq = new VariertyFileQueue("datafqx/chinaxinjiang_2014-03-16.fmq");
		VariertyFileQueue.Source<WebPageMessage> source = vfq.source(new FMQDecoder<WebPageMessage>() {
			@Override
			public WebPageMessage parse(byte[] buf, int off, int len) {
				try {
					return WebPageMessage.parseFrom(ByteString.copyFrom(buf, off, len));
				} catch (InvalidProtocolBufferException e) {
					e.printStackTrace();
				}
				return null;
			}
		});

		while ( source.hasNext() ) {
			WebPageMessage message = source.next();
			System.out.println(message.getTimestamp());
			System.out.println(message.getContent().toStringUtf8());
			System.out.println("--------------------------------------------------------------------");
		}
		
	}
	
}
