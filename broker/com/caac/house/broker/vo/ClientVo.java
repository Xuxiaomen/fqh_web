package com.caac.house.broker.vo;

public class ClientVo {
	/** 客户名称 **/
	private String name;
	/** 客户手机 **/
	private String phone;
	/** 成交套数 **/
	private int total;
	/** 建筑面积 **/
	private double area;
	/** 成交收益 **/
	private int monery;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public int getMonery() {
		return monery;
	}

	public void setMonery(int monery) {
		this.monery = monery;
	}

}
