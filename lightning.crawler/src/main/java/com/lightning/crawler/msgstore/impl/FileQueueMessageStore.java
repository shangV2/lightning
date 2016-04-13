package com.lightning.crawler.msgstore.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import com.lightning.crawler.msgstore.IMessageStore;
import com.lightning.crawler.msgstore.MStoreConfig;
import com.lightning.crawler.persistent.IWebPageItemPersistent;
import com.lightning.crawler.persistent.impl.FileQueuePersistent;

public class FileQueueMessageStore implements IMessageStore {

	private Properties props = new Properties();
	private Map<String, String> propertiesMap = new TreeMap<String, String>();
	
	public void init() {
		props.put("filequeue_datapath", propertiesMap.get("filequeue_datapath"));
		this.initialize(props);
	}
	
	@Override
	public void initialize(Properties props) {
		this.props = props;
		String datapath = props.getProperty("filequeue_datapath");
		File fdatapath = new File(datapath);
		if ( !fdatapath.exists() ) {
			fdatapath.mkdirs();
		}
	}

	@Override
	public void close() {
		
	}

	@Override
	public IWebPageItemPersistent createPersistent(MStoreConfig config) {
		FileQueuePersistent fqp = new FileQueuePersistent();
		Properties prop = new Properties();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		File dir = new File(props.getProperty("filequeue_datapath"), sdf.format(new Date()));
		if ( !dir.exists() ) {
			dir.mkdirs();
		}
		
		// @modify at 2014/09/06
		prop.setProperty("filequeue_datapath", dir.getAbsolutePath());
		
//		prop.setProperty("filequeue_datapath", props.getProperty("filequeue_datapath"));
		
		String filename = String.format("%d_%d_%s.fmq", 
				config.getWebid(), config.getLanguageType(), config.getTimestamp());
		prop.setProperty("filequeue_filename", filename);
		
		fqp.initialize(prop);
		return fqp;
	}

	public Map<String, String> getPropertiesMap() {
		return propertiesMap;
	}

	public void setPropertiesMap(Map<String, String> propertiesMap) {
		this.propertiesMap = propertiesMap;
	}
	
}
