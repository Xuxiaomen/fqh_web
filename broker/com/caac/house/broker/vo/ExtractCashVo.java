package com.caac.house.broker.vo;

import java.util.List;

public class ExtractCashVo {
	/** 验证码 **/
	private String code;
	/** 邀请房亲提现佣金 **/
	private Integer cash;
	/** 成交收益明细ID列表 **/
	private List<String> ids;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getCash() {
		return cash;
	}

	public void setCash(Integer cash) {
		this.cash = cash;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

}
