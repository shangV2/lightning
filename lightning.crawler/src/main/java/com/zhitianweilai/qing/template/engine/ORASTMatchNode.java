package com.zhitianweilai.qing.template.engine;

import java.util.ArrayList;
import java.util.List;

public class ORASTMatchNode {

	private List<ASTMatchNode> childrenNodes = new ArrayList<ASTMatchNode>();

	public List<ASTMatchNode> getChildrenNodes() {
		return childrenNodes;
	}

	public void setChildrenNodes(List<ASTMatchNode> childrenNodes) {
		this.childrenNodes = childrenNodes;
	}
	
}
