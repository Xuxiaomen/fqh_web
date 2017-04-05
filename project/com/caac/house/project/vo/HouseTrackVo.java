package com.caac.house.project.vo;

import java.util.Date;

public class HouseTrackVo {
	/** ID **/
	private String id;
	/** 楼盘名称 **/
	private String project;
	/** 楼盘logo **/
	private String logo;
	/** 客户名称 **/
	private String name;
	/** 手机号码 **/
	private String phone;
	/** 推荐时间 **/
	private Date time;
	/** 状态 **/
	private String status;
	
	/**跟单状态 0-预约 1-到访 2-回访 3-终止跟单 4-认购*/
	private String osStatus;
	
	/**成交状态 0-已提交 1-审核通过 2-审核不通过 3-删除*/
	private String bargainStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOsStatus() {
		return osStatus;
	}

	public void setOsStatus(String osStatus) {
		this.osStatus = osStatus;
	}

	public String getBargainStatus() {
		return bargainStatus;
	}

	public void setBargainStatus(String bargainStatus) {
		this.bargainStatus = bargainStatus;
	}
}
