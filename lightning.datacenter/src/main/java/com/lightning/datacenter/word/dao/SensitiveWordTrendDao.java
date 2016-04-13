package com.lightning.datacenter.word.dao;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.lightning.datacenter.word.model.SensitiveWordTrendDO;

public class SensitiveWordTrendDao extends SqlMapClientDaoSupport {

	public void addSensitiveWordTrend(SensitiveWordTrendDO trendDO) {
		getSqlMapClientTemplate().insert("SensitiveWordTrend.addWordTrend",
				trendDO);
	}

	public boolean existSensitiveWordTrend(String word, int type,
			String timestamp) {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("word", word);
		params.put("type", type);
		params.put("timestamp", timestamp);
		Integer value = (Integer) getSqlMapClientTemplate().queryForObject(
				"SensitiveWordTrend.existWordTrend", params);
		return value != null;
	}

	public void updateSensitiveWordTrend(String word, int type,
			String timestamp, int delta) {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("word", word);
		params.put("type", type);
		params.put("timestamp", timestamp);
		params.put("delta", delta);
		getSqlMapClientTemplate().update("SensitiveWordTrend.updateWordTrend",
				params);
	}

	@SuppressWarnings("unchecked")
	public List<SensitiveWordTrendDO> querySensitiveWordTrends(String word,
			int type, String start, String end) {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("word", word);
		params.put("type", type);
		params.put("start", start);
		params.put("end", end);
		return (List<SensitiveWordTrendDO>) getSqlMapClientTemplate()
				.queryForList("SensitiveWordTrend.queryWordTrends", params);
	}

}
