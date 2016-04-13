package com.lightning.common.filequeue.impl;

import java.nio.ByteBuffer;

public interface FMQEncoder<T> {

	public byte[] encode(T item);
	
}
