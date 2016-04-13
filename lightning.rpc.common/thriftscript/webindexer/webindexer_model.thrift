

namespace java com.lighting.rpc.webindexer.model


enum WIHighLightType {
	WI_HIGHLIGHT_OFF = 0,
	WI_HIGHLIGHT_ON = 1
}

enum WIQueryDivideType {
	WI_QUERY_DIVIDE_OFF = 0,
	WI_QUERY_DIVIDE_ON = 1,
}

struct WIWebPage {
	1: required i32	docid,
	2: required string title,
	3: required string url,
	4: required string summary
}

struct WIQueryRequest {	
	1: required i64 logid,
	2: required string query,
	3: required i32 pageno = 0,	
	4: required i32 pagesize = 10
}

struct WIQueryResponse {
	1: required i32 totalNumber,
	2: list<WIWebPage> webpages		
}

struct WIWebArticle {
	1: required i32	docid,
	2: required string url,
	3: required string source
	4: required string title,
	5: required string timestamp,
	6: required string content
}

struct WIQueryArticleRequest {
	1: required i64 logid,
	2: required i32 docid
}

struct WIQueryArticleResponse {
	1: required WIWebArticle article
}

struct WIQueryBatchArticlesRequest {
	1: required i64 logid,
	2: list<i32> docids
}

struct WIQueryBatchArticlesResponse {
	1: required list<WIWebArticle> articles
}


struct WIWebPageItem {
	1: required i64 docid,
	2: required string url,
	3: required string title,
	4: required string content,
	5: required string timestamp,
	6: required string source
}

struct WIQueryWebPagesRequest {
	1: required i64 logid,
	2: required string query,
	3: required i32 pageno = 0,
	4: required i32 pagesize = 10,
	5: required i32 summaryLength = 60,		// 默认摘要长度为60, summaryLength = 0, 表示取全文内容
	6: required WIHighLightType hightlight = WIHighLightType.WI_HIGHLIGHT_ON,
	7: required WIQueryDivideType divideType = WIQueryDivideType.WI_QUERY_DIVIDE_ON
}

struct WIQueryWebPagesResponse {
	1: required i32 totalNum,
	2: list<WIWebPageItem> webpages,
}

struct WIQueryWebPagesWithTimeRangeRequest {
	1: required i64 logid,
	2: required string query,
	3: required string startDate,
	4: required string endDate,
	5: required i32 pageno = 0,
	6: required i32 pagesize = 10,
	7: required i32 summaryLength = 60,		// 默认摘要长度为60, summaryLength = 0, 表示取全文内容
	//8: required WIHighLightType hightlight = WIHighLightType.WI_HIGHLIGHT_ON,
	//9: required WIQueryDivideType divideType = WIQueryDivideType.WI_QUERY_DIVIDE_ON
}

struct WIQueryWebPagesWithTimeRangeResponse {
	1: required i32 totalNum,
	2: list<WIWebPageItem> webpages,
}


