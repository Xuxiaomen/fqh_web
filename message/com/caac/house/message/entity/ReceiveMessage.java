package com.caac.house.message.entity;

import java.io.Serializable;
import java.util.Date;

public class ReceiveMessage implements Serializable{
	private static final long serialVersionUID = 3531768426486080737L;
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

}
