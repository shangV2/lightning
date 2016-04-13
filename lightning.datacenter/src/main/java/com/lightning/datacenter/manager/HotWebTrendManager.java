package com.lightning.datacenter.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lighting.rpc.datacenter.model.DCHotWebEvent;
import com.lighting.rpc.datacenter.model.DCWebPage;
import com.lightning.common.kvstore.api.ISortedKeyValueStoreEngine;
import com.lightning.common.kvstore.constants.KeyValueStoreError;
import com.lightning.common.kvstore.model.KVResult;
import com.lightning.datacenter.model.HotWebEventVO;
import com.lightning.datacenter.model.HotWebPageVO;
import com.lightning.datacenter.model.IMResult;
import com.lightning.datacenter.trend.proto.HotTrendData.BPHotWebEvent;
import com.lightning.datacenter.trend.proto.HotTrendData.BPHotWebEventList;
import com.lightning.datacenter.trend.proto.HotTrendData.BPHotWebPage;
import com.lightning.datacenter.trend.proto.HotTrendData.BPHotWebPageList;

public class HotWebTrendManager {

	private static final Logger logger = LoggerFactory.getLogger(HotWebTrendManager.class);
	
	@Resource 
	public ISortedKeyValueStoreEngine sortedKeyValueStoreEngine;
	
	/**
	 * 
	 * @param timestamp
	 * @param webEvents
	 * @return
	 */
	public IMResult<Integer> insertHotWebEvents(String timestamp, List<HotWebEventVO> webEvents) {
		
		IMResult<Integer> result = new IMResult<Integer>();
		result.setSuccess(false);
		
		String key = String.format("dc:hotweb:eventlist:%s", timestamp);
		
		BPHotWebEventList.Builder builder = BPHotWebEventList.newBuilder();
		builder.setTimestamp(timestamp);
		for ( HotWebEventVO event : webEvents ) {
			BPHotWebEvent.Builder ebuilder = BPHotWebEvent.newBuilder();
			ebuilder.setEventid(event.getEventid());
			ebuilder.setScore(event.getScore());
			ebuilder.setTitle(ByteString.copyFromUtf8(event.getTitle()));
			builder.addEvents(ebuilder.build());
		}
		
		KVResult<Void> rc2 = sortedKeyValueStoreEngine.set(key, builder.build().toByteArray());
		if ( rc2.isSuccess() ) {
			result.setValue(webEvents.size());
			result.setSuccess(true);
		} else {
			
		}
		
		return result;
		
	}
	
	/**
	 * 需要做进一步的修改
	 * @param timestamp
	 * @param webEvents
	 * @return
	 */
	public IMResult<Integer> addHotWebEvents(String timestamp, List<HotWebEventVO> webEvents) {
		
		// *) 进行排序处理
		Collections.sort(webEvents, new Comparator<HotWebEventVO>() {
			@Override
			public int compare(HotWebEventVO o1, HotWebEventVO o2) {
				if ( o1.getScore() > o2.getScore() ) {
					return -1;
				} else if ( o1.getScore() < o2.getScore() ) {
					return 1;
				}
				return 0;
			}
		});
		
		
		IMResult<Integer> result = new IMResult<Integer>();
		result.setSuccess(false);
		
		List<HotWebEventVO> resultEvents = new ArrayList<HotWebEventVO>();
		
		String key = String.format("dc:hotweb:eventlist:%s", timestamp);
		
		KVResult<byte[]> rc = sortedKeyValueStoreEngine.get(key);
		if ( rc.isSuccess() == true ) {
			byte[] datas = rc.getValue();
			BPHotWebEventList eventList = null;
			try {
				eventList = BPHotWebEventList.parseFrom(datas);
			} catch (InvalidProtocolBufferException e) {
				// TODO
				result.setSuccess(false);
				return result;
			}
			if ( eventList != null ) {
				for ( BPHotWebEvent event : eventList.getEventsList() ) {
					HotWebEventVO eventVO = new HotWebEventVO();
					eventVO.setEventid(event.getEventid());
					eventVO.setScore(event.getScore());
					eventVO.setTitle(event.getTitle().toStringUtf8());
					resultEvents.add(eventVO);
				}
			}
		} 
		
		// merge
		Set<String> eventSet = new TreeSet<String>();
		for ( HotWebEventVO event : resultEvents ) {
			eventSet.add(event.getEventid());
		}
		for ( HotWebEventVO event : webEvents ) {
			if ( eventSet.contains(event.getEventid()) ) {
				continue;
			}
			eventSet.add(event.getEventid());
			resultEvents.add(event);
		}
		
		
		BPHotWebEventList.Builder builder = BPHotWebEventList.newBuilder();
		builder.setTimestamp(timestamp);
		for ( HotWebEventVO event : resultEvents ) {
			BPHotWebEvent.Builder ebuilder = BPHotWebEvent.newBuilder();
			ebuilder.setEventid(event.getEventid());
			ebuilder.setScore(event.getScore());
			ebuilder.setTitle(ByteString.copyFromUtf8(event.getTitle()));
			builder.addEvents(ebuilder.build());
		}
		
		KVResult<Void> rc2 = sortedKeyValueStoreEngine.set(key, builder.build().toByteArray());
		if ( rc2.isSuccess() ) {
			result.setValue(webEvents.size());
			result.setSuccess(true);
		} else {
			
		}
		
		return result;
		
	}
	
