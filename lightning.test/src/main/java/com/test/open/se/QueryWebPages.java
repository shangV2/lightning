package com.test.open.se;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.qing.logiclayer.Logiclayer;
import com.qing.logiclayer.OpenCommonQueryDivideType;
import com.qing.logiclayer.OpenWebPageContent;
import com.qing.logiclayer.QueryOpenWebPageContentRequest;
import com.qing.logiclayer.QueryOpenWebPageContentResponse;

public class QueryWebPages {
	
	public static void main(String[] args) {
		
		if ( args.length != 3 ) {
			System.err.println("Usage: ip port word");
			System.exit(1);
		}
		
		String ip = args[0];
		int port = Integer.parseInt(args[1]);
		String word = args[2];
		
		TTransport transport = new TSocket(ip, port);
		try {
			transport.open();
		} catch (TTransportException e) {
			e.printStackTrace();
		}

		TProtocol protocol = new TBinaryProtocol(transport);
		Logiclayer.Client client = new Logiclayer.Client(protocol);

		QueryOpenWebPageContentRequest request = new QueryOpenWebPageContentRequest();
		request.setQuery(word);
		request.setDivideType(OpenCommonQueryDivideType.OC_QUERY_DIVIDE_OFF);
		request.setSummaryLength(80);
		request.setPageno(0);
		request.setPagesize(10);

		try {
			QueryOpenWebPageContentResponse result = client
					.queryOpenWebPageContent(request);
			System.out.println("hit page => " + result.getTotalNum());
			for (OpenWebPageContent summary : result.getWebpages()) {
				System.out.println("url => " + summary.getUrl());
				System.out.println("title => " + summary.getTitle());
				System.out.println("content => " + summary.getContent());
				System.out.println("timestamp => " + summary.getTimestamp());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if ( transport != null ) {
			transport.close();
		}
		
	}
	
}
