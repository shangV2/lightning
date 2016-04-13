package test.client;

import java.util.Arrays;
import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lighting.rpc.datacenter.model.DCRemoveTopicRequest;
import com.qing.logiclayer.ConsistentTopic;
import com.qing.logiclayer.CreateConsistentTopicRequest;
import com.qing.logiclayer.CreateConsistentTopicResponse;
import com.qing.logiclayer.DateNumberTopicTrend;
import com.qing.logiclayer.FightingServiceException;
import com.qing.logiclayer.ListConsistentTopicsRequest;
import com.qing.logiclayer.ListConsistentTopicsResponse;
import com.qing.logiclayer.ListShortMessageForTopicRequest;
import com.qing.logiclayer.ListShortMessageForTopicResponse;
import com.qing.logiclayer.ListShortMessageForTopicWithPhoneRequest;
import com.qing.logiclayer.ListShortMessageForTopicWithPhoneResponse;
import com.qing.logiclayer.ListWebPageForTopicRequest;
import com.qing.logiclayer.ListWebPageForTopicResponse;
import com.qing.logiclayer.Logiclayer;
import com.qing.logiclayer.OpenCommonLanguge;
import com.qing.logiclayer.OpenShortMessageContent;
import com.qing.logiclayer.OpenWebPageContent;
import com.qing.logiclayer.QueryShortMessageForTopicAtTimestampRequest;
import com.qing.logiclayer.QueryShortMessageForTopicAtTimestampResponse;
import com.qing.logiclayer.QueryShortMessageTrendForTopicRequest;
import com.qing.logiclayer.QueryShortMessageTrendForTopicResponse;
import com.qing.logiclayer.QueryWebPageForTopicAtTimestampRequest;
import com.qing.logiclayer.QueryWebPageForTopicAtTimestampResponse;
import com.qing.logiclayer.QueryWebPageTrendForTopicRequest;
import com.qing.logiclayer.QueryWebPageTrendForTopicResponse;
import com.qing.logiclayer.RemoveConsistentTopicRequest;

public class ConsistentTopicTest {

	private static Logiclayer.Client client = null;

	@BeforeClass
	public static void setUp() {
		TTransport transport = new TSocket("localhost", 9090);
		try {
			transport.open();
		} catch (TTransportException e) {
			e.printStackTrace();
		}

		TProtocol protocol = new TBinaryProtocol(transport);
		client = new Logiclayer.Client(protocol);
	}
	
