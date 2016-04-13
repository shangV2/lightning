package com.qing.index.text;

import org.apache.lucene.search.Query;

public interface ISummaryGenerator {

	public String summary(String article, Query query, int limit);
	
}
