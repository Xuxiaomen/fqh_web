package com.caac.house.project.vo;

import com.kernle.engine.entity.PageVo;

public class UserEvaluatePageVo extends PageVo<UserEvaluateVo>{

	/**置业顾问id*/
	private String userId;
	
	/**跟单状态 0-预约 1-到访 2-回访 4-认购 5-成交*/
	private String osStatus;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOsStatus() {
		return osStatus;
	}

	public void setOsStatus(String osStatus) {
		this.osStatus = osStatus;
	}
}
