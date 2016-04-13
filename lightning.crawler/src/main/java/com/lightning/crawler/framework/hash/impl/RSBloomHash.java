package com.lightning.crawler.framework.hash.impl;

import com.lightning.crawler.framework.hash.IBloomHash;

public class RSBloomHash implements IBloomHash {
	@Override
	public int hash(String url) {
		int hashCode = 0;
		int b = 3785551;
		int a = 63689;
		int index = 0;
		while ( index < url.length() ) {
			hashCode = hashCode * a + url.charAt(index);
			index++;
		}
		return (hashCode & 0x7FFFFFFF);
	}
}