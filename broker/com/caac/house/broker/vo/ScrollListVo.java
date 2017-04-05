package com.caac.house.broker.vo;

import java.util.Date;

public class ScrollListVo {

	/**时间*/
	private Date time;
	
	/**广告类型 0-成交 1-提现 2-认购*/
	private String type;
	
	/**楼盘名称*/
	private String name;
	
	/**广告内容*/
	private String content;
	
	/**图片*/
	private String logo;
	
	/**创建时间*/
	private Date createTime;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}