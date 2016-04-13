//package com.lightning.importer.impl;
//
//import java.util.List;
//import java.util.Map;
//import java.util.TreeMap;
//
//import javax.annotation.Resource;
//
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//
//import com.lighting.rpc.datacenter.model.DCCommonLanguge;
//import com.lightning.datacenter.importer.QFImporter;
//import com.lightning.datacenter.importer.SuperFileQueue;
//import com.lightning.datacenter.importer.SuperFileQueue.SuperSource;
//import com.lightning.datacenter.manager.SensitiveWordManager;
//import com.lightning.datacenter.model.IMResult;
//import com.lightning.datacenter.model.SensitiveWordVO;
//import com.lightning.web.proto.WebPageProto.WebPageMessage;
//
//public class SensitiveWordImporter implements QFImporter {
//	
//	@Resource 
//	private SensitiveWordManager sensitiveWordManager;
//	
//	public void init() {
//		
//	}
//	
//	@Override
//	public void handle(String timestamp, SuperFileQueue sfq) {
//		
//		int type = DCCommonLanguge.DC_LAN_CHINESE_ZH_CN.getValue();
//		IMResult<List<SensitiveWordVO>> rc = sensitiveWordManager.queryAllSensitiveWord(type);
//		
//		if ( !rc.isSuccess() ) {
//			return;
//		} 
//		
//		List<SensitiveWordVO> wordVOs = rc.getValue();
//		for ( SensitiveWordVO wordVO : wordVOs ) {
//			System.out.println(wordVO.getWord());
//		}
//
//		Map<String, Integer> keyValueMap = new TreeMap<String, Integer>();
//		
//		SuperSource ss = sfq.source();
//		while ( ss.hasNext() ) {
//			WebPageMessage webpage = ss.next();
//			String wtimestamp = webpage.getTimestamp();
//			if ( wtimestamp == null || !wtimestamp.equalsIgnoreCase(timestamp) ) {
//				continue;
//			} 
//			
//			String title = webpage.getTitle().toStringUtf8();
//			String content = webpage.getContent().toStringUtf8();
//			for ( SensitiveWordVO wordVO : wordVOs ) {
//				String word = wordVO.getWord();
//				if ( title != null && title.contains(word) ) {
//					if ( keyValueMap.containsKey(word) ) {
//						keyValueMap.put(word, keyValueMap.get(word) + 1);
//					} else {
//						keyValueMap.put(word, 1);
//					}
//				} else if ( content != null && content.contains(wordVO.getWord()) ) {
//					if ( keyValueMap.containsKey(word) ) {
//						keyValueMap.put(word, keyValueMap.get(word) + 1);
//					} else {
//						keyValueMap.put(word, 1);
//					}
//				}
//			}
//		}
//		ss.close();
//		
//		for ( Map.Entry<String, Integer> entry : keyValueMap.entrySet() ) {
//			sensitiveWordManager.updateSensitiveTrend(entry.getKey(), type, timestamp, entry.getValue());
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
//		SensitiveWordImporter importer = (SensitiveWordImporter)applicationContext.getBean("sensitiveWordImporter");
//		importer.init();
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
//
//	
//}
