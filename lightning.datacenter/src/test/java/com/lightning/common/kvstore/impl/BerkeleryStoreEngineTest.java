package com.lightning.common.kvstore.impl;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import com.lightning.common.kvstore.api.IKeyValueStoreEngine;
import com.lightning.common.kvstore.config.BerkeleyStoreConfiguration;
import com.lightning.common.kvstore.constants.KeyValueStoreError;
import com.lightning.common.kvstore.model.KVResult;

public class BerkeleryStoreEngineTest {

	@Test
	public void test1001() {

		BerkeleyStoreConfiguration configuration = new BerkeleyStoreConfiguration();
		configuration.setDbDirname("dbdir");
		configuration.setDbFilename("dbfilename");

		IKeyValueStoreEngine engine = new BerkeleryStoreEngine(configuration);

		// Properties properties = new Properties();
		// engine.init(properties);
		try {
			engine.init();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String key = "hello";
		String value = "你好";

		try {
			KVResult<Void> kvrc1 = engine.set(key, value.getBytes("UTF-8"));
			Assert.assertEquals(kvrc1.isSuccess(), true);

			KVResult<byte[]> kvrc2 = engine.get(key);
			Assert.assertEquals(kvrc2.isSuccess(), true);
			Assert.assertEquals(new String(kvrc2.getValue(), "UTF-8"), value);

			System.out.println("value: "
					+ new String(kvrc2.getValue(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		engine.close();

	}

	@Test
	public void testConcurrent() {

		BerkeleyStoreConfiguration configuration = new BerkeleyStoreConfiguration();
		configuration.setDbDirname("dbdir");
		configuration.setDbFilename("dbfilename");

		final IKeyValueStoreEngine engine = new BerkeleryStoreEngine(
				configuration);

		// Properties properties = new Properties();
		// engine.init(properties);
		try {
			engine.init();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ExecutorService executors = Executors.newCachedThreadPool();

		for (int i = 0; i < 20; i++) {
			executors.execute(new Runnable() {
				private Random random = new Random();

				public void run() {
					// TODO Auto-generated method stub
					for (int i = 0; i < 2000; i++) {
						int v = ((random.nextInt() % 10) + 10) % 10;
						int vt = ((random.nextInt() % 200) + 200) % 20;
						if (v == 0) {
							String key = vt + "key";
							String value = random.nextInt() + "value";
							engine.set(key, value.getBytes());
						} else {
							String key = vt + "key";
							KVResult<byte[]> rc = engine.get(key);
							if (rc.isSuccess()) {
								System.out.println(new String(rc.getValue()));
							}
						}
					}
				}
			});
		}

		executors.shutdown();

		while (!executors.isTerminated()) {
			try {
				executors.awaitTermination(2, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		engine.close();

	}

	@Test
	public void testRecover() {

		BerkeleyStoreConfiguration configuration = new BerkeleyStoreConfiguration();
		configuration.setDbDirname("dbdir");
		configuration.setDbFilename("dbfilename");

		IKeyValueStoreEngine engine = new BerkeleryStoreEngine(configuration);

		try {
			engine.init();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		try {
			KVResult<Void> kvrc1 = engine.set("lq124",
					"value".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		engine.close();

		try {
			engine.init();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		KVResult<byte[]> rc = engine.get("lq124");
		try {
			System.out.println("VALUE: " + new String(rc.getValue(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		engine.close();

	}

	@Test
	public void test1004() {

		BerkeleyStoreConfiguration configuration = new BerkeleyStoreConfiguration();
		configuration.setDbDirname("dbdir");
		configuration.setDbFilename("dbfilename");

		IKeyValueStoreEngine engine = new BerkeleryStoreEngine(configuration);
		try {
			engine.init();
		} catch (Exception e1) {
			e1.printStackTrace();

			String key = "hello_xxxxxxxxxxxxxx_key_not_found";

			KVResult<byte[]> kvrc2 = engine.get(key);
			Assert.assertEquals(kvrc2.isSuccess(), false);
			Assert.assertEquals(kvrc2.getErrorCode(),
					KeyValueStoreError.StoreKeyNotExistError.getErrorCode());
			Assert.assertEquals(kvrc2.getErrorMsg(),
					KeyValueStoreError.StoreKeyNotExistError.getErrorMessage());

			engine.close();

		}

	}

}
