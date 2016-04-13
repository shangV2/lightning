package com.qing.askengine.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	
	
	private static final int[][] DAY_NUM_OF_MONTH = {
		{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
		{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
	};
	
	public static String beforeDateForyyyyMMdd(int year, int month, int day, int num) {
		
		if ( day - num >= 1 ) {
			return String.format("%d/%02d/%02d", year, month, day - num);
		} else {
			num -= day;
			while ( num >= 0 ) {
				if ( month - 1 >= 1 ) {
					month = month - 1;
				} else {
					month = 12;
					year = year - 1;
				}
				if ( year <= 0 ) {
					return "1900/00/00";
				}
				int ndate = queryDayOfMonth(year, month);
				if ( ndate - num >= 1 ) {
					return String.format("%d/%02d/%02d", year, month, ndate - num);
				}
				num = num - ndate;
			}
		}
		return "";
		
	}
	
	public static String beforeDate(int year, int month, int day, int num) {
		
		if ( day - num >= 1 ) {
			return String.format("%d-%d", month, day - num);
		} else {
			num -= day;
			while ( num >= 0 ) {
				if ( month - 1 >= 1 ) {
					month = month - 1;
				} else {
					month = 12;
					year = year - 1;
				}
				if ( year <= 0 ) {
					return "0-0";
				}
				int ndate = queryDayOfMonth(year, month);
				if ( ndate - num >= 1 ) {
					return String.format("%d-%d", month, ndate - num);
				}
				num = num - ndate;
			}
		}
		return "";
		
	}
	
	public static int queryDayOfMonth(int year, int month) {
		if ( isLeap(year) ) {
			return DAY_NUM_OF_MONTH[1][month - 1];
		} else {
			return DAY_NUM_OF_MONTH[0][month - 1];
		}
	}
	
	public static boolean isLeap(int year) {
		if ( year % 400 == 0 || (year % 4 == 0 && year % 100 != 0) ) {
			return true;
		}
		return false;
	}
	
	public static boolean checkValidateDate(int year, int month, int day) {
		if ( year < 0 ) {
			return false;
		}
		if ( month <= 0 || month > 12 ) {
			return false;
		}
		int dayOfMonth = queryDayOfMonth(year, month);
		if ( day <= 0 || day > dayOfMonth ) {
			return false;
		}
		return true;
	}
	
	public static int diffBetweenDays(int startdate, int enddate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			Date startDate = format.parse(String.valueOf(startdate));
			Date endDate = format.parse(String.valueOf(enddate));
			return (int)((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000L));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	

	public static int convertToIntForYYYYMMDD(String date) {
		// TODO
		int value = Integer.parseInt(date);
		if ( value < 19000000 || value > 21000000 ) {
			throw new RuntimeException(String.format("illegal parameter not in range[%d, %d]", 19000000, 21000000));
		}
		return value;
	}
	
	public static String format2Normal(String msg) {
		msg = msg.replaceAll("/", "-");
		return msg = msg.replaceAll(":", "-");
	}
	
	public static String shortMessage2Normal(String text) {
		String[] terms = text.trim().split("/");
		if ( terms.length >= 2 ) {
			try {
				int value = Integer.parseInt(terms[1]);
				return String.format("%s-%02d-%02d", terms[0], value / 100, value % 100);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return format2Normal(text);
	}
	
	public static String convert2Normal(String format, String value) {
		if ( "yyyyMMdd".equalsIgnoreCase(format) ) {
			try {
				int intval = Integer.parseInt(value);
				return String.format("%d-%02d-%02d", intval / 10000, (intval % 10000) / 100, intval % 100);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	public static void main(String[] args) {
		for ( int i = 1; i < 365; i++ ) {
			System.out.println(beforeDate(2013, 2, 20, i));
		}
		System.out.println(diffBetweenDays(20131029, 20131110));
	}
	
}
