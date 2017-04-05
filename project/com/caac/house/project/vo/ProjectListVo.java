package com.caac.house.project.vo;

import java.util.Date;

public class ProjectListVo {
	/** 楼盘ID **/
	private String id;
	/** 楼盘名称 **/
	private String name;
	/** 所属城市 **/
	private String city;
	/** 所属区县 **/
	private String area;
	/** 楼盘起价 **/
	private int minPrice;
	/** 楼盘均价 **/
	private int averagePrice;
	/** 楼盘佣金 **/
	private String commission;
	/** 佣金有效期 **/
	private Date validity;
	/** 楼盘形象图 **/
	private String figure;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(int averagePrice) {
		this.averagePrice = averagePrice;
	}


	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getFigure() {
		return figure;
	}

	public void setFigure(String figure) {
		this.figure = figure;
	}

}