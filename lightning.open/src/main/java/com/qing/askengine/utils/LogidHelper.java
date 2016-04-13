package com.qing.askengine.utils;

import java.util.concurrent.atomic.AtomicInteger;

// << static helper class >>
public class LogidHelper {

	private static AtomicInteger counter = new AtomicInteger(1);
	
	public static long genLogid() {
		int nowvalue = counter.addAndGet(1);
		if ( nowvalue > Integer.MAX_VALUE / 2 ) {
			counter.set(1);
		}
		long currrentTime = System.currentTimeMillis();
		Thread thread = Thread.currentThread();
		long threadId = thread.hashCode();
		return currrentTime ^ threadId ^ nowvalue;
	}

//	public static void main(String[] args) {
//		for ( int i = 0; i < 100; i++ ) {
//			System.out.println(genLogid());
//		}
//	}
	
}
