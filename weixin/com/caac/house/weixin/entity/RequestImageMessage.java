package com.caac.house.weixin.entity;

public class RequestImageMessage extends RequestMessage {
	/** 图片链接 **/
	private String picUrl;
	/** 图片消息媒体id **/
	private String mediaId;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
