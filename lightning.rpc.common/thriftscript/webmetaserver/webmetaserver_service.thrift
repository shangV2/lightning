



include "../common/common_exception.thrift"
include "webmetaserver_model.thrift"

namespace java com.lighting.rpc.webmetaserver.service

service WebMetaServerService {

		/*
		* 
		* @brief	
		* @params	
		* @return	
		*/
		webmetaserver_model.WMSCreateCrawlerWebsiteResponse createCrawlerWebsite(1: webmetaserver_model.WMSCreateCrawlerWebsiteRequest request) 
										throws (1: common_exception.LightningServiceException e);
										
		/*
		* 
		* @brief	
		* @params
		* @return 
		*/
		//webmetaserver_model.WMSFetchCrawlerTaskResponse fetchCrawlerTask(1: webmetaserver_model.WMSFetchCrawlerTaskRequest request)
		//								throws (1: common_exception.LightningServiceException e);							
			
	
		/*
		*
		*/
		webmetaserver_model.WMSQueryCrawlerTaskResponse queryCrawlerTask(1: webmetaserver_model.WMSQueryCrawlerTaskRequest request)
										throws (1: common_exception.LightningServiceException e);	
		
		/*
		*
		*/		
		webmetaserver_model.WMSUpdateCrawlerTaskResponse updateCrawlerTask(1: webmetaserver_model.WMSUpdateCrawlerTaskRequest request)
										throws (1: common_exception.LightningServiceException e);	
		
		/*
		*
		*/
		webmetaserver_model.WMSRemoveCrawlerTaskResponse removeCrawlerTask(1: webmetaserver_model.WMSRemoveCrawlerTaskRequest request)
										throws (1: common_exception.LightningServiceException e);	
	
		/*
		*		
		*/
		webmetaserver_model.WMSApplyCrawlerTaskResponse applyCrawlerTask (1: webmetaserver_model.WMSApplyCrawlerTaskRequest request) 
										throws (1: common_exception.LightningServiceException e);	
			
		
}











