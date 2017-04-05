package com.caac.house.broker.vo;

public class UpdateApplyVo {
	/** 经纪人 **/
	private String brokerId;
	/** 申请金额 **/
	private int apply;
	/** 申请荐客金额 **/
	private int applyBroker;
	/** 申请荐房金额 **/
	private int applyBargain;

	public int getApply() {
		return apply;
	}

	public void setApply(int apply) {
		this.apply = apply;
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

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

}
