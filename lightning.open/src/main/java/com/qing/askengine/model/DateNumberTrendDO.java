package com.qing.askengine.model;

import java.io.Serializable;

public class DateNumberTrendDO implements Comparable<DateNumberTrendDO>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1175766481303439427L;

	private String date;
	
	private int number;
	
	public DateNumberTrendDO(String date, int number) {
		this.date = date;
		this.number = number;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public int compareTo(DateNumberTrendDO o) {
		return date.compareTo(o.date);
	}
	
}
