package com.caac.house.broker.vo;

public class ExtractVo {
	private String id;
	/** 经纪人id **/
	private String brokerId;
	/** 申请金额 **/
	private int applyMonery;
	/** 荐客申请金额 **/
	private int applyBroker;
	/** 荐房申请金额 **/
	private int applyBargain;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public int getApplyMonery() {
		return applyMonery;
	}

	public void setApplyMonery(int applyMonery) {
		this.applyMonery = applyMonery;
	}

	public int getApplyBroker() {
		return applyBroker;
	}

	public void setApplyBroker(int applyBroker) {
		this.applyBroker = applyBroker;
	}

	public int getApplyBargain() {
		return applyBargain;
	}

	public void setApplyBargain(int applyBargain) {
		this.applyBargain = applyBargain;
	}

}
