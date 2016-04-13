package com.lightning.crawler.taskfetcher.impl;

import java.util.Properties;

import javax.annotation.Resource;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lighting.rpc.common.exception.LightningServiceException;
import com.lighting.rpc.webmetaserver.model.WMSApplyCrawlerTaskRequest;
import com.lighting.rpc.webmetaserver.model.WMSApplyCrawlerTaskResponse;
import com.lighting.rpc.webmetaserver.model.WMSCrawlerWebsite;
import com.lighting.rpc.webmetaserver.model.WMSTaskStatus;
import com.lighting.rpc.webmetaserver.service.WebMetaServerService;
import com.lightning.crawler.convertor.LanguageConvertor;
import com.lightning.crawler.convertor.WebMetaServerConvertor;
import com.lightning.crawler.taskfetcher.ITaskFetcher;
import com.lightning.crawler.utility.LogidUtility;
import com.zhitianweilai.qing.crawler.task.DailyWebCrawlerTask;
import com.zhitianweilai.qing.utils.TimeHelper;

public class MetaServerTaskFetcher implements ITaskFetcher {

	private static final Logger logger = LoggerFactory.getLogger("rpc_logger");
	
	@Resource
	private WebMetaServerService.Iface webMetaServerService;
	
	private int languageType = 0;
	
	@Override
	public void initialize(Properties props) {
	}

	@Override
	public DailyWebCrawlerTask fetch() {

		StringBuilder sb = new StringBuilder();
		
		try {
			
			WMSApplyCrawlerTaskRequest request = new WMSApplyCrawlerTaskRequest();
			request.setLogid(LogidUtility.genLogid());
			request.setWebsiteType(LanguageConvertor.convert(languageType));
			
			sb.append(String.format("[request: {logid: %d, website_type: %d}]", 
					request.getLogid(), request.getWebsiteType().getValue()));
			
			WMSApplyCrawlerTaskResponse response = webMetaServerService.applyCrawlerTask(request);
			
			if ( response.getStatus() == WMSTaskStatus.WMS_WEBSITE_TASK_STATUS_SUCCESS ) {
				WMSCrawlerWebsite wmstask = response.getTask();
				DailyWebCrawlerTask task = WebMetaServerConvertor.convert(wmstask);
				task.setTimestamp(TimeHelper.currentDay());
				
				sb.append(String.format("[response: {status: success, webid: %d, timestamp: %s}]", 
						task.getWebid(), task.getTimestamp()));
				return task;
			} else {
				sb.append("[response: {status: none, message: crawler website don't left any more}]");
			}
			
		} catch (LightningServiceException e) {
			e.printStackTrace();
			sb.append(String.format("[exception: {error_code: %d, error_message: %s}]", 
					e.getErrorCode(), e.getErrorMessage()));
		} catch (TException e) {
			e.printStackTrace();
			sb.append(String.format("[exception: {error_message: %s}]", 
					e.getMessage()));
		} finally {
			logger.info(sb.toString());
		}
		
		return null;
	
	}

	public int getLanguageType() {
		return languageType;
	}

	public void setLanguageType(int languageType) {
		this.languageType = languageType;
	}

}
