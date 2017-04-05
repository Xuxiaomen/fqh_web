package com.caac.house.broker.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RecommendBrokerVo {
	/** 昵称 **/
	@Size(max = 10, message = "姓名长度最大为10个字符")
	@NotNull(message = "姓名不能为空")
	private String name;
	/** 手机号码 **/
	@Pattern(regexp = "1[0-9]{10}", message = "手机号码格式不正确!")
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
