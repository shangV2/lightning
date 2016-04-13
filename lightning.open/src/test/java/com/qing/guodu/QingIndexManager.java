package com.qing.guodu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.qing.index.text.ISummaryGenerator;
import com.qing.index.text.impl.LuceneSummaryGenerator;
import com.qing.index.utils.FileIOHelper;

public class QingIndexManager {

	private static final String DEFAULT_DETAIL_INDEX_DIR = "index";

	private static final String DEFAULT_CURRENT_FILENAME = "current.txt";

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	private Logger logger = Logger.getLogger(QingIndexManager.class.getName());

	private String indexRootDir = "index/";

	private volatile IndexSearcher indexSearcher = null;

	private volatile IndexReader indexReader = null;

	private Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_44);

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

	public void writeIndex(FileDataSource dataSource) {

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

			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_44,
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
					doc.add(new StoredField("timestamp", page.getTimestamp()));
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
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

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

	// 1. 读索引，同时构建检索项
	public void readIndex(String indexDirectory) {

		File indexDir = new File(indexDirectory);
		if (!indexDir.isDirectory()) {
			return;
		}

		IndexReader reader = null;
		try {

			Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_44);

			reader = DirectoryReader.open(FSDirectory.open(indexDir));
			IndexSearcher searcher = new IndexSearcher(reader);

			QueryParser parser = new QueryParser(Version.LUCENE_44, "content",
					analyzer);
			Query query = parser.parse("上海  知青");
			TopDocs results = searcher.search(query, 10);
			ScoreDoc[] score = results.scoreDocs;

			if (score.length == 0) {
				System.out.println("对不起，没有找到您要的结果。");
			} else {
				for (int i = 0; i < score.length; i++) {
					Document doc = searcher.doc(score[i].doc);
					System.out.println("url ==> " + doc.get("url"));
					System.out.println("title ==> " + doc.get("title"));
					System.out.println("内容 ==> " + doc.get("content"));

					System.out.println("----------------------------------");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	// 后台执行的任务
	public void execute() {

		FileDataSource dataSource = new FileDataSource(
				"D:/workspace/workspace/QingIndex/data");
		writeIndex(dataSource);

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

	// ==============================================================

	public WebPageResultDO query(String statement, int pageno, int pagesize) {

		WebPageResultDO resultDO = new WebPageResultDO();
		resultDO.setTotalNumber(0);
		
		if ( indexSearcher != null ) {
			int nsearch = (pageno + 1) * pagesize;
			QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_44, new String[] {"content", "title"}, getAnalyzer());
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
						
						resultDO.getWebPages().add(webPageDO);
					}
				}
			} catch (Exception e) {
			}
		} 
		return resultDO;
	}
	
	public WebPageDO queryWebPageByDocid(long docid) {
		if ( indexSearcher == null ) {
			return null;
		}
		WebPageDO webPageDO = new WebPageDO();
		try {
			Document doc = indexSearcher.doc((int) docid);
			if ( doc != null ) {
				webPageDO.setDocid(docid);
				webPageDO.setUrl(doc.get("url"));
				webPageDO.setTitle(doc.get("title"));
				webPageDO.setSummary(doc.get("content"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return webPageDO;
	}

	// --------------------------------------------------------

	public static void main(String[] args) {

		QingIndexManager manager = new QingIndexManager();

		// manager.writeIndex("data/indexA/");
		// manager.readIndex("data/indexA/");

		manager.init();
		manager.prepare();

		 manager.setAnalyzer(new StandardAnalyzer(Version.LUCENE_44));
		manager.execute();
		manager.swapDataIndexSearcher();

		IndexSearcher indexSearcher = manager.getIndexSearcher();
		if (indexSearcher != null) {
			QueryParser parser = new QueryParser(Version.LUCENE_44, "content",
					manager.getAnalyzer());
			try {
				Query query = parser.parse("་བརྡ་ཁྱབ་པར་རིས། ");
				TopDocs results = indexSearcher.search(query, 10);
				ScoreDoc[] score = results.scoreDocs;
				if (score.length == 0) {
					System.out.println("对不起，没有找到您要的结果。");
				} else {
					for (int i = 0; i < score.length; i++) {
						Document doc = indexSearcher.doc(score[i].doc);
						System.out.println("url ==> " + doc.get("url"));
						System.out.println("title ==> " + doc.get("title"));
						// System.out.println("内容 ==> " + doc.get("content"));

						ISummaryGenerator generator = new LuceneSummaryGenerator(
								manager.getAnalyzer(), "content");
						String summaryFragement = generator.summary(
								doc.get("content"), query, 100);
						System.out.println("内容摘要 ==> " + summaryFragement);
							
						System.out.println(doc.get("source") + ":" + doc.get("timestamp"));
						
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
