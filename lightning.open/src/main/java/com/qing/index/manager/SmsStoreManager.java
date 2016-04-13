package com.qing.index.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.qing.index.datasource.SmsDataSource;
import com.qing.index.model.SmsFriendInfo;
import com.qing.index.model.SmsInfo;

public class SmsStoreManager {

	private Map<String, List<SmsInfo>> userSmses = new TreeMap<String, List<SmsInfo>>();

	private Map<String, Map<String, Integer>> userFriends = new TreeMap<String, Map<String, Integer>>();

	private Map<String, String> userToNameMap = new TreeMap<String, String>();
	
	private List<SmsInfo> smsinfoes = new ArrayList<SmsInfo>();
	
	public void init() {
		SmsDataSource dataSource = new SmsDataSource("smsdata/sms.txt");
		Iterator<SmsInfo> iter = dataSource.iterator();
		while (iter.hasNext()) {
			SmsInfo sinfo = iter.next();
			smsinfoes.add(sinfo);
			String sendMobile = sinfo.getSendMobile();
			String recvMobile = sinfo.getRecvMobile();
			if (userSmses.containsKey(sendMobile)) {
				userSmses.get(sendMobile).add(sinfo);
			} else {
				userSmses.put(sendMobile, new ArrayList<SmsInfo>());
				userSmses.get(sendMobile).add(sinfo);
			}
			
			if (userSmses.containsKey(recvMobile)) {
				userSmses.get(recvMobile).add(sinfo);
			} else {
				userSmses.put(recvMobile, new ArrayList<SmsInfo>());
				userSmses.get(recvMobile).add(sinfo);
			}
			
			userToNameMap.put(sendMobile, sinfo.getSendUsername());
			userToNameMap.put(recvMobile, sinfo.getRecvUsername());
			
			// *) 
			if (userFriends.containsKey(sendMobile)) {
				Map<String, Integer> omap = userFriends.get(sendMobile);
				Integer val = omap.get(recvMobile);
				if (val == null) {
					omap.put(recvMobile, 1);
				} else {
					omap.put(recvMobile, val + 1);
				}
			} else {
				Map<String, Integer> omap = new TreeMap<String, Integer>();
				omap.put(recvMobile, 1);
				userFriends.put(sendMobile, omap);
			}
			
			// *)
			if (userFriends.containsKey(recvMobile)) {
				Map<String, Integer> omap = userFriends.get(recvMobile);
				Integer val = omap.get(sendMobile);
				if (val == null) {
					omap.put(sendMobile, 1);
				} else {
					omap.put(sendMobile, val + 1);
				}
			} else {
				Map<String, Integer> omap = new TreeMap<String, Integer>();
				omap.put(sendMobile, 1);
				userFriends.put(recvMobile, omap);
			}
		}
	}

	public List<SmsInfo> querySmsByContact(String username, int pageno,
			int limit) {

		List<SmsInfo> result = new ArrayList<SmsInfo>();
		synchronized (userSmses) {
			List<SmsInfo> smsinfos = userSmses.get(username);
			if (smsinfos != null) {
				for (int i = pageno * limit; i < smsinfos.size()
						&& i < (pageno + 1) * limit; i++) {
					result.add(smsinfos.get(i));
				}
			}
		}
		return result;

	}
	
	public List<SmsFriendInfo> querySmsFriendsByContact(String phoneno) {
		List<SmsFriendInfo> friends = new ArrayList<SmsFriendInfo>();
		synchronized (this) {
			Map<String, Integer> omap = userFriends.get(phoneno);
			if ( omap != null ) {
				for ( String id : omap.keySet() ) {
					String username = userToNameMap.get(id);
					SmsFriendInfo friend = new SmsFriendInfo();
					friend.setUsername(username);
					friend.setPhoneno(id);
					friend.setContactNum(omap.get(id));
					friends.add(friend);
				}
			}
		}
		return friends;
	}
	
	public List<SmsInfo> querySmsByTimestampAndWords(List<String> words, String timestamp) {
		List<SmsInfo> result = new ArrayList<SmsInfo>();
		synchronized (smsinfoes) {
			for ( SmsInfo sms : smsinfoes ) {
				if ( match(words, sms.getContent()) && match(timestamp, sms.getTimestamp()) ) {
					result.add(sms);
				}
			}
		}
		return result;
	}
	
	public List<SmsInfo> querySmsByWords(List<String> words) {
		List<SmsInfo> result = new ArrayList<SmsInfo>();
		synchronized (smsinfoes) {
			for ( SmsInfo sms : smsinfoes ) {
				if ( match(words, sms.getContent()) ) {
					result.add(sms);
				}
			}
		}
		return result;
	}

	private boolean match(List<String> words, String content) {
		for ( String word : words ) {
			if ( content.contains(word) ) {
				return true;
			}
		}
		return false;
	}
	
	private boolean match(String timestamp, String happen) {
		
		int timestampValue = 0;
		int happenValue = 0;
		try {
			timestampValue = Integer.parseInt(timestamp);
			
			String[] times = happen.split("/");
			if ( times.length <= 1 ) {
				return false;
			}
			int year = Integer.parseInt(times[0]);
			int day = Integer.parseInt(times[1]);
			
			return ((timestampValue / 10000) == year) && (day == (timestampValue % 10000));
			
		} catch(Exception e) {
			return false;
		}
		
	}
	
}
