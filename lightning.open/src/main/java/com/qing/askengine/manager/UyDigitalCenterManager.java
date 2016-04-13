package com.qing.askengine.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import com.qing.askengine.model.DigitalDateSplitTrendDO;
import com.qing.askengine.model.DigitalDateTrendDO;
import com.qing.askengine.model.FreqWordDO;
import com.qing.askengine.utils.TimeUtils;

/**
 *
 * 该类专门负责各类词频的统计和分析
 *
 */
public class UyDigitalCenterManager {


	// ===============================================================================
	
	private Random random = new Random();
	
//	// 热点词相
//	private List<String> hotWordList = new ArrayList<String>();
//	private Map<String, List<Integer>> hotWordMap = new TreeMap<String, List<Integer>>();
//	private ReentrantLock hotLock = new ReentrantLock();
	
//	// 敏感词列
//	private Map<String, List<Integer> > sensitiveWordFreqMap = new ConcurrentHashMap<String, List<Integer>>(new TreeMap<String, List<Integer> >());
//	private ReentrantLock sensitiveLock = new ReentrantLock();
//	// *) 用于敏感词的存储
//	private PersistentManager sensitivePersistentManager = new PersistentManager();
//	
	// ===============================================================================
	// 异常词相关的
	private List<String> unusualWordList = new ArrayList<String>();
	// 异常词词频列
	private Map<String, List<Integer> > unusualWordMap = new TreeMap<String, List<Integer> >();
	
	private ReentrantLock unusualLock = new ReentrantLock();
	
	// ===============================================================================
	
	
	
	public void init() {
		
		// *) 热点词初始化
//		initializeHotWord();
		// *) 敏感词初始化
//		initializeSensitiveWord();
		
		// *) 异常词初始化
		initializeUnusualWord();
	
	}
	
//	private void initializeHotWord() {
//		String[] words = new String[] {
//				"ئىگىلىۋالغان", "جەنۇبىنى", "نەچچە",
//		};
//		for ( String word : words ) {
//			List<Integer> list = new ArrayList<Integer>();
//			for (int i = 0; i < 7; i++) {
//				list.add(random.nextInt(120) + 10);
//			}
//			hotWordList.add(word);
//			hotWordMap.put(word, list);
//		}
//	}
	
//	public List<String> fetchHotWordList(int pageno, int pagesize) {
//		List<String> result = new ArrayList<String>();
//		try {
//			hotLock.lock();
//			int start = pageno * pagesize;
//			int end = (pageno + 1) * pagesize;
//			for ( int i = start; i < end && i < hotWordList.size(); i++ ) {
//				result.add(hotWordList.get(i));
//			}
//		} finally {
//			hotLock.unlock();
//		}
//		return result;
//	}
	
//	public int getHotTotalWords() {
//		int result = 0;
//		try {
//			hotLock.lock();
//			result = hotWordList.size();
//		} finally {
//			hotLock.unlock();
//		}
//		return result;
//	}
	
//	public List<DigitalDateTrendDO> fetchHotWordTrend4time(int startdate, int enddate) {
//		List<DigitalDateTrendDO> result = new ArrayList<DigitalDateTrendDO>();
//		
//		int numdate = TimeUtils.diffBetweenDays(startdate, enddate) + 1;
//		
//		int year = enddate / 10000;
//		int month = (enddate / 100) % 100;
//		int day = enddate % 100;
//		if ( !TimeUtils.checkValidateDate(year, month, day) ) {
//			return result;
//		}
//		
//		try {
//			hotLock.lock();
//			for ( int i = 0; i < numdate; i++ ) {
//				DigitalDateTrendDO set = new DigitalDateTrendDO();
//				set.setFreqWordDOs(new ArrayList<FreqWordDO>());
//				set.setDate(TimeUtils.beforeDate(year, month, day, i));
//				for ( String key : hotWordMap.keySet() ) {
//					List<Integer> freqs = hotWordMap.get(key);
//					FreqWordDO wi = new FreqWordDO();
//					wi.setWord(key);
//					if ( freqs != null && i < freqs.size() ) {
//						wi.setFreq(freqs.get(i));
//					} else {
//						wi.setFreq(0);
//					}
//					set.getFreqWordDOs().add(wi);
//				}
//				result.add(set);
//			}
//		} finally {
//			hotLock.unlock();
//		}
//		return result;
//	}
	
