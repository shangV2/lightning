package com.lightning.datacenter.importer.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.lightning.datacenter.importer.QFImporter;
import com.lightning.datacenter.importer.SuperFileQueue;
import com.lightning.datacenter.importer.SuperFileQueue.SuperSource;
import com.lightning.datacenter.manager.HotWordManager;
import com.lightning.datacenter.model.HotWordVO;
import com.lightning.web.proto.WebPageProto.WebPageMessage;

public class HotwordImporter extends QFImporter {
	
	private static final String DEFAULT_RECORD_FILENAME = "hotword_record.rec";
	private static final String DEFAULT_RECORD_DIRECTORY = "data/record"; 
	
	private String datapath = null;
	private String recordPath = DEFAULT_RECORD_DIRECTORY;
	private String recordFilename = DEFAULT_RECORD_FILENAME;
	
	@Resource 
	private HotWordManager hotwordManager;
	
	// 基于搜索引擎的判断
	private Analyzer ikAnalyzer = new IKAnalyzer(true);
	
	public Set<String> split(String content) {
		Set<String> sets = new TreeSet<String>();
		TokenStream ts = null;
		try {
			ts = ikAnalyzer.tokenStream("", new StringReader(content));
			ts.reset();
			CharTermAttribute charTerm = ts.getAttribute(CharTermAttribute.class);
			while ( ts.incrementToken() ) {
				String word = charTerm.toString();
				if ( !isFilter(word) ) {
					sets.add(word);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sets;
	}
	
	public boolean isFilter(String word) {
		if ( word.length() <= 1 ) {
			return true;
		}
		for ( int i = 0; i < word.length(); i++ ) {
			char ch = word.charAt(i);
			if ( ch >= '0' && ch <= '9' ) {
				return true;
			} else if ( ch == '.' ) {
				return true;
			}
		}
		return false;
	}

	class ValueNode implements Comparable<ValueNode> {
		private String word;
		private int val;
		public ValueNode(String word, int val) {
			this.word = word;
			this.val = val;
		}
		@Override
		public int compareTo(ValueNode arg0) {
			return val - arg0.val;
		}
		public String getWord() {
			return word;
		}
		public void setWord(String word) {
			this.word = word;
		}
		public int getVal() {
			return val;
		}
		public void setVal(int val) {
			this.val = val;
		}
	}

	@Override
	public void handle(String timestamp, SuperFileQueue sfq) {
		
		Map<String, Integer> wordFreqMap = new TreeMap<String, Integer>();
		
		SuperSource source = sfq.source();
		while (source.hasNext()) {
			WebPageMessage message = source.next();
			if (timestamp.equalsIgnoreCase(message.getTimestamp())) {
				Set<String> wordSets = split(message.getTitle().toStringUtf8() + " " + message.getContent().toStringUtf8());
				for ( String word : wordSets ) {
					if ( wordFreqMap.containsKey(word) ) {
						wordFreqMap.put(word, wordFreqMap.get(word) + 1);
					} else {
						wordFreqMap.put(word, 1);
					}
				}
			}
		}
		source.close();
		
		List<ValueNode> valueNodes = new ArrayList<ValueNode>();
		for ( Map.Entry<String, Integer> entry : wordFreqMap.entrySet() ) {
			valueNodes.add(new ValueNode(entry.getKey(), entry.getValue()));
		}
		Collections.sort(valueNodes);
		Collections.reverse(valueNodes);
		
//		System.out.println("timestamp: " + timestamp);
//		for ( ValueNode vn : valueNodes) {
//			System.out.println(vn.getWord() + ":" + vn.getVal());
//		}
		
		// ----------------------------------------------------
		
		valueNodes = valueNodes.subList(0, Math.min(100, valueNodes.size()));
		
		List<String> wordLists = new ArrayList<String>();
		List<HotWordVO> hotwords = new ArrayList<HotWordVO>();	
		for ( ValueNode vn : valueNodes ) {
			hotwords.add(new HotWordVO(vn.getWord(), vn.getVal()));
			wordLists.add(vn.getWord());
		}
		hotwordManager.writeWordList(timestamp, wordLists);
		hotwordManager.batchWriteWordFreqs(timestamp, hotwords);
		
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
	
	
}
