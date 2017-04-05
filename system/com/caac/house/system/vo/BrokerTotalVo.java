package com.caac.house.system.vo;

public class BrokerTotalVo {
	/** 直接房亲数 **/
	private int child;
	/** 让我直接收益 **/
	private int childMonery;
	/** 间接房亲数 **/
	private int grandson;
	/** 让我间接收益 **/
	private int grandsonMonery;

	public int getChild() {
		return child;
	}

	public void setChild(int child) {
		this.child = child;
	}

	public int getChildMonery() {
		return childMonery;
	}

	public void setChildMonery(int childMonery) {
		this.childMonery = childMonery;
	}

	public int getGrandson() {
		return grandson;
	}

	public void setGrandson(int grandson) {
		this.grandson = grandson;
	}

	public int getGrandsonMonery() {
		return grandsonMonery;
	}

	public void setGrandsonMonery(int grandsonMonery) {
		this.grandsonMonery = grandsonMonery;
	}

}
