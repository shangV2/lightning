package com.lighting.webindexer.controller;

import javax.annotation.Resource;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lighting.datasource.reader.DailyDataSourceReaderFactory;
import com.lighting.rpc.core.model.ServerConfiguration;
import com.lighting.rpc.webindexer.service.WebIndexerService;
import com.lighting.webindexer.config.IndexerConfiguration;
import com.lighting.webindexer.manager.IndexTimerManager;
import com.lighting.webindexer.manager.QingIndexManager;
import com.lighting.webindexer.rpc.handler.WebIndexerServiceHandlerImpl;

public class WebIndexerController implements InitializingBean {

	private QingIndexManager qingIndexManager = null;

	private IndexTimerManager indexTimerManager = null;
	
	private WebIndexerServiceHandlerImpl handlerImpl = null;
	
	@Resource
	private ServerConfiguration serverConfiguration;
	
	@Resource 
	private IndexerConfiguration indexerConfiguration;
	
	@Resource
	private DailyDataSourceReaderFactory dataSourceFactory;
	
	public void init() {
		
		qingIndexManager = new QingIndexManager();
		qingIndexManager.setIndexRootDir(indexerConfiguration.getIndexDir());
		qingIndexManager.setAnalyzer(indexerConfiguration.getAnalyzer());
		
		qingIndexManager.init();
		qingIndexManager.prepare();
		
		handlerImpl = new WebIndexerServiceHandlerImpl(this);
		
		indexTimerManager = new IndexTimerManager();
		indexTimerManager.setQingIndexManager(qingIndexManager);
		indexTimerManager.setReaderFactory(dataSourceFactory);
		indexTimerManager.setInterval(dataSourceFactory.getInterval());
		
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
//		System.out.println(indexerConfiguration.getIndexDir());
//		System.out.println(indexerConfiguration.getAnalyzer().getClass().getName());
	}
	
	public void run() {
		
		new Thread(indexTimerManager).start();
		
		try {
			TServerTransport serverTransport = new TServerSocket(serverConfiguration.getServerPort());
			WebIndexerService.Processor<WebIndexerServiceHandlerImpl> processor 
							= new WebIndexerService.Processor<WebIndexerServiceHandlerImpl>(handlerImpl);
			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(
					serverTransport).processor(processor));
			server.serve();
			server.stop();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
		
	}
	
	public QingIndexManager getQingIndexManager() {
		return qingIndexManager;
	}

	public void setQingIndexManager(QingIndexManager qingIndexManager) {
		this.qingIndexManager = qingIndexManager;
	}

	public static void main(String[] args) {
		
		DOMConfigurator.configure("conf/log/log4j.xml");
		
		// *) 采用spring的方式来启动，这是最好的方法了
		
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"conf/application_context.xml");
		WebIndexerController controller = (WebIndexerController) applicationContext
				.getBean("appController");
		
		controller.init();
		controller.run();
	
	}

}
