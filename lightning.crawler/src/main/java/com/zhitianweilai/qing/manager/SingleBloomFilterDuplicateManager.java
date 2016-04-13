package com.zhitianweilai.qing.manager;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import com.lightning.crawler.framework.hash.IBloomHash;
import com.lightning.crawler.framework.hash.impl.APBloomHash;
import com.lightning.crawler.framework.hash.impl.ELFBloomHash;
import com.lightning.crawler.framework.hash.impl.JSBloomHash;
import com.lightning.crawler.framework.hash.impl.RSBloomHash;
import com.lightning.crawler.framework.hash.impl.SDBMBloomHash;

public class SingleBloomFilterDuplicateManager extends BaseDuplicateUrlManager {

	private static final int DEFAULT_CAPACITY_SIZE = 10000 * 5;
	
	private List<IBloomHash> bloomHashes = new ArrayList<IBloomHash>();

	private BitSet bitset = null;
	
	@Override
	public void init() {
		this.init(DEFAULT_CAPACITY_SIZE);
	}
	
	@Override
	public void init(int capacity) {
		int newcapacity = Math.max(DEFAULT_CAPACITY_SIZE, capacity * 100);
		bitset = new BitSet(newcapacity);
		bloomHashes.add(new SDBMBloomHash());
		bloomHashes.add(new RSBloomHash());
		bloomHashes.add(new JSBloomHash());
		bloomHashes.add(new ELFBloomHash());
		bloomHashes.add(new APBloomHash());
	}
	
	public void clear() {
		bitset.clear();
	}

	@Override
	public boolean isDuplicatable(String website, String url) {
		// TODO Auto-generated method stub
		int[] hashCodes = new int[bloomHashes.size()];
		for ( int i = 0; i < bloomHashes.size(); i++ ) {
			IBloomHash hashFunc = bloomHashes.get(i);
			hashCodes[i] = hashFunc.hash(url);
		}
		
		synchronized(bitset) {
			for ( int i = 0; i < hashCodes.length; i++ ) {
				int ndx = hashCodes[i] % bitset.size();
				if ( bitset.get(ndx) == false ) {
					return false;
				}
			}
		}
		return true;
		
	}
	
	@Override
	public void doDuplicate(String website, String url) {
		
		int[] hashCodes = new int[bloomHashes.size()];
		for ( int i = 0; i < bloomHashes.size(); i++ ) {
			IBloomHash hashFunc = bloomHashes.get(i);
			hashCodes[i] = hashFunc.hash(url);
		}
		
		synchronized(bitset) {
			for ( int i = 0; i < hashCodes.length; i++ ) {
				int ndx = hashCodes[i] % bitset.size();
				bitset.set(ndx, true);
			}
		}
		
	}

	
}
