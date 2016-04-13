

namespace java com.lighting.rpc.datacenter.model


struct DCHotWebEvent {
	1: required string title,
	2: required string eventid,
	3: required i64 score
}

struct DCWebPage {
	1: required string url,
	2: required string title,
	3: required string content,
	4: required string timestamp,
	5: required string source
}

struct DCQueryHotWebEventsRequest {
	1: required i64 logid,
	2: required string timestamp = "now",
	3: required i32 pageno = 0,
	4: required i32 pagesize = 10
}

struct DCQueryHotWebEventsResponse {
	1: required string timestamp,
	2: required list<DCHotWebEvent> hotEvents
}

struct DCInsertHotWebEventsRequest {
	1: required i64 logid,
	2: required string timestamp,
	3: required list<DCHotWebEvent> events
}

struct DCInsertHotWebEventsResponse {
	// nothing
}

struct DCAddHotWebEventsRequest {
	1: required i64 logid,
	2: required string timestamp,
	3: required list<DCHotWebEvent> events,
}

struct DCAddHotWebEventsResponse {	
	1: required i32 successAmount
}

struct DCRemoveHotWebEventsRequest {
	1: required i64 logid,
	2: required string timestamp = "now",
}

struct DCRemoveHotWebEventsResponse {
	1: required i32 successAmount,
}

struct DCQueryHotWebPagesRequest {
	1: required i64 logid,
	2: required string eventid,
	3: required i32 pageno = 0,
	4: required i32 pagesize = 10
}

struct DCQueryHotWebPagesResponse {
	1: required i32 totalNum,
	2: required list<DCWebPage> webPages
}

struct DCInsertHotWebPagesRequest {
	1: required i64 logid,
	2: required string timestamp,
	3: required string title,
	4: required list<DCWebPage> webPages
}

struct DCInsertHotWebPagesResponse {
	1: required string eventid
}

struct DCAddHotWebPagesRequest {
	1: required i64 logid,
	2: required string timestamp,
	3: required string title,
	4: required list<DCWebPage> webPages		
}

struct DCAddHotWebPagesResponse {
	1: required string eventid
}

// -----------------------------------------
// 以下为主题相关的系列接口

enum DCCommonLanguge {
	// *) 中文
	DC_LAN_CHINESE_ZH_CN = 0,		
	// *) 维文
	DC_LAN_UYGHUR_DEFAULT = 1,
	// *) 藏文
	DC_LAN_TIBETAN_DEFAULT = 10,
}

struct DCDateNumberTopicTrend {
	1: required string date,
	2: required i32 number
}

struct DCConsistentTopic {
	1: optional i64 topicId,
	2: required string topicName,
	3: required list<string> words,
	4: required string startDate,
	5: required string endDate,
	6: required i32 percent = 20
}

// *) 创建主题
struct DCCreateTopicRequest {
	1: required i64 logid,
	2: required DCCommonLanguge langugeType = DCCommonLanguge.DC_LAN_CHINESE_ZH_CN,
	3: required DCConsistentTopic topic
}

struct DCCreateTopicResponse {
	1: required DCConsistentTopic topic
}

struct DCRemoveTopicRequest {
	1: required i64 logid,
	2: required DCCommonLanguge langugeType = DCCommonLanguge.DC_LAN_CHINESE_ZH_CN,
	3: required i64 topicId
}

struct DCRemoveTopicResponse {
	1: required i32 successAmount = 0
}

struct DCQueryTopicRequest {
	1: required i64 logid,
	2: required DCCommonLanguge langugeType = DCCommonLanguge.DC_LAN_CHINESE_ZH_CN,
	3: required i64 topicId
}

struct DCQueryTopicResponse {
	1: required DCConsistentTopic topic
}

// *) 列出话题的列表
struct DCQueryTopicListRequest {
	1: required i64 logid,
	2: required DCCommonLanguge langugeType = DCCommonLanguge.DC_LAN_CHINESE_ZH_CN,
	3: required i32 pageno = 0,
	4: required i32 pagesize = 10
}

struct DCQueryTopicListResponse {
	1: required i32 totalNum,
	2: required list<DCConsistentTopic> topics
}


