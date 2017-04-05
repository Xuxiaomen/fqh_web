package com.caac.house.system.vo;

public class UserRecommendVo {

	/**经纪人头像*/
	private String userLogo;
	
	/**经纪人姓名*/
	private String userName;
	
	/**房亲数*/
	private int brokerNum;
	
	/**房亲收益*/
	private int monery;
	
	/**姓名是否可见*/
	private String visiable;
	
	/**昵称*/
	private String nickname;

	public String getUserLogo() {
		return userLogo;
	}

	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getBrokerNum() {
		return brokerNum;
	}

	public void setBrokerNum(int brokerNum) {
		this.brokerNum = brokerNum;
	}

	public int getMonery() {
		return monery;
	}

	public void setMonery(int monery) {
		this.monery = monery;
	}

	public String getVisiable() {
		return visiable;
	}

	public void setVisiable(String visiable) {
		this.visiable = visiable;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
