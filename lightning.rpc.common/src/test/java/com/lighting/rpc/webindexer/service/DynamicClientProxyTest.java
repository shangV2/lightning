package com.lighting.rpc.webindexer.service;

import org.apache.thrift.TException;

import com.lighting.rpc.core.client.DynamicClientProxy;
import com.lighting.rpc.core.configure.RpcServerConfiguration;
import com.lighting.rpc.core.model.ServerNode;
import com.lighting.rpc.webindexer.model.WIQueryRequest;
import com.lighting.rpc.webindexer.model.WIQueryResponse;
import com.lighting.rpc.webindexer.model.WIWebPage;

public class DynamicClientProxyTest {

	
	public static void main(String[] args) {
		
		RpcServerConfiguration configuration = new RpcServerConfiguration();
		configuration.getServerNodes().add(new ServerNode("127.0.0.1", 9010));
//		configuration.setTimeout(1000 * 2);
		
		DynamicClientProxy<WebIndexerService.Client> proxy = new DynamicClientProxy<WebIndexerService.Client>();
		WebIndexerService.Iface client = (WebIndexerService.Iface)proxy.createProxy(WebIndexerService.Client.class, configuration);
		
		WIQueryRequest request = new WIQueryRequest();
		request.setLogid(1001L);
		request.setQuery("新疆");
		WIQueryResponse response;
		try {
			response = client.query(request);
			System.out.println(response.getTotalNumber());
			for ( WIWebPage webPage : response.getWebpages() ) {
				System.out.println(webPage.getTitle());
				System.out.println(webPage.getUrl());
				System.out.println(webPage.getSummary());
			}
		} catch (TException e) {
			e.printStackTrace();
		}

		
	}
	
}
