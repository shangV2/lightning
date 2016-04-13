package com.zhitianweilai.qing.crawler.system.hitstrategy;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.FrameTag;
import org.htmlparser.tags.ScriptTag;
import org.htmlparser.tags.StyleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.zhitianweilai.qing.recognizer.JavaScritpRecognizer;
import com.zhitianweilai.qing.utils.HtmlContentHelper;

public class NormalHtmlExtractor implements HtmlExtractor {

	/**
	 * @brief
	 */
	@Override
	public String extract(String html) {
		// TODO Auto-generated method stub

		JavaScritpRecognizer recognizer = new JavaScritpRecognizer();
		
		StringBuilder sb = new StringBuilder();
		try {
			Parser parser = new Parser(html);
			NodeList nodes = parser
					.extractAllNodesThatMatch(new ScriptFilter());
			for (int i = 0; i < nodes.size(); i++) {
				Node node = nodes.elementAt(i);
				if ( node instanceof TextNode ) {
					Node pnode = node.getParent();
					if ( pnode instanceof ScriptTag || pnode instanceof StyleTag ) {
						// Do nothing
					} else {
						String plainText = ((TextNode)node).toPlainTextString();
						if ( !recognizer.recognize(plainText) ) {
							sb.append(plainText);
						}
					}
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
		String content = sb.toString();
		return HtmlContentHelper.escape(content);

	}

	class ScriptFilter implements NodeFilter {
		public boolean accept(Node node) {
			if (node == null) {
				return false;
			}
			if (node instanceof Tag) {
				String tag = ((Tag) node).getTagName();
				if ("iframe".equalsIgnoreCase(tag))
					return false;
			}
			if (node instanceof StyleTag) {
				return false;
			} else if (node instanceof ScriptTag) {
				return false;
			} else if (node instanceof FrameTag) {
				return false;
			}
			return true;
		}
	}

}

/*
 * 
 */

/*
	
*/

