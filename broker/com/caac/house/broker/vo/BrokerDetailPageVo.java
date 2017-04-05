package com.caac.house.broker.vo;

import com.kernle.engine.entity.PageVo;

public class BrokerDetailPageVo extends PageVo<MoneryDetailVo>{
	private String broker;
	
	/**手机或姓名*/
	private String searchText;

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
}
