package com.lightning.datacenter.rpc.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;

import com.lighting.rpc.common.exception.LightningServiceException;
import com.lighting.rpc.core.utility.LoggerUtility;
import com.lighting.rpc.datacenter.model.DCAddHotWebEventsRequest;
import com.lighting.rpc.datacenter.model.DCAddHotWebEventsResponse;
import com.lighting.rpc.datacenter.model.DCAddSensitiveWordRequest;
import com.lighting.rpc.datacenter.model.DCAddSensitiveWordResponse;
import com.lighting.rpc.datacenter.model.DCCommonLanguge;
import com.lighting.rpc.datacenter.model.DCConsistentTopic;
import com.lighting.rpc.datacenter.model.DCCreateTopicRequest;
import com.lighting.rpc.datacenter.model.DCCreateTopicResponse;
import com.lighting.rpc.datacenter.model.DCDateNumberTopicTrend;
import com.lighting.rpc.datacenter.model.DCHotWebEvent;
import com.lighting.rpc.datacenter.model.DCInsertHotWebEventsRequest;
import com.lighting.rpc.datacenter.model.DCInsertHotWebEventsResponse;
import com.lighting.rpc.datacenter.model.DCInsertHotWebPagesRequest;
import com.lighting.rpc.datacenter.model.DCInsertHotWebPagesResponse;
import com.lighting.rpc.datacenter.model.DCQueryHotWebEventsRequest;
import com.lighting.rpc.datacenter.model.DCQueryHotWebEventsResponse;
import com.lighting.rpc.datacenter.model.DCQueryHotWebPagesRequest;
import com.lighting.rpc.datacenter.model.DCQueryHotWebPagesResponse;
import com.lighting.rpc.datacenter.model.DCQueryHotwordListRequest;
import com.lighting.rpc.datacenter.model.DCQueryHotwordListResponse;
import com.lighting.rpc.datacenter.model.DCQueryHotwordTrendRequest;
import com.lighting.rpc.datacenter.model.DCQueryHotwordTrendResponse;
import com.lighting.rpc.datacenter.model.DCQuerySensitiveWordListRequest;
import com.lighting.rpc.datacenter.model.DCQuerySensitiveWordListResponse;
import com.lighting.rpc.datacenter.model.DCQuerySensitiveWordListTrendRequest;
import com.lighting.rpc.datacenter.model.DCQuerySensitiveWordListTrendResponse;
import com.lighting.rpc.datacenter.model.DCQuerySensitiveWordTrendRequest;
import com.lighting.rpc.datacenter.model.DCQuerySensitiveWordTrendResponse;
import com.lighting.rpc.datacenter.model.DCQueryTopicAtTimeRequest;
import com.lighting.rpc.datacenter.model.DCQueryTopicAtTimeResponse;
import com.lighting.rpc.datacenter.model.DCQueryTopicListRequest;
import com.lighting.rpc.datacenter.model.DCQueryTopicListResponse;
import com.lighting.rpc.datacenter.model.DCQueryTopicRequest;
import com.lighting.rpc.datacenter.model.DCQueryTopicResponse;
import com.lighting.rpc.datacenter.model.DCQueryTopicTrendRequest;
import com.lighting.rpc.datacenter.model.DCQueryTopicTrendResponse;
import com.lighting.rpc.datacenter.model.DCQueryTopicWebpagesRequest;
import com.lighting.rpc.datacenter.model.DCQueryTopicWebpagesResponse;
import com.lighting.rpc.datacenter.model.DCRemoveHotWebEventsRequest;
import com.lighting.rpc.datacenter.model.DCRemoveHotWebEventsResponse;
import com.lighting.rpc.datacenter.model.DCRemoveSensitiveWordRequest;
import com.lighting.rpc.datacenter.model.DCRemoveSensitiveWordResponse;
import com.lighting.rpc.datacenter.model.DCRemoveTopicRequest;
import com.lighting.rpc.datacenter.model.DCRemoveTopicResponse;
import com.lighting.rpc.datacenter.model.DCTimeTrend;
import com.lighting.rpc.datacenter.model.DCTimeWordInfo;
import com.lighting.rpc.datacenter.model.DCTimeWordTrend;
import com.lighting.rpc.datacenter.model.DCWebPage;
import com.lighting.rpc.datacenter.service.DataCenterService;
import com.lightning.datacenter.controller.DataCenterServerController;
import com.lightning.datacenter.manager.ConsistentTopicManager;
import com.lightning.datacenter.manager.HotWebTrendManager;
import com.lightning.datacenter.manager.HotWordManager;
import com.lightning.datacenter.manager.SensitiveWordManager;
import com.lightning.datacenter.model.ConsistentTopicTrendVO;
import com.lightning.datacenter.model.ConsistentTopicVO;
import com.lightning.datacenter.model.ConsistentTopicWebPageVO;
import com.lightning.datacenter.model.HotWebEventVO;
import com.lightning.datacenter.model.HotWebPageVO;
import com.lightning.datacenter.model.HotWordTrendVO;
import com.lightning.datacenter.model.IMResult;
import com.lightning.datacenter.model.SensitiveWordTrendVO;
import com.lightning.datacenter.model.SensitiveWordVO;
import com.lightning.datacenter.model.TimeWordInfoVO;
import com.lightning.datacenter.model.TimeWordTrendVO;
import com.lightning.datacenter.utils.DataCenterEventUtility;

