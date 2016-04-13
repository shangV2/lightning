package com.lightning.common.kvstore.api;

import com.lightning.common.kvstore.model.KVResult;

public interface IKeyValueStoreEngine {

	
	/**
	 * 
	 * @return
	 */
	public void init() throws Exception;
	
	/**
	 * 
	 */
	public void close();
	
	
	// ------------------------------------------------------
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public KVResult<byte[]> get(String key);
	
	/**
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public KVResult<Void> set(String key, byte[] values);
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public KVResult<Void> delete(String key);
	
	
	/**
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public KVResult<Void> update(String key, byte[] values);
	
	
}
