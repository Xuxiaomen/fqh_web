package com.caac.house.system.vo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserVo {

	private String id;
	
	/**姓名*/
	private String name;
	
	/**性别*/
	@Size(max=1,message="性别最大支持1个字符")
	private String sex;
	
	/**身份证*/
	@Pattern(regexp="\\d{15}(\\d{2}[A-Za-z0-9])?",message="身份证号不正确")
	private String idcard;
	
	/**住址*/
	@Size(max=512,message="住址最大只支持512个字符")
	private String address;
	
	/**职业*/
	@Size(max=20,message="职业最大只支持20个字符")
	private String job;
	
	/** 籍贯 **/
	@Size(max=100,message="籍贯最大只支持100个字符")
	private String natives;
	
	/** 手机 **/
	@Size(max=20,message="电话最大只支持20个字符")
	private String phone;
	
	/** QQ **/
	@Size(max=50,message="QQ最大只支持20个字符")
	private String qq;
	
	/** 微信 **/
	@Size(max=50,message="微信最大只支持50个字符")
	private String wx;
	
	/** 邮箱 **/
	@Size(max=100,message="邮箱最大只支持100个字符")
	private String mail;
	
	/**旧密码*/
	private String oldPwd;
	
	/**新密码*/
	@Pattern(regexp="[0-9a-zA-Z!@#$%^&*]{6,16}",message="密码格式为6-16位字母,数字或!@#$%^&*组成")
	private String newPwd;
	
	/**开户行*/
	private String bank;
	
	/**账号名称*/
	private String accountName;
	
	/**银行账号*/
	@Pattern(regexp="[0-9]{16,19}",message="银行卡号格式为16-19位的纯数字.")
	private String account;
	
	/**手机验证码*/
	private String code;
	
	/**经纪人*/
	private String brokerId;
	
	/**头像*/
	private String logo;
	
	/**昵称*/
	@Size(max=20,message="昵称最大只支持20个字符")
	private String nickname;
	
	/**个性签名*/
	@Size(max=100,message="个性签名最大只支持20个字符")
	private String motto;
	
	/**姓名是否可见 0-可见 1-不可见*/
	private String visiable;
	
	/**可用房币*/
	private Double monery;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public String getNatives() {
		return natives;
	}

	public void setNatives(String natives) {
		this.natives = natives;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWx() {
		return wx;
	}

	public void setWx(String wx) {
		this.wx = wx;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getVisiable() {
		return visiable;
	}

	public void setVisiable(String visiable) {
		this.visiable = visiable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMonery() {
		return monery;
	}

	public void setMonery(Double monery) {
		this.monery = monery;
	}
	
}
