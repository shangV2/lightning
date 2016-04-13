package test.client;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qing.logiclayer.ContentType;
import com.qing.logiclayer.FightingServiceException;
import com.qing.logiclayer.Logiclayer;
import com.qing.logiclayer.OpenCommonLanguge;
import com.qing.logiclayer.OpenShortMessageContent;
import com.qing.logiclayer.OpenWebPageContent;
import com.qing.logiclayer.QueryOpenShortMessageContentRequest;
import com.qing.logiclayer.QueryOpenShortMessageContentResponse;
import com.qing.logiclayer.QueryOpenWebPageByDocidRequest;
import com.qing.logiclayer.QueryOpenWebPageByDocidResponse;
import com.qing.logiclayer.QueryTracePublicWordRequest;
import com.qing.logiclayer.QueryTracePublicWordResponse;
import com.qing.logiclayer.TraceId;
import com.qing.logiclayer.TracePublicWord;

public class ProblemTraceTest {

	private static Logiclayer.Client client = null;

	@BeforeClass
	public static void setUp() {
		TTransport transport = new TSocket("localhost", 8089);
		try {
			transport.open();
		} catch (TTransportException e) {
			e.printStackTrace();
		}

		TProtocol protocol = new TBinaryProtocol(transport);
		client = new Logiclayer.Client(protocol);
	}

