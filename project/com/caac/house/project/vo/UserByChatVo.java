package com.caac.house.project.vo;

import java.util.Date;

public class UserByChatVo {
	/** 置业顾问ID **/
	private String id;
	/** 置业顾问名称 **/
	private String name;
	/** 置业顾问头像 **/
	private String logo;
	/** 置业顾问手机 **/
	private String mobile;
	/** 最后交谈时间 **/
	private Date time;
	/** 最后交谈内容 **/
	private String content;

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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
