package com.lighting.webindexer.manager;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;
import org.junit.Test;

import com.qing.index.model.dataobject.WebPageDO;
import com.qing.index.model.dataobject.WebPageResultDO;
import com.qing.index.text.ISummaryGenerator;
import com.qing.index.text.impl.LuceneSummaryGenerator;

public class QingIndexManagerTest {

	@Test
	public void test1001() {

		QingIndexManager manager = new QingIndexManager();

		manager.init();
		manager.prepare();

		IndexSearcher indexSearcher = manager.getIndexSearcher();
		if (indexSearcher != null) {
			QueryParser parser = new QueryParser(Version.LUCENE_45, "content",
					manager.getAnalyzer());
			try {
				Query query = parser.parse("新疆");
//				TopDocs results = indexSearcher.search(query, 100);

				Query rangeQuery = TermRangeQuery.newStringRange("timestamp", "2014-01-01", "2014-01-20", true, true);
 				Filter filter = new QueryWrapperFilter(rangeQuery);
// 				TopDocs results = indexSearcher.search(query, filter, 100);
 				TopDocs results = indexSearcher.search(rangeQuery, 100);
 				
				ScoreDoc[] score = results.scoreDocs;
				if (score.length == 0) {
					System.out.println("对不起，没有找到您要的结果。");
				} else {
					for (int i = 0; i < score.length; i++) {
						Document doc = indexSearcher.doc(score[i].doc);
						System.out.println("url ==> " + doc.get("url"));
						System.out.println("title ==> " + doc.get("title"));
						System.out.println("timestamp ==> " + doc.get("timestamp"));

						ISummaryGenerator generator = new LuceneSummaryGenerator(
								manager.getAnalyzer(), "content");
						String summaryFragement = generator.summary(
								doc.get("content"), query, 100);
						System.out.println("内容摘要 ==> " + summaryFragement);

						System.out
								.println("----------------------------------");
					}
				}
			} catch (Exception e) {
			}
		} else {
			System.out.println("index searcher don't exist");
		}
	}
	
	@Test
	public void testQueryTimeRange() {
		
		QingIndexManager manager = new QingIndexManager();

		manager.init();
		manager.prepare();

		WebPageResultDO resultDO = manager.queryWebpagesWithTimeRange("新疆", "2014-01-10", "2014-08-21", 0, 10, 100);
		System.out.println(resultDO.getTotalNumber());
		for ( WebPageDO page : resultDO.getWebPages() ) {
			System.out.println(page.getTimestamp());
			System.out.println(page.getTitle());
		}
		
		
	}
	
}
