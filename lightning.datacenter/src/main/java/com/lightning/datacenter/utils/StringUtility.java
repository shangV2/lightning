package com.lightning.datacenter.utils;

import java.io.UnsupportedEncodingException;

public class StringUtility {

	public static byte[] toUtf8Bytes(String msg) {
		try {
			return msg.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return msg.getBytes();
	}
	
	public static String toUtf8String(byte[] data) {
		try {
			return new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new String(data);
	}
	
}
