package com.zhitianweilai.qing.template.engine;

public class SimpleTagMatchConditionParserImpl implements
		ITagMatchConditionParser {

	@Override
	public TagMatchCondition parse(String context) {
		// TODO Auto-generated method stub
		
		TagMatchCondition condition = new TagMatchCondition();
		
		TokenSource source = new TokenSource(context);
		
		try {
		
			leftBracket(source);
			String ttag = tagName(source);
			TagType tagType = TagType.match(ttag);
			if ( tagType == TagType.HTML_TAG_NONE ) {
				return null;
			}
			condition.setTagType(tagType);
			
			while ( true ) {
				String tkey = null;
				String tvalue = null;
				int oldindex = source.start;
				try {
					tkey = key(source);
				} catch( Exception e ) {
					source.start = oldindex;
					break;
				}
 				equality(source);
				tvalue = value(source);
				
				condition.addAttribute(tkey, tvalue);
			}
			
			rightBracket(source);
			
			return condition;
			
		} catch (Exception e) {
	
		}
		
		return null;
	}
	
	public void leftBracket(TokenSource source) {
		String token = nextToken(source);
		if ( token == null || !"<".equalsIgnoreCase(token) ) {
			throw new RuntimeException("illegal token");
		}
	}
	
	public void rightBracket(TokenSource source) {
		String token = nextToken(source);
		if ( token == null || !">".equalsIgnoreCase(token) ) {
			throw new RuntimeException("illegal token");
		}
	}
	
	public String tagName(TokenSource source) {
		String token = nextToken(source);
		if ( token == null || !isValidWord(token) ) {
			throw new RuntimeException("illegal token");
		}
		return token;
	}
	
	public String key(TokenSource source) {
		String token = nextToken(source);
		if ( token == null || !isValidWord(token) ) {
			throw new RuntimeException("illegal token");
		}
		return token;
	}
	
	public void equality(TokenSource source) {
		String token = nextToken(source);
		if ( token == null || !"=".equalsIgnoreCase(token) ) {
			throw new RuntimeException("illegal token");
		}
	}
	
	public String value(TokenSource source) {
		String token = nextToken(source);
		if ( token == null ) {
			throw new RuntimeException("illegal token");
		}
		return token;
	}
	
	private boolean isValidWord(String word) {
		if ( word.length() == 0 ) {
			return false;
		}
		for ( int i = 0; i < word.length(); i++ ) {
			char ch = word.charAt(i);
			if ( (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') 
					|| (ch >= '0' && ch <= '9') || (ch == '_') ) {
			} else {
				return false;
			}
		}
		return true;
	}
	
	
	
	public String nextToken(TokenSource source) {
		
		int state = 0;				// 0:  none, 1: "" 2: word
		int backslash = 0;			//
		int strstart = 0, strend = 0;
		
		String content = source.content;
		for ( int i = source.start; i < content.length(); i++ ) {
			char ch = source.content.charAt(i);
			
			if ( state == 1 ) {
				if ( backslash == 0 ) {
					if ( ch == '\\' ) {
						backslash = 1;
					} else if ( ch == '\"' || ch == '\'' ) {
						source.start = i + 1;
						return content.substring(strstart, i);
					}
				} else if ( backslash == 1 ) {
					backslash = 0;
				}
			} else if ( state == 0 ) {
				if ( ch == ' ' || ch == '\r' || ch == '\n' || ch == '\t' ) {
					continue;
				}
				if ( ch == '\"' || ch == '\'' ) {
					state = 1;
					strstart = i + 1;
				} else if ( ch == '=' ) {
					source.start = i + 1;
					return "=";
				} else if ( ch == '<' ) {
					source.start = i + 1;
					return "<";
				} else if ( ch == '>' ) {
					source.start = i + 1;
					return ">";
				} else if ( ch == '/') {
					
				} else {
					if ( (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') 
							|| (ch >= '0' && ch <= '9') || (ch == '_') ) {
						state = 2;
						strstart = i;
					} else {
						return "ILLEGAL";
					}
				}
			} else if ( state == 2 ) {
				if ( (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') 
						|| (ch >= '0' && ch <= '9') || (ch == '_') ) {
				} else {
					source.start = i;
					return content.substring(strstart, i);
				}
			}
		}
		if ( state == 2 ) {
			source.start = content.length();
			return content.substring(strstart);
		}
		return "EOF";
	}
	
	static class TokenSource {
		public String content;
		public int start = 0;
		public TokenSource(String content) {
			super();
			this.content = content;
			this.start = 0;
		}
	}
	
	public static void main(String[] args) {
		

	}
	
}
