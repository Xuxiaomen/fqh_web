package com.caac.house.project.vo;

import java.util.Date;

public class UserExtVo {
	private String id;
	private String name;
	private String mobile;
	private String extId;
	private Date sendTime;
	private int sendNum;
	private int dayRecommend;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getExtId() {
		return extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public int getSendNum() {
		return sendNum;
	}

	public void setSendNum(int sendNum) {
		this.sendNum = sendNum;
	}

	public int getDayRecommend() {
		return dayRecommend;
	}

	public void setDayRecommend(int dayRecommend) {
		this.dayRecommend = dayRecommend;
	}

}