	// @
//	public List<DigitalDateSplitTrendDO> fetchSplitHotWordTrend4time(String word, int startdate, int enddate) {
//		List<DigitalDateSplitTrendDO> result = new ArrayList<DigitalDateSplitTrendDO>();
//		
//		int numdate = TimeUtils.diffBetweenDays(startdate, enddate) + 1;
//		
//		int year = enddate / 10000;
//		int month = (enddate / 100) % 100;
//		int day = enddate % 100;
//		if ( !TimeUtils.checkValidateDate(year, month, day) ) {
//			return result;
//		}
//		
//		try {
//			hotLock.lock();
//			List<Integer> freqs = hotWordMap.get(word);
//			for ( int i = 0; i < numdate; i++ ) {
//				DigitalDateSplitTrendDO trend = new DigitalDateSplitTrendDO();
//				trend.setDate(TimeUtils.beforeDate(year, month, day, i));
//				if (  freqs != null && i < freqs.size() ) {
//					int freqval = freqs.get(i);
//					trend.setWebfreq(freqval / 3 * 2);
//					trend.setSmsfreq(freqval / 3);
//				} else {
//					trend.setWebfreq(0);
//					trend.setSmsfreq(0);
//				}
//				result.add(trend);
//			}
//		} finally {
//			hotLock.unlock();
//		}
//		return result;
//	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
//	private void initializeSensitiveWord() {
//		// 敏感词初始化
//		sensitivePersistentManager.setDatapath("./uyword.per");
//		sensitivePersistentManager.init();
//		
//		List<String> words = sensitivePersistentManager.getWords();
//		
//		for ( String word : words ) {
//			List<Integer> freqs = new ArrayList<Integer>();
//			for ( int i = 0; i < 7; i++ ) {
//				freqs.add(random.nextInt(100) + 10);
//			}
//			sensitiveWordFreqMap.put(word, freqs);
//		}
//	}
	
//	public List<String> fetchSensitiveWordList(int pageno, int pagesize) {
//		List<String> result = new ArrayList<String>();
//		try {
//			sensitiveLock.lock();
//			List<String> words = sensitivePersistentManager.getWords();
//			int start = pageno * pagesize;
//			int end = (pageno + 1) * pagesize;
//			for ( int i = start; i < end && i < words.size(); i++ ) {
//				result.add(words.get(i));
//			}
//		} finally {
//			sensitiveLock.unlock();
//		}
//		return result;
//	}
	
//	public int getSensitiveTotalWords() {
//		int result = 0;
//		try {
//			sensitiveLock.lock();
//			result = sensitivePersistentManager.getWords().size();
//		} finally {
//			sensitiveLock.unlock();
//		}
//		return result;
//	}
	
//	public void addSensitiveWord(String word) {
//		try {
//			sensitiveLock.lock();
//			if ( !sensitivePersistentManager.isDuplicateWord(word) ) {
//				sensitivePersistentManager.addWord(word);
//				List<Integer> freqs = new ArrayList<Integer>();
//				for ( int i = 0; i < 7; i++ ) {
//					freqs.add(random.nextInt(100) + 10);
//				}
//				sensitiveWordFreqMap.put(word, freqs);
//			} 
//		} finally {
//			sensitiveLock.unlock();
//		}
//	}
	
//	public void removeSensitiveWord(String word) {
//		try {
//			sensitiveLock.lock();
//			if ( sensitivePersistentManager.isDuplicateWord(word) ) {
//				sensitivePersistentManager.removeWord(word);
//				sensitiveWordFreqMap.remove(word);
//			}
//		} finally {
//			sensitiveLock.unlock();
//		}
//	}
	
//	public void modifySensitiveWord(String oword, String nword) {
//		if ( oword.equals(nword) ) {
//			return;
//		}
//		removeSensitiveWord(oword);
//		addSensitiveWord(nword);
//	}
	
//	public List<DigitalDateTrendDO> fetchSensitiveWordTrend4time(int startdate, int enddate) {
//		List<DigitalDateTrendDO> result = new ArrayList<DigitalDateTrendDO>();
//		
//		int numdate = TimeUtils.diffBetweenDays(startdate, enddate) + 1;
//		
//		int year = enddate / 10000;
//		int month = (enddate / 100) % 100;
//		int day = enddate % 100;
//		if ( !TimeUtils.checkValidateDate(year, month, day) ) {
//			return result;
//		}
//		
//		try {
//			sensitiveLock.lock();
//			for ( int i = 0; i < numdate; i++ ) {
//				DigitalDateTrendDO set = new DigitalDateTrendDO();
//				set.setFreqWordDOs(new ArrayList<FreqWordDO>());
//				set.setDate(TimeUtils.beforeDate(year, month, day, i));
//				for ( String key : sensitiveWordFreqMap.keySet() ) {
//					List<Integer> freqs = sensitiveWordFreqMap.get(key);
//					FreqWordDO wi = new FreqWordDO();
//					wi.setWord(key);
//					if ( freqs != null && i < freqs.size() ) {
//						wi.setFreq(freqs.get(i));
//					} else {
//						wi.setFreq(0);
//					}
//					set.getFreqWordDOs().add(wi);
//				}
//				result.add(set);
//			}
//		} finally {
//			sensitiveLock.unlock();
//		}
//		return result;
//	}
	
	
//	public List<DigitalDateSplitTrendDO> fetchSplitSensitiveWordTrend4time(String word, int startdate, int enddate) {
//		List<DigitalDateSplitTrendDO> result = new ArrayList<DigitalDateSplitTrendDO>();
//		
//		int numdate = TimeUtils.diffBetweenDays(startdate, enddate) + 1;
//		
//		int year = enddate / 10000;
//		int month = (enddate / 100) % 100;
//		int day = enddate % 100;
//		if ( !TimeUtils.checkValidateDate(year, month, day) ) {
//			return result;
//		}
//		
//		try {
//			sensitiveLock.lock();
//			List<Integer> freqs = sensitiveWordFreqMap.get(word);
//			for ( int i = 0; i < numdate; i++ ) {
//				DigitalDateSplitTrendDO trend = new DigitalDateSplitTrendDO();
//				trend.setDate(TimeUtils.beforeDate(year, month, day, i));
//				if ( freqs != null && i < freqs.size() ) {
//					int freqval = freqs.get(i);
//					trend.setWebfreq(freqval / 3 * 2);
//					trend.setSmsfreq(freqval / 3);
//				} else {
//					trend.setWebfreq(0);
//					trend.setSmsfreq(0);
//				}
//				result.add(trend);
//			}
//		} finally {
//			sensitiveLock.unlock();
//		}
//		return result;
//	}
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	
	private void initializeUnusualWord() {
		
		String[] words = new String[] {
				"ھۆكۈمەت", "شەرقىي",
		};
		
		Random random = new Random();
		for ( String word : words ) {
			unusualWordList.add(word);
			List<Integer> freqs = new ArrayList<Integer>();
			for ( int i = 0; i < 21; i++ ) {
				freqs.add(random.nextInt(100) + 10);
			}
			unusualWordMap.put(word, freqs);
		}
		
	}
	
