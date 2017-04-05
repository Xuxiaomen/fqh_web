package com.caac.house.system.vo;

import com.kernle.engine.entity.PageVo;

public class BrokerChildPageVo extends PageVo<BrokerChildVo>{
	/** 经纪人ID **/
	private String brokerId;

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
}