public class DataCenterServiceHandlerImpl implements DataCenterService.Iface {

//	private static final Logger logger =
//			LoggerFactory.getLogger("rpc");
//			LoggerFactory.getLogger(DataCenterServiceHandlerImpl.class);
	
	private DataCenterServerController controller;

	public DataCenterServiceHandlerImpl(DataCenterServerController controller) {
		this.controller = controller;
	}
	
	@Override
	public DCQueryHotWebEventsResponse queryHotWebEvents(
			DCQueryHotWebEventsRequest request)
			throws LightningServiceException, TException {
		// TODO Auto-generated method stub

		/*
		LoggerUtility.noticeLog("[logid: %d] [request: {timestamp: %s}]", request.getLogid(), request.getTimestamp());
		
		DCQueryHotWebEventsResponse response = new DCQueryHotWebEventsResponse();
		response.setTimestamp(request.getTimestamp());
		response.setHotEvents(new ArrayList<DCHotWebEvent>());
		
		// 以下涉及具体的业务逻辑......
		String timestamp = request.getTimestamp();
		
		String key = String.format("dc:hotweb:eventlist:%s", timestamp);
		
		IKeyValueStoreEngine engine = controller.getKeyValueStoreEngine();
		KVResult<byte[]> rc = engine.get(key);
		
		if ( rc == null ) {
			return response;
		}
		
		if ( rc.isSuccess() == true ) {
			byte[] datas = rc.getValue();
			BPHotWebEventList eventList = null;
			try {
				eventList = BPHotWebEventList.parseFrom(datas);
			} catch (InvalidProtocolBufferException e) {
				// TODO
				// logger  记录发生的事情
				throw new LightningServiceException(request.getLogid(), 10001, "data error");
			}
			int pageno = request.getPageno();
			int pagesize = request.getPagesize();
			for ( int i = pageno * pagesize; i < eventList.getEventsCount() && i < (pageno + 1) * pagesize; i++ ) {
				BPHotWebEvent event = eventList.getEvents(i);
				DCHotWebEvent dcevent = new DCHotWebEvent();
				dcevent.setEventid(event.getEventid());
				dcevent.setTitle(event.getTitle().toStringUtf8());
				dcevent.setScore(event.getScore());
				response.getHotEvents().add(dcevent);
			}
			return response;
		} else if ( rc.getErrorCode() == KeyValueStoreError.StoreKeyNotExistError.getErrorCode() ) {
			logger.warn(String.format("key: %s, errorMsg: key not exist in KeyValueStoreEngine", key));
			return response;
		} else {
			
		}
		*/
		// --------------------------------------
		
		// 以下涉及具体的业务逻辑......
		long logid = request.getLogid();
		String timestamp = request.getTimestamp();
		int pageno = request.getPageno();
		int pagesize = request.getPagesize();
		
		LoggerUtility.noticeLog("[logid: %d] [request: {timestamp: %s, pageno: %d, pagesize: %d}]", 
					logid, timestamp, pageno, pagesize);
		
		DCQueryHotWebEventsResponse response = new DCQueryHotWebEventsResponse();
		response.setTimestamp(request.getTimestamp());
		response.setHotEvents(new ArrayList<DCHotWebEvent>());
		
		HotWebTrendManager hotWebTrendManager = controller.getHotWebTrendManager();
		IMResult<List<HotWebEventVO>> rc = hotWebTrendManager.queryHowebEventList(timestamp, pageno, pagesize);
		if ( rc.isSuccess() ) {
			List<HotWebEventVO> webEventVOs = rc.getValue();
			for ( HotWebEventVO webEventVO : webEventVOs ) {
				DCHotWebEvent webEvent = new DCHotWebEvent();
				webEvent.setEventid(webEventVO.getEventid());
				webEvent.setTitle(webEventVO.getTitle());
				webEvent.setScore(webEventVO.getScore());
				
				response.getHotEvents().add(webEvent);
			}
		}
		
		return response;
		
	}

