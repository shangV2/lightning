package com.lightning.datacenter.word.dao;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.lightning.datacenter.word.model.ConsistentTopicDO;

public class ConsistentTopicDao extends SqlMapClientDaoSupport {

	public void addConsistentTopic(ConsistentTopicDO topicDO) {
		getSqlMapClientTemplate().insert("ConsistentTopic.addTopic", topicDO);
	}
	
	public void removeConsistentTopic(long topicId) {
		getSqlMapClientTemplate().delete("ConsistentTopic.removeTopic", topicId);
	}
	
	public ConsistentTopicDO queryConsistentTopicByTopicId(long topicId) {
		return (ConsistentTopicDO)getSqlMapClientTemplate().queryForObject("ConsistentTopic.queryTopicByTopicId", topicId);
	}
	
	public boolean existConsistentTopic(long topicId) {
		Long rtopicId = (Long)getSqlMapClientTemplate().queryForObject("ConsistentTopic.existTopic", topicId);
		return rtopicId != null && rtopicId == topicId;
	}
	
	@SuppressWarnings("unchecked")
	public List<ConsistentTopicDO> listConsistentTopics(int type, int offset, int limit) {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("type", type);
		params.put("offset", offset);
		params.put("limit", limit);
		return (List<ConsistentTopicDO>)getSqlMapClientTemplate().queryForList("ConsistentTopic.listTopics", params);
	}
	
}
