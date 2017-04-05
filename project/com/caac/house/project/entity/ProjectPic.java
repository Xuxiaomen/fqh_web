package com.caac.house.project.entity;

import com.kernle.engine.utils.StringUtil;

/**
 * 
 * @ClassName: ProjectPic
 * @Description: 楼盘图片
 * @author 叶
 * @date 2015年8月15日 下午3:34:49
 *
 */
public class ProjectPic {

	private String id;
	
	/**楼盘id*/
	private String projectId;
	
	/**图片类型 1-总规图 2-效果图 3-样板间 4-配套图 5-区位图*/
	private String type;
	
	/**附件ID*/
	private String attachment;
	
	/** 图片标题 **/
	private String title;
	
	/**图片说明*/
	private byte[] note;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getNote() {
		if(note == null) return "";
		return StringUtil.toEncodedStringByUTF8(note);
	}

	public void setNote(byte[] note) {
		this.note = note;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
