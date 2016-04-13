package com.zhitianweilai.qing.url;

import java.util.List;

public interface IUrlExtractor {

	public List<String> extract(String host, String content);
	public List<String> extract(String content);
	
}
