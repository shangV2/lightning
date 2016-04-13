package com.lighting.rpc.webindexer.service;

import java.util.Arrays;
import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lighting.rpc.common.exception.LightningServiceException;
import com.lighting.rpc.webindexer.model.WIHighLightType;
import com.lighting.rpc.webindexer.model.WIQueryArticleRequest;
import com.lighting.rpc.webindexer.model.WIQueryArticleResponse;
import com.lighting.rpc.webindexer.model.WIQueryBatchArticlesRequest;
import com.lighting.rpc.webindexer.model.WIQueryBatchArticlesResponse;
import com.lighting.rpc.webindexer.model.WIQueryRequest;
import com.lighting.rpc.webindexer.model.WIQueryResponse;
import com.lighting.rpc.webindexer.model.WIQueryWebPagesRequest;
import com.lighting.rpc.webindexer.model.WIQueryWebPagesResponse;
import com.lighting.rpc.webindexer.model.WIQueryWebPagesWithTimeRangeRequest;
import com.lighting.rpc.webindexer.model.WIQueryWebPagesWithTimeRangeResponse;
import com.lighting.rpc.webindexer.model.WIWebArticle;
import com.lighting.rpc.webindexer.model.WIWebPage;
import com.lighting.rpc.webindexer.model.WIWebPageItem;

public class WebIndexerServiceClientTest {

	private static WebIndexerService.Client client = null;

	@BeforeClass
	public static void setUp() {
		TTransport transport = new TSocket("localhost", 8010);
		try {
			transport.open();
		} catch (TTransportException e) {
			e.printStackTrace();
		}

		TProtocol protocol = new TBinaryProtocol(transport);
		client = new WebIndexerService.Client(protocol);
	}
	
	@Test
	public void testQuery() {
		
		try {
			WIQueryRequest request = new WIQueryRequest();
			request.setLogid(1001L);
			request.setQuery("新疆");
			WIQueryResponse response = client.query(request);

			System.out.println(response.getTotalNumber());
			for ( WIWebPage webPage : response.getWebpages() ) {
				System.out.println(webPage.getDocid());
				System.out.println(webPage.getTitle());
				System.out.println(webPage.getUrl());
				System.out.println(webPage.getSummary());
			}
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testQueryArticle() {
		
		try {
			WIQueryArticleRequest request = new WIQueryArticleRequest();
			request.setLogid(1001L);
			request.setDocid(49);
			
			WIQueryArticleResponse response = client.queryArticle(request);

			WIWebArticle article = response.getArticle();
			System.out.println(article.getDocid());
			System.out.println(article.getTitle());
			System.out.println(article.getUrl());
			System.out.println(article.getContent());
			
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testQueryBatchArticles() {
		try {
			WIQueryBatchArticlesRequest request = new WIQueryBatchArticlesRequest();
			request.setLogid(1001L);
			List<Integer> docids = Arrays.asList(new Integer[] {
					1, 2, 3
			});
			request.setDocids(docids);
			WIQueryBatchArticlesResponse response = client.queryBatchArticles(request);
			for ( WIWebArticle article : response.getArticles() ) {
				System.out.println(article.getDocid());
				System.out.println(article.getTitle());
				System.out.println(article.getUrl());
				System.out.println(article.getContent());
			}
		} catch(TException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryWebpages() {
		
		WIQueryWebPagesRequest request = new WIQueryWebPagesRequest();
		request.setLogid(10001L);
		request.setSummaryLength(100);
		request.setQuery("新疆");
		request.setHightlight(WIHighLightType.WI_HIGHLIGHT_ON);
		
		try {
			WIQueryWebPagesResponse response = client.queryWebpages(request);
			System.out.println("total num :" + response.getTotalNum());
			for ( WIWebPageItem item : response.getWebpages() ) {
				System.out.println(item.getDocid());
				System.out.println(item.getTitle());
				System.out.println(item.getUrl());
				System.out.println(item.getTimestamp());
				System.out.println(item.getSource());
				System.out.println(item.getContent());
				
				System.out.println("-----------------------------------------");
			}
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testQueryWebpagesByTerm() {
		
		WIQueryWebPagesRequest request = new WIQueryWebPagesRequest();
		request.setLogid(10001L);
		request.setSummaryLength(100);
		request.setQuery("大上海");
		request.setHightlight(WIHighLightType.WI_HIGHLIGHT_ON);
//		request.setDivideType(WIQueryDivideType.WI_QUERY_DIVIDE_OFF);
		
		try {
			WIQueryWebPagesResponse response = client.queryWebpages(request);
			System.out.println("total num :" + response.getTotalNum());
			for ( WIWebPageItem item : response.getWebpages() ) {
				System.out.println(item.getDocid());
				System.out.println(item.getTitle());
				System.out.println(item.getUrl());
				System.out.println(item.getTimestamp());
				System.out.println(item.getSource());
				System.out.println(item.getContent());
				
				System.out.println("-----------------------------------------");
			}
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testQueryWithTimeRange() {
		
		WIQueryWebPagesWithTimeRangeRequest request = new WIQueryWebPagesWithTimeRangeRequest();
		request.setStartDate("2014-01-20");
		request.setEndDate("2014-01-30");
		request.setQuery("新疆");
		try {
			WIQueryWebPagesWithTimeRangeResponse response = client.queryWebPagesWithTimeRange(request);
			System.out.println(response.getTotalNum());
			for ( WIWebPageItem item : response.getWebpages() ) {
				System.out.println(item.getTitle());
				System.out.println(item.getTimestamp());
				System.out.println("================================");
			}
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
