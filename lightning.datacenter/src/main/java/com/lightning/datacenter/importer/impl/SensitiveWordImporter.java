package com.lightning.datacenter.importer.impl;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lighting.rpc.datacenter.model.DCCommonLanguge;
import com.lightning.datacenter.importer.QFImporter;
import com.lightning.datacenter.importer.SuperFileQueue;
import com.lightning.datacenter.importer.SuperFileQueue.SuperSource;
import com.lightning.datacenter.manager.SensitiveWordManager;
import com.lightning.datacenter.model.IMResult;
import com.lightning.datacenter.model.SensitiveWordVO;
import com.lightning.web.proto.WebPageProto.WebPageMessage;

public class SensitiveWordImporter extends QFImporter {
	
	private static final Logger logger = LoggerFactory.getLogger("importer");
	
	private static final String DEFAULT_RECORD_FILENAME = "sensitive_record.rec";
	private static final String DEFAULT_RECORD_DIRECTORY = "data/record"; 
	
	private String datapath = null;
	private String recordPath = DEFAULT_RECORD_DIRECTORY;
	private String recordFilename = DEFAULT_RECORD_FILENAME;
	
	@Resource
	private SensitiveWordManager sensitiveWordManager;
	
	public void init() {
		
	}
	
	@Override
	public void handle(String timestamp, SuperFileQueue sfq) {
		
		logger.info(
				String.format("sensitive_importer be called, timestamp: %s, start...", timestamp)
			);
		
		int type = DCCommonLanguge.DC_LAN_CHINESE_ZH_CN.getValue();
		IMResult<List<SensitiveWordVO>> rc = sensitiveWordManager.queryAllSensitiveWord(type);
		
		if ( !rc.isSuccess() ) {
			logger.warn("sensitive_importer handle, however call queryAllSensitiveWord fail");
			return;
		} 
		
		List<SensitiveWordVO> wordVOs = rc.getValue();
//		for ( SensitiveWordVO wordVO : wordVOs ) {
//			System.out.println(wordVO.getWord());
//		}

		Map<String, Integer> keyValueMap = new TreeMap<String, Integer>();
		
		// for debug...
		logger.debug(String.format("sensitive word size: %d, word: %s", wordVOs.size(),
				wordVOs.size() > 0 ? wordVOs.get(0).getWord() : "null"));
		
		SuperSource ss = sfq.source();
		while ( ss.hasNext() ) {
			WebPageMessage webpage = ss.next();
			
			logger.debug(
					String.format("params { timestamp: %s, title: %s, content: %s", 
							webpage.getTimestamp(), 
							webpage.getTitle().toStringUtf8(),
							webpage.getContent().toStringUtf8())
						);
			
			String wtimestamp = webpage.getTimestamp();
			if ( wtimestamp == null || !wtimestamp.equalsIgnoreCase(timestamp) ) {
				continue;
			} 
			
			String title = webpage.getTitle().toStringUtf8();
			String content = webpage.getContent().toStringUtf8();
			for ( SensitiveWordVO wordVO : wordVOs ) {
				String word = wordVO.getWord();
				if ( title != null && title.contains(word) ) {
					if ( keyValueMap.containsKey(word) ) {
						keyValueMap.put(word, keyValueMap.get(word) + 1);
					} else {
						keyValueMap.put(word, 1);
					}
				} else if ( content != null && content.contains(wordVO.getWord()) ) {
					if ( keyValueMap.containsKey(word) ) {
						keyValueMap.put(word, keyValueMap.get(word) + 1);
					} else {
						keyValueMap.put(word, 1);
					}
				}
			}
		}
		ss.close();
		
		for ( Map.Entry<String, Integer> entry : keyValueMap.entrySet() ) {
			sensitiveWordManager.updateSensitiveTrend(entry.getKey(), type, timestamp, entry.getValue());
			
			logger.debug(String.format("da ge , women geng xin le: key: %s, value: %d", 
					entry.getKey(), entry.getValue()));
		}
	
		logger.info(
				String.format("sensitive_importer be called, timestamp: %s, game over, write %d success",
						timestamp, keyValueMap.size())
			);
		
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

	@Override
	public String getDataDirPath() {
		return datapath;
	}

	@Override
	public String getRecordFilePath() {
		return new File(recordPath, recordFilename).getAbsolutePath();
	}
	

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
	
}
