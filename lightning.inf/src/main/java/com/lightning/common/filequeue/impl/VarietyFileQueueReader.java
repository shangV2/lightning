package com.lightning.common.filequeue.impl;

import java.io.File;
import java.io.FileFilter;

public class VarietyFileQueueReader {

	private String baseDir;
	
	private FMQDecoder decoder;
	
	public VarietyFileQueueReader(String baseDir) {
		this.baseDir = baseDir;
	}
	
	public void init() {
		File dir = new File(baseDir);
		File [] files = dir.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				if ( pathname.isFile() && !pathname.isHidden() ) {
					return true;
				}
				return false;
			}
		});
		// ##########################################
		
		
	}
	
	public boolean hasNext() {
		return false;
	}
	
	public <T> T next() {
		return (T)decoder.parse(null, 0, 0);
	}

	public FMQDecoder getDecoder() {
		return decoder;
	}

	public void setDecoder(FMQDecoder decoder) {
		this.decoder = decoder;
	}
	
}
