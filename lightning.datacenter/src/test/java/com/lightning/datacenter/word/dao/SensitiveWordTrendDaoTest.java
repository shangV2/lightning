package com.lightning.datacenter.word.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lightning.datacenter.word.model.SensitiveWordTrendDO;

public class SensitiveWordTrendDaoTest {

	
	private Logger logger = Logger.getLogger(SensitiveWordDaoTest.class);
	
	private static FileSystemXmlApplicationContext applicationContext = null;
	
	@BeforeClass
	public static void setUpClass() {
		applicationContext = new FileSystemXmlApplicationContext(
				"conf/application_context.xml");
	}
	
	@Test
	public void test() {
//		fail("Not yet implemented");
	}

	@Test
	public void testAddSensitiveTrend() {
		SensitiveWordTrendDao dao = (SensitiveWordTrendDao)applicationContext.getBean("sensitiveWordTrendDao");
		
		SensitiveWordTrendDO trendDO = new SensitiveWordTrendDO();
		trendDO.setWord("新疆");
		trendDO.setType(0);
		trendDO.setTimestamp("2014-01-20");
		trendDO.setValue(2);
		dao.addSensitiveWordTrend(trendDO);
	}
	
	@Test
	public void testQuerySensitiveWordTrend() {
		
		SensitiveWordTrendDao dao = (SensitiveWordTrendDao)applicationContext.getBean("sensitiveWordTrendDao");
		
		List<SensitiveWordTrendDO> trendDOs = dao.querySensitiveWordTrends("新疆", 0, "2014-01-10", "2014-01-31");
		
		for ( SensitiveWordTrendDO trendDO : trendDOs ) {
			System.out.println(trendDO.getWord() + ":" + trendDO.getTimestamp() + ":" + trendDO.getValue());
		}
		
	}
	
	
}
