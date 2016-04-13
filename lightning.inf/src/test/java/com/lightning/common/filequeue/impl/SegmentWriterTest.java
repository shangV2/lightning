package com.lightning.common.filequeue.impl;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.google.protobuf.ByteString;
import com.lightning.web.proto.WebPageProto;
import com.lightning.web.proto.WebPageProto.WebPageMessage;

public class SegmentWriterTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testInit() {
		
		SegmentWriter writer = new SegmentWriter("fqx/mq1001");
		writer.init();
		
		for ( int i = 0; i < 10; i++ ) {
			WebPageMessage.Builder builder = WebPageMessage.newBuilder();
			builder.setTimestamp("1988-10-12");
			builder.setSource(ByteString.copyFromUtf8("cnbeta"));
			builder.setContent(ByteString.copyFromUtf8("content " + i));
			builder.setTitle(ByteString.copyFromUtf8("中文:" + i));
			builder.setUrl("www.baidu.com");
			
			WebPageMessage message = builder.build();
			byte[] datas = message.toByteArray();
			writer.writeInt(datas.length);
			writer.write(datas, 0, datas.length);
		}
		
		writer.close();
		
	}

}
