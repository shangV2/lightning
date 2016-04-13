package com.lightning.crawler.msgstore;

import java.util.Properties;

import com.lightning.crawler.persistent.IWebPageItemPersistent;

public interface IMessageStore {

	public void initialize(Properties props);
	
	public IWebPageItemPersistent createPersistent(MStoreConfig config);
	
	public void close();
	
}
