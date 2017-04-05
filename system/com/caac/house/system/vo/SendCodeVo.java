package com.caac.house.system.vo;

public class SendCodeVo {
	/** 经纪人ID **/
	private String broker;
	/** 接收手机 **/
	private String phone;
	/** 类型 1-当前用户 2-平台房亲 3-新号码**/
	private Integer type;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

}
