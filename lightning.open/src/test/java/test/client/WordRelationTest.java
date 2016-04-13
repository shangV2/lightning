package test.client;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qing.logiclayer.AddSensitiveWordRequest;
import com.qing.logiclayer.FightingServiceException;
import com.qing.logiclayer.Logiclayer;
import com.qing.logiclayer.OpenCommonLanguge;
import com.qing.logiclayer.QueryTermCategoryWordListResponse;
import com.qing.logiclayer.QueryTermCategoryWordSplitTrendRequest;
import com.qing.logiclayer.QueryTermCategoryWordSplitTrendResponse;
import com.qing.logiclayer.QueryTermCategoryWordTrendRequest;
import com.qing.logiclayer.QueryTermCategoryWordTrendResponse;
import com.qing.logiclayer.QueryTermCategoryWordlistRequest;
import com.qing.logiclayer.RemoveSensitiveWordRequest;
import com.qing.logiclayer.TermCategoryType;
import com.qing.logiclayer.TimeTrendSet;
import com.qing.logiclayer.WordInfo;
import com.qing.logiclayer.WordSplitTimeTrend;

public class WordRelationTest {

	private static Logiclayer.Client client = null;

	@BeforeClass
	public static void setUp() {
		TTransport transport = new TSocket("localhost", 9090);
		try {
			transport.open();
		} catch (TTransportException e) {
			e.printStackTrace();
		}

		TProtocol protocol = new TBinaryProtocol(transport);
		client = new Logiclayer.Client(protocol);
	}

