package com.caac.house.project.vo;

import java.util.List;

import com.caac.house.project.entity.ProjectPic;

public class ProjectPicVo {
	/** 楼盘名称 **/
	private String name;
	/** 楼盘LOGO **/
	private String logo;
	/** 楼盘图片 **/
	private List<ProjectPic> pic;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<ProjectPic> getPic() {
		return pic;
	}

	public void setPic(List<ProjectPic> pic) {
		this.pic = pic;
	}
}
