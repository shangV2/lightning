//package test.server;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//import java.util.Set;
//import java.util.TreeMap;
//import java.util.TreeSet;
//import java.util.concurrent.ConcurrentHashMap;
//
//import org.apache.thrift.TException;
//import org.apache.thrift.server.TServer;
//import org.apache.thrift.server.TThreadPoolServer;
//import org.apache.thrift.transport.TServerSocket;
//import org.apache.thrift.transport.TServerTransport;
//import org.apache.thrift.transport.TTransportException;
//
//import com.lighting.rpc.core.client.DynamicClientProxy;
//import com.lighting.rpc.webindexer.model.WIQueryArticleRequest;
//import com.lighting.rpc.webindexer.model.WIQueryArticleResponse;
//import com.lighting.rpc.webindexer.model.WIQueryRequest;
//import com.lighting.rpc.webindexer.model.WIQueryResponse;
//import com.lighting.rpc.webindexer.model.WIWebArticle;
//import com.lighting.rpc.webindexer.model.WIWebPage;
//import com.lighting.rpc.webindexer.service.WebIndexerService;
//import com.qing.index.manager.QingIndexManager;
//import com.qing.index.manager.SmsQingIndexManager;
//import com.qing.index.manager.SmsStoreManager;
//import com.qing.index.model.SmsFriendInfo;
//import com.qing.index.model.SmsInfo;
//import com.qing.index.model.dataobject.SmsInfoDO;
//import com.qing.index.model.dataobject.SmsInfoResultDO;
//import com.qing.index.model.dataobject.WebPageDO;
//import com.qing.index.model.dataobject.WebPageResultDO;
//import com.qing.logiclayer.Article;
//import com.qing.logiclayer.BriefReport;
//import com.qing.logiclayer.ContentType;
//import com.qing.logiclayer.FightingServiceException;
//import com.qing.logiclayer.ListAllTopicArticleResult;
//import com.qing.logiclayer.ListTopicArticleResult;
//import com.qing.logiclayer.ListTopicResult;
//import com.qing.logiclayer.ListTopicSmsResult;
//import com.qing.logiclayer.Logiclayer;
//import com.qing.logiclayer.PageInfo;
//import com.qing.logiclayer.PublicWord;
//import com.qing.logiclayer.QueryShortMessageTraceResult;
//import com.qing.logiclayer.QueryTracePublicWordRequest;
//import com.qing.logiclayer.QueryTracePublicWordResult;
//import com.qing.logiclayer.QueryWebPageTraceResult;
//import com.qing.logiclayer.SearchSummary;
//import com.qing.logiclayer.SearchSummaryResult;
//import com.qing.logiclayer.ShortMessageTraceContent;
//import com.qing.logiclayer.ShortMessageTraceSummary;
//import com.qing.logiclayer.SmsArticle;
//import com.qing.logiclayer.SmsFriend;
//import com.qing.logiclayer.SmsSummary;
//import com.qing.logiclayer.TimeTrendSet;
//import com.qing.logiclayer.Topic;
//import com.qing.logiclayer.TraceId;
//import com.qing.logiclayer.WebPageTraceContent;
//import com.qing.logiclayer.WebPageTraceSummary;
//import com.qing.logiclayer.WordInfo;
//import com.qing.logiclayer.WordSplitTimeTrend;
//import com.qing.logiclayer.WordTimeTrend;
//import com.qing.logiclayer.manager.PersistentManager;
//import com.qing.logiclayer.manager.TopicManager;
//import com.qing.logiclayer.model.MTopic;
//import com.qing.logiclayer.model.WordFreq;
//import com.qing.logiclayer.utils.TimeUtils;
//import com.qing.logiclayer.utils.WordSplitUtils;
//
//
//public class BackupServerTest {
//
//	
//	public static class LogiclayerHandler implements Logiclayer.Iface {
//
//		// *) 热点词
//		private Map<String, List<Integer> > hotWordMap = new TreeMap<String, List<Integer>>();
//		
//		// *) 预警简报
//		private List<BriefReport> briefReports = new ArrayList<BriefReport>();
//		
//		private QingIndexManager qingIndexManager = new QingIndexManager();
//		
//		// *) 
//		private SmsQingIndexManager smsQingIndexManager = new SmsQingIndexManager();
//		
//		// *)
//		private SmsStoreManager smsStoreManager = new SmsStoreManager();
//		
//		// *) 用于敏感词的存储
//		private PersistentManager persistentManager = new PersistentManager();
//		
//		// *) 用于话题的定制
//		private TopicManager topicManager = new TopicManager();
//		
//		// ----------------------------------------------------------------------
//		// 敏感词
//		private List<String> sensitiveWords = new ArrayList<String>();
//		private Map<String, List<Integer> > sensitiveWordFreqMap = new ConcurrentHashMap<String, List<Integer>>(new TreeMap<String, List<Integer> >());
//		
//		public void init() {
//			
//			// 1. 对数据进行初始化
//			
//			// 对热点词的数据，进行初始化
//			{
//				Random random = new Random();
//				List<Integer> list1 = new ArrayList<Integer>();
//				for ( int i = 0; i < 7; i++ ) {
//					list1.add(random.nextInt(100) + 10);
//				}
//				hotWordMap.put("黑社会", list1);
//				
//				List<Integer> list2 = new ArrayList<Integer>();
//				for ( int i = 0; i < 7; i++ ) {
//					list2.add(random.nextInt(120) + 10);
//				}
//				hotWordMap.put("流氓", list2);
//
//				List<Integer> list3 = new ArrayList<Integer>();
//				for ( int i = 0; i < 7; i++ ) {
//					list3.add(random.nextInt(110) + 2);
//				}	
//				hotWordMap.put("解放", list3);
//				
//				List<Integer> list4 = new ArrayList<Integer>();
//				for ( int i = 0; i < 7; i++ ) {
//					list4.add(random.nextInt(105) + 3);
//				}
//				hotWordMap.put("反动", list4);
//				
//				List<Integer> list5 = new ArrayList<Integer>();
//				for ( int i = 0; i < 7; i++ ) {
//					list5.add(random.nextInt(200) + 3);
//				}
//				hotWordMap.put("谋杀", list5);
//			}
//			
//			{
//				
//			}
//			
//			// 对预警简报的数据，进行初始化
//			{
//				BriefReport report1 = new BriefReport();
//				report1.setUrl("http://bbs.tiexue.net/post2_6931505_1.html");
//				report1.setContent("中国网9月14日讯 据韩国《朝鲜日报》网站9月13日报道，韩国《东亚日报》引用朝鲜一位消息灵通人士称，金正恩夫人李雪主的父亲为空军飞行员出身，其母亲为一中学老师。《东亚日报》称，在朝鲜，飞行员身份调查严格，需要调查亲属关系至堂兄弟一层。飞行员虽属上流阶层，但很难触碰实权的核心。且出于对飞行员投韩可能性的考虑，即使其家族成员有人犯罪，一般也只会轻罚。");
//				report1.setTitle("韩媒：李雪主背景揭秘 其父亲为空军飞行员");
//				briefReports.add(report1);
//				
//				BriefReport report2 = new BriefReport();
//				report2.setUrl("http://bbs.tiexue.net/post_6931552_1.html");
//				report2.setContent("印度东北各邦的民族，包括藏南地的阿鲁纳恰尔邦，由于相貌和中国人很相似，在印度其他地区经常面对各种各样种族歧视，尤其是东北印度的女孩子，肤色较浅，而且和其他印度人非常不一样，让她们成为了强奸犯注意的目标，东北邦的人一直想从印度独立出来，由于人种和中国接近，很多人想干脆加入中国算了，过去这些年，印度没有给这些地区投入任何资金，这些地区经济发展严重落后。下面是很多东北邦德人的讨论，很多人想离开印度转投中国。");
//				report2.setTitle("印度东北邦要求并入中国 引三哥网民热议");
//				briefReports.add(report2);
//				
//				BriefReport report3 = new BriefReport();
//				report3.setUrl("http://news.qq.com/a/20130915/000296.htm");
//				report3.setContent("海航HU8005航班已于北京时间10:05从北京首都机场起飞，预计于当地时间13:00抵达济州国际机场。HU8001航班预计于当地时间13:20抵达。“海娜号”邮轮目前已有450名客人下船，到达济州国际机场等待。");
//				report3.setTitle("中国邮轮被扣韩国 海航包机已启程接千余游客");
//				briefReports.add(report3);
//				
//				BriefReport report4 = new BriefReport();
//				report4.setUrl("http://news.qq.com/a/20130915/004480.htm");
//				report4.setContent("铁道大佬们与情妇的“传奇”还在法庭续写，继张曙光受审曝出为追情人大把花钱之后，原昆明铁路局局长闻清良也因供养情妇收受巨额贿赂被提起公诉。闻清良被控安排铁路车皮帮助相关企业进行煤炭发运，为此伙同情妇收受多名山西煤老板的2000余万元贿赂，受贿财物中既包括住房，也有价值20万元的消费卡。北京市第二中级法院近日受理了此案。");
//				report4.setTitle("原昆明铁路局局长被控伙同情妇受贿2000万");
//				briefReports.add(report4);
//			}
//			// 
//			
////			qingIndexManager.init();
////			qingIndexManager.prepare();
//			
//			smsQingIndexManager.init();
//			smsQingIndexManager.prepare();
//			
//			smsStoreManager.init();
//			
//			{
//				// 敏感词初始化
//				String[] words = new String[] {
//						"城管", "民主", "政治",
//				};
//				Random random = new Random();
//				for ( String word : words ) {
//					sensitiveWords.add(word);
//					List<Integer> freqs = new ArrayList<Integer>();
//					for ( int i = 0; i < 7; i++ ) {
//						freqs.add(random.nextInt(100) + 10);
//					}
//					sensitiveWordFreqMap.put(word, freqs);
//				}
//			}
//			
//			persistentManager.init();
//			
//			topicManager.init();
//			
//		}
//		
//		@Override
//		public List<WordInfo> queryHotWordList() throws TException {
//			List<WordInfo> wordInfos = new ArrayList<WordInfo>();
//			for ( String key : hotWordMap.keySet() ) {
//				List<Integer> list = hotWordMap.get(key);
//				wordInfos.add(new WordInfo(key, list.get(0)));
//			}
//			return wordInfos;
//		}
//		
//		@Override
//		public List<PageInfo> queryPageByWord(String word) throws TException {
//			// TODO Auto-generated method stub
//			List<PageInfo> pageInfos = new ArrayList<PageInfo>();
//			pageInfos.add(new PageInfo("mongodb", "http://www.baidu.com", new Date().toString()));
//			pageInfos.add(new PageInfo("google", "http://www.google.com", new Date().toString()));
//			return pageInfos;
//		}
//		
//		@Override
//		public int queryDayFreqByWord(String word) throws TException {
//			// TODO Auto-generated method stub
//			List<Integer> lists = hotWordMap.get(word);
//			if ( lists == null || lists.size() == 0 ) {
//				return 0;
//			}
//			return lists.get(0);
//		}
//		
//		@Override
//		public List<WordTimeTrend> queryWordTrend(String word, int numdate)
//				throws TException {
//			
//			List<WordTimeTrend> trends = new ArrayList<WordTimeTrend>();
//			
//			Date date = new Date();
//			int year = date.getYear() + 1900;
//			int month = date.getMonth() + 1;
//			int day = date.getDate();
//
//			List<Integer> freqs = hotWordMap.get(word);
//			for ( int i = 0; i < numdate; i++ ) {
//				WordTimeTrend trend = new WordTimeTrend();
//				if ( freqs != null && i < freqs.size() ) {
//					trend.setTotal(freqs.get(i));
//				} else {
//					trend.setTotal(0);
//				}
//				String repdate = TimeUtils.beforeDate(year, month, day, i);
//				trend.setDate(repdate);
//				
//				trends.add(trend);
//			}
//			
//			return trends;
//		}
//		
//		@Override
//		public List<WordTimeTrend> queryWordTrend4time(String word,
//				String sstartdate, String senddate) throws TException {
//			
//			int startdate = 0;
//			int enddate = 0;
//			
//			try {
//				startdate = Integer.parseInt(sstartdate);
//				enddate = Integer.parseInt(senddate);
//			} catch (Exception e) {
//				return new ArrayList<WordTimeTrend>();
//			}
//			// TODO Auto-generated method stub
//			List<WordTimeTrend> trends = new ArrayList<WordTimeTrend>();
//	
//			if ( startdate > enddate ) {
//				return trends;
//			}
//			
//			if ( startdate < 19000000 || startdate > 21000000 ) {
//				return trends;
//			} else if ( enddate < 19000000 || enddate > 21000000 ) {
//				return trends;
//			}
//			
//			int year = startdate / 10000;
//			int month = (startdate / 100) % 100;
//			int day = startdate % 100;
//			if ( !TimeUtils.checkValidateDate(year, month, day) ) {
//				return trends;
//			}
//			
//			year = enddate / 10000;
//			month = (enddate / 100) % 100;
//			day = enddate % 100;
//			if ( !TimeUtils.checkValidateDate(year, month, day) ) {
//				return trends;
//			}
//			
//			int numdate = TimeUtils.diffBetweenDays(startdate, enddate) + 1;
//			List<Integer> freqs = hotWordMap.get(word);
//			for ( int i = 0; i < numdate; i++ ) {
//				WordTimeTrend trend = new WordTimeTrend();
//				if ( freqs != null && i < freqs.size() ) {
//					trend.setTotal(freqs.get(i));
//				} else {
//					trend.setTotal(0);
//				}
//				String repdate = TimeUtils.beforeDate(year, month, day, i);
//				trend.setDate(repdate);
//				
//				trends.add(trend);
//			}
//			
//			return trends;
//		}
//		
//		@Override
//		public List<WordSplitTimeTrend> queryWordSplitTrend(String word,
//				int numdate) throws TException {
//			
//			List<WordSplitTimeTrend> trends = new ArrayList<WordSplitTimeTrend>();
//			
//			Date date = new Date();
//			int year = date.getYear() + 1900;
//			int month = date.getMonth() + 1;
//			int day = date.getDate();
//
//			List<Integer> freqs = hotWordMap.get(word);
//			for ( int i = 0; i < numdate; i++ ) {
//				WordSplitTimeTrend trend = new WordSplitTimeTrend();
//				if ( freqs != null && i < freqs.size() ) {
//					trend.setPagefreq(freqs.get(i) / 3);
//					trend.setSmsfreq(freqs.get(i) / 3 * 2);
//				} else {
//					trend.setPagefreq(0);
//					trend.setSmsfreq(0);
//				}
//				String repdate = TimeUtils.beforeDate(year, month, day, i);
//				trend.setDate(repdate);
//				
//				trends.add(trend);
//			}
//			
//			return trends;
//			
//		}
//		
//		@Override
//		public List<WordSplitTimeTrend> queryWordSplitTrend4time(String word,
//				String sstartdate, String senddate) throws TException {
//			
//			int startdate = 0;
//			int enddate = 0;
//			
//			try {
//				startdate = Integer.parseInt(sstartdate);
//				enddate = Integer.parseInt(senddate);
//			} catch (Exception e) {
//				return new ArrayList<WordSplitTimeTrend>();
//			}
//			
//			List<WordSplitTimeTrend> trends = new ArrayList<WordSplitTimeTrend>();
//			
//			if ( startdate > enddate ) {
//				return trends;
//			}
//			
//			if ( startdate < 19000000 || startdate > 21000000 ) {
//				return trends;
//			} else if ( enddate < 19000000 || enddate > 21000000 ) {
//				return trends;
//			}
//			
//			int year = startdate / 10000;
//			int month = (startdate / 100) % 100;
//			int day = startdate % 100;
//			if ( !TimeUtils.checkValidateDate(year, month, day) ) {
//				return trends;
//			}
//			
//			year = enddate / 10000;
//			month = (enddate / 100) % 100;
//			day = enddate % 100;
//			if ( !TimeUtils.checkValidateDate(year, month, day) ) {
//				return trends;
//			}
//			
//			int numdate = TimeUtils.diffBetweenDays(startdate, enddate) + 1;
//
//			List<Integer> freqs = hotWordMap.get(word);
//			for ( int i = 0; i < numdate; i++ ) {
//				WordSplitTimeTrend trend = new WordSplitTimeTrend();
//				if ( freqs != null && i < freqs.size() ) {
//					trend.setPagefreq(freqs.get(i) / 3);
//					trend.setSmsfreq(freqs.get(i) / 3 * 2);
//				} else {
//					trend.setPagefreq(0);
//					trend.setSmsfreq(0);
//				}
//				String repdate = TimeUtils.beforeDate(year, month, day, i);
//				trend.setDate(repdate);
//				
//				trends.add(trend);
//			}
//			
//			return trends;
//			
//		}
//		
//		
//		@Override
//		public SearchSummaryResult querySearchSummary(String query, int pageno,
//				int limit) throws TException {
//			
//			List<SearchSummary> summaries = new ArrayList<SearchSummary>();
//			SearchSummaryResult result = new SearchSummaryResult();
//			result.setTotal(0);
//			result.setSummaries(summaries);
//			
//			WebPageResultDO resultDO = qingIndexManager.query(query, pageno, limit);
//			result.setTotal(resultDO.getTotalNumber());
//			for ( WebPageDO webPage : resultDO.getWebPages() ) {
//				SearchSummary summary = new SearchSummary();
//				summary.setTitle(webPage.getTitle());
//				summary.setUrl(webPage.getUrl());
//				summary.setContent(webPage.getSummary());
//				summaries.add(summary);
//			}
//			
//			return result;
//			
//		}
//		
//		@Override
//		public List<BriefReport> queryBriefReport(int pageno, int limit)
//				throws TException {
//			
//			List<BriefReport> results = new ArrayList<BriefReport>();
//			for ( int i = pageno * limit; i < pageno * limit + limit && i < briefReports.size(); i++ ) {
//				BriefReport report = briefReports.get(i);
//				results.add(report);
//			}
//			return results;
//			
//		}
//
//		@Override
//		public List<TimeTrendSet> queryBatchWordTimeTrend(int numdate)
//				throws TException {
//			
//			List<TimeTrendSet> trends = new ArrayList<TimeTrendSet>();
//			
//			Date date = new Date();
//			int year = date.getYear() + 1900;
//			int month = date.getMonth() + 1;
//			int day = date.getDate();
//
//			for ( int i = 0; i < numdate; i++ ) {
//				TimeTrendSet set = new TimeTrendSet();
//				set.setDate(TimeUtils.beforeDate(year, month, day, i));
//				for ( String key : hotWordMap.keySet() ) {
//					List<Integer> freqs = hotWordMap.get(key);
//					WordInfo wi = new WordInfo();
//					wi.setWord(key);
//					if ( freqs != null && i < freqs.size() ) {
//						wi.setFreq(freqs.get(i));
//					} else {
//						wi.setFreq(0);
//					}
//					set.addToWordinfos(wi);
//				}
//				trends.add(set);
//			}
//			return trends;
//			
//		}
//		
//
//		@Override
//		public List<TimeTrendSet> queryBatchWordTimeTrend4time(String sstartdate,
//				String senddate) throws TException {
//			
//			int startdate = 0;
//			int enddate = 0;
//			
//			try {
//				startdate = Integer.parseInt(sstartdate);
//				enddate = Integer.parseInt(senddate);
//			} catch (Exception e) {
//				return new ArrayList<TimeTrendSet>();
//			}
//			
//			List<TimeTrendSet> trends = new ArrayList<TimeTrendSet>();
//			
//			if ( startdate > enddate ) {
//				return trends;
//			}
//			
//			if ( startdate < 19000000 || startdate > 21000000 ) {
//				return trends;
//			} else if ( enddate < 19000000 || enddate > 21000000 ) {
//				return trends;
//			}
//			
//			int year = startdate / 10000;
//			int month = (startdate / 100) % 100;
//			int day = startdate % 100;
//			if ( !TimeUtils.checkValidateDate(year, month, day) ) {
//				return trends;
//			}
//			
//			year = enddate / 10000;
//			month = (enddate / 100) % 100;
//			day = enddate % 100;
//			if ( !TimeUtils.checkValidateDate(year, month, day) ) {
//				return trends;
//			}
//			
//			int numdate = TimeUtils.diffBetweenDays(startdate, enddate) + 1;
//			
//			for ( int i = 0; i < numdate; i++ ) {
//				TimeTrendSet set = new TimeTrendSet();
//				set.setDate(TimeUtils.beforeDate(year, month, day, i));
//				for ( String key : hotWordMap.keySet() ) {
//					List<Integer> freqs = hotWordMap.get(key);
//					WordInfo wi = new WordInfo();
//					wi.setWord(key);
//					if ( freqs != null && i < freqs.size() ) {
//						wi.setFreq(freqs.get(i));
//					} else {
//						wi.setFreq(0);
//					}
//					set.addToWordinfos(wi);
//				}
//				trends.add(set);
//			}
//			return trends;
//		}
//
//		@Override
//		public List<SmsSummary> querySearchSmsSummary(String queryItem, int pageno,
//				int limit) throws TException {
//			
//			List<SmsSummary> summaries = new ArrayList<SmsSummary>();
//			
//			SmsInfoResultDO resultDO = smsQingIndexManager.query(queryItem, pageno, limit);
//			
//			List<SmsInfoDO> smsInfoDOs = resultDO.getSmses();
//			for (int i = 0; i < smsInfoDOs.size(); i++) {
//				SmsSummary summary = new SmsSummary();
//				SmsInfoDO sms = smsInfoDOs.get(i);
//				summary.setContent(sms.getContent());
//				summary.setSendMobile(sms.getSendMobile());
//				summary.setRecvMobile(sms.getRecvMobile());
//				summary.setTimestamp(sms.getTimestamp());
//				summary.setSendUsername(sms.getSendUsername());
//				summary.setRecvUsername(sms.getRecvUsername());
//				summaries.add(summary);
//			}
//			return summaries;
//
//		}
//
//		@Override
//		public List<SmsSummary> querySmsSummaryByPhone(String phoneno,
//				int pageno, int limit) throws TException {
//			
//			List<SmsSummary> results = new ArrayList<SmsSummary>();
//			List<SmsInfo> smsinfos = smsStoreManager.querySmsByContact(phoneno, pageno, limit);
//			for ( SmsInfo sms : smsinfos ) {
//				SmsSummary summary = new SmsSummary();
//				summary.setContent(sms.getContent());
//				summary.setSendMobile(sms.getSendMobile());
//				summary.setSendUsername(sms.getSendUsername());
//				summary.setRecvMobile(sms.getRecvMobile());
//				summary.setRecvUsername(sms.getRecvUsername());
//				summary.setTimestamp(sms.getTimestamp());
//				results.add(summary);
//			}
//			return results;
//			
//		}
//
//		@Override
//		public List<SmsFriend> querySmsFriendByPhone(String phoneno)
//				throws TException {
//			List<SmsFriend> friends = new ArrayList<SmsFriend>();
//			List<SmsFriendInfo> infos = smsStoreManager.querySmsFriendsByContact(phoneno);
//			for ( SmsFriendInfo finfo : infos ) {
//				SmsFriend friend = new SmsFriend();
//				friend.setPhoneno(finfo.getPhoneno());
//				friend.setUsername(finfo.getUsername());
//				friend.setContactNum(finfo.getContactNum());
//				friends.add(friend);
//			}
//			return friends;
//		}
//
//		@Override
//		public List<WordInfo> querySensitiveWordList() throws TException {
//			// TODO Auto-generated method stub
//			List<WordInfo> results = new ArrayList<WordInfo>();
//			List<String> words = persistentManager.getWords();
//			for ( String word : words ) {
//				WordInfo wi = new WordInfo();
//				wi.setWord(word);
//				wi.setFreq(0);
//				results.add(wi);
//			}
//			return results;
//		}
//
//		@Override
//		public List<WordTimeTrend> querySensitiveWordTrend(String word,
//				int numdate) throws TException {
//			
//			List<WordTimeTrend> trends = new ArrayList<WordTimeTrend>();
//			
//			Date date = new Date();
//			int year = date.getYear() + 1900;
//			int month = date.getMonth() + 1;
//			int day = date.getDate();
//
//			List<Integer> freqs = sensitiveWordFreqMap.get(word);
//			for ( int i = 0; i < numdate; i++ ) {
//				WordTimeTrend trend = new WordTimeTrend();
//				if ( freqs != null && i < freqs.size() ) {
//					trend.setTotal(freqs.get(i));
//				} else {
//					trend.setTotal(0);
//				}
//				String repdate = TimeUtils.beforeDate(year, month, day, i);
//				trend.setDate(repdate);
//				
//				trends.add(trend);
//			}
//			
//			return trends;
//			
//		}
//		
//		@Override
//		public List<WordTimeTrend> querySensitiveWordTrend4time(String word,
//				String sstartdate, String senddate) throws TException {
//			// TODO Auto-generated method stub
//			
//			int startdate = 0;
//			int enddate = 0;
//			try {
//				startdate = Integer.parseInt(sstartdate);
//				enddate = Integer.parseInt(senddate);
//			} catch (Exception e) {
//				return new ArrayList<WordTimeTrend>();
//			}
//			
//			List<WordTimeTrend> trends = new ArrayList<WordTimeTrend>();
//			
//			if ( startdate > enddate ) {
//				return trends;
//			}
//			
//			if ( startdate < 19000000 || startdate > 21000000 ) {
//				return trends;
//			} else if ( enddate < 19000000 || enddate > 21000000 ) {
//				return trends;
//			}
//			
//			int year = startdate / 10000;
//			int month = (startdate / 100) % 100;
//			int day = startdate % 100;
//			if ( !TimeUtils.checkValidateDate(year, month, day) ) {
//				return trends;
//			}
//			
//			year = enddate / 10000;
//			month = (enddate / 100) % 100;
//			day = enddate % 100;
//			if ( !TimeUtils.checkValidateDate(year, month, day) ) {
//				return trends;
//			}
//			
//			int numdate = TimeUtils.diffBetweenDays(startdate, enddate) + 1;
//			
//			List<Integer> freqs = sensitiveWordFreqMap.get(word);
//			for ( int i = 0; i < numdate; i++ ) {
//				WordTimeTrend trend = new WordTimeTrend();
//				if ( freqs != null && i < freqs.size() ) {
//					trend.setTotal(freqs.get(i));
//				} else {
//					trend.setTotal(0);
//				}
//				String repdate = TimeUtils.beforeDate(year, month, day, i);
//				trend.setDate(repdate);
//				
//				trends.add(trend);
//			}
//			
//			return trends;
//			
//		}
//
//		@Override
//		public List<WordSplitTimeTrend> querySensitiveWordSplitTrend(
//				String word, int numdate) throws TException {
////			return null;
//			List<WordSplitTimeTrend> trends = new ArrayList<WordSplitTimeTrend>();
//			
//			Date date = new Date();
//			int year = date.getYear() + 1900;
//			int month = date.getMonth() + 1;
//			int day = date.getDate();
//
//			List<Integer> freqs = sensitiveWordFreqMap.get(word);
//			for ( int i = 0; i < numdate; i++ ) {
//				WordSplitTimeTrend trend = new WordSplitTimeTrend();
//				if ( freqs != null && i < freqs.size() ) {
//					trend.setPagefreq(freqs.get(i) / 3);
//					trend.setSmsfreq(freqs.get(i) / 3 * 2);
//				} else {
//					trend.setPagefreq(0);
//					trend.setSmsfreq(0);
//				}
//				String repdate = TimeUtils.beforeDate(year, month, day, i);
//				trend.setDate(repdate);
//				
//				trends.add(trend);
//			}
//			
//			return trends;
//			
//		}
//		
//		@Override
//		public List<WordSplitTimeTrend> querySensitiveWordSplitTrend4time(
//				String word, String sstartdate, String senddate) throws TException {
//			
//			int startdate = 0;
//			int enddate = 0;
//			try {
//				startdate = Integer.parseInt(sstartdate);
//				enddate = Integer.parseInt(senddate);
//			} catch (Exception e) {
//				return new ArrayList<WordSplitTimeTrend>();
//			}
//			
//			List<WordSplitTimeTrend> trends = new ArrayList<WordSplitTimeTrend>();
//			
//			if ( startdate > enddate ) {
//				return trends;
//			}
//			
//			if ( startdate < 19000000 || startdate > 21000000 ) {
//				return trends;
//			} else if ( enddate < 19000000 || enddate > 21000000 ) {
//				return trends;
//			}
//			
//			int year = startdate / 10000;
//			int month = (startdate / 100) % 100;
//			int day = startdate % 100;
//			if ( !TimeUtils.checkValidateDate(year, month, day) ) {
//				return trends;
//			}
//			
//			year = enddate / 10000;
//			month = (enddate / 100) % 100;
//			day = enddate % 100;
//			if ( !TimeUtils.checkValidateDate(year, month, day) ) {
//				return trends;
//			}
//			
//			int numdate = TimeUtils.diffBetweenDays(startdate, enddate) + 1;
//			List<Integer> freqs = sensitiveWordFreqMap.get(word);
//			for ( int i = 0; i < numdate; i++ ) {
//				WordSplitTimeTrend trend = new WordSplitTimeTrend();
//				if ( freqs != null && i < freqs.size() ) {
//					trend.setPagefreq(freqs.get(i) / 3);
//					trend.setSmsfreq(freqs.get(i) / 3 * 2);
//				} else {
//					trend.setPagefreq(0);
//					trend.setSmsfreq(0);
//				}
//				String repdate = TimeUtils.beforeDate(year, month, day, i);
//				trend.setDate(repdate);
//				trends.add(trend);
//			}
//			return trends;
//			
//		}
//
//		@Override
//		public SearchSummaryResult queryPageByHotWord(String query, int pageno,
//				int limit) throws TException {
//			
//			List<SearchSummary> summaries = new ArrayList<SearchSummary>();
//			SearchSummaryResult result = new SearchSummaryResult();
//			result.setTotal(0);
//			result.setSummaries(summaries);
//			
//			WebPageResultDO resultDO = qingIndexManager.query(query, pageno, limit);
//			result.setTotal(resultDO.getTotalNumber());
//			for ( WebPageDO webPage : resultDO.getWebPages() ) {
//				SearchSummary summary = new SearchSummary();
//				summary.setTitle(webPage.getTitle());
//				summary.setUrl(webPage.getUrl());
//				summary.setContent(webPage.getSummary());
//				summaries.add(summary);
//			}
//			return result;
//			
//		}
//
//		@Override
//		public int addSensitiveWord(String word) throws TException {
//			// TODO Auto-generated method stub
//			return persistentManager.addWord(word);
//		}
//
//		@Override
//		public int removeSensitiveWord(String word) throws TException {
//			// TODO Auto-generated method stub
//			return persistentManager.removeWord(word);
//		}
//
//		@Override
//		public int modifySensitiveWord(String oword, String nword)
//				throws TException {
//			// TODO Auto-generated method stub
//			return persistentManager.modifyWord(oword, nword);
//		}
//
//		@Override
//		public int createTopic(String topic, List<String> words, int percents,
//				String startdate, String enddate) throws TException {
//			// TODO Auto-generated method stub
//			
//			// *) 做参数校验
//			int startDateValue = 0;
//			int endDateValue = 0;
//			try {
//				startDateValue = TimeUtils.convertToIntForYYYYMMDD(startdate);
//				endDateValue = TimeUtils.convertToIntForYYYYMMDD(enddate);
//				if ( startDateValue >= endDateValue ) {
//					return -1;
//				}
//			} catch (Throwable e) {
//				return -1;
//			}
//			
//			// *) 具体的逻辑操作
//			MTopic mtopic = new MTopic();
//			mtopic.setTopic(topic);
//			mtopic.setPercent(percents);
//			mtopic.setStartDate(startdate);
//			mtopic.setEndDate(enddate);
//			
//			TreeSet<String> twords = new TreeSet<String>();
//			for ( String w : words ) {
//				twords.add(w);
//			}
//			mtopic.setWords(twords);
//			topicManager.addTopic(topic, mtopic);
//			return 0;
//			
//		}
//
//		@Override
//		public int removeTopic(String topic) throws TException {
//			topicManager.removeTopic(topic);
//			return 0;
//		}
//
//		@Override
//		public ListTopicResult listTopic(int pageno, int pagesize)
//				throws TException {
//			ListTopicResult result = new ListTopicResult();
//			int totalNum = topicManager.countTopics();
//			result.setTopicTotalNum(totalNum);
//			List<MTopic> topices = topicManager.listTopics(pageno, pagesize);
//			List<Topic> topics2 = new ArrayList<Topic>();
//			for ( MTopic t : topices ) {
//				Topic t2 = new Topic();
//				t2.setTopicname(t.getTopic());
//				t2.setPercents(t.getPercent());
//				t2.setStartdate(t.getStartDate());
//				t2.setEnddate(t.getEndDate());
//				List<String> words = new ArrayList<String>();
//				Set<String> setwords = t.getWords();
//				for ( String sw : setwords ) {
//					words.add(sw);
//				}
//				t2.setWords(words);
//				topics2.add(t2);
//			}
//			result.setTopics(topics2);
//			return result;
//		}
//
//		@Override
//		public ListTopicArticleResult listTopicArticles(String topic,
//				int pageno, int pagesize) throws TException {
//			// TODO Auto-generated method stub
//			ListTopicArticleResult result = new ListTopicArticleResult();
//			result.setArticleTotalNum(1);
//			List<Article> articles = result.getArticles();
//			Article article = new Article();
//			article.setContent("先随意填一下，占个坑");
//			article.setTitle("标题");
//			article.setUrl("www.baidu.com");
//			article.setDate(new Date().toString());
//			articles.add(article);
//			return result;
//		}
//
//		@Override
//		public List<String> listUnusualWordList() throws TException {
//			// TODO Auto-generated method stub
//			List<String> results = new ArrayList<String>();
//			results.add("芝麻开门");
//			results.add("天王盖地虎");
//			results.add("宝塔镇河妖");
//			return results;
//		}
//
//		@Override
//		public ListAllTopicArticleResult listAllTopicArticles(int pageno,
//				int pagesize) throws TException {
//			// TODO Auto-generated method stub
//			ListAllTopicArticleResult result = new ListAllTopicArticleResult();
//			result.setArticleTotalNum(1);
//			List<Article> articles = result.getArticles();
//			Article article = new Article();
//			article.setContent("先随意填一下，占个坑");
//			article.setTitle("标题");
//			article.setUrl("www.baidu.com");
//			article.setDate(new Date().toString());
//			articles.add(article);
//			return result;
//		}
//
//		@Override
//		public int modifyTopic(String topicName, List<String> words,
//				int percents, String startdate, String enddate)
//				throws TException {
//			// TODO Auto-generated method stub
//			return 0;
//		}
//
//		@Override
//		public ListTopicSmsResult listTopicSmses(String topicName, int pageno,
//				int pagesize) throws TException {
//			// TODO Auto-generated method stub
//			
//			ListTopicSmsResult result = new ListTopicSmsResult();
//			result.setSmsTotalNum(0);
//			
//			MTopic mtopic = topicManager.queryTopic(topicName);
//			if ( mtopic == null ) {
//				return result;
//			}
//			
//			StringBuilder sb = new StringBuilder();
//			Set<String> wordSet = mtopic.getWords();
//			for ( String word : wordSet ) {
//				sb.append(word).append(" ");
//			}
//			
//			SmsInfoResultDO smsInfoResultDO = smsQingIndexManager.query(sb.toString(), pageno, pagesize);
//			result.setSmsTotalNum(smsInfoResultDO.getTotalNum());
//			result.setArticles(new ArrayList<SmsArticle>());
//			
//			List<SmsInfoDO> smses = smsInfoResultDO.getSmses();
//			for ( SmsInfoDO sms : smses ) {
//				SmsArticle article = new SmsArticle();
//				article.setContent(sms.getContent());
//				article.setReceiveMobile(sms.getRecvMobile());
//				article.setSendMobile(sms.getSendMobile());
//				article.setTimestamp(sms.getTimestamp());
//				result.getArticles().add(article);
//			}
//			return result;
//			
//		}
//
//		@Override
//		public ListTopicSmsResult listTopicSmsesWithPhone(String topicName,
//				String phone, int pageno, int pagesize) throws TException {
//			ListTopicSmsResult result = new ListTopicSmsResult();
//			result.setSmsTotalNum(0);
//			
//			MTopic mtopic = topicManager.queryTopic(topicName);
//			if ( mtopic == null ) {
//				return result;
//			}
//
//			List<String> wordList = new ArrayList<String>();
//			Set<String> wordSet = mtopic.getWords();
//			for ( String word : wordSet ) {
//				wordList.add(word);
//			}
//			
//			SmsInfoResultDO smsInfoResultDO = smsQingIndexManager.query(phone, wordList, pageno, pagesize);
//			result.setSmsTotalNum(smsInfoResultDO.getTotalNum());
//			result.setArticles(new ArrayList<SmsArticle>());
//			
//			List<SmsInfoDO> smses = smsInfoResultDO.getSmses();
//			for ( SmsInfoDO sms : smses ) {
//				SmsArticle article = new SmsArticle();
//				article.setContent(sms.getContent());
//				article.setReceiveMobile(sms.getRecvMobile());
//				article.setSendMobile(sms.getSendMobile());
//				article.setTimestamp(sms.getTimestamp());
//				result.getArticles().add(article);
//			}
//			return result;
//			
//		}
//		
//		// ----------------------------------------------------------------------------------------------------------
//
//		@Override
//		public QueryWebPageTraceResult queryWebPageTrace(String query,
//				int pageno, int pagesize) throws FightingServiceException,
//				TException {
//
//			QueryWebPageTraceResult result = new QueryWebPageTraceResult();
//			WebPageResultDO webPageResultDO = qingIndexManager.query(query, pageno, pagesize);
//			result.setTotalNumber(webPageResultDO.getTotalNumber());
//			List<WebPageTraceSummary> summaries = new ArrayList<WebPageTraceSummary>();
//			for ( WebPageDO webPage : webPageResultDO.getWebPages() ) {
//				WebPageTraceSummary summary = new WebPageTraceSummary();
//				summary.setDocid(webPage.getDocid());
//				summary.setTitle(webPage.getTitle());
//				summary.setUrl(webPage.getUrl());
//				summary.setSummary(webPage.getSummary());
//				summaries.add(summary);
//			}
//			result.setSummaries(summaries);
//			return result;
//			
//			
//		}
//
//		@Override
//		public QueryShortMessageTraceResult queryShortMessageTrace(
//				String query, int pageno, int pagesize)
//				throws FightingServiceException, TException {
//			// TODO Auto-generated method stub
//			QueryShortMessageTraceResult result = new QueryShortMessageTraceResult();
//			SmsInfoResultDO resultDO = smsQingIndexManager.query(query, pageno, pagesize);
//			result.setTotalNumber(resultDO.getTotalNum());
//			List<ShortMessageTraceSummary> summaries = new ArrayList<ShortMessageTraceSummary>();
//			for ( SmsInfoDO smsinfo : resultDO.getSmses() ) {
//				ShortMessageTraceSummary summary = new ShortMessageTraceSummary();
//				summary.setDocid(smsinfo.getDocid());
//				summary.setMessage(smsinfo.getContent());
//				summary.setRecvPhone(smsinfo.getRecvMobile());
//				summary.setSendPhone(smsinfo.getSendMobile());
//				summaries.add(summary);
//			}
//			result.setSummaries(summaries);
//			return result;
//			
//		}
//
//		@Override
//		public QueryTracePublicWordResult queryTracePublicWord(
//				QueryTracePublicWordRequest request)
//				throws FightingServiceException, TException {
//			// TODO Auto-generated method stub
//
//			QueryTracePublicWordResult result = new QueryTracePublicWordResult();
//			List<String> contentList = new ArrayList<String>();
//			List<TraceId> traceIds = request.getTraceIds();
//			for ( TraceId traceId : traceIds ) {
//				if ( traceId.getContentType() == ContentType.WEB_PAGE ) {
//					StringBuilder sb = new StringBuilder();
//
//					WebPageDO webPageDO = qingIndexManager.queryWebPageByDocid((int)traceId.getDocid());
//					
//					if (webPageDO.getSummary() != null) {
//						sb.append(webPageDO.getSummary());
//					}
//					if (webPageDO.getTitle() != null) {
//						sb.append(webPageDO.getTitle());
//					}
//					String tx = sb.toString();
//					if (tx.trim().length() > 0) {
//						contentList.add(tx);
//					}
//					
//				} else if ( traceId.getContentType() == ContentType.SHORT_MESSAGE ) {
//					StringBuilder sb = new StringBuilder();
//					SmsInfoDO smsInfoDO = smsQingIndexManager.querySmsInfoByDocid(traceId.getDocid());
//					if ( smsInfoDO.getContent() != null ) {
//						sb.append(smsInfoDO.getContent());
//					}
//					String tx = sb.toString();
//					if ( tx.trim().length() > 0 ) {
//						contentList.add(tx);
//					}
//				}
//			}
//			
//			List<List<WordFreq> > objes = new ArrayList<List<WordFreq> >();
//			for ( String content : contentList ) {
//				List<WordFreq> wfl = WordSplitUtils.parseSplit(content);
//				objes.add(wfl);
//			}
//			List<WordFreq> commonWordFreqs = WordSplitUtils.extractCommonWord(objes);
//			List<PublicWord> publicWords = new ArrayList<PublicWord>();
//			for ( WordFreq wordFreq : commonWordFreqs ) {
//				PublicWord pword = new PublicWord();
//				pword.setFreq(wordFreq.getFreq());
//				pword.setWord(wordFreq.getWord());
////				result.getPublicWords().add(pword);
//				publicWords.add(pword);
//			}
//			result.setPublicWords(publicWords);
//			return result;
//			
//		}
//
//		@Override
//		public WebPageTraceContent queryWebPageTraceByDocid(long docid)
//				throws FightingServiceException, TException {
//			
//			WebPageDO webPageDO = qingIndexManager.queryWebPageByDocid(docid);
//			if ( webPageDO.getUrl() == null ) {
//				FightingServiceException e = new FightingServiceException();
//				e.setLogid(100001);
//				e.setErrorCode(1001);
//				e.setErrorMessage("Resource Not Found");
//				throw e;
//			}
//			WebPageTraceContent result = new WebPageTraceContent();
//			result.setDocid(webPageDO.getDocid());
//			result.setUrl((webPageDO.getUrl() != null) ? webPageDO.getUrl() : "");
//			result.setTitle((webPageDO.getTitle() != null) ? webPageDO.getTitle() : "");
//			result.setContent((webPageDO.getSummary() != null) ? webPageDO.getSummary() : "");
//			return result;
//
//		}
//
//		@Override
//		public ShortMessageTraceContent queryShortMessageTraceByDocid(long docid)
//				throws FightingServiceException, TException {
//			// TODO Auto-generated method stub
//			SmsInfoDO smsInfoDO = smsQingIndexManager.querySmsInfoByDocid(docid);
//			if ( smsInfoDO.getRecvMobile() == null ) {
//				FightingServiceException e = new FightingServiceException();
//				e.setLogid(100001);
//				e.setErrorCode(1001);
//				e.setErrorMessage("Resource Not Found");
//				throw e;
//			}
//			ShortMessageTraceContent result = new ShortMessageTraceContent();
//			result.setDocid(smsInfoDO.getDocid());
//			result.setSendPhone((smsInfoDO.getSendMobile() != null) ? smsInfoDO.getSendMobile() : "");
//			result.setRecvPhone((smsInfoDO.getRecvMobile() != null) ? smsInfoDO.getRecvMobile() : "");
//			result.setMessage((smsInfoDO.getContent() != null) ? smsInfoDO.getContent() : "");
//			return result;
//			
//		}
//
//	}
//	
//	// =================================================================================================
//	// -------------------------------------------------------------------------------------------------
//	
//	public static void main(String[] args) {
//		
//		// 1. 编写测试thrift，使用它来实现rpc调用
//		// seamless
//		
//		try {
//			
//			// 传输层说明
//			TServerTransport serverTransport = new TServerSocket(9090);
//			
//			LogiclayerHandler handler = new LogiclayerHandler();
//			handler.init();
//			Logiclayer.Processor processor = new Logiclayer.Processor(handler);
//			
//			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
//			
//			server.serve();
//			
//			System.out.println("test data........");
//			
//			server.stop();
//			
//		} catch (TTransportException e) {
//			e.printStackTrace();
//		}
//		
//	}
//}
