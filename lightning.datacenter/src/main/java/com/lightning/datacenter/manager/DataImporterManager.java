package com.lightning.datacenter.manager;

import java.util.ArrayList;
import java.util.List;

import com.lightning.datacenter.importer.QFImporter;
import com.lightning.datacenter.utils.TimeUtility;

public class DataImporterManager implements Runnable {

	private static final long DEFAULT_INTERVAL_SECONDS = 10 * 60L;
	
	private List<QFImporter> importers = new ArrayList<QFImporter>();
	
	private long interval = DEFAULT_INTERVAL_SECONDS;
	
	@Override
	public void run() {
		
		do {
			
			TimeUtility.sleep(interval * 1000L);
			
			for ( QFImporter importer : importers ) {
				importer.process();
			}
		} while (true);
		
	}

	public List<QFImporter> getImporters() {
		return importers;
	}

	public void setImporters(List<QFImporter> importers) {
		this.importers = importers;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}
	
}
