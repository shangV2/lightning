

namespace java com.lighting.rpc.webmetaserver.model

enum WMSWebsiteType {
	WMS_WEBSITE_ZH_CN = 0,
}

enum WMSWebsiteCrawlerType {
	WMS_WEBSITE_CRAWLER_STAND_ALONE = 0,
	WMS_WEBSITE_CRALWER_DISTRIBUTED = 1
}

enum WMSWebsiteUrlRuleType {
	WMS_WEBSITE_URL_RULE_HIT = 0
}

enum WMSWebsiteContentRuleType {
	WMS_WEBSITE_CONTENT_RULE_TEMPLATE_HPATH = 0, 
}

enum WMSTaskStatus {
	WMS_WEBSITE_TASK_STATUS_NOTHING = 0,
	WMS_WEBSITE_TASK_STATUS_SUCCESS = 1
}

struct WMSCrawlerWebsite {
	1: required string website,
	2: required WMSWebsiteType websiteType 
					= WMSWebsiteType.WMS_WEBSITE_ZH_CN, 
	3: required WMSWebsiteCrawlerType crawlerType 
					= WMSWebsiteCrawlerType.WMS_WEBSITE_CRAWLER_STAND_ALONE,
	4: required WMSWebsiteUrlRuleType urlType 
					= WMSWebsiteUrlRuleType.WMS_WEBSITE_URL_RULE_HIT,
	5: required string urlRule,
	6: required WMSWebsiteContentRuleType contentType
					= WMSWebsiteContentRuleType.WMS_WEBSITE_CONTENT_RULE_TEMPLATE_HPATH,
	7: required string contentRule,
	8: required list<string> seeds,
	9: required i32 crawlerNum,
	10: optional i32 crawlerSchedule,
	11: optional i32 webid
}

struct WMSCreateCrawlerWebsiteRequest {
	1: required i64 logid,
	2: required WMSCrawlerWebsite webinfo
}

struct WMSCreateCrawlerWebsiteResponse {
}

/*
struct WMSFetchCrawlerTaskRequest {
	1: required i64 logid,
	2: required i32 crawlerDegree
}

struct WMSFetchCrawlerTaskResponse {
	1: required list<WMSCrawlerWebsite> tasks
}
*/

struct WMSQueryCrawlerTaskRequest {
	1: required i64 logid,
	2: required i32 pageno,
	3: required i32 pagesize
}

struct WMSQueryCrawlerTaskResponse {
	1: required i32 totalNum,
	2: required list<WMSCrawlerWebsite> tasks
}

struct WMSUpdateCrawlerTaskRequest {
	1: required i64 logid,
	2: required WMSCrawlerWebsite webinfo
}

struct WMSUpdateCrawlerTaskResponse {
	1: required WMSCrawlerWebsite webinfo
}

struct WMSRemoveCrawlerTaskRequest {
	1: required i64 logid,
	2: required WMSWebsiteType websiteType,
	3: required i32 webid
}

struct WMSRemoveCrawlerTaskResponse {
	
}






struct WMSApplyCrawlerTaskRequest {
	1: required i64 logid,
	2: required WMSWebsiteType websiteType
}

struct WMSApplyCrawlerTaskResponse {
	1:required WMSTaskStatus status,
	2: optional WMSCrawlerWebsite task
}
















