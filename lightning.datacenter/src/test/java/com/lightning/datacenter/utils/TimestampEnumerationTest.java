package com.lightning.datacenter.utils;

import org.junit.Test;

public class TimestampEnumerationTest {

	@Test
	public void test() {
//		fail("Not yet implemented");
	}

	@Test
	public void test1001() {
		 TimestampEnumeration te = new TimestampEnumeration("2013-02-01", "2013-04-01");
		 while ( te.hasNext() ) {
			 String timestamp = te.next();
			 System.out.println(timestamp);
		 }
	}
	
}
