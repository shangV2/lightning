package com.lighting.webindexer.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lighting.datasource.reader.DailyDataSourceReader;
import com.lighting.datasource.reader.DailyDataSourceReaderFactory;
import com.lighting.webindexer.config.IndexerConfiguration;
import com.lighting.webindexer.utility.TimeUtility;

public class IndexTimerManager implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger("index");
	
	private QingIndexManager qingIndexManager = null;
	
	private DailyDataSourceReaderFactory readerFactory = null;
	
	private long interval = 60;
	
	@Override
	public void run() {
		do {
			// *)  等待 interval的秒数后, 进行索引构建
			TimeUtility.sleep(interval * 1000);
			
			// *) 创建数据源
			DailyDataSourceReader dataSource = readerFactory.createDataSourceReader();
			
			// *) 写索引数据
			qingIndexManager.writeIndex(dataSource);
			logger.info("write index [" + dataSource.getDatapath() + "] success");
			
			// *) 切换索引
			qingIndexManager.swapDataIndexSearcher();
			logger.info("swap index success");
		} while (true);
	}

	public QingIndexManager getQingIndexManager() {
		return qingIndexManager;
	}

	public void setQingIndexManager(QingIndexManager qingIndexManager) {
		this.qingIndexManager = qingIndexManager;
	}

	public DailyDataSourceReaderFactory getReaderFactory() {
		return readerFactory;
	}

	public void setReaderFactory(DailyDataSourceReaderFactory readerFactory) {
		this.readerFactory = readerFactory;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}
	
	
	
	public static void main(String[] args) {
		
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"conf/application_context.xml");
		
		DailyDataSourceReaderFactory dataSourceFactory = 
				(DailyDataSourceReaderFactory)applicationContext.getBean("dataSourceFactory");
		IndexerConfiguration config = (IndexerConfiguration)applicationContext.getBean("indexerConfiguration");
		
		QingIndexManager qingIndexManager = new QingIndexManager();
		qingIndexManager.setIndexRootDir(config.getIndexDir());
		qingIndexManager.setAnalyzer(config.getAnalyzer());
		
		qingIndexManager.init();
		qingIndexManager.prepare();
		
		IndexTimerManager timerManager = new IndexTimerManager();
		timerManager.setInterval(1);
		timerManager.setQingIndexManager(qingIndexManager);
		timerManager.setReaderFactory(dataSourceFactory);
		
		new Thread(timerManager).start();
		
	}
	
	
	
}
