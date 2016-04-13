package com.lightning.importer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lightning.common.filequeue.impl.FMQDecoder;
import com.lightning.common.filequeue.impl.VariertyFileQueue;
import com.lightning.web.proto.WebPageProto.WebPageMessage;

public class SuperFileQueue {

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

	private List<String> filenames = new ArrayList<String>(); 
	
	public SuperFileQueue(String[] filenames) {
		this.filenames = Arrays.asList(filenames);
	}
	
	public SuperFileQueue(List<String> filenames) {
		this.filenames = filenames;
	}
	
	public SuperSource source() {
		return new SuperSource();
	}
	
	public class SuperSource {
		
		private int index = 0;
		private VariertyFileQueue.Source<WebPageMessage> source = null;
		
		public boolean hasNext() {
			while ( source == null || source.hasNext() == false ) {
				if ( source != null ) {
					source.close();
				}
				if ( index >= filenames.size() ) {
					return false;
				}
				
				VariertyFileQueue vfq = new VariertyFileQueue(filenames.get(index));
				source = vfq.source(fmqDecoder);
				index++;
			}
			return source.hasNext();
		}
		
		public WebPageMessage next() {
			return source.next();
		}
		
		public void reset() {
			if ( source != null ) {
				source.close();
			}
			index = 0;
			source = null;
		}
		
		public void close() {
			reset();
		}
		
	}
	
}
