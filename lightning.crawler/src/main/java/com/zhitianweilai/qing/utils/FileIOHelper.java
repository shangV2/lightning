package com.zhitianweilai.qing.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 
 * @brief	
 * 
 * @author renjie.rj
 *
 */

public class FileIOHelper {

	public static void writeContentToFile(String filename, String content) {
		
		if ( content == null ) {
			return;
		}
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));
			pw.write(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			if ( pw != null ) {
				pw.close();
			}
		}
	}
	
	
	
}
