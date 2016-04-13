package com.lightning.spliter;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lightning.common.filequeue.impl.FMQDecoder;
import com.lightning.common.filequeue.impl.FMQEncoder;
import com.lightning.common.filequeue.impl.VariertyFileQueue;
import com.lightning.web.proto.WebPageProto.WebPageMessage;

public class DataQueueValidator {

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
	
//	private FMQEncoder<WebPageMessage> fmqEncoder = new FMQEncoder<WebPageMessage>() {
//		@Override
//		public byte[] encode(WebPageMessage message) {
//			return message.toByteArray();
//		}
//	};
	
	public DataQueueValidator() {
	}

	public void execute() {
		
		String[] filenames = new String[] {
//			"D:\\test\\submit\\crawler-split\\data\\results\\tianshan2014-03-28.fmq",
			"datafqx/chinaxinjiang2014-04-13.fmq",
			"datafqx/tianshan2014-04-13.fmq"
		};
		
		
		for ( String filename : filenames ) {
			VariertyFileQueue vfq = new VariertyFileQueue(filename);
			VariertyFileQueue.Source<WebPageMessage> source = vfq.source(fmqDecoder);
			
			while ( source.hasNext() ) {
				WebPageMessage wpm = source.next();
				
				String url = wpm.getUrl();
				String sour = wpm.getSource().toStringUtf8();
				String title = wpm.getTitle().toStringUtf8();
				String timestamp = wpm.getTimestamp();
				String content = wpm.getContent().toStringUtf8();
				
				System.out.println("timestamp:" + timestamp);
				System.out.println("title:" + title);
				System.out.println("---------------------------");
				
			}
			
			source.close();
		}
		
	}
	
	
	
	public static void main(String[] args) {
		DataQueueValidator spliter = new DataQueueValidator();
		spliter.execute();
	}
	
}
