package com.zhitianweilai.qing.utils;

public class ContentTest {

	public static void main(String[] args) {
		
		String msg = "hello world\r\n, wahaha";
		System.out.println(msg.replaceAll("[\r\n]", " "));
		
	}
	
}
