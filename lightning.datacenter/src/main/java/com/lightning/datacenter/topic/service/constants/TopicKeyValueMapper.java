package com.lightning.datacenter.topic.service.constants;


/**
topic:${topic_id}:${timestamp}:doc:${docid}
topic:${topic_id}:${timestamp}:num
 */


public class TopicKeyValueMapper {
	
	
	public static String toDateTrendWithTimeKey(long topicid, String timestamp) {
		return String.format("topic:%d:%s:num", topicid, timestamp);
	}
	
	public static String toTrendArticleWithTimeKey(long topicid, String timestamp) {
		return String.format("topic:%d:%s:doc", topicid, timestamp);	
	}
	
	
}
