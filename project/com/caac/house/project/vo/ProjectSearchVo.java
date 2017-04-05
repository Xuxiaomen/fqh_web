package com.caac.house.project.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.kernle.engine.entity.PageVo;

/**
 * 
 * @ClassName: ProjectSearchVo
 * @Description: 楼盘搜索参数
 * @author 叶
 * @date 2015年8月8日 下午4:33:44
 *
 */
public class ProjectSearchVo extends PageVo<ProjectViewVo>{

	/**登录用户*/
	private String user;
	
	/**楼盘名称*/
	private String name;
	
	/**均价一级查询条件*/
	private String avgFirstPrice;
	
	/**均价子查询条件*/
	private String avgSecondPrice;
	
	/**朝向(前端传的数据)*/
	private String orientation;
	
	/**项目类型(前端传的数据)*/
	private String type;
	
	/**房数(前端传的数据)*/
	private String house;
	
	/**朝向(执行SQL的字段)*/
	private List<String> cx;
	
	/**项目类型(执行SQL的字段)*/
	private List<String> xmlx;
	
	/**房数(执行SQL的字段)*/
	private List<String> hx;
	
	/**户型面积*/
	private Integer hxmj;
	
	/**市县*/
	private String city;
	
	/**区域*/
	private String district;
	
	/**总价一级查询条件*/
	private String totalFirstPrice;
	
	/**总价二级查询条件*/
	private String totalSecondPrice;
	
	/**关注*/
	private String attention;
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		if(StringUtils.isNotBlank(name)){
			try {
				return URLDecoder.decode(name, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvgFirstPrice() {
		return avgFirstPrice;
	}

	public void setAvgFirstPrice(String avgFirstPrice) {
		this.avgFirstPrice = avgFirstPrice;
	}

	public String getAvgSecondPrice() {
		return avgSecondPrice;
	}

	public void setAvgSecondPrice(String avgSecondPrice) {
		this.avgSecondPrice = avgSecondPrice;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTotalFirstPrice() {
		return totalFirstPrice;
	}

	public void setTotalFirstPrice(String totalFirstPrice) {
		this.totalFirstPrice = totalFirstPrice;
	}

	public String getTotalSecondPrice() {
		return totalSecondPrice;
	}

	public void setTotalSecondPrice(String totalSecondPrice) {
		this.totalSecondPrice = totalSecondPrice;
	}

	public List<String> getCx() {
		return cx;
	}

	public void setCx(List<String> cx) {
		this.cx = cx;
	}

	public List<String> getXmlx() {
		return xmlx;
	}

	public void setXmlx(List<String> xmlx) {
		this.xmlx = xmlx;
	}

	public List<String> getHx() {
		return hx;
	}

	public void setHx(List<String> hx) {
		this.hx = hx;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public Integer getHxmj() {
		return hxmj;
	}

	public void setHxmj(Integer hxmj) {
		this.hxmj = hxmj;
	}
}
