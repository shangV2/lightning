package com.lightning.webmetaserver.rpc.handler;

import java.util.Arrays;

import org.apache.thrift.TException;
import org.junit.Test;

import com.lighting.rpc.common.exception.LightningServiceException;
import com.lighting.rpc.core.client.DynamicClientProxy;
import com.lighting.rpc.core.configure.RpcServerConfiguration;
import com.lighting.rpc.core.model.ServerNode;
import com.lighting.rpc.webmetaserver.model.WMSApplyCrawlerTaskRequest;
import com.lighting.rpc.webmetaserver.model.WMSApplyCrawlerTaskResponse;
import com.lighting.rpc.webmetaserver.model.WMSCrawlerWebsite;
import com.lighting.rpc.webmetaserver.model.WMSCreateCrawlerWebsiteRequest;
import com.lighting.rpc.webmetaserver.model.WMSCreateCrawlerWebsiteResponse;
import com.lighting.rpc.webmetaserver.model.WMSQueryCrawlerTaskRequest;
import com.lighting.rpc.webmetaserver.model.WMSQueryCrawlerTaskResponse;
import com.lighting.rpc.webmetaserver.model.WMSTaskStatus;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteContentRuleType;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteCrawlerType;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteType;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteUrlRuleType;
import com.lighting.rpc.webmetaserver.service.WebMetaServerService;

public class WebMetaServerServiceClientTest {
	
	
	@Test
	public void testQueryCrawlerWebsite() {
		RpcServerConfiguration configuration = new RpcServerConfiguration();
		configuration.getServerNodes().add(new ServerNode("127.0.0.1", 8030));
		configuration.setTimeout(1000 * 5);
		
		DynamicClientProxy<WebMetaServerService.Client> proxy = new DynamicClientProxy<WebMetaServerService.Client>();
		WebMetaServerService.Iface client = (WebMetaServerService.Iface)proxy.createProxy(WebMetaServerService.Client.class, configuration);
		
		WMSQueryCrawlerTaskRequest request = new WMSQueryCrawlerTaskRequest();
		request.setLogid(1000L);
		request.setPageno(0);
		request.setPagesize(10);
		try {
			WMSQueryCrawlerTaskResponse response = client.queryCrawlerTask(request);
			System.out.println(response.toString());
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateCrawlerWebsite() {
//		fail("Not yet implemented");
		
		RpcServerConfiguration configuration = new RpcServerConfiguration();
		configuration.getServerNodes().add(new ServerNode("127.0.0.1", 9000));
		configuration.setTimeout(1000 * 5);
		
		DynamicClientProxy<WebMetaServerService.Client> proxy = new DynamicClientProxy<WebMetaServerService.Client>();
		WebMetaServerService.Iface client = (WebMetaServerService.Iface)proxy.createProxy(WebMetaServerService.Client.class, configuration);
		
		try {
			
			WMSCreateCrawlerWebsiteRequest request = new WMSCreateCrawlerWebsiteRequest();
			request.setLogid(10000L);
			WMSCrawlerWebsite webinfo = new WMSCrawlerWebsite();
			webinfo.setWebsite("tianshan");
			webinfo.setWebsiteType(WMSWebsiteType.WMS_WEBSITE_ZH_CN);
			
			webinfo.setCrawlerType(WMSWebsiteCrawlerType.WMS_WEBSITE_CRAWLER_STAND_ALONE);
			webinfo.setCrawlerSchedule(24);
			webinfo.setCrawlerNum(200);
			webinfo.setContentType(WMSWebsiteContentRuleType.WMS_WEBSITE_CONTENT_RULE_TEMPLATE_HPATH);
			webinfo.setContentRule("content_rule");
			
			webinfo.setUrlType(WMSWebsiteUrlRuleType.WMS_WEBSITE_URL_RULE_HIT);
			webinfo.setUrlRule("url_rule");
			
			webinfo.setSeeds(Arrays.asList(new String[] {
					"http://www.ts.cn/"
			}));
			request.setWebinfo(webinfo);

			WMSCreateCrawlerWebsiteResponse response = client.createCrawlerWebsite(request);
			
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testApplyCrawlerWebsite() {
//		fail("Not yet implemented");
		
		RpcServerConfiguration configuration = new RpcServerConfiguration();
		configuration.getServerNodes().add(new ServerNode("127.0.0.1", 9000));
		configuration.setTimeout(1000 * 10);
		
		DynamicClientProxy<WebMetaServerService.Client> proxy = new DynamicClientProxy<WebMetaServerService.Client>();
		WebMetaServerService.Iface client = (WebMetaServerService.Iface)proxy.createProxy(WebMetaServerService.Client.class, configuration);
		
		try {
			
//			WMSCreateCrawlerWebsiteRequest request = new WMSCreateCrawlerWebsiteRequest();
//			request.setLogid(10000L);
//			WMSCrawlerWebsite webinfo = new WMSCrawlerWebsite();
//			webinfo.setWebsite("tianshan");
//			webinfo.setWebsiteType(WMSWebsiteType.WMS_WEBSITE_ZH_CN);
//			
//			webinfo.setCrawlerType(WMSWebsiteCrawlerType.WMS_WEBSITE_CRAWLER_STAND_ALONE);
//			webinfo.setCrawlerSchedule(24);
//			webinfo.setCrawlerNum(200);
//			webinfo.setContentType(WMSWebsiteContentRuleType.WMS_WEBSITE_CONTENT_RULE_TEMPLATE_HPATH);
//			webinfo.setContentRule("content_rule");
//			
//			webinfo.setUrlType(WMSWebsiteUrlRuleType.WMS_WEBSITE_URL_RULE_HIT);
//			webinfo.setUrlRule("url_rule");
//			
//			webinfo.setSeeds(Arrays.asList(new String[] {
//					"http://www.ts.cn/"
//			}));
//			request.setWebinfo(webinfo);
//
//			WMSCreateCrawlerWebsiteResponse response = client.createCrawlerWebsite(request);
			
			WMSApplyCrawlerTaskRequest request = new WMSApplyCrawlerTaskRequest();
			request.setLogid(10000L);
			request.setWebsiteType(WMSWebsiteType.WMS_WEBSITE_ZH_CN);
			
			WMSApplyCrawlerTaskResponse response = client.applyCrawlerTask(request);

			if ( response.getStatus() == WMSTaskStatus.WMS_WEBSITE_TASK_STATUS_NOTHING ) {
				System.out.println("nothing");
			} else {
				System.out.println("yes");
				System.out.println(response.getTask());
			}
			
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testRethrow() {
		
		try {
			try {
				throw new RuntimeException();
			} catch (Exception e) {
				throw e;
			} finally {
				System.out.println("finally zai na");
			}
		} catch (Exception e) {
			System.out.println("..............");
		}
	}
	
	
}
