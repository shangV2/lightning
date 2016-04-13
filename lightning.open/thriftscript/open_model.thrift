

namespace java com.lightning.rest.model

exception FightingServiceException {
	1: required i64 logid,
	2: required i32 errorCode,
	3: required string errorMessage
}

enum OpenCommonHighlight {
	OC_HIGHLIGHT_OFF = 0,
	OC_HIGHLIGHT_ON = 1,
}

enum OpenCommonLanguge {
	// *) 中文
	OC_LAN_CHINESE_ZH_CN = 0,		
	// *) 维文
	OC_LAN_UYGHUR_DEFAULT = 1,
	// *) 藏文
	OC_LAN_TIBETAN_DEFAULT = 10,
}

enum OpenCommonQueryDivideType {
	// 全匹配
	OC_QUERY_DIVIDE_OFF = 0,
	// 进行切分
	OC_QUERY_DIVIDE_ON = 1,
}


struct OpenWebPageContent {
	1: required i64 docid,
	2: required string url,
	3: required string title,
	4: required string content,
	5: required string timestamp,
	6: required string source
}

struct QueryOpenWebPageContentRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required string query,
	3: required i32 pageno = 0,
	4: required i32 pagesize = 10,
	5: required i32 summaryLength = 60,		// 默认摘要长度
	6: required OpenCommonHighlight hightlight = OpenCommonHighlight.OC_HIGHLIGHT_ON,
	7: required OpenCommonQueryDivideType divideType = OpenCommonQueryDivideType.OC_QUERY_DIVIDE_ON
}

struct QueryOpenWebPageContentResponse {
	1: required i64 logid = 1001,		// 系统保留
	2: required i32 totalNum,
	3: list<OpenWebPageContent> webpages,	
}


enum ContentType {
	WEB_PAGE,
	SHORT_MESSAGE
}

struct OpenShortMessageContent {
	1: required i64 docid,
	2: required string sendPhone,
	3: required string sendUsermame,
	4: required string recvPhone,
	5: required string recvUsername,
	6: required string message,
	7: required string timestamp
}

struct QueryOpenShortMessageContentRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required string query,
	3: required i32 pageno = 0,
	4: required i32 pagesize = 10,
	5: required i32 summaryLength = 60,		// 默认摘要长度
	6: required OpenCommonHighlight hightlight = OpenCommonHighlight.OC_HIGHLIGHT_ON
}

struct QueryOpenShortMessageContentResponse {
	1: required i64 logid = 1001,		// 系统保留
	2: required i32 totalNum,
	3: required list<OpenShortMessageContent> shortmessages	
}


struct TraceId {
	1: required ContentType contentType,	// WEB_PAGE, docid为网页文档id， SHORT_MESSAGE， docid为短信文档id
	2: required i64 docid
}

struct TracePublicWord {
	1: required string word;
	2: required i32 freq;
}

struct QueryTracePublicWordRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required list<TraceId> traceIds		
}

struct QueryTracePublicWordResponse {
	1: required i64 logid = 1001,		// 系统保留
	2: required list<TracePublicWord> publicWords		
}

struct QueryOpenWebPageByDocidRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required i64 docid
}

struct QueryOpenWebPageByDocidResponse {
	1: required i64 logid = 1001,		// 系统保留
	2: required OpenWebPageContent webpage
}

// -------------------------------------------------

	
struct ConsistentTopic {
	1: optional i64 topicId,
	2: required string topicName,
	3: required list<string> words,
	4: required string startDate,
	5: required string endDate,
	6: required i32 percent = 20
}

struct DateNumberTopicTrend {
	1: required string date,
	2: required i32 number
}

struct CreateConsistentTopicRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required ConsistentTopic topic
}

struct CreateConsistentTopicResponse {
	1: required i64 logid = 1001, 
	2: required ConsistentTopic topic
}

struct RemoveConsistentTopicRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required i64 topicId
}

