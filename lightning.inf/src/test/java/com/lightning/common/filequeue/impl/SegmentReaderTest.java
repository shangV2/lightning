package com.lightning.common.filequeue.impl;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.google.protobuf.ByteString;
import com.lightning.web.proto.WebPageProto.WebPageMessage;

public class SegmentReaderTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testRead() {
		SegmentReader reader = new SegmentReader("fqx/mq1001");
		reader.init();

		try {
			byte[] datas = new byte[1024];
			while (!reader.isEof()) {
				int datalen = reader.readInt();
				reader.read(datas, 0, datalen);
				WebPageMessage message = WebPageMessage.parseFrom(ByteString
						.copyFrom(datas, 0, datalen));
				System.out.println(message);
				System.out.println(message.getTitle().toStringUtf8());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		reader.close();
		// writer.close();

	}

}
