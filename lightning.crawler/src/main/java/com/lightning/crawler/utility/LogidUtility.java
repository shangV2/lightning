package com.lightning.crawler.utility;

import java.util.concurrent.atomic.AtomicInteger;

public class LogidUtility {

	private static AtomicInteger counter = new AtomicInteger(1);

	public static long genLogid() {
		int nowvalue = counter.addAndGet(1);
		if (nowvalue > Integer.MAX_VALUE / 2) {
			counter.set(1);
		}
		long currrentTime = System.currentTimeMillis();
		Thread thread = Thread.currentThread();
		long threadId = thread.hashCode();
		return currrentTime ^ threadId ^ nowvalue;
	}

}
