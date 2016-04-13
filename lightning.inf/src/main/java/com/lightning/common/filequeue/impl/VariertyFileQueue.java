package com.lightning.common.filequeue.impl;

import java.io.EOFException;
import java.io.IOException;

import com.lightning.common.filequeue.IFileQueue;

public class VariertyFileQueue implements IFileQueue {

	private String filename;

	public VariertyFileQueue(String filename) {
		this.filename = filename;
	}

	public <T> Source<T> source(FMQDecoder<T> decoder) {
		return new Source(filename, decoder);
	}
	
	public <T> Sink<T> sink(FMQEncoder<T> encoder) {
		return new Sink(filename, encoder);
	}
	
	public class Source<T> {
		
		private SegmentReader reader;
		private FMQDecoder<T> decoder;
		
		public Source(String filename, FMQDecoder<T> decoder) {
			reader = new SegmentReader(filename);
			reader.init();
			this.decoder = decoder;
		}
		
		public T next() {
			try {
				int datalen = reader.readInt();
				byte[] buf = new byte[datalen];
				reader.read(buf, 0, datalen);
				return decoder.parse(buf, 0, datalen);
			} catch (EOFException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public boolean hasNext() {
			try {
				return !reader.isEof();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		public void close() {
			reader.close();
		}
		
	}
	
	public class Sink<T> {
		
		private SegmentWriter writer;
		private FMQEncoder<T> encoder;
		
		public Sink(String filename, FMQEncoder<T> encoder) {
			writer = new SegmentWriter(filename);
			writer.init();
			this.encoder = encoder;
		}

		public void write(T item) {
//			ByteBuffer buf = ByteBuffer.allocate(1024);
			byte[] databuf = encoder.encode(item);
//			System.out.println("databuf is size : " + databuf.length);
			writer.writeInt(databuf.length);
			writer.write(databuf, 0, databuf.length);
		}
		
		public void write(byte[] databuf) {
			writer.writeInt(databuf.length);
			writer.write(databuf, 0, databuf.length);
		}
		
		public void close() {
			writer.close();
		}
		
	}
	
}
