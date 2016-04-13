package com.qing.askengine.controller;

import javax.annotation.Resource;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lighting.rpc.core.model.ServerConfiguration;
import com.lighting.rpc.datacenter.service.DataCenterService;
import com.lighting.rpc.webindexer.service.WebIndexerService;
import com.lighting.rpc.webmetaserver.service.WebMetaServerService;
import com.qing.askengine.handler.LogiclayerHandler;
import com.qing.askengine.service.TranslateService;
import com.qing.logiclayer.Logiclayer;

public class AskEngineController implements InitializingBean {
	
	public AskEngineController() {
		// TODO Auto-generated constructor stub
	}
	
	@Resource
	private ServerConfiguration serverConfiguration;
	
	@Resource
	private WebMetaServerService.Iface webMetaServerService;
	
	@Resource
	private DataCenterService.Iface dataCenterService;
	
	@Resource
	private WebIndexerService.Iface cnwebIndexerService;
	
	@Resource
	private WebIndexerService.Iface uywebIndexerService;
	
	@Resource
	private TranslateService translateService;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
//		System.out.println(serverPort);
	}

	public void run() {
		// 传输层说明
		try {
			TServerTransport serverTransport = new TServerSocket(serverConfiguration.getServerPort());
			LogiclayerHandler handler = new LogiclayerHandler(this);

			// *) 
			handler.init();
			
			Logiclayer.Processor<LogiclayerHandler> processor 
					= new Logiclayer.Processor<LogiclayerHandler>(handler);
			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(
					serverTransport).processor(processor));
			
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}

	}

	public WebMetaServerService.Iface getWebMetaServerService() {
		return webMetaServerService;
	}

	public void setWebMetaServerService(
			WebMetaServerService.Iface webMetaServerService) {
		this.webMetaServerService = webMetaServerService;
	}

	public DataCenterService.Iface getDataCenterService() {
		return dataCenterService;
	}

	public void setDataCenterService(DataCenterService.Iface dataCenterService) {
		this.dataCenterService = dataCenterService;
	}

	public ServerConfiguration getServerConfiguration() {
		return serverConfiguration;
	}

	public WebIndexerService.Iface getCnwebIndexerService() {
		return cnwebIndexerService;
	}

	public void setCnwebIndexerService(WebIndexerService.Iface cnwebIndexerService) {
		this.cnwebIndexerService = cnwebIndexerService;
	}
	
	public TranslateService getTranslateService() {
		return translateService;
	}

	public void setTranslateService(TranslateService translateService) {
		this.translateService = translateService;
	}

	public WebIndexerService.Iface getUywebIndexerService() {
		return uywebIndexerService;
	}

	public void setUywebIndexerService(WebIndexerService.Iface uywebIndexerService) {
		this.uywebIndexerService = uywebIndexerService;
	}

	public static void main(String[] args) {

		DOMConfigurator.configure("conf/log/log4j.xml");
		
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"conf/application_context.xml");
		AskEngineController controller = (AskEngineController) applicationContext
				.getBean("appController");
		
		controller.run();
		
	}
}
