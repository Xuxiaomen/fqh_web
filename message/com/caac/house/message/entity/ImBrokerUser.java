package com.caac.house.message.entity;

import java.util.Date;

public class ImBrokerUser {
	/** ID **/
	private String id;
	/** 经纪人ID **/
	private String broker;
	/** 置业顾问ID **/
	private String user;
	/** 交谈时间 **/
	private Date time;
	/** 交谈内容 **/
	private String content;
	/** 交谈类型 0-经纪人发送 1-置业顾问发送 **/
	private String type;
	/** 状态 0-未读 1-已读 **/
	private String status;
	/** 经纪人来源 0-房亲会 1-快佣宝 **/
	private String source;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
