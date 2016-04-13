package com.qing.askengine.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.qing.askengine.model.WordFreq;
import com.qing.index.text.ISummaryGenerator;
import com.qing.index.text.impl.LuceneSummaryGenerator;

public class WordSplitUtils {

	private static Analyzer ikAnalyzer = new IKAnalyzer(true);
	
	private static Analyzer standardAnalyzer = new StandardAnalyzer(Version.LUCENE_45);
	
	public static List<WordFreq> parseSplit(String content) {

		List<WordFreq> wordFreqes = new ArrayList<WordFreq>();
		Map<String, Integer> wordCounterMap = new TreeMap<String, Integer>(); 
		
		try {
			TokenStream ts = ikAnalyzer.tokenStream("", new StringReader(content));
			ts.reset();
			
			CharTermAttribute charTerm = ts.getAttribute(CharTermAttribute.class);
			while ( ts.incrementToken() ) {
				String word = charTerm.toString();
				if ( wordCounterMap.containsKey(word) ) {
					wordCounterMap.put(word, wordCounterMap.get(word) + 1);
				} else {
					wordCounterMap.put(word, 1);
				}
			}
			for ( Map.Entry<String, Integer> entry : wordCounterMap.entrySet() ) {
				wordFreqes.add(new WordFreq(entry.getKey(), entry.getValue()));
			}
			Collections.sort(wordFreqes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return wordFreqes;
	}
	
	public static List<WordFreq> parseSplitUy(String content) {

		List<WordFreq> wordFreqes = new ArrayList<WordFreq>();
		Map<String, Integer> wordCounterMap = new TreeMap<String, Integer>(); 
		
		try {
			TokenStream ts = standardAnalyzer.tokenStream("", new StringReader(content));
			ts.reset();
			
			CharTermAttribute charTerm = ts.getAttribute(CharTermAttribute.class);
			while ( ts.incrementToken() ) {
				String word = charTerm.toString();
				if ( wordCounterMap.containsKey(word) ) {
					wordCounterMap.put(word, wordCounterMap.get(word) + 1);
				} else {
					wordCounterMap.put(word, 1);
				}
			}
			for ( Map.Entry<String, Integer> entry : wordCounterMap.entrySet() ) {
				wordFreqes.add(new WordFreq(entry.getKey(), entry.getValue()));
			}
			Collections.sort(wordFreqes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return wordFreqes;
	}
	
	// -------------------------------------------------------------
	
	public static List<WordFreq> extractCommonWord(List<List<WordFreq> > wordFreqs) {
		List<WordFreq> result = new ArrayList<WordFreq>();
		if ( wordFreqs.size() >= 1 ) { 
			result.addAll(wordFreqs.get(0));
			for ( int i = 1; i < wordFreqs.size(); i++ ) {
				result = commonWordFromTwoList(result, wordFreqs.get(i));
			}
		}
		return result;
	}
	
	public static List<WordFreq> extractCommonWord(List<WordFreq> [] wordFreqs) {
		List<WordFreq> result = new ArrayList<WordFreq>();
		if ( wordFreqs.length >= 1 ) { 
			result.addAll(wordFreqs[0]);
			for ( int i = 1; i < wordFreqs.length; i++ ) {
				result = commonWordFromTwoList(result, wordFreqs[i]);
			}
		}
		return result;
	}
	
	public static List<WordFreq> commonWordFromTwoList(List<WordFreq> one, List<WordFreq> two) {
		List<WordFreq> result = new ArrayList<WordFreq>();
		int idx1 = 0, idx2 = 0;
		while ( idx1 < one.size() && idx2 < two.size() ) {
			WordFreq w1 = one.get(idx1);
			WordFreq w2 = two.get(idx2);
			int eqs = w1.getWord().compareTo(w2.getWord());
			if ( eqs == 0 ) {
				idx1++; 
				idx2++;
				result.add(new WordFreq(w1.getWord(), w1.getFreq() + w2.getFreq()));
			} else if ( eqs < 0 ) {
				idx1++;
			} else if ( eqs > 0 ) {
				idx2++;
			}
		}
		return result;
	}
	
	public static String generateSummary(String field, String article, String content, int wordNum) {
		ISummaryGenerator generator = new LuceneSummaryGenerator(ikAnalyzer, "content");
		QueryParser parser = new QueryParser(Version.LUCENE_45, "content", ikAnalyzer);
		Query query;
		try {
			query = parser.parse(article);
			String summaryFragement = generator.summary(content, query, wordNum);
			if ( summaryFragement != null ) {
				return summaryFragement;
			} else {
				return content.substring(0, Math.min(content.length(), wordNum));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return content.substring(0, Math.min(content.length(), wordNum));
		
	}
	
	public static String generateLanguageSummary(String languageType, String field, String article, String content, int wordNum) {
		
		ISummaryGenerator generator = null;
		if ( "cn".equalsIgnoreCase(languageType) ) {
			generator = new LuceneSummaryGenerator(ikAnalyzer, "content");
		} else {
			generator = new LuceneSummaryGenerator(standardAnalyzer, "content");
		}
		
		QueryParser parser = new QueryParser(Version.LUCENE_45, "content", ikAnalyzer);
		Query query;
		try {
			query = parser.parse(article);
			String summaryFragement = generator.summary(content, query, wordNum);
			if ( summaryFragement != null ) {
				return summaryFragement;
			} else {
				return content.substring(0, Math.min(content.length(), wordNum));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return content.substring(0, Math.min(content.length(), wordNum));
		
	}
	
	
}
