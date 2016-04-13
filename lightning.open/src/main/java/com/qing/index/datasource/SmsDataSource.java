package com.qing.index.datasource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.qing.index.model.SmsInfo;

public class SmsDataSource {

	private String smsDataFile = null;
	
	public SmsDataSource(String dataFile) {
		this.smsDataFile = dataFile;
	}
	
	public void init() {
		
	}
	
	public SmsIterator iterator() {
		SmsIterator iter = new SmsIterator();
		iter.load(smsDataFile);
		return iter;
	}
	
	// @brief		
	public class SmsIterator implements Iterator<SmsInfo> {
		
		private List<SmsInfo> smses = new ArrayList<SmsInfo>();
		private int current = 0;
		
		public void load(String filename) {
			
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
				String line = null;
				while ( (line = br.readLine()) != null ) {
					if ( line.trim().length() > 0 ) {
						Scanner scanner = new Scanner(new StringReader(line));
						String sendMobile = scanner.next();
						String sendUsername = scanner.next();
						String recvMobile = scanner.next();
						String recvUsername = scanner.next();
						String timestamp = scanner.next();
						scanner.skip("[ \t]");
						String content = scanner.nextLine();
						
						SmsInfo sms = new SmsInfo(); //(sendMobile, recvMobile, timestamp, content);
						sms.setSendMobile(sendMobile);
						sms.setRecvMobile(recvMobile);
						sms.setContent(content);
						sms.setTimestamp(timestamp);
						sms.setRecvUsername(recvUsername);
						sms.setSendUsername(sendUsername);
						
						smses.add(sms);
						scanner.close();
					}
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
			
		}
		
		@Override
		public boolean hasNext() {
			return current < smses.size();
		}

		@Override
		public SmsInfo next() {
			return smses.get(current++);
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
		}
		
	}
	
	public static void main(String[] args) {
		SmsDataSource source = new SmsDataSource("smsdata/sms.txt");
		Iterator<SmsInfo> iter = source.iterator();
	}
	
}
