package com.qing.askengine.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

import com.qing.askengine.model.DigitalDateSplitTrendDO;
import com.qing.askengine.model.DigitalDateTrendDO;
import com.qing.askengine.model.FreqWordDO;
import com.qing.askengine.utils.TimeUtils;

/**
 *
 *
 */
public class DigitalCenterManager {


	// ===============================================================================
	
	private Random random = new Random();

	/*
	private List<String> hotWordList = new ArrayList<String>();
	
	private Map<String, List<Integer>> hotWordMap = new TreeMap<String, List<Integer>>();
	
	private ReentrantLock hotLock = new ReentrantLock();
	
	private Map<String, List<Integer> > sensitiveWordFreqMap = new ConcurrentHashMap<String, List<Integer>>(new TreeMap<String, List<Integer> >());

	private ReentrantLock sensitiveLock = new ReentrantLock();
	
	private PersistentManager sensitivePersistentManager = new PersistentManager();
	*/
	
	// ===============================================================================
	private List<String> unusualWordList = new ArrayList<String>();
	private Map<String, List<Integer> > unusualWordMap = new TreeMap<String, List<Integer> >();
	
	private ReentrantLock unusualLock = new ReentrantLock();
	
	// ===============================================================================
	
	
	public void init() {
		
		initializeUnusualWord();
	
	}
	
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	
	private void initializeUnusualWord() {
		
		String[] words = new String[] {
				"天王盖地虎", "宝塔镇河妖",
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
				if ( freqs != null && i < freqs.size() ) {
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
