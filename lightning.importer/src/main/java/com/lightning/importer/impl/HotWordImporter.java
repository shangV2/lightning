//package com.lightning.importer.impl;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.PrintStream;
//import java.io.StringReader;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.TreeMap;
//import java.util.TreeSet;
//
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.TokenStream;
//import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
//import org.apache.thrift.TException;
//import org.apache.thrift.protocol.TBinaryProtocol;
//import org.apache.thrift.protocol.TProtocol;
//import org.apache.thrift.transport.TSocket;
//import org.apache.thrift.transport.TTransport;
//import org.apache.thrift.transport.TTransportException;
//import org.wltea.analyzer.lucene.IKAnalyzer;
//
//import com.google.protobuf.ByteString;
//import com.google.protobuf.InvalidProtocolBufferException;
//import com.lighting.rpc.common.exception.LightningServiceException;
//import com.lighting.rpc.datacenter.model.DCInsertHotWebPagesRequest;
//import com.lighting.rpc.datacenter.model.DCInsertHotWebPagesResponse;
//import com.lighting.rpc.datacenter.model.DCWebPage;
//import com.lighting.rpc.datacenter.service.DataCenterService;
//import com.lightning.common.filequeue.impl.FMQDecoder;
//import com.lightning.common.filequeue.impl.VariertyFileQueue;
//import com.lightning.common.kvstore.api.ISortedKeyValueStoreEngine;
//import com.lightning.common.kvstore.impl.LevelDBStoreEngine;
//import com.lightning.datacenter.importer.QFImporter;
//import com.lightning.datacenter.importer.SuperFileQueue;
//import com.lightning.datacenter.importer.SuperFileQueue.SuperSource;
//import com.lightning.datacenter.manager.HotWordManager;
//import com.lightning.datacenter.model.HotWordVO;
//import com.lightning.web.proto.WebPageProto.WebPageMessage;
//
//public class HotWordImporter implements QFImporter {
//
//	// 基于搜索引擎的判断
//	private Analyzer ikAnalyzer = new IKAnalyzer(true);
//	
//	public Set<String> split(String content) {
//		Set<String> sets = new TreeSet<String>();
//		TokenStream ts = null;
//		try {
//			ts = ikAnalyzer.tokenStream("", new StringReader(content));
//			ts.reset();
//			CharTermAttribute charTerm = ts.getAttribute(CharTermAttribute.class);
//			while ( ts.incrementToken() ) {
//				String word = charTerm.toString();
//				if ( !isFilter(word) ) {
//					sets.add(word);
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return sets;
//	}
//	
//	public boolean isFilter(String word) {
//		if ( word.length() <= 1 ) {
//			return true;
//		}
//		for ( int i = 0; i < word.length(); i++ ) {
//			char ch = word.charAt(i);
//			if ( ch >= '0' && ch <= '9' ) {
//				return true;
//			} else if ( ch == '.' ) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	class ValueNode implements Comparable<ValueNode> {
//		private String word;
//		private int val;
//		public ValueNode(String word, int val) {
//			this.word = word;
//			this.val = val;
//		}
//		@Override
//		public int compareTo(ValueNode arg0) {
//			return val - arg0.val;
//		}
//		public String getWord() {
//			return word;
//		}
//		public void setWord(String word) {
//			this.word = word;
//		}
//		public int getVal() {
//			return val;
//		}
//		public void setVal(int val) {
//			this.val = val;
//		}
//	}
//
//	@Override
//	public void handle(String timestamp, SuperFileQueue sfq) {
//		
//		
//		Map<String, Integer> wordFreqMap = new TreeMap<String, Integer>();
//		
//		SuperSource source = sfq.source();
//		while (source.hasNext()) {
//			WebPageMessage message = source.next();
//			if (timestamp.equalsIgnoreCase(message.getTimestamp())) {
//				Set<String> wordSets = split(message.getTitle().toStringUtf8() + " " + message.getContent().toStringUtf8());
//				for ( String word : wordSets ) {
//					if ( wordFreqMap.containsKey(word) ) {
//						wordFreqMap.put(word, wordFreqMap.get(word) + 1);
//					} else {
//						wordFreqMap.put(word, 1);
//					}
//				}
//			}
//		}
//		source.close();
//		
//		List<ValueNode> valueNodes = new ArrayList<ValueNode>();
//		for ( Map.Entry<String, Integer> entry : wordFreqMap.entrySet() ) {
//			valueNodes.add(new ValueNode(entry.getKey(), entry.getValue()));
//		}
//		Collections.sort(valueNodes);
//		Collections.reverse(valueNodes);
//		System.out.println("timestamp: " + timestamp);
//		for ( ValueNode vn : valueNodes) {
//			System.out.println(vn.getWord() + ":" + vn.getVal());
//		}
//		
//		// ----------------------------------------------------
//		
//		ISortedKeyValueStoreEngine storeEngine = new LevelDBStoreEngine();
//		try {
//			storeEngine.init();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		HotWordManager hotwordManager = new HotWordManager();
//		hotwordManager.storeKeyValueEngine = storeEngine;
//		
//		valueNodes = valueNodes.subList(0, Math.min(100, valueNodes.size()));
//		
//		List<String> wordLists = new ArrayList<String>();
//		List<HotWordVO> hotwords = new ArrayList<HotWordVO>();	
//		for ( ValueNode vn : valueNodes ) {
//			hotwords.add(new HotWordVO(vn.getWord(), vn.getVal()));
//			wordLists.add(vn.getWord());
//		}
//		hotwordManager.writeWordList(timestamp, wordLists);
//		hotwordManager.batchWriteWordFreqs(timestamp, hotwords);
//		
//		storeEngine.close();
//		
//	}
//	
//	public static void main(String[] args) {
//		DriverImporter driver = new DriverImporter();
//		driver.init();
//		driver.register(new HotWordImporter());
//		driver.drive();
//	}
//	
//}
