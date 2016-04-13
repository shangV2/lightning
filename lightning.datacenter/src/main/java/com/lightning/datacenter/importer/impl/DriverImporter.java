package com.lightning.datacenter.importer.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lightning.datacenter.importer.QFImporter;
import com.lightning.datacenter.importer.SuperFileQueue;

public class DriverImporter {

	private List<QFImporter> importers = new ArrayList<QFImporter>();
	private String path = "output";
	
	public void init() {
//		importers.add(new HotWebImporter());
		
		for ( QFImporter importer : importers ) {  
		}
		
	}
	
	public void register(QFImporter importer) {
		importers.add(importer);
	}
	

	private Map<String, List<String>> extract(String path) {
		Map<String, List<String> > resultMap = new TreeMap<String, List<String>>();
		
		File dir = new File(path);
		String[] filenames = dir.list(new FilenameFilter() {
			Pattern FMQ_PATTERN = Pattern.compile(".*\\d{4}-\\d{2}-\\d{2}\\.fmq");
			@Override
			public boolean accept(File dir, String filename) {
				return FMQ_PATTERN.matcher(filename).matches();
			}
		});
		
		Pattern timePattern = Pattern.compile(".*(\\d{4}-\\d{2}-\\d{2})\\.fmq");
		for ( String filename : filenames ) {
			Matcher matcher = timePattern.matcher(filename);
			if ( matcher.find() ) {
				String timestamp = matcher.group(1);
				if ( !resultMap.containsKey(timestamp) ) {
					resultMap.put(timestamp, new ArrayList<String>());
				}
				List<String> flist = resultMap.get(timestamp);
				flist.add(new File(dir, filename).getAbsolutePath());
			}
		}
		
		return resultMap;
	}
	
	public void drive() {
		Map<String, List<String> > resultMap = extract(this.path);
		for ( Map.Entry<String, List<String>> entry : resultMap.entrySet() ) {
			String key = entry.getKey();
			List<String> values = entry.getValue();
			
			String timestamp = key;
			for ( QFImporter importer : importers ) {
				SuperFileQueue sfq = new SuperFileQueue(values);
				importer.handle(timestamp, sfq);
			}
		}
	}
	
//	public static void main(String[] args) {
//		DriverImporter driver = new DriverImporter();
//		driver.init();
//		driver.register(new HotWebImporter());
//		driver.drive();
//	}
	
}
