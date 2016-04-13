package com.test.open.dc;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.lighting.rpc.common.exception.LightningServiceException;
import com.lighting.rpc.datacenter.model.DCDateNumberTopicTrend;
import com.lighting.rpc.datacenter.model.DCQueryTopicTrendRequest;
import com.lighting.rpc.datacenter.model.DCQueryTopicTrendResponse;
import com.lighting.rpc.datacenter.service.DataCenterService;

public class QueryTopicTrends {

	
	public static void main(String[] args) {
	
		if (args.length != 3) {
			System.err.println("Usage: ip port topic_id");
			System.exit(1);
		}

		String ip = args[0];
		int port = Integer.parseInt(args[1]);
		long topicId = Long.parseLong(args[2]);
		
		DataCenterService.Client client = null;
		TTransport transport = new TSocket(ip, port);
		try {
			transport.open();
		} catch (TTransportException e) {
			e.printStackTrace();
		}

		TProtocol protocol = new TBinaryProtocol(transport);
		client = new DataCenterService.Client(protocol);
		
		DCQueryTopicTrendRequest request = new DCQueryTopicTrendRequest();
		request.setLogid(1000L);
		request.setTopicId(topicId);
		
		try {
			DCQueryTopicTrendResponse response = client.queryTopicTrend(request);
			for ( DCDateNumberTopicTrend trend : response.getTrends() ) {
				System.out.println(trend.getDate() + ":" + trend.getNumber());
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
