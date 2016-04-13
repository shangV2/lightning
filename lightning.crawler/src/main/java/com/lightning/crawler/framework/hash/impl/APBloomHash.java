package com.lightning.crawler.framework.hash.impl;

import com.lightning.crawler.framework.hash.IBloomHash;


public class APBloomHash implements IBloomHash {
	@Override
	public int hash(String url) {
		int hashCode = 0;
		for ( int i = 0; i < url.length(); i++ ) {
			if ( (i & 1) == 0 ) {
				hashCode ^= (hashCode << 7) ^ url.charAt(i) ^ (hashCode >> 3);
			} else {
				hashCode ^= (~((hashCode << 11) ^ url.charAt(i) ^ (hashCode >> 5)));
			}
		}
		return (hashCode & 0x7FFFFFFF);
	}
}