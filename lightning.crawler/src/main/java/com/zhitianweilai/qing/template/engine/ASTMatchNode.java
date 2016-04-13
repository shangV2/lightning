package com.zhitianweilai.qing.template.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 */
public class ASTMatchNode {

	private ASTMatchNodeType nodeType = ASTMatchNodeType.AST_MATCH_NODE_LEAF;
	
	private TagMatchCondition condition;
	
	private List<TagMatchCondition> filterConditions = new ArrayList<TagMatchCondition>();
	
	private List<ASTMatchNode> childrenNodes = new ArrayList<ASTMatchNode>();

	public ASTMatchNodeType getNodeType() {
		return nodeType;
	}

	public void setNodeType(ASTMatchNodeType nodeType) {
		this.nodeType = nodeType;
	}

	public TagMatchCondition getCondition() {
		return condition;
	}

	public void setCondition(TagMatchCondition condition) {
		this.condition = condition;
	}

	public List<TagMatchCondition> getFilterConditions() {
		return filterConditions;
	}

	public void setFilterConditions(List<TagMatchCondition> filterConditions) {
		this.filterConditions = filterConditions;
	}

	public List<ASTMatchNode> getChildrenNodes() {
		return childrenNodes;
	}

	public void setChildrenNodes(List<ASTMatchNode> childrenNodes) {
		this.childrenNodes = childrenNodes;
	}
	
	public String toString () {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(condition.toXml(0)).append("\n");
		for ( TagMatchCondition cond : filterConditions ) {
			sb.append(cond.toXml(1)).append("</" + cond.getTagType().getValue() + ">").append("\n");
		}
		
		for ( ASTMatchNode child : childrenNodes ) {
			sb.append(child.toString());
		}
		
		sb.append("</").append(condition.getTagType().getValue()).append(">");
		return sb.toString();
	}
	
}
