package com.lighting.webindexer.rpc.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;

import com.lighting.rpc.common.exception.LightningErrorCode;
import com.lighting.rpc.common.exception.LightningServiceException;
import com.lighting.rpc.webindexer.model.WIHighLightType;
import com.lighting.rpc.webindexer.model.WIQueryArticleRequest;
import com.lighting.rpc.webindexer.model.WIQueryArticleResponse;
import com.lighting.rpc.webindexer.model.WIQueryBatchArticlesRequest;
import com.lighting.rpc.webindexer.model.WIQueryBatchArticlesResponse;
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
import com.lighting.webindexer.controller.WebIndexerController;
import com.lighting.webindexer.manager.QingIndexManager;
import com.qing.index.model.dataobject.WebPageDO;
import com.qing.index.model.dataobject.WebPageResultDO;

public class WebIndexerServiceHandlerImpl implements WebIndexerService.Iface {

	private WebIndexerController webIndexerController;
	
	public WebIndexerServiceHandlerImpl(WebIndexerController webIndexerController) {
		this.webIndexerController = webIndexerController;
	}
	
	@Override
	public WIQueryResponse query(WIQueryRequest request) throws TException {
		
		List<WIWebPage> webpages = new ArrayList<WIWebPage>();
		WIQueryResponse response = new WIQueryResponse();
		response.setTotalNumber(0);
		response.setWebpages(webpages);
		
		QingIndexManager qingIndexManager = webIndexerController.getQingIndexManager();
		WebPageResultDO resultDO = qingIndexManager.query(request.getQuery(), request.getPageno(), request.getPagesize());
		
		response.setTotalNumber(resultDO.getTotalNumber());
		for ( WebPageDO webPageDO : resultDO.getWebPages() ) {
			WIWebPage webpage = new WIWebPage();
			webpage.setDocid(webPageDO.getDocid());
			webpage.setTitle(webPageDO.getTitle());
			webpage.setUrl(webPageDO.getUrl());
			webpage.setSummary(webPageDO.getSummary());
			response.getWebpages().add(webpage);
		}
		
		return response;
	
	}

	@Override
	public WIQueryArticleResponse queryArticle(WIQueryArticleRequest request)
			throws LightningServiceException, TException {
		// TODO Auto-generated method stub
		
		WIQueryArticleResponse response = new WIQueryArticleResponse();
		
		QingIndexManager qingIndexManager = webIndexerController.getQingIndexManager();
		WebPageDO webPageDO = qingIndexManager.queryWebPageByDocid(request.getDocid());
		if ( webPageDO == null ) {
			LightningErrorCode error = LightningErrorCode.RPC_WEBINDEXER_ARTICLE_NOT_FOUND_ERROR;
			throw new LightningServiceException(request.getLogid(), error.getErrorCode(), error.getDescription());
		}
		
		WIWebArticle article = new WIWebArticle();
		article.setDocid(webPageDO.getDocid());
		article.setTitle(webPageDO.getTitle());
		article.setUrl(webPageDO.getUrl());
		article.setContent(webPageDO.getSummary());
		article.setTimestamp(webPageDO.getTimestamp());
		article.setSource(webPageDO.getSource());
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
		
		QingIndexManager qingIndexManager = webIndexerController.getQingIndexManager();
		
		List<Integer> docids = request.getDocids();
		for ( Integer docid : docids ) {
			WebPageDO webPageDO = qingIndexManager.queryWebPageByDocid(docid);
			if ( webPageDO != null ) {
				WIWebArticle article = new WIWebArticle();
				article.setDocid(webPageDO.getDocid());
				article.setTitle(webPageDO.getTitle());
				article.setUrl(webPageDO.getUrl());
				article.setContent(webPageDO.getSummary());
				article.setSource(webPageDO.getSource());
				article.setTimestamp(webPageDO.getTimestamp());
				response.getArticles().add(article);
			}
		}
		return response;
		
	}

