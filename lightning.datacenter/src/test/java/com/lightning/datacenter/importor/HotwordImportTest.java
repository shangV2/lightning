package com.lightning.datacenter.importor;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lightning.datacenter.importer.impl.HotwordImporter;

public class HotwordImportTest {
	
	
	public static void main(String[] args) {
		
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"conf/application_context.xml");
		
		HotwordImporter importer = (HotwordImporter)applicationContext.getBean("hotwordImporter");
		
		importer.setDatapath("D:\\test\\submit\\fqdata");
		
		importer.process();
		
	}

}
