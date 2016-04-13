package com.zhitianweilai.qing.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.mozilla.universalchardet.UniversalDetector;

public class EncoderDetectorHelper {

	public static final String DEFAULT_ENCODING_LANG = "UTF-8";
	
	public static String detect(byte[] content) {
		
		if ( content == null || content.length == 0 ) {
			return DEFAULT_ENCODING_LANG;
		}
		
		ByteArrayInputStream bais = new ByteArrayInputStream(content);
		
		byte[] buf = new byte[1024];
	    UniversalDetector detector = new UniversalDetector(null);
	    // (2)
	    int nread = 0;
	    try {
			while ((nread = bais.read(buf)) > 0 && !detector.isDone()) {
			  detector.handleData(buf, 0, nread);
			}
		} catch (IOException e) {
//			e.printStackTrace();
			detector.dataEnd();
			return DEFAULT_ENCODING_LANG;
		}
	    // (3)
	    detector.dataEnd();
	    // (4)
	    String encoding = detector.getDetectedCharset();
	    detector.reset();
	    if (encoding != null) {
	      return encoding;
	    }
		
		return DEFAULT_ENCODING_LANG;
	}
	
	
	public static String detect(InputStream is) {
		
		if ( is == null ) {
			return DEFAULT_ENCODING_LANG;
		}
		
		byte[] buf = new byte[1024];
	    UniversalDetector detector = new UniversalDetector(null);
	    // (2)
	    int nread = 0;
	    try {
			while ((nread = is.read(buf)) > 0 && !detector.isDone()) {
			  detector.handleData(buf, 0, nread);
			}
		} catch (IOException e) {
//			e.printStackTrace();
			detector.dataEnd();
			return DEFAULT_ENCODING_LANG;
		}
	    // (3)
	    detector.dataEnd();
	    // (4)
	    String encoding = detector.getDetectedCharset();
	    detector.reset();
	    if (encoding != null) {
	      return encoding;
	    }
		
		return DEFAULT_ENCODING_LANG;
	}
	
	
	
	
}
