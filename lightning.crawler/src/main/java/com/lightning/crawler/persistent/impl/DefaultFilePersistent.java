package com.lightning.crawler.persistent.impl;

import java.util.Properties;

import com.lightning.crawler.persistent.IWebPageItemPersistent;
import com.zhitianweilai.qing.crawler.executor.model.WebPageItem;
import com.zhitianweilai.qing.utils.FileIOHelper;

public class DefaultFilePersistent implements IWebPageItemPersistent {

	@Override
	public void persist(WebPageItem item) {
		FileIOHelper.writeContentToFile("data/" + System.currentTimeMillis(), 
				item.getUrl() + "\n" + 
				item.getSource() + "\n" +
				item.getTitle() + "\n" + 
				item.getTimestamp() + "\n" +
				item.getContent());	
	}

	@Override
	public void initialize(Properties props) {
		
	}

	@Override
	public void cleanup() {
		
	}

}
