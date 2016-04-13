package com.lightning.datacenter.importer.impl;


import java.io.File;
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
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.lightning.datacenter.importer.QFImporter;
import com.lightning.datacenter.importer.SuperFileQueue;
import com.lightning.datacenter.importer.SuperFileQueue.SuperSource;
import com.lightning.datacenter.manager.HotWebTrendManager;
import com.lightning.datacenter.model.HotWebEventVO;
import com.lightning.datacenter.model.HotWebPageVO;
import com.lightning.datacenter.model.IMResult;
import com.lightning.datacenter.utils.DataCenterEventUtility;
import com.lightning.web.proto.WebPageProto.WebPageMessage;

public class HotWebImporter extends QFImporter {
	
	
	private static final String DEFAULT_RECORD_FILENAME = "hotweb_record.rec";
	private static final String DEFAULT_RECORD_DIRECTORY = "data/record"; 
	
	private String datapath = null;
	private String recordPath = DEFAULT_RECORD_DIRECTORY;
	private String recordFilename = DEFAULT_RECORD_FILENAME;
	
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
	
		
		String eventid = DataCenterEventUtility.genEventid(timestamp, title);
		
		List<HotWebPageVO> hotWebPageVOs = new ArrayList<HotWebPageVO>();
		for (WebPageMessage msg : wpms) {
			HotWebPageVO webpageVO = new HotWebPageVO();
			webpageVO.setSource(msg.getSource().toStringUtf8());
			webpageVO.setUrl(msg.getUrl());
			webpageVO.setTitle(msg.getTitle().toStringUtf8());
			webpageVO.setTimestamp(msg.getTimestamp());
			webpageVO.setContent(msg.getContent().toStringUtf8());
			
			hotWebPageVOs.add(webpageVO);
		}
		hotWebTrendManager.insertHotWebPages(eventid, hotWebPageVOs);
		
		List<HotWebEventVO> eventVOs = new ArrayList<HotWebEventVO>(
			);
		eventVOs.add(new HotWebEventVO(title, eventid, score));
		
		IMResult<Integer> rc = hotWebTrendManager.addHotWebEvents(timestamp, eventVOs);
		if ( rc.isSuccess() ) {
		} else {
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

//		System.out
//				.println("####################################################");

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
		
//		System.out.println("--------------------------------------------------");
//		for ( TitleScore ascore : answer ) {
//			System.out.println(ascore.getTitle() + "\t:\t" + ascore.getScore());
//		}
		
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
		
//		System.out.println("############################################");
		
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
			
//			System.out.println(tscore.getTitle());
//			for ( ScoreArticle sa : sas ) {
//				System.out.println(sa.getMessage().getUrl());
//				System.out.println(sa.getMessage().getTitle().toStringUtf8());
//			}
			
			List<WebPageMessage> wpms = new ArrayList<WebPageMessage>();
			for ( ScoreArticle sa : sas ) {
				wpms.add(sa.getMessage());
			}
			doHotwebpage(timestamp, tscore.getTitle(), wpms, tscore.freqRankScore);
			
//			System.out.println("^^^^^^^^^^^^^^^^^^^^");
		}
		
		
		ramDirectory.close();
		
	}

	@Override
	public String getDataDirPath() {
		return datapath;
	}

	@Override
	public String getRecordFilePath() {
		return new File(recordPath, recordFilename).getAbsolutePath();
	}

	public String getDatapath() {
		return datapath;
	}

	public void setDatapath(String datapath) {
		this.datapath = datapath;
	}

	public String getRecordPath() {
		return recordPath;
	}

	public void setRecordPath(String recordPath) {
		this.recordPath = recordPath;
	}

	public String getRecordFilename() {
		return recordFilename;
	}

	public void setRecordFilename(String recordFilename) {
		this.recordFilename = recordFilename;
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





