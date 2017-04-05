package com.caac.house.project.vo;

import java.util.Date;

public class UserPerformanceVo {

	/**成交套数*/
	private int bargainSuit;
	
	/**成交时间*/
	private Date bargainDate;
	
	/**楼盘名称*/
	private String projName;

	public int getBargainSuit() {
		return bargainSuit;
	}

	public void setBargainSuit(int bargainSuit) {
		this.bargainSuit = bargainSuit;
	}

	public Date getBargainDate() {
		return bargainDate;
	}

	public void setBargainDate(Date bargainDate) {
		this.bargainDate = bargainDate;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}
}
