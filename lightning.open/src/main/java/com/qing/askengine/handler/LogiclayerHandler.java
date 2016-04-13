package com.qing.askengine.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;

import com.lighting.rpc.common.exception.LightningServiceException;
import com.lighting.rpc.datacenter.model.DCAddSensitiveWordRequest;
import com.lighting.rpc.datacenter.model.DCAddSensitiveWordResponse;
import com.lighting.rpc.datacenter.model.DCCommonLanguge;
import com.lighting.rpc.datacenter.model.DCConsistentTopic;
import com.lighting.rpc.datacenter.model.DCCreateTopicRequest;
import com.lighting.rpc.datacenter.model.DCCreateTopicResponse;
import com.lighting.rpc.datacenter.model.DCDateNumberTopicTrend;
import com.lighting.rpc.datacenter.model.DCHotWebEvent;
import com.lighting.rpc.datacenter.model.DCQueryHotWebEventsRequest;
import com.lighting.rpc.datacenter.model.DCQueryHotWebEventsResponse;
import com.lighting.rpc.datacenter.model.DCQueryHotWebPagesRequest;
import com.lighting.rpc.datacenter.model.DCQueryHotWebPagesResponse;
import com.lighting.rpc.datacenter.model.DCQueryHotwordListRequest;
import com.lighting.rpc.datacenter.model.DCQueryHotwordListResponse;
import com.lighting.rpc.datacenter.model.DCQueryHotwordTrendRequest;
import com.lighting.rpc.datacenter.model.DCQueryHotwordTrendResponse;
import com.lighting.rpc.datacenter.model.DCQuerySensitiveWordListRequest;
import com.lighting.rpc.datacenter.model.DCQuerySensitiveWordListResponse;
import com.lighting.rpc.datacenter.model.DCQuerySensitiveWordListTrendRequest;
import com.lighting.rpc.datacenter.model.DCQuerySensitiveWordListTrendResponse;
import com.lighting.rpc.datacenter.model.DCQuerySensitiveWordTrendRequest;
import com.lighting.rpc.datacenter.model.DCQuerySensitiveWordTrendResponse;
import com.lighting.rpc.datacenter.model.DCQueryTopicAtTimeRequest;
import com.lighting.rpc.datacenter.model.DCQueryTopicAtTimeResponse;
import com.lighting.rpc.datacenter.model.DCQueryTopicListRequest;
import com.lighting.rpc.datacenter.model.DCQueryTopicListResponse;
import com.lighting.rpc.datacenter.model.DCQueryTopicRequest;
import com.lighting.rpc.datacenter.model.DCQueryTopicResponse;
import com.lighting.rpc.datacenter.model.DCQueryTopicTrendRequest;
import com.lighting.rpc.datacenter.model.DCQueryTopicTrendResponse;
import com.lighting.rpc.datacenter.model.DCQueryTopicWebpagesRequest;
import com.lighting.rpc.datacenter.model.DCQueryTopicWebpagesResponse;
import com.lighting.rpc.datacenter.model.DCRemoveSensitiveWordRequest;
import com.lighting.rpc.datacenter.model.DCRemoveSensitiveWordResponse;
import com.lighting.rpc.datacenter.model.DCRemoveTopicRequest;
import com.lighting.rpc.datacenter.model.DCRemoveTopicResponse;
import com.lighting.rpc.datacenter.model.DCTimeTrend;
import com.lighting.rpc.datacenter.model.DCTimeWordInfo;
import com.lighting.rpc.datacenter.model.DCTimeWordTrend;
import com.lighting.rpc.datacenter.model.DCWebPage;
import com.lighting.rpc.datacenter.service.DataCenterService;
import com.lighting.rpc.webindexer.model.WIHighLightType;
import com.lighting.rpc.webindexer.model.WIQueryArticleRequest;
import com.lighting.rpc.webindexer.model.WIQueryArticleResponse;
import com.lighting.rpc.webindexer.model.WIQueryDivideType;
import com.lighting.rpc.webindexer.model.WIQueryRequest;
import com.lighting.rpc.webindexer.model.WIQueryResponse;
import com.lighting.rpc.webindexer.model.WIQueryWebPagesRequest;
import com.lighting.rpc.webindexer.model.WIQueryWebPagesResponse;
import com.lighting.rpc.webindexer.model.WIQueryWebPagesWithTimeRangeRequest;
import com.lighting.rpc.webindexer.model.WIQueryWebPagesWithTimeRangeResponse;
import com.lighting.rpc.webindexer.model.WIWebArticle;
import com.lighting.rpc.webindexer.model.WIWebPage;
import com.lighting.rpc.webindexer.model.WIWebPageItem;
import com.lighting.rpc.webindexer.service.WebIndexerService;
import com.lighting.rpc.webmetaserver.model.WMSCrawlerWebsite;
import com.lighting.rpc.webmetaserver.model.WMSCreateCrawlerWebsiteRequest;
import com.lighting.rpc.webmetaserver.model.WMSCreateCrawlerWebsiteResponse;
import com.lighting.rpc.webmetaserver.model.WMSQueryCrawlerTaskRequest;
import com.lighting.rpc.webmetaserver.model.WMSQueryCrawlerTaskResponse;
import com.lighting.rpc.webmetaserver.model.WMSRemoveCrawlerTaskRequest;
import com.lighting.rpc.webmetaserver.model.WMSUpdateCrawlerTaskRequest;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteContentRuleType;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteCrawlerType;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteType;
import com.lighting.rpc.webmetaserver.model.WMSWebsiteUrlRuleType;
import com.lighting.rpc.webmetaserver.service.WebMetaServerService;
import com.qing.askengine.controller.AskEngineController;
import com.qing.askengine.convertor.WebMetaServerConvertor;
import com.qing.askengine.manager.DigitalCenterManager;
import com.qing.askengine.manager.TibetDigitalCenterManager;
import com.qing.askengine.manager.UyDigitalCenterManager;
import com.qing.askengine.model.ConsistentTopicDO;
import com.qing.askengine.model.DigitalDateSplitTrendDO;
import com.qing.askengine.model.DigitalDateTrendDO;
import com.qing.askengine.model.FreqWordDO;
import com.qing.askengine.model.WordFreq;
import com.qing.askengine.result.KVResult;
import com.qing.askengine.service.TranslateService;
import com.qing.askengine.utils.FastJsonUtility;
import com.qing.askengine.utils.LanguageHelper;
import com.qing.askengine.utils.LogidHelper;
import com.qing.askengine.utils.TimeUtils;
import com.qing.askengine.utils.TimestampHelper;
import com.qing.askengine.utils.WordSplitUtils;
import com.qing.index.manager.SmsQingIndexManager;
import com.qing.index.manager.SmsStoreManager;
import com.qing.index.model.SmsFriendInfo;
import com.qing.index.model.SmsInfo;
import com.qing.index.model.dataobject.SmsInfoDO;
import com.qing.index.model.dataobject.SmsInfoResultDO;
import com.qing.logiclayer.AddHitWebsiteCrawlerRequest;
import com.qing.logiclayer.AddHitWebsiteCrawlerResponse;
import com.qing.logiclayer.AddSensitiveWordRequest;
import com.qing.logiclayer.AddSensitiveWordResponse;
import com.qing.logiclayer.BriefReport;
import com.qing.logiclayer.ConsistentTopic;
import com.qing.logiclayer.ContentType;
import com.qing.logiclayer.CreateConsistentTopicRequest;
import com.qing.logiclayer.CreateConsistentTopicResponse;
import com.qing.logiclayer.DateNumberTopicTrend;
import com.qing.logiclayer.FightingServiceException;
import com.qing.logiclayer.HitWebisteCrawler;
import com.qing.logiclayer.HotWebEvent;
import com.qing.logiclayer.HotWebPageContent;
import com.qing.logiclayer.ListConsistentTopicsRequest;
import com.qing.logiclayer.ListConsistentTopicsResponse;
import com.qing.logiclayer.ListShortMessageForTopicRequest;
import com.qing.logiclayer.ListShortMessageForTopicResponse;
import com.qing.logiclayer.ListShortMessageForTopicWithPhoneRequest;
import com.qing.logiclayer.ListShortMessageForTopicWithPhoneResponse;
import com.qing.logiclayer.ListWebPageForTopicRequest;
import com.qing.logiclayer.ListWebPageForTopicResponse;
import com.qing.logiclayer.Logiclayer;
import com.qing.logiclayer.OpenCommonHighlight;
import com.qing.logiclayer.OpenCommonLanguge;
import com.qing.logiclayer.OpenCommonQueryDivideType;
import com.qing.logiclayer.OpenShortMessageContent;
import com.qing.logiclayer.OpenWebPageContent;
import com.qing.logiclayer.QueryHitWebsiteCrawlersRequest;
import com.qing.logiclayer.QueryHitWebsiteCrawlersResponse;
import com.qing.logiclayer.QueryHotWebEventRequest;
import com.qing.logiclayer.QueryHotWebEventResponse;
import com.qing.logiclayer.QueryHotWebPageByEventRequest;
import com.qing.logiclayer.QueryHotWebPageByEventResponse;
import com.qing.logiclayer.QueryHotWordListResponse;
import com.qing.logiclayer.QueryHotWordTrendRequest;
import com.qing.logiclayer.QueryHotWordTrendResponse;
import com.qing.logiclayer.QueryHotWordlistRequest;
import com.qing.logiclayer.QueryOpenShortMessageContentRequest;
import com.qing.logiclayer.QueryOpenShortMessageContentResponse;
import com.qing.logiclayer.QueryOpenWebPageByDocidRequest;
import com.qing.logiclayer.QueryOpenWebPageByDocidResponse;
import com.qing.logiclayer.QueryOpenWebPageContentRequest;
import com.qing.logiclayer.QueryOpenWebPageContentResponse;
import com.qing.logiclayer.QueryOpenWebPageWithTimeRequest;
import com.qing.logiclayer.QueryOpenWebPageWithTimeResponse;
import com.qing.logiclayer.QueryShortMessageForTopicAtTimestampRequest;
import com.qing.logiclayer.QueryShortMessageForTopicAtTimestampResponse;
import com.qing.logiclayer.QueryShortMessageTrendForTopicRequest;
import com.qing.logiclayer.QueryShortMessageTrendForTopicResponse;
import com.qing.logiclayer.QueryTermCategoryWordListResponse;
import com.qing.logiclayer.QueryTermCategoryWordSplitTrendRequest;
import com.qing.logiclayer.QueryTermCategoryWordSplitTrendResponse;
import com.qing.logiclayer.QueryTermCategoryWordTrendRequest;
import com.qing.logiclayer.QueryTermCategoryWordTrendResponse;
import com.qing.logiclayer.QueryTermCategoryWordlistRequest;
import com.qing.logiclayer.QueryTracePublicWordRequest;
import com.qing.logiclayer.QueryTracePublicWordResponse;
import com.qing.logiclayer.QueryWebPageForTopicAtTimestampRequest;
import com.qing.logiclayer.QueryWebPageForTopicAtTimestampResponse;
import com.qing.logiclayer.QueryWebPageTrendForTopicRequest;
import com.qing.logiclayer.QueryWebPageTrendForTopicResponse;
import com.qing.logiclayer.RemoveConsistentTopicRequest;
import com.qing.logiclayer.RemoveConsistentTopicResponse;
import com.qing.logiclayer.RemoveHitWebsiteCrawlerRequest;
import com.qing.logiclayer.RemoveHitWebsiteCrawlerResponse;
import com.qing.logiclayer.RemoveSensitiveWordRequest;
import com.qing.logiclayer.RemoveSensitiveWordResponse;
import com.qing.logiclayer.SearchSummary;
import com.qing.logiclayer.SearchSummaryResult;
import com.qing.logiclayer.SmsFriend;
import com.qing.logiclayer.SmsSummary;
import com.qing.logiclayer.TermCategoryType;
import com.qing.logiclayer.TimeTrendSet;
import com.qing.logiclayer.TraceId;
import com.qing.logiclayer.TracePublicWord;
import com.qing.logiclayer.TranslateRequest;
import com.qing.logiclayer.TranslateResponse;
import com.qing.logiclayer.UpdateHitWebsiteCrawlerRequest;
import com.qing.logiclayer.UpdateHitWebsiteCrawlerResponse;
import com.qing.logiclayer.WordInfo;
import com.qing.logiclayer.WordSplitTimeTrend;
import com.qing.logiclayer.WordTimeTrend;

