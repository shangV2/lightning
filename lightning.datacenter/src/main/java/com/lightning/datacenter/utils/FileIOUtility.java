package com.lightning.datacenter.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileIOUtility {
	
	public static String readContent(String filename) {
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(new File(filename)));
			String line = null;
			while ( (line = br.readLine()) != null ) {
				sb.append(line).append("\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if ( br != null ) {
				 try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return sb.toString();
	}

	public static void writeContent(String filename, String content) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(filename);
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