	@Override
	public DCQueryHotWebPagesResponse queryHotWebPages(
			DCQueryHotWebPagesRequest request)
			throws LightningServiceException, TException {

		/*
		DCQueryHotWebPagesResponse response = new DCQueryHotWebPagesResponse();
		response.setTotalNum(0);
		response.setWebPages(new ArrayList<DCWebPage>());
		
		String eventid = request.getEventid();
		String key = String.format("dc:hotweb:eventid:%s", eventid);
		
		IKeyValueStoreEngine engine = controller.getKeyValueStoreEngine();
		KVResult<byte[]> rc = engine.get(key);
		
		if ( rc == null ) {
			return response;
		}
		if ( rc.isSuccess() ) {
			byte[] datas = rc.getValue();
			BPHotWebPageList webpageList = null;
			try {
				webpageList = BPHotWebPageList.parseFrom(datas);
			} catch (InvalidProtocolBufferException e) {
				// TODO
				// logger  记录发生的事情
				throw new LightningServiceException(request.getLogid(), 10001, "data error");
			}
			int pageno = request.getPageno();
			int pagesize = request.getPagesize();
			for ( int i = pageno * pagesize; i < webpageList.getWebpagesCount() && i < (pageno + 1) * pagesize; i++ ) {
				BPHotWebPage webpage = webpageList.getWebpages(i);
				DCWebPage dcwebpage = new DCWebPage();
				dcwebpage.setUrl(webpage.getUrl());
				dcwebpage.setSource(webpage.getSource().toStringUtf8());
				dcwebpage.setTitle(webpage.getTitle().toStringUtf8());
				dcwebpage.setTimestamp(webpage.getTimestamp());
				dcwebpage.setContent(webpage.getContent().toStringUtf8());
				response.getWebPages().add(dcwebpage);
			}
			response.setTotalNum(webpageList.getWebpagesCount());
			return response;
		} else if ( rc.getErrorCode() == KeyValueStoreError.StoreKeyNotExistError.getErrorCode() ) {
			logger.warn(String.format("key: %s, errorMsg: key not exist in KeyValueStoreEngine", key));
			return response;
		} else {
			
		}
		
		return response;
		*/
		
		long logid = request.getLogid();
		String eventid = request.getEventid();
		int pageno = request.getPageno();
		int pagesize = request.getPagesize();

		LoggerUtility.noticeLog("[logid: %d] [request: {eventid: %s, pageno: %d, pagesize: %d}]", 
				logid, eventid, pageno, pagesize);
		
		DCQueryHotWebPagesResponse response = new DCQueryHotWebPagesResponse();
		response.setTotalNum(0);
		response.setWebPages(new ArrayList<DCWebPage>());
		
		HotWebTrendManager hotWebTrendManager = controller.getHotWebTrendManager();
		IMResult<List<HotWebPageVO>> rc = hotWebTrendManager.queryHotWebPages(eventid, pageno, pagesize);
		if ( rc.isSuccess() ) {
			List<HotWebPageVO> hotWebPageVOs = rc.getValue();
			for ( HotWebPageVO webpageVO : hotWebPageVOs ) {
				DCWebPage webpage = new DCWebPage();
				webpage.setUrl(webpageVO.getUrl());
				webpage.setSource(webpageVO.getSource());
				webpage.setTitle(webpageVO.getTitle());
				webpage.setTimestamp(webpageVO.getTimestamp());
				webpage.setContent(webpageVO.getContent());
				
				response.getWebPages().add(webpage);
			}
		} else {
			// 打错个warning什么的......
		}
		return response;
		
	}

	@Override
	public DCInsertHotWebPagesResponse insertHotWebPages(
			DCInsertHotWebPagesRequest request)
			throws LightningServiceException, TException {
		// TODO Auto-generated method stub
		
		/*
		String timestamp = request.getTimestamp();
		String title = request.getTitle();
		List<DCWebPage> webpages = request.getWebPages();

		// 日志记录
		LoggerUtility.noticeLog("[logid: %d] [request : {timestamp: %s, title: %s, webspage_size: %d}]", 
				request.getLogid(), timestamp, title, webpages.size());
		
		String eventid = DataCenterEventUtility.genEventid(timestamp, title);
		
		DCInsertHotWebPagesResponse response = new DCInsertHotWebPagesResponse();
		response.setEventid(eventid);
		
		String key = String.format("dc:hotweb:eventid:%s", eventid);
		BPHotWebPageList.Builder builder = BPHotWebPageList.newBuilder();
		for ( DCWebPage webpage : webpages ) {
			BPHotWebPage.Builder bpbuilder = BPHotWebPage.newBuilder();
			bpbuilder.setUrl(webpage.getUrl());
			bpbuilder.setSource(ByteString.copyFromUtf8(webpage.getSource()));
			bpbuilder.setTitle(ByteString.copyFromUtf8(webpage.getTitle()));
			bpbuilder.setTimestamp(webpage.getTimestamp());
			bpbuilder.setContent(ByteString.copyFromUtf8(webpage.getContent()));
			builder.addWebpages(bpbuilder.build());
		}
		
		IKeyValueStoreEngine engine = controller.getKeyValueStoreEngine();
		KVResult<Void> rc = engine.set(key, builder.build().toByteArray());
		if ( rc.isSuccess() ) {
		} else {
		}
		
		return response;
		*/
		
		long logid = request.getLogid();
		String timestamp = request.getTimestamp();
		String title = request.getTitle();
		List<DCWebPage> webpages = request.getWebPages();

		// 日志记录
		LoggerUtility.noticeLog("[logid: %d] [request : {timestamp: %s, title: %s, webspage_size: %d}]", 
				logid, timestamp, title, webpages.size());
		
		String eventid = DataCenterEventUtility.genEventid(timestamp, title);
		
		DCInsertHotWebPagesResponse response = new DCInsertHotWebPagesResponse();
		response.setEventid(eventid);
		
		List<HotWebPageVO> webpageVOs = new ArrayList<HotWebPageVO>();
		for ( DCWebPage webpage : webpages ) {
			HotWebPageVO webpageVO = new HotWebPageVO();
			webpageVO.setUrl(webpage.getUrl());
			webpageVO.setSource(webpage.getSource());
			webpageVO.setTitle(webpage.getTitle());
			webpageVO.setTimestamp(webpage.getTimestamp());
			webpageVO.setContent(webpage.getContent());
			
			webpageVOs.add(webpageVO);
		}
		
		HotWebTrendManager hotWebTrendManager = controller.getHotWebTrendManager();
		IMResult<Void> rc = hotWebTrendManager.insertHotWebPages(eventid, webpageVOs);
		if ( rc.isSuccess() ) {
			rc.setSuccess(true);
		}
		
		return response;
		
	}

