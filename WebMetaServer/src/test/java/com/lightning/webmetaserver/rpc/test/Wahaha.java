package com.lightning.webmetaserver.rpc.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Wahaha {

	private List<String> servers = new ArrayList<String>();

	public List<String> getServers() {
		return servers;
	}

	public void setServers(List<String> servers) {
		this.servers = servers;
	}

	public void init() {
		System.out.println("init " + servers.size());
	}
	
	public static void main(String[] args) {
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"application_context.xml");
		
		Wahaha wahaha = (Wahaha)applicationContext.getBean("wahaha");
		
		List<String> servers = wahaha.getServers();
		for ( String server : servers ) {
			System.out.println(servers);
		}
		
	}
	
}