public class LogiclayerHandler implements Logiclayer.Iface {

	// *) 预警简报
//	private List<BriefReport> briefReports = new ArrayList<BriefReport>();

	// *)
	private SmsQingIndexManager smsQingIndexManager = new SmsQingIndexManager();
	// *)
	private SmsStoreManager smsStoreManager = new SmsStoreManager();

	// *) 数字中心
	// *) 用于中文词管理
	private DigitalCenterManager digitalCenterManager = new DigitalCenterManager();
	// *) 用于维文词管理
	private UyDigitalCenterManager uyDigitalCenterManager = new UyDigitalCenterManager();
	// *) 用于藏文词管理
	private TibetDigitalCenterManager tibetDigitalCenterManager = new TibetDigitalCenterManager();

	private AskEngineController askEngineController;

	public LogiclayerHandler(AskEngineController askEngineController) {
		this.askEngineController = askEngineController;
	}

	public void init() {

		smsQingIndexManager.init();
		smsQingIndexManager.prepare();
		smsStoreManager.init();

		// *) ----------------------------------------------------------
		digitalCenterManager.init();
		uyDigitalCenterManager.init();
		tibetDigitalCenterManager.init();
		// -----------------------------------------------------------------------

	}

	@Override
	public SearchSummaryResult querySearchSummary(String query, int pageno,
			int limit) throws TException {

		SearchSummaryResult result = new SearchSummaryResult();
		result.setTotal(0);
		List<SearchSummary> summaries = new ArrayList<SearchSummary>();
		result.setSummaries(summaries);

		WebIndexerService.Iface client = askEngineController
				.getCnwebIndexerService();

		try {
			WIQueryRequest request = new WIQueryRequest();
			request.setLogid(100001L);
			request.setQuery(query);
			request.setPageno(pageno);
			request.setPagesize(limit);
			WIQueryResponse response = client.query(request);

			result.setTotal(response.getTotalNumber());
			for (WIWebPage webPage : response.getWebpages()) {
				SearchSummary summary = new SearchSummary();
				summary.setTitle(webPage.getTitle());
				summary.setUrl(webPage.getUrl());
				summary.setContent(webPage.getSummary());
				summaries.add(summary);
			}
		} catch (TException e) {
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public List<BriefReport> queryBriefReport(int pageno, int limit)
			throws TException {

		List<BriefReport> results = new ArrayList<BriefReport>();
//		for (int i = pageno * limit; i < pageno * limit + limit
//				&& i < briefReports.size(); i++) {
//			BriefReport report = briefReports.get(i);
//			results.add(report);
//		}
		return results;

	}

	@Override
	public List<SmsSummary> querySearchSmsSummary(String queryItem, int pageno,
			int limit) throws TException {

		List<SmsSummary> summaries = new ArrayList<SmsSummary>();

		SmsInfoResultDO resultDO = smsQingIndexManager.query(queryItem, pageno,
				limit);

		List<SmsInfoDO> smsInfoDOs = resultDO.getSmses();
		for (int i = 0; i < smsInfoDOs.size(); i++) {
			SmsSummary summary = new SmsSummary();
			SmsInfoDO sms = smsInfoDOs.get(i);
			summary.setContent(sms.getContent());
			summary.setSendMobile(sms.getSendMobile());
			summary.setRecvMobile(sms.getRecvMobile());
			summary.setTimestamp(sms.getTimestamp());
			summary.setSendUsername(sms.getSendUsername());
			summary.setRecvUsername(sms.getRecvUsername());
			summaries.add(summary);
		}
		return summaries;

	}

	@Override
	public List<SmsSummary> querySmsSummaryByPhone(String phoneno, int pageno,
			int limit) throws TException {

		List<SmsSummary> results = new ArrayList<SmsSummary>();
		List<SmsInfo> smsinfos = smsStoreManager.querySmsByContact(phoneno,
				pageno, limit);
		for (SmsInfo sms : smsinfos) {
			SmsSummary summary = new SmsSummary();
			summary.setContent(sms.getContent());
			summary.setSendMobile(sms.getSendMobile());
			summary.setSendUsername(sms.getSendUsername());
			summary.setRecvMobile(sms.getRecvMobile());
			summary.setRecvUsername(sms.getRecvUsername());
			summary.setTimestamp(sms.getTimestamp());
			results.add(summary);
		}
		return results;

	}

	@Override
	public List<SmsFriend> querySmsFriendByPhone(String phoneno)
			throws TException {
		List<SmsFriend> friends = new ArrayList<SmsFriend>();
		List<SmsFriendInfo> infos = smsStoreManager
				.querySmsFriendsByContact(phoneno);
		for (SmsFriendInfo finfo : infos) {
			SmsFriend friend = new SmsFriend();
			friend.setPhoneno(finfo.getPhoneno());
			friend.setUsername(finfo.getUsername());
			friend.setContactNum(finfo.getContactNum());
			friends.add(friend);
		}
		return friends;
	}

	// --------------------------------------------------------------------------------------------
	// 如下皆为话题相关内容

	@Override
	public QueryOpenWebPageContentResponse queryOpenWebPageContent(
			QueryOpenWebPageContentRequest request)
			throws FightingServiceException, TException {

		QueryOpenWebPageContentResponse response = new QueryOpenWebPageContentResponse();
		response.setTotalNum(0);
		response.setWebpages(new ArrayList<OpenWebPageContent>());

		OpenCommonLanguge languageType = request.getLangugeType();

		WebIndexerService.Iface client = returnWebIndexerClient(languageType);
		
		if (client != null) {
			WIQueryWebPagesRequest orequest = new WIQueryWebPagesRequest();
			orequest.setQuery(request.getQuery());
			orequest.setPageno(request.getPageno());
			orequest.setPagesize(request.getPagesize());
			orequest.setHightlight((request.getHightlight() == OpenCommonHighlight.OC_HIGHLIGHT_ON) ? WIHighLightType.WI_HIGHLIGHT_ON
					: WIHighLightType.WI_HIGHLIGHT_OFF);
			orequest.setSummaryLength(request.getSummaryLength());
			orequest.setDivideType((request.getDivideType() == OpenCommonQueryDivideType.OC_QUERY_DIVIDE_ON) ? WIQueryDivideType.WI_QUERY_DIVIDE_ON
					: WIQueryDivideType.WI_QUERY_DIVIDE_OFF);

			WIQueryWebPagesResponse oresponse = client.queryWebpages(orequest);

			response.setTotalNum(oresponse.getTotalNum());

			for (WIWebPageItem item : oresponse.getWebpages()) {
				OpenWebPageContent summary = new OpenWebPageContent();
				summary.setDocid(item.getDocid());
				summary.setSource(item.getSource());
				summary.setContent(item.getContent());
				summary.setTitle(item.getTitle());
				summary.setTimestamp(item.getTimestamp());
				summary.setUrl(item.getUrl());
				response.getWebpages().add(summary);
			}
		}
		return response;

	}

	@Override
	public QueryOpenWebPageWithTimeResponse queryOpenWebPageWithTime(
			QueryOpenWebPageWithTimeRequest request)
			throws FightingServiceException, TException {

		QueryOpenWebPageWithTimeResponse response = new QueryOpenWebPageWithTimeResponse();
		response.setTotalNum(0);
		response.setWebpages(new ArrayList<OpenWebPageContent>());

		OpenCommonLanguge languageType = request.getLangugeType();
		String startDate = request.getStartDate();
		String endDate = request.getEndDate();

		startDate = TimestampHelper.normalize(startDate);
		endDate = TimestampHelper.normalize(endDate);

		WebIndexerService.Iface client = returnWebIndexerClient(languageType);

		if (client != null) {
			WIQueryWebPagesWithTimeRangeRequest orequest = new WIQueryWebPagesWithTimeRangeRequest();
			orequest.setQuery(request.getQuery());
			orequest.setStartDate(startDate);
			orequest.setEndDate(endDate);
			orequest.setPageno(request.getPageno());
			orequest.setPagesize(request.getPagesize());
			orequest.setSummaryLength(request.getSummaryLength());

			// WIQueryWebPagesResponse oresponse =
			// client.queryWebpages(orequest);
			WIQueryWebPagesWithTimeRangeResponse oresponse = client
					.queryWebPagesWithTimeRange(orequest);

			response.setTotalNum(oresponse.getTotalNum());

			for (WIWebPageItem item : oresponse.getWebpages()) {
				OpenWebPageContent summary = new OpenWebPageContent();
				summary.setDocid(item.getDocid());
				summary.setSource(item.getSource());
				summary.setContent(item.getContent());
				summary.setTitle(item.getTitle());
				summary.setTimestamp(item.getTimestamp());
				summary.setUrl(item.getUrl());
				response.getWebpages().add(summary);
			}
		}
		return response;

	}

	@Override
	public QueryOpenShortMessageContentResponse queryOpenShortMessageContent(
			QueryOpenShortMessageContentRequest request)
			throws FightingServiceException, TException {

		QueryOpenShortMessageContentResponse response = new QueryOpenShortMessageContentResponse();
		response.setTotalNum(0);
		response.setShortmessages(new ArrayList<OpenShortMessageContent>());

		OpenCommonLanguge languageType = request.getLangugeType();
		if (OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN == languageType) {
			// 1. 中文
			SmsInfoResultDO resultDO = smsQingIndexManager.query(
					request.getQuery(), request.getPageno(),
					request.getPagesize());
			response.setTotalNum(resultDO.getTotalNum());
			for (SmsInfoDO smsinfo : resultDO.getSmses()) {
				OpenShortMessageContent shortmessage = new OpenShortMessageContent();
				shortmessage.setDocid(smsinfo.getDocid());
				shortmessage.setRecvPhone(smsinfo.getRecvMobile());
				shortmessage.setRecvUsername(smsinfo.getRecvUsername());
				shortmessage.setSendPhone(smsinfo.getSendMobile());
				shortmessage.setSendUsermame(smsinfo.getSendUsername());
				shortmessage.setMessage(smsinfo.getContent());
				shortmessage.setTimestamp(smsinfo.getTimestamp());
				response.getShortmessages().add(shortmessage);
			}
		} else if (OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT == languageType) {
			// 2. 藏文
		}
		return response;

	}

	@Override
	public QueryTracePublicWordResponse queryTracePublicWord(
			QueryTracePublicWordRequest request)
			throws FightingServiceException, TException {

		// *) 不同语言，分词算法也不一样，这个需要特别的注意下

		// TODO Auto-generated method stub
		QueryTracePublicWordResponse response = new QueryTracePublicWordResponse();
		response.setPublicWords(new ArrayList<TracePublicWord>());

		OpenCommonLanguge languageType = request.getLangugeType();

		List<String> contentList = new ArrayList<String>();
		List<TraceId> traceIds = request.getTraceIds();
		for (TraceId traceId : traceIds) {
			if (traceId.getContentType() == ContentType.WEB_PAGE) {
				StringBuilder sb = new StringBuilder();

				WebIndexerService.Iface client = returnWebIndexerClient(languageType);
				
				if (client == null) {
					continue;
				}

				WIQueryArticleRequest wirequest = new WIQueryArticleRequest();
				wirequest.setLogid(10000L);
				wirequest.setDocid((int) traceId.getDocid());

				try {

					WIQueryArticleResponse wiresponse = client
							.queryArticle(wirequest);
					WIWebArticle article = wiresponse.getArticle();

					if (article.getContent() != null) {
						sb.append(article.getContent());
					}
					if (article.getTitle() != null) {
						sb.append(article.getTitle());
					}
					String tx = sb.toString();
					if (tx.trim().length() > 0) {
						contentList.add(tx);
					}

				} catch (TException e) {
					e.printStackTrace();
				}

			} else if (traceId.getContentType() == ContentType.SHORT_MESSAGE) {
				StringBuilder sb = new StringBuilder();
				SmsInfoDO smsInfoDO = smsQingIndexManager
						.querySmsInfoByDocid(traceId.getDocid());
				if (smsInfoDO.getContent() != null) {
					sb.append(smsInfoDO.getContent());
				}
				String tx = sb.toString();
				if (tx.trim().length() > 0) {
					contentList.add(tx);
				}
			}
		}

		List<List<WordFreq>> objes = new ArrayList<List<WordFreq>>();
		for (String content : contentList) {
			if (OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN == languageType) {
				List<WordFreq> wfl = WordSplitUtils.parseSplit(content);
				objes.add(wfl);
			} else {
				List<WordFreq> wfl = WordSplitUtils.parseSplitUy(content);
				objes.add(wfl);
			}
		}
		List<WordFreq> commonWordFreqs = WordSplitUtils
				.extractCommonWord(objes);
		List<TracePublicWord> publicWords = new ArrayList<TracePublicWord>();
		for (WordFreq wordFreq : commonWordFreqs) {
			TracePublicWord pword = new TracePublicWord();
			pword.setFreq(wordFreq.getFreq());
			pword.setWord(wordFreq.getWord());
			publicWords.add(pword);
		}
		response.setPublicWords(publicWords);
		return response;

	}

	@Override
	public QueryOpenWebPageByDocidResponse queryOpenWebPageByDocid(
			QueryOpenWebPageByDocidRequest request)
			throws FightingServiceException, TException {

		QueryOpenWebPageByDocidResponse response = new QueryOpenWebPageByDocidResponse();

		OpenCommonLanguge languageType = request.getLangugeType();

		WebIndexerService.Iface client = returnWebIndexerClient(languageType);

		if (client == null) {
			throw new FightingServiceException(1002, 1002,
					"Don't support this language");
		}

		WIQueryArticleRequest wirequest = new WIQueryArticleRequest();
		wirequest.setLogid(10000L);
		wirequest.setDocid((int) request.getDocid());

		WIQueryArticleResponse wiresponse = client.queryArticle(wirequest);

		WIWebArticle article = wiresponse.getArticle();

		OpenWebPageContent webpage = new OpenWebPageContent();

		webpage.setUrl((article.getUrl() == null) ? "" : article.getUrl());
		webpage.setTitle((article.getTitle() == null) ? "" : article.getTitle());
		webpage.setContent((article.getContent() == null) ? "" : article
				.getContent());
		// webpage.setSource("N/A");
		// webpage.setTimestamp("N/A");
		webpage.setSource(article.getSource());
		webpage.setTimestamp(article.getTimestamp());

		webpage.setDocid(request.getDocid());

		response.setWebpage(webpage);

		return response;

	}

	@Override
	public CreateConsistentTopicResponse createConsistentTopic(
			CreateConsistentTopicRequest request)
			throws FightingServiceException, TException {

		long logid = LogidHelper.genLogid();

		// *) 语言选项
		OpenCommonLanguge languageType = request.getLangugeType();
		ConsistentTopic topic = request.getTopic();

		CreateConsistentTopicResponse response = new CreateConsistentTopicResponse();
		response.setLogid(logid);
		response.setTopic(new ConsistentTopic(topic));

		try {
			DCCommonLanguge dcLanguageType = LanguageHelper
					.convert(languageType);
			
			DataCenterService.Iface client = askEngineController.getDataCenterService();
			
			DCCreateTopicRequest dcRequest = new DCCreateTopicRequest();
			dcRequest.setLogid(logid);
			dcRequest.setLangugeType(dcLanguageType);
			DCConsistentTopic dctopic = new DCConsistentTopic();
			dctopic.setTopicName(topic.getTopicName());
			dctopic.setWords(topic.getWords());
			dctopic.setStartDate(topic.getStartDate());
			dctopic.setEndDate(topic.getEndDate());
			dctopic.setPercent(topic.getPercent());
			dcRequest.setTopic(dctopic);

			DCCreateTopicResponse dcResponse = client
					.createConsistentTopic(dcRequest);
			long topicId = dcResponse.getTopic().getTopicId();

			response.setTopic(topic);
			response.getTopic().setTopicId(topicId);
			return response;
		} catch (LightningServiceException e) {
			e.printStackTrace();
			// TODO
			throw new FightingServiceException(logid, 1001, e.getErrorMessage());
		} catch (TException e) {
			e.printStackTrace();
			// TODO
			throw new FightingServiceException(logid, 1001, e.getMessage());
		}
		
	}

	@Override
	public RemoveConsistentTopicResponse removeConsistentTopic(
			RemoveConsistentTopicRequest request)
			throws FightingServiceException, TException {

		long logid = LogidHelper.genLogid();

		OpenCommonLanguge langugeType = request.getLangugeType();
		long topicId = request.getTopicId();

		RemoveConsistentTopicResponse response = new RemoveConsistentTopicResponse();
		response.setLogid(logid);

		try {
			DCCommonLanguge dcLanguageType = LanguageHelper
					.convert(langugeType);
			DataCenterService.Iface client = askEngineController.getDataCenterService();
			
			DCRemoveTopicRequest dcRequest = new DCRemoveTopicRequest();
			dcRequest.setLogid(logid);
			dcRequest.setLangugeType(dcLanguageType);
			dcRequest.setTopicId(topicId);
			DCRemoveTopicResponse dcResponse = client
					.removeConsistentTopic(dcRequest);

			return response;
		} catch (LightningServiceException e) {
			e.printStackTrace();
			// TODO
			throw new FightingServiceException(logid, 1001, e.getErrorMessage());
		} catch (TException e) {
			e.printStackTrace();
			// TODO
			throw new FightingServiceException(logid, 1001, e.getMessage());
		}

	}

	@Override
	public ListConsistentTopicsResponse listConsistentTopics(
			ListConsistentTopicsRequest request)
			throws FightingServiceException, TException {

		long logid = LogidHelper.genLogid();

		OpenCommonLanguge languageType = request.getLangugeType();
		int pageno = request.getPageno();
		int pagesize = request.getPagesize();

		ListConsistentTopicsResponse response = new ListConsistentTopicsResponse();
		response.setTotalNum(0);
		response.setTopics(new ArrayList<ConsistentTopic>());

		try {
			DCCommonLanguge dcLanguageType = LanguageHelper
					.convert(languageType);
			DataCenterService.Iface client = askEngineController.getDataCenterService();
			
			DCQueryTopicListRequest dcRequest = new DCQueryTopicListRequest();
			dcRequest.setLogid(logid);
			dcRequest.setLangugeType(dcLanguageType);
			dcRequest.setPageno(pageno);
			dcRequest.setPagesize(pagesize);

			DCQueryTopicListResponse dcResponse = client
					.queryTopicList(dcRequest);

			List<DCConsistentTopic> dcTopics = dcResponse.getTopics();
			for (DCConsistentTopic dcTopic : dcTopics) {
				ConsistentTopic topic = new ConsistentTopic();
				topic.setTopicId(dcTopic.getTopicId());
				topic.setTopicName(dcTopic.getTopicName());
				topic.setWords(dcTopic.getWords());
				topic.setStartDate(dcTopic.getStartDate());
				topic.setEndDate(dcTopic.getEndDate());
				topic.setPercent(dcTopic.getPercent());

				response.getTopics().add(topic);
			}
			response.setTotalNum(dcResponse.getTotalNum());

			return response;
		} catch (LightningServiceException e) {
			e.printStackTrace();
			// TODO
			throw new FightingServiceException(logid, 1001, e.getErrorMessage());
		} catch (TException e) {
			e.printStackTrace();
			// TODO
			throw new FightingServiceException(logid, 1001, e.getMessage());
		}

	}

	@Override
	public ListWebPageForTopicResponse listWebPageForTopic(
			ListWebPageForTopicRequest request)
			throws FightingServiceException, TException {
		// TODO Auto-generated method stub
		long logid = LogidHelper.genLogid();
		long topicId = request.getTopicId();
		int pageno = request.getPageno();
		int pagesize = request.getPagesize();

		ListWebPageForTopicResponse response = new ListWebPageForTopicResponse();
		response.setLogid(logid);
		response.setTotalNum(0);
		response.setWebpages(new ArrayList<OpenWebPageContent>());

		OpenCommonLanguge languageType = request.getLangugeType();

		try {
			DCCommonLanguge dcLanguageType = LanguageHelper
					.convert(languageType);
			DataCenterService.Iface client = askEngineController.getDataCenterService();
			
			// *) 查询出相关的
			String query = "";
			do {
				DCQueryTopicRequest dcQTRequest = new DCQueryTopicRequest();
				dcQTRequest.setLogid(logid);
				dcQTRequest.setLangugeType(dcLanguageType);
				dcQTRequest.setTopicId(topicId);

				DCQueryTopicResponse dcQTResponse = client
						.queryConsistentTopic(dcQTRequest);
				List<String> words = dcQTResponse.getTopic().getWords();

				StringBuilder sb = new StringBuilder();
				for (String word : words) {
					sb.append(word).append(" ");
				}
				query = sb.toString();
			} while (false);

			DCQueryTopicWebpagesRequest dcRequest = new DCQueryTopicWebpagesRequest();
			dcRequest.setLogid(logid);
			dcRequest.setLangugeType(dcLanguageType);
			dcRequest.setPageno(pageno);
			dcRequest.setPagesize(pagesize);
			dcRequest.setTopicId(topicId);

			DCQueryTopicWebpagesResponse dcResponse = client
					.queryTopicWebpages(dcRequest);

			for (DCWebPage dcwebpage : dcResponse.getWebpages()) {
				OpenWebPageContent owebpage = new OpenWebPageContent();
				owebpage.setUrl(dcwebpage.getUrl());
				owebpage.setSource(dcwebpage.getSource());
				owebpage.setTitle(dcwebpage.getTitle());
				owebpage.setTimestamp(dcwebpage.getTimestamp());

				String content = dcwebpage.getContent();
				content = WordSplitUtils.generateLanguageSummary("cn",
						"content", query, content, 80);
				owebpage.setContent(content);

				response.getWebpages().add(owebpage);
			}
			response.setTotalNum(0);

			return response;
		} catch (LightningServiceException e) {
			e.printStackTrace();
			// TODO
			throw new FightingServiceException(logid, 1001, e.getErrorMessage());
		} catch (TException e) {
			e.printStackTrace();
			// TODO
			throw new FightingServiceException(logid, 1001, e.getMessage());
		}

	}

	@Override
	public QueryWebPageTrendForTopicResponse queryWebPageTrendForTopic(
			QueryWebPageTrendForTopicRequest request)
			throws FightingServiceException, TException {

		long logid = LogidHelper.genLogid();

		OpenCommonLanguge langugeType = request.getLangugeType();
		long topicId = request.getTopicId();

		QueryWebPageTrendForTopicResponse response = new QueryWebPageTrendForTopicResponse();
		response.setLogid(logid);
		response.setTrends(new ArrayList<DateNumberTopicTrend>());

		try {
			DCCommonLanguge dcLanguageType = LanguageHelper
					.convert(langugeType);
			DataCenterService.Iface client = askEngineController.getDataCenterService();
			
			DCQueryTopicTrendRequest dcRequest = new DCQueryTopicTrendRequest();
			dcRequest.setLogid(logid);
			dcRequest.setLangugeType(dcLanguageType);
			dcRequest.setTopicId(topicId);

			DCQueryTopicTrendResponse dcResponse = client
					.queryTopicTrend(dcRequest);
			for (DCDateNumberTopicTrend trend : dcResponse.getTrends()) {
				DateNumberTopicTrend ttrend = new DateNumberTopicTrend();
				ttrend.setDate(trend.getDate());
				ttrend.setNumber(trend.getNumber());
				response.getTrends().add(ttrend);
			}

			return response;
		} catch (LightningServiceException e) {
			e.printStackTrace();
			throw new FightingServiceException(logid, 1001, e.getErrorMessage());
		} catch (TException e) {
			e.printStackTrace();
			throw new FightingServiceException(logid, 1001, e.getMessage());
		}

	}

	@Override
	public QueryWebPageForTopicAtTimestampResponse queryWebPageForTopicAtTimestamp(
			QueryWebPageForTopicAtTimestampRequest request)
			throws FightingServiceException, TException {
		// TODO Auto-generated method stub

		long logid = LogidHelper.genLogid();
		OpenCommonLanguge langugeType = request.getLangugeType();
		String timestamp = request.getTimestamp();
		long topicId = request.getTopicId();

		timestamp = TimestampHelper.normalize(timestamp);

		QueryWebPageForTopicAtTimestampResponse response = new QueryWebPageForTopicAtTimestampResponse();
		response.setLogid(logid);
		response.setWebpages(new ArrayList<OpenWebPageContent>());

		try {
			DCCommonLanguge dcLanguageType = LanguageHelper
					.convert(langugeType);
			DataCenterService.Iface client = askEngineController.getDataCenterService();
			
			// *) 查询出相关的
			String query = "";
			do {
				DCQueryTopicRequest dcQTRequest = new DCQueryTopicRequest();
				dcQTRequest.setLogid(logid);
				dcQTRequest.setLangugeType(dcLanguageType);
				dcQTRequest.setTopicId(topicId);

				DCQueryTopicResponse dcQTResponse = client
						.queryConsistentTopic(dcQTRequest);
				List<String> words = dcQTResponse.getTopic().getWords();

				StringBuilder sb = new StringBuilder();
				for (String word : words) {
					sb.append(word).append(" ");
				}
				query = sb.toString();
			} while (false);

			DCQueryTopicAtTimeRequest dcRequest = new DCQueryTopicAtTimeRequest();
			dcRequest.setLogid(logid);
			dcRequest.setLangugeType(dcLanguageType);
			dcRequest.setTimestamp(timestamp);
			dcRequest.setTopicId(topicId);

			DCQueryTopicAtTimeResponse dcResponse = client
					.queryTopicAtTime(dcRequest);

			for (DCWebPage webpage : dcResponse.getWebpages()) {
				OpenWebPageContent item = new OpenWebPageContent();
				item.setTimestamp(webpage.getTimestamp());
				item.setSource(webpage.getSource());
				item.setTitle(webpage.getTitle());
				item.setUrl(webpage.getUrl());
				item.setDocid(0L);

				String content = webpage.getContent();
				content = WordSplitUtils.generateLanguageSummary("cn",
						"content", query, content, 80);
				item.setContent(content);

				response.getWebpages().add(item);
			}

			return response;
		} catch (LightningServiceException e) {
			e.printStackTrace();
			throw new FightingServiceException(logid, 1001, e.getErrorMessage());
		} catch (TException e) {
			e.printStackTrace();
			throw new FightingServiceException(logid, 1001, e.getMessage());
		}

	}

	@Override
	public ListShortMessageForTopicResponse listShortMessageForTopic(
			ListShortMessageForTopicRequest request)
			throws FightingServiceException, TException {

		long logid = LogidHelper.genLogid();
		long topicId = request.getTopicId();

		ListShortMessageForTopicResponse response = new ListShortMessageForTopicResponse();
		response.setTotalNum(0);
		response.setShortmessages(new ArrayList<OpenShortMessageContent>());

		OpenCommonLanguge langugeType = request.getLangugeType();

		if (OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN == langugeType) {

			ConsistentTopicDO topic = queryTopic(logid, langugeType, topicId);
			if (topic == null) {
				return response;
			}

			StringBuilder sb = new StringBuilder();
			List<String> wordSet = topic.getWords();
			for (String word : wordSet) {
				sb.append(word).append(" ");
			}

			SmsInfoResultDO smsInfoResultDO = smsQingIndexManager.query(
					sb.toString(), request.getPageno(), request.getPagesize());

			List<SmsInfoDO> smses = smsInfoResultDO.getSmses();
			for (SmsInfoDO sms : smses) {
				OpenShortMessageContent item = new OpenShortMessageContent();
				item.setTimestamp(TimeUtils.shortMessage2Normal(sms
						.getTimestamp()));
				item.setMessage(sms.getContent());
				item.setRecvPhone(sms.getRecvMobile());
				item.setRecvUsername(sms.getRecvUsername());
				item.setSendPhone(sms.getSendMobile());
				item.setSendUsermame(sms.getSendUsername());
				item.setDocid(0L);
				response.getShortmessages().add(item);
			}

		} else if (OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT == langugeType) {
			// TODO
		}

		return response;

	}

	@Override
	public ListShortMessageForTopicWithPhoneResponse listShortMessageForTopicWithPhone(
			ListShortMessageForTopicWithPhoneRequest request)
			throws FightingServiceException, TException {
		// TODO Auto-generated method stub

		long logid = LogidHelper.genLogid();
		long topicId = request.getTopicId();

		ListShortMessageForTopicWithPhoneResponse response = new ListShortMessageForTopicWithPhoneResponse();
		response.setTotalNum(0);
		response.setShortmessages(new ArrayList<OpenShortMessageContent>());

		OpenCommonLanguge langugeType = request.getLangugeType();
		if (OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN == langugeType) {

			ConsistentTopicDO topic = queryTopic(logid, langugeType, topicId);
			if (topic == null) {
				return response;
			}

			List<String> wordList = new ArrayList<String>();
			List<String> wordSet = topic.getWords();
			for (String word : wordSet) {
				wordList.add(word);
			}

			SmsInfoResultDO smsInfoResultDO = smsQingIndexManager.query(
					request.getPhone(), wordList, request.getPageno(),
					request.getPagesize());

			List<SmsInfoDO> smses = smsInfoResultDO.getSmses();
			for (SmsInfoDO sms : smses) {
				OpenShortMessageContent item = new OpenShortMessageContent();
				item.setTimestamp(TimeUtils.shortMessage2Normal(sms
						.getTimestamp()));
				item.setMessage(sms.getContent());
				item.setRecvPhone(sms.getRecvMobile());
				item.setRecvUsername(sms.getRecvUsername());
				item.setSendPhone(sms.getSendMobile());
				item.setSendUsermame(sms.getSendUsername());
				item.setDocid(sms.getDocid());
				response.getShortmessages().add(item);
			}
			response.setTotalNum(smsInfoResultDO.getTotalNum());

		} else if (OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT == langugeType) {

		}
		return response;

	}

	@Override
	public QueryShortMessageTrendForTopicResponse queryShortMessageTrendForTopic(
			QueryShortMessageTrendForTopicRequest request)
			throws FightingServiceException, TException {

		QueryShortMessageTrendForTopicResponse response = new QueryShortMessageTrendForTopicResponse();
		response.setTrends(new ArrayList<DateNumberTopicTrend>());

		OpenCommonLanguge langugeType = request.getLangugeType();
		if (OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN == langugeType) {

		} else if (OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT == langugeType) {

		}

		return response;

	}

	@Override
	public QueryShortMessageForTopicAtTimestampResponse queryShortMessageForTopicAtTimestamp(
			QueryShortMessageForTopicAtTimestampRequest request)
			throws FightingServiceException, TException {

		long logid = LogidHelper.genLogid();
		long topicId = request.getTopicId();

		// TODO Auto-generated method stub
		QueryShortMessageForTopicAtTimestampResponse response = new QueryShortMessageForTopicAtTimestampResponse();
		response.setShortmessages(new ArrayList<OpenShortMessageContent>());

		OpenCommonLanguge langugeType = request.getLangugeType();

		if (OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN == langugeType) {
			ConsistentTopicDO topic = queryTopic(logid, langugeType, topicId);
			if (topic == null) {
				return response;
			}
			List<SmsInfo> smses = smsStoreManager.querySmsByTimestampAndWords(
					topic.getWords(), request.getTimestamp());
			for (SmsInfo sms : smses) {
				OpenShortMessageContent item = new OpenShortMessageContent();
				item.setTimestamp(TimeUtils.shortMessage2Normal(sms
						.getTimestamp()));
				item.setMessage(sms.getContent());
				item.setRecvPhone(sms.getRecvMobile());
				item.setRecvUsername(sms.getRecvUsername());
				item.setSendPhone(sms.getSendMobile());
				item.setSendUsermame(sms.getSendUsername());
				item.setDocid(0L);
				response.getShortmessages().add(item);
			}

			if (response.getShortmessages().size() == 0) {
				List<SmsInfo> smses2 = smsStoreManager.querySmsByWords(topic
						.getWords());
				for (SmsInfo sms : smses2) {
					OpenShortMessageContent item = new OpenShortMessageContent();
					item.setTimestamp(TimeUtils.convert2Normal("yyyyMMdd",
							request.getTimestamp()));
					item.setMessage(sms.getContent());
					item.setRecvPhone(sms.getRecvMobile());
					item.setRecvUsername(sms.getRecvUsername());
					item.setSendPhone(sms.getSendMobile());
					item.setSendUsermame(sms.getSendUsername());
					item.setDocid(0L);
					response.getShortmessages().add(item);
				}
			}
		} else if (OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT == langugeType) {
			// TODO

		}
		return response;
	}

	// --------------------------------------------------------------------------------------

	@Override
	public QueryTermCategoryWordListResponse queryTermCategoryWordlist(
			QueryTermCategoryWordlistRequest request)
			throws FightingServiceException, TException {

		long logid = 10001L;

		int pageno = request.getPageno();
		int pagesize = request.getPagesize();
		OpenCommonLanguge langugeType = request.getLangugeType();
		TermCategoryType tctype = request.getTctype();

		QueryTermCategoryWordListResponse response = new QueryTermCategoryWordListResponse();
		response.setTotalNum(0);
		response.setWords(new ArrayList<String>());

		// *) 这边对敏感词特殊对待
		if (TermCategoryType.TCT_SENSWORD_TYPE == tctype) {
			DCCommonLanguge dcLanguageType = LanguageHelper
					.convert(langugeType);
			DataCenterService.Iface client = askEngineController.getDataCenterService();
			
			DCQuerySensitiveWordListRequest dcRequest = new DCQuerySensitiveWordListRequest();
			dcRequest.setLogid(logid);
			dcRequest.setLangugeType(dcLanguageType);
			dcRequest.setPageno(pageno);
			dcRequest.setPagesize(pagesize);

			try {
				DCQuerySensitiveWordListResponse dcResponse = client
						.querySensitiveWordList(dcRequest);
				for (String word : dcResponse.getWords()) {
					response.getWords().add(word);
				}
			} catch (LightningServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}

			return response;
		}

		//
		if (OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN == langugeType) {
			// 中文处理
			if (TermCategoryType.TCT_UNUSWORD_TYPE == tctype) {
				response.getWords().addAll(
						digitalCenterManager.fetchUnusualWordList(
								request.getPageno(), request.getPagesize()));
				response.setTotalNum(digitalCenterManager
						.fetchUnusualTotalWords());
			}
		} else if (OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT == langugeType) {

			if (TermCategoryType.TCT_UNUSWORD_TYPE == tctype) {
				response.getWords().addAll(
						uyDigitalCenterManager.fetchUnusualWordList(
								request.getPageno(), request.getPagesize()));
				response.setTotalNum(uyDigitalCenterManager
						.fetchUnusualTotalWords());
			}

		} else if (OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT == langugeType) {
			if (TermCategoryType.TCT_UNUSWORD_TYPE == tctype) {
				response.getWords().addAll(
						tibetDigitalCenterManager.fetchUnusualWordList(
								request.getPageno(), request.getPagesize()));
				response.setTotalNum(tibetDigitalCenterManager
						.fetchUnusualTotalWords());
			}

		}

		return response;

	}

	@Override
	public QueryTermCategoryWordTrendResponse queryTermCategoryWordTrend(
			QueryTermCategoryWordTrendRequest request)
			throws FightingServiceException, TException {
		// TODO Auto-generated method stub

		long logid = 10001L;
		String startDate = request.getStartdate();
		String endDate = request.getEnddate();
		// TODO, 对日期进行转换
		startDate = TimestampHelper.normalize(startDate);
		endDate = TimestampHelper.normalize(endDate);

		QueryTermCategoryWordTrendResponse response = new QueryTermCategoryWordTrendResponse();
		response.setTrends(new ArrayList<TimeTrendSet>());

		// *) 做参数校验
		int startDateValue = 0;
		int endDateValue = 0;
		try {
			startDateValue = TimeUtils.convertToIntForYYYYMMDD(request
					.getStartdate());
			endDateValue = TimeUtils.convertToIntForYYYYMMDD(request
					.getEnddate());
			if (startDateValue >= endDateValue) {
				throw new FightingServiceException(1001, 1000,
						"illegal argument");
			}
		} catch (Throwable e) {
			throw new FightingServiceException(1001, 1000, "illegal argument");
		}

		OpenCommonLanguge langugeType = request.getLangugeType();
		TermCategoryType tctype = request.getTctype();

		// *) -----------------------------------------------------------------
		if (TermCategoryType.TCT_SENSWORD_TYPE == tctype) {
			DCCommonLanguge dcLanguageType = LanguageHelper
					.convert(langugeType);
			DataCenterService.Iface client = askEngineController.getDataCenterService();
			
			DCQuerySensitiveWordListTrendRequest dcRequest = new DCQuerySensitiveWordListTrendRequest();
			dcRequest.setLogid(logid);
			dcRequest.setLangugeType(dcLanguageType);
			dcRequest.setPageno(0);
			dcRequest.setPagesize(10);
			dcRequest.setStartDate(startDate);
			dcRequest.setEndDate(endDate);

			try {
				DCQuerySensitiveWordListTrendResponse dcResponse = client
						.querySensitiveWordListTrend(dcRequest);
				dcResponse.getTrends();

				for (DCTimeWordTrend dctwt : dcResponse.getTrends()) {
					TimeTrendSet tts = new TimeTrendSet();
					tts.setDate(dctwt.getTimestamp());
					tts.setWordinfos(new ArrayList<WordInfo>());
					for (DCTimeWordInfo dctwi : dctwt.getWordinfos()) {
						tts.getWordinfos().add(
								new WordInfo(dctwi.getWord(), dctwi.getFreq()));
					}
					response.getTrends().add(tts);
				}

			} catch (LightningServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}

			return response;
		}

		if (OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN == langugeType) {
			if (TermCategoryType.TCT_UNUSWORD_TYPE == tctype) {

				List<DigitalDateTrendDO> dateTrendDOs = digitalCenterManager
						.fetchUnusualWordTrend4time(startDateValue,
								endDateValue);
				for (DigitalDateTrendDO trend : dateTrendDOs) {
					TimeTrendSet set = new TimeTrendSet();
					set.setDate(trend.getDate());
					List<FreqWordDO> freqWordDOs = trend.getFreqWordDOs();
					set.setWordinfos(new ArrayList<WordInfo>());
					for (FreqWordDO freqWord : freqWordDOs) {
						set.getWordinfos().add(
								new WordInfo(freqWord.getWord(), freqWord
										.getFreq()));
					}
					response.getTrends().add(set);
				}

			}
		} else if (OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT == langugeType) {

			List<DigitalDateTrendDO> dateTrendDOs = new ArrayList<DigitalDateTrendDO>();
			if (TermCategoryType.TCT_UNUSWORD_TYPE == tctype) {
				dateTrendDOs = uyDigitalCenterManager
						.fetchUnusualWordTrend4time(startDateValue,
								endDateValue);
			}

			for (DigitalDateTrendDO trend : dateTrendDOs) {
				TimeTrendSet set = new TimeTrendSet();
				set.setDate(trend.getDate());
				List<FreqWordDO> freqWordDOs = trend.getFreqWordDOs();
				set.setWordinfos(new ArrayList<WordInfo>());
				for (FreqWordDO freqWord : freqWordDOs) {
					set.getWordinfos()
							.add(new WordInfo(freqWord.getWord(), freqWord
									.getFreq()));
				}
				response.getTrends().add(set);
			}

		} else if (OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT == langugeType) {

			List<DigitalDateTrendDO> dateTrendDOs = new ArrayList<DigitalDateTrendDO>();
			if (TermCategoryType.TCT_UNUSWORD_TYPE == tctype) {
				dateTrendDOs = tibetDigitalCenterManager
						.fetchUnusualWordTrend4time(startDateValue,
								endDateValue);
			}
			// TODO

			for (DigitalDateTrendDO trend : dateTrendDOs) {
				TimeTrendSet set = new TimeTrendSet();
				set.setDate(trend.getDate());
				List<FreqWordDO> freqWordDOs = trend.getFreqWordDOs();
				set.setWordinfos(new ArrayList<WordInfo>());
				for (FreqWordDO freqWord : freqWordDOs) {
					set.getWordinfos()
							.add(new WordInfo(freqWord.getWord(), freqWord
									.getFreq()));
				}
				response.getTrends().add(set);
			}

		}

		return response;

	}

	// --------------------------------------------------

	@Override
	public QueryTermCategoryWordSplitTrendResponse queryTermCategoryWordSplitTrend(
			QueryTermCategoryWordSplitTrendRequest request)
			throws FightingServiceException, TException {
		// TODO Auto-generated method stub

		long logid = 10001L;
		String word = request.getWord();
		String startDate = request.getStartdate();
		String endDate = request.getEnddate();
		OpenCommonLanguge langugeType = request.getLangugeType();
		TermCategoryType tctype = request.getTctype();

		startDate = TimestampHelper.normalize(startDate);
		endDate = TimestampHelper.normalize(endDate);

		QueryTermCategoryWordSplitTrendResponse response = new QueryTermCategoryWordSplitTrendResponse();
		response.setLogid(logid);
		response.setTrends(new ArrayList<WordSplitTimeTrend>());

		if (TermCategoryType.TCT_SENSWORD_TYPE == tctype) {
			DCCommonLanguge dcLanguageType = LanguageHelper
					.convert(langugeType);
			DataCenterService.Iface client = askEngineController.getDataCenterService();
			
			DCQuerySensitiveWordTrendRequest dcRequest = new DCQuerySensitiveWordTrendRequest();
			dcRequest.setLogid(logid);
			dcRequest.setLangugeType(dcLanguageType);
			dcRequest.setWord(word);
			dcRequest.setStartDate(startDate);
			dcRequest.setEndDate(endDate);

			try {
				DCQuerySensitiveWordTrendResponse dcResponse = client
						.querySensitiveWordTrend(dcRequest);

				for (DCTimeTrend dctt : dcResponse.getTrends()) {
					WordSplitTimeTrend wstt = new WordSplitTimeTrend();
					wstt.setDate(dctt.getTimestamp());
					wstt.setPagefreq(dctt.getFreq());
					wstt.setSmsfreq(dctt.getFreq() / 2);

					response.getTrends().add(wstt);
				}

			} catch (LightningServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}

			return response;
		}

		// *) 做参数校验
		int startDateValue = 0;
		int endDateValue = 0;
		try {
			startDateValue = TimeUtils.convertToIntForYYYYMMDD(request
					.getStartdate());
			endDateValue = TimeUtils.convertToIntForYYYYMMDD(request
					.getEnddate());
			if (startDateValue >= endDateValue) {
				throw new FightingServiceException(1001, 1000,
						"illegal argument");
			}
		} catch (Throwable e) {
			throw new FightingServiceException(1001, 1000, "illegal argument");
		}

		if (OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN == langugeType) {
			if (TermCategoryType.TCT_UNUSWORD_TYPE == tctype) {

				List<DigitalDateSplitTrendDO> dateTrendDOs = digitalCenterManager
						.fetchSplitUnusualWordTrend4time(request.getWord(),
								startDateValue, endDateValue);
				for (DigitalDateSplitTrendDO trend : dateTrendDOs) {
					WordSplitTimeTrend wstrend = new WordSplitTimeTrend();
					wstrend.setDate(trend.getDate());
					wstrend.setPagefreq(trend.getWebfreq());
					wstrend.setSmsfreq(trend.getSmsfreq());
					response.getTrends().add(wstrend);
				}

			}

		} else if (OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT == langugeType) {

			List<DigitalDateSplitTrendDO> dateTrendDOs = new ArrayList<DigitalDateSplitTrendDO>();
			if (TermCategoryType.TCT_UNUSWORD_TYPE == tctype) {
				dateTrendDOs = uyDigitalCenterManager
						.fetchSplitUnusualWordTrend4time(request.getWord(),
								startDateValue, endDateValue);
			}
			// TODO

			for (DigitalDateSplitTrendDO trend : dateTrendDOs) {
				WordSplitTimeTrend wstrend = new WordSplitTimeTrend();
				wstrend.setDate(trend.getDate());
				wstrend.setPagefreq(trend.getWebfreq());
				wstrend.setSmsfreq(trend.getSmsfreq());
				response.getTrends().add(wstrend);
			}

		} else if (OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT == langugeType) {

			List<DigitalDateSplitTrendDO> dateTrendDOs = new ArrayList<DigitalDateSplitTrendDO>();
			if (TermCategoryType.TCT_UNUSWORD_TYPE == tctype) {
				dateTrendDOs = tibetDigitalCenterManager
						.fetchSplitUnusualWordTrend4time(request.getWord(),
								startDateValue, endDateValue);
			}

			for (DigitalDateSplitTrendDO trend : dateTrendDOs) {
				WordSplitTimeTrend wstrend = new WordSplitTimeTrend();
				wstrend.setDate(trend.getDate());
				wstrend.setPagefreq(trend.getWebfreq());
				wstrend.setSmsfreq(trend.getSmsfreq());
				response.getTrends().add(wstrend);
			}

		}

		return response;

	}

	@Override
	public AddSensitiveWordResponse addSensitiveWord(
			AddSensitiveWordRequest request) throws FightingServiceException,
			TException {
		// TODO Auto-generated method stub
		long logid = 2000L;

		OpenCommonLanguge languageType = request.getLangugeType();
		String word = request.getWord();

		AddSensitiveWordResponse response = new AddSensitiveWordResponse();
		response.setLogid(logid);

		DCCommonLanguge dcLanguageType = LanguageHelper.convert(languageType);

		DataCenterService.Iface client = askEngineController.getDataCenterService();
		
		try {

			DCAddSensitiveWordRequest dcRequest = new DCAddSensitiveWordRequest();
			dcRequest.setLogid(logid);
			dcRequest.setLangugeType(dcLanguageType);
			dcRequest.setWord(word);

			DCAddSensitiveWordResponse dcResponse = client
					.addSensitiveWord(dcRequest);

			int successAmount = dcResponse.getSuccessAmount();

		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}

		return response;

	}

	@Override
	public RemoveSensitiveWordResponse removeSensitiveWord(
			RemoveSensitiveWordRequest request)
			throws FightingServiceException, TException {

		long logid = 1000L;

		OpenCommonLanguge languageType = request.getLangugeType();
		String word = request.getWord();

		RemoveSensitiveWordResponse response = new RemoveSensitiveWordResponse();
		response.setLogid(logid);

		DCCommonLanguge dcLanguageType = LanguageHelper.convert(languageType);

		DataCenterService.Iface client = askEngineController.getDataCenterService();
		
		try {

			DCRemoveSensitiveWordRequest dcRequest = new DCRemoveSensitiveWordRequest();
			dcRequest.setLogid(logid);
			dcRequest.setLangugeType(dcLanguageType);
			dcRequest.setWord(word);

			DCRemoveSensitiveWordResponse dcResponse = client
					.removeSensitiveWord(dcRequest);

			int successAmount = dcResponse.getSuccessAmount();

		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}

		return response;

	}

	// --------------------------------------------------------------------
	// 如下是新增的服务接口，热点分析
	@Override
	public QueryHotWebEventResponse queryHotWebEvent(
			QueryHotWebEventRequest request) throws FightingServiceException,
			TException {
		// TODO Auto-generated method stub

		long logid = 1000L;
		String timestamp = request.getTimestamp();
		timestamp = TimestampHelper.normalize(timestamp);

		QueryHotWebEventResponse response = new QueryHotWebEventResponse();
		response.setLogid(logid);
		response.setTotalNum(0);
		response.setEvents(new ArrayList<HotWebEvent>());

		DataCenterService.Iface client = askEngineController.getDataCenterService();
		
		try {
			DCQueryHotWebEventsRequest req = new DCQueryHotWebEventsRequest();
			req.setLogid(logid);
			req.setTimestamp(request.getTimestamp());
			req.setPageno(request.getPageno());
			req.setPagesize(request.getPagesize());

			DCQueryHotWebEventsResponse res = client.queryHotWebEvents(req);

			List<DCHotWebEvent> events = res.getHotEvents();
			for (DCHotWebEvent event : events) {
				HotWebEvent revent = new HotWebEvent();
				revent.setEventid(event.getEventid());
				revent.setTitle(event.getTitle());
				revent.setTimestamp(timestamp);
				revent.setScore(event.getScore());
				response.getEvents().add(revent);
			}
			// TODO
			// 这边需要在补充一下
			response.setTotalNum(res.getHotEventsSize());
		} catch (LightningServiceException e) {

		}

		return response;
	}

	@Override
	public QueryHotWebPageByEventResponse queryHotWebPageByEvent(
			QueryHotWebPageByEventRequest request)
			throws FightingServiceException, TException {
		// TODO Auto-generated method stub

		long logid = 1000L;

		QueryHotWebPageByEventResponse response = new QueryHotWebPageByEventResponse();
		response.setLogid(logid);
		response.setTotalNum(0);
		response.setWebpages(new ArrayList<HotWebPageContent>());

		DataCenterService.Iface client = askEngineController.getDataCenterService();
		
		try {
			DCQueryHotWebPagesRequest req = new DCQueryHotWebPagesRequest();
			req.setLogid(logid);
			req.setEventid(request.getEventid());
			req.setPageno(request.getPageno());
			req.setPagesize(request.getPagesize());

			DCQueryHotWebPagesResponse res = client.queryHotWebPages(req);

			List<DCWebPage> webpages = res.getWebPages();
			for (DCWebPage event : webpages) {
				HotWebPageContent revent = new HotWebPageContent();
				revent.setUrl(event.getUrl());
				revent.setTitle(event.getTitle());
				revent.setTimestamp(event.getTimestamp());
				revent.setSource(event.getSource());

				String content = event.getContent();
				// content = WordSplitUtils.generateLanguageSummary("cn",
				// "content", event.getTitle(), content, 80);
				content = content.trim();
				content = content.substring(0, Math.min(160, content.length()))
						.replaceAll("[\r\n]", " ");

				revent.setContent(content);

				response.getWebpages().add(revent);
			}
			response.setTotalNum(res.getTotalNum());
		} catch (LightningServiceException e) {

		}
		return response;

	}

	@Override
	public QueryHotWordListResponse queryHotWordList(
			QueryHotWordlistRequest request) throws FightingServiceException,
			TException {

		long logid = 1000L;
		String timestamp = request.getTimestamp();
		int pageno = request.getPageno();
		int pagesize = request.getPagesize();

		timestamp = TimestampHelper.normalize(timestamp);

		QueryHotWordListResponse response = new QueryHotWordListResponse();
		response.setLogid(logid);
		response.setWords(new ArrayList<String>());

		DataCenterService.Iface client = askEngineController.getDataCenterService();
		
		try {
			DCQueryHotwordListRequest dcrequest = new DCQueryHotwordListRequest();
			dcrequest.setLogid(logid);
			dcrequest.setTimestamp(timestamp);
			dcrequest.setPageno(pageno);
			dcrequest.setPagesize(pagesize);

			DCQueryHotwordListResponse dcresponse = client
					.queryHotwordList(dcrequest);
			for (String word : dcresponse.getWords()) {
				response.getWords().add(word);
			}
		} catch (LightningServiceException e) {
		}

		return response;

	}

	@Override
	public QueryHotWordTrendResponse queryHotWordTrend(
			QueryHotWordTrendRequest request) throws FightingServiceException,
			TException {

		String word = request.getWord();
		String startDate = request.getStartdate();
		String endDate = request.getEnddate();

		startDate = TimestampHelper.normalize(startDate);
		endDate = TimestampHelper.normalize(endDate);

		long logid = 10001L;

		QueryHotWordTrendResponse response = new QueryHotWordTrendResponse();
		response.setLogid(logid);
		response.setTrends(new ArrayList<WordTimeTrend>());

		DataCenterService.Iface client = askEngineController.getDataCenterService();
		
		try {
			// ---------------------------------------
			DCQueryHotwordTrendRequest dcrequest = new DCQueryHotwordTrendRequest();
			dcrequest.setWord(word);
			dcrequest.setStartDate(startDate);
			dcrequest.setEndDate(endDate);

			DCQueryHotwordTrendResponse dcresponse = client
					.queryHotwordTrend(dcrequest);
			for (DCTimeTrend trend : dcresponse.getTrends()) {
				response.getTrends()
						.add(new WordTimeTrend(trend.getTimestamp(), trend
								.getFreq()));
			}

		} catch (LightningServiceException e) {
		}

		return response;

	}

	private ConsistentTopicDO queryTopic(long logid,
			OpenCommonLanguge langugeType, long topicId) {
		try {
			DCCommonLanguge dcLanguageType = LanguageHelper
					.convert(langugeType);
			DataCenterService.Iface client = askEngineController.getDataCenterService();
			
			DCQueryTopicRequest dcRequest = new DCQueryTopicRequest();
			dcRequest.setLogid(logid);
			dcRequest.setLangugeType(dcLanguageType);
			dcRequest.setTopicId(topicId);

			DCQueryTopicResponse dcResponse = client
					.queryConsistentTopic(dcRequest);
			DCConsistentTopic dctopic = dcResponse.getTopic();

			ConsistentTopicDO topicDO = new ConsistentTopicDO();

			topicDO.setTopicId(dctopic.getTopicId());
			topicDO.setTopicName(dctopic.getTopicName());
			topicDO.setWords(dctopic.getWords());
			topicDO.setStartDate(dctopic.getStartDate());
			topicDO.setEndDate(dctopic.getEndDate());
			topicDO.setPercent(dctopic.getPercent());

			return topicDO;

		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private WebIndexerService.Iface returnWebIndexerClient(OpenCommonLanguge languageType) {
		if (languageType == OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN) {
			// *) 处理中文检索
			return askEngineController.getCnwebIndexerService();
		} else if (languageType == OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT) {
			// *) 处理藏文检索
			return null;
		} else if (languageType == OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT) {
			// *) 处理维文检索
			return askEngineController.getUywebIndexerService();
		}
		return null;
	}

	// --------------------------------------------
	
	@Override
	public AddHitWebsiteCrawlerResponse addHitWebsiteCrawler(
			AddHitWebsiteCrawlerRequest request)
			throws FightingServiceException, TException {
		// TODO Auto-generated method stub
		
		long logid = LogidHelper.genLogid();
		
		WebMetaServerService.Iface wmservice = askEngineController.getWebMetaServerService();
		
		WMSCrawlerWebsite webinfo = new WMSCrawlerWebsite();
		webinfo.setUrlType(WMSWebsiteUrlRuleType.WMS_WEBSITE_URL_RULE_HIT);
		webinfo.setContentType(WMSWebsiteContentRuleType.WMS_WEBSITE_CONTENT_RULE_TEMPLATE_HPATH);
		webinfo.setCrawlerType(WMSWebsiteCrawlerType.WMS_WEBSITE_CRAWLER_STAND_ALONE);
		webinfo.setCrawlerSchedule(0);
		
		webinfo.setWebsite(request.getWebsiteCrawler().getWebsite());
		webinfo.setCrawlerNum(request.getWebsiteCrawler().getCrawlerNum());

		HitWebisteCrawler hitWebsiteCrawler = request.getWebsiteCrawler();

		String contentRule = FastJsonUtility.toJson4ContentRule(hitWebsiteCrawler.getTitleContentExp(), 
				hitWebsiteCrawler.getContentContentExp(), 
				hitWebsiteCrawler.getTimeContentExp());
		webinfo.setContentRule(contentRule);
		
		String urlRule = FastJsonUtility.toJson4UrlRule(hitWebsiteCrawler.getHostUrlExp(),
				hitWebsiteCrawler.getNavigationUrlExp(), 
				hitWebsiteCrawler.getContentUrlExp());
		webinfo.setUrlRule(urlRule);
		
		if ( request.getLangugeType() == OpenCommonLanguge.OC_LAN_CHINESE_ZH_CN ) {
			webinfo.setWebsiteType(WMSWebsiteType.WMS_WEBSITE_ZH_CN);
		} 

		webinfo.setSeeds(hitWebsiteCrawler.getSeedUrls());
		
		WMSCreateCrawlerWebsiteRequest wmsRequest = new WMSCreateCrawlerWebsiteRequest();
		wmsRequest.setLogid(logid);
		wmsRequest.setWebinfo(webinfo);
		
		try {
			WMSCreateCrawlerWebsiteResponse wmsResponse = wmservice.createCrawlerWebsite(wmsRequest);
		} catch (LightningServiceException e) {
			throw new FightingServiceException(logid, e.getErrorCode(), e.getErrorMessage());
		}
		
		AddHitWebsiteCrawlerResponse response = new AddHitWebsiteCrawlerResponse();
		response.setLogid(logid);
		
		return response;
		
	}

	@Override
	public QueryHitWebsiteCrawlersResponse queryHitWebsiteCrawlers(
			QueryHitWebsiteCrawlersRequest request)
			throws FightingServiceException, TException {
		// TODO Auto-generated method stub
		
		long logid = LogidHelper.genLogid();
		int pageno = request.getPageno();
		int pagesize = request.getPagesize();
		
		QueryHitWebsiteCrawlersResponse response = new QueryHitWebsiteCrawlersResponse();
		response.setLogid(logid);
		response.setTotalNum(0);
		response.setWebsiteCrawlers(new ArrayList<HitWebisteCrawler>());
		
		WebMetaServerService.Iface wmservice = askEngineController.getWebMetaServerService();
		
		WMSQueryCrawlerTaskRequest wmsRequest = new WMSQueryCrawlerTaskRequest();
		wmsRequest.setLogid(logid);
		wmsRequest.setPageno(pageno);
		wmsRequest.setPagesize(pagesize);
		
		try {
			WMSQueryCrawlerTaskResponse wmsResponse = wmservice
					.queryCrawlerTask(wmsRequest);
			response.setTotalNum(wmsResponse.getTotalNum());
			List<WMSCrawlerWebsite> cws = wmsResponse.getTasks();
			for ( WMSCrawlerWebsite cw : cws ) {
				if ( WebMetaServerConvertor.isValidate(cw) ) {
					HitWebisteCrawler hwc = WebMetaServerConvertor.convert(cw);
					response.getWebsiteCrawlers().add(hwc);
				}
			}
		} catch (LightningServiceException e) {
			throw new FightingServiceException(logid, e.getErrorCode(), e.getErrorMessage());
		}
		
		return response;
		
	}

	@Override
	public RemoveHitWebsiteCrawlerResponse removeHitWebsiteCrawler(
			RemoveHitWebsiteCrawlerRequest request)
			throws FightingServiceException, TException {
		// TODO Auto-generated method stub
		
		long logid = LogidHelper.genLogid();
		OpenCommonLanguge languageType = request.getLangugeType();
		int webid = request.getWebid();
		
		RemoveHitWebsiteCrawlerResponse response = new RemoveHitWebsiteCrawlerResponse();
		response.setLogid(logid);
		
		WebMetaServerService.Iface wmservice = askEngineController.getWebMetaServerService();
		
		WMSRemoveCrawlerTaskRequest wmsRequest = new WMSRemoveCrawlerTaskRequest();
		wmsRequest.setLogid(logid);
		wmsRequest.setWebid(webid);
		wmsRequest.setWebsiteType(LanguageHelper.convert2WebsitType(languageType));
		
		try {
			wmservice.removeCrawlerTask(wmsRequest);
		} catch (LightningServiceException e) {
			throw new FightingServiceException(logid, e.getErrorCode(), e.getErrorMessage());
		}
		
		return response;
		
	}

	@Override
	public UpdateHitWebsiteCrawlerResponse updateHitWebsiteCrawler(
			UpdateHitWebsiteCrawlerRequest request)
			throws FightingServiceException, TException {

		long logid = LogidHelper.genLogid();
		HitWebisteCrawler hwc = request.getWebsiteCrawler();
		
		UpdateHitWebsiteCrawlerResponse response = new UpdateHitWebsiteCrawlerResponse();
		response.setLogid(logid);
		
		WebMetaServerService.Iface wmservice = askEngineController.getWebMetaServerService();
		try {
			WMSUpdateCrawlerTaskRequest wmsRequest = new WMSUpdateCrawlerTaskRequest();
			wmsRequest.setLogid(logid);
			wmsRequest.setWebinfo(WebMetaServerConvertor.convert(hwc));
			wmservice.updateCrawlerTask(wmsRequest);
		} catch (LightningServiceException e) {
			throw new FightingServiceException(logid, e.getErrorCode(), e.getErrorMessage());
		}
		
		return response;
	
	}

	@Override
	public TranslateResponse translate(TranslateRequest request)
			throws FightingServiceException, TException {

		long logid = LogidHelper.genLogid();
		
		TranslateResponse response = new TranslateResponse();
		response.setLogid(logid);
		response.setContent("");
		
		String from = request.getFrom();
		String to = request.getTo();
		String text = request.getText();
		
		TranslateService service = askEngineController.getTranslateService();
		KVResult<String> kvresult = service.translate(from, to, text);
		if ( kvresult.isSuccess() == true ) {
			response.setContent(kvresult.getValue());
		} else {
			throw new FightingServiceException(logid, 1001, 
					kvresult.getErrMsg() == null ? "" : kvresult.getErrMsg());
		}
		return response;
		
	}
	

}
