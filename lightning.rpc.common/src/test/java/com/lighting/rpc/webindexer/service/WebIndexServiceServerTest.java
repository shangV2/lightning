package com.lighting.rpc.webindexer.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import com.lighting.rpc.common.exception.LightningServiceException;
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

public class WebIndexServiceServerTest {

	static class WebIndexerServiceHandlerMock implements
			WebIndexerService.Iface {
		@Override
		public WIQueryResponse query(WIQueryRequest request)
				throws LightningServiceException, TException {
			WIQueryResponse response = new WIQueryResponse();
			response.setTotalNumber(10);
			List<WIWebPage> webPages = new ArrayList<WIWebPage>();
			WIWebPage webPage = new WIWebPage();
			webPage.setSummary("summary");
			webPage.setTitle("title");
			webPage.setUrl("www.baidu.com");
			webPages.add(webPage);
			response.setWebpages(webPages);
			return response;
		}

		@Override
		public WIQueryArticleResponse queryArticle(WIQueryArticleRequest request)
				throws LightningServiceException, TException {
			WIQueryArticleResponse response = new WIQueryArticleResponse();
			WIWebArticle article = new WIWebArticle();
			article.setDocid(request.getDocid());
			article.setUrl("www.baidu.com");
			article.setTitle("hello");
			article.setContent("hello world");
			response.setArticle(article);
			return response;
		}

		@Override
		public WIQueryBatchArticlesResponse queryBatchArticles(
				WIQueryBatchArticlesRequest request)
				throws LightningServiceException, TException {
			
			WIQueryBatchArticlesResponse response = new WIQueryBatchArticlesResponse();
			List<WIWebArticle> articles = new ArrayList<WIWebArticle>();
			response.setArticles(articles);
	
			for ( int docid : request.getDocids() ) {
				WIWebArticle article = new WIWebArticle();
				article.setDocid(docid);
				article.setUrl("www.baidu.com");
				article.setTitle("hello");
				article.setContent("hello world");
				articles.add(article);
			}
			
			return response;
		}

		@Override
		public WIQueryWebPagesResponse queryWebpages(
				WIQueryWebPagesRequest request)
				throws LightningServiceException, TException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public WIQueryWebPagesWithTimeRangeResponse queryWebPagesWithTimeRange(
				WIQueryWebPagesWithTimeRangeRequest request)
				throws LightningServiceException, TException {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public static void main(String[] args) {

		try {
			// 传输层说明
			TServerTransport serverTransport = new TServerSocket(9010);
			WebIndexerServiceHandlerMock handler = new WebIndexerServiceHandlerMock();

			WebIndexerService.Processor processor = new WebIndexerService.Processor(
					handler);

			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(
					serverTransport).processor(processor));

			server.serve();

			System.out.println("test data........");

			server.stop();

		} catch (TTransportException e) {
			e.printStackTrace();
		}

	}

}
