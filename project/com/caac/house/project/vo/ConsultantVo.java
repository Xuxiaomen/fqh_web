package com.caac.house.project.vo;

/**
 * 
 * @ClassName: ConsultantVo
 * @Description: 置业顾问列表信息
 * @author 叶
 * @date 2015年10月17日 上午11:44:12
 *
 */
public class ConsultantVo {

	/**置业顾问id*/
	private String userId;
	
	/**置业顾问logo*/
	private String userLogo;
	
	/**置业顾问名称*/
	private String userName;
	
	/**到访人数*/
	private int arriveTime;
	
	/**到访率*/
	private int arriveRate;
	
	/**成交套数*/
	private int bargain;
	
	/**成交率*/
	private int bargainRate;
	
	/**评价总分*/
	private int score;
	
	/**成交总额*/
	private int bargainMonery;
	
	/**排名*/
	private int rank;
	
	/**岗位名称*/
	private String postName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserLogo() {
		return userLogo;
	}

	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(int arriveTime) {
		this.arriveTime = arriveTime;
	}

	public int getArriveRate() {
		return arriveRate;
	}

	public void setArriveRate(int arriveRate) {
		this.arriveRate = arriveRate;
	}

	public int getBargain() {
		return bargain;
	}

	public void setBargain(int bargain) {
		this.bargain = bargain;
	}

	public int getBargainRate() {
		return bargainRate;
	}

	public void setBargainRate(int bargainRate) {
		this.bargainRate = bargainRate;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getBargainMonery() {
		return bargainMonery;
	}

	public void setBargainMonery(int bargainMonery) {
		this.bargainMonery = bargainMonery;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}
}
