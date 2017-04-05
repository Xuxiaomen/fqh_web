package com.caac.house.weixin.vo;

public class JsapiTicketVo {
	/** ticket **/
	private String ticket;
	/** 到期秒数 **/
	private int expires_in;
	/** 状态编码 **/
	private int errcode;
	/** 状态信息 **/
	private String errmsg;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
