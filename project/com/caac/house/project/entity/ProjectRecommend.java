package com.caac.house.project.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 
 * @ClassName: ProjectRecommend
 * @Description: 房源推荐
 * @author 叶
 * @date 2015年8月5日 上午9:13:49
 *
 */
public class ProjectRecommend {

	private String id;
	
	/**楼盘ID*/
	private String projectId;
	
	/**经纪人ID*/
	private String brokerId;
	
	/**客户名称*/
	@NotNull(message="客户名称不能为空")
	@Size(max=10,message="客户名称最大支持10个字符")
	@Pattern(regexp="[a-zA-Z\u4e00-\u9fa5]+",message="客户名称只能是中文或英文")
	private String name;
	
	/**手机号码*/
	@Pattern(regexp="1\\d{10}",message="手机号码格式不正确!")
	private String phone;
	
	/**其他说明*/
	@Size(max=512,message="说明信息最大支持512个字符")
	private String note;
	
	/**推荐时间*/
	private Date time;
	
	/**状态 0-待派单 1-已派单 2-跟单中 3-已成交 4-未成交*/
	private String status;
	
	/**朝向 0-朝东 1-朝南 2-朝西 3-朝北*/
	private String direction;
	
	/**房数 0-一房 1-二房 2-三房 3-四房*/
	private String room;
	
	/**总价范围 0-30万以内 1-30万-40万 2-40万-50万 3-50万以上*/
	private String price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