	public List<String> fetchUnusualWordList(int pageno, int pagesize) {
		List<String> result = new ArrayList<String>();
		try {
			unusualLock.lock();
			int start = pageno * pagesize;
			int end = (pageno + 1) * pagesize;
			for ( int i = start; i < end && i < unusualWordList.size(); i++ ) {
				result.add(unusualWordList.get(i));
			}
		} finally {
			unusualLock.unlock();
		}
		return result;
	}
	
	public int fetchUnusualTotalWords() {
		int result = 0;
		try {
			unusualLock.lock();
			result = unusualWordList.size();
		} finally {
			unusualLock.unlock();
		}
		return result;
	}
	
	public List<DigitalDateTrendDO> fetchUnusualWordTrend4time(int startdate, int enddate) {
		List<DigitalDateTrendDO> result = new ArrayList<DigitalDateTrendDO>();
		
		int numdate = TimeUtils.diffBetweenDays(startdate, enddate) + 1;
		
		int year = enddate / 10000;
		int month = (enddate / 100) % 100;
		int day = enddate % 100;
		if ( !TimeUtils.checkValidateDate(year, month, day) ) {
			return result;
		}
		
		try {
			unusualLock.lock();
			for ( int i = 0; i < numdate; i++ ) {
				DigitalDateTrendDO set = new DigitalDateTrendDO();
				set.setFreqWordDOs(new ArrayList<FreqWordDO>());
				set.setDate(TimeUtils.beforeDate(year, month, day, i));
				for ( String key : unusualWordMap.keySet() ) {
					List<Integer> freqs = unusualWordMap.get(key);
					FreqWordDO wi = new FreqWordDO();
					wi.setWord(key);
					if ( freqs != null && i < freqs.size() ) {
						wi.setFreq(freqs.get(i));
					} else {
						wi.setFreq(0);
					}
					set.getFreqWordDOs().add(wi);
				}
				result.add(set);
			}
		} finally {
			unusualLock.unlock();
		}
		return result;
	}
	
	public List<DigitalDateSplitTrendDO> fetchSplitUnusualWordTrend4time(String word, int startdate, int enddate) {
		List<DigitalDateSplitTrendDO> result = new ArrayList<DigitalDateSplitTrendDO>();
		
		int numdate = TimeUtils.diffBetweenDays(startdate, enddate) + 1;
		
		int year = enddate / 10000;
		int month = (enddate / 100) % 100;
		int day = enddate % 100;
		if ( !TimeUtils.checkValidateDate(year, month, day) ) {
			return result;
		}
		
		try {
			unusualLock.lock();
			List<Integer> freqs = unusualWordMap.get(word);
			for ( int i = 0; i < numdate; i++ ) {
				DigitalDateSplitTrendDO trend = new DigitalDateSplitTrendDO();
				trend.setDate(TimeUtils.beforeDate(year, month, day, i));
				if (  freqs != null && i < freqs.size() ) {
					int freqval = freqs.get(i);
					trend.setWebfreq(freqval / 3 * 2);
					trend.setSmsfreq(freqval / 3);
				} else {
					trend.setWebfreq(0);
					trend.setSmsfreq(0);
				}
				result.add(trend);
			}
		} finally {
			unusualLock.unlock();
		}
		return result;
	}
	
	
}
