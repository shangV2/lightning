package com.zhitianweilai.qing.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class EncoderDetectorHelperTest {

	@Test
	public void testDetect() {
		
		String text = "...";
		String encode = EncoderDetectorHelper.detect(text.getBytes());
		System.out.println(encode);
		
	}

}
