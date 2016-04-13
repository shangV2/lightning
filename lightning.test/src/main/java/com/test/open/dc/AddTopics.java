package com.test.open.dc;

import java.util.Arrays;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.lighting.rpc.common.exception.LightningServiceException;
import com.lighting.rpc.datacenter.model.DCAddSensitiveWordRequest;
import com.lighting.rpc.datacenter.model.DCAddSensitiveWordResponse;
import com.lighting.rpc.datacenter.model.DCCommonLanguge;
import com.lighting.rpc.datacenter.model.DCConsistentTopic;
import com.lighting.rpc.datacenter.model.DCCreateTopicRequest;
import com.lighting.rpc.datacenter.model.DCCreateTopicResponse;
import com.lighting.rpc.datacenter.service.DataCenterService;

public class AddTopics {

	public static void main(String[] args) {
		
//		args = new String[] { "110.249.221.251", "8020" };

		if (args.length != 2) {
			System.err.println("Usage: ip port");
			System.exit(1);
		}

		String ip = args[0];
		int port = Integer.parseInt(args[1]);

		DataCenterService.Client client = null;
		TTransport transport = new TSocket(ip, port);
		try {
			transport.open();
		} catch (TTransportException e) {
			e.printStackTrace();
		}

		TProtocol protocol = new TBinaryProtocol(transport);
		client = new DataCenterService.Client(protocol);

		String[] topicNames = {
				"神权政治",
				"宗教至上", 
				"异教徒",
				"圣战",
		};
		
		String[][] topicWords = {
				{ "伊斯兰政权", "真主", "唯一", "不服从", "政府" },
				{ "古兰经", "圣训", "制定", "衡量", "判断", "国家", "法律", "经济", "现代文明" },
				{ "安拉", "异教徒", "叛教者", "孤立", "恐吓", "杀死", "砍死", "武器" },
				{ "圣战", "主命", "政府", "推翻", "杀死", "武器" },
				{ "主持仪式", "神学班", "修道班", "地点", "婚礼", "葬礼", "聚会", "麦西来甫", "台比力克", "孩子", "隐蔽", "政府" }
		};
	
		try {
			
			for ( int i = 0; i < topicNames.length; i++ ) {
				DCCreateTopicRequest request = new DCCreateTopicRequest();
				request.setLogid(10001L);
				request.setLangugeType(DCCommonLanguge.DC_LAN_CHINESE_ZH_CN);
				DCConsistentTopic topic = new DCConsistentTopic();
				topic.setTopicName(topicNames[i]);
//				topic.setWords(Arrays.asList(new String[] { "伊斯兰政权", "真主", "唯一", "不服从", "政府" }));
				topic.setWords(Arrays.asList(topicWords[i]));
				topic.setStartDate("2014-09-01");
				topic.setEndDate("2014-12-31");
				topic.setPercent(1);
				request.setTopic(topic);
				
				DCCreateTopicResponse response = client.createConsistentTopic(request);
				DCConsistentTopic topic2 = response.getTopic();
				System.out.println(topic2.getTopicId());
				
			}
			
		} catch (LightningServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
		if (transport != null) {
			transport.close();
		}
	}

}
