package com.caac.house.project.entity;

import java.util.Date;

/**
 * 
 * @ClassName: SendOrders
 * @Description: 推荐派单
 * @author 叶
 * @date 2015年9月7日 下午2:14:58
 *
 */
public class SendOrders {

	private String id;
	
	/**房源推荐ID*/
	private String recommend;
	
	/**接单人ID*/
	private String supervisor;
	
	/**派单人ID*/
	private String send;
	
	/**派单时间*/
	private Date sendTime;
	
	/**派单类型 0-人工派单 1-自动派单*/
	private String type;
	
	/**状态 0-已派单 1-已停止*/
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
