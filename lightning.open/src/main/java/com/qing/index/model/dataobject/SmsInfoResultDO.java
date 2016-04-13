package com.qing.index.model.dataobject;

import java.util.ArrayList;
import java.util.List;

public class SmsInfoResultDO {

	private int totalNum;
	
	private List<SmsInfoDO> smses = new ArrayList<SmsInfoDO>();

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public List<SmsInfoDO> getSmses() {
		return smses;
	}

	public void setSmses(List<SmsInfoDO> smses) {
		this.smses = smses;
	}
	
}
