package com.caac.house.weixin.entity;

public class ResponseQrcode {
	/** 获取的二维码ticket **/
	private String ticket;
	/** 二维码图片解析后的地址 **/
	private String url;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
