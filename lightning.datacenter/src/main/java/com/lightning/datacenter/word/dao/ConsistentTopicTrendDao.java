package com.lightning.datacenter.word.dao;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.lightning.datacenter.word.model.ConsistentTopicTrendDO;

public class ConsistentTopicTrendDao extends SqlMapClientDaoSupport {

	// -------------------------------------------------------------------------
	public void addConsistentTrendTopic(ConsistentTopicTrendDO trendDO) {
		getSqlMapClientTemplate().insert("ConsistentTopicTrend.addTrend", trendDO);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConsistentTopicTrendDO> queryConsistentTopicTrends(long topicId, String startDate, String endDate) {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("topicId", topicId);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return (List<ConsistentTopicTrendDO>)getSqlMapClientTemplate().queryForList("ConsistentTopicTrend.queryTrends", params);
	}
	
}
