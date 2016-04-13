package com.lighting.datasource.reader;

import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class DailyDataSourceReaderFactory {

	private static final int DEFAULT_NUM_OF_DAYS = 30;
	
	private String datapath = null;
	
	private int numOfDays = DEFAULT_NUM_OF_DAYS;
	
	private int interval = 0;
	
	private Map<String, Object> params = new TreeMap<String, Object>();
	
	public void init() {
	}
	
	public void init(Properties props) {
	}

	public DailyDataSourceReader createDataSourceReader() {
		return new DailyDataSourceReader(datapath, numOfDays);
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

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}
	
}