	@Override
	public DCInsertHotWebEventsResponse insertHotWebEvents(
			DCInsertHotWebEventsRequest request)
			throws LightningServiceException, TException {
		
		/*
		DCInsertHotWebEventsResponse response = new DCInsertHotWebEventsResponse();
		
		List<DCHotWebEvent> events = request.getEvents();
		String timestamp = request.getTimestamp();

		// *) 进行排序处理
		Collections.sort(events, new Comparator<DCHotWebEvent>() {
			@Override
			public int compare(DCHotWebEvent o1, DCHotWebEvent o2) {
				if ( o1.getScore() > o2.getScore() ) {
					return -1;
				} else if ( o1.getScore() < o2.getScore() ) {
					return 1;
				}
				return 0;
			}
		});
		
		String key = String.format("dc:hotweb:eventlist:%s", timestamp);
		BPHotWebEventList.Builder builder = BPHotWebEventList.newBuilder();
		builder.setTimestamp(timestamp);
		
		for ( DCHotWebEvent event : events ) {
			BPHotWebEvent.Builder bpbuilder = BPHotWebEvent.newBuilder();
			bpbuilder.setEventid(event.getEventid());
			bpbuilder.setTitle(ByteString.copyFromUtf8(event.getTitle()));
			bpbuilder.setScore(event.getScore());
			builder.addEvents(bpbuilder.build());
		}
		
		IKeyValueStoreEngine engine = controller.getKeyValueStoreEngine();
		KVResult<Void> rc = engine.set(key, builder.build().toByteArray());
		if ( rc.isSuccess() ) {
		} else {
		}
		
		return response;
		*/
		
		long logid = request.getLogid();
		String timestamp = request.getTimestamp();
		List<DCHotWebEvent> events = request.getEvents();
		
		LoggerUtility.noticeLog("[logid: %d] [request: {timestamp: %s, webevent_size: %d}]", 
					logid, timestamp, events.size()); 

		DCInsertHotWebEventsResponse response = new DCInsertHotWebEventsResponse();

		List<HotWebEventVO> eventVOs = new ArrayList<HotWebEventVO>();
		for ( DCHotWebEvent event : events ) {
			HotWebEventVO eventVO = new HotWebEventVO();
			eventVO.setEventid(event.getEventid());
			eventVO.setTitle(event.getTitle());
			eventVO.setScore(event.getScore());
			eventVOs.add(eventVO);
		}
		
		HotWebTrendManager hotWebTrendManager = controller.getHotWebTrendManager();
		IMResult<Integer> rc = hotWebTrendManager.addHotWebEvents(timestamp, eventVOs);
		if ( rc.isSuccess() ) {
		} else {
			
		}
		
		return response;
		
	}

	@Override
	public DCAddHotWebEventsResponse addHotWebEvents(
			DCAddHotWebEventsRequest request) throws LightningServiceException,
			TException {
		// TODO Auto-generated method stub
		
		/*
		DCAddHotWebEventsResponse response = new DCAddHotWebEventsResponse();
		response.setSuccessAmount(0);

		String timestamp = request.getTimestamp();
		
		List<DCHotWebEvent> resultEvents = new ArrayList<DCHotWebEvent>();
		
		String key = String.format("dc:hotweb:eventlist:%s", timestamp);
		
		IKeyValueStoreEngine engine = controller.getKeyValueStoreEngine();
		KVResult<byte[]> rc = engine.get(key);
		if ( rc.isSuccess() == true ) {
			byte[] datas = rc.getValue();
			BPHotWebEventList eventList = null;
			try {
				eventList = BPHotWebEventList.parseFrom(datas);
			} catch (InvalidProtocolBufferException e) {
				// TODO
				// logger  记录发生的事情
				throw new LightningServiceException(request.getLogid(), 10001, "data error");
			}
			if ( eventList != null ) {
				for ( BPHotWebEvent event : eventList.getEventsList() ) {
					DCHotWebEvent dcevent = new DCHotWebEvent();
					dcevent.setEventid(event.getEventid());
					dcevent.setScore(event.getScore());
					dcevent.setTitle(event.getTitle().toStringUtf8());
					resultEvents.add(dcevent);
				}
			}
		} 
		
		// merge
		Set<String> eventSet = new TreeSet<String>();
		for ( DCHotWebEvent event : resultEvents ) {
			eventSet.add(event.getEventid());
		}
		for ( DCHotWebEvent event : request.getEvents() ) {
			if ( eventSet.contains(event.getEventid()) ) {
				continue;
			}
			eventSet.add(event.getEventid());
			resultEvents.add(event);
		}
		
		
		BPHotWebEventList.Builder builder = BPHotWebEventList.newBuilder();
		builder.setTimestamp(timestamp);
		for ( DCHotWebEvent event : resultEvents ) {
			BPHotWebEvent.Builder ebuilder = BPHotWebEvent.newBuilder();
			ebuilder.setEventid(event.getEventid());
			ebuilder.setScore(event.getScore());
			ebuilder.setTitle(ByteString.copyFromUtf8(event.getTitle()));
			builder.addEvents(ebuilder.build());
		}
		
		KVResult<Void> rc2 = engine.set(key, builder.build().toByteArray());
		if ( rc2.isSuccess() ) {
			response.setSuccessAmount(request.getEventsSize());
		} else {
			
		}
		return response;
		*/
		
		long logid = request.getLogid();
		String timestamp = request.getTimestamp();
		List<DCHotWebEvent> events = request.getEvents();
		
		LoggerUtility.noticeLog("[logid: %d] [request: {timestamp: %s, webevent_size: %d}]", 
					logid, timestamp, events.size()); 

		DCAddHotWebEventsResponse response = new DCAddHotWebEventsResponse();
		response.setSuccessAmount(0);
		
		List<HotWebEventVO> eventVOs = new ArrayList<HotWebEventVO>();
		for ( DCHotWebEvent event : events ) {
			HotWebEventVO eventVO = new HotWebEventVO();
			eventVO.setEventid(event.getEventid());
			eventVO.setTitle(event.getTitle());
			eventVO.setScore(event.getScore());
			eventVOs.add(eventVO);
		}
		
		HotWebTrendManager hotWebTrendManager = controller.getHotWebTrendManager();
		IMResult<Integer> rc = hotWebTrendManager.addHotWebEvents(timestamp, eventVOs);
		if ( rc.isSuccess() ) {
			int intValue = (rc.getValue() == null) ? 0 : rc.getValue().intValue();
			response.setSuccessAmount(intValue);
		} else {
			
		}
		
		return response;
		
	}


