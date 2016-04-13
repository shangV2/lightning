package com.lighting.rpc.common.exception;

public enum LightningErrorCode {
	
	// *) 公共的异常
	RPC_COMMON_INTERNAL_SERVER_ERROR(10000, "Internal Server Error"),
	RPC_COMMON_ILLEGAL_PARAMETER_ERROR(10001, "Illegal Parameter Error"),
	
	// *) 访问网页索引相关的异常错误
	RPC_WEBINDEXER_ARTICLE_NOT_FOUND_ERROR(20001, "Web Article Not Found In Index"),
	
	// *) 
	
	;
	
	private int errorCode;
	
	private String description;
	
	private LightningErrorCode(int errorCode, String description) {
		this.errorCode = errorCode;
		this.description = description;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
