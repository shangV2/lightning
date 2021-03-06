//package com.lightning.importer.impl;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//import java.util.PriorityQueue;
//import java.util.TreeMap;
//
//import javax.annotation.Resource;
//
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//
//import com.lighting.rpc.datacenter.model.DCCommonLanguge;
//import com.lightning.common.kvstore.api.ISortedKeyValueStoreEngine;
//import com.lightning.datacenter.importer.QFImporter;
//import com.lightning.datacenter.importer.SuperFileQueue;
//import com.lightning.datacenter.importer.SuperFileQueue.SuperSource;
//import com.lightning.datacenter.manager.ConsistentTopicManager;
//import com.lightning.datacenter.model.ConsistentTopicVO;
//import com.lightning.datacenter.model.ConsistentTopicWebPageVO;
//import com.lightning.datacenter.model.IMResult;
//import com.lightning.web.proto.WebPageProto.WebPageMessage;
//
//public class ConsistentTopicImporter implements QFImporter {
//	
//	@Resource 
//	private ConsistentTopicManager consistentTopicManager;
//	
//	@Resource 
//	public ISortedKeyValueStoreEngine sortedKeyValueStoreEngine;
//	
//	public void init() {
//		try {
//			sortedKeyValueStoreEngine.init();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	
//	public int matchScore(String title, String content, List<String> words) {
//
//		int score = 0;
//		int length = 1;
//		if ( words == null || words.size() == 0 ) {
//			return 0;
//		}
//		if ( title != null ) {
//			for ( String word : words ) {
//				int index = 0;
//				while ( (index = title.indexOf(word, index)) != -1 ) {
//					score += 10;
//					index = index + word.length();
//				}
//			}
//			length += title.length() / 2 ;
//		}
//		if ( content != null ) {
//			for ( String word : words ) {
//				int index = 0;
//				while ( (index = content.indexOf(word, index)) != -1 ) {
//					score += 2;
//					index = index + word.length();
//				}
//			}
//			length += content.length() / 2;
//		}
//		
//		if ( score * 100 / length == 0 ) {
//			return Math.min(score, 10);
//		}
//		
//		return score * 100 / length;
//		
//	}
//	
//	class ScoreArticle implements Comparable<ScoreArticle> {
//		
//		private WebPageMessage message;
//		
//		private int score;
//		
//		public ScoreArticle() {
//		}
//		
//		public ScoreArticle(WebPageMessage message, int score) {
//			this.message = message;
//			this.score = score;
//		}
//		
//		public WebPageMessage getMessage() {
//			return message;
//		}
//
//		public void setMessage(WebPageMessage message) {
//			this.message = message;
//		}
//
//		public int getScore() {
//			return score;
//		}
//
//		public void setScore(int score) {
//			this.score = score;
//		}
//
//		@Override
//		public int compareTo(ScoreArticle o) {
//			return score - o.score;
//		}
//	} 
//	
//	
//	@Override
//	public void handle(String timestamp, SuperFileQueue sfq) {
//		// TODO Auto-generated method stub
//		
//		int type = DCCommonLanguge.DC_LAN_CHINESE_ZH_CN.getValue();
//		List<ConsistentTopicVO> topics = new ArrayList<ConsistentTopicVO>();
//		
//		for ( int i = 0; ; i++ ) {
//			IMResult<List<ConsistentTopicVO>> rc = consistentTopicManager.listConsistentTopics(type, i * 10, 10);
//			if ( rc.isSuccess() ) {
//				List<ConsistentTopicVO> topicVOs = rc.getValue();
//				if ( topicVOs.size() == 0 ) {
//					break;
//				}
//				topics.addAll(topicVOs);
//			} else {
//				break;
//			}
//		}
//		
//		// --------------------------------------------------------------------------------
////		String timestamp = "2014-02-06";
////		String[] filenames = new String[] {
////				"fqdata/tianshan_2014-02-06.fmq"
////		};
//		
//		Map<Long, PriorityQueue<ScoreArticle> > mmap = new TreeMap<Long, PriorityQueue<ScoreArticle>>();
//		
//		Map<String, Integer> keyValueMap = new TreeMap<String, Integer>();
//		
//		SuperSource source = sfq.source();
//		while ( source.hasNext() ) {
//			WebPageMessage webpage = source.next();
//			String wtimestamp = webpage.getTimestamp();
//			if ( wtimestamp == null || !wtimestamp.equalsIgnoreCase(timestamp) ) {
//				continue;
//			} 
//			
//			String url = webpage.getUrl();
//			String source2 = webpage.getSource().toStringUtf8();
//			String title = webpage.getTitle().toStringUtf8();
//			String timestamp2 = webpage.getTimestamp();
//			String content = webpage.getContent().toStringUtf8();
//			
//			for ( ConsistentTopicVO topicVO : topics ) {
//				String startDate = topicVO.getStartDate();
//				String endDate = topicVO.getEndDate();
//				int percent = topicVO.getPercent();
//				List<String> words = topicVO.getWords();
//				
//				// 进行得分的计算
//				int score = matchScore(title, content, words);
//				
//				System.out.println("score: " + score);
//				
//				// 进行得分的过滤
//				if ( score < percent ) {
//					continue;
//				}
//				
//				System.out.println("score: " + score + " : percent : " + percent);
//				
//				long topicId = topicVO.getTopicId();
//				
//				if ( !mmap.containsKey(topicId) ) {
//					mmap.put(topicId, new PriorityQueue<ScoreArticle>());
//				}
//				
//				PriorityQueue<ScoreArticle> pq = mmap.get(topicId);
//				if ( pq.size() < 10 ) {
//					pq.add(new ScoreArticle(webpage, score));
//				} else {
//					ScoreArticle sa = pq.peek();
//					if ( sa.score < score ) {
//						pq.poll();
//						pq.add(new ScoreArticle(webpage, score));
//					}
//				}
//				
//			}
//			
//		}
//		source.close();
//		
//		
//		// ------------------------------------
//		for ( Map.Entry<Long, PriorityQueue<ScoreArticle>> entry : mmap.entrySet() ) {
//			
//			long topicId = entry.getKey();
//			List<ConsistentTopicWebPageVO> webpageVOs = new ArrayList<ConsistentTopicWebPageVO>();
//			PriorityQueue<ScoreArticle> pq = entry.getValue();
//			while ( !pq.isEmpty() ) {
//				ScoreArticle sa = pq.peek();
//				pq.poll();
//				ConsistentTopicWebPageVO webpageVO = new ConsistentTopicWebPageVO();
//				webpageVO.setUrl(sa.getMessage().getUrl());
//				webpageVO.setSource(sa.getMessage().getSource().toStringUtf8());
//				webpageVO.setTitle(sa.getMessage().getTitle().toStringUtf8());
//				webpageVO.setTimestamp(sa.getMessage().getTimestamp());
//				webpageVO.setContent(sa.getMessage().getContent().toStringUtf8());
//				
//				System.out.println(sa.getMessage().getUrl());
//				System.out.println(sa.getMessage().getTitle().toStringUtf8());
//				System.out.println(sa.getScore());
//				webpageVOs.add(webpageVO);
//			}
//		
//			Collections.reverse(webpageVOs);
//			
//			consistentTopicManager.addConsistentTopicTrend(topicId, timestamp, webpageVOs.size());
//			consistentTopicManager.addTopicWebpages(topicId, timestamp, webpageVOs);
//			
//		}
//		
//	}
//	
//	
//	public static void main(String[] args) {
//	
//		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(
//				"importer_conf/sensitive_importer.xml");
//		
//		ConsistentTopicImporter importer = (ConsistentTopicImporter)applicationContext.getBean("consistentTopicImporter");
//		
//		importer.init();
////		
////		do {
////			String[] indexFiles = new String[] {
////					"datafqx/tianshan_2014-02-06.fmq",
////			};
////			
////			SuperFileQueue sfq = new SuperFileQueue(indexFiles);
////			importer.handle("2014-02-06", sfq);
////			
////		} while(false);
////		
//		
//		DriverImporter driver = new DriverImporter();
//		driver.register(importer);
//
//		driver.init();
//		driver.drive();
//	
//	}
//
//	
//}