	@Override
	public WIQueryWebPagesResponse queryWebpages(WIQueryWebPagesRequest request)
			throws LightningServiceException, TException {

		WIQueryWebPagesResponse response = new WIQueryWebPagesResponse();
		response.setTotalNum(0);
		response.setWebpages(new ArrayList<WIWebPageItem>());
		
		WIHighLightType highlightType = request.getHightlight();
		if ( WIHighLightType.WI_HIGHLIGHT_ON == highlightType ) {
			
			QingIndexManager qingIndexManager = webIndexerController.getQingIndexManager();
			
			WebPageResultDO resultDO = null;
			if ( request.isSetDivideType() && request.getDivideType() == WIQueryDivideType.WI_QUERY_DIVIDE_ON ) {
				resultDO = qingIndexManager.queryWebpages(request.getQuery(), request.getPageno(), request.getPagesize(), request.getSummaryLength());
			} else {
				resultDO = qingIndexManager.queryWebpagesByTerm(request.getQuery().trim(), request.getPageno(), request.getPagesize(), request.getSummaryLength());
			}
			
			response.setTotalNum(resultDO.getTotalNumber());
			for ( WebPageDO webPageDO : resultDO.getWebPages() ) {
				WIWebPageItem webpage = new WIWebPageItem();
				webpage.setDocid(webPageDO.getDocid());
				webpage.setUrl(webPageDO.getUrl());
				webpage.setTitle(webPageDO.getTitle());
				webpage.setContent(webPageDO.getSummary());
				webpage.setTimestamp(webPageDO.getTimestamp());
				webpage.setSource(webPageDO.getSource());
				response.getWebpages().add(webpage);
			}
			
		} else if ( WIHighLightType.WI_HIGHLIGHT_OFF == highlightType ) {
			
			QingIndexManager qingIndexManager = webIndexerController.getQingIndexManager();
			WebPageResultDO resultDO = qingIndexManager.queryWebpages(request.getQuery(), request.getPageno(), request.getPagesize(), request.getSummaryLength());
			
			response.setTotalNum(resultDO.getTotalNumber());
			for ( WebPageDO webPageDO : resultDO.getWebPages() ) {
				WIWebPageItem webpage = new WIWebPageItem();
				webpage.setDocid(webPageDO.getDocid());
				webpage.setUrl(webPageDO.getUrl());
				webpage.setTitle(webPageDO.getTitle());
				webpage.setContent(webPageDO.getSummary());
				webpage.setTimestamp(webPageDO.getTimestamp());
				webpage.setSource(webPageDO.getSource());
				response.getWebpages().add(webpage);
			}
			
		}
		return response;
		
	}

	@Override
	public WIQueryWebPagesWithTimeRangeResponse queryWebPagesWithTimeRange(
			WIQueryWebPagesWithTimeRangeRequest request)
			throws LightningServiceException, TException {
		// TODO Auto-generated method stub
		
		WIQueryWebPagesWithTimeRangeResponse response = new WIQueryWebPagesWithTimeRangeResponse();
		response.setTotalNum(0);
		response.setWebpages(new ArrayList<WIWebPageItem>());
		
		String query = request.getQuery();
		String startDate = request.getStartDate();
		String endDate = request.getEndDate();
		int pageno = request.getPageno();
		int pagesize = request.getPagesize();
		int summaryLength = request.getSummaryLength();
		
		QingIndexManager qingIndexManager = webIndexerController.getQingIndexManager();
		WebPageResultDO webPageResultDO = qingIndexManager.queryWebpagesWithTimeRange(query, startDate, endDate, pageno, pagesize, summaryLength);
		
		response.setTotalNum(webPageResultDO.getTotalNumber());
		for ( WebPageDO webPageDO : webPageResultDO.getWebPages() ) {
			WIWebPageItem webpage = new WIWebPageItem();
			webpage.setDocid(webPageDO.getDocid());
			webpage.setUrl(webPageDO.getUrl());
			webpage.setTitle(webPageDO.getTitle());
			webpage.setContent(webPageDO.getSummary());
			webpage.setTimestamp(webPageDO.getTimestamp());
			webpage.setSource(webPageDO.getSource());
			response.getWebpages().add(webpage);
		}
		
		return response;
	
	}
	
}