	public IMResult<List<HotWebEventVO>> queryHowebEventList(String timestamp, int pageno, int pagesize) {
		
		IMResult<List<HotWebEventVO>> result = new IMResult<List<HotWebEventVO>>();
		result.setSuccess(false);
		result.setValue(new ArrayList<HotWebEventVO>());
		
		String key = String.format("dc:hotweb:eventlist:%s", timestamp);
		
		KVResult<byte[]> rc = sortedKeyValueStoreEngine.get(key);
		
		if ( rc.isSuccess() == true ) {
			byte[] datas = rc.getValue();
			BPHotWebEventList eventList = null;
			try {
				eventList = BPHotWebEventList.parseFrom(datas);
			} catch (InvalidProtocolBufferException e) {
				// TODO
				// logger  记录发生的事情
				result.setSuccess(false);
				return result;
			}
			for ( int i = pageno * pagesize; i < eventList.getEventsCount() && i < (pageno + 1) * pagesize; i++ ) {
				BPHotWebEvent event = eventList.getEvents(i);
				HotWebEventVO vo = new HotWebEventVO();
				vo.setEventid(event.getEventid());
				vo.setTitle(event.getTitle().toStringUtf8());
				vo.setScore(event.getScore());
				
				result.getValue().add(vo);
			}
			result.setSuccess(true);
			return result;
		} else if ( rc.getErrorCode() == KeyValueStoreError.StoreKeyNotExistError.getErrorCode() ) {
			logger.warn(String.format("key: %s, errorMsg: key not exist in KeyValueStoreEngine", key));
			result.setSuccess(false);
			return result;
		} else {
			
		}
		
		return result;
		
	}

	public IMResult<Void> removeHotWebEvents(String timestamp) {

		IMResult<Void> result = new IMResult<Void>();
		result.setSuccess(false);
		
		String key = String.format("dc:hotweb:eventlist:%s", timestamp);
		
		KVResult<Void> rc = sortedKeyValueStoreEngine.delete(key);
		if ( rc.isSuccess() ) {
			result.setSuccess(true);
		}
		
		return result;
	}

	
	public IMResult<Void> insertHotWebPages(String eventid, List<HotWebPageVO> webpages) {
		
		IMResult<Void> result = new IMResult<Void>();
		result.setSuccess(false);
		
		String key = String.format("dc:hotweb:eventid:%s", eventid);
		BPHotWebPageList.Builder builder = BPHotWebPageList.newBuilder();
		
		Set<String> filterSet = new TreeSet<String>();
		for ( HotWebPageVO webpage : webpages ) {
			// -------------------------------------------------
			if ( filterSet.contains(webpage.getUrl()) ) {
				continue;
			}
			filterSet.add(webpage.getUrl());
			
			BPHotWebPage.Builder bpbuilder = BPHotWebPage.newBuilder();
			bpbuilder.setUrl(webpage.getUrl());
			bpbuilder.setSource(ByteString.copyFromUtf8(webpage.getSource()));
			bpbuilder.setTitle(ByteString.copyFromUtf8(webpage.getTitle()));
			bpbuilder.setTimestamp(webpage.getTimestamp());
			bpbuilder.setContent(ByteString.copyFromUtf8(webpage.getContent()));
			builder.addWebpages(bpbuilder.build());
		}
		
		KVResult<Void> rc = sortedKeyValueStoreEngine.set(key, builder.build().toByteArray());
		if ( rc.isSuccess() ) {
			result.setSuccess(true);
		} else {
		}
		
		return result;
		
	}

	public IMResult<List<HotWebPageVO>> queryHotWebPages(String eventid, int pageno, int pagesize) {
		
		IMResult<List<HotWebPageVO>> result = new IMResult<List<HotWebPageVO>>();
		result.setSuccess(false);
		result.setValue(new ArrayList<HotWebPageVO>());
		
		String key = String.format("dc:hotweb:eventid:%s", eventid);
		
		KVResult<byte[]> rc = sortedKeyValueStoreEngine.get(key);
		
		if ( rc.isSuccess() ) {
			byte[] datas = rc.getValue();
			BPHotWebPageList webpageList = null;
			try {
				webpageList = BPHotWebPageList.parseFrom(datas);
			} catch (InvalidProtocolBufferException e) {
				// TODO
				// logger  记录发生的事情
				result.setSuccess(false);
				return result;
			}
			for ( int i = pageno * pagesize; i < webpageList.getWebpagesCount() && i < (pageno + 1) * pagesize; i++ ) {
				BPHotWebPage webpage = webpageList.getWebpages(i);
				DCWebPage dcwebpage = new DCWebPage();
				dcwebpage.setUrl(webpage.getUrl());
				dcwebpage.setSource(webpage.getSource().toStringUtf8());
				dcwebpage.setTitle(webpage.getTitle().toStringUtf8());
				dcwebpage.setTimestamp(webpage.getTimestamp());
				dcwebpage.setContent(webpage.getContent().toStringUtf8());
				
				HotWebPageVO vo = new HotWebPageVO();
				vo.setUrl(webpage.getUrl());
				vo.setSource(webpage.getSource().toStringUtf8());
				vo.setTitle(webpage.getTitle().toStringUtf8());
				vo.setTimestamp(webpage.getTimestamp());
				vo.setContent(webpage.getContent().toStringUtf8());
				result.getValue().add(vo);
			}
			result.setSuccess(true);
			return result;
		} else if ( rc.getErrorCode() == KeyValueStoreError.StoreKeyNotExistError.getErrorCode() ) {
			logger.warn(String.format("key: %s, errorMsg: key not exist in KeyValueStoreEngine", key));
			result.setSuccess(false);
			return result;
		} else {
			
		}
		
		return result;
		
	}
	
}
