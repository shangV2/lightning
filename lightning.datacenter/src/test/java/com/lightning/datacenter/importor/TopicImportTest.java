package com.lightning.datacenter.importor;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lightning.datacenter.importer.impl.ConsistentTopicImporter;

public class TopicImportTest {

	
	
	public static void main(String[] args) {
		
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"conf/application_context.xml");
		
		ConsistentTopicImporter importer = (ConsistentTopicImporter)applicationContext.getBean("consistentTopicImporter");
		
		importer.setDatapath("D:\\test\\submit\\fqdata");
		
		importer.process();
		
	}
	
}
