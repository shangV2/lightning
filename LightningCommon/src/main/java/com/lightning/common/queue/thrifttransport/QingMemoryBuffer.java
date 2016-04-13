package com.lightning.common.queue.thrifttransport;

import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class QingMemoryBuffer extends TTransport {

	private byte[] buffer = null;
	
	private int writepos = 0;
	
	private int readpos = 0;
	
	public QingMemoryBuffer(int size) {
		buffer = new byte[size];
		readpos = 0;
		writepos = 0;
	}
	
	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void open() throws TTransportException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public int read(byte[] buf, int off, int len) throws TTransportException {
		// TODO Auto-generated method stub
		if ( this.writepos - this.readpos >= len ) {
			System.arraycopy(this.buffer, this.readpos, buf, off, len);
			this.readpos += len;
			return len;
		} else if ( this.writepos - this.readpos < len && this.writepos >= this.readpos ) {
			int res = this.writepos - this.readpos;
			System.arraycopy(this.buffer, this.readpos, buf, off, this.writepos - this.readpos);
			this.readpos = this.writepos;
			return res;
		} 
		throw new TTransportException(".....");
//		return 0;
	}

	@Override
	public void write(byte[] buf, int off, int len) throws TTransportException {
		// TODO Auto-generated method stub
		if ( this.writepos + len > this.buffer.length ) {
			byte[] newBuffer = new byte[(this.writepos + len) * 2];
			System.arraycopy(this.buffer, 0, newBuffer, 0, this.writepos);
			this.buffer = newBuffer;
		}
		System.arraycopy(buf, off, this.buffer, this.writepos, len);
		this.writepos += len;
	}
	
	public void reset() {
		readpos = 0;
		writepos = 0;
	}
	
	public int length() {
		return writepos - readpos;
	}

	public byte[] getArray() {
		return buffer;
	}

}
