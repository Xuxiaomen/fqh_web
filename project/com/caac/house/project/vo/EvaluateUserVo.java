package com.caac.house.project.vo;

import java.util.Date;

import com.kernle.engine.utils.StringUtil;

public class EvaluateUserVo {

	/**置业顾问名称*/
	private String userName;
	
	/**置业顾问头像*/
	private String userLogo;
	
	/**跟单/成交时间*/
	private Date orderTime;
	
	/**跟单/成交内容*/
	private byte[] content;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLogo() {
		return userLogo;
	}

	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getContent() {
		return StringUtil.toEncodedStringByUTF8(content);
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
}
