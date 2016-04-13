package com.lightning.datacenter.word.dao;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.lightning.datacenter.word.model.SensitiveWordDO;

public class SensitiveWordDao extends SqlMapClientDaoSupport {

	/**
	 * 
	 * @param wordDO
	 */
	public void addSenstiveWord(SensitiveWordDO wordDO) {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("word", wordDO.getWord());
		params.put("type", wordDO.getType());
		getSqlMapClientTemplate().insert("SensitiveWord.addWord", params);
	}
	
	/**
	 * 
	 * @param word
	 * @param type
	 * @return
	 */
	public boolean existSensitiveWord(String word, int type) {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("word", word);
		params.put("type", type);
		SensitiveWordDO wordDO =(SensitiveWordDO)getSqlMapClientTemplate().queryForObject("SensitiveWord.existWord", params);
		return wordDO != null;
	}
	
	/**
	 * 
	 * @param word
	 * @param type
	 */
	public void removeSensitiveWord(String word, int type) {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("word", word);
		params.put("type", type);
		getSqlMapClientTemplate().update("SensitiveWord.removeWord", params);
	}

	/**
	 * 
	 * @param type
	 * @param offset
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SensitiveWordDO> querySensitiveWords(int type, int offset, int limit) {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("type", type);
		params.put("offset", offset);
		params.put("limit", limit);
		return (List<SensitiveWordDO>)getSqlMapClientTemplate().queryForList("SensitiveWord.queryWordList", params);
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SensitiveWordDO> queryAllSensitiveWords(int type) {
		return (List<SensitiveWordDO>) getSqlMapClientTemplate().queryForList(
				"SensitiveWord.queryAllWordList", type);
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public int countSensitveWord(int type) {
		Integer value = (Integer)getSqlMapClientTemplate().queryForObject("SensitiveWord.countWord", type);
		if ( value == null ) {
			return 0;
		}
		return value.intValue();
	}
	
}
