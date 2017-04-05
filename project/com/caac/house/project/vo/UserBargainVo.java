package com.caac.house.project.vo;

public class UserBargainVo {

	/**岗位名称*/
	private String postName;
	
	/**到访率*/
	private int arriveRate;
	
	/**成交率*/
	private int bargainRate;
	
	/**成交套数*/
	private int bargain;

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public int getArriveRate() {
		return arriveRate;
	}

	public void setArriveRate(int arriveRate) {
		this.arriveRate = arriveRate;
	}

	public int getBargainRate() {
		return bargainRate;
	}

	public void setBargainRate(int bargainRate) {
		this.bargainRate = bargainRate;
	}

	public int getBargain() {
		return bargain;
	}

	public void setBargain(int bargain) {
		this.bargain = bargain;
	}
}
