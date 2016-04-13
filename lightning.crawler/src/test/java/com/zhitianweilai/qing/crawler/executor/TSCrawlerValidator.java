package com.zhitianweilai.qing.crawler.executor;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lightning.common.filequeue.impl.FMQDecoder;
import com.lightning.common.filequeue.impl.VariertyFileQueue;
import com.lightning.web.proto.WebPageProto.WebPageMessage;

public class TSCrawlerValidator {

	
	public static void main(String[] args) {
		
//		VariertyFileQueue vfq = new VariertyFileQueue("datafqx/tianshan_2014-01-17.fmq");
		
		VariertyFileQueue vfq = new VariertyFileQueue("fqdata\\uy_2014-09-15-all.fmq");
		
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
			System.out.println(message.getTitle().toStringUtf8());
			System.out.println(message.getTimestamp());
			System.out.println(message.getContent().toStringUtf8());
			System.out.println(message.getUrl());
			System.out.println("--------------------------------------------------------------------");
		}
		
	}
	
}
