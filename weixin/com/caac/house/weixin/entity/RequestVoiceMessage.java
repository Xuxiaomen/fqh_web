package com.caac.house.weixin.entity;

public class RequestVoiceMessage extends RequestMessage {
	/** 语音消息媒体id **/
	private String mediaId;
	/** 语言格式 **/
	private String format;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
