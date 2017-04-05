package com.caac.house.project.vo;

/**
 * 
 * @ClassName: UserVo
 * @Description: 置业顾问信息
 * @author 叶
 * @date 2015年9月7日 上午9:05:46
 *
 */
public class UserVo {

	/**置业顾问id*/
	private String userId;
	
	/**置业顾问名称*/
	private String userName;
	
	/**置业顾问头像*/
	private String userLogo;
	
	/**成交套数*/
	private int bargain;
	
	/**月冠次数*/
	private int champion;
	
	/**接待数*/
	private int receive;
	
	/**分数*/
	private Integer score;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLogo() {
		return userLogo;
	}

	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
	}

	public int getBargain() {
		return bargain;
	}

	public void setBargain(int bargain) {
		this.bargain = bargain;
	}

	public int getChampion() {
		return champion;
	}

	public void setChampion(int champion) {
		this.champion = champion;
	}

	public int getReceive() {
		return receive;
	}

	public void setReceive(int receive) {
		this.receive = receive;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
