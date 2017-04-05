package com.caac.house.project.entity;

public class SalesPerformance {
	/** ID **/
	private String id;
	/** 楼盘ID **/
	private String projectId;
	/** 销售人员ID **/
	private String sales;
	/** 星级分数 **/
	private int score;
	/** 成交套数 **/
	private int bargain;
	/** 经纪人评价总分 **/
	private int brokerScore;
	/** 经纪人评价次数 **/
	private int brokerCount;
	/** 成交金额 **/
	private double monery;
	/** 到访次数 **/
	private int arrive;
	/** 派单次数 **/
	private int send;
	/** 约访次数 **/
	private int appoint;
	/** 回访次数 **/
	private int visit;
	/** 认购次数 **/
	private int sign;

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

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getBargain() {
		return bargain;
	}

	public void setBargain(int bargain) {
		this.bargain = bargain;
	}

	public int getBrokerScore() {
		return brokerScore;
	}

	public void setBrokerScore(int brokerScore) {
		this.brokerScore = brokerScore;
	}

	public int getBrokerCount() {
		return brokerCount;
	}

	public void setBrokerCount(int brokerCount) {
		this.brokerCount = brokerCount;
	}

	public double getMonery() {
		return monery;
	}

	public void setMonery(double monery) {
		this.monery = monery;
	}

	public int getArrive() {
		return arrive;
	}

	public void setArrive(int arrive) {
		this.arrive = arrive;
	}

	public int getSend() {
		return send;
	}

	public void setSend(int send) {
		this.send = send;
	}

	public int getAppoint() {
		return appoint;
	}

	public void setAppoint(int appoint) {
		this.appoint = appoint;
	}

	public int getVisit() {
		return visit;
	}

	public void setVisit(int visit) {
		this.visit = visit;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}
}
