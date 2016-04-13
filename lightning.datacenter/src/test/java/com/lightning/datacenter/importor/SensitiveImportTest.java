package com.lightning.datacenter.importor;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lightning.datacenter.importer.impl.SensitiveWordImporter;

public class SensitiveImportTest {

	
	public static void main(String[] args) {
		
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"conf/application_context.xml");
		
		SensitiveWordImporter importer = (SensitiveWordImporter)applicationContext.getBean("sensitiveWordImporter");
		importer.init();
		
		importer.setDatapath("D:\\test\\submit\\fqdata");
		
		importer.process();
		
	}
	
}
