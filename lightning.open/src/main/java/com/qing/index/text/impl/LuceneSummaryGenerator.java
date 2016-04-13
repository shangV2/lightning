package com.qing.index.text.impl;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

import com.qing.index.text.ISummaryGenerator;

public class LuceneSummaryGenerator implements ISummaryGenerator {

	private Analyzer analyzer = null;
	
	private String field = null;
	
	public LuceneSummaryGenerator(Analyzer analyzer, String field) {
		this.analyzer = analyzer;
		this.field = field;
	}
	
	/*
	 * �����ı�ժҪ����ȡ
	 * 
	 * (non-Javadoc)
	 * @see com.qing.index.text.ISummaryGenerator#summary(java.lang.String, org.apache.lucene.search.Query, int)
	 */
	@Override
	public String summary(String article, Query query, int limit) {

		Highlighter highlighter = new Highlighter(
				new SimpleHTMLFormatter("<em>","</em>"), 
				new QueryScorer(query)
			);
		
		highlighter.setTextFragmenter(new SimpleFragmenter(limit));
		
		String result = null;
		try {
			result = highlighter.getBestFragment(analyzer, field, article);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidTokenOffsetsException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

}
