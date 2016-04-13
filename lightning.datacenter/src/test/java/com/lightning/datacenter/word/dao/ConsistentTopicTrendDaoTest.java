package com.lightning.datacenter.word.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lightning.datacenter.word.model.ConsistentTopicTrendDO;

public class ConsistentTopicTrendDaoTest {

	private Logger logger = Logger.getLogger(SensitiveWordDaoTest.class);

	private static FileSystemXmlApplicationContext applicationContext = null;

	@BeforeClass
	public static void setUpClass() {
		applicationContext = new FileSystemXmlApplicationContext(
				"conf/application_context.xml");
	}

	@Test
	public void test() {
		// fail("Not yet implemented");
	}

	@Test
	public void testAddTrend() {
		
		ConsistentTopicTrendDao trendDao = (ConsistentTopicTrendDao)applicationContext.getBean("consistentTopicTrendDao");
		
		ConsistentTopicTrendDO trendDO = new ConsistentTopicTrendDO();
		trendDO.setTimestamp("2014-02-01");
		trendDO.setTopicId(2147462497L);
		trendDO.setValue(3);
		trendDao.addConsistentTrendTopic(trendDO);
	
	}
	
	@Test
	public void testQueryTrends() {
		
		ConsistentTopicTrendDao trendDao = (ConsistentTopicTrendDao)applicationContext.getBean("consistentTopicTrendDao");
		
		List<ConsistentTopicTrendDO> trendDOs = trendDao.queryConsistentTopicTrends(1000L, "2014-02-01", "2014-02-30");
		
		for ( ConsistentTopicTrendDO trendDO : trendDOs ) {
			System.out.println(trendDO);
		}
		
	}
	

}
