package com.lightning.webmetaserver.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtility {

	/**
	 * 
	 * @return
	 */
	public static int getDailyValue() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return Integer.parseInt(sdf.format(new Date()));
	}
	
}
