package com.lighting.webindexer.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.lighting.webindexer.utility.TimeUtility;
import com.qing.index.datasource.IIndexDataSource;
import com.qing.index.handler.IWebPageHandler;
import com.qing.index.model.WebPage;
import com.qing.index.model.dataobject.WebPageDO;
import com.qing.index.model.dataobject.WebPageResultDO;
import com.qing.index.text.ISummaryGenerator;
import com.qing.index.text.impl.LuceneSummaryGenerator;
import com.qing.index.utils.FileIOHelper;

public class QingIndexManager {

	private static final String DEFAULT_DETAIL_INDEX_DIR = "index";

	private static final String DEFAULT_CURRENT_FILENAME = "current.txt";

	private static final Version CURRENT_LUCENE_VERSION = Version.LUCENE_45;
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//	private Logger logger = Logger.getLogger(QingIndexManager.class.getName());

	private String indexRootDir = "index/";

	private volatile IndexSearcher indexSearcher = null;

	private volatile IndexReader indexReader = null;

	private Analyzer analyzer = new SmartChineseAnalyzer(CURRENT_LUCENE_VERSION);

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// *) 初始化工作
	public void init() {

	}

	// *) 做初始化的工作
	public void prepare() {

		// *)
		buildIndexEnv();

		// *)
		loadIndexEnv();

	}

