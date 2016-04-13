package com.qing.index.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * 
 * @brief	
 * 
 * @author renjie.rj
 *
 */

public class FileIOHelper {

	public static void writeContentToFile(String filename, String content) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(filename));
			pw.write(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if ( pw != null ) {
				pw.close();
			}
		}
	}
	
	
	
}
