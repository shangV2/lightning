package com.qing.index.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.qing.index.datasource.SmsDataSource;
import com.qing.index.model.SmsInfo;
import com.qing.index.model.dataobject.SmsInfoDO;
import com.qing.index.model.dataobject.SmsInfoResultDO;
import com.qing.index.utils.FileIOHelper;

public class SmsQingIndexManager {

	private static final String DEFAULT_DETAIL_INDEX_DIR = "index";

	private static final String DEFAULT_CURRENT_FILENAME = "current.txt";
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	private Logger logger = Logger.getLogger(SmsQingIndexManager.class.getName());
	
	private String indexRootDir = "smsindex/";
	
	private volatile IndexSearcher indexSearcher = null;
	
	private volatile IndexReader indexReader = null;
	
	private Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_44);
	
	//  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	// *) ��ʼ������
	public void init() {
		
	}
	
	// *) ����ʼ���Ĺ���
	public void prepare() {
		
		// *) 
		buildIndexEnv();
		
		// *)
		loadIndexEnv();
		
	}
	
	public void buildIndexEnv() {
		
		// @brief
		
		File indexRootDirFile = new File(indexRootDir);
		if ( !indexRootDirFile.exists() ) {
			indexRootDirFile.mkdirs();
		}
		
		File index0DirFile = new File(indexRootDir, String.format("%s%d", DEFAULT_DETAIL_INDEX_DIR, 0));
		if ( !index0DirFile.exists() ) {
			index0DirFile.mkdirs();
		}
		
		File index1DirFile = new File(indexRootDir, String.format("%s%d", DEFAULT_DETAIL_INDEX_DIR, 1));
		if ( !index1DirFile.exists() ) {
			index1DirFile.mkdirs();
		}
		
		File currentFile = new File(indexRootDir, DEFAULT_CURRENT_FILENAME);
		if ( !currentFile.exists() ) {
			try {
				currentFile.createNewFile();
				FileIOHelper.writeContentToFile(currentFile.getAbsolutePath(), "0\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void loadIndexEnv() {
		
		int currentIndex = getCurrentIndex();
		String currentIndexDir = String.format("%s%d", DEFAULT_DETAIL_INDEX_DIR, currentIndex);
		File indexDir = new File(indexRootDir, currentIndexDir);
		
		try {
			indexReader = DirectoryReader.open(FSDirectory.open(indexDir));
			indexSearcher = new IndexSearcher(indexReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private int getCurrentIndex() {
		int currentIndex = 0;
		File currentFile = new File(indexRootDir, DEFAULT_CURRENT_FILENAME);
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileInputStream(currentFile));
			currentIndex = scanner.nextInt();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if ( scanner != null ) {
				scanner.close();
			}
		}
		return currentIndex;
	}
	
	private void writeCurrentIndex(int currentIndex) {
		File currentFile = new File(indexRootDir, DEFAULT_CURRENT_FILENAME);
		if ( !currentFile.exists() ) {
			try {
				currentFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileIOHelper.writeContentToFile(currentFile.getAbsolutePath(), "" + currentIndex + "\n");
	}
	
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	// @--------------start----------------
	
	@SuppressWarnings("deprecation")
	public void writeIndex(SmsDataSource dataSource) {
		
		int currentIndex = getCurrentIndex();
		if ( currentIndex == 0 ) {
			currentIndex = 1;
		} else {
			currentIndex = 0;
		}
		
		String currentIndexDir = String.format("%s%d", DEFAULT_DETAIL_INDEX_DIR, currentIndex);
		File indexDir = new File(indexRootDir, currentIndexDir);
		
		IndexWriter indexWriter = null;
		
		try {
			
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_44, analyzer);
			config.setOpenMode(OpenMode.CREATE);
			
			Directory directory = FSDirectory.open(indexDir);
			indexWriter = new IndexWriter(directory, config);
			
			Iterator<SmsInfo> iter = dataSource.iterator();
			while ( iter.hasNext() ) {
				SmsInfo sms = iter.next();
				Document doc = new Document();
				doc.add(new TextField("content", sms.getContent(), Store.YES));
				doc.add(new StringField("send_mobile", sms.getSendMobile(), Store.YES));
				doc.add(new StringField("recv_mobile", sms.getRecvMobile(), Store.YES));
				
//				doc.add(new StoredField("send_mobile", sms.getSendMobile()));
//				doc.add(new StoredField("recv_mobile", sms.getRecvMobile()));
				doc.add(new StoredField("timestamp", sms.getTimestamp()));
				doc.add(new StoredField("send_username", sms.getSendUsername()));
				doc.add(new StoredField("recv_username", sms.getRecvUsername()));
				try {
					indexWriter.addDocument(doc);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if ( indexWriter != null ) {
				try {
					indexWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	// @--------------end------------------
	
	// @
	public void swapDataIndexSearcher() {
		
		int currentIndex = getCurrentIndex();
		
		IndexSearcher oldIndexSearcher = indexSearcher;
		IndexReader oldIndexReader = indexReader;
		
		if ( currentIndex == 0 ) {
			currentIndex = 1;
		} else {
			currentIndex = 0;
		}
		
		String currentIndexDir = String.format("%s%d", DEFAULT_DETAIL_INDEX_DIR, currentIndex);
		File indexDir = new File(indexRootDir, currentIndexDir);
		
		try {
			indexReader = DirectoryReader.open(FSDirectory.open(indexDir));
			indexSearcher = new IndexSearcher(indexReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		writeCurrentIndex(currentIndex);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if ( oldIndexSearcher != null ) {
			try {
				oldIndexReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if ( oldIndexReader != null ) {
			try {
				oldIndexReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void execute() {
		
//		FileDataSource dataSource = new FileDataSource("D:/workspace/QingCrawler/data");
		SmsDataSource dataSource = new SmsDataSource("smsdata/sms.txt");
		writeIndex(dataSource);
		
	}
	
	public void server() {
		
	}
	
	public IndexSearcher getIndexSearcher() {
		return indexSearcher;
	}
	
	public Analyzer getAnalyzer() {
		return analyzer;
	}

	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}
	
	public SmsInfoResultDO query(String phone, List<String> words, int pageno, int pagesize) {
		
		SmsInfoResultDO resultDO = new SmsInfoResultDO();
		resultDO.setTotalNum(0);
		
		if ( indexSearcher != null ) {
			
			StringBuilder sb = new StringBuilder();
			for ( String word : words ) {
				sb.append(word).append(" ");
			}
			
			QueryParser parser = new QueryParser(Version.LUCENE_44, "content", getAnalyzer());
			try {
				Query query = parser.parse(sb.toString());
				BooleanQuery query2 = new BooleanQuery();
				query2.add(query, Occur.MUST);

				BooleanQuery query3 = new BooleanQuery();
				query3.add(new TermQuery(new Term("send_mobile", phone)), Occur.SHOULD);
				query3.add(new TermQuery(new Term("recv_mobile", phone)), Occur.SHOULD);
				
				query2.add(query3, Occur.MUST);
				TopDocs results = indexSearcher.search(query2, (pageno + 1) * pagesize);
				
				ScoreDoc[] score = results.scoreDocs;
				resultDO.setTotalNum(results.totalHits);
				if (score.length == 0) {
				} else {
					for (int i = pageno * pagesize; i < score.length && i < (pageno + 1) * pagesize; i++) {
						Document doc = indexSearcher.doc(score[i].doc);
						SmsInfoDO smsinfoDO = new SmsInfoDO();
						smsinfoDO.setDocid(score[i].doc);
						smsinfoDO.setContent(doc.get("content"));
						smsinfoDO.setRecvMobile(doc.get("recv_mobile"));
						smsinfoDO.setRecvUsername(doc.get("recv_username"));
						smsinfoDO.setSendMobile(doc.get("send_mobile"));
						smsinfoDO.setSendUsername(doc.get("send_username"));
						smsinfoDO.setTimestamp(doc.get("timestamp"));
						resultDO.getSmses().add(smsinfoDO);
					}
				}
				
			} catch (Exception e) {
			}
		}
		return resultDO;
		
	}
	
	public SmsInfoResultDO query(String statement, int pageno, int pagesize) {
		
		SmsInfoResultDO resultDO = new SmsInfoResultDO();
		resultDO.setTotalNum(0);
		
		if ( indexSearcher != null ) {
			
			QueryParser parser = new QueryParser(Version.LUCENE_44, "content", getAnalyzer());
			try {
				Query query = parser.parse(statement);
				TopDocs results = indexSearcher.search(query, (pageno + 1) * pagesize);
				
				ScoreDoc[] score = results.scoreDocs;
				resultDO.setTotalNum(results.totalHits);
				if (score.length == 0) {
				} else {
					for (int i = pageno * pagesize; i < score.length && i < (pageno + 1) * pagesize; i++) {
						Document doc = indexSearcher.doc(score[i].doc);
						SmsInfoDO smsinfoDO = new SmsInfoDO();
						smsinfoDO.setDocid(score[i].doc);
						smsinfoDO.setContent(doc.get("content"));
						smsinfoDO.setRecvMobile(doc.get("recv_mobile"));
						smsinfoDO.setRecvUsername(doc.get("recv_username"));
						smsinfoDO.setSendMobile(doc.get("send_mobile"));
						smsinfoDO.setSendUsername(doc.get("send_username"));
						smsinfoDO.setTimestamp(doc.get("timestamp"));
						resultDO.getSmses().add(smsinfoDO);
					}
				}
				
			} catch (Exception e) {
			}
		}
		return resultDO;
		
	}
	
	public SmsInfoDO querySmsInfoByDocid(long docid) {
		if ( indexSearcher == null ) {
			return null;
		}
		SmsInfoDO smsinfoDO = new SmsInfoDO();
		try {
			Document doc = indexSearcher.doc((int) docid);
			if ( doc != null ) {
				smsinfoDO.setDocid(docid);
				smsinfoDO.setContent(doc.get("content"));
				smsinfoDO.setRecvMobile(doc.get("recv_mobile"));
				smsinfoDO.setRecvUsername(doc.get("recv_username"));
				smsinfoDO.setSendMobile(doc.get("send_mobile"));
				smsinfoDO.setSendUsername(doc.get("send_username"));
				smsinfoDO.setTimestamp(doc.get("timestamp"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return smsinfoDO;
	}
	
	
	// --------------------------------------------------------

	public static void main(String[] args) {
		
		SmsQingIndexManager manager = new SmsQingIndexManager();
		
		manager.init();
		manager.prepare();
		
		manager.execute();
		manager.swapDataIndexSearcher();
		
		IndexSearcher indexSearcher = manager.getIndexSearcher();
		if ( indexSearcher != null ) {
			QueryParser parser = new QueryParser(Version.LUCENE_44, "content", manager.getAnalyzer());
			try {
				Query query = parser.parse("虚假");
				TopDocs results = indexSearcher.search(query, 10);
				ScoreDoc[] score = results.scoreDocs;
				if (score.length == 0) {
					System.out.println("");
				} else {
					for (int i = 0; i < score.length; i++) {
						Document doc = indexSearcher.doc(score[i].doc);
						System.out.println("content ==> " + doc.get("content"));
						System.out.println("send_mobile ==> " + doc.get("send_mobile"));
						System.out.println("recv_mobile ==> " + doc.get("recv_mobile"));
						System.out.println("send_username ==> " + doc.get("send_username"));
						System.out.println("recv_username ==> " + doc.get("recv_username"));
					}
				}
			} catch (Exception e) {
			}
		} else {
			System.out.println("index searcher don't exist");
		}
		
		
		if ( indexSearcher != null ) {
			QueryParser parser = new QueryParser(Version.LUCENE_44, "content", manager.getAnalyzer());
			try {
				Query query = parser.parse("安全");
				BooleanQuery query2 = new BooleanQuery();
				query2.add(query, Occur.MUST);

				BooleanQuery query3 = new BooleanQuery();
				query3.add(new TermQuery(new Term("send_mobile", "14212345678")), Occur.SHOULD);
				query3.add(new TermQuery(new Term("recv_mobile", "14212345678")), Occur.SHOULD);
				
				query2.add(query3, Occur.MUST);
				TopDocs results = indexSearcher.search(query2, 2);
				System.out.println(results.totalHits);
				
				ScoreDoc[] score = results.scoreDocs;
				if (score.length == 0) {
					System.out.println("");
				} else {
					for (int i = 0; i < score.length; i++) {
						Document doc = indexSearcher.doc(score[i].doc);
						System.out.println("content ==> " + doc.get("content"));
						System.out.println("send_mobile ==> " + doc.get("send_mobile"));
						System.out.println("recv_mobile ==> " + doc.get("recv_mobile"));
						System.out.println("send_username ==> " + doc.get("send_username"));
						System.out.println("recv_username ==> " + doc.get("recv_username"));
					}
				}
			} catch (Exception e) {
			}
			
			System.out.println("#################################");
			if ( indexSearcher != null ) {
				
				
				
			}
			
		} else {
			System.out.println("index searcher don't exist");
		}
		
		
		
		
	}
	
}
