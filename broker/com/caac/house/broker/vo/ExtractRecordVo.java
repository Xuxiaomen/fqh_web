package com.caac.house.broker.vo;

import java.util.Date;
import java.util.List;

import com.kernle.engine.utils.StringUtil;

public class ExtractRecordVo {
	/** ID **/
	private String id;
	/** 申请时间 **/
	private Date applyTime;
	/** 类型 **/
	private String type;
	/** 备注 **/
	private byte[] note;
	/** 申请金额 **/
	private int applyMonery;
	/** 申请荐客金额 **/
	private int applyBroker;
	/** 申请荐房金额 **/
	private int applyBargain;
	/** 申请提现房源列表 **/
	private List<BargainToExtractRecordVo> bargain;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNote() {
		return StringUtil.toEncodedStringByUTF8(note); 
	}

	public void setNote(byte[] note) {
		this.note = note;
	}

	public int getApplyMonery() {
		return applyMonery;
	}

	public void setApplyMonery(int applyMonery) {
		this.applyMonery = applyMonery;
	}

	public int getApplyBroker() {
		return applyBroker;
	}

	public void setApplyBroker(int applyBroker) {
		this.applyBroker = applyBroker;
	}

	public int getApplyBargain() {
		return applyBargain;
	}

	public void setApplyBargain(int applyBargain) {
		this.applyBargain = applyBargain;
	}

	public List<BargainToExtractRecordVo> getBargain() {
		return bargain;
	}

	public void setBargain(List<BargainToExtractRecordVo> bargain) {
		this.bargain = bargain;
	}

}
