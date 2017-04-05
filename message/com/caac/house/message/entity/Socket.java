package com.caac.house.message.entity;

import java.util.Date;

public class Socket {
	/** 销售人员 **/
	private String salesId;
	/** 经纪人 **/
	private String brokerId;
	/** 接收时间 **/
	private Date time;
	/** 消息内容 **/
	private String content;
	/** 交谈类型 0-经纪人发送 1-置业顾问发送 2-系统消息 **/
	private String type;
	/** 标识 0-交谈消息 1-历史消息 **/
	private String flag;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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
