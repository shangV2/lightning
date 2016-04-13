package com.lightning.datacenter.manager;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.protobuf.ByteString;
import com.lightning.common.kvstore.api.ISortedKeyValueStoreEngine;
import com.lightning.common.kvstore.constants.KeyValueStoreError;
import com.lightning.common.kvstore.model.KVResult;
import com.lightning.common.kvstore.model.KeyValuePair;
import com.lightning.datacenter.model.ConsistentTopicTrendVO;
import com.lightning.datacenter.model.ConsistentTopicVO;
import com.lightning.datacenter.model.ConsistentTopicWebPageVO;
import com.lightning.datacenter.model.IMResult;
import com.lightning.datacenter.trend.proto.HotTrendData.BPHotWebPage;
import com.lightning.datacenter.trend.proto.HotTrendData.BPHotWebPageList;
import com.lightning.datacenter.utils.TimestampEnumeration;
import com.lightning.datacenter.word.dao.ConsistentTopicDao;
import com.lightning.datacenter.word.dao.ConsistentTopicTrendDao;
import com.lightning.datacenter.word.model.ConsistentTopicDO;
import com.lightning.datacenter.word.model.ConsistentTopicTrendDO;

public class ConsistentTopicManager {
	
	@Resource 
	public ISortedKeyValueStoreEngine sortedKeyValueStoreEngine;
	
	@Resource 
	public ConsistentTopicDao consistentTopicDao;
	
	@Resource 
	public ConsistentTopicTrendDao consistentTopicTrendDao;
	
	/**
	 * 
	 * @param topicVO
	 * @return
	 */
	public IMResult<Long> createConsistentTopic(ConsistentTopicVO topicVO) {
		IMResult<Long> result = new IMResult<Long>();
		result.setSuccess(false);
		try {
			long topicId = generateTopicId(topicVO.getTopicName(), topicVO.getType());
			// *)判断该话题是否已存在
			if ( !consistentTopicDao.existConsistentTopic(topicId) ) {
				ConsistentTopicDO consistentTopicDO = new ConsistentTopicDO();
				consistentTopicDO.setTopicId(topicId);
				consistentTopicDO.setTopicName(topicVO.getTopicName());
				consistentTopicDO.setType(topicVO.getType());
				String wordsJson = words2json(topicVO.getWords());
				consistentTopicDO.setWords(wordsJson);
				consistentTopicDO.setStartDate(topicVO.getStartDate());
				consistentTopicDO.setEndDate(topicVO.getEndDate());
				consistentTopicDO.setPercent(topicVO.getPercent());
				
				// *) 进行话题的添加
				consistentTopicDao.addConsistentTopic(consistentTopicDO);
			}
			result.setSuccess(true);
			result.setValue(topicId);
		} catch(Throwable e) {
			result.setSuccess(false);
		}
		return result;
		
	}
	
	/**
	 * 
	 * @param topicId
	 * @return
	 */
	public IMResult<Void> removeConsistentTopic(long topicId) {
		IMResult<Void> result = new IMResult<Void>();
		result.setSuccess(false);
		try {
			consistentTopicDao.removeConsistentTopic(topicId);
			result.setSuccess(true);
		} catch(Throwable e) {
			result.setSuccess(false);
		}
		return result;
	}
	
