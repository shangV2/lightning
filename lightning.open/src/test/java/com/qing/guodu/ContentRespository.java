package com.qing.guodu;

import java.util.ArrayList;
import java.util.List;

public class ContentRespository {

	private List<WebpageConsumer> webpageConsumers = new ArrayList<WebpageConsumer>();
	
	private String dataPath = "data/";
	
	public void registerWebpageConsumer(WebpageConsumer consumer) {
		webpageConsumers.add(consumer);
	}
	
	public void loadWebPage() {
		FileDataSource dataSource = new FileDataSource();
		dataSource.loadData(dataPath, new IWebPageHandler() {
			@Override
			public void onHandleWebPage(WebPage page) {
				for ( WebpageConsumer consumer : webpageConsumers ) {
					consumer.consume(page);
				}
			}
		});
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	
}
