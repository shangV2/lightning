package com.lightning.datacenter.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


// <<static utility>>
public class DataCenterEventUtility {

	public static String genEventid(String timestamp, String title) {
		return getMd5Value(timestamp + ":" + title);
	}
	
	public static String getMd5Value(String expression) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(expression.getBytes("UTF-8"));
			byte[] mddata = md.digest();
			StringBuilder sb = new StringBuilder();
			for ( byte c : mddata ) {
				int cv = (int)(c & 0xFF);
				if ( cv < 10 ) {
					sb.append("0");
				} else {
					sb.append(Integer.toHexString(cv));
				}
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	public static void main(String[] args) {
		System.out.println(DataCenterEventUtility.getMd5Value("hi, nihao"));
	}
	
}