	@Override
	public DCRemoveHotWebEventsResponse removeHotWebEvents(
			DCRemoveHotWebEventsRequest request)
			throws LightningServiceException, TException {

		/*
		DCRemoveHotWebEventsResponse response = new DCRemoveHotWebEventsResponse();
		response.setSuccessAmount(0);
		
		String timestamp = request.getTimestamp();
		String key = String.format("dc:hotweb:eventlist:%s", timestamp);
		
		IKeyValueStoreEngine engine = controller.getKeyValueStoreEngine();
		KVResult<Void> rc = engine.delete(key);
		if ( rc.isSuccess() ) {
			response.setSuccessAmount(1);
		}
		return response;
		*/
		
		long logid = request.getLogid();
		String timestamp = request.getTimestamp();
		
		LoggerUtility.noticeLog("[logid: %d] [request: {timestamp: %s}]", 
				logid, timestamp); 
		
		DCRemoveHotWebEventsResponse response = new DCRemoveHotWebEventsResponse();
		response.setSuccessAmount(0);
		
		HotWebTrendManager hotWebTrendManager = controller.getHotWebTrendManager();
		IMResult<Void> rc = hotWebTrendManager.removeHotWebEvents(timestamp);
		if ( rc.isSuccess() ) {
			response.setSuccessAmount(1);
		} else {
		}
		
		return response;
		
	}

	@Override
	public DCQueryHotwordListResponse queryHotwordList(
			DCQueryHotwordListRequest request)
			throws LightningServiceException, TException {
		// TODO Auto-generated method stub
		
		// 请求参数
		String timestamp = request.getTimestamp();
		int pageno = request.getPageno();
		int pagesize = request.getPagesize();
		
		LoggerUtility.noticeLog("[logid: %d] [request: {timestamp: %s, pageno: %d, pagesize: %d}]",
				request.getLogid(), timestamp, pageno, pagesize);
		
		// 结果集
		DCQueryHotwordListResponse response = new DCQueryHotwordListResponse();
		response.setWords(new ArrayList<String>());
		
		// 
		HotWordManager hotwordManager = controller.getHotwordManager();
		List<String> words = hotwordManager.readWordList(timestamp);
		for ( int i = pageno * pagesize; i < words.size() && i < (pageno + 1) * pagesize; i++ ) {
			response.getWords().add(words.get(i));
		}
		
		LoggerUtility.noticeLog("[response: { words.size = %d }]", response.getWords().size());
		// 
		return response;
		
	}

	@Override
	public DCQueryHotwordTrendResponse queryHotwordTrend(
			DCQueryHotwordTrendRequest request)
			throws LightningServiceException, TException {

		// 请求参数
		String word = request.getWord();
		String startDate = request.getStartDate();
		String endDate = request.getEndDate();
		
		// 
		DCQueryHotwordTrendResponse response = new DCQueryHotwordTrendResponse();
		response.setTrends(new ArrayList<DCTimeTrend>());
		
		// 交互
		HotWordManager hotwordManager = controller.getHotwordManager();
		List<HotWordTrendVO> trends = hotwordManager.batchReadWordFreqs(word, startDate, endDate);
		
		// 
		for ( HotWordTrendVO tvo : trends ) {
			response.getTrends().add(new DCTimeTrend(tvo.getTimestamp(), tvo.getFreq()));
		}
		
		return response;

	}

