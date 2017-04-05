package com.caac.house.project.vo;

public class EvaluateReplyVo {
	/** 跟单评价ID **/
	private String evaluateId;
	/** 回复内容 **/
	private byte[] note;

	public String getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(String evaluateId) {
		this.evaluateId = evaluateId;
	}

	public byte[] getNote() {
		return note;
	}

	public void setNote(byte[] note) {
		this.note = note;
	}
}
