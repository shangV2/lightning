package com.qing.index.model;

public class SmsInfo {

	private String sendMobile;
	
	private String recvMobile;
	
	private String timestamp;
	
	private String content;
	
	private String sendUsername;
	
	private String recvUsername;

	public SmsInfo() {
	}

	public String getSendMobile() {
		return sendMobile;
	}

	public void setSendMobile(String sendMobile) {
		this.sendMobile = sendMobile;
	}

	public String getRecvMobile() {
		return recvMobile;
	}

	public void setRecvMobile(String recvMobile) {
		this.recvMobile = recvMobile;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendUsername() {
		return sendUsername;
	}

	public void setSendUsername(String sendUsername) {
		this.sendUsername = sendUsername;
	}

	public String getRecvUsername() {
		return recvUsername;
	}

	public void setRecvUsername(String recvUsername) {
		this.recvUsername = recvUsername;
	}
	
	
}
