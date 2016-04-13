package com.lightning.importer.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.lighting.rpc.common.exception.LightningServiceException;
import com.lighting.rpc.datacenter.model.DCAddHotWebEventsRequest;
import com.lighting.rpc.datacenter.model.DCHotWebEvent;
import com.lighting.rpc.datacenter.model.DCInsertHotWebPagesRequest;
import com.lighting.rpc.datacenter.model.DCInsertHotWebPagesResponse;
import com.lighting.rpc.datacenter.model.DCWebPage;
import com.lighting.rpc.datacenter.service.DataCenterService;
import com.lightning.datacenter.importer.QFImporter;
import com.lightning.datacenter.importer.SuperFileQueue;
import com.lightning.datacenter.importer.SuperFileQueue.SuperSource;
import com.lightning.datacenter.manager.HotWebTrendManager;
import com.lightning.web.proto.WebPageProto.WebPageMessage;

public class HotWebImporter extends QFImporter {
	
	@Resource
	private HotWebTrendManager hotWebTrendManager;
	
	public static final int THRESHOLD_VALUE = 15; 
	
	class TitleScore implements Comparable<TitleScore> {
		private String title;
		private float score;
		private float pageRankScore;
		private int freqRankScore;
		
		public TitleScore(String title, float score) {
			this.title = title;
			this.score = score;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public float getScore() {
			return score;
		}
		public void setScore(float score) {
			this.score = score;
		}
		public float getPageRankScore() {
			return pageRankScore;
		}
		public void setPageRankScore(float pageRankScore) {
			this.pageRankScore = pageRankScore;
		}
		public int getFreqRankScore() {
			return freqRankScore;
		}
		public void setFreqRankScore(int freqRankScore) {
			this.freqRankScore = freqRankScore;
		}
		@Override
		public int compareTo(TitleScore o) {
			if ( score < o.score ) {
				return -1;
			} else if ( score == o.score ) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	class ScoreArticle implements Comparable<ScoreArticle> {
		
		private WebPageMessage message;
		
		private int score;
		
		public ScoreArticle() {
		}
		
		public ScoreArticle(WebPageMessage message, int score) {
			this.message = message;
			this.score = score;
		}
		
		public WebPageMessage getMessage() {
			return message;
		}

		public void setMessage(WebPageMessage message) {
			this.message = message;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

		@Override
		public int compareTo(ScoreArticle o) {
			return score - o.score;
		}
	} 
	
	public void doHotwebpage(String timestamp, String title, List<WebPageMessage> wpms, int score) {
	
		
		hotWebTrendManager.insertHotWebPages(eventid, webpages);
		
		
		TTransport transport = new TSocket("localhost", 8020);
		try {
			transport.open();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	
		TProtocol protocol = new TBinaryProtocol(transport);
		DataCenterService.Client client = new DataCenterService.Client(protocol);
	
		do {
	
			DCInsertHotWebPagesRequest request = new DCInsertHotWebPagesRequest();
			request.setLogid(10001L);
			request.setTimestamp(timestamp);
			request.setTitle(title);
			request.setWebPages(new ArrayList<DCWebPage>());
	
			for (WebPageMessage msg : wpms) {
				DCWebPage dcwebpage = new DCWebPage();
				dcwebpage.setUrl(msg.getUrl());
				dcwebpage.setSource(msg.getSource().toStringUtf8());
				dcwebpage.setTitle(msg.getTitle().toStringUtf8());
				dcwebpage.setTimestamp(msg.getTimestamp());
				dcwebpage.setContent(msg.getContent().toStringUtf8());
				request.getWebPages().add(dcwebpage);
			}
	
			DCInsertHotWebPagesResponse response = null;
			try {
				response = client
						.insertHotWebPages(request);
			} catch (LightningServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
	
			final String eventid = response.getEventid();
	
			DCAddHotWebEventsRequest request2 = new DCAddHotWebEventsRequest();
			request2.setLogid(10001L);
			request2.setTimestamp(timestamp);
			
			request2.setEvents(Collections.singletonList(new DCHotWebEvent(title, eventid, score)));
	
			try {
				client.addHotWebEvents(request2);
			} catch (LightningServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
	
		} while (false);
	
		if (transport != null) {
			transport.close();
		}
		
		
		
		

	}
	
	private int similarity(String title, String otitle) {
		int score = 0;
		for ( int i = 0; i < title.length(); i++ ) {
			char ch = title.charAt(i);
			for ( int j = 0; j < otitle.length(); j++ ) {
				char och = otitle.charAt(j);
				if ( ch == och ) {
					score += 2;
					break;
				}
			}
		}
		return score * 100 / (title.length() + otitle.length() + 1);
	}

	@Override
	public void handle(String timestamp, SuperFileQueue sfq) {
		// TODO Auto-generated method stub
		
		
		List<TitleScore> titleScoreLists = new ArrayList<TitleScore>();
		
		// 基于搜索引擎的判断
		Analyzer ikAnalyzer = new IKAnalyzer(true);

		RAMDirectory ramDirectory = new RAMDirectory();
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_45, ikAnalyzer);
		IndexWriter indexWriter = null;
		try {
			indexWriter = new IndexWriter(ramDirectory, config);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		SuperSource ss = sfq.source();
		while ( ss.hasNext() ) {
			WebPageMessage message = ss.next();
			System.out.println(message.getTitle().toStringUtf8());

			Document doc = new Document();
			doc.add(new TextField("title", message.getTitle().toStringUtf8(),
					Store.YES));
			try {
				indexWriter.addDocument(doc);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			titleScoreLists.add(new TitleScore(message.getTitle().toStringUtf8(), 0));
		}
		ss.close();
		
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
		IndexSearcher searcher = new IndexSearcher(reader);

		QueryParser parser = new QueryParser(Version.LUCENE_45, "title",
				ikAnalyzer);

		for ( TitleScore tscore : titleScoreLists ) {
			Query query = null;
			try {
				query = parser.parse(tscore.getTitle());
				TopDocs topDocs = searcher.search(query, 10);
	
				float fscore = 0.0f;
				ScoreDoc[] docs = topDocs.scoreDocs;
				for (int i = 0; i < docs.length; i++) {
					Document doc = searcher.doc(docs[i].doc);
					System.out.println(doc.get("title"));
					fscore += docs[i].score;
				}
				
				tscore.setScore(fscore);
				tscore.setFreqRankScore(topDocs.totalHits);
				
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// ====================================================
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Collections.sort(titleScoreLists);
		Collections.reverse(titleScoreLists);
		
		for ( TitleScore tscore : titleScoreLists ) {
			System.out.println(tscore.getTitle() + " : " + tscore.getScore());
		}
		
		List<TitleScore> answer = new ArrayList<TitleScore>();
		for ( TitleScore tscore : titleScoreLists ) {
			boolean flag = false;
			for ( TitleScore ascore : answer ) {
				int sscore = similarity(tscore.getTitle(), ascore.getTitle());
				if ( sscore > THRESHOLD_VALUE ) {
					flag = true;
					break;
				}
			}
			if ( flag == false ) {
				answer.add(tscore);
			}
		}
		
		System.out.println("--------------------------------------------------");
		for ( TitleScore ascore : answer ) {
			System.out.println(ascore.getTitle() + "\t:\t" + ascore.getScore());
		}
		
		answer = answer.subList(0, Math.min(10, answer.size()));
		
		Map<Integer, PriorityQueue<ScoreArticle>> mmap = new TreeMap<Integer, PriorityQueue<ScoreArticle>>();
		// 重新一轮的遍历处理
		
		
		ss.reset();
		while (ss.hasNext()) {
			WebPageMessage message = ss.next();
			for ( int i = 0; i < answer.size(); i++ ) {
				TitleScore tscore = answer.get(i);
				int svalue = similarity(tscore.getTitle(), message.getTitle().toStringUtf8());
				if ( svalue > THRESHOLD_VALUE ) {
					if ( !mmap.containsKey(i) ) {
						mmap.put(i, new PriorityQueue<ScoreArticle>());
					}
					
					PriorityQueue<ScoreArticle> pq = mmap.get(i);
					if ( pq.size() < 10 ) {
						pq.add(new ScoreArticle(message, svalue));
					} else {
						ScoreArticle sa = pq.peek();
						if ( sa.getScore() < svalue ) {
							pq.poll();
							pq.add(new ScoreArticle(message, svalue));
						}
						}	
				}
			}
		}
		ss.close();
		
		System.out.println("############################################");
		
		// -------------------------
		for ( Map.Entry<Integer, PriorityQueue<ScoreArticle>> entry : mmap.entrySet() ) {
			int idx = entry.getKey().intValue();
			TitleScore tscore = answer.get(idx);
			
			PriorityQueue<ScoreArticle> pq = entry.getValue();
			
			List<ScoreArticle> sas = new ArrayList<ScoreArticle>();
			while ( !pq.isEmpty() ) {
				ScoreArticle sa = pq.poll();
				sas.add(sa);
			}
			Collections.reverse(sas);
			
			System.out.println(tscore.getTitle());
			for ( ScoreArticle sa : sas ) {
				System.out.println(sa.getMessage().getUrl());
				System.out.println(sa.getMessage().getTitle().toStringUtf8());
			}
			
			List<WebPageMessage> wpms = new ArrayList<WebPageMessage>();
			for ( ScoreArticle sa : sas ) {
				wpms.add(sa.getMessage());
			}
			doHotwebpage(timestamp, tscore.getTitle(), wpms, tscore.freqRankScore);
			
			System.out.println("^^^^^^^^^^^^^^^^^^^^");
		}
		
		
		ramDirectory.close();
		
	}

	@Override
	public String getDataDirPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRecordFilePath() {
		// TODO Auto-generated method stub
		return null;
	}

//	public static void main(String[] args) {
//		DriverImporter driver = new DriverImporter();
//		driver.init();
//		driver.register(new HotWebImporter());
//		driver.drive();
//	}
	
//	public static void main(String[] args) {
//
//		do {
//			String[] indexFiles = new String[] {
//					"datafqx/tianshan_2014-02-06.fmq",
//			};
//			
//			HotWebImporter impoter = new HotWebImporter();
//			SuperFileQueue sfq = new SuperFileQueue(indexFiles);
//			impoter.handle("2014-02-06", sfq);
//			
//		} while(false);
//			
//	}

}


/*
 * 写个简易的算法：
 * 		高聚合，低耦合		
 * 
 */





