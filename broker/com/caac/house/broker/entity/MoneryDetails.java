package com.caac.house.broker.entity;

import java.util.Date;

/**
 * 房币明细
 * @author Administrator
 *
 */
public class MoneryDetails {

	private String id;
	
	/**房币主表ID*/
	private String masterId;

	/**推荐ID*/
	private String recommend;
	
	/**类型 0-注册 1-荐客 2-荐房 3-提现*/
	private String type;
	
	/**金额*/
	private int monery;
	
	/**获取时间*/
	private Date getTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMonery() {
		return monery;
	}

	public void setMonery(int monery) {
		this.monery = monery;
	}

	public Date getGetTime() {
		return getTime;
	}

	public void setGetTime(Date getTime) {
		this.getTime = getTime;
	}
}
