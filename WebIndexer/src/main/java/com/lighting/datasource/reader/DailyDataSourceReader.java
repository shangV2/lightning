package com.lighting.datasource.reader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.qing.index.datasource.IIndexDataSource;
import com.qing.index.datasource.VariertyFileQueueDataSource;
import com.qing.index.handler.IWebPageHandler;


public class DailyDataSourceReader implements IIndexDataSource {

	private static final int DEFAULT_NUM_OF_DAYS = 30;
	
	private String datapath = null;
	
	private int numOfDays = DEFAULT_NUM_OF_DAYS;
	
	public DailyDataSourceReader(String datapath, int numOfDays) {
		this.datapath = datapath;
		this.numOfDays = numOfDays;
	}

	
	// *) 
	@Override
	public void loadData(IWebPageHandler webPageHandler) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		File dataDir = new File(datapath);
		if ( dataDir.exists() && dataDir.isDirectory() && !dataDir.isHidden()) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -numOfDays);
			for ( int i = 0; i < numOfDays; i++ ) {
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				Date day = calendar.getTime();
				
				String timestamp = sdf.format(day);
				
				File childDir = new File(dataDir, timestamp);
				if ( childDir.exists() && childDir.isDirectory() ) {
					VariertyFileQueueDataSource dataSource = new VariertyFileQueueDataSource(childDir.getAbsolutePath());
					dataSource.loadData(childDir.getAbsolutePath(), webPageHandler);
				}
			}
		}
		
	}
	
	
	public String getDatapath() {
		return datapath;
	}

	public void setDatapath(String datapath) {
		this.datapath = datapath;
	}

	public int getNumOfDays() {
		return numOfDays;
	}

	public void setNumOfDays(int numOfDays) {
		this.numOfDays = numOfDays;
	}
	
}
