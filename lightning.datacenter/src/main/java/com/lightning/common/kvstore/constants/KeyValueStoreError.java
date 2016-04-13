package com.lightning.common.kvstore.constants;

public enum KeyValueStoreError {

	StoreKeyNotExistError(10001, "Key Not Found"),
	
	StoreInternalServerError(20001, "Internal Server Error"),
	
	;
	
	private int errorCode;
	private String errorMessage;
	
	private KeyValueStoreError(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
