package com.caac.house.weixin.entity;

import java.util.Map;

public class RequestQrcode {
	/** 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值 **/
	private String action_name;
	/** 二维码详细信息 **/
	private Map<String, Object> action_info;

	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}

	public Map<String, Object> getAction_info() {
		return action_info;
	}

	public void setAction_info(Map<String, Object> action_info) {
		this.action_info = action_info;
	}

}