	@Override
	public DCAddSensitiveWordResponse addSensitiveWord(
			DCAddSensitiveWordRequest request)
			throws LightningServiceException, TException {

		long logid = request.getLogid();
		String word = request.getWord();
		DCCommonLanguge langugeType = request.getLangugeType();
		
		LoggerUtility.noticeLog("[logid: %d] [request: {word: %s, languageType: %d}]", 
					logid, word, langugeType.getValue());
		
		DCAddSensitiveWordResponse response = new DCAddSensitiveWordResponse();
		response.setSuccessAmount(0);
		
		SensitiveWordManager sensitiveWordManager = controller.getSensitiveWordManager();
		sensitiveWordManager.addWord(word, langugeType.getValue());
		response.setSuccessAmount(1);
		
		LoggerUtility.noticeLog("[response: {success_amount: 1}]");
		
		return response;
		
	}

	@Override
	public DCRemoveSensitiveWordResponse removeSensitiveWord(
			DCRemoveSensitiveWordRequest request)
			throws LightningServiceException, TException {

		long logid = request.getLogid();
		String word = request.getWord();
		DCCommonLanguge languageType = request.getLangugeType();
		
		LoggerUtility.noticeLog("[logid: %d] [request: {word: %s, languageType: %d}]", 
					logid, word, languageType.getValue());
		
		DCRemoveSensitiveWordResponse response = new DCRemoveSensitiveWordResponse();
		response.setSuccessAmount(0);

		SensitiveWordManager sensitiveWordManager = controller.getSensitiveWordManager();
		sensitiveWordManager.removeWord(word, languageType.getValue());
		response.setSuccessAmount(1);
		
		LoggerUtility.noticeLog("[response: {success_amount: 1}]");
		
		return response;
		
	}

	@Override
	public DCQuerySensitiveWordListResponse querySensitiveWordList(
			DCQuerySensitiveWordListRequest request)
			throws LightningServiceException, TException {
		
		long logid = request.getLogid();
		int pageno = request.getPageno();
		int pagesize = request.getPagesize();
		DCCommonLanguge languageType = request.getLangugeType();
		
		LoggerUtility.noticeLog("[logid: %d]", logid);
		
		DCQuerySensitiveWordListResponse response = new DCQuerySensitiveWordListResponse();
		response.setWords(new ArrayList<String>());
		
		SensitiveWordManager sensitiveWordManager = controller.getSensitiveWordManager();
		List<SensitiveWordVO> wordVOs = sensitiveWordManager.querySensitiveWord(languageType.getValue(), pageno, pagesize);
		
		for ( SensitiveWordVO vo : wordVOs ) {
			response.getWords().add(vo.getWord());
		}
		
		LoggerUtility.noticeLog("[response: {word_size: %d}]", response.getWords().size());
		
		return response;
	
	}
	
	@Override
	public DCQuerySensitiveWordTrendResponse querySensitiveWordTrend(
			DCQuerySensitiveWordTrendRequest request)
			throws LightningServiceException, TException {

		long logid = request.getLogid();
		DCCommonLanguge languageType = request.getLangugeType();
		String word = request.getWord();
		String startDate = request.getStartDate();
		String endDate = request.getEndDate();
		int type = languageType.getValue();
		
		LoggerUtility.noticeLog("[logid: %d] [request: {}]", logid);
		
		DCQuerySensitiveWordTrendResponse response = new DCQuerySensitiveWordTrendResponse();
		response.setTrends(new ArrayList<DCTimeTrend>());
		
		SensitiveWordManager sensitiveWordManager = controller.getSensitiveWordManager();
		IMResult<List<SensitiveWordTrendVO>> rc = sensitiveWordManager.listSensitiveWordTrend(word, type, startDate, endDate);
		
		if ( rc.isSuccess() ) {
			List<SensitiveWordTrendVO> trendVOs = rc.getValue();
			for ( SensitiveWordTrendVO trend : trendVOs ) {
				DCTimeTrend dctrend = new DCTimeTrend();
				dctrend.setTimestamp(trend.getTimestamp());
				dctrend.setFreq(trend.getValue());
				response.getTrends().add(dctrend);
			}
		} else {
			// TODO
		}
		
		return response;
	
	}
	
