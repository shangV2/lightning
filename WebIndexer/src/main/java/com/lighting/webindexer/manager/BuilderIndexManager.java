package com.lighting.webindexer.manager;

import org.wltea.analyzer.lucene.IKAnalyzer;

import com.qing.index.datasource.IIndexDataSource;
import com.qing.index.datasource.VariertyFileQueueDataSource;

public class BuilderIndexManager {

	
	public static void main(String[] args) {
		
		QingIndexManager qingManager = new QingIndexManager();
		qingManager.buildIndexEnv();
		qingManager.setAnalyzer(new IKAnalyzer(false));
		
		IIndexDataSource dataSource = new VariertyFileQueueDataSource("ready_index");
		qingManager.writeIndex(dataSource);
		System.out.println("first one done");
		qingManager.swapIndex();
		
		qingManager.writeIndex(dataSource);
		System.out.println("second one done");
	}
	
}
