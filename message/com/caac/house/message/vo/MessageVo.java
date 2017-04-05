package com.caac.house.message.vo;

public class MessageVo {
	/** 销售人员 **/
	private String user;
	/** 经纪人 **/
	private String broker;
	/** 开始位置 **/
	private int index;
	/** 数据条数 **/
	private int length;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