	@Test
	public void testQueryShortMessage() {
		
		String query = "行动";
		try {
			QueryOpenShortMessageContentRequest request = new QueryOpenShortMessageContentRequest();
			request.setQuery(query);
			
			QueryOpenShortMessageContentResponse result = client.queryOpenShortMessageContent(request);
			int totalNumber = result.getTotalNum();
			System.out.println(totalNumber);
			for ( OpenShortMessageContent summary : result.getShortmessages() ) {
				System.out.println(summary.getDocid());
				System.out.println(summary.getMessage());
				System.out.println(summary.getRecvPhone());
				System.out.println(summary.getRecvUsername());
				System.out.println(summary.getSendPhone());
				System.out.println(summary.getSendUsermame());
				System.out.println(summary.getTimestamp());
				System.out.println("##################################################");
			}
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
//	
//	
//	
	@Test
	public void testQueryTracePublicWordCN() {
	
		QueryTracePublicWordRequest request = new QueryTracePublicWordRequest();
		
		request.setLangugeType(OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN);
		
		// 0, 49
		List<TraceId> traceIds = new ArrayList<TraceId>();
		
		TraceId traceId1 = new TraceId();
		traceId1.setContentType(ContentType.WEB_PAGE);
		traceId1.setDocid(4L);
		TraceId traceId2 = new TraceId();
		
		traceId2.setContentType(ContentType.WEB_PAGE);
		traceId2.setDocid(4L);
		traceIds.add(traceId1);
		traceIds.add(traceId2);
		
		request.setTraceIds(traceIds);
		try {
			QueryTracePublicWordResponse result = client.queryTracePublicWord(request);
			Iterator<TracePublicWord> iter = result.getPublicWordsIterator();
			while ( iter.hasNext() ) {
				TracePublicWord pw = iter.next();
				System.out.println(pw.getWord() + ":" + pw.getFreq());
			}
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testQueryTracePublicWordUy() {
	
		QueryTracePublicWordRequest request = new QueryTracePublicWordRequest();
		
		request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
		
		// 0, 49
		List<TraceId> traceIds = new ArrayList<TraceId>();
		
		TraceId traceId1 = new TraceId();
		traceId1.setContentType(ContentType.WEB_PAGE);
		traceId1.setDocid(4L);
		TraceId traceId2 = new TraceId();
		
		traceId2.setContentType(ContentType.WEB_PAGE);
		traceId2.setDocid(4L);
		traceIds.add(traceId1);
		traceIds.add(traceId2);
		
		request.setTraceIds(traceIds);
		try {
			QueryTracePublicWordResponse result = client.queryTracePublicWord(request);
			Iterator<TracePublicWord> iter = result.getPublicWordsIterator();
			while ( iter.hasNext() ) {
				TracePublicWord pw = iter.next();
				System.out.println(pw.getWord() + ":" + pw.getFreq());
			}
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testQueryTracePublicWordTibie() {
	
		QueryTracePublicWordRequest request = new QueryTracePublicWordRequest();
		
		request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
		
		// 0, 49
		List<TraceId> traceIds = new ArrayList<TraceId>();
		
		TraceId traceId1 = new TraceId();
		traceId1.setContentType(ContentType.WEB_PAGE);
		traceId1.setDocid(4L);
		TraceId traceId2 = new TraceId();
		
		traceId2.setContentType(ContentType.WEB_PAGE);
		traceId2.setDocid(4L);
		traceIds.add(traceId1);
		traceIds.add(traceId2);
		
		request.setTraceIds(traceIds);
		try {
			QueryTracePublicWordResponse result = client.queryTracePublicWord(request);
			Iterator<TracePublicWord> iter = result.getPublicWordsIterator();
			while ( iter.hasNext() ) {
				TracePublicWord pw = iter.next();
				System.out.println(pw.getWord() + ":" + pw.getFreq());
			}
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testQueryWebPageContentCn() {
		try {
			QueryOpenWebPageByDocidRequest request = new QueryOpenWebPageByDocidRequest();
			request.setLangugeType(OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN);
			request.setDocid(10L);
			QueryOpenWebPageByDocidResponse response = client.queryOpenWebPageByDocid(request);
			OpenWebPageContent wptc = response.getWebpage();
			System.out.println(wptc.getDocid());
			System.out.println(wptc.getUrl());
			System.out.println(wptc.getTitle());
			System.out.println(wptc.getContent());
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryWebPageContentUy() {
	
		try {
			QueryOpenWebPageByDocidRequest request = new QueryOpenWebPageByDocidRequest();
			request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
			request.setDocid(10L);
			QueryOpenWebPageByDocidResponse response = client.queryOpenWebPageByDocid(request);
			OpenWebPageContent wptc = response.getWebpage();
			System.out.println(wptc.getDocid());
			System.out.println(wptc.getUrl());
			System.out.println(wptc.getTitle());
			System.out.println(wptc.getContent());
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testQueryWebPageContentTibet() {
	
		try {
			QueryOpenWebPageByDocidRequest request = new QueryOpenWebPageByDocidRequest();
			request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
			request.setDocid(10L);
			QueryOpenWebPageByDocidResponse response = client.queryOpenWebPageByDocid(request);
			OpenWebPageContent wptc = response.getWebpage();
			System.out.println(wptc.getDocid());
			System.out.println(wptc.getUrl());
			System.out.println(wptc.getTitle());
			System.out.println(wptc.getContent());
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
//	
//	@Test
//	public void testQueryShortMessageContent() {
//	
//		long docid = 0L;
//		try {
//			ShortMessageTraceContent wptc = client.queryShortMessageTraceByDocid(docid);
//			System.out.println(wptc.getDocid());
//			System.out.println(wptc.getRecvPhone());
//			System.out.println(wptc.getSendPhone());
//			System.out.println(wptc.getMessage());
//			System.out.println(wptc.getTimestamp());
//		} catch (FightingServiceException e) {
//			e.printStackTrace();
//		} catch (TException e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	@Test
	public void testQueryOpenWebPageByDocid() {
		
		QueryOpenWebPageByDocidRequest request = new QueryOpenWebPageByDocidRequest();
		try {
			request.setDocid(10L);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN);
			QueryOpenWebPageByDocidResponse response = client.queryOpenWebPageByDocid(request);
			
			OpenWebPageContent pwpc = response.getWebpage();
			System.out.println(pwpc.getTimestamp());
			System.out.println(pwpc.getSource());
			System.out.println(pwpc.getTitle());
			
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	

}
