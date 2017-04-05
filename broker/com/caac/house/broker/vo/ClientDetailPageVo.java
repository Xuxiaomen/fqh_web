package com.caac.house.broker.vo;

import com.kernle.engine.entity.PageVo;

public class ClientDetailPageVo extends PageVo<MoneryDetailVo> {
	private String broker;

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}
	
}
