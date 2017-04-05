package com.caac.house.weixin.entity;

public class CommandButton extends Button {
	/** 响应动作类型 **/
	private String type;
	/** 菜单KEY值 **/
	private String key;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
