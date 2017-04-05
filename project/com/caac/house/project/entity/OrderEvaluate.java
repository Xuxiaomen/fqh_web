package com.caac.house.project.entity;

import java.util.Date;

public class OrderEvaluate {
	/** ID **/
	private String id;
	/** 推荐房源ID **/
	private String recommend;
	/** 评价时间 **/
	private Date time;
	/** 评价分数 **/
	private int score;
	/** 评价内容 **/
	private String note;	
	/**态度*/
	private int attitude;	
	/**效率*/
	private int efficiency;	
	/**专业*/
	private int specialty;
	
	/**推荐环节ID*/
	private String linkId;
	
	/**环节类型 0-跟单 1-成交*/
	private String linkType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getAttitude() {
		return attitude;
	}

	public void setAttitude(int attitude) {
		this.attitude = attitude;
	}

	public int getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(int efficiency) {
		this.efficiency = efficiency;
	}

	public int getSpecialty() {
		return specialty;
	}

	public void setSpecialty(int specialty) {
		this.specialty = specialty;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
}
