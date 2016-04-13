package com.qing.index.model.dataobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WebPageResultDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5931957490607746357L;

	private int totalNumber;
	
	private List<WebPageDO> webPages = new ArrayList<WebPageDO>();

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	public List<WebPageDO> getWebPages() {
		return webPages;
	}

	public void setWebPages(List<WebPageDO> webPages) {
		this.webPages = webPages;
	}
	
}
