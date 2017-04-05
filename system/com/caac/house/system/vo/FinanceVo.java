package com.caac.house.system.vo;

import java.util.List;

public class FinanceVo {
	private List<String> months;
	private List<Integer> child;
	private List<Integer> childBargain;
	private List<Integer> grandson;
	private List<Integer> grandsonBargain;
	
	/** 直接房亲总数 **/
	private int childTotal;
	
	/** 间接房亲总数 **/
	private int grandsonTotal;

	public List<String> getMonths() {
		return months;
	}

	public void setMonths(List<String> months) {
		this.months = months;
	}

	public List<Integer> getChild() {
		return child;
	}

	public void setChild(List<Integer> child) {
		this.child = child;
	}

	public List<Integer> getChildBargain() {
		return childBargain;
	}

	public void setChildBargain(List<Integer> childBargain) {
		this.childBargain = childBargain;
	}

	public List<Integer> getGrandson() {
		return grandson;
	}

	public void setGrandson(List<Integer> grandson) {
		this.grandson = grandson;
	}

	public List<Integer> getGrandsonBargain() {
		return grandsonBargain;
	}

	public void setGrandsonBargain(List<Integer> grandsonBargain) {
		this.grandsonBargain = grandsonBargain;
	}

	public int getChildTotal() {
		return childTotal;
	}

	public void setChildTotal(int childTotal) {
		this.childTotal = childTotal;
	}

	public int getGrandsonTotal() {
		return grandsonTotal;
	}

	public void setGrandsonTotal(int grandsonTotal) {
		this.grandsonTotal = grandsonTotal;
	}

}
