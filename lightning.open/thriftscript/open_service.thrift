


include "./open_model.thrift"

namespace java com.lightning.rest.model

service Logiclayer {

	// --------------------------------------------------------------------------
	
	// @brief	���½�Ϊ�½ӿ�(�ع�, 2013.12.19)
	/*
	* @brief	�����ӿڣ�������ҳ����
	*/
	open_model.QueryOpenWebPageContentResponse queryOpenWebPageContent(1: open_model.QueryOpenWebPageContentRequest request) throws(1: open_model.FightingServiceException e);
	
	/*
	* @brief	�����ӿڣ� ������������
	*/
	open_model.QueryOpenShortMessageContentResponse queryOpenShortMessageContent(1: open_model.QueryOpenShortMessageContentRequest request) throws(1: open_model.FightingServiceException e);

	/*
	* @brief	������٣����ڲ�ѯ������
	*/
	QueryTracePublicWordResponse queryTracePublicWord(1: QueryTracePublicWordRequest request) throws(1: FightingServiceException e);

	/*
	* @brief	������٣����ڲ�ѯ������ҳ������docid��ʵ��
	*/
	QueryOpenWebPageByDocidResponse queryOpenWebPageByDocid(1: QueryOpenWebPageByDocidRequest request) throws (1: FightingServiceException e);
	
		
	/*
	* @brief	�������ƻ���
	*/
	CreateConsistentTopicResponse createConsistentTopic(1: CreateConsistentTopicRequest request) throws(1: FightingServiceException e);
	
	
	/*
	* @brief	ɾ�����ƻ���
	*/
	RemoveConsistentTopicResponse removeConsistentTopic(1: RemoveConsistentTopicRequest request) throws(1: FightingServiceException e);
	
	
	/*
	* @brief	�г����ƻ����б�	
	*/
	ListConsistentTopicsResponse listConsistentTopics(1: ListConsistentTopicsRequest request) throws(1: FightingServiceException e);
	
	
	// ��ѯ��������ص�����
	ListWebPageForTopicResponse listWebPageForTopic(1: ListWebPageForTopicRequest request) throws(1: FightingServiceException e);
	
	
	// ��ѯ����������ص����Ʊ仯
	QueryWebPageTrendForTopicResponse queryWebPageTrendForTopic(1: QueryWebPageTrendForTopicRequest request) throws(1: FightingServiceException e);
				
	// ��ѯ��������ĳ��ʱ�����ȹ���ҳ
	QueryWebPageForTopicAtTimestampResponse queryWebPageForTopicAtTimestamp(1: QueryWebPageForTopicAtTimestampRequest request) throws(1: FightingServiceException e);
	
	
	// ��ѯ������صĶ����б�, �������
	ListShortMessageForTopicResponse listShortMessageForTopic(1: ListShortMessageForTopicRequest request) throws(1: FightingServiceException e);
	
	// ��ѯ����+�ֻ�������صĶ����б�
	ListShortMessageForTopicWithPhoneResponse listShortMessageForTopicWithPhone(1: ListShortMessageForTopicWithPhoneRequest request) throws(1: FightingServiceException e);
	
	// ��ѯ����������ص����Ʊ仯
	QueryShortMessageTrendForTopicResponse queryShortMessageTrendForTopic(1: QueryShortMessageTrendForTopicRequest request) throws(1: FightingServiceException e);
	
	// ��ѯ��������ĳ��ʱ������ض���
	QueryShortMessageForTopicAtTimestampResponse queryShortMessageForTopicAtTimestamp(1: QueryShortMessageForTopicAtTimestampRequest request) throws(1: FightingServiceException e);
	
	// *) ��ѯ������صĽӿڣ������ȵ㣬�쳣�����дʣ�����ͳһΪͬһ�ӿ�
	QueryTermCategoryWordListResponse queryTermCategoryWordlist(1: QueryTermCategoryWordlistRequest request) throws(1: FightingServiceException e);
		
	// *) ��ѯ�ʵ����Ʊ仯����startdate��enddata�������ʽΪ yyyyMMdd
	QueryTermCategoryWordTrendResponse queryTermCategoryWordTrend(1: QueryTermCategoryWordTrendRequest request) throws(1: FightingServiceException e);
		
	// *) ��ѯ�ʵ����Ʊ仯����Ϊ���ź���ҳ����startdate��enddata�������ʽΪ yyyyMMdd
	QueryTermCategoryWordSplitTrendResponse queryTermCategoryWordSplitTrend(1: QueryTermCategoryWordSplitTrendRequest request) throws(1: FightingServiceException e);

	// *)
	AddSensitiveWordResponse addSensitiveWord(1: AddSensitiveWordRequest request) throws(1: FightingServiceException e);

	// *)
	RemoveSensitiveWordResponse removeSensitiveWord(1: RemoveSensitiveWordRequest request) throws(1: FightingServiceException e);
		
}












