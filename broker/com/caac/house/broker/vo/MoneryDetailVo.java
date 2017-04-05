package com.caac.house.broker.vo;

import java.util.Date;

public class MoneryDetailVo {
	/** 名称 **/
	private String name;
	/** 电话 **/
	private String phone;
	/** 获取时间 **/
	private Date getTime;
	/** 房币 **/
	private int monery;
	/** 类型 **/
	private String type;
	/** 头像 **/
	private String logo;
	/** 成交类型 0-客户成交 1-房亲收益 **/
	private String bargainType;
	/** 二维码推荐类型 0-不存在 1-注册 2-二维码荐客 **/
	private String wxtjType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getGetTime() {
		return getTime;
	}

	public void setGetTime(Date getTime) {
		this.getTime = getTime;
	}

	public int getMonery() {
		return monery;
	}

	public void setMonery(int monery) {
		this.monery = monery;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getBargainType() {
		return bargainType;
	}

	public void setBargainType(String bargainType) {
		this.bargainType = bargainType;
	}

	public String getWxtjType() {
		return wxtjType;
	}

	public void setWxtjType(String wxtjType) {
		this.wxtjType = wxtjType;
	}

}
