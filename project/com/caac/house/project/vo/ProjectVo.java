package com.caac.house.project.vo;

import java.util.List;

import com.caac.house.project.entity.Project;
import com.caac.house.project.entity.ProjectPic;
import com.kernle.engine.utils.StringUtil;

public class ProjectVo extends Project{

	/**项目介绍*/
	private byte[] introduce;
	
	/**楼盘标志图*/
	private String logo;
	
	/**形象图*/
	private String figure;
	
	/**楼盘图片*/
	private List<ProjectPic> pics;
	
	/**成交套数*/
	private int bargain;
	
	/**房亲收益*/
	private int monery;
	
	/**项目宣传语*/
	private String slogan; 
	
	/** 物业类型 **/
	private String propertyType;
	
	public String getIntroduce() {
		return StringUtil.toEncodedStringByUTF8(introduce);
	}

	public void setIntroduce(byte[] introduce) {
		this.introduce = introduce;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getFigure() {
		return figure;
	}

	public void setFigure(String figure) {
		this.figure = figure;
	}

	public List<ProjectPic> getPics() {
		return pics;
	}

	public void setPics(List<ProjectPic> pics) {
		this.pics = pics;
	}

	public int getBargain() {
		return bargain;
	}

	public void setBargain(int bargain) {
		this.bargain = bargain;
	}

	public int getMonery() {
		return monery;
	}

	public void setMonery(int monery) {
		this.monery = monery;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
}
