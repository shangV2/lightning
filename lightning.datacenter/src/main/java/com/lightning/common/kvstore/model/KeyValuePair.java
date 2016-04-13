package com.lightning.common.kvstore.model;

import java.io.Serializable;

public class KeyValuePair implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5797138452216191087L;

	private String key;
	
	private byte[] value;

	public KeyValuePair() {
	}
	
	public KeyValuePair(String key, byte[] value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}
	
}
