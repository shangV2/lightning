
namespace java com.qing.logiclayer

struct WordInfo {
	1: string word
	2: i32 freq
}

struct PageInfo {
	1: string title
	2: string url
	3: string date
}

struct WordTimeTrend {
	1: string date 
	2: i32 total
}

struct WordSplitTimeTrend {
	1: string date
	2: i32 smsfreq
	3: i32 pagefreq
}

struct TimeTrendSet {
	1: string date
	2: list<WordInfo> wordinfos
}

struct SearchSummary {
	1: string title
	2: string url
	3: string content
}

struct SearchSummaryResult {
	1: list<SearchSummary> summaries
	2: i32 total
}

struct BriefReport {
	1: string title
	2: string url
	3: string content
}

struct SmsSummary {
	1: string sendMobile
	2: string sendUsername
	3: string recvMobile
	4: string recvUsername
	5: string content
	6: string timestamp
}

struct SmsFriend {
	1: string phoneno
	2: string username
	3: i32 contactNum
}	

// -----------------------------------------------


// ���½�Ϊ�¶���Ľṹ����, 2013.12.19, �ع�

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
	// *) ����
	OC_LAN_CHINESE_ZH_CN = 0,		
	// *) ά��
	OC_LAN_UYGHUR_DEFAULT = 1,
	// *) ����
	OC_LAN_TIBETAN_DEFAULT = 10,
}

enum OpenCommonQueryDivideType {
	// ȫƥ��
	OC_QUERY_DIVIDE_OFF = 0,
	// �����з�
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
	5: required i32 summaryLength = 60,		// Ĭ��ժҪ����
	6: required OpenCommonHighlight hightlight = OpenCommonHighlight.OC_HIGHLIGHT_ON,
	7: required OpenCommonQueryDivideType divideType = OpenCommonQueryDivideType.OC_QUERY_DIVIDE_ON
}

struct QueryOpenWebPageContentResponse {
	1: required i64 logid = 1001,		// ϵͳ����
	2: required i32 totalNum,
	3: list<OpenWebPageContent> webpages,	
}

struct QueryOpenWebPageWithTimeRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required string query,
	3: required string startDate,
	4: required string endDate,
	5: required i32 pageno = 0,
	6: required i32 pagesize = 10,
	7: required i32 summaryLength = 60,		// Ĭ��ժҪ����
}

struct QueryOpenWebPageWithTimeResponse {
	1: required i64 logid = 1001,		// ϵͳ����
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
	5: required i32 summaryLength = 60,		// Ĭ��ժҪ����
	6: required OpenCommonHighlight hightlight = OpenCommonHighlight.OC_HIGHLIGHT_ON
}

struct QueryOpenShortMessageContentResponse {
	1: required i64 logid = 1001,		// ϵͳ����
	2: required i32 totalNum,
	3: required list<OpenShortMessageContent> shortmessages	
}


struct TraceId {
	1: required ContentType contentType,	// WEB_PAGE, docidΪ��ҳ�ĵ�id�� SHORT_MESSAGE�� docidΪ�����ĵ�id
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
	1: required i64 logid = 1001,		// ϵͳ����
	2: required list<TracePublicWord> publicWords		
}

struct QueryOpenWebPageByDocidRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required i64 docid
}

struct QueryOpenWebPageByDocidResponse {
	1: required i64 logid = 1001,		// ϵͳ����
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
	TCT_HOTWORD_TYPE = 0,		// �ȵ��
	TCT_SENSWORD_TYPE = 1,		// ���д�
	TCT_UNUSWORD_TYPE = 2		// �쳣��
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

// ----------------------------------------------------
// ���ȵ��¼����
struct HotWebEvent {
	1: required string timestamp,
	2: required string eventid,
	3: required string title,
	4: required i64 score
}

struct HotWebPageContent {
	1: required string url,
	2: required string title,
	3: required string content,
	4: required string timestamp,
	5: required string source
}

struct QueryHotWebEventRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required string timestamp,
	3: required i32 pageno = 0,
	4: required i32 pagesize = 10
}

struct QueryHotWebEventResponse {
	1: required i64 logid = 1001,
	2: required i32 totalNum,
	3: required list<HotWebEvent> events
}

struct QueryHotWebPageByEventRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required string eventid,
	3: required i32 pageno = 0,
	4: required i32 pagesize = 10
}

struct QueryHotWebPageByEventResponse {
	1: required i64 logid = 1001,
	2: required i32 totalNum,
	3: required list<HotWebPageContent> webpages
}

//---------------------------------------------------


struct QueryHotWordlistRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required string timestamp, // format��ʽΪyyyyMMdd������yyyy-MM-dd
	3: required i32 pageno = 0,
	4: required i32 pagesize = 10
}

struct QueryHotWordListResponse {
	1: required i64 logid = 1001,
	2: required list<string> words
}

struct QueryHotWordTrendRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required string word,
	3: required string startdate,  // format��ʽΪyyyyMMdd������yyyy-MM-dd 
	4: required string enddate     // format��ʽΪyyyyMMdd������yyyy-MM-dd
}

struct QueryHotWordTrendResponse {
	1: required i64 logid = 1001,
	2: required list<WordTimeTrend> trends
}

