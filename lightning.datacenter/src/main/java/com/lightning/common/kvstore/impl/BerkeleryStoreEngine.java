package com.lightning.common.kvstore.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import com.lightning.common.kvstore.api.IKeyValueStoreEngine;
import com.lightning.common.kvstore.config.BerkeleyStoreConfiguration;
import com.lightning.common.kvstore.constants.KeyValueStoreError;
import com.lightning.common.kvstore.model.KVResult;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockConflictException;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;
import com.sleepycat.je.Transaction;
import com.sleepycat.je.TransactionConfig;

public class BerkeleryStoreEngine implements IKeyValueStoreEngine {

	private BerkeleyStoreConfiguration configuration = null;
	
	private Environment dbEnvironment = null;

	private Database database = null;
	
	public BerkeleryStoreEngine() {
	}
	
	public BerkeleryStoreEngine(BerkeleyStoreConfiguration configuration) {
		this.configuration = configuration;
	}

	public void init() throws Exception {
		// TODO Auto-generated method stub

		String dbDirname = configuration.getDbDirname();
		String dbFilename = configuration.getDbFilename();
		long txnTimeout = configuration.getTxnTimeout();
		long lockTimeout = configuration.getLockTimeout();

		EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setAllowCreate(true);
		envConfig.setTransactional(true);
		envConfig.setReadOnly(false);
		envConfig.setTxnTimeout(txnTimeout, TimeUnit.MILLISECONDS);
		envConfig.setLockTimeout(lockTimeout, TimeUnit.MILLISECONDS);

		File file = new File(dbDirname);
		if (!file.exists()) {
			file.mkdirs();
		}
		dbEnvironment = new Environment(file, envConfig);

		DatabaseConfig dbConfig = new DatabaseConfig();
		dbConfig.setAllowCreate(true);
		dbConfig.setTransactional(true);
		dbConfig.setReadOnly(false);

		database = dbEnvironment.openDatabase(null, dbFilename, dbConfig);

	}

	public void close() {
		// TODO Auto-generated method stub
		if (database != null) {
			database.close();
		}
		if (dbEnvironment != null) {
			dbEnvironment.cleanLog();
			dbEnvironment.close();
		}
	}

	public KVResult<byte[]> get(String key) {

		KVResult<byte[]> result = new KVResult<byte[]>();

		DatabaseEntry dataKey = null;
		try {
			dataKey = new DatabaseEntry(key.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		DatabaseEntry dataValue = new DatabaseEntry();
		Transaction txn = null;
		try {
			TransactionConfig txConfig = new TransactionConfig();
			txConfig.setSerializableIsolation(true);
			txn = dbEnvironment.beginTransaction(null, txConfig);
			OperationStatus res = database.get(txn, dataKey, dataValue,
					LockMode.DEFAULT);
			txn.commit();
			if ( OperationStatus.SUCCESS == res ) {
				byte[] resData = dataValue.getData();
				result.setSuccess(true);
				result.setValue(resData);
				return result;
			} else if ( OperationStatus.NOTFOUND == res ) {
				result.setSuccess(false);
				result.setErrorCode(KeyValueStoreError.StoreKeyNotExistError.getErrorCode());
				result.setErrorMsg(KeyValueStoreError.StoreKeyNotExistError.getErrorMessage());
				return result;
			} else {
				result.setSuccess(false);
				result.setErrorCode(KeyValueStoreError.StoreInternalServerError.getErrorCode());
				result.setErrorMsg(KeyValueStoreError.StoreInternalServerError.getErrorMessage());
				return result;
			}
		} catch (LockConflictException lockConflict) {
			txn.abort();
		}

		return result;
	}

	public KVResult<Void> set(String key, byte[] values) {
		// TODO Auto-generated method stub

		KVResult<Void> result = new KVResult<Void>();

		DatabaseEntry dataKey = null;
		try {
			dataKey = new DatabaseEntry(key.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		DatabaseEntry dataValue = new DatabaseEntry(values);
		Transaction txn = null;

		OperationStatus res = null;
		try {
			TransactionConfig txConfig = new TransactionConfig();
			txConfig.setSerializableIsolation(true);
			txn = dbEnvironment.beginTransaction(null, txConfig);
			res = database.put(txn, dataKey, dataValue);
			txn.commit();
			if (res == OperationStatus.SUCCESS) {
				result.setSuccess(true);
				return result;
			} else if (res == OperationStatus.KEYEXIST) {
			} else {
			}
		} catch (LockConflictException lockConflict) {
			txn.abort();
		}
		return result;

	}

	public KVResult<Void> delete(String key) {
		// TODO Auto-generated method stub
		
		KVResult<Void> result = new KVResult<Void>();

		DatabaseEntry dataKey = null;
		try {
			dataKey = new DatabaseEntry(key.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		TransactionConfig txConfig = new TransactionConfig();
		txConfig.setSerializableIsolation(true);
		Transaction txn = dbEnvironment.beginTransaction(null, txConfig);
		
		OperationStatus res = database.delete(txn, dataKey);
		txn.commit();
		if (res == OperationStatus.SUCCESS) {
			result.setSuccess(true);
			return result;
		} else if (res == OperationStatus.KEYEMPTY) {
			
		} else {
			
		}
		
		return result;
		
	}

	public KVResult<Void> update(String key, byte[] values) {
		// TODO Auto-generated method stub
		KVResult<Void> result = new KVResult<Void>();

		DatabaseEntry dataKey = null;
		try {
			dataKey = new DatabaseEntry(key.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		DatabaseEntry dataValue = new DatabaseEntry(values);
		Transaction txn = null;

		OperationStatus res = null;
		try {
			TransactionConfig txConfig = new TransactionConfig();
			txConfig.setSerializableIsolation(true);
			txn = dbEnvironment.beginTransaction(null, txConfig);
			res = database.put(txn, dataKey, dataValue);
			txn.commit();
			if (res == OperationStatus.SUCCESS) {
				result.setSuccess(true);
				return result;
			} else if (res == OperationStatus.KEYEXIST) {
			} else {
			}
		} catch (LockConflictException lockConflict) {
			txn.abort();
		}
		return result;
		
	}
	
//	public BerkeleyStoreConfiguration getConfiguration() {
//		return configuration;
//	}
//
	
	public void setConfiguration(BerkeleyStoreConfiguration configuration) {
		this.configuration = configuration;
	}

}
