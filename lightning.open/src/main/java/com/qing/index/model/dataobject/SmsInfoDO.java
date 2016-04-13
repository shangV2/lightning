package com.qing.index.model.dataobject;

import java.io.Serializable;

public class SmsInfoDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1162890584833147717L;
	
	private long docid;

	private String sendMobile;
	
	private String recvMobile;
	
	private String timestamp;
	
	private String content;
	
	private String sendUsername;
	
	private String recvUsername;

	public long getDocid() {
		return docid;
	}

	public void setDocid(long docid) {
		this.docid = docid;
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
