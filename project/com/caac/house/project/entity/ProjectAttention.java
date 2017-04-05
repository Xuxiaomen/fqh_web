package com.caac.house.project.entity;

import java.util.Date;

/**
 * 
 * @ClassName: ProjectAttention
 * @Description: 楼盘关注
 * @author 叶
 * @date 2015年8月4日 上午9:57:58
 *
 */
public class ProjectAttention {

	private String id;
	
	/**楼盘ID*/
	private String projectId;
	
	/**关注人*/
	private String user;
	
	/**关注时间*/
	private Date time;
	
	/**所属系统 0-房亲会 1-房东东*/
	private String system;

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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}
	
}
