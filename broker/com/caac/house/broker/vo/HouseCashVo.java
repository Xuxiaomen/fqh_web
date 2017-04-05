package com.caac.house.broker.vo;

import java.util.Date;

public class HouseCashVo {
	private String id;
	private String projectId;
	private String project;
	private int monery;
	private String no;
	private String building;
	private int bargain;
	private Date getTime;
	private String type;
	private String name;

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

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public int getMonery() {
		return monery;
	}

	public void setMonery(int monery) {
		this.monery = monery;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public int getBargain() {
		return bargain;
	}

	public void setBargain(int bargain) {
		this.bargain = bargain;
	}

	public Date getGetTime() {
		return getTime;
	}

	public void setGetTime(Date getTime) {
		this.getTime = getTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