	public void buildIndexEnv() {

		// @brief

		// @保证主索引目录的存在
		File indexRootDirFile = new File(indexRootDir);
		if (!indexRootDirFile.exists()) {
			indexRootDirFile.mkdirs();
		}

		// @保证主索引目录下的0目录存在
		File index0DirFile = new File(indexRootDir, String.format("%s%d",
				DEFAULT_DETAIL_INDEX_DIR, 0));
		if (!index0DirFile.exists()) {
			index0DirFile.mkdirs();
		}

		// @保证主索引目录下的1目录存在
		File index1DirFile = new File(indexRootDir, String.format("%s%d",
				DEFAULT_DETAIL_INDEX_DIR, 1));
		if (!index1DirFile.exists()) {
			index1DirFile.mkdirs();
		}

		// @保证切换目录文件current.txt的存在
		File currentFile = new File(indexRootDir, DEFAULT_CURRENT_FILENAME);
		if (!currentFile.exists()) {
			try {
				currentFile.createNewFile();
				FileIOHelper.writeContentToFile(currentFile.getAbsolutePath(),
						"0\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void loadIndexEnv() {

		int currentIndex = getCurrentIndex();
		String currentIndexDir = String.format("%s%d",
				DEFAULT_DETAIL_INDEX_DIR, currentIndex);
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
			if (scanner != null) {
				scanner.close();
			}
		}
		return currentIndex;
	}

	private void writeCurrentIndex(int currentIndex) {
		// @保证切换目录文件current.txt的存在
		File currentFile = new File(indexRootDir, DEFAULT_CURRENT_FILENAME);
		if (!currentFile.exists()) {
			try {
				currentFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileIOHelper.writeContentToFile(currentFile.getAbsolutePath(), ""
				+ currentIndex + "\n");
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public void swapIndex() {
		int currentIndex = getCurrentIndex();
		if ( currentIndex == 0 ) {
			currentIndex = 1;
		} else {
			currentIndex = 0;
		}
		writeCurrentIndex(currentIndex);
	}
	
	public void writeIndex(IIndexDataSource dataSource) {

		int currentIndex = getCurrentIndex();
		if (currentIndex == 0) {
			currentIndex = 1;
		} else {
			currentIndex = 0;
		}

		String currentIndexDir = String.format("%s%d",
				DEFAULT_DETAIL_INDEX_DIR, currentIndex);
		File indexDir = new File(indexRootDir, currentIndexDir);

		IndexWriter indexWriter = null;

		try {

			IndexWriterConfig config = new IndexWriterConfig(CURRENT_LUCENE_VERSION,
					analyzer);
			config.setOpenMode(OpenMode.CREATE);

			Directory directory = FSDirectory.open(indexDir);
			final IndexWriter findexWriter = indexWriter = new IndexWriter(
					directory, config);

			dataSource.loadData(new IWebPageHandler() {
				@Override
				public void onHandleWebPage(WebPage page) {
					// TODO Auto-generated method stub
					String content = page.getContent();
					String url = page.getUrl();
					String title = page.getTitle();

					if (content == null || url == null || title == null) {
						return;
					}

					Document doc = new Document();
					doc.add(new TextField("title", page.getTitle(), Store.YES));
					doc.add(new TextField("content", page.getContent(),
							Store.YES));
					doc.add(new StoredField("url", page.getUrl()));
					doc.add(new StringField("timestamp", page.getTimestamp(), Store.YES));
					doc.add(new StoredField("source", page.getSource()));
					try {
						findexWriter.addDocument(doc);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (indexWriter != null) {
				try {
					indexWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// @
	public void swapDataIndexSearcher() {

		int currentIndex = getCurrentIndex();

		IndexSearcher oldIndexSearcher = indexSearcher;
		IndexReader oldIndexReader = indexReader;

		if (currentIndex == 0) {
			currentIndex = 1;
		} else {
			currentIndex = 0;
		}

		String currentIndexDir = String.format("%s%d",
				DEFAULT_DETAIL_INDEX_DIR, currentIndex);
		File indexDir = new File(indexRootDir, currentIndexDir);

		try {
			indexReader = DirectoryReader.open(FSDirectory.open(indexDir));
			indexSearcher = new IndexSearcher(indexReader);
		} catch (IOException e) {
			e.printStackTrace();
		}

		writeCurrentIndex(currentIndex);

		// 应该算是双buffer切换的实现思路
		// 这边腾出足够的时间，让对旧对象的引用，高概率的自然消亡

		// *) 等待buffer时间
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		TimeUtility.sleep(1000 * 10);

		// *) 索引对象的销毁
		if (oldIndexSearcher != null) {
			try {
				oldIndexReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// *) 索引读取者的销毁
		if (oldIndexReader != null) {
			try {
				oldIndexReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// 服务的启动
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

	public String getIndexRootDir() {
		return indexRootDir;
	}

	public void setIndexRootDir(String indexRootDir) {
		this.indexRootDir = indexRootDir;
	}
	
	// ==============================================================

	public WebPageResultDO query(String statement, int pageno, int pagesize) {

		WebPageResultDO resultDO = new WebPageResultDO();
		resultDO.setTotalNumber(0);
		
		if ( indexSearcher != null ) {
			int nsearch = (pageno + 1) * pagesize;
			QueryParser parser = new MultiFieldQueryParser(CURRENT_LUCENE_VERSION, new String[] {"content", "title"}, getAnalyzer());
			try {
				ISummaryGenerator generator = new LuceneSummaryGenerator(getAnalyzer(), "content");
				ISummaryGenerator generator2 = new LuceneSummaryGenerator(getAnalyzer(), "title");
				Query query = parser.parse(statement);
				TopDocs results = indexSearcher.search(query, nsearch);
				resultDO.setTotalNumber(results.totalHits);
				ScoreDoc[] score = results.scoreDocs;
				if (score.length == 0) {
				} else {
					for (int i = pageno * pagesize; i < nsearch && i < score.length; i++) {
						WebPageDO webPageDO = new WebPageDO();
						Document doc = indexSearcher.doc(score[i].doc);
						
						webPageDO.setDocid(score[i].doc);
						webPageDO.setUrl(doc.get("url"));
						
						String title = doc.get("title");
						if ( title != null ) {
							String titleFragement = generator2.summary(title, query, 50);
							webPageDO.setTitle((titleFragement != null) ? titleFragement : title);
						} else {
							webPageDO.setTitle("");
						}
						
						String content = doc.get("content");
						if ( content != null ) {
							content = content.replaceAll("\\?\\?\\?\\?", "");
							content = content.replaceAll("[\r\n]", " ");
							content = content.trim();
							String summaryFragement = generator.summary(content, query, 100);
							if ( summaryFragement != null ) {
								webPageDO.setSummary(summaryFragement);
							} else {
								webPageDO.setSummary(content.substring(0, Math.min(content.length(), 100)));
							}
						} else {
							webPageDO.setSummary("");
						}
						
						String timestamp = doc.get("timestamp");
						if ( timestamp != null ) {
							webPageDO.setTimestamp(timestamp);
						} else {
							webPageDO.setTimestamp("N/A");
						}	
						
						String source = doc.get("source");
						if ( source != null ) {
							webPageDO.setSource(source);
						} else {
							webPageDO.setSource("N/A");
						}
						
						resultDO.getWebPages().add(webPageDO);
					}
				}
			} catch (Exception e) {
			}
		} 
		return resultDO;
	}
	
	
	
	
	public WebPageResultDO queryWebpages(String statement, int pageno, int pagesize, int summaryLength) {
		WebPageResultDO resultDO = new WebPageResultDO();
		resultDO.setTotalNumber(0);
		
		if ( indexSearcher != null ) {
			int nsearch = (pageno + 1) * pagesize;
			QueryParser parser = new MultiFieldQueryParser(CURRENT_LUCENE_VERSION, new String[] {"content", "title"}, getAnalyzer());
			try {
				ISummaryGenerator generator = new LuceneSummaryGenerator(getAnalyzer(), "content");
				ISummaryGenerator generator2 = new LuceneSummaryGenerator(getAnalyzer(), "title");
				Query query = parser.parse(statement);
				TopDocs results = indexSearcher.search(query, nsearch);
				resultDO.setTotalNumber(results.totalHits);
				ScoreDoc[] score = results.scoreDocs;
				if (score.length == 0) {
				} else {
					for (int i = pageno * pagesize; i < nsearch && i < score.length; i++) {
						WebPageDO webPageDO = new WebPageDO();
						Document doc = indexSearcher.doc(score[i].doc);
						
						webPageDO.setDocid(score[i].doc);
						webPageDO.setUrl(doc.get("url"));
						
						String title = doc.get("title");
						if ( title != null ) {
							String titleFragement = generator2.summary(title, query, 50);
							webPageDO.setTitle((titleFragement != null) ? titleFragement : title);
						} else {
							webPageDO.setTitle("");
						}
						
						String content = doc.get("content");
						if ( content != null ) {
							content = content.replaceAll("\\?\\?\\?\\?", "");
							content = content.replaceAll("[\r\n]", " ");
							content = content.trim();
							String summaryFragement = generator.summary(content, query, summaryLength);
							if ( summaryFragement != null ) {
								webPageDO.setSummary(summaryFragement);
							} else {
								webPageDO.setSummary(content.substring(0, Math.min(content.length(), summaryLength)));
							}
						} else {
							webPageDO.setSummary("");
						}
						
						String timestamp = doc.get("timestamp");
						if ( timestamp != null ) {
							webPageDO.setTimestamp(timestamp);
						} else {
							webPageDO.setTimestamp("N/A");
						}	
						
						String source = doc.get("source");
						if ( source != null ) {
							webPageDO.setSource(source);
						} else {
							webPageDO.setSource("N/A");
						}
						
						resultDO.getWebPages().add(webPageDO);
					}
				}
			} catch (Exception e) {
			}
		} 
		return resultDO;
	}
	
	public WebPageResultDO queryWebpagesByTerm(String term, int pageno, int pagesize, int summaryLength) {
		Query termQuery1 = new TermQuery(new Term("content", term));
		Query termQuery2 = new TermQuery(new Term("title", term));
		
		BooleanQuery query = new BooleanQuery();
		query.add(termQuery1, Occur.SHOULD);
		query.add(termQuery2, Occur.SHOULD);
		
		return queryWebpages(query, pageno, pagesize, summaryLength);
	}
	
	public WebPageResultDO queryWebpages(Query query, int pageno, int pagesize, int summaryLength) {
		WebPageResultDO resultDO = new WebPageResultDO();
		resultDO.setTotalNumber(0);
		
		if ( indexSearcher != null ) {
			int nsearch = (pageno + 1) * pagesize;
			try {
				ISummaryGenerator generator = new LuceneSummaryGenerator(getAnalyzer(), "content");
				ISummaryGenerator generator2 = new LuceneSummaryGenerator(getAnalyzer(), "title");
				TopDocs results = indexSearcher.search(query, nsearch);
				resultDO.setTotalNumber(results.totalHits);
				ScoreDoc[] score = results.scoreDocs;
				if (score.length == 0) {
				} else {
					for (int i = pageno * pagesize; i < nsearch && i < score.length; i++) {
						WebPageDO webPageDO = new WebPageDO();
						Document doc = indexSearcher.doc(score[i].doc);
						
						webPageDO.setDocid(score[i].doc);
						webPageDO.setUrl(doc.get("url"));
						
						String title = doc.get("title");
						if ( title != null ) {
							String titleFragement = generator2.summary(title, query, 50);
							webPageDO.setTitle((titleFragement != null) ? titleFragement : title);
						} else {
							webPageDO.setTitle("");
						}
						
						String content = doc.get("content");
						if ( content != null ) {
							content = content.replaceAll("\\?\\?\\?\\?", "");
							content = content.replaceAll("[\r\n]", " ");
							content = content.trim();
							String summaryFragement = generator.summary(content, query, summaryLength);
							if ( summaryFragement != null ) {
								webPageDO.setSummary(summaryFragement);
							} else {
								webPageDO.setSummary(content.substring(0, Math.min(content.length(), summaryLength)));
							}
						} else {
							webPageDO.setSummary("");
						}
						
						String timestamp = doc.get("timestamp");
						if ( timestamp != null ) {
							webPageDO.setTimestamp(timestamp);
						} else {
							webPageDO.setTimestamp("N/A");
						}	
						
						String source = doc.get("source");
						if ( source != null ) {
							webPageDO.setSource(source);
						} else {
							webPageDO.setSource("N/A");
						}
						
						resultDO.getWebPages().add(webPageDO);
					}
				}
			} catch (Exception e) {
			}
		} 
		return resultDO;
	}

	
	public WebPageResultDO queryWebpagesWithTimeRange(String statement,
			String startDate, String endDate, int pageno, int pagesize,
			int summaryLength) {

		QueryParser parser = new MultiFieldQueryParser(CURRENT_LUCENE_VERSION, new String[] {"content", "title"}, getAnalyzer());
		try {
			Query query = parser.parse(statement);
			return this.queryWebpagesWithTimeRange(query, startDate, endDate, pageno, pagesize, summaryLength);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		WebPageResultDO resultDO = new WebPageResultDO();
		resultDO.setTotalNumber(0);
		return resultDO;
		
	}
	
	public WebPageResultDO queryWebpagesWithTimeRange(Query query, String startDate, String endDate, int pageno, int pagesize, int summaryLength) {
		WebPageResultDO resultDO = new WebPageResultDO();
		resultDO.setTotalNumber(0);
		
		if ( indexSearcher != null ) {
			int nsearch = (pageno + 1) * pagesize;
			try {
				ISummaryGenerator generator = new LuceneSummaryGenerator(getAnalyzer(), "content");
				ISummaryGenerator generator2 = new LuceneSummaryGenerator(getAnalyzer(), "title");
				
				Query rangeQuery = TermRangeQuery.newStringRange("timestamp", startDate, endDate, true, true);
 				Filter filter = new QueryWrapperFilter(rangeQuery);
				
				TopDocs results = indexSearcher.search(query, filter, nsearch);
				resultDO.setTotalNumber(results.totalHits);
				ScoreDoc[] score = results.scoreDocs;
				if (score.length == 0) {
				} else {
					for (int i = pageno * pagesize; i < nsearch && i < score.length; i++) {
						WebPageDO webPageDO = new WebPageDO();
						Document doc = indexSearcher.doc(score[i].doc);
						
						webPageDO.setDocid(score[i].doc);
						webPageDO.setUrl(doc.get("url"));
						
						String title = doc.get("title");
						if ( title != null ) {
							String titleFragement = generator2.summary(title, query, 50);
							webPageDO.setTitle((titleFragement != null) ? titleFragement : title);
						} else {
							webPageDO.setTitle("");
						}
						
						String content = doc.get("content");
						if ( content != null ) {
							content = content.replaceAll("\\?\\?\\?\\?", "");
							content = content.replaceAll("[\r\n]", " ");
							content = content.trim();
							String summaryFragement = generator.summary(content, query, summaryLength);
							if ( summaryFragement != null ) {
								webPageDO.setSummary(summaryFragement);
							} else {
								webPageDO.setSummary(content.substring(0, Math.min(content.length(), summaryLength)));
							}
						} else {
							webPageDO.setSummary("");
						}
						
						String timestamp = doc.get("timestamp");
						if ( timestamp != null ) {
							webPageDO.setTimestamp(timestamp);
						} else {
							webPageDO.setTimestamp("N/A");
						}	
						
						String source = doc.get("source");
						if ( source != null ) {
							webPageDO.setSource(source);
						} else {
							webPageDO.setSource("N/A");
						}
						
						resultDO.getWebPages().add(webPageDO);
					}
				}
			} catch (Exception e) {
			}
		} 
		return resultDO;
	}
	
	public WebPageResultDO queryWebpages(String statement, int pageno, int pagesize) {
		WebPageResultDO resultDO = new WebPageResultDO();
		resultDO.setTotalNumber(0);
		
		if ( indexSearcher != null ) {
			int nsearch = (pageno + 1) * pagesize;
			QueryParser parser = new MultiFieldQueryParser(CURRENT_LUCENE_VERSION, new String[] {"content", "title"}, getAnalyzer());
			try {
				Query query = parser.parse(statement);
				TopDocs results = indexSearcher.search(query, nsearch);
				resultDO.setTotalNumber(results.totalHits);
				ScoreDoc[] score = results.scoreDocs;
				if (score.length == 0) {
				} else {
					for (int i = pageno * pagesize; i < nsearch && i < score.length; i++) {
						WebPageDO webPageDO = new WebPageDO();
						Document doc = indexSearcher.doc(score[i].doc);
						
						webPageDO.setDocid(score[i].doc);
						webPageDO.setUrl(doc.get("url"));
						
						String title = doc.get("title");
						webPageDO.setTitle((title == null) ? "" : title);
						
						String content = doc.get("content");
						if ( content != null ) {
							content = content.replaceAll("\\?\\?\\?\\?", "");
							content = content.replaceAll("[\r\n]", " ");
							content = content.trim();
							webPageDO.setSummary(content);
						} else {
							webPageDO.setSummary("");
						}
						
						String timestamp = doc.get("timestamp");
						if ( timestamp != null ) {
							webPageDO.setTimestamp(timestamp);
						} else {
							webPageDO.setTimestamp("N/A");
						}	
						
						String source = doc.get("source");
						if ( source != null ) {
							webPageDO.setSource(source);
						} else {
							webPageDO.setSource("N/A");
						}
						
						resultDO.getWebPages().add(webPageDO);
					}
				}
			} catch (Exception e) {
			}
		} 
		return resultDO;
	}
	
	public WebPageDO queryWebPageByDocid(int docid) {
		if ( indexSearcher == null ) {
			return null;
		}
		
		try {
			Document doc = indexSearcher.doc((int) docid);
			if ( doc != null ) {
				WebPageDO webPageDO = new WebPageDO();
				webPageDO.setDocid(docid);
				webPageDO.setUrl(doc.get("url"));
				webPageDO.setTitle(doc.get("title"));
				webPageDO.setSummary(doc.get("content"));
				webPageDO.setTimestamp(doc.get("timestamp"));
				webPageDO.setSource(doc.get("source"));
				return webPageDO;
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// --------------------------------------------------------
	
}