// -------------------------------
// *) add something about crawler

struct HitWebisteCrawler {
	1: optional OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required string website,
	3: required list<string> seedUrls,
	4: required i32 crawlerNum,
	
	5: required string hostUrlExp,
	6: required string navigationUrlExp,
	7: required string contentUrlExp,
	
	8: required string titleContentExp,
	9: required string contentContentExp,
	10: required string timeContentExp,
	11: optional i32 webid
}

struct AddHitWebsiteCrawlerRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required HitWebisteCrawler websiteCrawler
}

struct AddHitWebsiteCrawlerResponse {
	1: required i64 logid = 1001,
}

struct QueryHitWebsiteCrawlersRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required i32 pageno = 0,
	3: required i32 pagesize = 10
}

struct QueryHitWebsiteCrawlersResponse {
	1: required i64 logid = 1001,
	2: required i32 totalNum = 0,
	3: required list<HitWebisteCrawler> websiteCrawlers
}

struct UpdateHitWebsiteCrawlerRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required HitWebisteCrawler websiteCrawler
}

struct UpdateHitWebsiteCrawlerResponse {
	1: required i64 logid = 1001
}

struct RemoveHitWebsiteCrawlerRequest {
	1: required OpenCommonLanguge langugeType = OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN,
	2: required i32 webid
}

struct RemoveHitWebsiteCrawlerResponse {
	1: required i64 logid = 1001,
}


struct TranslateRequest {
	1: required string from,
	2: required string to,
	3: required string text
}

struct TranslateResponse {
	1: required i64 logid = 1001,
	2: required string content
}

// ----------------------------------------------------

service Logiclayer {

	// *) ��ѯ�ؼ��ֵ���ҳ�б�
	SearchSummaryResult querySearchSummary(1: string query, 2: i32 pageno, 3: i32 limit);
	
	// *) �����
	list<BriefReport> queryBriefReport(1: i32 pageno, 2: i32 limit);

	// *) ��ѯ�ؼ��ֵĶ����б�
	list<SmsSummary> querySearchSmsSummary(1: string query, 2: i32 pageno, 3: i32 limit);

	// *) �������ѯ��ص��Ǹ��ֻ�������ϵ�Ķ��ż�
	list<SmsSummary> querySmsSummaryByPhone(1: string phoneno, 2: i32 pageno, 3: i32 limit);
	
	// *) �������ѯ��ص��Ǹ��ֻ��������ϵ�˵Ĺ�ϵͼ�б�
	list<SmsFriend> querySmsFriendByPhone(1: string phoneno);
	
	// --------------------------------------------------------------------------
	
	// @brief	���½�Ϊ�½ӿ�(�ع�, 2013.12.19)
	/*
	* @brief	�����ӿڣ�������ҳ����
	*/
	QueryOpenWebPageContentResponse queryOpenWebPageContent(1: QueryOpenWebPageContentRequest request) throws(1: FightingServiceException e);
	
	/*
	* @brief	�ṩ��ҳ���������ʱ�䷶Χ
	*/	
	QueryOpenWebPageWithTimeResponse queryOpenWebPageWithTime(1: QueryOpenWebPageWithTimeRequest request)  throws(1: FightingServiceException e);
	/*
	* @brief	�����ӿڣ� ������������
	*/
	QueryOpenShortMessageContentResponse queryOpenShortMessageContent(1: QueryOpenShortMessageContentRequest request) throws(1: FightingServiceException e);

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

	
	// -----------------------
	// *) ���ȵ������ص�
	
	// *) �����г�ĳ������Ż���
	QueryHotWebEventResponse queryHotWebEvent(1: QueryHotWebEventRequest request) throws(1: FightingServiceException e);
	
	// *) ���ĳ�����廰���eventid, չʾ�漰�������ҳ
	QueryHotWebPageByEventResponse queryHotWebPageByEvent(1: QueryHotWebPageByEventRequest request) throws(1: FightingServiceException e);
	

	// ����ӿ�����
	QueryHotWordListResponse queryHotWordList(1: QueryHotWordlistRequest request)
					 throws(1: FightingServiceException e);

	QueryHotWordTrendResponse queryHotWordTrend(1: QueryHotWordTrendRequest request)
					 throws(1: FightingServiceException e);
				
				
	// ================================			
	AddHitWebsiteCrawlerResponse addHitWebsiteCrawler(1: AddHitWebsiteCrawlerRequest request)
					 throws(1: FightingServiceException e);

	QueryHitWebsiteCrawlersResponse queryHitWebsiteCrawlers(1: QueryHitWebsiteCrawlersRequest request)
					 throws(1: FightingServiceException e);
			
	UpdateHitWebsiteCrawlerResponse updateHitWebsiteCrawler(1: UpdateHitWebsiteCrawlerRequest request)
					 throws(1: FightingServiceException e);
			
	RemoveHitWebsiteCrawlerResponse removeHitWebsiteCrawler(1: RemoveHitWebsiteCrawlerRequest request)
					 throws(1: FightingServiceException e);
				
	
	// =====================================
	// *)  �´���(Ħ����������)
	TranslateResponse translate(1: TranslateRequest request)
					throws(1: FightingServiceException e);			
				
}









