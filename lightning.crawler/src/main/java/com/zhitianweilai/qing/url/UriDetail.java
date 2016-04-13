package com.zhitianweilai.qing.url;

public class UriDetail {

	private String schema;
	
	private String host;
	
	private String path;

	public UriDetail() {
	}

	public UriDetail(String schema, String host, String path) {
		this.schema = schema;
		this.host = host;
		this.path = path;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
