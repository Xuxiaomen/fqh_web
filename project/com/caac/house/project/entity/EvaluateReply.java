package com.caac.house.project.entity;

import java.util.Date;

import com.kernle.engine.utils.StringUtil;

public class EvaluateReply {
	/** ID **/
	private String id;
	/** 跟单评价ID **/
	private String evaluateId;
	/** 回复人 **/
	private String reply;
	/** 回复内容 **/
	private byte[] note;
	/** 回复时间 **/
	private Date time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(String evaluateId) {
		this.evaluateId = evaluateId;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getNote() {
		return StringUtil.toEncodedStringByUTF8(note);
	}

	public void setNote(byte[] note) {
		this.note = note;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
