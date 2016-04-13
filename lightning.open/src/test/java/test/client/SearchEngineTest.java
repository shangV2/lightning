package test.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qing.logiclayer.Logiclayer;
import com.qing.logiclayer.OpenCommonLanguge;
import com.qing.logiclayer.OpenCommonQueryDivideType;
import com.qing.logiclayer.OpenWebPageContent;
import com.qing.logiclayer.QueryOpenWebPageContentRequest;
import com.qing.logiclayer.QueryOpenWebPageContentResponse;
import com.qing.logiclayer.QueryOpenWebPageWithTimeRequest;
import com.qing.logiclayer.QueryOpenWebPageWithTimeResponse;

public class SearchEngineTest {

	private Logiclayer.Client client = null;
	private TTransport transport = null;
	
	@Before
	public void setUp() {
		transport = new TSocket("localhost", 8089);
		try {
			transport.open();
		} catch (TTransportException e) {
			e.printStackTrace();
		}

		TProtocol protocol = new TBinaryProtocol(transport);
		client = new Logiclayer.Client(protocol);
	}

	@After
	public void tearDown() {
		if ( transport != null ) {
			transport.close();
		}
	}

	// @Test
	// public void testQuerySearchSummary() {
	// List<SearchSummary> summaries;
	// try {
	// SearchSummaryResult result = client.querySearchSummary("违法", 0, 3);
	// summaries = result.getSummaries();
	//
	// System.out.println("hit page => " + result.getTotal());
	// for ( SearchSummary summary : summaries ) {
	// System.out.println("url => " + summary.getUrl());
	// System.out.println("title => " + summary.getTitle());
	// System.out.println("content => " + summary.getContent());
	// }
	//
	// } catch (TException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// System.out.println("<__________________________________________________>");
	//
	// try {
	// SearchSummaryResult result = client.querySearchSummary("上海", 1, 3);
	// summaries = result.getSummaries();
	//
	// System.out.println("hit page => " + result.getTotal());
	// for ( SearchSummary summary : summaries ) {
	// System.out.println("url => " + summary.getUrl());
	// System.out.println("title => " + summary.getTitle());
	// System.out.println("content => " + summary.getContent());
	// }
	//
	// } catch (TException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	@Test
	public void testQuerySearchWebpages() {
		try {

			QueryOpenWebPageContentRequest request = new QueryOpenWebPageContentRequest();
			request.setQuery("中秋");
			request.setDivideType(OpenCommonQueryDivideType.OC_QUERY_DIVIDE_OFF);
			request.setSummaryLength(80);
			request.setPageno(0);
			request.setPagesize(10);

			QueryOpenWebPageContentResponse result = client
					.queryOpenWebPageContent(request);

			System.out.println("hit page => " + result.getTotalNum());
			for (OpenWebPageContent summary : result.getWebpages()) {
				System.out.println("url => " + summary.getUrl());
				System.out.println("title => " + summary.getTitle());
				System.out.println("content => " + summary.getContent());
				System.out.println("timestamp => " + summary.getTimestamp());
			}

		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testQuerySearchWebpagesWithTimeRange() {
		try {

			QueryOpenWebPageWithTimeRequest request = new QueryOpenWebPageWithTimeRequest();
			request.setQuery("包子  ");
			request.setStartDate("20140110");
			request.setEndDate("20140820");
			request.setSummaryLength(80);
			request.setPageno(0);
			request.setPagesize(10);

			QueryOpenWebPageWithTimeResponse result = client
					.queryOpenWebPageWithTime(request);

			System.out.println("hit page => " + result.getTotalNum());
			for (OpenWebPageContent summary : result.getWebpages()) {
				System.out.println("url => " + summary.getUrl());
				System.out.println("title => " + summary.getTitle());
				System.out.println("content => " + summary.getContent());
				System.out.println("timestamp => " + summary.getTimestamp());
			}

		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testQueryUy() {

		try {

			QueryOpenWebPageContentRequest request = new QueryOpenWebPageContentRequest();
			request.setLangugeType(OpenCommonLanguge.OC_LAN_UYGHUR_DEFAULT);
			request.setQuery("قېلىش");
			request.setSummaryLength(80);
			request.setPageno(0);
			request.setPagesize(10);

			QueryOpenWebPageContentResponse result = client
					.queryOpenWebPageContent(request);

			System.out.println("hit page => " + result.getTotalNum());
			for (OpenWebPageContent summary : result.getWebpages()) {
				System.out.println("url => " + summary.getUrl());
				System.out.println("title => " + summary.getTitle());
				System.out.println("content => " + summary.getContent());
				System.out.println("timestamp => " + summary.getTimestamp());
			}

		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testQueryTibet() {
		try {

			QueryOpenWebPageContentRequest request = new QueryOpenWebPageContentRequest();
			request.setLangugeType(OpenCommonLanguge.OC_LAN_TIBETAN_DEFAULT);
			request.setQuery("་བརྡ་ཁྱབ་པར་རིས།");
			request.setSummaryLength(80);
			request.setPageno(0);
			request.setPagesize(10);

			QueryOpenWebPageContentResponse result = client
					.queryOpenWebPageContent(request);

			System.out.println("hit page => " + result.getTotalNum());
			for (OpenWebPageContent summary : result.getWebpages()) {
				System.out.println("url => " + summary.getUrl());
				System.out.println("title => " + summary.getTitle());
				System.out.println("content => " + summary.getContent());
				System.out.println("timestamp => " + summary.getTimestamp());
			}

		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
