package com.lightning.datacenter.importor;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lightning.datacenter.importer.impl.HotWebImporter;

public class HotWebImporterTest {

	public static void main(String[] args) {
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"conf/application_context.xml");
		
		HotWebImporter importer = (HotWebImporter)applicationContext.getBean("hotWebImporter");
		
		importer.setDatapath("D:\\test\\submit\\fqdata");
		
		importer.process();	
	}
	
}

