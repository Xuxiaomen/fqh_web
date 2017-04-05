package com.caac.house.broker.vo;

import com.kernle.engine.entity.PageVo;

public class ClientPageVo extends PageVo<ClientVo>{
	/** 经纪人ID **/
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
