package com.lightning.crawler.framework.hash.impl;

import com.lightning.crawler.framework.hash.IBloomHash;


public class ELFBloomHash implements IBloomHash {
	@Override
	public int hash(String url) {
		int hashCode = 0;
		int x = 0;
		int index = 0;
		while ( index < url.length() ) {
			hashCode = (hashCode << 4) + url.charAt(index);
			if ( (x = (hashCode & 0xF0000000)) != 0 ) {
				hashCode ^= (x >> 24);
				hashCode &= ~x;
			}
			index++;
		}
		return (hashCode & 0x7FFFFFFF);
	}
}