	/*
	 * @brief 	webpage topic
	 */
	@Test
	public void testCreateTopicCn() {
		String topicName = "topic_blog";
		String startdate = "20140101";
		String enddate = "20140210";
		int percents = 1;
		List<String> words = Arrays.asList(new String[] {
				"大雾", "广告", "谋杀"
		});
		try {
			ConsistentTopic topic = new ConsistentTopic();
			topic.setTopicName(topicName);
			topic.setWords(words);
			topic.setStartDate(startdate);
			topic.setEndDate(enddate);
			topic.setPercent(percents);
			
			CreateConsistentTopicRequest request = new CreateConsistentTopicRequest();
			request.setTopic(topic);
			
			CreateConsistentTopicResponse response = client.createConsistentTopic(request);
			System.out.println(response.getTopic().getTopicId());
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRemoveTopicCN() {
		long topicId = 2147483645L;
		RemoveConsistentTopicRequest request = new RemoveConsistentTopicRequest();
		request.setTopicId(topicId);
//		client.removeConsistentTopic(request);
		try {
			client.removeConsistentTopic(request);
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testCreateTopicUy() {
		String topicName = "topic3";
		String startdate = "20131001";
		String enddate = "20131228";
		int percents = 1;
		List<String> words = Arrays.asList(new String[] {
				"گېزىت", "مۇئە", "مىچېل"
		});
		try {
			ConsistentTopic topic = new ConsistentTopic();
			topic.setTopicName(topicName);
			topic.setWords(words);
			topic.setStartDate(startdate);
			topic.setEndDate(enddate);
			topic.setPercent(percents);
			
			CreateConsistentTopicRequest request = new CreateConsistentTopicRequest();
			request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
			request.setTopic(topic);
			
			CreateConsistentTopicResponse response = client.createConsistentTopic(request);
			System.out.println(response.getTopic().getTopicId());
		} catch (TException e) {
			e.printStackTrace();
		}
		// 8511859219695438447
		
	}
	
	@Test
	public void testCreateTopicTibet() {
		String topicName = "topic3";
		String startdate = "20131001";
		String enddate = "20131228";
		int percents = 1;
		List<String> words = Arrays.asList(new String[] {
				"བོད་་", "ག", "ཀྱི་གློ"
		});
		try {
			ConsistentTopic topic = new ConsistentTopic();
			topic.setTopicName(topicName);
			topic.setWords(words);
			topic.setStartDate(startdate);
			topic.setEndDate(enddate);
			topic.setPercent(percents);
			
			CreateConsistentTopicRequest request = new CreateConsistentTopicRequest();
			request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
			request.setTopic(topic);
			
			CreateConsistentTopicResponse response = client.createConsistentTopic(request);
			System.out.println(response.getTopic().getTopicId());
		} catch (TException e) {
			e.printStackTrace();
		}
		// 8511859219695438447
		
	}

	
	@Test
	public void testListTopicsCN() {
		
		try {
			
			ListConsistentTopicsRequest request = new ListConsistentTopicsRequest();
			request.setLangugeType(OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN);
			request.setPageno(0);
			request.setPagesize(10);
			ListConsistentTopicsResponse response = client.listConsistentTopics(request);
			
			for ( ConsistentTopic topic : response.getTopics() ) {
				System.out.println(topic.getTopicName() + ":" + topic.getTopicId());
				System.out.println(topic.getStartDate() + ":" + topic.getEndDate());
				for ( String word : topic.getWords() ) {
					System.out.print(word + "\t");
				}
				System.out.println();
				System.out.println("----------------------------------------------");
			}
			
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testListTopicsUY() {
		
		try {
			
			ListConsistentTopicsRequest request = new ListConsistentTopicsRequest();
			request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
			request.setPageno(0);
			request.setPagesize(10);
			ListConsistentTopicsResponse response = client.listConsistentTopics(request);
			
			for ( ConsistentTopic topic : response.getTopics() ) {
				System.out.println(topic.getTopicName() + ":" + topic.getTopicId());
				System.out.println(topic.getStartDate() + ":" + topic.getEndDate());
				for ( String word : topic.getWords() ) {
					System.out.print(word + "\t");
				}
				System.out.println();
				System.out.println("----------------------------------------------");
			}
			
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testListTopicsTibet() {
		
		try {
			
			ListConsistentTopicsRequest request = new ListConsistentTopicsRequest();
			request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
			request.setPageno(0);
			request.setPagesize(10);
			ListConsistentTopicsResponse response = client.listConsistentTopics(request);
			
			for ( ConsistentTopic topic : response.getTopics() ) {
				System.out.println(topic.getTopicName() + ":" + topic.getTopicId());
				System.out.println(topic.getStartDate() + ":" + topic.getEndDate());
				for ( String word : topic.getWords() ) {
					System.out.print(word + "\t");
				}
				System.out.println();
				System.out.println("----------------------------------------------");
			}
			
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void removeWebpageTopic() {
		
		String topicName = "remove_topic";
		String startdate = "20131127";
		String enddate = "20131130";
		int percents = 1;
		List<String> words = Arrays.asList(new String[] {
				"进展", "活动", "新疆"
		});
		
		CreateConsistentTopicResponse response = new CreateConsistentTopicResponse();
		try {
			ConsistentTopic topic = new ConsistentTopic();
			topic.setTopicName(topicName);
			topic.setWords(words);
			topic.setStartDate(startdate);
			topic.setEndDate(enddate);
			topic.setPercent(percents);
			
			CreateConsistentTopicRequest request = new CreateConsistentTopicRequest();
			request.setTopic(topic);
			
			response = client.createConsistentTopic(request);
			System.out.println(response.getTopic().getTopicId());
		} catch (TException e) {
			e.printStackTrace();
		}
//		
		System.out.println("---------------");
		
		try {
			RemoveConsistentTopicRequest request = new RemoveConsistentTopicRequest();
			request.setTopicId(response.getTopic().getTopicId());
			client.removeConsistentTopic(request);
		} catch(TException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void removeUYWebpageTopic() {
		
		String topicName = "remove_topic";
		String startdate = "20131127";
		String enddate = "20131130";
		int percents = 1;
		List<String> words = Arrays.asList(new String[] {
				"进展", "活动", "新疆"
		});
		
		CreateConsistentTopicResponse response = new CreateConsistentTopicResponse();
		try {
			ConsistentTopic topic = new ConsistentTopic();
			topic.setTopicName(topicName);
			topic.setWords(words);
			topic.setStartDate(startdate);
			topic.setEndDate(enddate);
			topic.setPercent(percents);
			
			CreateConsistentTopicRequest request = new CreateConsistentTopicRequest();
			request.setTopic(topic);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
			
			response = client.createConsistentTopic(request);
			System.out.println(response.getTopic().getTopicId());
		} catch (TException e) {
			e.printStackTrace();
		}
//		
		System.out.println("---------------");
		
		try {
			RemoveConsistentTopicRequest request = new RemoveConsistentTopicRequest();
			request.setTopicId(response.getTopic().getTopicId());
			request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
			client.removeConsistentTopic(request);
		} catch(TException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryWebPageTrendForTopic() {
		
		long topicId = 2147483561L;
		
		try {
			QueryWebPageTrendForTopicRequest request = new QueryWebPageTrendForTopicRequest();
			request.setTopicId(topicId);
			
			QueryWebPageTrendForTopicResponse response = client.queryWebPageTrendForTopic(request);
			
			for ( DateNumberTopicTrend trend : response.getTrends() ) {
				System.out.println(trend.getDate() + ":" + trend.getNumber());
			}
			
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testQueryWebPageTrendForTopicUY() {
		
		long topicId = 5103045988229650542L;
		
		try {
			QueryWebPageTrendForTopicRequest request = new QueryWebPageTrendForTopicRequest();
			request.setTopicId(topicId);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
			
			QueryWebPageTrendForTopicResponse response = client.queryWebPageTrendForTopic(request);
			
			for ( DateNumberTopicTrend trend : response.getTrends() ) {
				System.out.println(trend.getDate() + ":" + trend.getNumber());
			}
			
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testQueryWebPageTrendForTopicTibet() {
		
		long topicId = 7540281081695586703L;
		
		do {
			ListConsistentTopicsRequest request = new ListConsistentTopicsRequest();
			request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
			request.setPageno(0);
			request.setPagesize(10);
			ListConsistentTopicsResponse response = null;
			try {
				response = client.listConsistentTopics(request);
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
			for ( ConsistentTopic topic : response.getTopics() ) {
				topicId = topic.getTopicId();
			}
		} while (false);
		
		// --------------------------------------------------
		
		do {
			try {
				QueryWebPageTrendForTopicRequest request = new QueryWebPageTrendForTopicRequest();
				request.setTopicId(topicId);
				request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
				
				QueryWebPageTrendForTopicResponse response = client.queryWebPageTrendForTopic(request);
				
				for ( DateNumberTopicTrend trend : response.getTrends() ) {
					System.out.println(trend.getDate() + ":" + trend.getNumber());
				}
				
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		} while(false);
		
	}
	
	
	@Test
	public void testListWebPageForTopicCn() {
		
		long topicId = 2147483561L;
		
		try {
			
			ListWebPageForTopicRequest request = new ListWebPageForTopicRequest();
			request.setTopicId(topicId);
			ListWebPageForTopicResponse response = client.listWebPageForTopic(request);
			for ( OpenWebPageContent item : response.getWebpages() ) {
				System.out.println(item.getTimestamp());
				System.out.println(item.getSource());
				System.out.println(item.getContent());
				System.out.println(item.getUrl());
				System.out.println(item.getTitle());
				System.out.println("--------------------------");
			}
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testListWebPageForTopicUy() {
		
		long topicId = 5103045988229650542L;
		
		try {
			
			ListWebPageForTopicRequest request = new ListWebPageForTopicRequest();
			request.setTopicId(topicId);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
			ListWebPageForTopicResponse response = client.listWebPageForTopic(request);
			for ( OpenWebPageContent item : response.getWebpages() ) {
				System.out.println(item.getTimestamp());
				System.out.println(item.getSource());
				System.out.println(item.getContent());
				System.out.println(item.getUrl());
				System.out.println(item.getTitle());
				System.out.println("--------------------------");
			}
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testListWebPageForTopicTibet() {
		
		long topicId = 5103045988229650542L;
		try {
			ListConsistentTopicsRequest request = new ListConsistentTopicsRequest();
			request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
			request.setPageno(0);
			request.setPagesize(10);
			ListConsistentTopicsResponse response = client.listConsistentTopics(request);
			for ( ConsistentTopic topic : response.getTopics() ) {
				topicId = topic.getTopicId();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//----------------------------------------------------------------
		
		try {
			
			ListWebPageForTopicRequest request = new ListWebPageForTopicRequest();
			request.setTopicId(topicId);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
			ListWebPageForTopicResponse response = client.listWebPageForTopic(request);
			for ( OpenWebPageContent item : response.getWebpages() ) {
				System.out.println(item.getTimestamp());
				System.out.println(item.getSource());
				System.out.println(item.getContent());
				System.out.println(item.getUrl());
				System.out.println(item.getTitle());
				System.out.println("--------------------------");
			}
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	@Test
	public void testQueryWebPageAtTimestampCn() {
		
		long topicId = 2147483561L;
		QueryWebPageForTopicAtTimestampRequest request = new QueryWebPageForTopicAtTimestampRequest();
		request.setTimestamp("20140206");
		request.setTopicId(topicId);
		try {
			QueryWebPageForTopicAtTimestampResponse response = client.queryWebPageForTopicAtTimestamp(request);
			for ( OpenWebPageContent item : response.getWebpages() ) {
				System.out.println(item.getUrl());
				System.out.println(item.getTitle());
				System.out.println(item.getContent());
			}
		} catch (FightingServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testQueryWebPageAtTimestampUy() {
		
		long topicId = 8511859219695438447L;
		QueryWebPageForTopicAtTimestampRequest request = new QueryWebPageForTopicAtTimestampRequest();
		request.setTimestamp("20131025");
		request.setTopicId(topicId);
		request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
		try {
			QueryWebPageForTopicAtTimestampResponse response = client.queryWebPageForTopicAtTimestamp(request);
			for ( OpenWebPageContent item : response.getWebpages() ) {
				System.out.println(item.getUrl());
				System.out.println(item.getTitle());
				System.out.println(item.getContent());
			}
		} catch (FightingServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testQueryWebPageAtTimestampTibet() {
		
		long topicId = 8511859219695438447L;
		
		do {
			try {
				ListConsistentTopicsRequest request = new ListConsistentTopicsRequest();
				request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
				request.setPageno(0);
				request.setPagesize(10);
				ListConsistentTopicsResponse response = client.listConsistentTopics(request);
				for ( ConsistentTopic topic : response.getTopics() ) {
					topicId = topic.getTopicId();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} while (false);
		
		do {
			QueryWebPageForTopicAtTimestampRequest request = new QueryWebPageForTopicAtTimestampRequest();
			request.setTimestamp("20131127");
			request.setTopicId(topicId);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
			try {
				QueryWebPageForTopicAtTimestampResponse response = client.queryWebPageForTopicAtTimestamp(request);
				for ( OpenWebPageContent item : response.getWebpages() ) {
					System.out.println(item.getTimestamp());
					System.out.println(item.getUrl());
					System.out.println(item.getTitle());
					System.out.println(item.getContent());
				}
			} catch (FightingServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while(false);
		
	}
	
	
	@Test
	public void testListShortMessageForTopic() {

		long topicId = 150995459L;
		
		try {
		
			ListShortMessageForTopicRequest request = new ListShortMessageForTopicRequest();
			request.setTopicId(topicId);
			
			ListShortMessageForTopicResponse response = client.listShortMessageForTopic(request);
			
			System.out.println(response.getTotalNum());
			for ( OpenShortMessageContent item : response.getShortmessages() ) {
				System.out.println(item.getTimestamp());
				System.out.println(item.getMessage());
				System.out.println(item.getRecvPhone());
				System.out.println(item.getSendPhone());
			}
			
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testListShortMessageForTopicWithPhone() {
		
		ListShortMessageForTopicWithPhoneRequest request = new ListShortMessageForTopicWithPhoneRequest();
		
		long topicId = 150995459L;
		request.setTopicId(topicId);
		request.setPhone("13091827364");
		
		try {
			ListShortMessageForTopicWithPhoneResponse response = client.listShortMessageForTopicWithPhone(request);
			for ( OpenShortMessageContent item : response.getShortmessages() ) {
				System.out.println(item.getTimestamp());
				System.out.println(item.getMessage());
				System.out.println(item.getRecvPhone());
				System.out.println(item.getSendPhone());
			}
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testQueryShortMessageTrendForTopic() {
		
		long topicId = 150995459L;
		
		QueryShortMessageTrendForTopicRequest request = new QueryShortMessageTrendForTopicRequest();
		request.setTopicId(topicId);
		
		try {
			QueryShortMessageTrendForTopicResponse response = client.queryShortMessageTrendForTopic(request);
			for ( DateNumberTopicTrend trend : response.getTrends() ) {
				System.out.println(trend.getDate() + ":" + trend.getNumber());
			}
		} catch (FightingServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testQueryShortMessageForTopicAtTimestamp() {
		
		QueryShortMessageForTopicAtTimestampRequest request = new QueryShortMessageForTopicAtTimestampRequest();
		long topicId = 150995459L;
		request.setTopicId(topicId);
		request.setTimestamp("20131212");
		
		try {
			QueryShortMessageForTopicAtTimestampResponse response = client.queryShortMessageForTopicAtTimestamp(request);
			for ( OpenShortMessageContent item : response.getShortmessages() ) {
				System.out.println(item.getRecvPhone());
				System.out.println(item.getSendPhone());
				System.out.println(item.getTimestamp());
				System.out.println(item.getMessage());
			}
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
}
