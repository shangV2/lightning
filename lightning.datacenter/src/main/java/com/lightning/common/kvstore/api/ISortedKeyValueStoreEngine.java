package com.lightning.common.kvstore.api;

import java.util.List;

import com.lightning.common.kvstore.model.KVResult;
import com.lightning.common.kvstore.model.KeyValuePair;

public interface ISortedKeyValueStoreEngine extends IKeyValueStoreEngine {

	/**
	 * 
	 * @param startKey
	 * @param endKey
	 * @param length
	 * @return
	 */
	public KVResult<List<KeyValuePair> > getRange(String startKey, String endKey, int length);
	
	/**
	 * 
	 * @param startKey
	 * @param endKey
	 * @return
	 */
	public KVResult<List<KeyValuePair> > getRange(String startKey, String endKey); 
	
}
