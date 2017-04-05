package com.caac.house.broker.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Recommend {
	/** ID_ **/
	private String id;
	/** 经纪人ID **/
	private String brokerId;
	/** 昵称 **/
	@Size(max=10,message="昵称长度最大为10个字符")
	private String name;
	/** 手机号码 **/
	@NotNull(message="手机号码不能为空")
	@Pattern(regexp="1[0-9]{10}",message="手机号码格式不正确!")
	private String phone;
	/** 密码 **/
	@NotNull(message="密码不能为空")
	@Size(max=512,message="密码长度最大为512")
	private String pwd;
	/** 状态 0-申请 1-确认 2-超时**/
	private String status;
	/** 申请时间 **/
	private Date applyTime;
	/** 确认时间 **/
	private Date configTime;
	/** 超时时间 **/
	private Date timeOut;
	/** 房币 **/
	private int monery;
	/** 申请次数 **/
	private int num;
	
	/**原始密码*/
	private String oldPwd;
	
	/**楼盘id*/
	private String projId;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getConfigTime() {
		return configTime;
	}

	public void setConfigTime(Date configTime) {
		this.configTime = configTime;
	}

	public Date getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}

	public int getMonery() {
		return monery;
	}

	public void setMonery(int monery) {
		this.monery = monery;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}
	
}