	public IMResult<ConsistentTopicVO> queryConsistentTopic(long topicId) {
		
		IMResult<ConsistentTopicVO> result = new IMResult<ConsistentTopicVO>();
		result.setSuccess(false);

		try {
			ConsistentTopicDO topicDO = consistentTopicDao.queryConsistentTopicByTopicId(topicId);
			if ( topicDO == null ) {
				result.setSuccess(false);
				return result;
			}
			
			ConsistentTopicVO topicVO = new ConsistentTopicVO();
			topicVO.setTopicId(topicDO.getTopicId());
			topicVO.setTopicName(topicDO.getTopicName());
			topicVO.setType(topicDO.getType());
			topicVO.setWords(json2words(topicDO.getWords()));
			topicVO.setStartDate(topicDO.getStartDate());
			topicVO.setEndDate(topicDO.getEndDate());
			topicVO.setPercent(topicDO.getPercent());
			
			result.setValue(topicVO);
			result.setSuccess(true);
		} catch(Throwable e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
		
	}
	
	/**
	 * 
	 * @param type
	 * @param offset
	 * @param limit
	 * @return
	 */
	public IMResult<List<ConsistentTopicVO>> listConsistentTopics(int type, int offset, int limit) {
		IMResult<List<ConsistentTopicVO>> result = new IMResult<List<ConsistentTopicVO>>();
		result.setValue(new ArrayList<ConsistentTopicVO>());
		result.setSuccess(false);
		
		try {
			List<ConsistentTopicDO> consistentTopicDOs = consistentTopicDao.listConsistentTopics(type, offset, limit);
			for ( ConsistentTopicDO topicDO : consistentTopicDOs ) {
				ConsistentTopicVO topicVO = new ConsistentTopicVO();
				topicVO.setTopicId(topicDO.getTopicId());
				topicVO.setTopicName(topicDO.getTopicName());
				topicVO.setType(topicDO.getType());
				List<String> words = json2words(topicDO.getWords());
				topicVO.setWords(words);
				topicVO.setStartDate(topicDO.getStartDate());
				topicVO.setEndDate(topicDO.getEndDate());
				topicVO.setPercent(topicDO.getPercent());
				
				result.getValue().add(topicVO);
			}
			result.setSuccess(true);
		} catch(Throwable e) {
			result.setSuccess(false);
		}
		
		return result;
		
	}
	
	public IMResult<Void> addConsistentTopicTrend(long topicId, String timestamp, int val) {
		
		IMResult<Void> result = new IMResult<Void>();
		result.setSuccess(false);
		
		try {
			
			ConsistentTopicTrendDO trendDO = new ConsistentTopicTrendDO();
			trendDO.setTimestamp(timestamp);
			trendDO.setTopicId(topicId);
			trendDO.setValue(val);
			consistentTopicTrendDao.addConsistentTrendTopic(trendDO);
			result.setSuccess(true);
		} catch (Throwable e) {
			result.setSuccess(false);
		}
		
		return result;
		
	}
	
	/**
	 * 
	 * @param topicId
	 * @return
	 */
	public IMResult<List<ConsistentTopicTrendVO>> queryConsistentTopicTrends(long topicId) {

		IMResult<List<ConsistentTopicTrendVO>> result = new IMResult<List<ConsistentTopicTrendVO>>();
		result.setSuccess(false);
		result.setValue(new ArrayList<ConsistentTopicTrendVO>());
		
		try {
			ConsistentTopicDO topicDO = consistentTopicDao.queryConsistentTopicByTopicId(topicId);
			if ( topicDO != null ) {
				List<ConsistentTopicTrendDO> trendDOs = consistentTopicTrendDao.queryConsistentTopicTrends(topicId, topicDO.getStartDate(), topicDO.getEndDate());
				
				// *) 转换工作
				List<ConsistentTopicTrendVO> trendVOs = new ArrayList<ConsistentTopicTrendVO>();
				for ( ConsistentTopicTrendDO trendDO : trendDOs ) {
					ConsistentTopicTrendVO trendVO = new ConsistentTopicTrendVO();
					trendVO.setTimestamp(trendDO.getTimestamp());
					trendVO.setTopicId(trendDO.getTopicId());
					trendVO.setValue(trendDO.getValue());
					trendVOs.add(trendVO);
				}
				Collections.sort(trendVOs);
				
				// *) 
				int index = 0;
				TimestampEnumeration timestampEnumeration = new TimestampEnumeration(topicDO.getStartDate(), topicDO.getEndDate());
				while ( timestampEnumeration.hasNext() ) {
					String curnow = timestampEnumeration.next();
					ConsistentTopicTrendVO vo = new ConsistentTopicTrendVO();
					vo.setTimestamp(curnow);
					vo.setTopicId(topicId);
					vo.setValue(0);
					while ( index < trendVOs.size() ) {
						ConsistentTopicTrendVO trend = trendVOs.get(index);
						int sign = curnow.compareTo(trend.getTimestamp());
						if ( sign < 0 ) {
							vo.setValue(0);
							break;
						} else if ( sign > 0 ) {
							index++;
						} else {
							vo.setValue(trend.getValue());
							index++;
							break;
						}
					}
					result.getValue().add(vo);
				}
				result.setSuccess(true);
			} else {
				// TODO, 表明该 topicId不存在
			}
		} catch (Throwable e) {
			result.setSuccess(false);
		}
		return result;
		
	}

	
	/**
	 * 
	 * @param topicId
	 * @param timestamp
	 * @param webpageVOs
	 * @return
	 */
	public IMResult<Void> addTopicWebpages(long topicId, String timestamp, List<ConsistentTopicWebPageVO> webpageVOs) {
		
		IMResult<Void> result = new IMResult<Void>();
		result.setSuccess(false);
		
		try {
			BPHotWebPageList.Builder builder = BPHotWebPageList.newBuilder();
			for ( ConsistentTopicWebPageVO webpageVO : webpageVOs ) {
				BPHotWebPage.Builder wbuilder = BPHotWebPage.newBuilder();
				wbuilder.setUrl(webpageVO.getUrl());
				wbuilder.setSource(ByteString.copyFromUtf8(webpageVO.getSource()));
				wbuilder.setTitle(ByteString.copyFromUtf8(webpageVO.getTitle()));
				wbuilder.setTimestamp(webpageVO.getTimestamp());
				wbuilder.setContent(ByteString.copyFromUtf8(webpageVO.getContent()));
				
				builder.addWebpages(wbuilder.build());
			}
			
			String key = String.format("dc:topic:%d:webpage:%s", topicId, timestamp);
			KVResult<Void> rc = sortedKeyValueStoreEngine.set(key, builder.build().toByteArray());
			if ( rc.isSuccess() ) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
			
		} catch(Throwable e) {
			result.setSuccess(false);
		}
		return result;
		
	}
	
	
	/**
	 * 
	 * @param topicId
	 * @param timestamp
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	public IMResult<List<ConsistentTopicWebPageVO>> queryTopicWebpageAtTime(long topicId, String timestamp, int pageno, int pagesize) {
		
		IMResult<List<ConsistentTopicWebPageVO>> result = new IMResult<List<ConsistentTopicWebPageVO>>();
		result.setSuccess(false);
		result.setValue(new ArrayList<ConsistentTopicWebPageVO>());
		
		try {
			String key = String.format("dc:topic:%d:webpage:%s", topicId, timestamp);
			KVResult<byte[]> rc = sortedKeyValueStoreEngine.get(key);
			if ( rc.isSuccess() ) {
				BPHotWebPageList bpWebpageList = BPHotWebPageList.parseFrom(rc.getValue());
				List<BPHotWebPage> bpWebpages = bpWebpageList.getWebpagesList();
				for ( int i = pageno * pagesize; i < (pageno + 1) * pagesize && i < bpWebpages.size(); i++ ) {
					BPHotWebPage webpage = bpWebpages.get(i);
					ConsistentTopicWebPageVO webpageVO = new ConsistentTopicWebPageVO();
					webpageVO.setUrl(webpage.getUrl());
					webpageVO.setSource(webpage.getSource().toStringUtf8());
					webpageVO.setTitle(webpage.getTitle().toStringUtf8());
					webpageVO.setTimestamp(webpage.getTimestamp());
					webpageVO.setContent(webpage.getContent().toStringUtf8());
					
					result.getValue().add(webpageVO);
				}
				result.setSuccess(true);
			} else if ( rc.getErrorCode() == KeyValueStoreError.StoreKeyNotExistError.getErrorCode() ) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch (Throwable e) {
			result.setSuccess(false);
		}
		
		return result;
		
	}
	
	/**
	 * 
	 * @param topicId
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	public IMResult<List<ConsistentTopicWebPageVO>> queryTopicWebpage(long topicId, int pageno, int pagesize) {
		
		IMResult<List<ConsistentTopicWebPageVO>> result = new IMResult<List<ConsistentTopicWebPageVO>>();
		result.setSuccess(false);
		result.setValue(new ArrayList<ConsistentTopicWebPageVO>());
		
		try {
			String key = String.format("dc:topic:%d:webpage", topicId);
			KVResult<List<KeyValuePair>> rc = sortedKeyValueStoreEngine.getRange(key, null, 1);
			if ( rc.isSuccess() ) {
				List<KeyValuePair> kvps = rc.getValue();
				if ( kvps.size() == 1 ) {
					KeyValuePair kvp = kvps.get(0);
					BPHotWebPageList bpWebpageList = BPHotWebPageList.parseFrom(kvp.getValue());
					List<BPHotWebPage> bpWebpages = bpWebpageList.getWebpagesList();
					for ( int i = pageno * pagesize; i < (pageno + 1) * pagesize && i < bpWebpages.size(); i++ ) {
						BPHotWebPage webpage = bpWebpages.get(i);
						ConsistentTopicWebPageVO webpageVO = new ConsistentTopicWebPageVO();
						webpageVO.setUrl(webpage.getUrl());
						webpageVO.setSource(webpage.getSource().toStringUtf8());
						webpageVO.setTitle(webpage.getTitle().toStringUtf8());
						webpageVO.setTimestamp(webpage.getTimestamp());
						webpageVO.setContent(webpage.getContent().toStringUtf8());
						
						result.getValue().add(webpageVO);
					}
				}
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch(Throwable e) {
			result.setSuccess(false);
		}
		
		return result;
		
	}
	
	// -------------------------------------------------------
	
	private long generateTopicId(String topicName, int type) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(String.format("%s:%d", topicName, type).getBytes("UTF-8"));
			byte[] buf = digest.digest();
			return ((buf[0] << 24) | (buf[1] << 16) | (buf[2] << 8) | buf[3]) & 0x7FFFFFFF;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return 0L;
//		UUID uuid = UUID.fromString(String.format("%s:%d", topicName, type));
//		long value = uuid.getLeastSignificantBits();
//		return value > 0 ? value : -value;
	}
	
	private String words2json(List<String> words) {
		Gson gson = new Gson();
		return gson.toJson(words);
	}
	
	private List<String> json2words(String jsontext) {
		Gson gson = new Gson();
//		return (List<String>)gson.fromJson(jsontext, ArrayList.class);
		return gson.fromJson(jsontext, new TypeToken<List<String>>() {}.getType());
	}  
	
}
