package com.caac.house.project.vo;

import com.kernle.engine.entity.PageVo;

public class UserPageVo extends PageVo<UserVo>{

	/**楼盘所属项目组*/
	private String groups;
	
	/**楼盘id*/
	private String projId;
	
	/**置业顾问id*/
	private String userId;

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
