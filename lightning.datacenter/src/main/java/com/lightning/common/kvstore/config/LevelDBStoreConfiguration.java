package com.lightning.common.kvstore.config;

public class LevelDBStoreConfiguration {

	public static final String DEFAULT_LEVELDB_DIRNAME = "leveldb_dir";
	
	private String dirname = DEFAULT_LEVELDB_DIRNAME;

	
	
	public String getDirname() {
		return dirname;
	}

	public void setDirname(String dirname) {
		this.dirname = dirname;
	}
	
	
}
