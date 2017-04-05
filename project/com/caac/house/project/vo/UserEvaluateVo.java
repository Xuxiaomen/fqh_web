package com.caac.house.project.vo;

import java.util.Date;

import com.kernle.engine.utils.StringUtil;

/**
 * 
 * @ClassName: UserEvaluateVo
 * @Description: 经纪人评价
 * @author 叶
 * @date 2015年10月23日 下午1:58:23
 *
 */
public class UserEvaluateVo {

	/**评价内容*/
	private byte[] note;
	
	/**评价时间*/
	private Date time;
	
	/**跟单状态 0-预约 1-到访 2-回访 4-认购 5-成交*/
	private String osStatus;
	
	/**评价分数*/
	private int score;
	
	/**经纪人手机号*/
	private String phone;
	
	/**经纪人名称*/
	private String userName;
	
	/**经纪人头像*/
	private String userLogo;

	public String getNote() {
		return StringUtil.toEncodedStringByUTF8(note);
	}

	public void setNote(byte[] note) {
		this.note = note;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getOsStatus() {
		return osStatus;
	}

	public void setOsStatus(String osStatus) {
		this.osStatus = osStatus;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
}
