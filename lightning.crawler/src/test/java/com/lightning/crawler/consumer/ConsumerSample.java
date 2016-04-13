package com.lightning.crawler.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import com.google.protobuf.InvalidProtocolBufferException;
import com.lightning.web.proto.WebPageProto.WebPageMessage;

public class ConsumerSample {

	public static void main(String[] args) {

		// *) 创建ConsumerConfig
		Properties props = new Properties();
		props.put("zookeeper.connect", "127.0.0.1:2181");
		props.put("group.id", "group_id");
		props.put("auto.commit.interval.ms", "1000");

		ConsumerConfig consumerConfig = new ConsumerConfig(props);
		ConsumerConnector consumer = (ConsumerConnector) Consumer.createJavaConsumerConnector(consumerConfig);
		
		String topic = "test_daily";
		int threadNum = 1;
		
		// *) 设置Topic=>Thread Num映射关系, 构建具体的流
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic,threadNum);
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		
		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
		
		ExecutorService executor = Executors.newCachedThreadPool();
		for ( final KafkaStream<byte[], byte[]> stream : streams ) {
			executor.submit(new Runnable() {
				public void run() {
					ConsumerIterator<byte[], byte[]> iter = stream.iterator();
					while ( iter.hasNext() ) {
						MessageAndMetadata<byte[] , byte[]> mam = iter.next();
//						System.out.println(
//								String.format("thread_id: %d, key: %s, value: %s",
//									Thread.currentThread().getId(),
//									new String(mam.key()),
//									new String(mam.message())
//								)
//							);

						try {
							WebPageMessage message = WebPageMessage.parseFrom(mam.message());
							
							System.out.println(message.getTitle().toStringUtf8());
							System.out.println("timestamp " + message.getTimestamp());
							System.out.println(message.getContent().toStringUtf8());
							
							System.out.println("=========================================================================");
							
						} catch (InvalidProtocolBufferException e) {
							e.printStackTrace();
						}
						
					}
				}
			});
		}

		try {
			Thread.sleep(1000 * 10 * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		consumer.shutdown();
		executor.shutdown();
		
		while ( !executor.isTerminated() ) {
			try {
				executor.awaitTermination(1, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
			}
		}
		
	}


}
