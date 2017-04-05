package com.caac.house.broker.vo;

import java.util.Date;

public class ConfirmInfoVo {

	/**推荐人昵称*/
	private String userName;
	
	/**被推荐人昵称*/
	private String recommendName;
	
	/**推荐人手机号*/
	private String userPhone;
	
	/**被推荐人手机号*/
	private String recommendPhone;
	
	/**推荐时间*/
	private Date applyTime;
	
	/**超时时间*/
	private Date outTime;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRecommendName() {
		return recommendName;
	}

	public void setRecommendName(String recommendName) {
		this.recommendName = recommendName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getRecommendPhone() {
		return recommendPhone;
	}

	public void setRecommendPhone(String recommendPhone) {
		this.recommendPhone = recommendPhone;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
}
