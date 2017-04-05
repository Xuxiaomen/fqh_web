package com.caac.house.project.vo;

import com.caac.house.project.entity.SysUserExt;

/**
 * 
 * @ClassName: UserPerformanceTotalVo
 * @Description: 置业顾问成交总信息
 * @author 叶
 * @date 2015年10月15日 下午4:47:38
 *
 */
public class UserPerformanceTotalVo {

	private UserVo user;
	
	private UserBargainVo userBargain;
	
	private SysUserExt ext;

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public UserBargainVo getUserBargain() {
		return userBargain;
	}

	public void setUserBargain(UserBargainVo userBargain) {
		this.userBargain = userBargain;
	}

	public SysUserExt getExt() {
		return ext;
	}

	public void setExt(SysUserExt ext) {
		this.ext = ext;
	}
}
