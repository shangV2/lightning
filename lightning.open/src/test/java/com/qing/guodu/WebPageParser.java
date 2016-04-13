package com.qing.guodu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WebPageParser {

	public static WebPage parse(String filename) {
	
		WebPage webPage = new WebPage();
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			String line = null;
			while ( (line = reader.readLine()) != null ) {
				if ( line.trim().length() > 0 ) {
					webPage.setUrl(line.trim());
					break;
				}
			}
			if ( line == null ) {
				return webPage;
			}
			
			while ( (line = reader.readLine()) != null ) {
				if ( line.trim().length() > 0 ) {
					webPage.setSource(line.trim());
					break;
				}
			}
			if ( line == null ) {
				return webPage;
			}
			
			while ( (line = reader.readLine()) != null ) {
				if ( line.trim().length() > 0 ) {
					webPage.setTitle(line.trim());
					break;
				}
			}
			if ( line == null ) {
				return webPage;
			}
			
			while ( (line = reader.readLine()) != null ) {
				if ( line.trim().length() > 0 ) {
					webPage.setTimestamp(line.trim());
					break;
				}
			}
			if ( line == null ) {
				return webPage;
			}

			StringBuilder sb = new StringBuilder();
			while ( (line = reader.readLine()) != null ) {
				sb.append(line.trim()).append("\n");
			}
			webPage.setContent(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if ( reader != null ) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return webPage;
		}
		
	}
	
//	public WebPage parse(InputStream is) {
//	}
	
}
