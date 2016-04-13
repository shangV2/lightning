


include "./open_model.thrift"

namespace java com.lightning.rest.model

service Logiclayer {

	// --------------------------------------------------------------------------
	
	// @brief	以下皆为新接口(重构, 2013.12.19)
	/*
	* @brief	公共接口：检索网页内容
	*/
	open_model.QueryOpenWebPageContentResponse queryOpenWebPageContent(1: open_model.QueryOpenWebPageContentRequest request) throws(1: open_model.FightingServiceException e);
	
	/*
	* @brief	公共接口： 检索短信内容
	*/
	open_model.QueryOpenShortMessageContentResponse queryOpenShortMessageContent(1: open_model.QueryOpenShortMessageContentRequest request) throws(1: open_model.FightingServiceException e);

	/*
	* @brief	问题跟踪，用于查询公共词
	*/
	QueryTracePublicWordResponse queryTracePublicWord(1: QueryTracePublicWordRequest request) throws(1: FightingServiceException e);

	/*
	* @brief	问题跟踪，用于查询单个网页，根据docid来实现
	*/
	QueryOpenWebPageByDocidResponse queryOpenWebPageByDocid(1: QueryOpenWebPageByDocidRequest request) throws (1: FightingServiceException e);
	
		
	/*
	* @brief	创建定制话题
	*/
	CreateConsistentTopicResponse createConsistentTopic(1: CreateConsistentTopicRequest request) throws(1: FightingServiceException e);
	
	
	/*
	* @brief	删除定制话题
	*/
	RemoveConsistentTopicResponse removeConsistentTopic(1: RemoveConsistentTopicRequest request) throws(1: FightingServiceException e);
	
	
	/*
	* @brief	列出定制话题列表	
	*/
	ListConsistentTopicsResponse listConsistentTopics(1: ListConsistentTopicsRequest request) throws(1: FightingServiceException e);
	
	
	// 查询与主题相关的文章
	ListWebPageForTopicResponse listWebPageForTopic(1: ListWebPageForTopicRequest request) throws(1: FightingServiceException e);
	
	
	// 查询出与主题相关的趋势变化
	QueryWebPageTrendForTopicResponse queryWebPageTrendForTopic(1: QueryWebPageTrendForTopicRequest request) throws(1: FightingServiceException e);
				
	// 查询出主题在某个时间点的先关网页
	QueryWebPageForTopicAtTimestampResponse queryWebPageForTopicAtTimestamp(1: QueryWebPageForTopicAtTimestampRequest request) throws(1: FightingServiceException e);
	
	
	// 查询主题相关的短信列表, 话题相关
	ListShortMessageForTopicResponse listShortMessageForTopic(1: ListShortMessageForTopicRequest request) throws(1: FightingServiceException e);
	
	// 查询主题+手机号码相关的短信列表
	ListShortMessageForTopicWithPhoneResponse listShortMessageForTopicWithPhone(1: ListShortMessageForTopicWithPhoneRequest request) throws(1: FightingServiceException e);
	
	// 查询出与主题相关的趋势变化
	QueryShortMessageTrendForTopicResponse queryShortMessageTrendForTopic(1: QueryShortMessageTrendForTopicRequest request) throws(1: FightingServiceException e);
	
	// 查询出主题在某个时间点的相关短信
	QueryShortMessageForTopicAtTimestampResponse queryShortMessageForTopicAtTimestamp(1: QueryShortMessageForTopicAtTimestampRequest request) throws(1: FightingServiceException e);
	
	// *) 查询词组相关的接口，比如热点，异常，敏感词，现在统一为同一接口
	QueryTermCategoryWordListResponse queryTermCategoryWordlist(1: QueryTermCategoryWordlistRequest request) throws(1: FightingServiceException e);
		
	// *) 查询词的趋势变化，由startdate，enddata，输入格式为 yyyyMMdd
	QueryTermCategoryWordTrendResponse queryTermCategoryWordTrend(1: QueryTermCategoryWordTrendRequest request) throws(1: FightingServiceException e);
		
	// *) 查询词的趋势变化，分为短信和网页，由startdate，enddata，输入格式为 yyyyMMdd
	QueryTermCategoryWordSplitTrendResponse queryTermCategoryWordSplitTrend(1: QueryTermCategoryWordSplitTrendRequest request) throws(1: FightingServiceException e);

	// *)
	AddSensitiveWordResponse addSensitiveWord(1: AddSensitiveWordRequest request) throws(1: FightingServiceException e);

	// *)
	RemoveSensitiveWordResponse removeSensitiveWord(1: RemoveSensitiveWordRequest request) throws(1: FightingServiceException e);
		
}












