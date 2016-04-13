package com.lightning.crawler.msgstore.impl;

import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.TreeMap;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import com.google.protobuf.ByteString;
import com.lightning.crawler.msgstore.IMessageStore;
import com.lightning.crawler.msgstore.MStoreConfig;
import com.lightning.crawler.persistent.IWebPageItemPersistent;
import com.lightning.web.proto.WebPageProto.WebPageMessage;
import com.zhitianweilai.qing.crawler.executor.model.WebPageItem;

public class KafkaMessageStore implements IMessageStore {

	private Producer<String, byte[]> producer = null;
	
	private Random random = new Random();
	
	private String topicName = "";
	
	private Map<String, String> propertiesMap = new TreeMap<String, String>();
	
	public void init() {
		
		this.topicName = propertiesMap.get("topic");

		Properties props = new Properties();
		props.put("metadata.broker.list", propertiesMap.get("server_list"));
		this.initialize(props);
		
	}
	
	@Override
	public void initialize(Properties props) {
//		Properties props = new Properties();
//		// 配置metadata.broker.list, 为了高可用, 最好配两个broker实例
//		props.put("metadata.broker.list", "127.0.0.1:9092");
//		// serializer.class为消息的序列化类
//		props.put("serializer.class", "kafka.serializer.StringEncoder");
//		// 设置Partition类, 对队列进行合理的划分
//		props.put("partitioner.class", "mmxf.kafka.practise.SimplePartitioner");
//		// ACK机制, 消息发送需要kafka服务端确认
//		props.put("request.required.acks", "1");
		
		props.put("serializer.class", "kafka.serializer.DefaultEncoder");
		props.put("key.serializer.class", "kafka.serializer.StringEncoder");
//		props.put("serializer.class", "kafka.serializer.StringEncoder");
		// 设置Partition类, 对队列进行合理的划分
		props.put("partitioner.class", "com.lightning.crawler.msgstore.impl.SimplePartitioner");
		// ACK机制, 消息发送需要kafka服务端确认
		props.put("request.required.acks", "1");
		
		ProducerConfig config = new ProducerConfig(props);
		producer = new Producer<String, byte[]>(config);
	}
	
	public void store(byte[] msgdata) {
		
		int keyValue = random.nextInt();
		// KeyedMessage<K, V>
		// 			K对应Partition Key的类型
		//				V对应消息本身的类型
		try {
			KeyedMessage<String, byte[]> message = new KeyedMessage<String, byte[]>(
					this.topicName, String.valueOf(keyValue), msgdata);
			producer.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void close() {
		producer.close();
	}
	
	public Map<String, String> getPropertiesMap() {
		return propertiesMap;
	}

	public void setPropertiesMap(Map<String, String> propertiesMap) {
		this.propertiesMap = propertiesMap;
	}

	
	@Override
	public IWebPageItemPersistent createPersistent(MStoreConfig config) {
		return new IWebPageItemPersistent() {
			@Override
			public void persist(WebPageItem item) {
				WebPageMessage.Builder builder = WebPageMessage.newBuilder();
				builder.setUrl(item.getUrl());
				builder.setTimestamp(item.getTimestamp());
				builder.setSource(ByteString.copyFromUtf8(item.getSource()));
				builder.setTitle(ByteString.copyFromUtf8(item.getTitle()));
				builder.setContent(ByteString.copyFromUtf8(item.getContent()));
				KafkaMessageStore.this.store(builder.build().toByteArray());
			}
			
			@Override
			public void initialize(Properties props) {
			}
			
			@Override
			public void cleanup() {
			}
		};
	}
		
}
