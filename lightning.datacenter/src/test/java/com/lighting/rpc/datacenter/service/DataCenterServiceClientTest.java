package com.lighting.rpc.datacenter.service;

import java.util.Arrays;
import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lighting.rpc.common.exception.LightningServiceException;
import com.lighting.rpc.datacenter.model.DCAddSensitiveWordRequest;
import com.lighting.rpc.datacenter.model.DCAddSensitiveWordResponse;
import com.lighting.rpc.datacenter.model.DCCommonLanguge;
import com.lighting.rpc.datacenter.model.DCConsistentTopic;
import com.lighting.rpc.datacenter.model.DCCreateTopicRequest;
import com.lighting.rpc.datacenter.model.DCCreateTopicResponse;
import com.lighting.rpc.datacenter.model.DCDateNumberTopicTrend;
import com.lighting.rpc.datacenter.model.DCHotWebEvent;
import com.lighting.rpc.datacenter.model.DCQueryHotWebEventsRequest;
import com.lighting.rpc.datacenter.model.DCQueryHotWebEventsResponse;
import com.lighting.rpc.datacenter.model.DCQueryHotWebPagesRequest;
import com.lighting.rpc.datacenter.model.DCQueryHotWebPagesResponse;
import com.lighting.rpc.datacenter.model.DCQueryHotwordListRequest;
import com.lighting.rpc.datacenter.model.DCQueryHotwordListResponse;
import com.lighting.rpc.datacenter.model.DCQueryHotwordTrendRequest;
import com.lighting.rpc.datacenter.model.DCQueryHotwordTrendResponse;
import com.lighting.rpc.datacenter.model.DCQuerySensitiveWordTrendRequest;
import com.lighting.rpc.datacenter.model.DCQuerySensitiveWordTrendResponse;
import com.lighting.rpc.datacenter.model.DCQueryTopicAtTimeRequest;
import com.lighting.rpc.datacenter.model.DCQueryTopicAtTimeResponse;
import com.lighting.rpc.datacenter.model.DCQueryTopicListRequest;
import com.lighting.rpc.datacenter.model.DCQueryTopicListResponse;
import com.lighting.rpc.datacenter.model.DCQueryTopicTrendRequest;
import com.lighting.rpc.datacenter.model.DCQueryTopicTrendResponse;
import com.lighting.rpc.datacenter.model.DCQueryTopicWebpagesRequest;
import com.lighting.rpc.datacenter.model.DCQueryTopicWebpagesResponse;
import com.lighting.rpc.datacenter.model.DCRemoveHotWebEventsRequest;
import com.lighting.rpc.datacenter.model.DCRemoveTopicRequest;
import com.lighting.rpc.datacenter.model.DCRemoveTopicResponse;
import com.lighting.rpc.datacenter.model.DCTimeTrend;
import com.lighting.rpc.datacenter.model.DCWebPage;

public class DataCenterServiceClientTest {

	private DataCenterService.Client client = null;
	private TTransport transport = null;
	
	@Before
	public void setUp() {
		transport = new TSocket("localhost", 8020);
		try {
			transport.open();
		} catch (TTransportException e) {
			e.printStackTrace();
		}

		TProtocol protocol = new TBinaryProtocol(transport);
		client = new DataCenterService.Client(protocol);
	}

	@After
	public void tearDown() {
		if ( transport != null ) {
			transport.close();
		}
	}
	
