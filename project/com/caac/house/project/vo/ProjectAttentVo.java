package com.caac.house.project.vo;

/**
 * 
 * @ClassName: ProjectAttentVo
 * @Description: 关注楼盘
 * @author 叶
 * @date 2015年8月4日 下午3:41:31
 *
 */
public class ProjectAttentVo {

	/**楼盘*/
	private String proj;
	
	/**用户*/
	private String user;
	
	/**关注数*/
	private int num;

	public String getProj() {
		return proj;
	}

	public void setProj(String proj) {
		this.proj = proj;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
