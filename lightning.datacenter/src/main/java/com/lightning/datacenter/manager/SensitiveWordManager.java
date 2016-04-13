package com.lightning.datacenter.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import com.lightning.datacenter.model.IMResult;
import com.lightning.datacenter.model.SensitiveWordTrendVO;
import com.lightning.datacenter.model.SensitiveWordVO;
import com.lightning.datacenter.model.TimeWordInfoVO;
import com.lightning.datacenter.model.TimeWordTrendVO;
import com.lightning.datacenter.utils.TimestampEnumeration;
import com.lightning.datacenter.word.dao.SensitiveWordDao;
import com.lightning.datacenter.word.dao.SensitiveWordTrendDao;
import com.lightning.datacenter.word.model.SensitiveWordDO;
import com.lightning.datacenter.word.model.SensitiveWordTrendDO;

public class SensitiveWordManager {

	@Resource
	private SensitiveWordDao sensitiveWordDao;
	
	@Resource 
	private SensitiveWordTrendDao sensitiveWordTrendDao;

	public void addWord(String word, int type) {
		if ( !existWord(word, type) ) {
			insertWord(word, type);
		}
	}

	private void insertWord(String word, int type) {
		SensitiveWordDO sensitiveWordDO = new SensitiveWordDO();
		sensitiveWordDO.setWord(word);
		sensitiveWordDO.setType(type);
		sensitiveWordDao.addSenstiveWord(sensitiveWordDO);
	}
	
	public boolean existWord(String word, int type) {
		return sensitiveWordDao.existSensitiveWord(word, type);
	}
	
	public void removeWord(String word, int type) {
		sensitiveWordDao.removeSensitiveWord(word, type);
	}

	public List<SensitiveWordVO> querySensitiveWord(int type, int offset,
			int limit) {
		List<SensitiveWordVO> result = new ArrayList<SensitiveWordVO>();
		List<SensitiveWordDO> wordDOs = sensitiveWordDao.querySensitiveWords(type,
				offset, limit);
		for (SensitiveWordDO wordDO : wordDOs) {
			SensitiveWordVO vo = new SensitiveWordVO();
			vo.setWord(wordDO.getWord());
			vo.setType(wordDO.getType());
			result.add(vo);
		}
		return result;
	}

	public IMResult<List<SensitiveWordVO>> queryAllSensitiveWord(int type) {
		IMResult<List<SensitiveWordVO>> result = new IMResult<List<SensitiveWordVO>>();
		result.setSuccess(false);
		result.setValue(new ArrayList<SensitiveWordVO>());
		
		List<SensitiveWordDO> wordDOs = sensitiveWordDao.queryAllSensitiveWords(type);
		for (SensitiveWordDO wordDO : wordDOs) {
			SensitiveWordVO vo = new SensitiveWordVO();
			vo.setWord(wordDO.getWord());
			vo.setType(wordDO.getType());
			result.getValue().add(vo);
		}
		result.setSuccess(true);
		
		return result;
	}
	
	// -------------------------------------------------------------------------------------
	
	public IMResult<Void> addSensitiveTrend(SensitiveWordTrendVO trendVO) {
		
		IMResult<Void> result = new IMResult<Void>();
		result.setSuccess(false);
		
		try {
			SensitiveWordTrendDO trendDO = new SensitiveWordTrendDO();
			trendDO.setWord(trendVO.getWord());
			trendDO.setType(trendVO.getType());
			trendDO.setTimestamp(trendVO.getTimestamp());
			trendDO.setValue(trendVO.getValue());
			sensitiveWordTrendDao.addSensitiveWordTrend(trendDO);
			result.setSuccess(true);
		} catch(Throwable e) {
			result.setSuccess(false);
		}
		
		return result;
		
	}
	
