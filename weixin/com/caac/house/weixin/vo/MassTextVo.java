package com.caac.house.weixin.vo;

import java.util.List;

public class MassTextVo {
	private List<String> touser;
	private TextVo text;
	private String msgtype;

	public List<String> getTouser() {
		return touser;
	}

	public void setTouser(List<String> touser) {
		this.touser = touser;
	}

	public TextVo getText() {
		return text;
	}

	public void setText(TextVo text) {
		this.text = text;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

}
