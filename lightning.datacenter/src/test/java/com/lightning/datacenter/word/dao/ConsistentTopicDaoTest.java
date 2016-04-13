package com.lightning.datacenter.word.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lightning.datacenter.word.model.ConsistentTopicDO;

public class ConsistentTopicDaoTest {

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
	public void testAddConsistentTopic() {
		ConsistentTopicDO topicDO = new ConsistentTopicDO();
		topicDO.setTopicId(1000L);
		topicDO.setTopicName("name");
		topicDO.setStartDate("2014-01-20");
		topicDO.setEndDate("2014-01-25");
		topicDO.setType(0);
		topicDO.setWords("[\"test\", \"world\"]");
		topicDO.setPercent(10);
		
		ConsistentTopicDao topicDao = (ConsistentTopicDao)applicationContext.getBean("consistentTopicDao");
		topicDao.addConsistentTopic(topicDO);
	}
	
	@Test
	public void testListConsistentTopics() {
		ConsistentTopicDao topicDao = (ConsistentTopicDao)applicationContext.getBean("consistentTopicDao");
		List<ConsistentTopicDO> topics = topicDao.listConsistentTopics(0, 0, 10);
		for ( ConsistentTopicDO topic : topics ) {
			System.out.println(topic);
		}
	}
	
	@Test
	public void testExistTopic() {
		long topicId = 1000L;
		ConsistentTopicDao topicDao = (ConsistentTopicDao)applicationContext.getBean("consistentTopicDao");
		boolean result = topicDao.existConsistentTopic(topicId);
		Assert.assertEquals(true, result);
		
		long topicId2 = 1001L;
		boolean result2 = topicDao.existConsistentTopic(topicId2);
		Assert.assertEquals(false, result2);
	}
	
	
}