	public IMResult<Void> updateSensitiveTrend(String word, int type, String timestamp, int delta) {
		IMResult<Void> result = new IMResult<Void>();
		result.setSuccess(false);
		try {
			boolean existFlag = sensitiveWordTrendDao.existSensitiveWordTrend(word, type, timestamp);
			if ( existFlag == true ) {
				sensitiveWordTrendDao.updateSensitiveWordTrend(word, type, timestamp, delta);
			} else {
				SensitiveWordTrendDO trendDO = new SensitiveWordTrendDO();
				trendDO.setWord(word);
				trendDO.setType(type);
				trendDO.setTimestamp(timestamp);
				trendDO.setValue(delta);
				sensitiveWordTrendDao.addSensitiveWordTrend(trendDO);
			}
		} catch(Throwable e) {
			result.setSuccess(false);
		}
		return result;
	}
	
	
	public IMResult<List<SensitiveWordTrendVO>> listSensitiveWordTrend(String word, int type, String startDate, String endDate) {
		
		IMResult<List<SensitiveWordTrendVO>> result = new IMResult<List<SensitiveWordTrendVO>>();
		result.setSuccess(false);
		result.setValue(new ArrayList<SensitiveWordTrendVO>());
		
		try {
			List<SensitiveWordTrendDO> trendDOs = sensitiveWordTrendDao.querySensitiveWordTrends(word, type, startDate, endDate);
			
			List<SensitiveWordTrendVO> trendVOs = new ArrayList<SensitiveWordTrendVO>();
			
			for ( SensitiveWordTrendDO trendDO : trendDOs ) {
				SensitiveWordTrendVO trendVO = new SensitiveWordTrendVO();
				trendVO.setWord(trendDO.getWord());
				trendVO.setType(trendDO.getType());
				trendVO.setTimestamp(trendDO.getTimestamp());
				trendVO.setValue(trendDO.getValue());
				
//				result.getValue().add(trendVO);
				trendVOs.add(trendVO);
			}
			Collections.sort(trendVOs);
			
			//  --------------------------------------------
			TimestampEnumeration enumeration = new TimestampEnumeration(startDate, endDate);
			int index = 0;
			while ( enumeration.hasNext() ) {
				String curnow = enumeration.next();
				
				SensitiveWordTrendVO vo = new SensitiveWordTrendVO();
				vo.setTimestamp(curnow);
				vo.setType(type);
				vo.setValue(0);
				while ( index < trendVOs.size() ) {
					SensitiveWordTrendVO trend = trendVOs.get(index);
					int sign = curnow.compareTo(trend.getTimestamp());
					if ( sign < 0 ) {
						vo.setValue(0);
						break;
					} else if ( sign > 0 ) {
						index++;
					} else {
						vo.setValue(trend.getValue());
						index++;
						break;
					}
				}
				result.getValue().add(vo);
			}
			result.setSuccess(true);
		} catch (Throwable e) {
			result.setSuccess(false);
		}
		
		return result;
	}
	
	
	public IMResult<List<TimeWordTrendVO>> listSensitiveWordListTrend(int type, int pageno,
			int pagesize, String startDate, String endDate) {
		
		IMResult<List<TimeWordTrendVO>> result = new IMResult<List<TimeWordTrendVO>>();
		result.setSuccess(false);
		
		List<TimeWordTrendVO> timeWordTrendVOs = new ArrayList<TimeWordTrendVO>();
		TimestampEnumeration enumeration = new TimestampEnumeration(startDate, endDate);
		while ( enumeration.hasNext() ) {
			String curnow = enumeration.next();
			TimeWordTrendVO trendVO = new TimeWordTrendVO();
			trendVO.setTimestamp(curnow);
			trendVO.setWordInfos(new ArrayList<TimeWordInfoVO>());
			timeWordTrendVOs.add(trendVO);
		}
		
		try {
			List<SensitiveWordDO>  sensitiveWordDOs = sensitiveWordDao.querySensitiveWords(type, pageno * pagesize, pagesize);
			for ( SensitiveWordDO wordDO : sensitiveWordDOs ) {
				IMResult<List<SensitiveWordTrendVO>> rc = listSensitiveWordTrend(wordDO.getWord(), type, startDate, endDate);
				if ( rc.isSuccess() ) {
					List<SensitiveWordTrendVO> trendVOs = rc.getValue();
					if ( trendVOs.size() == timeWordTrendVOs.size() ) {
						for ( int i = 0; i < trendVOs.size(); i++ ) {
							TimeWordTrendVO twtvo = timeWordTrendVOs.get(i);
							twtvo.getWordInfos().add(new TimeWordInfoVO(wordDO.getWord(), trendVOs.get(i).getValue()));
						}
					}
				} else {
					// TODO
				}
			}
			result.setSuccess(true);
			result.setValue(timeWordTrendVOs);
			
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	
}