	@Override
	public DCQuerySensitiveWordListTrendResponse querySensitiveWordListTrend(
			DCQuerySensitiveWordListTrendRequest request)
			throws LightningServiceException, TException {
		
		
		long logid = request.getLogid();
		DCCommonLanguge languageType = request.getLangugeType();
		int pageno = request.getPageno();
		int pagesize = request.getPagesize();
		String startDate = request.getStartDate();
		String endDate = request.getEndDate();
		
		LoggerUtility.noticeLog("[logid: %d] [request: {pageno: %d, pagesize: %d}]", logid, pageno, pagesize);
		
		DCQuerySensitiveWordListTrendResponse response = new DCQuerySensitiveWordListTrendResponse();
		response.setTrends(new ArrayList<DCTimeWordTrend>());		
		
		SensitiveWordManager sensitiveWordManager = controller.getSensitiveWordManager();
		
		IMResult<List<TimeWordTrendVO>> rc = sensitiveWordManager.listSensitiveWordListTrend(
				languageType.getValue(), pageno, pagesize, startDate, endDate);
		
		if ( rc.isSuccess() ) {
			List<TimeWordTrendVO> twtvos = rc.getValue();
			for ( TimeWordTrendVO twtvo : twtvos ) {
				DCTimeWordTrend dctwt = new DCTimeWordTrend();
				dctwt.setTimestamp(twtvo.getTimestamp());
				dctwt.setWordinfos(new ArrayList<DCTimeWordInfo>());
				for ( TimeWordInfoVO twinfo : twtvo.getWordInfos() ) {
					dctwt.getWordinfos().add(new DCTimeWordInfo(twinfo.getWord(), twinfo.getFreq()));
				}
				response.getTrends().add(dctwt);
			}
		}
		
		return response;

	}
	
	
	@Override
	public DCCreateTopicResponse createConsistentTopic(
			DCCreateTopicRequest request) throws LightningServiceException,
			TException {

		long logid = request.getLogid();
		DCCommonLanguge languageType = request.getLangugeType();
		DCConsistentTopic reqTopic = request.getTopic();
		
		LoggerUtility.noticeLog("[logid: %d] [request: {languageType: %d}]", 
					logid, languageType.getValue());
		
		DCCreateTopicResponse response = new DCCreateTopicResponse();
		DCConsistentTopic resTopic = new DCConsistentTopic(reqTopic);
		response.setTopic(resTopic);
		
		ConsistentTopicVO topicVO = new ConsistentTopicVO();
		topicVO.setTopicName(reqTopic.getTopicName());
		topicVO.setType(languageType.getValue());
		topicVO.setStartDate(reqTopic.getStartDate());
		topicVO.setEndDate(reqTopic.getEndDate());
		topicVO.setPercent(reqTopic.getPercent());
		topicVO.setWords(reqTopic.getWords());
		
		ConsistentTopicManager consistentTopicManager = controller.getConsistentTopicManager();
		IMResult<Long> rc = consistentTopicManager.createConsistentTopic(topicVO);
		if ( rc.isSuccess() ) {
			resTopic.setTopicId(rc.getValue());
		} else {
			// TODO
		}
		return response;
		
	}

	@Override
	public DCRemoveTopicResponse removeConsistentTopic(
			DCRemoveTopicRequest request) throws LightningServiceException,
			TException {
		
		long logid = request.getLogid();
		long topicId = request.getTopicId();
		
		LoggerUtility.noticeLog("[logid: %d] [request: {topicId: %d}]", logid, topicId);
		
		DCRemoveTopicResponse response = new DCRemoveTopicResponse();
		response.setSuccessAmount(0);
		
		ConsistentTopicManager consistentTopicManager = controller.getConsistentTopicManager();
		IMResult<Void> rc = consistentTopicManager.removeConsistentTopic(topicId);
		if ( rc.isSuccess() ) {
			response.setSuccessAmount(1);
		} else {
		}
		
		return response;
		
	}
	
	@Override
	public DCQueryTopicResponse queryConsistentTopic(DCQueryTopicRequest request)
			throws LightningServiceException, TException {
		
		long logid = request.getLogid();
		long topicId = request.getTopicId();
		
		DCQueryTopicResponse response = new DCQueryTopicResponse();
		
		ConsistentTopicManager consistentTopicManager = controller.getConsistentTopicManager();
		IMResult<ConsistentTopicVO> rc = consistentTopicManager.queryConsistentTopic(topicId);
		
		if ( rc.isSuccess() ) {
			ConsistentTopicVO topicVO = rc.getValue();
			DCConsistentTopic topic = new DCConsistentTopic();
			
			topic.setTopicId(topicVO.getTopicId());
			topic.setTopicName(topicVO.getTopicName());
			topic.setWords(topicVO.getWords());
			topic.setStartDate(topicVO.getStartDate());
			topic.setEndDate(topicVO.getEndDate());
			topic.setPercent(topicVO.getPercent());
			
			response.setTopic(topic);
		} else {
			throw new LightningServiceException(logid, 10002, "Topic Not Found");
		}
		
		return response;
		
	}

	@Override
	public DCQueryTopicListResponse queryTopicList(
			DCQueryTopicListRequest request) throws LightningServiceException,
			TException {

		long logid = request.getLogid();
		DCCommonLanguge languageType = request.getLangugeType();
		int pageno = request.getPageno();
		int pagesize = request.getPagesize();
		
		LoggerUtility.noticeLog("[logid: %d] [request: {languageType: %d, pageno: %d, pagesize: %d}]",
							logid, languageType.getValue(), pageno, pagesize); 
		
		DCQueryTopicListResponse response = new DCQueryTopicListResponse();
		response.setTotalNum(0);
		response.setTopics(new ArrayList<DCConsistentTopic>());
		
		ConsistentTopicManager consistentTopicManager = controller.getConsistentTopicManager();
		IMResult<List<ConsistentTopicVO>> rc = consistentTopicManager
					.listConsistentTopics(languageType.getValue(), pageno * pagesize, pagesize);
		
		if ( rc.isSuccess() ) {
			List<ConsistentTopicVO> topicVOs = rc.getValue();
			for ( ConsistentTopicVO topicVO : topicVOs ) {
				DCConsistentTopic topic = new DCConsistentTopic();
				topic.setTopicId(topicVO.getTopicId());
				topic.setTopicName(topicVO.getTopicName());
				topic.setWords(topicVO.getWords());
				topic.setStartDate(topicVO.getStartDate());
				topic.setEndDate(topicVO.getEndDate());
				topic.setPercent(topicVO.getPercent());
				
				response.getTopics().add(topic);
			}
		} else {
		}
		
		return response;
		
	}

