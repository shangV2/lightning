package com.lightning.crawler.utility;

public class TimeUtility {

	public static void sleep(long msec) {
		
		long before = System.currentTimeMillis();
		long after = before;
		while ( after - before < msec ) {
			try {
				Thread.sleep(msec - (after - before));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			after = System.currentTimeMillis();
		}
		
	}
	
//	public static void main(String[] args) {
//		System.out.println(System.currentTimeMillis());
//		TimeUtility.sleep(1200);
//		System.out.println(System.currentTimeMillis());
//	}
	
}
