package com.lightning.common.kvstore.config;

public class BerkeleyStoreConfiguration {

	private String dbDirname;
	
	private String dbFilename;
	
	private long txnTimeout = 2000;
	
	private long lockTimeout = 2000;

	public String getDbDirname() {
		return dbDirname;
	}

	public void setDbDirname(String dbDirname) {
		this.dbDirname = dbDirname;
	}

	public String getDbFilename() {
		return dbFilename;
	}

	public void setDbFilename(String dbFilename) {
		this.dbFilename = dbFilename;
	}

	public long getTxnTimeout() {
		return txnTimeout;
	}

	public void setTxnTimeout(long txnTimeout) {
		this.txnTimeout = txnTimeout;
	}

	public long getLockTimeout() {
		return lockTimeout;
	}

	public void setLockTimeout(long lockTimeout) {
		this.lockTimeout = lockTimeout;
	}
	
}
