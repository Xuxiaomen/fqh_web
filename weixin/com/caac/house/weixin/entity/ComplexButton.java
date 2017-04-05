package com.caac.house.weixin.entity;

public class ComplexButton extends Button {
	/** 二级菜单数组 **/
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
}
