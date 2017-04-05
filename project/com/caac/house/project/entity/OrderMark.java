package com.caac.house.project.entity;

import java.util.Date;

public class OrderMark {

	private String id;
	
	/**推荐ID*/
	private String recommend;
	
	/**派单ID*/
	private String order;
	
	/**接单人ID*/
	private String supervisor;
	
	/**派单人ID*/
	private String send;
	
	/**派单时间*/
	private Date sendTime;
	
	/**派单类型 0-手动派单 1-经纪人自选 2-自动派单*/
	private int type;
	
	/**派单级别 0-A级 1-B级 2-C级*/
	private int level;
	
	/**创建时间*/
	private Date createTime;
	
	/**状态 0-正常 1-派单 2-超时*/
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
