package com.caac.house.project.entity;

import java.util.Date;

public class Project {

private String id;
	
	/**楼盘名称*/
	private String name;
	
	/**楼盘位置*/
	private String address;
	
	/**开发商*/
	private String developer;
	
	/**物业公司*/
	private String property;
	
	/**物业费*/
	private String cost;
	
	/**产权类型*/
	private String equityType;
	
	/**装修情况*/
	private String fitment;
	
	/**层差*/
	private String distance;
	
	/**所属省份*/
	private String province;
	
	/**所属城市*/
	private String city;
	
	/**所属区县*/
	private String area;
	
	/**行政区*/
	private String district;
	
	/**海岸线分布*/
	private String coastline;
	
	/**楼盘状态 0-正常 1-禁用 2-删除*/
	private String status;
	
	/**占地面积*/
	private int floorSpace;
	
	/**建筑面积*/
	private int floorArea;
	
	/**小区户数*/
	private int houseNum;
	
	/**车位数量*/
	private int carport;
	
	/**楼盘起价*/
	private int minPrice;
	
	/**楼盘均价*/
	private int averagePrice;
	
	/**历史最高价*/
	private int maxPrice;
	
	/**产权年限*/
	private int equityYear;
	
	/**容积率*/
	private double plotRatio;
	
	/**绿化率*/
	private double greeningRate;
	
	/**开盘时间*/
	private Date openTime;
	
	/**入住时间*/
	private Date occupancyTime;
	
	/**栋数*/
	private int build;
	
	/**期数*/
	private int period;
	
	/**创建时间*/
	private Date createTime;
	
	/**创建人*/
	private String creater;
	
	/**所属项目组*/
	private String groups;
	
	/**佣金比例*/
	private double ratio; 
	
	/** 佣金有效期 **/
	private Date vilidity;
	
	/**楼盘佣金*/
	private String commission;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getEquityType() {
		return equityType;
	}

	public void setEquityType(String equityType) {
		this.equityType = equityType;
	}

	public String getFitment() {
		return fitment;
	}

	public void setFitment(String fitment) {
		this.fitment = fitment;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
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

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCoastline() {
		return coastline;
	}

	public void setCoastline(String coastline) {
		this.coastline = coastline;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getFloorSpace() {
		return floorSpace;
	}

	public void setFloorSpace(int floorSpace) {
		this.floorSpace = floorSpace;
	}

	public int getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(int floorArea) {
		this.floorArea = floorArea;
	}

	public int getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(int houseNum) {
		this.houseNum = houseNum;
	}

	public int getCarport() {
		return carport;
	}

	public void setCarport(int carport) {
		this.carport = carport;
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

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getEquityYear() {
		return equityYear;
	}

	public void setEquityYear(int equityYear) {
		this.equityYear = equityYear;
	}

	public double getPlotRatio() {
		return plotRatio;
	}

	public void setPlotRatio(double plotRatio) {
		this.plotRatio = plotRatio;
	}

	public double getGreeningRate() {
		return greeningRate;
	}

	public void setGreeningRate(double greeningRate) {
		this.greeningRate = greeningRate;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getOccupancyTime() {
		return occupancyTime;
	}

	public void setOccupancyTime(Date occupancyTime) {
		this.occupancyTime = occupancyTime;
	}

	public int getBuild() {
		return build;
	}

	public void setBuild(int build) {
		this.build = build;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public Date getVilidity() {
		return vilidity;
	}

	public void setVilidity(Date vilidity) {
		this.vilidity = vilidity;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}
}
