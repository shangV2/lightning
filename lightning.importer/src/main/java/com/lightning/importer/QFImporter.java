package com.lightning.importer;

import com.lightning.datacenter.importer.SuperFileQueue;

public interface QFImporter {
	
//	public void init();
	
	public void handle(String timestamp, SuperFileQueue sfq);
	
}
