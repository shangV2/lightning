

include "../common/common_exception.thrift"
include "datacenter_model.thrift"

namespace java com.lighting.rpc.datacenter.service


service DataCenterService {

	// *) ��ѯ������ȵ���Ϣ
	datacenter_model.DCQueryHotWebEventsResponse queryHotWebEvents(1: datacenter_model.DCQueryHotWebEventsRequest request) 
						throws (1: common_exception.LightningServiceException e);

	// *) ���ĳ�����������¼�
	datacenter_model.DCInsertHotWebEventsResponse insertHotWebEvents(1: datacenter_model.DCInsertHotWebEventsRequest request)
						throws (1: common_exception.LightningServiceException e);
	
	// *) ��Ӿ���ĳ������Ż���
	datacenter_model.DCAddHotWebEventsResponse addHotWebEvents(1: datacenter_model.DCAddHotWebEventsRequest request)
						throws (1: common_exception.LightningServiceException e);
	
	// *) ɾ������ĳ������Ż���
	datacenter_model.DCRemoveHotWebEventsResponse removeHotWebEvents(1: datacenter_model.DCRemoveHotWebEventsRequest request)
						throws (1: common_exception.LightningServiceException e);
	
	// --------------------------------------------------------------------------------
	
	// *) ��ѯ�ȵ����������ҳ����
	datacenter_model.DCQueryHotWebPagesResponse queryHotWebPages(1: datacenter_model.DCQueryHotWebPagesRequest request)
						throws (1: common_exception.LightningServiceException e);

	// *) ��Ӿ����ȵ��¼��������ҳ����
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




