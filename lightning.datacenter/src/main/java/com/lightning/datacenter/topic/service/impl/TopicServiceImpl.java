package com.lightning.datacenter.topic.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.lightning.common.kvstore.api.ISortedKeyValueStoreEngine;
import com.lightning.datacenter.topic.service.api.ITopicService;
import com.lightning.datacenter.topic.service.model.TopicDateTrendVO;

public class TopicServiceImpl implements ITopicService {
	
	@Resource
	public ISortedKeyValueStoreEngine storeKeyValueEngine;
	
	public List<TopicDateTrendVO> dateTrends(long topicid) {
		
		return null;
	}
	
}