	@Test
	public void testQueryTermCategoryWordList() {

//		{
//			// 热点词的查询
//			QueryTermCategoryWordlistRequest request = new QueryTermCategoryWordlistRequest();
//			request.setTctype(TermCategoryType.TCT_HOTWORD_TYPE);
//			try {
//				QueryTermCategoryWordListResponse response = client
//						.queryTermCategoryWordlist(request);
//				System.out.println(response.getTotalNum());
//				for (String word : response.getWords()) {
//					System.out.println(word);
//				}
//			} catch (FightingServiceException e) {
//				e.printStackTrace();
//			} catch (TException e) {
//				e.printStackTrace();
//			}
//		}
//
//		System.out.println("###############################");
//		
//		{
//			// 敏感词的查询
//			QueryTermCategoryWordlistRequest request = new QueryTermCategoryWordlistRequest();
//			request.setTctype(TermCategoryType.TCT_SENSWORD_TYPE);
//			try {
//				QueryTermCategoryWordListResponse response = client
//						.queryTermCategoryWordlist(request);
//				System.out.println(response.getTotalNum());
//				for (String word : response.getWords()) {
//					System.out.println(word);
//				}
//			} catch (FightingServiceException e) {
//				e.printStackTrace();
//			} catch (TException e) {
//				e.printStackTrace();
//			}
//		}

		System.out.println("###############################");
		
		{
			// 异常词的查询
			QueryTermCategoryWordlistRequest request = new QueryTermCategoryWordlistRequest();
			request.setTctype(TermCategoryType.TCT_UNUSWORD_TYPE);
			try {
				QueryTermCategoryWordListResponse response = client
						.queryTermCategoryWordlist(request);
				System.out.println(response.getTotalNum());
				for (String word : response.getWords()) {
					System.out.println(word);
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Test
	public void testQueryTermCategoryWordListUy() {

		{
			// 热点词的查询
			QueryTermCategoryWordlistRequest request = new QueryTermCategoryWordlistRequest();
			request.setTctype(TermCategoryType.TCT_HOTWORD_TYPE);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
			try {
				QueryTermCategoryWordListResponse response = client
						.queryTermCategoryWordlist(request);
				System.out.println(response.getTotalNum());
				for (String word : response.getWords()) {
					System.out.println(word);
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}

		System.out.println("###############################");
		
		{
			// 敏感词的查询
			QueryTermCategoryWordlistRequest request = new QueryTermCategoryWordlistRequest();
			request.setTctype(TermCategoryType.TCT_SENSWORD_TYPE);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
			try {
				QueryTermCategoryWordListResponse response = client
						.queryTermCategoryWordlist(request);
				System.out.println(response.getTotalNum());
				for (String word : response.getWords()) {
					System.out.println(word);
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}

		System.out.println("###############################");
		
		{
			// 异常词的查询
			QueryTermCategoryWordlistRequest request = new QueryTermCategoryWordlistRequest();
			request.setTctype(TermCategoryType.TCT_UNUSWORD_TYPE);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
			try {
				QueryTermCategoryWordListResponse response = client
						.queryTermCategoryWordlist(request);
				System.out.println(response.getTotalNum());
				for (String word : response.getWords()) {
					System.out.println(word);
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	@Test
	public void testQueryTermCategoryWordListTibet() {

		{
			// 热点词的查询
			QueryTermCategoryWordlistRequest request = new QueryTermCategoryWordlistRequest();
			request.setTctype(TermCategoryType.TCT_HOTWORD_TYPE);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
			try {
				QueryTermCategoryWordListResponse response = client
						.queryTermCategoryWordlist(request);
				System.out.println(response.getTotalNum());
				for (String word : response.getWords()) {
					System.out.println(word);
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}

		System.out.println("###############################");
		
		{
			// 敏感词的查询
			QueryTermCategoryWordlistRequest request = new QueryTermCategoryWordlistRequest();
			request.setTctype(TermCategoryType.TCT_SENSWORD_TYPE);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
			try {
				QueryTermCategoryWordListResponse response = client
						.queryTermCategoryWordlist(request);
				System.out.println(response.getTotalNum());
				for (String word : response.getWords()) {
					System.out.println(word);
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}

		System.out.println("###############################");
		
		{
			// 异常词的查询
			QueryTermCategoryWordlistRequest request = new QueryTermCategoryWordlistRequest();
			request.setTctype(TermCategoryType.TCT_UNUSWORD_TYPE);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
			try {
				QueryTermCategoryWordListResponse response = client
						.queryTermCategoryWordlist(request);
				System.out.println(response.getTotalNum());
				for (String word : response.getWords()) {
					System.out.println(word);
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	@Test
	public void testQueryTermCategoryWordTrend() {
		
		{
			// *) 热点词的趋势变化
			QueryTermCategoryWordTrendRequest request = new QueryTermCategoryWordTrendRequest();
			request.setStartdate("20131217");
			request.setEnddate("20131221");
			request.setTctype(TermCategoryType.TCT_HOTWORD_TYPE);
			try {
				QueryTermCategoryWordTrendResponse response = client.queryTermCategoryWordTrend(request);
				for ( TimeTrendSet tset : response.getTrends() ) {
					System.out.println(tset.getDate());
					List<WordInfo> lists = tset.getWordinfos();
					for (WordInfo wi : lists) {
						System.out.println(wi.getWord() + ":" + wi.getFreq());
					}
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("############################################");
		
		{
			// *) 敏感词的趋势变化
			QueryTermCategoryWordTrendRequest request = new QueryTermCategoryWordTrendRequest();
			request.setStartdate("20140101");
			request.setEnddate("20140207");
			request.setTctype(TermCategoryType.TCT_SENSWORD_TYPE);
			try {
				QueryTermCategoryWordTrendResponse response = client.queryTermCategoryWordTrend(request);
				for ( TimeTrendSet tset : response.getTrends() ) {
					System.out.println(tset.getDate());
					List<WordInfo> lists = tset.getWordinfos();
					for (WordInfo wi : lists) {
						System.out.println(wi.getWord() + ":" + wi.getFreq());
					}
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("###################################################################");
		
		{
			// *) 异常词的趋势变化
			QueryTermCategoryWordTrendRequest request = new QueryTermCategoryWordTrendRequest();
			request.setStartdate("20131217");
			request.setEnddate("20131221");
			request.setTctype(TermCategoryType.TCT_UNUSWORD_TYPE);
			try {
				QueryTermCategoryWordTrendResponse response = client.queryTermCategoryWordTrend(request);
				for ( TimeTrendSet tset : response.getTrends() ) {
					System.out.println(tset.getDate());
					List<WordInfo> lists = tset.getWordinfos();
					for (WordInfo wi : lists) {
						System.out.println(wi.getWord() + ":" + wi.getFreq());
					}
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	@Test
	public void testQueryTermCategoryWordTrendUy() {
		
		{
			// *) 热点词的趋势变化
			QueryTermCategoryWordTrendRequest request = new QueryTermCategoryWordTrendRequest();
			request.setStartdate("20131217");
			request.setEnddate("20131221");
			request.setTctype(TermCategoryType.TCT_HOTWORD_TYPE);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
			try {
				QueryTermCategoryWordTrendResponse response = client.queryTermCategoryWordTrend(request);
				for ( TimeTrendSet tset : response.getTrends() ) {
					System.out.println(tset.getDate());
					List<WordInfo> lists = tset.getWordinfos();
					for (WordInfo wi : lists) {
						System.out.println(wi.getWord() + ":" + wi.getFreq());
					}
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("############################################");
		
		{
			// *) 敏感词的趋势变化
			QueryTermCategoryWordTrendRequest request = new QueryTermCategoryWordTrendRequest();
			request.setStartdate("20131217");
			request.setEnddate("20131221");
			request.setTctype(TermCategoryType.TCT_SENSWORD_TYPE);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
			try {
				QueryTermCategoryWordTrendResponse response = client.queryTermCategoryWordTrend(request);
				for ( TimeTrendSet tset : response.getTrends() ) {
					System.out.println(tset.getDate());
					List<WordInfo> lists = tset.getWordinfos();
					for (WordInfo wi : lists) {
						System.out.println(wi.getWord() + ":" + wi.getFreq());
					}
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("###################################################################");
		
		{
			// *) 异常词的趋势变化
			QueryTermCategoryWordTrendRequest request = new QueryTermCategoryWordTrendRequest();
			request.setStartdate("20131217");
			request.setEnddate("20131221");
			request.setTctype(TermCategoryType.TCT_UNUSWORD_TYPE);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
			try {
				QueryTermCategoryWordTrendResponse response = client.queryTermCategoryWordTrend(request);
				for ( TimeTrendSet tset : response.getTrends() ) {
					System.out.println(tset.getDate());
					List<WordInfo> lists = tset.getWordinfos();
					for (WordInfo wi : lists) {
						System.out.println(wi.getWord() + ":" + wi.getFreq());
					}
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	@Test
	public void testQueryTermCategoryWordTrendTibet() {
		
		{
			// *) 热点词的趋势变化
			QueryTermCategoryWordTrendRequest request = new QueryTermCategoryWordTrendRequest();
			request.setStartdate("20131217");
			request.setEnddate("20131221");
			request.setTctype(TermCategoryType.TCT_HOTWORD_TYPE);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
			try {
				QueryTermCategoryWordTrendResponse response = client.queryTermCategoryWordTrend(request);
				for ( TimeTrendSet tset : response.getTrends() ) {
					System.out.println(tset.getDate());
					List<WordInfo> lists = tset.getWordinfos();
					for (WordInfo wi : lists) {
						System.out.println(wi.getWord() + ":" + wi.getFreq());
					}
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("############################################");
		
		{
			// *) 敏感词的趋势变化
			QueryTermCategoryWordTrendRequest request = new QueryTermCategoryWordTrendRequest();
			request.setStartdate("20131217");
			request.setEnddate("20131221");
			request.setTctype(TermCategoryType.TCT_SENSWORD_TYPE);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
			try {
				QueryTermCategoryWordTrendResponse response = client.queryTermCategoryWordTrend(request);
				for ( TimeTrendSet tset : response.getTrends() ) {
					System.out.println(tset.getDate());
					List<WordInfo> lists = tset.getWordinfos();
					for (WordInfo wi : lists) {
						System.out.println(wi.getWord() + ":" + wi.getFreq());
					}
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("###################################################################");
		
		{
			// *) 异常词的趋势变化
			QueryTermCategoryWordTrendRequest request = new QueryTermCategoryWordTrendRequest();
			request.setStartdate("20131217");
			request.setEnddate("20131221");
			request.setTctype(TermCategoryType.TCT_UNUSWORD_TYPE);
			request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
			try {
				QueryTermCategoryWordTrendResponse response = client.queryTermCategoryWordTrend(request);
				for ( TimeTrendSet tset : response.getTrends() ) {
					System.out.println(tset.getDate());
					List<WordInfo> lists = tset.getWordinfos();
					for (WordInfo wi : lists) {
						System.out.println(wi.getWord() + ":" + wi.getFreq());
					}
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	@Test
	public void testQueryTermCategoryWordSplitTrend() {
		
		{
			// 热点词
			QueryTermCategoryWordSplitTrendRequest request = new QueryTermCategoryWordSplitTrendRequest();
			request.setStartdate("20131217");
			request.setEnddate("20131221");
			request.setTctype(TermCategoryType.TCT_HOTWORD_TYPE);
			request.setWord("大雾");
			try {
				QueryTermCategoryWordSplitTrendResponse response = client.queryTermCategoryWordSplitTrend(request);
				for ( WordSplitTimeTrend trend : response.getTrends() ) {
					System.out.println(trend.getDate() + ":" + trend.getPagefreq() + "/" + trend.getSmsfreq());
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("######################################################");
		
		{
			// 敏感词
			QueryTermCategoryWordSplitTrendRequest request = new QueryTermCategoryWordSplitTrendRequest();
			request.setStartdate("20140101");
			request.setEnddate("20140205");
			request.setTctype(TermCategoryType.TCT_SENSWORD_TYPE);
			request.setWord("新疆");
			try {
				QueryTermCategoryWordSplitTrendResponse response = client.queryTermCategoryWordSplitTrend(request);
				for ( WordSplitTimeTrend trend : response.getTrends() ) {
					System.out.println(trend.getDate() + ":" + trend.getPagefreq() + "/" + trend.getSmsfreq());
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("######################################################");
		{
			// 敏感词
			QueryTermCategoryWordSplitTrendRequest request = new QueryTermCategoryWordSplitTrendRequest();
			request.setStartdate("20131217");
			request.setEnddate("20131221");
			request.setTctype(TermCategoryType.TCT_UNUSWORD_TYPE);
			request.setWord("宝塔镇河妖");
			try {
				QueryTermCategoryWordSplitTrendResponse response = client.queryTermCategoryWordSplitTrend(request);
				for ( WordSplitTimeTrend trend : response.getTrends() ) {
					System.out.println(trend.getDate() + ":" + trend.getPagefreq() + "/" + trend.getSmsfreq());
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	@Test
	public void testQueryTermCategoryWordSplitTrendUy() {
		
		{
			// 热点词
			QueryTermCategoryWordSplitTrendRequest request = new QueryTermCategoryWordSplitTrendRequest();
			request.setStartdate("20131217");
			request.setEnddate("20131221");
			request.setTctype(TermCategoryType.TCT_HOTWORD_TYPE);
			request.setWord("جەنۇبىنى");
			request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
			try {
				QueryTermCategoryWordSplitTrendResponse response = client.queryTermCategoryWordSplitTrend(request);
				for ( WordSplitTimeTrend trend : response.getTrends() ) {
					System.out.println(trend.getDate() + ":" + trend.getPagefreq() + "/" + trend.getSmsfreq());
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("######################################################");
		
		{
			// 敏感词
			QueryTermCategoryWordSplitTrendRequest request = new QueryTermCategoryWordSplitTrendRequest();
			request.setStartdate("20131217");
			request.setEnddate("20131221");
			request.setTctype(TermCategoryType.TCT_SENSWORD_TYPE);
			request.setWord("六四");
			request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
			try {
				QueryTermCategoryWordSplitTrendResponse response = client.queryTermCategoryWordSplitTrend(request);
				for ( WordSplitTimeTrend trend : response.getTrends() ) {
					System.out.println(trend.getDate() + ":" + trend.getPagefreq() + "/" + trend.getSmsfreq());
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("######################################################");
		{
			// 敏感词
			QueryTermCategoryWordSplitTrendRequest request = new QueryTermCategoryWordSplitTrendRequest();
			request.setStartdate("20131217");
			request.setEnddate("20131221");
			request.setTctype(TermCategoryType.TCT_UNUSWORD_TYPE);
			request.setWord("ھۆكۈمەت");
			request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
			try {
				QueryTermCategoryWordSplitTrendResponse response = client.queryTermCategoryWordSplitTrend(request);
				for ( WordSplitTimeTrend trend : response.getTrends() ) {
					System.out.println(trend.getDate() + ":" + trend.getPagefreq() + "/" + trend.getSmsfreq());
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	@Test
	public void testQueryTermCategoryWordSplitTrendTibet() {
		
		{
			// 热点词
			QueryTermCategoryWordSplitTrendRequest request = new QueryTermCategoryWordSplitTrendRequest();
			request.setStartdate("20131217");
			request.setEnddate("20131221");
			request.setTctype(TermCategoryType.TCT_HOTWORD_TYPE);
			request.setWord("བོད་རིག");
			request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
			try {
				QueryTermCategoryWordSplitTrendResponse response = client.queryTermCategoryWordSplitTrend(request);
				for ( WordSplitTimeTrend trend : response.getTrends() ) {
					System.out.println(trend.getDate() + ":" + trend.getPagefreq() + "/" + trend.getSmsfreq());
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("######################################################");
		
		{
			// 敏感词
			QueryTermCategoryWordSplitTrendRequest request = new QueryTermCategoryWordSplitTrendRequest();
			request.setStartdate("20131217");
			request.setEnddate("20131221");
			request.setTctype(TermCategoryType.TCT_SENSWORD_TYPE);
			request.setWord("");
			request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
			try {
				QueryTermCategoryWordSplitTrendResponse response = client.queryTermCategoryWordSplitTrend(request);
				for ( WordSplitTimeTrend trend : response.getTrends() ) {
					System.out.println(trend.getDate() + ":" + trend.getPagefreq() + "/" + trend.getSmsfreq());
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("######################################################");
		{
			// 异常词
			QueryTermCategoryWordSplitTrendRequest request = new QueryTermCategoryWordSplitTrendRequest();
			request.setStartdate("20131217");
			request.setEnddate("20131221");
			request.setTctype(TermCategoryType.TCT_UNUSWORD_TYPE);
			request.setWord("་རིག");
			request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
			try {
				QueryTermCategoryWordSplitTrendResponse response = client.queryTermCategoryWordSplitTrend(request);
				for ( WordSplitTimeTrend trend : response.getTrends() ) {
					System.out.println(trend.getDate() + ":" + trend.getPagefreq() + "/" + trend.getSmsfreq());
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Test
	public void testAddRemoveSensitiveWord() {
		
		{
			System.out.println("list word phrase");
			// list word
			// 敏感词的查询
			QueryTermCategoryWordlistRequest request = new QueryTermCategoryWordlistRequest();
			request.setTctype(TermCategoryType.TCT_SENSWORD_TYPE);
			try {
				QueryTermCategoryWordListResponse response = client
						.queryTermCategoryWordlist(request);
				System.out.println(response.getTotalNum());
				for (String word : response.getWords()) {
					System.out.println(word);
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		{
			System.out.println("add new word phrase");
			// add word
			AddSensitiveWordRequest request = new AddSensitiveWordRequest();
			request.setWord("习近平");
			try {
				client.addSensitiveWord(request);
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
			
		}
		
		{
			System.out.println("list word phrase");
			// list word
			// 敏感词的查询
			QueryTermCategoryWordlistRequest request = new QueryTermCategoryWordlistRequest();
			request.setTctype(TermCategoryType.TCT_SENSWORD_TYPE);
			try {
				QueryTermCategoryWordListResponse response = client
						.queryTermCategoryWordlist(request);
				System.out.println(response.getTotalNum());
				for (String word : response.getWords()) {
					System.out.println(word);
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		{
			System.out.println("remove word phrase");
			// remove word
			RemoveSensitiveWordRequest request = new RemoveSensitiveWordRequest();
			request.setWord("习近平");
			try {
				client.removeSensitiveWord(request);
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		
		{
			System.out.println("list word phrase");
			// list word
			// 敏感词的查询
			QueryTermCategoryWordlistRequest request = new QueryTermCategoryWordlistRequest();
			request.setTctype(TermCategoryType.TCT_SENSWORD_TYPE);
			try {
				QueryTermCategoryWordListResponse response = client
						.queryTermCategoryWordlist(request);
				System.out.println(response.getTotalNum());
				for (String word : response.getWords()) {
					System.out.println(word);
				}
			} catch (FightingServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
}
