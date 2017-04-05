package com.caac.house.broker.vo;

import java.util.List;

public class ExtractInfoVo {
	/** 房亲手机号 **/
	private String phone;
	/** 现金账户信息 **/
	private CashAccountVo account;
	/** 待提现的成交信息 **/
	private List<HouseCashVo> house;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public CashAccountVo getAccount() {
		return account;
	}

	public void setAccount(CashAccountVo account) {
		this.account = account;
	}

	public List<HouseCashVo> getHouse() {
		return house;
	}

	public void setHouse(List<HouseCashVo> house) {
		this.house = house;
	}

}
