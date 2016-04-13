package com.lightning.common.filequeue.impl;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SegmentReader {

	
	private String filename;
	private long fileSize = 0;
	private FileInputStream fis = null;
	
	public SegmentReader(String filename) {
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
		fileSize = file.length();
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int readInt() throws EOFException, IOException {
		byte[] buf = new byte[4];
		if ( read(buf, 0, 4) != 4 ) {
			throw new EOFException();
		}
		return ((buf[0] << 24) & 0xFF000000) | ((buf[1] << 16) & 0xFF0000) | ((buf[2] << 8) & 0xFF00) | (buf[3] & 0xFF);
	}
	
	public int read(byte[] buf, int offset, int size) throws IOException {
		return fis.read(buf, offset, size);
	}
	
	public boolean isEof() throws IOException {
		return fileSize <= fis.getChannel().position();
	}
	
	public void close() {
		if ( fis != null ) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
 	
}
