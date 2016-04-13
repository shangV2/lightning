package com.lightning.webmetaserver.rpc.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;

import com.lighting.rpc.common.exception.LightningServiceException;
import com.lighting.rpc.core.utility.LoggerUtility;
import com.lighting.rpc.webmetaserver.model.WMSApplyCrawlerTaskRequest;
import com.lighting.rpc.webmetaserver.model.WMSApplyCrawlerTaskResponse;
import com.lighting.rpc.webmetaserver.model.WMSCrawlerWebsite;
import com.lighting.rpc.webmetaserver.model.WMSCreateCrawlerWebsiteRequest;
import com.lighting.rpc.webmetaserver.model.WMSCreateCrawlerWebsiteResponse;
import com.lighting.rpc.webmetaserver.model.WMSQueryCrawlerTaskRequest;
import com.lighting.rpc.webmetaserver.model.WMSQueryCrawlerTaskResponse;
import com.lighting.rpc.webmetaserver.model.WMSRemoveCrawlerTaskRequest;
import com.lighting.rpc.webmetaserver.model.WMSRemoveCrawlerTaskResponse;
import com.lighting.rpc.webmetaserver.model.WMSTaskStatus;
import com.lighting.rpc.webmetaserver.model.WMSUpdateCrawlerTaskRequest;
import com.lighting.rpc.webmetaserver.model.WMSUpdateCrawlerTaskResponse;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteType;
import com.lighting.rpc.webmetaserver.service.WebMetaServerService;
import com.lightning.webmetaserver.controller.WebMetaServerController;
import com.lightning.webmetaserver.dataobject.WebsiteCrawlerDO;
import com.lightning.webmetaserver.dataobject.convertor.WebsiteCrawlerConvertor;
import com.lightning.webmetaserver.service.WebsiteCrawlerService;

public class WebMetaServerServiceHandlerImpl implements WebMetaServerService.Iface {

	private WebMetaServerController controller;
	
	public WebMetaServerServiceHandlerImpl(WebMetaServerController controller) {
		this.controller = controller;
	}
	
	@Override
	public WMSCreateCrawlerWebsiteResponse createCrawlerWebsite(
			WMSCreateCrawlerWebsiteRequest request)
			throws LightningServiceException, TException {
		
		
		long logid = request.getLogid();
		WMSCrawlerWebsite websiteInfo = request.getWebinfo();
		
		WMSCreateCrawlerWebsiteResponse response = new WMSCreateCrawlerWebsiteResponse();
		
		LoggerUtility.noticeLog("[logid: %d] [request: {%s}]", 
				logid, websiteInfo.toString());
		
		// *) 类型的转化
		WebsiteCrawlerDO websiteCrawlerDO = WebsiteCrawlerConvertor.convert(websiteInfo);

		// *) 调用服务
		WebsiteCrawlerService websiteCrawlerService = controller.getWebsiteCrawlerService();
		websiteCrawlerService.addWebsiteCrawler(websiteCrawlerDO);
		
		LoggerUtility.noticeLog("[respose: {success_amount: 1}]");
		
		// *) 结果内容填充
		return response;
		
	}

//	@Override
//	public WMSFetchCrawlerTaskResponse fetchCrawlerTask(
//			WMSFetchCrawlerTaskRequest request)
//			throws LightningServiceException, TException {
//		// TODO Auto-generated method stub
//		
//		WMSFetchCrawlerTaskResponse response = new WMSFetchCrawlerTaskResponse();
//		response.setTasks(new ArrayList<WMSCrawlerWebsite>());
//		int degree = request.getCrawlerDegree();
//		
//		WebsiteCrawlerService websiteCrawlerService = controller.getWebsiteCrawlerService();
//		List<WebsiteCrawlerDO> websiteCrawlerDOs = websiteCrawlerService.queryWebsiteCrawlers(0, 0, degree);
//		
//		for ( WebsiteCrawlerDO websiteCrawlerDO : websiteCrawlerDOs ) {
//			WMSCrawlerWebsite crawlerWebsite = WebsiteCrawlerConvertor.convert(websiteCrawlerDO);
//			response.getTasks().add(crawlerWebsite);
//		}
//		return response;
//		
//	}


