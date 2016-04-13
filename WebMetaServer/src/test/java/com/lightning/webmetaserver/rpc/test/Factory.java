package com.lightning.webmetaserver.rpc.test;

import java.util.List;

import org.apache.thrift.TException;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lighting.rpc.common.exception.LightningServiceException;
import com.lighting.rpc.core.client.DynamicClientProxy;
import com.lighting.rpc.core.configure.RpcServerConfiguration;
import com.lighting.rpc.core.model.ServerNode;
import com.lighting.rpc.webmetaserver.model.WMSApplyCrawlerTaskRequest;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteType;
import com.lighting.rpc.webmetaserver.service.WebMetaServerService;

public class Factory {

//	public static Object createIface() {
//		return null;
//	}
	
	public Factory() {
	}
	
	public static Object createIface() {
		return "hello";
	}
	
	public static Object createIface(String clazzName)  {
		System.out.println("clazz_name " + clazzName);
		return "obj";
	}
	
	public static Object createIface(String className, List<String> lists) {
		
		System.out.println("create name" + className);
		for ( String ip : lists ) {
			System.out.println(ip);
		}
		
		try {

//			Class.forName("com.lighting.rpc.webmetaserver.service.WebMetaServerService$Iface");
			int idx = className.lastIndexOf('.');
			className = className.substring(0, idx) + "$Iface";
			Class.forName(className);
			
			String clientClassName = className.substring(0, idx) + "$Client";
			Class clientClazz = Class.forName(clientClassName);
			
			RpcServerConfiguration configuration = new RpcServerConfiguration();
			configuration.getServerNodes().add(
					new ServerNode("127.0.0.1", 9000));
			configuration.setTimeout(1000 * 5);
			
			DynamicClientProxy proxy = new DynamicClientProxy();
			return proxy.createProxy(clientClazz, configuration);
			
//			DynamicClientProxy<WebMetaServerService.Client> proxy = new DynamicClientProxy<WebMetaServerService.Client>();
//			WebMetaServerService.Iface client = (WebMetaServerService.Iface) proxy
//					.createProxy(WebMetaServerService.Client.class,
//							configuration);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "hello";
		
	}
	
	
	public static void main(String[] args) {
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"application_context.xml");
//		Object obj = (Object)applicationContext.getBean("id1", new Object[] {"dsf" });
		Object obj = (Object)applicationContext.getBean("id1");
		
		System.out.println(obj.getClass().getName());
		
		for ( Class<?> clz : obj.getClass().getInterfaces() ) {
			System.out.println(clz.getName());
		}
		
		if ( obj instanceof WebMetaServerService.Iface ) {
			System.out.println("Yes");
		}
		
		WebMetaServerService.Iface iface = (WebMetaServerService.Iface)obj;
		WMSApplyCrawlerTaskRequest request = new WMSApplyCrawlerTaskRequest();
		request.setWebsiteType(WMSWebsiteType.WMS_WEBSITE_ZH_CN);
		try {
			iface.applyCrawlerTask(request);
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
		
	}
	
}