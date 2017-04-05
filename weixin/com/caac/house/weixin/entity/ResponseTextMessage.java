package com.caac.house.weixin.entity;

public class ResponseTextMessage extends ResponseMessage {
	/** 文本消息内容 **/
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
