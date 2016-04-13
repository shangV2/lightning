package com.qing.askengine.model;

public class WordFreq implements Comparable<WordFreq> {

	private String word;
	
	private int freq;
	
	public WordFreq(String word, int freq) {
		super();
		this.word = word;
		this.freq = freq;
	}
	
	@Override
	public int compareTo(WordFreq o) {
		return word.compareTo(o.word);
	}

	@Override
	public boolean equals(Object obj) {
		return word.equals(((WordFreq)obj).word);
	}

	@Override
	public int hashCode() {
		return word.hashCode();
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	
}
