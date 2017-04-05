package com.caac.house.weixin.vo;

import java.io.Serializable;
import java.util.Date;

public class AccessTokenVo implements Serializable {
	private static final long serialVersionUID = 3913586866498839095L;
	/** access token **/
	private String access_token;
	/** 到期时间 **/
	private Date expires;
	/** 到期秒数 **/
	private int expires_in;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}
}
