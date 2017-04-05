package com.caac.house.message.entity;

public class SendMessage {
	/** 消息标识 0-连接消息 1-历史消息 2-交谈消息 3-轮询消息 **/
	private String flag;
	/** 销售人员 **/
	private String salesId;
	/** 经纪人 **/
	private String brokerId;
	/** 消息内容 **/
	private String content;
	/** 数据读取位置 **/
	private int index;
	/** 交谈类型 0-经纪人发送 1-置业顾问发送 **/
	private String type;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

}
