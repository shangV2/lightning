package com.zhitianweilai.qing.crawler.executor;

import org.junit.Test;

import com.zhitianweilai.qing.crawler.task.WebCrawlerTask;

public class CrawlerExecutorTest {

	@Test
	public void testTianShan() {

		WebCrawlerTask task = new WebCrawlerTask();

		task.setHostExpression("[a-z]*\\.ts\\.cn");
		task.setNavPageExpression("");
		task.setConPageExpression("content_(\\d)+.htm$");

		//
		task.setLanguage(0);
		task.setWebsite("tianshan");
		task.setSeedUrl("http://www.ts.cn/");
		task.setPageMaxLimit(1000);

		task.setTitleExpression("<div class=\"title\" />");
		task.setContentExpression("<div class=\"txt link05\"> <p/> <div is_filter='yes'/></div>");
		task.setTimeExpression("<div class=\"info link16\"><span is_filter='yes'/></div>");
		
		//
		CrawlerExecutor executor = new CrawlerExecutor();
		executor.execute(task);

	}

	// ----------------------------------------------------------------------
	
	@Test
	public void testTibet() {

		WebCrawlerTask task = new WebCrawlerTask();

		task.setHostExpression("tb\\.tibet\\.cn");
		task.setNavPageExpression("2010[a-zA-Z]+\\\\$");
		task.setConPageExpression("t(\\d)+_(\\d)+.htm(l)?$");

		//
		task.setLanguage(0);
		task.setWebsite("tibetancn");
		task.setSeedUrl("http://tb.tibet.cn/");
		task.setPageMaxLimit(1000);

		task.setTitleExpression("<td class=\"title\" />  <td align=\"center\" class=\"lan_20\" />");
		task.setContentExpression("<td class=\"content\" />  <p class=\"MsoNormal\" > <span lang=\"BO\" /> </p>");
		task.setTimeExpression("<td align=\"center\" class=\"content\" height=\"20\" ></td>");

		//
		CrawlerExecutor executor = new CrawlerExecutor();
		executor.execute(task);
		
	}

	@Test
	public void testUyghur() {
		
		WebCrawlerTask task = new WebCrawlerTask();

		task.setHostExpression("uy\\.ts\\.cn");
		task.setNavPageExpression("node_(\\d)+.htm$");
		task.setConPageExpression("content_(\\d)+.htm$");

		//
		task.setLanguage(0);
		task.setWebsite("tianshan_uy");
		task.setSeedUrl("http://uy.ts.cn/");
		task.setPageMaxLimit(1000);

		task.setTitleExpression("<div class=\"content\"> <h2/> </div>");
		task.setContentExpression("<div id=\"content_value\" > <p/> </div>");
		task.setTimeExpression("<div class=\"times\"> <td /> </div>");
		
		//
		CrawlerExecutor executor = new CrawlerExecutor();
		executor.execute(task);
		
		
	}
	
	
	// ----------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
}


//      /td[@class="title"]/

