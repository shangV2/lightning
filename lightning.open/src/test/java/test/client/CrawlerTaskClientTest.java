package test.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

import com.qing.logiclayer.AddHitWebsiteCrawlerRequest;
import com.qing.logiclayer.FightingServiceException;
import com.qing.logiclayer.HitWebisteCrawler;
import com.qing.logiclayer.Logiclayer;
import com.qing.logiclayer.OpenCommonLanguge;
import com.qing.logiclayer.QueryHitWebsiteCrawlersRequest;
import com.qing.logiclayer.QueryHitWebsiteCrawlersResponse;
import com.qing.logiclayer.RemoveHitWebsiteCrawlerRequest;
import com.qing.logiclayer.RemoveHitWebsiteCrawlerResponse;
import com.qing.logiclayer.UpdateHitWebsiteCrawlerRequest;

public class CrawlerTaskClientTest {

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
	public void testAddWebCrawler() {
	
		AddHitWebsiteCrawlerRequest request = new AddHitWebsiteCrawlerRequest();
		try {
			
			request.setLangugeType(OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN);
			HitWebisteCrawler websiteCrawler = new HitWebisteCrawler();
			
			websiteCrawler.setLangugeType(OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN);
			websiteCrawler.setWebsite("tianshan");
			websiteCrawler.setSeedUrls(Arrays.asList(new String[] {"http://www.ts.cn/"}));
			websiteCrawler.setCrawlerNum(1000);
			
			websiteCrawler.setContentContentExp("<div class=\"txt link05\"> <p/> <div is_filter='yes'/></div>");
			websiteCrawler.setTitleContentExp("<div class=\"title\" />");
			websiteCrawler.setTimeContentExp("<div class=\"info link16\"><span is_filter='yes'/></div>");
			
			websiteCrawler.setHostUrlExp("[a-z]*\\.ts\\.cn");
			websiteCrawler.setNavigationUrlExp("navigation_url_exp");
			websiteCrawler.setContentUrlExp("content_(\\d)+.htm$");
			
			request.setWebsiteCrawler(websiteCrawler);
			
			client.addHitWebsiteCrawler(request);
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testRemoveWebCrawler() {
		try {
			RemoveHitWebsiteCrawlerRequest request = new RemoveHitWebsiteCrawlerRequest();
			request.setLangugeType(OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN);
			request.setWebid(1);
			
			client.removeHitWebsiteCrawler(request);
			
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryWebCrawler() {
		try {
			
			QueryHitWebsiteCrawlersRequest request = new QueryHitWebsiteCrawlersRequest();
			request.setLangugeType(OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN);
			request.setPageno(0);
			request.setPagesize(10);
			
			QueryHitWebsiteCrawlersResponse response = client.queryHitWebsiteCrawlers(request);
			
			System.out.println(response);
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateWebCrawler() {
		try {
			
			QueryHitWebsiteCrawlersRequest request = new QueryHitWebsiteCrawlersRequest();
			request.setLangugeType(OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN);
			request.setPageno(0);
			request.setPagesize(10);
			
			QueryHitWebsiteCrawlersResponse response = client.queryHitWebsiteCrawlers(request);
			
			System.out.println(response);
			
			List<HitWebisteCrawler> websiteCrawlers = response.getWebsiteCrawlers();
			if ( websiteCrawlers.size() > 0 ) {
				HitWebisteCrawler hwc = websiteCrawlers.get(0);
				
				hwc.setContentUrlExp(hwc.getContentContentExp() + "_update");
				hwc.setNavigationUrlExp(hwc.getNavigationUrlExp()  + "_update");
				hwc.setHostUrlExp(hwc.getHostUrlExp()  + "_update");
				
				hwc.setTitleContentExp(hwc.getTitleContentExp()  + "_update");
				hwc.setContentContentExp(hwc.getContentContentExp()  + "_update");
				hwc.setTimeContentExp(hwc.getTimeContentExp()  + "_update");
				
				hwc.setCrawlerNum(hwc.getCrawlerNum() + 1);
				
				UpdateHitWebsiteCrawlerRequest urequest = new UpdateHitWebsiteCrawlerRequest();
				urequest.setLangugeType(OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN);
				urequest.setWebsiteCrawler(hwc);
				client.updateHitWebsiteCrawler(urequest);
				
			}
			
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
	}
	
}
