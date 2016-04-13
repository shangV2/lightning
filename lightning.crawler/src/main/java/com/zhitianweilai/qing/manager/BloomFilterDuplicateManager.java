package com.zhitianweilai.qing.manager;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lightning.crawler.framework.hash.IBloomHash;
import com.lightning.crawler.framework.hash.impl.APBloomHash;
import com.lightning.crawler.framework.hash.impl.ELFBloomHash;
import com.lightning.crawler.framework.hash.impl.JSBloomHash;
import com.lightning.crawler.framework.hash.impl.RSBloomHash;
import com.lightning.crawler.framework.hash.impl.SDBMBloomHash;

public class BloomFilterDuplicateManager extends BaseDuplicateUrlManager {

	private Map<String, BitSet> websiteBitsets = new ConcurrentHashMap<String, BitSet>();
	
	private List<IBloomHash> bloomHashes = new ArrayList<IBloomHash>();
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		websiteBitsets.put("tianshan", new BitSet(100000 * 5));
		websiteBitsets.put("tibetancn", new BitSet(100000 * 5));
		
		bloomHashes.add(new SDBMBloomHash());
		bloomHashes.add(new RSBloomHash());
		bloomHashes.add(new JSBloomHash());
		bloomHashes.add(new ELFBloomHash());
		bloomHashes.add(new APBloomHash());
		
	}

	@Override
	public boolean isDuplicatable(String website, String url) {
		// TODO Auto-generated method stub
		BitSet bitset = websiteBitsets.get(website);
		if ( bitset == null ) {
			return true;
		}
		
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
		
		BitSet bitset = websiteBitsets.get(website);
		if ( bitset == null ) {
			return;
		}
		
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

	@Override
	public void init(int capacity) {
		// TODO Auto-generated method stub
		
	}
	
	
}
