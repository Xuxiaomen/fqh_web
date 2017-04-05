package com.caac.house.broker.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class InviteVo {
	@NotNull(message = "关键字不能为空")
	private String id;
	@NotNull(message = "关键字不能为空")
	private String oid;
	@Size(max = 10, message = "姓名长度最大为10个字符")
	@NotNull(message = "姓名不能为空")
	private String name;
	@Pattern(regexp = "1[0-9]{10}", message = "手机号码格式不正确!")
	private String phone;
	@NotNull(message = "验证码不能为空")
	private String code;
	@NotNull(message = "密码不能为空")
	private String passwd;
	@NotNull(message = "确认密码不能为空")
	private String configpwd;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getConfigpwd() {
		return configpwd;
	}

	public void setConfigpwd(String configpwd) {
		this.configpwd = configpwd;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
	
}
