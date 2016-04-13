package com.lighting.webindexer.manager;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class RawIndexerForDateRangeQueryTest {

	public static void main(String[] args) {

		
		RAMDirectory ramDirectory = new RAMDirectory();
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_45,
				new StandardAnalyzer(Version.LUCENE_45));
		IndexWriter indexWriter = null;
		try {
			indexWriter = new IndexWriter(ramDirectory, config);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// IndexWriter writer=new IndexWriter(ramDirectory, new
		// StandardAnalyzer());
		
		do {
			Document doc = new Document();
			doc.add(new TextField("title", "title - 2014-01-24",
					Store.YES));
			doc.add(new StringField("timestamp", "2014-01-10", Store.YES));
			try {
				indexWriter.addDocument(doc);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (false);
		
			
		try {
			indexWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		System.out
				.println("####################################################");

		IndexReader reader = null;
		try {
			reader = DirectoryReader.open(ramDirectory);
		} catch (IOException e) {
			e.printStackTrace();
		}
		IndexSearcher indexSearcher = new IndexSearcher(reader);
		
		
		if (indexSearcher != null) {
			QueryParser parser = new QueryParser(Version.LUCENE_45, "title",
					new StandardAnalyzer(Version.LUCENE_45));
			try {
				Query query = parser.parse("title");
//				TopDocs results = indexSearcher.search(query, 100);

				Query rangeQuery = TermRangeQuery.newStringRange("timestamp", "2014-01-01", "2014-01-09", true, true);
 				Filter filter = new QueryWrapperFilter(rangeQuery);
 				TopDocs results = indexSearcher.search(query, filter, 100);
// 				TopDocs results = indexSearcher.search(rangeQuery, 100);
 				
				ScoreDoc[] score = results.scoreDocs;
				if (score.length == 0) {
					System.out.println("对不起，没有找到您要的结果。");
				} else {
					for (int i = 0; i < score.length; i++) {
						Document doc = indexSearcher.doc(score[i].doc);
						System.out.println("title ==> " + doc.get("title"));
						System.out.println("timestamp ==> " + doc.get("timestamp"));

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
	
}