	@Override
	public WMSQueryCrawlerTaskResponse queryCrawlerTask(
			WMSQueryCrawlerTaskRequest request)
			throws LightningServiceException, TException {
		// TODO Auto-generated method stub
		
		long logid = request.getLogid();
		int pageno = request.getPageno();
		int pagesize = request.getPagesize();
		
		LoggerUtility.noticeLog("[logid: %d] [request: {pageno: %d, pagesize: %d}]", 
				logid, pageno, pagesize);
		
		WMSQueryCrawlerTaskResponse response = new WMSQueryCrawlerTaskResponse();
		
		WebsiteCrawlerService websiteCrawlerService = controller.getWebsiteCrawlerService();
		List<WebsiteCrawlerDO> crawlerDOs = websiteCrawlerService.queryWebsiteCrawlers(0, pageno, pagesize);

		response.setTotalNum(0);
		response.setTasks(new ArrayList<WMSCrawlerWebsite>());
		
		for ( WebsiteCrawlerDO crawlerDO : crawlerDOs ) {
			WMSCrawlerWebsite website = WebsiteCrawlerConvertor.convert(crawlerDO);
			response.getTasks().add(website);
		}
		
		LoggerUtility.noticeLog("[response: {totalNum: %d, website_crawler_num: %d}]",
				response.getTotalNum(), response.getTasks().size());
		
		return response;
		
	}

	@Override
	public WMSRemoveCrawlerTaskResponse removeCrawlerTask(
			WMSRemoveCrawlerTaskRequest request)
			throws LightningServiceException, TException {
		
		long logid = request.getLogid();
		WMSWebsiteType websiteType = request.getWebsiteType();
		int webid = request.getWebid();

		LoggerUtility.noticeLog("[logid: %d] [request: {webid: %d}]", 
				logid, webid);
		
		WMSRemoveCrawlerTaskResponse response = new WMSRemoveCrawlerTaskResponse(); 
		
		WebsiteCrawlerService websiteCrawlerService = controller.getWebsiteCrawlerService();
		websiteCrawlerService.removeWebsiteCrawler(websiteType.getValue(), webid);
		
		// *) 添加计时器
		LoggerUtility.noticeLog("[action: {remove website_crawler, consume: ms}]");
		
		LoggerUtility.noticeLog("[response: {success_amount: 1}]");
		
		return response;
		
	}

	@Override
	public WMSUpdateCrawlerTaskResponse updateCrawlerTask(
			WMSUpdateCrawlerTaskRequest request)
			throws LightningServiceException, TException {
		// TODO Auto-generated method stub
		
		long logid = request.getLogid();
		WMSCrawlerWebsite webInfo = request.getWebinfo();
		
		WMSUpdateCrawlerTaskResponse response = new WMSUpdateCrawlerTaskResponse();
		response.setWebinfo(webInfo);
		
		if ( !webInfo.isSetWebid() ) {
			return response;
		}
		
		WebsiteCrawlerDO websiteCrawlerDO = WebsiteCrawlerConvertor.convert(webInfo);
		WebsiteCrawlerService websiteCrawlerService = controller.getWebsiteCrawlerService();
		websiteCrawlerService.updateWebsiteCrawler(websiteCrawlerDO);
		
		return response;
		
	}

	
	// ===============================
	@Override
	public WMSApplyCrawlerTaskResponse applyCrawlerTask(
			WMSApplyCrawlerTaskRequest request)
			throws LightningServiceException, TException {

		WMSApplyCrawlerTaskResponse response = new WMSApplyCrawlerTaskResponse();
		response.setStatus(WMSTaskStatus.WMS_WEBSITE_TASK_STATUS_NOTHING);
		
		int websiteType = request.getWebsiteType().getValue();
		
		WebsiteCrawlerService websiteCrawlerService = controller.getWebsiteCrawlerService();
		WebsiteCrawlerDO websiteCrawlerDO = websiteCrawlerService.applyWebsiteCrawler(websiteType);
		
		if ( websiteCrawlerDO != null ) {
			WMSCrawlerWebsite wmsCrawlerWebsite = WebsiteCrawlerConvertor.convert(websiteCrawlerDO);
			response.setTask(wmsCrawlerWebsite);
			response.setStatus(WMSTaskStatus.WMS_WEBSITE_TASK_STATUS_SUCCESS);
		}
		return response;
		
	}
	

}
