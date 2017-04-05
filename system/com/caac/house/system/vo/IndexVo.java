package com.caac.house.system.vo;

import java.util.List;

public class IndexVo {
	private String name;
	/** 可用房币 **/
	private double monery;
	/** 直接房亲总数 **/
	private int childTotal;
	/** 前5位直接房亲 **/
	private List<BrokerChildVo> brokerChild;
	/** 间接房亲总数 **/
	private int grandsonTotal;
	/** 前5位间接房亲 **/
	private List<BrokerGrandsonVo> brokerGrandson;
	
	/**用户头像*/
	private String logo;

	public double getMonery() {
		return monery;
	}

	public void setMonery(double monery) {
		this.monery = monery;
	}

	public int getChildTotal() {
		return childTotal;
	}

	public void setChildTotal(int childTotal) {
		this.childTotal = childTotal;
	}

	public List<BrokerChildVo> getBrokerChild() {
		return brokerChild;
	}

	public void setBrokerChild(List<BrokerChildVo> brokerChild) {
		this.brokerChild = brokerChild;
	}

	public int getGrandsonTotal() {
		return grandsonTotal;
	}

	public void setGrandsonTotal(int grandsonTotal) {
		this.grandsonTotal = grandsonTotal;
	}

	public List<BrokerGrandsonVo> getBrokerGrandson() {
		return brokerGrandson;
	}

	public void setBrokerGrandson(List<BrokerGrandsonVo> brokerGrandson) {
		this.brokerGrandson = brokerGrandson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
}
