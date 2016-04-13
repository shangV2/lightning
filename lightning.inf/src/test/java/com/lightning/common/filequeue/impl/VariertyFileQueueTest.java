package com.lightning.common.filequeue.impl;

import org.junit.Test;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lightning.web.proto.WebPageProto.WebPageMessage;

public class VariertyFileQueueTest {

	
	@Test
	public void test() {
		
		VariertyFileQueue fq = new VariertyFileQueue("fqx/test1002");
		
		VariertyFileQueue.Sink<WebPageMessage> sink = fq.sink(new FMQEncoder<WebPageMessage>() {
			@Override
			public byte[] encode(WebPageMessage item) {
				return item.toByteArray();
			}
		});
		
		for ( int i = 0; i < 10; i++ ) {
			WebPageMessage.Builder builder = WebPageMessage.newBuilder();
			builder.setTimestamp("1988-10-12");
			builder.setSource(ByteString.copyFromUtf8("cnbeta"));
			builder.setContent(ByteString.copyFromUtf8("content " + i));
			builder.setTitle(ByteString.copyFromUtf8("chinese:" + i));
			builder.setUrl("www.baidu.com");
			
			WebPageMessage message = builder.build();
			sink.write(message);
		}
		sink.close();
		
		VariertyFileQueue.Source<WebPageMessage> source = fq.source(new FMQDecoder<WebPageMessage>() {
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
			if ( message != null ) {
				System.out.println(message.getTitle().toStringUtf8());
				System.out.println(message.getContent().toStringUtf8());
			}
			System.out.println("################################");
		}
		source.close();
	}

}