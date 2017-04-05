package com.caac.house.broker.vo;

import com.kernle.engine.entity.PageVo;

public class ExtractPageVo extends PageVo<ExtractVo>{

	/**经纪人*/
	private String brokerId;

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
}