	@Override
	public DCQueryTopicTrendResponse queryTopicTrend(
			DCQueryTopicTrendRequest request) throws LightningServiceException,
			TException {

		long logid = request.getLogid();
		long topicId = request.getTopicId();
		
		LoggerUtility.noticeLog("[logid: %d] [request: {topicId: %d}]", logid, topicId);

		DCQueryTopicTrendResponse response = new DCQueryTopicTrendResponse();
		response.setTrends(new ArrayList<DCDateNumberTopicTrend>());
		
		ConsistentTopicManager consistentTopicManager = controller.getConsistentTopicManager();
		IMResult<List<ConsistentTopicTrendVO>> rc = consistentTopicManager.queryConsistentTopicTrends(topicId);
		if ( rc.isSuccess() ) {
			List<ConsistentTopicTrendVO> trendVOs = rc.getValue();
			for ( ConsistentTopicTrendVO trend : trendVOs ) {
				DCDateNumberTopicTrend dctrend = new DCDateNumberTopicTrend();
				dctrend.setDate(trend.getTimestamp());
				dctrend.setNumber(trend.getValue());
				
				response.getTrends().add(dctrend);
			}
			LoggerUtility.noticeLog("[response: {trend_size: %d}]", response.getTrends().size());
		} else {
			// TODO
		}
		
		return response;
		
	}

	@Override
	public DCQueryTopicAtTimeResponse queryTopicAtTime(
			DCQueryTopicAtTimeRequest request)
			throws LightningServiceException, TException {
		
		long logid = request.getLogid();
		long topicId = request.getTopicId();
		String timestamp = request.getTimestamp();
		int pageno = 0;
		int pagesize = 10;
		
		LoggerUtility.noticeLog("[logid: %d] [request: {topicId: %d, timestamp: %s}]", logid, topicId, timestamp);
		
		DCQueryTopicAtTimeResponse response = new DCQueryTopicAtTimeResponse();
		response.setWebpages(new ArrayList<DCWebPage>());
		
		ConsistentTopicManager consistentTopicManager = controller.getConsistentTopicManager();
		
		IMResult<List<ConsistentTopicWebPageVO>> rc = consistentTopicManager.queryTopicWebpageAtTime(topicId, timestamp, pageno, pagesize);
		if ( rc.isSuccess() ) {
			List<ConsistentTopicWebPageVO> webpageVOs = rc.getValue();
			for ( ConsistentTopicWebPageVO webpageVO : webpageVOs ) {
				DCWebPage dcwebpage = new DCWebPage();
				dcwebpage.setUrl(webpageVO.getUrl());
				dcwebpage.setSource(webpageVO.getSource());
				dcwebpage.setTitle(webpageVO.getTitle());
				dcwebpage.setTimestamp(webpageVO.getTimestamp());
				dcwebpage.setContent(webpageVO.getContent());
				
				response.getWebpages().add(dcwebpage);
			}
			LoggerUtility.noticeLog("[response: {webpages_size: %d}]", response.getWebpages().size());
		} else {
		}
		
		return response;
		
	}
	// -----------------------=

	@Override
	public DCQueryTopicWebpagesResponse queryTopicWebpages(
			DCQueryTopicWebpagesRequest request)
			throws LightningServiceException, TException {

		long logid = request.getLogid();
		long topicId = request.getTopicId();
		int pageno = request.getPageno();
		int pagesize = request.getPagesize();
		
		LoggerUtility.noticeLog("[logid: %d] [request: {topicId: %d, pageno: %d, pagesize: %d}]", 
					logid, topicId, pageno, pagesize);
		
		DCQueryTopicWebpagesResponse response = new DCQueryTopicWebpagesResponse();
		response.setWebpages(new ArrayList<DCWebPage>());
		
		ConsistentTopicManager consistentTopicManager = controller.getConsistentTopicManager();
		IMResult<List<ConsistentTopicWebPageVO>> rc = consistentTopicManager.queryTopicWebpage(topicId, pageno, pagesize);
		
		if ( rc.isSuccess() ) {
			List<ConsistentTopicWebPageVO> webpageVOs = rc.getValue();
			for ( ConsistentTopicWebPageVO webpageVO : webpageVOs ) {
				DCWebPage dcwebpage = new DCWebPage();
				dcwebpage.setUrl(webpageVO.getUrl());
				dcwebpage.setSource(webpageVO.getSource());
				dcwebpage.setTitle(webpageVO.getTitle());
				dcwebpage.setTimestamp(webpageVO.getTimestamp());
				dcwebpage.setContent(webpageVO.getContent());
				
				response.getWebpages().add(dcwebpage);
			}
		} else {
		}
		
		return response;
	}

}
