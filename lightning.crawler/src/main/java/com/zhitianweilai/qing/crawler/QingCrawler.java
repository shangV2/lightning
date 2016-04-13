package com.zhitianweilai.qing.crawler;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.zhitianweilai.qing.crawler.exception.QingCrawlerException;
import com.zhitianweilai.qing.utils.EncoderDetectorHelper;

public class QingCrawler {

	/*
	 * 
	 */
//	public void crawle(String url) {
//		
//		HttpGet request = new HttpGet(url);
//		DefaultHttpClient client = new DefaultHttpClient();
//		
//		try {
//			
//			request.setHeader("User-Agent", "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803");
////			request.setHeader("Accept-Encoding", "gzip, deflate");
//			request.setHeader("Accept", "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
//			
//			HttpResponse response = client.execute(request);
//			StatusLine status = response.getStatusLine();
//			
////			System.out.println(status.getStatusCode() + ":" + status.getProtocolVersion());
//			if ( status.getStatusCode() == 200 ) {
//				HttpEntity entity = response.getEntity();
//				
//				Header header = entity.getContentType();
//				if ( header != null ) {
////					System.out.println(header.toString());			// @QingCrawler
//					if ( "text/html".equalsIgnoreCase(header.getValue()) ) {
//						// 1. text/html				}
//						InputStream is = entity.getContent();
//						if ( is != null ) {
////							String content = EntityUtils.toString(entity);
//							
//							byte[] byteBuffer = EntityUtils.toByteArray(entity);
//							
//							String encoding = EncoderDetectorHelper.detect(byteBuffer);
//							
//							System.out.println("content -> " + new String(byteBuffer, encoding));
//						}
//					}
//				}
//			
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if ( request != null ) {
//				request.releaseConnection();
//			}
//		}		
//		
//	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public String crawle(String url) throws QingCrawlerException {
		
		HttpGet request = new HttpGet(url);
		DefaultHttpClient client = new DefaultHttpClient();
		
		try {
			
			request.setHeader("User-Agent", "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803");
//			request.setHeader("Accept-Encoding", "gzip, deflate");
			request.setHeader("Accept", "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
			
			HttpResponse response = client.execute(request);
			StatusLine status = response.getStatusLine();
			
			if ( status.getStatusCode() == 200 ) {
				HttpEntity entity = response.getEntity();
				
				Header header = entity.getContentType();
				if ( header != null ) {
					if ( "text/html".equalsIgnoreCase(header.getValue()) ) {
						InputStream is = entity.getContent();
						if ( is != null ) {
							byte[] byteBuffer = EntityUtils.toByteArray(entity);
							String encoding = EncoderDetectorHelper.detect(byteBuffer);
							return new String(byteBuffer, encoding);
						}
					}
				}
			}
			return "";
		} catch (ClientProtocolException e) {
//			e.printStackTrace();
			throw new QingCrawlerException();
		} catch (IOException e) {
//			e.printStackTrace();
			throw new QingCrawlerException();
		} finally {
			if ( request != null ) {
				request.releaseConnection();
			}
		}		
	}
	
}
