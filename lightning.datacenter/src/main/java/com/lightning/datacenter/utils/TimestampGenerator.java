package com.lightning.datacenter.utils;

import java.util.Calendar;

public class TimestampGenerator {
	
	private String currentDate;
	
	private Calendar calendar;
	
	public TimestampGenerator(String startDate) {
		this.currentDate = startDate;
		calendar = Calendar.getInstance();
		String[] segs = startDate.split("-");
		calendar.set(Calendar.YEAR, Integer.parseInt(segs[0]));
		calendar.set(Calendar.MONTH, Integer.parseInt(segs[1]) - 1);
		calendar.set(Calendar.DATE, Integer.parseInt(segs[2]));
	}
	
	public String next() {
		String date = currentDate;
		calendar.add(Calendar.DATE, 1);
		currentDate = String.format("%04d-%02d-%02d", 
								calendar.get(Calendar.YEAR),
								calendar.get(Calendar.MONTH) + 1, 
								calendar.get(Calendar.DATE)
							);
		return date;
	}
	
}
