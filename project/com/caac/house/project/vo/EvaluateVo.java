package com.caac.house.project.vo;

import javax.validation.constraints.NotNull;

public class EvaluateVo {
	/** 推荐房源ID **/
	private String recommend;
	/**态度*/
	private Integer attitude;	
	/**效率*/
	private Integer efficiency;	
	/**专业*/
	private Integer specialty;
	/** 评价内容 **/
	@NotNull(message="请填写评价内容")
	private String note;
	
	/**推荐环节id*/
	private String linkId;
	
	/**环节类型 0-跟单 1-成交*/
	private String linkType;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getAttitude() {
		return attitude;
	}

	public void setAttitude(Integer attitude) {
		this.attitude = attitude;
	}

	public Integer getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(Integer efficiency) {
		this.efficiency = efficiency;
	}

	public Integer getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Integer specialty) {
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