// *) 话题趋势变化请求接口
struct DCQueryTopicTrendRequest {
	1: required i64 logid,
	2: required DCCommonLanguge langugeType = DCCommonLanguge.DC_LAN_CHINESE_ZH_CN,
	3: required i64 topicId
}

struct DCQueryTopicTrendResponse {
	1: required list<DCDateNumberTopicTrend> trends		
}

// *) 话题在某个时间点的查询接口
struct DCQueryTopicAtTimeRequest {
	1: required i64 logid,
	2: required DCCommonLanguge langugeType = DCCommonLanguge.DC_LAN_CHINESE_ZH_CN,
	3: required i64 topicId,
	4: required string timestamp
}

struct DCQueryTopicAtTimeResponse {
	1: required list<DCWebPage> webpages,
}

struct DCQueryTopicWebpagesRequest {
	1: required i64 logid,
	2: required DCCommonLanguge langugeType = DCCommonLanguge.DC_LAN_CHINESE_ZH_CN,
	3: required i64 topicId,
	4: required i32 pageno = 0,
	5: required i32 pagesize = 10	
}

struct DCQueryTopicWebpagesResponse {
	1: required list<DCWebPage> webpages,
}

// --------------------------------------------------------
// 以下为热点接口

struct DCTimeTrend {
	1: required string timestamp,
	2: required i32 freq
}

struct DCTimeWordInfo {
	1: required string word;
	2: required i32 freq;
}

struct DCTimeWordTrend {
	1: required string timestamp,
	2: required list<DCTimeWordInfo> wordinfos
}

struct DCQueryHotwordListRequest {
	1: required i64 logid,
	2: required DCCommonLanguge langugeType = DCCommonLanguge.DC_LAN_CHINESE_ZH_CN,
	3: required string timestamp, // format格式为yyyyMMdd，或者yyyy-MM-dd
	4: required i32 pageno = 0,
	5: required i32 pagesize = 10
}

struct DCQueryHotwordListResponse {
	1: required list<string> words
}

struct DCQueryHotwordTrendRequest {
	1: required i64 logid,
	2: required DCCommonLanguge langugeType = DCCommonLanguge.DC_LAN_CHINESE_ZH_CN,
	3: required string word,
	4: required string startDate,
	5: required string endDate
}

struct DCQueryHotwordTrendResponse {
	1: required list<DCTimeTrend> trends
}

// ------------------------------------
struct DCAddSensitiveWordRequest {
	1: required i64 logid,
	2: required DCCommonLanguge langugeType = DCCommonLanguge.DC_LAN_CHINESE_ZH_CN,
	3: required string word
}

struct DCAddSensitiveWordResponse {
	1: required i32 successAmount = 0
}

struct DCRemoveSensitiveWordRequest {
	1: required i64 logid,
	2: required DCCommonLanguge langugeType = DCCommonLanguge.DC_LAN_CHINESE_ZH_CN,
	3: required string word
}

struct DCRemoveSensitiveWordResponse {
	1: required i32 successAmount = 0
}

struct DCQuerySensitiveWordListRequest {
	1: required i64 logid,
	2: required DCCommonLanguge langugeType = DCCommonLanguge.DC_LAN_CHINESE_ZH_CN,
	3: required i32 pageno = 0,
	4: required i32 pagesize = 10
}

struct DCQuerySensitiveWordListResponse {
	1: required list<string> words
}

struct DCQuerySensitiveWordTrendRequest {
	1: required i64 logid,
	2: required DCCommonLanguge langugeType = DCCommonLanguge.DC_LAN_CHINESE_ZH_CN,
	3: required string word,
	4: required string startDate,
	5: required string endDate
}

struct DCQuerySensitiveWordTrendResponse {
	1: required list<DCTimeTrend> trends
}

struct DCQuerySensitiveWordListTrendRequest {
	1: required i64 logid,
	2: required DCCommonLanguge langugeType = DCCommonLanguge.DC_LAN_CHINESE_ZH_CN,
	3: required string startDate,
	4: required string endDate,
	5: required i32 pageno = 0, 
	6: required i32 pagesize = 10
}

struct DCQuerySensitiveWordListTrendResponse {
	1: required list<DCTimeWordTrend> trends
}






