package com.zhitianweilai.qing.template.engine;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.ScriptTag;
import org.htmlparser.tags.StyleTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.NodeVisitor;

import com.zhitianweilai.qing.crawler.system.IHtmlTemplateExtractor;
import com.zhitianweilai.qing.crawler.system.TemplatePage;
import com.zhitianweilai.qing.utils.HtmlContentHelper;

public class HPHtmlTemplateExtractor implements IHtmlTemplateExtractor {
	
	private HPTagMatchEngine engine = null;

	@Override
	public TemplatePage extract(String htmlContent) {
		// TODO Auto-generated method stub
		try {
			
			StringBuilder sb = new StringBuilder();
			Parser parser = new Parser(htmlContent);
			MyNodeVisitor visitor = new MyNodeVisitor(true, true);
			parser.visitAllNodesWith(visitor);
			
			String content = visitor.getContent();
			if ( content == null || content.length() == 0 ) {
				return new TemplatePage("", "");
			}
			
			content = HtmlContentHelper.escape(content);	
			
			String title = visitor.getTitle();
			if ( title == null ) {
				title = "";
			} else {
				title = HtmlContentHelper.escape(title);
			}
			
			return new TemplatePage(title, content);
			
		} catch(ParserException e) {
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
			if ( engine.isPageContent(tag) ) {
				String tcontent = extractText(tag, 0);
				if ( tcontent == null ) {
					return;
				}
				if ( content == null || content.length() < tcontent.length() ) {
					content = tcontent; 
				}
			} else if ( engine.isPageTitle(tag) ) {
				String ttitle = extractText(tag, 0);
				if ( ttitle == null ) {
					return;
				}
				if ( title == null || title.length() < ttitle.length() ) {
					title = ttitle;
				}
			}
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

		private String title;
		
		private String content;
		
	}

	public HPTagMatchEngine getEngine() {
		return engine;
	}

	public void setEngine(HPTagMatchEngine engine) {
		this.engine = engine;
	};
	

}
