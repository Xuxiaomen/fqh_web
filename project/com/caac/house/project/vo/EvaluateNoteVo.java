package com.caac.house.project.vo;

import java.util.Date;

import com.kernle.engine.utils.StringUtil;

public class EvaluateNoteVo {

	private String id;
	
	/**推荐环节ID*/
	private String linkId;
	
	/**评价时间*/
	private Date time;
	
	/**评价分数*/
	private int score;
	
	/**评价内容*/
	private byte[] note;
	
	/**态度*/
	private int attitude;
	
	/**效率*/
	private int efficiency;
	
	/**专业*/
	private int specialty;
	
	/**跟单人信息*/
	private EvaluateUserVo evaluateUserVo;

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
		return StringUtil.toEncodedStringByUTF8(note);
	}

	public void setNote(byte[] note) {
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

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}


	public EvaluateUserVo getEvaluateUserVo() {
		return evaluateUserVo;
	}

	public void setEvaluateUserVo(EvaluateUserVo evaluateUserVo) {
		this.evaluateUserVo = evaluateUserVo;
	}
}
