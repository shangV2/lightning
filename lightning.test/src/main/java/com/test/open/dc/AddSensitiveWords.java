package com.test.open.dc;

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
import com.lighting.rpc.datacenter.service.DataCenterService;

public class AddSensitiveWords {

	
	public static void main(String[] args) {
		
//		args = new String[] {
//			"110.249.221.251", "8020"	
//		};
		
		if ( args.length != 2 ) {
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

		String[] words = new String[] { "伊扎布特", "伊吉拉特", "圣战", "异教徒", "叛教者",
				"异教徒", "主命", "宗教至上", "圣战", "推翻政府", "讲经", "修道班", "神学班", "麦西来甫",
				"台比力克", "朝觐", "宗教课税", "吉里巴甫", "尼卡", "小花帽", "大胡子", "蒙面纱",
				"母语幼儿园", "双语教育", "穆斯林", "古兰经", "东突", "突厥", "东突厥斯坦", "安全时间",
				"民族压迫", "内地新疆班", "援疆", "维吾尔小偷", "清真与非清真", "7.5", "真主", "黑大爷",
				"世维会", "热比娅", "疆独", "藏独", "民族自决", };

		for ( String word : words ) {
			DCAddSensitiveWordRequest request = new DCAddSensitiveWordRequest();
			request.setLogid(1000L);
			request.setLangugeType(DCCommonLanguge.DC_LAN_CHINESE_ZH_CN);
			request.setWord(word);
			try {
				DCAddSensitiveWordResponse response = client.addSensitiveWord(request);
			} catch (LightningServiceException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}
		}
		
		if ( transport != null ) {
			transport.close();
		}
		
	}
	
}
