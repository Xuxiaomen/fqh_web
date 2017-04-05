package com.caac.house.weixin.vo;

import java.util.List;

public class MassNewsVo {
	private List<String> touser;
	private MediaIdVo mpnews;
	private String msgtype;

	public List<String> getTouser() {
		return touser;
	}

	public void setTouser(List<String> touser) {
		this.touser = touser;
	}

	public MediaIdVo getMpnews() {
		return mpnews;
	}

	public void setMpnews(MediaIdVo mpnews) {
		this.mpnews = mpnews;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	
}
