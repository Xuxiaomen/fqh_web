package com.caac.house.project.entity;

import java.util.Date;

import com.kernle.engine.utils.StringUtil;

/**
 * 
 * @ClassName: SysUserExt
 * @Description: 销售人员附属信息
 * @author 叶
 * @date 2015年10月15日 下午4:22:34
 *
 */
public class SysUserExt {

	private String id;
	
	/**销售人员ID*/
	private String userId;
	
	/**自我介绍*/
	private byte[] introduce;
	
	/**点赞次数*/
	private int praise;
	
	/**最新派单时间*/
	private Date sendTime;
	
	/**派单剩余次数*/
	private int sendNum;
	
	/**最新点赞时间*/
	private Date praiseTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIntroduce() {
		return StringUtil.toEncodedStringByUTF8(introduce);
	}

	public void setIntroduce(byte[] introduce) {
		this.introduce = introduce;
	}

	public int getPraise() {
		return praise;
	}

	public void setPraise(int praise) {
		this.praise = praise;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public int getSendNum() {
		return sendNum;
	}

	public void setSendNum(int sendNum) {
		this.sendNum = sendNum;
	}

	public Date getPraiseTime() {
		return praiseTime;
	}

	public void setPraiseTime(Date praiseTime) {
		this.praiseTime = praiseTime;
	}
}
