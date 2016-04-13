package com.lightning.crawler.framework.hash.impl;

import com.lightning.crawler.framework.hash.IBloomHash;

public class SDBMBloomHash implements IBloomHash {
	@Override
	public int hash(String url) {
		int hashCode = 0;
		int index = 0;
		while ( index < url.length() ) {
			hashCode = url.charAt(index) + (hashCode << 6) + (hashCode << 16) - hashCode;
			index++;
		}
		return (hashCode & 0x7FFFFFFF);
	}
}