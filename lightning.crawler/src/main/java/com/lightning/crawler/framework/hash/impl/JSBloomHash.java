package com.lightning.crawler.framework.hash.impl;

import com.lightning.crawler.framework.hash.IBloomHash;

public class JSBloomHash implements IBloomHash {
	@Override
	public int hash(String url) {
		int hashCode = 1315423911;
		int index = 0;
		while ( index < url.length() ) {
			hashCode = (hashCode << 5) + url.charAt(index) + (hashCode >> 2);
			index++;
		}
		return (hashCode & 0x7FFFFFFF);
	}
}
