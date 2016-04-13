package com.zhitianweilai.qing.manager;

public abstract class BaseDuplicateUrlManager {

	public abstract void init();
	
	public abstract void init(int capacity);
	
	public abstract boolean isDuplicatable(String website, String url);
	
	public abstract void doDuplicate(String website, String url);
	
}
