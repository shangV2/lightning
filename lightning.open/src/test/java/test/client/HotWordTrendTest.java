package test.client;

import java.util.ArrayList;
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

import com.qing.logiclayer.FightingServiceException;
import com.qing.logiclayer.HotWebEvent;
import com.qing.logiclayer.HotWebPageContent;
import com.qing.logiclayer.Logiclayer;
import com.qing.logiclayer.OpenCommonLanguge;
import com.qing.logiclayer.QueryHotWebEventRequest;
import com.qing.logiclayer.QueryHotWebEventResponse;
import com.qing.logiclayer.QueryHotWebPageByEventRequest;
import com.qing.logiclayer.QueryHotWebPageByEventResponse;

public class HotWordTrendTest {

	private Logiclayer.Client client = null;
	private TTransport transport = null;
	
	@Before
	public void setUp() {
		transport = new TSocket("localhost", 8089);
		try {
			transport.open();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
		TProtocol protocol = new TBinaryProtocol(transport);
		client = new Logiclayer.Client(protocol);
	}

	@After
	public void tearDown() {
		if ( transport != null ) {
			transport.close();
		}
	}
	
	@Test
	public void testQueryHotWebEvent() {

		QueryHotWebEventRequest request = new QueryHotWebEventRequest();
		request.setLangugeType(OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN);
		request.setTimestamp("2014-03-30");
		try {
			QueryHotWebEventResponse response = client.queryHotWebEvent(request);
			List<HotWebEvent> events = response.getEvents();
			for ( HotWebEvent event : events ) {
				System.out.println(event.getEventid());
				System.out.println(event.getTitle());
				System.out.println(event.getScore());
			}
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testQueryHotWebPageByEvent() {
	
		List<String> eventids = new ArrayList<String>();
		do {
			QueryHotWebEventRequest request = new QueryHotWebEventRequest();
			request.setLangugeType(OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN);
			request.setTimestamp("2014-03-30");
			try {
				QueryHotWebEventResponse response = client.queryHotWebEvent(request);
				List<HotWebEvent> events = response.getEvents();
				for ( HotWebEvent event : events ) {
					System.out.println(event.getEventid());
					System.out.println(event.getTitle());
					System.out.println(event.getScore());
					eventids.add(event.getEventid());
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		} while (false);
		
		do {
			for ( String eventid : eventids ) {
				QueryHotWebPageByEventRequest request = new QueryHotWebPageByEventRequest();
				request.setEventid(eventid);
				request.setLangugeType(OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN);
				System.out.println("eventid:" + eventid);
				try {
					QueryHotWebPageByEventResponse response = client.queryHotWebPageByEvent(request);
					List<HotWebPageContent> hwpcs = response.getWebpages();
					for ( HotWebPageContent hwpc : hwpcs ) {
						System.out.println(hwpc.getUrl());
						System.out.println(hwpc.getSource());
						System.out.println(hwpc.getTitle());
						System.out.println(hwpc.getTimestamp());
						System.out.println(hwpc.getContent());
					}
				} catch (FightingServiceException e) {
					e.printStackTrace();
				} catch (TException e) {
					e.printStackTrace();
				}
			}
		} while (false);
		
	
	}
	
	
}
