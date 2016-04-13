package com.lightning.datacenter.controller;

import javax.annotation.Resource;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lighting.rpc.core.handler.LogProxyHandler;
import com.lighting.rpc.core.model.ServerConfiguration;
import com.lighting.rpc.datacenter.service.DataCenterService;
import com.lighting.rpc.datacenter.service.DataCenterService.Iface;
import com.lightning.datacenter.manager.ConsistentTopicManager;
import com.lightning.datacenter.manager.DataImporterManager;
import com.lightning.datacenter.manager.HotWebTrendManager;
import com.lightning.datacenter.manager.HotWordManager;
import com.lightning.datacenter.manager.SensitiveWordManager;
import com.lightning.datacenter.rpc.handler.DataCenterServiceHandlerImpl;

public class DataCenterServerController implements InitializingBean {

//	@Resource
//	private IKeyValueStoreEngine keyValueStoreEngine;
//	
//	@Resource
//	private ISortedKeyValueStoreEngine sortedKeyValueStoreEngine;
	
	@Resource
	private ServerConfiguration serverConfiguration;
	
	@Resource 
	private HotWordManager hotwordManager;
	
	@Resource
	private SensitiveWordManager sensitiveWordManager;
	
	@Resource
	private ConsistentTopicManager consistentTopicManager;
	
	@Resource
	private HotWebTrendManager hotWebTrendManager;
	
	@Resource
	private DataImporterManager dataImporterManager;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
	}
	
	public void init() throws Exception {
//		keyValueStoreEngine.init();
//		sortedKeyValueStoreEngine.init();
	}

	public void run() {
		// 传输层说明
		
		// *) 启动数据自动导入工作
		new Thread(dataImporterManager).start();
		
		try {
			TServerTransport serverTransport = new TServerSocket(serverConfiguration.getServerPort());
			DataCenterServiceHandlerImpl handler = new DataCenterServiceHandlerImpl(this);

			LogProxyHandler<DataCenterService.Iface> logProxyHanlder = new LogProxyHandler<DataCenterService.Iface>(handler);
			
			DataCenterService.Processor<DataCenterService.Iface> processor
					= new DataCenterService.Processor<DataCenterService.Iface>((Iface) logProxyHanlder.createProxy());
			
//			DataCenterService.Processor<DataCenterServiceHandlerImpl> processor
//					= new DataCenterService.Processor<DataCenterServiceHandlerImpl>(handler);
			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(
					serverTransport).processor(processor));
			
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}

	}

	public ServerConfiguration getServerConfiguration() {
		return serverConfiguration;
	}

//	public IKeyValueStoreEngine getKeyValueStoreEngine() {
//		return keyValueStoreEngine;
//	}
//	
//	public ISortedKeyValueStoreEngine getSortedKeyValueStoreEngine() {
//		return sortedKeyValueStoreEngine;
//	}

	public HotWordManager getHotwordManager() {
		return hotwordManager;
	}

	public SensitiveWordManager getSensitiveWordManager() {
		return sensitiveWordManager;
	}

	public ConsistentTopicManager getConsistentTopicManager() {
		return consistentTopicManager;
	}
	
	public HotWebTrendManager getHotWebTrendManager() {
		return hotWebTrendManager;
	}

	public static void main(String[] args) {
		
		// *) 配置Log4j的日志系统
		DOMConfigurator.configure("conf/log/log4j.xml");
		
		// *) 
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"conf/application_context.xml");
		DataCenterServerController controller = (DataCenterServerController) applicationContext
				.getBean("appController");
		
		try {
			controller.init();
			controller.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