	// @brief
	// -----------------------------------------------------------------------
	@Test
	public void testQueryHotWebEvents() {
		
		DCQueryHotWebEventsRequest request = new DCQueryHotWebEventsRequest();
		request.setLogid(10001L);
		request.setTimestamp("2014-09-04");
		
		try {
			DCQueryHotWebEventsResponse response = client.queryHotWebEvents(request);
			System.out.println("fetch a hotpoint web size: " + response.getHotEventsSize());
			for ( DCHotWebEvent event : response.getHotEvents() ) {
				System.out.println(event.getEventid());
				System.out.println(event.getTitle());
				System.out.println(event.getScore());
				System.out.println("###########################################");
			}
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testRemoveHotWebEvents() {
		
		try {
			DCRemoveHotWebEventsRequest request = new DCRemoveHotWebEventsRequest();
			request.setLogid(10001L);
			request.setTimestamp("2014-02-06");
			
			client.removeHotWebEvents(request);
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testQueryHotWebPages() {
		
		DCQueryHotWebEventsRequest request = new DCQueryHotWebEventsRequest();
		request.setLogid(10001L);
		request.setTimestamp("2014-09-04");
		
		try {
			DCQueryHotWebEventsResponse response = client.queryHotWebEvents(request);
			System.out.println("fetch a hotpoint web size: " + response.getHotEventsSize());
			for ( DCHotWebEvent event : response.getHotEvents() ) {
				System.out.println(event.getEventid());
				System.out.println(event.getTitle());
				System.out.println(event.getScore());
				System.out.println("----------------------------------------------------------------");
				
				DCQueryHotWebPagesRequest dcrequest = new DCQueryHotWebPagesRequest();
				dcrequest.setEventid(event.getEventid());
				dcrequest.setLogid(10000L);
				
				DCQueryHotWebPagesResponse dcresponse = client.queryHotWebPages(dcrequest);
				System.out.println(dcresponse.getTotalNum());
				List<DCWebPage> webpages = dcresponse.getWebPages();
				for ( DCWebPage webpage : webpages ) {
					System.out.println(webpage.getUrl());
					System.out.println(webpage.getSource());
					System.out.println(webpage.getTitle());
					System.out.println(webpage.getTimestamp());
//					System.out.println(webpage.getContent());
					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				}
				
				System.out.println("###########################################");
			}
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testQueryHotwordList() {
		
		DCQueryHotwordListRequest request = new DCQueryHotwordListRequest();
		request.setLogid(10001L);
		request.setTimestamp("2014-09-04");
		
		try {
			DCQueryHotwordListResponse response = client.queryHotwordList(request);
			for ( String word : response.getWords() ) {
				System.out.println(word);
			}
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test 
	public void testAddSensitiveWord() {
		
		String[] words = new String[] {
				"伊扎布特",
				"伊吉拉特",
				"圣战",
				"异教徒",
				"叛教者",
				"异教徒",
				"主命",
				"宗教至上",
				"圣战",
				"推翻政府",
				"讲经",
				"修道班",
				"神学班",
				"麦西来甫",
				"台比力克",
				"朝觐",
				"宗教课税",
				"吉里巴甫",
				"尼卡",
				"小花帽",
				"大胡子",
				"蒙面纱",
				"母语幼儿园",
				"双语教育",
				"穆斯林",
				"古兰经",
				"东突",
				"突厥",
				"东突厥斯坦",
				"安全时间",
				"民族压迫",
				"内地新疆班",
				"援疆",
				"维吾尔小偷",
				"清真与非清真",
				"7.5",
				"真主",
				"黑大爷",
				"世维会",
				"热比娅",
				"疆独",
				"藏独",
				"民族自决",	
		};
		
		for ( String word : words ) {
		
			DCAddSensitiveWordRequest request = new DCAddSensitiveWordRequest();
			request.setLogid(1000L);
			request.setLangugeType(DCCommonLanguge.DC_LAN_CHINESE_ZH_CN);
			request.setWord(word);
			
			try {
				DCAddSensitiveWordResponse response = client.addSensitiveWord(request);
			} catch (LightningServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		
		}
		
		
		
	}
	
	@Test
	public void testQueryHotwordTrend() {
		
		DCQueryHotwordTrendRequest request = new DCQueryHotwordTrendRequest();
		request.setWord("新疆");
		request.setStartDate("2014-09-01");
		request.setEndDate("2014-09-10");
			
		try {
			DCQueryHotwordTrendResponse response = client.queryHotwordTrend(request);
			for ( DCTimeTrend vo : response.getTrends() ) {
				System.out.println(vo.getTimestamp() + ":" + vo.getFreq());
			}
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	// -------------------------------------------------------------------------------------------------------
	
	@Test
	public void testQuerySensistiveTrend() {
		
		
		DCQuerySensitiveWordTrendRequest request = new DCQuerySensitiveWordTrendRequest();
		request.setLogid(10001L);
		request.setStartDate("2014-09-01");
		request.setEndDate("2014-09-10");
		request.setWord("新疆");
		
		try {
			DCQuerySensitiveWordTrendResponse response = client.querySensitiveWordTrend(request);
			
			for ( DCTimeTrend trend : response.getTrends() ) {
				System.out.println(trend.getTimestamp() + ":" + trend.getFreq());
			}
			
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	// ---------------------------------------------------------------------------------------------------------
	
	@Test
	public void testCreateTopic() {
		
		String[] topicNames = {
				"神权政治",
				"宗教至上", 
				"异教徒",
				"圣战",
		};
		
		String[][] topicWords = {
				{ "伊斯兰政权", "真主", "唯一", "不服从", "政府" },
				{ "古兰经", "圣训", "制定", "衡量", "判断", "国家", "法律", "经济", "现代文明" },
				{ "安拉", "异教徒", "叛教者", "孤立", "恐吓", "杀死", "砍死", "武器" },
				{ "圣战", "主命", "政府", "推翻", "杀死", "武器" },
				{ "主持仪式", "神学班", "修道班", "地点", "婚礼", "葬礼", "聚会", "麦西来甫", "台比力克", "孩子", "隐蔽", "政府" }
		};
	
		try {
			
			for ( int i = 0; i < topicNames.length; i++ ) {
			
				DCCreateTopicRequest request = new DCCreateTopicRequest();
				request.setLogid(10001L);
				request.setLangugeType(DCCommonLanguge.DC_LAN_CHINESE_ZH_CN);
				DCConsistentTopic topic = new DCConsistentTopic();
				topic.setTopicName(topicNames[i]);
//				topic.setWords(Arrays.asList(new String[] { "伊斯兰政权", "真主", "唯一", "不服从", "政府" }));
				topic.setWords(Arrays.asList(topicWords[i]));
				topic.setStartDate("2014-09-01");
				topic.setEndDate("2014-12-31");
				topic.setPercent(1);
				request.setTopic(topic);
				
				DCCreateTopicResponse response = client.createConsistentTopic(request);
				DCConsistentTopic topic2 = response.getTopic();
				System.out.println(topic2.getTopicId());
				
			}
			
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testRemoveTopics() {
		
		
		long topicId = 0L;
		do {
			DCCreateTopicRequest request = new DCCreateTopicRequest();
			request.setLogid(10001L);
			
			DCConsistentTopic topic = new DCConsistentTopic();
			topic.setTopicName("春晚自转女");
			topic.setWords(Arrays.asList(new String[] {"春晚", "自转"}));
			topic.setStartDate("2014-01-10");
			topic.setEndDate("2014-02-10");
			topic.setPercent(1);
			request.setTopic(topic);
			
			request.setTopic(topic);
			try {
				DCCreateTopicResponse response = client.createConsistentTopic(request);
				topicId = response.getTopic().getTopicId();
			} catch (LightningServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
			
		} while (false);
		
		System.out.println("topicId: " + topicId);
		
		do {
			try {
				DCRemoveTopicRequest request = new DCRemoveTopicRequest();
				request.setTopicId(topicId);
				DCRemoveTopicResponse response = client.removeConsistentTopic(request);
				System.out.println("success_amount: " + response.getSuccessAmount());
			} catch (LightningServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		} while (false);
		
	}
	
	@Test
	public void testQueryTopics() {
		
		DCQueryTopicListRequest request = new DCQueryTopicListRequest();
		request.setLangugeType(DCCommonLanguge.DC_LAN_CHINESE_ZH_CN);
		request.setLogid(10001L);
		
		try {
			DCQueryTopicListResponse response = client.queryTopicList(request);
			List<DCConsistentTopic> topics = response.getTopics();
			for ( DCConsistentTopic topic : topics ) {
				System.out.println(topic.getTopicId() + ":" + topic.getStartDate() + "xxxxxx" + topic.getEndDate() + "--》" + topic.getTopicName());
			}
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testQueryTopicTrends() {
		
		DCQueryTopicTrendRequest request = new DCQueryTopicTrendRequest();
		request.setLogid(1000L);
		request.setTopicId(2147483537L);
		
		try {
			DCQueryTopicTrendResponse response = client.queryTopicTrend(request);
			for ( DCDateNumberTopicTrend trend : response.getTrends() ) {
				System.out.println(trend.getDate() + ":" + trend.getNumber());
			}
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testQueryTopicWebpage() {
		
		long topicId = 2147483537L;
		
		DCQueryTopicWebpagesRequest request = new DCQueryTopicWebpagesRequest();
		request.setLogid(1001L);
		request.setLangugeType(DCCommonLanguge.DC_LAN_CHINESE_ZH_CN);
		request.setTopicId(topicId);
		
		try {
			DCQueryTopicWebpagesResponse response = client.queryTopicWebpages(request);
			
			for ( DCWebPage webpage : response.getWebpages() ) {
				System.out.println(webpage.getUrl());
				System.out.println(webpage.getTitle());
				System.out.println(webpage.getContent());
			}
			
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testQueryTopicWebpageAtTimestamp() {
		
		// TO Modify something
		long topicId = 2147483537L;
		
//		DCQueryTopicWebpagesRequest request = new DCQueryTopicWebpagesRequest();
//		request.setLogid(1001L);
//		request.setLangugeType(DCCommonLanguge.DC_LAN_CHINESE_ZH_CN);
//		request.setTopicId(topicId);
		
		DCQueryTopicAtTimeRequest request = new DCQueryTopicAtTimeRequest();
		request.setLogid(1001L);
		request.setTopicId(topicId);
		request.setTimestamp("2014-09-04");
		
		try {
		
			DCQueryTopicAtTimeResponse response = client.queryTopicAtTime(request);
			
			for ( DCWebPage webpage : response.getWebpages() ) {
				System.out.println(webpage.getUrl());
				System.out.println(webpage.getTitle());
				System.out.println(webpage.getContent());
			}
			
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
}
