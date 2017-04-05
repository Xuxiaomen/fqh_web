package com.caac.house.project.vo;

import com.kernle.engine.entity.PageVo;

public class TrackListVo extends PageVo<HouseTrackVo> {
	/** 查询条件，可查询手机号码、客户姓名 **/
	private String cond;
	/** 是否成交 **/
	private String bargain;
	/** 经纪人ID **/
	private String brokerId;

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public String getBargain() {
		return bargain;
	}

	public void setBargain(String bargain) {
		this.bargain = bargain;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

}
