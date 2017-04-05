package com.caac.house.project.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.caac.house.project.entity.OrderMark;
import com.caac.house.project.entity.ProjectPic;
import com.caac.house.project.entity.ProjectRecommend;
import com.caac.house.project.entity.SendOrders;
import com.caac.house.project.vo.GroupInfoVo;
import com.caac.house.project.vo.MapVo;
import com.caac.house.project.vo.ProjectBaseVo;
import com.caac.house.project.vo.ProjectListVo;
import com.caac.house.project.vo.ProjectVo;

@Component
public interface ProjectDao {
	
	/**
	 * @Title: getProject
	 * @Description: 首页房源展示
	 * @return
	 * @throws
	 */
	List<ProjectListVo> getIndexProject();
	
	/**
	 * @Title: getProject
	 * @Description: 楼盘列表
	 * @return
	 * @throws
	 */
	public List<ProjectListVo> getProject();
	
	/**
	 * 
	 * @Title: addRecommend
	 * @Description: 新增推荐
	 * @param pr
	 * @return
	 * @throws
	 */
	void addRecommend(ProjectRecommend pr);
	
	/**
	 * 
	 * @Title: addSendOrders
	 * @Description: 新增派单
	 * @param so
	 * @return
	 * @throws
	 */
	void addSendOrders(SendOrders so);
	
	/**
	 * 
	 * @Title: addOrderMark
	 * @Description: 新增派单记录
	 * @param entity
	 * @return
	 * @throws
	 */
	void addOrderMark(OrderMark entity);
	
	/**
	 * 
	 * @Title: getRecommendOrNot
	 * @Description: 该客户是否被推荐过
	 * @param pr
	 * @return
	 * @throws
	 */
	int getRecommendOrNot(ProjectRecommend pr);
	
	/**
	 * 
	 * @Title: getProjectById
	 * @Description: 获取指定楼盘信息
	 * @param proj 楼盘id
	 * @return
	 * @throws
	 */
	ProjectVo getProjectById(String proj);
	
	/**
	 * 
	 * @Title: getProjectPic
	 * @Description: 获取指定楼盘图片
	 * @param proj 楼盘id
	 * @return
	 * @throws
	 */
	List<ProjectPic> getProjectPic(String proj);
	
	/**
	 * 
	 * @Title: updateView
	 * @Description: 增加浏览次数
	 * @param proj 楼盘id
	 * @throws
	 */
	void updateView(String proj);
	
	/**
	 * 
	 * @Title: getImgNote
	 * @Description: 获取指定图片说明
	 * @param id 图片id
	 * @return
	 * @throws
	 */
	ProjectPic getImgNote(String id);
	
	/**
	 * 
	 * @Title: getVisitNum
	 * @Description: 判断该手机号码是否已存在于报备表中
	 * @param phone 手机号码
	 * @return
	 * @throws
	 */
	int getVisitNum(String phone);
	
	/**
	 * @Title: getProjectBase
	 * @Description: 获取楼盘基本信息
	 * @param id 楼盘ID
	 * @return
	 * @throws
	 */
	public List<ProjectBaseVo> getProjectBase(String id);
	
	/**
	 * @Title: getMap
	 * @Description: 获取楼盘信息，用于地图
	 * @param id 楼盘ID
	 * @return
	 * @throws
	 */
	public MapVo getMap(String id);
	
	/**
	 * @Title: getGroupInfo
	 * @Description: 获取咨询列表的楼盘名称和所属组
	 * @return
	 * @throws
	 */
	public List<GroupInfoVo> getGroupInfo();
	
}
