package com.caac.house.project.vo;

/**
 * 
 * @ClassName: ProjectViewVo
 * @Description: 房源展示
 * @author 叶
 * @date 2015年8月3日 上午10:26:09
 *
 */
public class ProjectViewVo {

	/**登录用户*/
	private String userId;
	
	/**楼盘id*/
	private String id;
	
	/**关注人*/
	private String user;
	
	/**形象图*/
	private String figure;
	
	/**楼盘名称*/
	private String name;
	
	/**所属城市*/
	private String city;
	
	/**所属区县*/
	private String area;
	
	/**楼盘均价*/
	private int averagePrice;
	
	/**全款折扣*/
	private double fullPay;
	
	/**按揭折扣*/
	private double mortgage;
	
	/**个人关注次数*/
	private int num;
	
	/**楼盘总关注次数*/
	private int total;
	
	/**浏览次数*/
	private int view;
	
	/**所属项目组*/
	private String groups;

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

	public int getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(int averagePrice) {
		this.averagePrice = averagePrice;
	}

	public double getFullPay() {
		return fullPay;
	}

	public void setFullPay(double fullPay) {
		this.fullPay = fullPay;
	}

	public double getMortgage() {
		return mortgage;
	}

	public void setMortgage(double mortgage) {
		this.mortgage = mortgage;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getFigure() {
		return figure;
	}

	public void setFigure(String figure) {
		this.figure = figure;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}
}
