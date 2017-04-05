package com.caac.house.broker.vo;

import com.kernle.engine.entity.PageVo;

public class MyRevenuePageVo extends PageVo<MoneryDetailVo>{

	/**经纪人*/
	private String broker;
	
	/**搜索类型 0-房亲 1-客户*/
	private String searchType;
	
	/**搜索关键字*/
	private String searchText;

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
