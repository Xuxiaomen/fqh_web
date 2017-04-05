package com.caac.house.project.vo;

import java.util.Date;

import com.kernle.engine.utils.StringUtil;

public class TrackInfoVo {
	/** ID **/
	private String id;
	/** 类型 0-推荐 1-派单 2-跟单 3-成交 **/
	private Integer type;
	/** 状态 **/
	private String status;
	/** 操作人 **/
	private String name;
	/** 时间 **/
	private Date time;
	/** 说明 **/
	private byte[] note;
	
	/**评价分数*/
	private Integer score;
	
	/**置业顾问id*/
	private String userId;
	
	/**置业顾问头像*/
	private String logo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getNote() {
		return StringUtil.toEncodedStringByUTF8(note);
	}

	public void setNote(byte[] note) {
		this.note = note;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
}
