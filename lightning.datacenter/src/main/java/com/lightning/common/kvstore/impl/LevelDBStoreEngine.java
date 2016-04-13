package com.lightning.common.kvstore.impl;

import static org.fusesource.leveldbjni.JniDBFactory.factory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBException;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;

import com.lightning.common.kvstore.api.ISortedKeyValueStoreEngine;
import com.lightning.common.kvstore.constants.KeyValueStoreError;
import com.lightning.common.kvstore.model.KVResult;
import com.lightning.common.kvstore.model.KeyValuePair;
import com.lightning.datacenter.utils.StringUtility;

public class LevelDBStoreEngine implements ISortedKeyValueStoreEngine {

	private DB levelDb = null;
	private ReentrantLock lock = new ReentrantLock();
	
	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		Options options = new Options();
		options.createIfMissing(true);
		
		levelDb = factory.open(new File("sample"), options);
	}

	@Override
	public void close() {
		if ( levelDb != null ) {
			try {
				levelDb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public KVResult<byte[]> get(String key) {
		KVResult<byte[]> result = new KVResult<byte[]>();
		byte[] bkey = StringUtility.toUtf8Bytes(key);
		
		byte[] bvalue = null;
		try {
			lock.lock();
			bvalue = levelDb.get(bkey);
		} catch (DBException e) {
			result.setSuccess(false);
			result.setErrorCode(KeyValueStoreError.StoreInternalServerError.getErrorCode());
			result.setErrorMsg(KeyValueStoreError.StoreInternalServerError.getErrorMessage());
			return result;
		} finally {
			lock.unlock();
		}
		if ( bvalue == null ) {
			result.setSuccess(false);
			result.setErrorCode(KeyValueStoreError.StoreKeyNotExistError.getErrorCode());
			result.setErrorMsg(KeyValueStoreError.StoreKeyNotExistError.getErrorMessage());
		} else {
			result.setSuccess(true);
			result.setValue(bvalue);
		}
		return result;
	}

	@Override
	public KVResult<Void> set(String key, byte[] values) {
		// TODO Auto-generated method stub
		KVResult<Void> result = new KVResult<Void>();
		byte[] bkey = StringUtility.toUtf8Bytes(key);
		try {
			lock.lock();
			levelDb.put(bkey, values);
		} catch (DBException e) {
			result.setSuccess(false);
			result.setErrorCode(KeyValueStoreError.StoreInternalServerError.getErrorCode());
			result.setErrorMsg(KeyValueStoreError.StoreInternalServerError.getErrorMessage());
			return result;
		} finally {
			lock.unlock();
		}
		result.setSuccess(true);
		return result;
	}

	@Override
	public KVResult<Void> delete(String key) {
		KVResult<Void> result = new KVResult<Void>();
		byte[] bkey = StringUtility.toUtf8Bytes(key);
		try {
			lock.lock();
			levelDb.delete(bkey);
		} catch (DBException e) {
			result.setSuccess(false);
			result.setErrorCode(KeyValueStoreError.StoreInternalServerError.getErrorCode());
			result.setErrorMsg(KeyValueStoreError.StoreInternalServerError.getErrorMessage());
			return result;
		} finally {
			lock.unlock();
		}
		result.setSuccess(true);
		return result;
	}

	@Override
	public KVResult<Void> update(String key, byte[] values) {
		KVResult<Void> result = new KVResult<Void>();
		byte[] bkey = StringUtility.toUtf8Bytes(key);
		try {
			lock.lock();
			levelDb.put(bkey, values);
		} catch (DBException e) {
			result.setSuccess(false);
			result.setErrorCode(KeyValueStoreError.StoreInternalServerError.getErrorCode());
			result.setErrorMsg(KeyValueStoreError.StoreInternalServerError.getErrorMessage());
			return result;
		} finally {
			lock.unlock();
		}
		result.setSuccess(true);
		return result;
	}

	@Override
	public KVResult<List<KeyValuePair> > getRange(String startKey, String endKey, int length) {
		
		KVResult<List<KeyValuePair>> result = new KVResult<List<KeyValuePair>>();
		result.setValue(new ArrayList<KeyValuePair>());
		
		DBIterator iter = null;
		try {
			lock.lock();
			iter = levelDb.iterator();
			iter.seek(StringUtility.toUtf8Bytes(startKey));
			int number = 0;
			while ( iter.hasNext() && number < length ) {
				Map.Entry<byte[], byte[]> entry = iter.next();
				String curKey = StringUtility.toUtf8String(entry.getKey());
				if ( curKey == null ) {
					break;
				}
				if ( endKey != null && endKey.compareTo(curKey) < 0 ) {
					break;
				}
				result.getValue().add(new KeyValuePair(curKey, entry.getValue()));
				number++;
			}
		} catch(DBException e) {
			result.setSuccess(false);
			result.setErrorCode(KeyValueStoreError.StoreInternalServerError.getErrorCode());
			result.setErrorMsg(KeyValueStoreError.StoreInternalServerError.getErrorMessage());
			return result;
		} finally {
			if ( iter != null ) {
				try {
					iter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			lock.unlock();
		}
		result.setSuccess(true);
		return result;
	}

	@Override
	public KVResult<List<KeyValuePair>> getRange(String startKey, String endKey) {
		
		KVResult<List<KeyValuePair>> result = new KVResult<List<KeyValuePair>>();
		result.setValue(new ArrayList<KeyValuePair>());
		
		DBIterator iter = null;
		try {
			lock.lock();
			iter = levelDb.iterator();
			iter.seek(StringUtility.toUtf8Bytes(startKey));
			while ( iter.hasNext() ) {
				Map.Entry<byte[], byte[]> entry = iter.next();
				String curKey = StringUtility.toUtf8String(entry.getKey());
				if ( curKey == null ) {
					break;
				}
				if ( endKey != null && endKey.compareTo(curKey) < 0 ) {
					break;
				}
				result.getValue().add(new KeyValuePair(curKey, entry.getValue()));
			}
		} catch(DBException e) {
			result.setSuccess(false);
			result.setErrorCode(KeyValueStoreError.StoreInternalServerError.getErrorCode());
			result.setErrorMsg(KeyValueStoreError.StoreInternalServerError.getErrorMessage());
			return result;
		} finally {
			if ( iter != null ) {
				try {
					iter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			lock.unlock();
		}
		result.setSuccess(true);
		return result;
		
	}

//	@Override
//	public void afterPropertiesSet() throws Exception {
//		// TODO Auto-generated method stub
//	}
	

}
