package com.lightning.common.kvstore.model;

import java.io.Serializable;

public class KVResult<T> implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 1026666935943766281L;

	private boolean success;
	
	private int errorCode;
	
	private String errorMsg;
	
	private T value;
	
	public KVResult() {
		this.success = false;
		this.errorCode = 1001;
		this.errorMsg = "";
	}

	public KVResult(int errorCode, String errorMsg) {
		this.success = false;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public KVResult(T value) {
		this.success = true;
		this.value = value;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
