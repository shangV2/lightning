

include "../common/common_exception.thrift"
include "datacenter_model.thrift"

namespace java com.lighting.rpc.datacenter.service


service DataCenterService {

	// *) 查询当天的热点信息
	datacenter_model.DCQueryHotWebEventsResponse queryHotWebEvents(1: datacenter_model.DCQueryHotWebEventsRequest request) 
						throws (1: common_exception.LightningServiceException e);

	// *) 添加某天具体的热门事件
	datacenter_model.DCInsertHotWebEventsResponse insertHotWebEvents(1: datacenter_model.DCInsertHotWebEventsRequest request)
						throws (1: common_exception.LightningServiceException e);
	
	// *) 添加具体某天的热门话题
	datacenter_model.DCAddHotWebEventsResponse addHotWebEvents(1: datacenter_model.DCAddHotWebEventsRequest request)
						throws (1: common_exception.LightningServiceException e);
	
	// *) 删除具体某天的热门话题
	datacenter_model.DCRemoveHotWebEventsResponse removeHotWebEvents(1: datacenter_model.DCRemoveHotWebEventsRequest request)
						throws (1: common_exception.LightningServiceException e);
	
	// --------------------------------------------------------------------------------
	
	// *) 查询热点具体的相关网页新闻
	datacenter_model.DCQueryHotWebPagesResponse queryHotWebPages(1: datacenter_model.DCQueryHotWebPagesRequest request)
						throws (1: common_exception.LightningServiceException e);

	// *) 添加具体热点事件的相关网页内容
	datacenter_model.DCInsertHotWebPagesResponse insertHotWebPages(1: datacenter_model.DCInsertHotWebPagesRequest request)
						throws (1: common_exception.LightningServiceException e);


	// --------------------------------------------------
	// *)
	datacenter_model.DCCreateTopicResponse createConsistentTopic(1: datacenter_model.DCCreateTopicRequest request)
						throws (1: common_exception.LightningServiceException e);
	// *)
	datacenter_model.DCRemoveTopicResponse removeConsistentTopic(1: datacenter_model.DCRemoveTopicRequest request)
						throws (1: common_exception.LightningServiceException e);
	// *)
	datacenter_model.DCQueryTopicResponse queryConsistentTopic(1: datacenter_model.DCQueryTopicRequest request)
						throws (1: common_exception.LightningServiceException e);
	// *)					
	datacenter_model.DCQueryTopicListResponse queryTopicList(1: datacenter_model.DCQueryTopicListRequest request)
						throws (1: common_exception.LightningServiceException e);					
	// *)
	datacenter_model.DCQueryTopicTrendResponse queryTopicTrend(1: datacenter_model.DCQueryTopicTrendRequest request) 
						throws (1: common_exception.LightningServiceException e);
	// *)
	datacenter_model.DCQueryTopicAtTimeResponse queryTopicAtTime(1: datacenter_model.DCQueryTopicAtTimeRequest request)
						throws (1: common_exception.LightningServiceException e);
	// *)
	datacenter_model.DCQueryTopicWebpagesResponse queryTopicWebpages(1: datacenter_model.DCQueryTopicWebpagesRequest request)
						throws (1: common_exception.LightningServiceException e);
						
	// ---------------------------------------------------
	// *)
	datacenter_model.DCQueryHotwordListResponse queryHotwordList(1: datacenter_model.DCQueryHotwordListRequest request)
						throws (1: common_exception.LightningServiceException e);
						
	// *)
	datacenter_model.DCQueryHotwordTrendResponse queryHotwordTrend(1: datacenter_model.DCQueryHotwordTrendRequest request)
						throws (1: common_exception.LightningServiceException e);
		
					
	// ==========================================================================================
	// *)
	datacenter_model.DCAddSensitiveWordResponse addSensitiveWord(1: datacenter_model.DCAddSensitiveWordRequest request)
						throws (1: common_exception.LightningServiceException e);
	
	// *)					
	datacenter_model.DCRemoveSensitiveWordResponse removeSensitiveWord(1: datacenter_model.DCRemoveSensitiveWordRequest request)
						throws (1: common_exception.LightningServiceException e);	
	
	// *)
	datacenter_model.DCQuerySensitiveWordListResponse querySensitiveWordList(1: datacenter_model.DCQuerySensitiveWordListRequest request)
						throws (1: common_exception.LightningServiceException e);
	
	// *)
	datacenter_model.DCQuerySensitiveWordTrendResponse querySensitiveWordTrend(1: datacenter_model.DCQuerySensitiveWordTrendRequest request)
						throws (1: common_exception.LightningServiceException e);
						
	// *)
	datacenter_model.DCQuerySensitiveWordListTrendResponse querySensitiveWordListTrend(1: datacenter_model.DCQuerySensitiveWordListTrendRequest request)
						throws (1: common_exception.LightningServiceException e);
						
	
}




