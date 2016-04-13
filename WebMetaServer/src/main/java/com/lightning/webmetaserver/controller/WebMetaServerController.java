package com.lightning.webmetaserver.controller;

import javax.annotation.Resource;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lighting.rpc.core.handler.LogProxyHandler;
import com.lighting.rpc.core.model.ServerConfiguration;
import com.lighting.rpc.webmetaserver.service.WebMetaServerService;
import com.lightning.webmetaserver.rpc.handler.WebMetaServerServiceHandlerImpl;
import com.lightning.webmetaserver.service.WebsiteCrawlerService;

public class WebMetaServerController implements InitializingBean {

	// 环境的配置
//	private int serverPort;
	
	@Resource
	private ServerConfiguration serverConfiguration;
	
	@Resource
	private WebsiteCrawlerService websiteCrawlerService;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
//		System.out.println(serverPort);
	}

	public void run() {
		// 传输层说明
		try {
			
			TServerTransport serverTransport = new TServerSocket(serverConfiguration.getServerPort());
			WebMetaServerServiceHandlerImpl handler = new WebMetaServerServiceHandlerImpl(this);

//			WebMetaServerService.Processor<WebMetaServerServiceHandlerImpl> processor 
//					= new WebMetaServerService.Processor<WebMetaServerServiceHandlerImpl>(handler);
			
			LogProxyHandler<WebMetaServerService.Iface> logProxyHanlder = new LogProxyHandler<WebMetaServerService.Iface>(handler);
			WebMetaServerService.Processor<WebMetaServerService.Iface> processor 
					= new WebMetaServerService.Processor<WebMetaServerService.Iface>((WebMetaServerService.Iface)logProxyHanlder.createProxy());
			
			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(
					serverTransport).processor(processor));
			
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}

	}

	public WebsiteCrawlerService getWebsiteCrawlerService() {
		return websiteCrawlerService;
	}
	
	public ServerConfiguration getServerConfiguration() {
		return serverConfiguration;
	}

	
	public static void main(String[] args) {

		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"conf/application_context.xml");
		WebMetaServerController controller = (WebMetaServerController) applicationContext
				.getBean("appController");
		
		controller.run();
		
	}

}
