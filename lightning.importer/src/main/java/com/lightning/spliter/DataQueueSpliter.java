package com.lightning.spliter;

import java.io.File;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lightning.common.filequeue.impl.FMQDecoder;
import com.lightning.common.filequeue.impl.FMQEncoder;
import com.lightning.common.filequeue.impl.VariertyFileQueue;
import com.lightning.web.proto.WebPageProto.WebPageMessage;

public class DataQueueSpliter {

	private FMQDecoder<WebPageMessage> fmqDecoder = new FMQDecoder<WebPageMessage>() {
		@Override
		public WebPageMessage parse(byte[] buf, int off, int len) {
			try {
				return WebPageMessage.parseFrom(ByteString
						.copyFrom(buf, off, len));
			} catch (InvalidProtocolBufferException e) {
				e.printStackTrace();
			}
			return null;
		}
	};
	
	private FMQEncoder<WebPageMessage> fmqEncoder = new FMQEncoder<WebPageMessage>() {
		@Override
		public byte[] encode(WebPageMessage message) {
			return message.toByteArray();
		}
	};
	
	public DataQueueSpliter() {
	}

	public void execute() {
		
		String[] filenames = new String[] {
			"1_0_2014-09-11.fmq",
			"2_0_2014-09-11.fmq",
			"3_0_2014-09-11.fmq",
			"4_0_2014-09-11.fmq",
			"5_0_2014-09-11.fmq"
		};
		
		String destDir = "output\\";
		
		
		String path = "D:\\workspace\\Lightning\\Lightning-2014-0728\\Lightning\\lightning.crawler\\fqdata\\2014-09-11";
		for ( String filename : filenames ) {
			VariertyFileQueue vfq = new VariertyFileQueue(new File(path, filename).getAbsolutePath());
			VariertyFileQueue.Source<WebPageMessage> source = vfq.source(fmqDecoder);
			
			while ( source.hasNext() ) {
				WebPageMessage wpm = source.next();
				
//				String url = wpm.getUrl();
				String sour = wpm.getSource().toStringUtf8();
//				String title = wpm.getTitle().toStringUtf8();
				String timestamp = wpm.getTimestamp();
//				String content = wpm.getContent().toStringUtf8();
				
				String fqueue = timestamp + sour + ".fmq";
				fqueue = destDir + fqueue;
				VariertyFileQueue wvfq = new VariertyFileQueue(fqueue);
				VariertyFileQueue.Sink<WebPageMessage> sink = wvfq.sink(fmqEncoder);
				sink.write(wpm);
				sink.close();
			}
			
			source.close();
		}
		
	}
	
	
	
	public static void main(String[] args) {
		DataQueueSpliter spliter = new DataQueueSpliter();
		spliter.execute();
	}
	
}
