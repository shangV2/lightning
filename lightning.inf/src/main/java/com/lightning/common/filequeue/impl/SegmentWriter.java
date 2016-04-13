package com.lightning.common.filequeue.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class SegmentWriter {

	private String filename;
	
	private FileOutputStream fos = null;
	
	public SegmentWriter(String filename) {
		this.filename = filename;
	}
	
	public void init() {
		File file = new File(filename);
		if ( !file.exists() ) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			fos = new FileOutputStream(file, true);
			fos.getChannel().position(file.length());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	public int writeInt(int value) {
		byte[] buf = new byte[4];
		buf[0] = (byte)(0xff & (value >> 24));
		buf[1] = (byte)(0xff & (value >> 16));
		buf[2] = (byte)(0xff & (value >> 8));
		buf[3] = (byte)(0xff & value);
		return write(buf, 0, 4);
	}
	
	public int write(byte[] buf, int offset, int size) {
		try {
			return fos.getChannel().write(ByteBuffer.wrap(buf, offset, size));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int write(ByteBuffer buf) {
		try {
			return fos.getChannel().write(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public void close() {
		
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
