package com.caac.house.system.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 经纪人账号
 * @author Administrator
 *
 */
public class BrokerUser {

	private String id;
	
	/**上级id*/
	private String pid;
	
	@Size(max=10,message="姓名长度最大为10个字符")
	@NotNull(message="姓名不能为空")
	/**姓名*/
	private String name;
	
	/** 昵称 **/
	private String nickname;
	
	/** 姓名是否可见 0-可见 1-不可见 **/
	private String visible;
	
	@Pattern(regexp="1[0-9]{10}",message="手机号码格式不正确!")
	@NotNull
	/**手机号码*/
	private String phone;
	
	@Size(max=512,message="密码长度最大为512")
	/**密码*/
	private String pwd;
	
	/**经纪人来源 0-注册，1-推荐，2-微信二维码*/
	private String source = "0";
	
	/**状态 0-正常，1-禁用，2-删除*/
	private String status = "0";
	
	/**荐客申请ID*/
	private String recommend;
	
	/**推荐时间*/
	private Date recommendTime;
	
	/**注册时间*/
	private Date registerTime;
	
	/**登录时间*/
	private Date loginTime;
	
	private String sex;
	
	/**身份证*/
	private String idcard;
	
	/**住址*/
	private String address;
	
	/**职业*/
	private String job;
	
	@Pattern(regexp="1[0-9]{10}",message="推荐人手机号码格式不正确!")
	/**推荐人手机号码*/
	private String recommendPhone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name.trim();
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull(message="手机号不能为空")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@NotNull(message="密码不能为空")
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@NotNull(message="经纪人来源不能为空")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@NotNull(message="状态来源不能为空")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public Date getRecommendTime() {
		return recommendTime;
	}

	public void setRecommendTime(Date recommendTime) {
		this.recommendTime = recommendTime;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

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

	public String getNickname() {
		return nickname.trim();
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getRecommendPhone() {
		return recommendPhone;
	}

	public void setRecommendPhone(String recommendPhone) {
		this.recommendPhone = recommendPhone;
	}
}
