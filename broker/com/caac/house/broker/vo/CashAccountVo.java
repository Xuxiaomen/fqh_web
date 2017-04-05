package com.caac.house.broker.vo;

public class CashAccountVo {
	/** 账户金额 **/
	private int monery;
	/** 荐客余额 **/
	private int moneryBroker;
	/** 成交总收益 **/
	private int totalBargain;
	/** 可提现金额 **/
	private int canCash;

	public int getMonery() {
		return monery;
	}

	public void setMonery(int monery) {
		this.monery = monery;
	}

	public int getMoneryBroker() {
		return moneryBroker;
	}

	public void setMoneryBroker(int moneryBroker) {
		this.moneryBroker = moneryBroker;
	}

	public int getTotalBargain() {
		return totalBargain;
	}

	public void setTotalBargain(int totalBargain) {
		this.totalBargain = totalBargain;
	}

	public int getCanCash() {
		return canCash;
	}

	public void setCanCash(int canCash) {
		this.canCash = canCash;
	}

}
