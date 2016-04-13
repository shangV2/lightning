package com.zhitianweilai.qing.template.engine;

import org.junit.Test;

public class SimpleTagMatchConditionParserImplTest {

	@Test
	public void test() {
		String[] testmsgs = {
				"<div class=\"hello world\" wahaha=\"ddd\">", 
				"<div class=\"hello world\" wahaha=\"ddd\" />",
				"<div class=\"hello world\" wahaha=\"ddd\"",
				"<div class=\"hello world\" wahaha=\"ddd\\\"dsfdad\"  daf23424='12321312'>"
		};
		
		SimpleTagMatchConditionParserImpl impl = new SimpleTagMatchConditionParserImpl();
		for ( String msg : testmsgs ) {
			SimpleTagMatchConditionParserImpl.TokenSource source = new SimpleTagMatchConditionParserImpl.TokenSource(msg);
			while ( true ) {
				String token = impl.nextToken(source);
				if ( token == null ) {
					break;
				}
				System.out.print(String.format(" #%s# ", token));
				if ( "EOF".equalsIgnoreCase(token) || "ILLEGAL".equalsIgnoreCase(token) ) {
					break;
				}
			} 
			System.out.println("---------------------------------------------------");
		}
	}

}
