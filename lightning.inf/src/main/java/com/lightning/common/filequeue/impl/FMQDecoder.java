package com.lightning.common.filequeue.impl;

public interface FMQDecoder<T> {

	public T parse(byte[] buf, int off, int len);
	
}
