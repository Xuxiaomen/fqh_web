package com.caac.house.system.vo;

public class BrokerChildVo {
	/** ID **/
	private String id;
	/** 经纪人名称 **/
	private String name;
	/** 手机号码 **/
	private String phone;
	/** 推荐人数 **/
	private String num;
	/** 成交收益 **/
	private int totalBargain;
	/** 成交总金额 **/
	private int monery;
	
	/**用户头像*/
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

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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

	public int getTotalBargain() {
		return totalBargain;
	}

	public void setTotalBargain(int totalBargain) {
		this.totalBargain = totalBargain;
	}

	
}
