package com.zhitianweilai.qing.template.engine;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ASTMatchNodeParser {

	public static final String FILTER_MARK = "is_filter";
	
	public ORASTMatchNode parse(String xml) {
		
		ORASTMatchNode result = new ORASTMatchNode();
		
		xml = "<fengqingxue> " + xml + " </fengqingxue>";
		
		ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes());
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(bais);
			Element element = document.getDocumentElement();
			
			NodeList nodelist = element.getChildNodes();
			for ( int i = 0; i < nodelist.getLength(); i++ ) {
				Node curnode = nodelist.item(i);
				if ( curnode.getNodeType() == 3 || "#text".equalsIgnoreCase(curnode.getNodeName()) ) {
					// ignore it
				} else {
					ASTMatchNode node = explain(curnode);
					result.getChildrenNodes().add(node);
				}
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;

	}
	
	private ASTMatchNode explain(Node curnode) {
		
		ASTMatchNode astMatchNode = new ASTMatchNode();
		String tagName = curnode.getNodeName().trim();
		TagMatchCondition condition = new TagMatchCondition();
		astMatchNode.setCondition(condition);
		
		condition.setTagType(TagType.match(tagName.toLowerCase()));
		Map<String, String> params = new TreeMap<String, String>();
		condition.setAttributes(params);
		
		NamedNodeMap nnmap = curnode.getAttributes();
		if ( nnmap != null ) {
			for ( int i = 0; i < nnmap.getLength(); i++ ) {
				Node p = nnmap.item(i);
				params.put(p.getNodeName(), p.getNodeValue());
			}
		}
		
		if ( condition.getAttributes().containsKey(FILTER_MARK) ) {
			astMatchNode.setNodeType(ASTMatchNodeType.AST_MATCH_NODE_LEAF);
			return astMatchNode;
		}
		
		NodeList childrens = curnode.getChildNodes();
		for ( int i = 0; i < childrens.getLength(); i++ ) {
			Node childnode = childrens.item(i);
			if ( childnode.getNodeType() == 3 || childnode.getNodeName().equalsIgnoreCase("#text") ) {
				continue;
			}
			ASTMatchNode astChildNode = explain(childnode);
			TagMatchCondition childCondition = astChildNode.getCondition();
			if ( childCondition.getAttributes().containsKey(FILTER_MARK) ) {
				astMatchNode.getFilterConditions().add(childCondition);
			} else {
				astMatchNode.getChildrenNodes().add(astChildNode);
			}
		}
		
		if ( astMatchNode.getChildrenNodes().size() == 0 ) {
			astMatchNode.setNodeType(ASTMatchNodeType.AST_MATCH_NODE_LEAF);
		} else {
			astMatchNode.setNodeType(ASTMatchNodeType.AST_MATCH_NODE_NON_LEAF);	
		}
		
		return astMatchNode;
		
	}

	public static void main(String[] args) {
		
		String xml = "<div name=\"wahaha\"> <p/> <table> <td> <p/> <p is_filter=\"true\" /> </td> </table> </div>";
		ASTMatchNodeParser parser = new ASTMatchNodeParser();
		parser.parse(xml);
		
	}
	
	/**
	 * 
	 */
	
	
	
}
