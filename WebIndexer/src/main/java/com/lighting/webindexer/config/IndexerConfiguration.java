package com.lighting.webindexer.config;

import java.io.Serializable;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.InitializingBean;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class IndexerConfiguration implements InitializingBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -652708374448544666L;

	public static final String INDEXER_ROOT_DIR_PATH = "index";
	
	private String indexDir = INDEXER_ROOT_DIR_PATH;
	
	private String analyzerName;
	
	private Analyzer analyzer;

	public String getIndexDir() {
		return indexDir;
	}

	public void setIndexDir(String indexDir) {
		this.indexDir = indexDir;
	}

	public Analyzer getAnalyzer() {
		return analyzer;
	}

	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

	public String getAnalyzerName() {
		return analyzerName;
	}

	public void setAnalyzerName(String analyzerName) {
		this.analyzerName = analyzerName;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if ( "smartcn".equalsIgnoreCase(analyzerName) ) {
			analyzer = new SmartChineseAnalyzer(Version.LUCENE_45);
		} else if ( "standard".equalsIgnoreCase(analyzerName) ) {
			analyzer = new StandardAnalyzer(Version.LUCENE_45);
		} else if ( "ikcn".equalsIgnoreCase(analyzerName) ) {
			analyzer = new IKAnalyzer(false);
		} else {
			analyzer = new StandardAnalyzer(Version.LUCENE_45);
		}
	}
	
}
