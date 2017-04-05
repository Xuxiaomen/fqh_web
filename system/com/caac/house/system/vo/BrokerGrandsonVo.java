package com.caac.house.system.vo;

public class BrokerGrandsonVo {
	/** ID **/
	private String id;
	/** 经纪人名称 **/
	private String name;
	/** 手机号码 **/
	private String phone;
	/** 成交收益 **/
	private String totalBargain;
	/** 成交总金额 **/
	private int monery;
	
	/**经纪人头像*/
	private String logo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getMonery() {
		return monery;
	}

	public void setMonery(int monery) {
		this.monery = monery;
	}

	public String getTotalBargain() {
		return totalBargain;
	}

	public void setTotalBargain(String totalBargain) {
		this.totalBargain = totalBargain;
	}
}
