package com.caac.house.weixin.entity;

public class Video {
	/** 多媒体id **/
	private String mediaId;
	/** 视频消息标题 **/
	private String title;
	/** 视频消息描述 **/
	private String description;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
