package com.lightning.datacenter.word.dao;

import java.util.List;




import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lightning.datacenter.word.model.SensitiveWordDO;

public class SensitiveWordDaoTest {

	private Logger logger = Logger.getLogger(SensitiveWordDaoTest.class);
	
	private static FileSystemXmlApplicationContext applicationContext = null;
	
	@BeforeClass
	public static void setUpClass() {
		applicationContext = new FileSystemXmlApplicationContext(
				"conf/application_context.xml");
	}
	
	@Test
	public void testAddSensitiveWord() {
//		fail("Not yet implemented");
		
		SensitiveWordDao dao = (SensitiveWordDao) applicationContext.getBean("sensitiveWordDao");
		
		SensitiveWordDO wordDO = new SensitiveWordDO();
		wordDO.setType(0);
		wordDO.setWord("新疆");
		dao.addSenstiveWord(wordDO);
		
	}

	@Test
	public void testQuerySensitiveWordList() {
		
		SensitiveWordDao dao = (SensitiveWordDao) applicationContext.getBean("sensitiveWordDao");
		List<SensitiveWordDO> wordDOs = dao.querySensitiveWords(0, 0, 10);
		for ( SensitiveWordDO wordDO : wordDOs ) {
			System.out.println(wordDO.getWord()+":"+wordDO.getType());
		}
		
	}
	
	@Test
	public void testExistSensitiveWord() {
		
		SensitiveWordDao dao = (SensitiveWordDao) applicationContext.getBean("sensitiveWordDao");
		boolean result = dao.existSensitiveWord("helloword", 0);
		Assert.assertEquals(true, result);
		
		result = dao.existSensitiveWord("上海", 0);
		Assert.assertEquals(false, result);
		
	}
	
	@Test
	public void testRemoveSensitiveWord() {
		SensitiveWordDao dao = (SensitiveWordDao) applicationContext.getBean("sensitiveWordDao");
		dao.removeSensitiveWord("新疆", 0);
	}
	
	
}
