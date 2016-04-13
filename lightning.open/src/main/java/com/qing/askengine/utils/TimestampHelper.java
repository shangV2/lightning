package com.qing.askengine.utils;

/**
 * 
 *
 */
public class TimestampHelper {

	public static String normalize(String timestamp) {
		
		if ( timestamp == null ) {
			return timestamp;
		}
		if ( timestamp.length() == 8 ) {
			for ( int i = 0; i < timestamp.length(); i++ ) {
				char ch = timestamp.charAt(i);
				if ( !(ch >= '0' && ch <= '9') ) {
					return timestamp;
				}
			}
			return String.format("%4d-%02d-%02d", 
							Integer.parseInt(timestamp.substring(0, 4)),
							Integer.parseInt(timestamp.substring(4, 6)),
							Integer.parseInt(timestamp.substring(6, 8))
						);
		}
		return timestamp;
	}
	
	public static void main(String[] args) {
		String[] timestamps = new String[] {
				"20140120", "2014-01-20", "2014-02-01", "20140201", "20141021"
		};
		for ( String timestamp : timestamps ) {
			System.out.println(timestamp + ":" + TimestampHelper.normalize(timestamp));
		}
	}
	
}
