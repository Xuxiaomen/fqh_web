package com.caac.house.system.vo;

import java.util.List;

import com.caac.house.broker.vo.ScrollInfoVo;
import com.caac.house.broker.vo.SpreadVo;
import com.caac.house.project.vo.ProjectListVo;

public class IndexInfoVo {
	/** 姓名 **/
	private String name;
	/** 昵称 **/
	private String nickname;
	/** 头像 **/
	private String logo;
	/** 姓名是否可见 0-可见 1-不可见 */
	private String visiable;
	/** 个性签名 **/
	private String motto;
	/** 楼盘列表 **/
	private List<ProjectListVo> project;
	/** 滚动文字 **/
	private List<ScrollInfoVo> text;
	/** 广告图片 **/
	private List<SpreadVo> spread;

	public List<ProjectListVo> getProject() {
		return project;
	}

	public void setProject(List<ProjectListVo> project) {
		this.project = project;
	}

	public List<ScrollInfoVo> getText() {
		return text;
	}

	public void setText(List<ScrollInfoVo> text) {
		this.text = text;
	}

	public List<SpreadVo> getSpread() {
		return spread;
	}

	public void setSpread(List<SpreadVo> spread) {
		this.spread = spread;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getVisiable() {
		return visiable;
	}

	public void setVisiable(String visiable) {
		this.visiable = visiable;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

}