struct RemoveConsistentTopicResponse {
	1: required i64 logid = 1001,
}


struct ListConsistentTopicsRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required i32 pageno = 0,
	3: required i32 pagesize = 10	
}

struct ListConsistentTopicsResponse {
	1: required i64 logid = 1001,
	2: required i32 totalNum,
	3: list<ConsistentTopic> topics
}

struct ListWebPageForTopicRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required i64 topicId,
	3: required i32 pageno = 0,
	4: required i32 pagesize = 10		
}

struct ListWebPageForTopicResponse {
	1: required i64 logid = 1001,
	2: required i32 totalNum,
	3: required list<OpenWebPageContent> webpages		
}

struct QueryWebPageTrendForTopicRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required i64 topicId	
}

struct QueryWebPageTrendForTopicResponse {
	1: required i64 logid = 1001,
	2: required list<DateNumberTopicTrend> trends	
}

struct QueryWebPageForTopicAtTimestampRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required i64 topicId,
	3: required string timestamp
}

struct QueryWebPageForTopicAtTimestampResponse {
	1: required i64 logid = 1001,
	2: required list<OpenWebPageContent> webpages	
}

struct ListShortMessageForTopicRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required i64 topicId,
	3: required i32 pageno = 0,
	4: required i32 pagesize = 10	
}

struct ListShortMessageForTopicResponse {
	1: required i64 logid = 1001,
	2: required i32 totalNum,
	3: required list<OpenShortMessageContent> shortmessages	
}


struct ListShortMessageForTopicWithPhoneRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required i64 topicId,
	3: required string phone,
	4: required i32 pageno = 0,
	5: required i32 pagesize = 10	
}

struct ListShortMessageForTopicWithPhoneResponse {
	1: required i64 logid = 1001,
	2: required i32 totalNum,
	3: required list<OpenShortMessageContent> shortmessages	
}

struct QueryShortMessageTrendForTopicRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required i64 topicId	
}

struct QueryShortMessageTrendForTopicResponse {
	1: required i64 logid = 1001,
	2: required list<DateNumberTopicTrend> trends	
}


struct QueryShortMessageForTopicAtTimestampRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required i64 topicId,
	3: required string timestamp
}

struct QueryShortMessageForTopicAtTimestampResponse {
	1: required i64 logid = 1001,
	2: required list<OpenShortMessageContent> shortmessages
}


// ------------------------------------------------

enum TermCategoryType {
	TCT_HOTWORD_TYPE = 0,		// 热点词
	TCT_SENSWORD_TYPE = 1,		// 敏感词
	TCT_UNUSWORD_TYPE = 2		// 异常词
}

struct QueryTermCategoryWordlistRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required TermCategoryType tctype = TermCategoryType.TCT_HOTWORD_TYPE,
	3: required i32 pageno = 0,
	4: required i32 pagesize = 10
}

struct QueryTermCategoryWordListResponse {
	1: required i64 logid = 1001,
	2: required i32 totalNum,
	3: required list<string> words
}

struct QueryTermCategoryWordTrendRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required TermCategoryType tctype = TermCategoryType.TCT_HOTWORD_TYPE,
	3: required string startdate,
	4: required string enddate
}

struct QueryTermCategoryWordTrendResponse {
	1: required i64 logid = 1001,
	2: required list<TimeTrendSet> trends
}

struct QueryTermCategoryWordSplitTrendRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required TermCategoryType tctype = TermCategoryType.TCT_HOTWORD_TYPE,
	3: required string word,
	4: required string startdate,
	5: required string enddate		
}

struct QueryTermCategoryWordSplitTrendResponse {
	1: required i64 logid = 1001,
	2: required list<WordSplitTimeTrend> trends		
}

struct AddSensitiveWordRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required string word	
}

struct AddSensitiveWordResponse {
	1: required i64 logid = 1001
}

struct RemoveSensitiveWordRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required string word		
}

struct RemoveSensitiveWordResponse {
	1: required i64 logid = 1001
}








