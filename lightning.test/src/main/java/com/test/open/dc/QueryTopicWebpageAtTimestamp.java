package com.test.open.dc;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.lighting.rpc.common.exception.LightningServiceException;
import com.lighting.rpc.datacenter.model.DCQueryTopicAtTimeRequest;
import com.lighting.rpc.datacenter.model.DCQueryTopicAtTimeResponse;
import com.lighting.rpc.datacenter.model.DCWebPage;
import com.lighting.rpc.datacenter.service.DataCenterService;

public class QueryTopicWebpageAtTimestamp {

	public static void main(String[] args) {
		
		if (args.length != 4) {
			System.err.println("Usage: ip port topic_id");
			System.exit(1);
		}

		String ip = args[0];
		int port = Integer.parseInt(args[1]);
		long topicId = Long.parseLong(args[2]);
		String timestamp = args[3];
		
		DataCenterService.Client client = null;
		TTransport transport = new TSocket(ip, port);
		try {
			transport.open();
		} catch (TTransportException e) {
			e.printStackTrace();
		}

		TProtocol protocol = new TBinaryProtocol(transport);
		client = new DataCenterService.Client(protocol);
		
		DCQueryTopicAtTimeRequest request = new DCQueryTopicAtTimeRequest();
		request.setLogid(1001L);
		request.setTopicId(topicId);
		request.setTimestamp(timestamp);
		
		try {
		
			DCQueryTopicAtTimeResponse response = client.queryTopicAtTime(request);
			
			for ( DCWebPage webpage : response.getWebpages() ) {
				System.out.println(webpage.getUrl());
				System.out.println(webpage.getTitle());
				System.out.println(webpage.getContent());
			}
			
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
		if ( transport != null ) {
			transport.close();
		}
		
	}
	
}
