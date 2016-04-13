package com.zhitianweilai.qing.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHelper {

	public static void sleep(long millis) {
		while (millis > 0) {
			long before = System.currentTimeMillis();
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
			}
			long delta = System.currentTimeMillis() - before;
			millis = millis - delta;
		}
	}
	
	public static String currentDay() {
		 return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
	public static String convert2String(int value) {
		int year = value / 10000;
		int month = value % 10000 / 100;
		int day = value % 100;
		return String.format("%04d-%02d-%02d", year, month, day);
	}
	
//	public static void main(String[] args) {
//		TimeHelper.sleep(1000);
//	}
	
}
