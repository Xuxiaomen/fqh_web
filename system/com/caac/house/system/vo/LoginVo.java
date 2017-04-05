package com.caac.house.system.vo;

import javax.validation.constraints.NotNull;

public class LoginVo {
	/** 手机号码 **/
	private String phone;
	/** 登录密码 **/
	private String pwd;

	@NotNull(message = "手机号码不能为空")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@NotNull(message = "登录密码不能为空")
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
