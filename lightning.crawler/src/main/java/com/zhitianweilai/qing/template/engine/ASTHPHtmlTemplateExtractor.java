package com.zhitianweilai.qing.template.engine;

import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.ScriptTag;
import org.htmlparser.tags.StyleTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;
import org.htmlparser.visitors.NodeVisitor;

import com.zhitianweilai.qing.crawler.system.IHtmlTemplateExtractor;
import com.zhitianweilai.qing.crawler.system.TemplatePage;
import com.zhitianweilai.qing.utils.HtmlContentHelper;

public class ASTHPHtmlTemplateExtractor implements IHtmlTemplateExtractor {

	private ORASTMatchNode contentRootASTMatchNode = null;
	
	private ORASTMatchNode titleRootASTMatchNode = null;
	
	private ORASTMatchNode timeRootASTMatchNode = null;
	
	private DateExtractor dateExtractor = new DateExtractor();
	
	@Override
	public TemplatePage extract(String htmlContent) {
		// TODO Auto-generated method stub
		try {
			
			StringBuilder sb = new StringBuilder();
			Parser parser = new Parser();
			parser.setInputHTML(htmlContent);
			MyNodeVisitor visitor = new MyNodeVisitor(true, true);
			parser.visitAllNodesWith(visitor);
			
			String content = visitor.getContent();
			if ( content == null || content.length() == 0 ) {
				return new TemplatePage("", "");
			}
			
			content = HtmlContentHelper.escape(content);	// 
			
			String title = visitor.getTitle();
			if ( title == null ) {
				title = "";
			} else {
				title = HtmlContentHelper.escape(title);
			}
			
			String timestamp = visitor.getTimestamp();
			if ( timestamp == null ) {
				Calendar calendar = Calendar.getInstance();
				timestamp = String.format("%d-%02d-%02d", 
						calendar.get(Calendar.YEAR), 
						calendar.get(Calendar.MONTH) + 1,
						calendar.get(Calendar.DATE)
					);
			} else {
				timestamp = dateExtractor.extract(timestamp);
				if ( timestamp == null ) {
					Calendar calendar = Calendar.getInstance();
					timestamp = String.format("%d-%02d-%02d", 
							calendar.get(Calendar.YEAR), 
							calendar.get(Calendar.MONTH) + 1,
							calendar.get(Calendar.DATE)
						);
				}
			}
			return new TemplatePage(title.trim(), content.trim(), timestamp);
			
		} catch(ParserException e) {
			e.printStackTrace();
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		return new TemplatePage("", "");
		
	}
	
	
	class MyNodeVisitor extends NodeVisitor {
		
		public MyNodeVisitor(boolean arg1, boolean arg2) {
			super(arg1, arg2);
		}
		
		@Override
		public void visitTag(Tag tag) {
			// TODO Auto-generated method stub
			super.visitTag(tag);
			
			for ( ASTMatchNode curnode : contentRootASTMatchNode.getChildrenNodes() ) { 
				if ( TagMatchConditionHelper.isMatchCondition(tag, curnode.getCondition()) ) {
					String tcontent = divInto(tag, curnode, 0);
					if ( tcontent == null ) {
						return;
					}
					if ( content == null || content.length() < tcontent.length() ) {
						content = tcontent; 
					}
				}
			}
			
			for ( ASTMatchNode curnode : titleRootASTMatchNode.getChildrenNodes() ) {
				if ( TagMatchConditionHelper.isMatchCondition(tag, curnode.getCondition()) ) {
					String ttitle = divInto(tag, curnode, 0);
					if ( ttitle == null ) {
						return;
					}
					if ( title == null || title.length() < ttitle.length() ) {
						title = ttitle;
					}
				}
			}
			
			if ( timeRootASTMatchNode != null ) {
				for ( ASTMatchNode curnode : timeRootASTMatchNode.getChildrenNodes() ) {
					if ( TagMatchConditionHelper.isMatchCondition(tag, curnode.getCondition()) ) {
						String ttimestamp = divInto(tag, curnode, 0);
						if ( ttimestamp == null ) {
							continue;
						}
						
						String trytimestamp = dateExtractor.extract(ttimestamp);
						if ( trytimestamp != null ) {
							timestamp = ttimestamp;
						}
						
						/*
						if ( ttimestamp == null || ttimestamp.length() < ttimestamp.length() ) {
							timestamp = ttimestamp;
						}
						*/
					}
				}
			}

		}
		
		public String divInto(Tag tag, ASTMatchNode node, int depth) {
			if ( depth > 9 ) {
				return "";
			}
			if ( node.getNodeType() == ASTMatchNodeType.AST_MATCH_NODE_LEAF ) {
//				return extractText(tag, depth);
//				return extractText(tag, node.getFilterConditions(), depth);
				StringBuilder sb = new StringBuilder();
				NodeList nodeList = tag.getChildren();
				if ( nodeList != null ) {
					SimpleNodeIterator iter = nodeList.elements();
					while ( iter.hasMoreNodes() ) {
						Node curNode = iter.nextNode();
						sb.append(extractText(curNode, node.getFilterConditions(), depth + 1));
					}
				}
				return sb.toString();
			} else {
				StringBuilder sb = new StringBuilder();
				NodeList nodeList = tag.getChildren();
				if ( nodeList != null ) {
					SimpleNodeIterator iter = nodeList.elements();
					while ( iter.hasMoreNodes() ) {
						Node curNode = iter.nextNode();
						if ( curNode instanceof Tag ) {
							String childText = continueDivInto((Tag)curNode, node.getFilterConditions(), node.getChildrenNodes(), depth + 1);
							sb.append(childText);
						}
					}
				}
				return sb.toString();
			}
		}
		
		public String continueDivInto(Tag tag, List<TagMatchCondition> fitlers, List<ASTMatchNode> childrens, int depth) {
			if ( depth > 9 ) {
				return "";
			}
			for ( TagMatchCondition filter : fitlers ) {
				if( TagMatchConditionHelper.isMatchCondition(tag, filter) ) {
					return "";
				}
			}
			StringBuilder sb = new StringBuilder();
			for ( ASTMatchNode children : childrens ) {
				if ( TagMatchConditionHelper.isMatchCondition(tag, children.getCondition()) ) {
					if ( children.getNodeType() == ASTMatchNodeType.AST_MATCH_NODE_LEAF ) {
//						return extractText(tag, depth);
						
						NodeList nodeList = tag.getChildren();
						if ( nodeList != null ) {
							SimpleNodeIterator iter = nodeList.elements();
							while ( iter.hasMoreNodes() ) {
								Node curNode = iter.nextNode();
								sb.append(extractText(curNode, children.getFilterConditions(), depth + 1));
							}
						}
						return sb.toString();
						
					} else {
						NodeList nodeList = tag.getChildren();
						if ( nodeList != null ) {
							SimpleNodeIterator iter = nodeList.elements();
							while ( iter.hasMoreNodes() ) {
								Node curNode = iter.nextNode();
								if ( curNode instanceof Tag ) {
									String childText = continueDivInto((Tag)curNode, children.getFilterConditions(), children.getChildrenNodes(), depth + 1);
									sb.append(childText);
								}
							}
						}
					}
				}
			}
			
			NodeList nodeList = tag.getChildren();
			if ( nodeList != null ) {
				SimpleNodeIterator iter = nodeList.elements();
				while ( iter.hasMoreNodes() ) {
					Node curNode = iter.nextNode();
					if ( curNode instanceof Tag ) {
						String childText = continueDivInto((Tag)curNode, fitlers, childrens, depth + 1);
						sb.append(childText);
					}
				}
			}
			return sb.toString();
			
		}
		
		public String extractText(Node tag, int depth) {
			if ( depth > 9 ) {
				return "";
			}
			if ( tag instanceof TextNode ) {
				Node pnode = tag.getParent();
				if ( pnode instanceof ScriptTag || pnode instanceof StyleTag ) {
					return "";
				}
				return ((TextNode)tag).toPlainTextString();
			}
			StringBuilder sb = new StringBuilder();
			try {
				NodeList nodelist = tag.getChildren();
				if ( nodelist != null ) {
					NodeIterator iter = nodelist.elements();
					while ( iter.hasMoreNodes() ) {
						Node curnode = iter.nextNode();
						sb.append(extractText(curnode, depth + 1)).append(" ");
					}
				}
			} catch (ParserException e) {
				e.printStackTrace();
			}
			return sb.toString();
		}
		
		public String extractText(Node tag, List<TagMatchCondition> filters, int depth) {
			if ( depth > 9 ) {
				return "";
			}
			
			if ( tag instanceof Tag ) { 
				for ( TagMatchCondition condition : filters ) {
					if ( TagMatchConditionHelper.isMatchCondition((Tag)tag, condition) ) {
						return "";
					}
				}
			}
			
			if ( tag instanceof TextNode ) {
				Node pnode = tag.getParent();
				if ( pnode instanceof ScriptTag || pnode instanceof StyleTag ) {
					return "";
				}
				return ((TextNode)tag).toPlainTextString();
			}
			StringBuilder sb = new StringBuilder();
			try {
				NodeList nodelist = tag.getChildren();
				if ( nodelist != null ) {
					NodeIterator iter = nodelist.elements();
					while ( iter.hasMoreNodes() ) {
						Node curnode = iter.nextNode();
						sb.append(extractText(curnode, depth + 1)).append(" ");
					}
				}
			} catch (ParserException e) {
				e.printStackTrace();
			}
			return sb.toString();
		}
		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
		
		public String getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		private String title;
		
		private String content;
		
		private String timestamp;
		
	}

	private class DateExtractor {
		private Pattern pattern = Pattern.compile("(\\d)+");
		public String extract(String text) {
			Matcher matcher = pattern.matcher(text);
			int year = 0, month = 0, day = 0;
			if ( matcher.find() ) {
				year = Integer.parseInt(matcher.group());
			} else {
				return null;
			}
			if ( matcher.find() ) {
				month = Integer.parseInt(matcher.group());
			} else {
				return null;
			}
			if ( matcher.find() ) {
				day = Integer.parseInt(matcher.group());
			} else {
				return null;
			}
			return String.format("%d-%02d-%02d", year, month, day);
		}
	}
	
	public ORASTMatchNode getContentRootASTMatchNode() {
		return contentRootASTMatchNode;
	}

	public void setContentRootASTMatchNode(ORASTMatchNode contentRootASTMatchNode) {
		this.contentRootASTMatchNode = contentRootASTMatchNode;
	}

	public ORASTMatchNode getTitleRootASTMatchNode() {
		return titleRootASTMatchNode;
	}

	public void setTitleRootASTMatchNode(ORASTMatchNode titleRootASTMatchNode) {
		this.titleRootASTMatchNode = titleRootASTMatchNode;
	}

	public ORASTMatchNode getTimeRootASTMatchNode() {
		return timeRootASTMatchNode;
	}

	public void setTimeRootASTMatchNode(ORASTMatchNode timeRootASTMatchNode) {
		this.timeRootASTMatchNode = timeRootASTMatchNode;
	}
	
}
