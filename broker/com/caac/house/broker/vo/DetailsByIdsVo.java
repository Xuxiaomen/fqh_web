package com.caac.house.broker.vo;

public class DetailsByIdsVo {
	/** 房币明细ID **/
	private String id;
	/** 房币主表ID **/
	private String masterId;
	/** 推荐ID **/
	private String recommend;
	/** 成交收益 **/
	private int monery;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public int getMonery() {
		return monery;
	}

	public void setMonery(int monery) {
		this.monery = monery;
	}

}
