package com.caac.house.project.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.caac.house.broker.entity.Recommend;
import com.caac.house.project.entity.SalesPerformance;
import com.caac.house.project.vo.ConsultantVo;
import com.caac.house.project.vo.UserByChatListVo;
import com.caac.house.project.vo.UserByChatVo;
import com.caac.house.project.vo.UserEvaluatePageVo;
import com.caac.house.project.vo.UserEvaluateVo;
import com.caac.house.project.vo.UserExtVo;
import com.caac.house.project.vo.UserPageVo;
import com.caac.house.project.vo.UserVo;

@Component
public interface SalesDao {

	/**
	 * @Title: getUserInfo
	 * @Description: 获取置业顾问信息
	 * @param userId
	 * @return
	 * @throws
	 */
	public UserExtVo getUserInfo(String userId, String projId);
	
	/**
	 * 
	 * @Title: getProjIdByRecommend
	 * @Description: 根据推荐id获取楼盘id
	 * @param recommend 推荐id
	 * @return
	 * @throws
	 */
	Recommend getProjIdByRecommend(String recommend);
	
	/**
	 * @Title: getSalesPerformance
	 * @Description: 获取销售人员的成绩信息 
	 * @param projectId
	 * @param sales
	 * @return
	 * @throws
	 */
	public SalesPerformance getSalesPerformance(String projectId, String sales);
	
	/**
	 * @Title: insertSalesPerformanace
	 * @Description: 添加销售人员的成绩信息
	 * @param salesPerformance
	 * @throws
	 */
	public void insertSalesPerformanace(SalesPerformance salesPerformance);
	
	/**
	 * @Title: updateSalesPerformanaceSend
	 * @Description: 更新销售人员的派单次数
	 * @param salesPerformance
	 * @throws
	 */
	public void updateSalesPerformanaceSend(SalesPerformance salesPerformance);
	
	/**
	 * @Title: updateSalesPerformanaceAppoint
	 * @Description: 更新销售人员的约访次数
	 * @param salesPerformance
	 * @throws
	 */
	public void updateSalesPerformanaceAppoint(SalesPerformance salesPerformance);
	
	/**
	 * @Title: updateSalesPerformanaceArrive
	 * @Description: 更新销售人员的到访次数
	 * @param salesPerformance
	 * @throws
	 */
	public void updateSalesPerformanaceArrive(SalesPerformance salesPerformance);
	
	/**
	 * @Title: updateSalesPerformanaceVisit
	 * @Description: 更新销售人员的回访次数
	 * @param salesPerformance
	 * @throws
	 */
	public void updateSalesPerformanaceVisit(SalesPerformance salesPerformance);
	
	/**
	 * @Title: updateSalesPerformanaceSign
	 * @Description: 
	 * @param salesPerformance
	 * @throws
	 */
	public void updateSalesPerformanaceSign(SalesPerformance salesPerformance);
	
	/**
	 * 
	 * @Title: getUserByGroup
	 * @Description: 获取指定楼盘下的置业顾问
	 * @param vo 
	 * @return
	 * @throws
	 */
	List<UserVo> getUserByGroup(UserPageVo vo);
	
	/**
	 * 
	 * @Title: getUserOtherInfo
	 * @Description: 获取置业顾问其他信息
	 * @param userId 置业顾问id
	 * @return
	 * @throws
	 */
	ConsultantVo getUserOtherInfo(String userId);
	
	/**
	 * 
	 * @Title: getEvaluate
	 * @Description: 获取经纪人对置业顾问评论
	 * @param page
	 * @return
	 * @throws
	 */
	List<UserEvaluateVo> getEvaluate(UserEvaluatePageVo page);
	
	/**
	 * @Title: getSalesToChatByProj
	 * @Description: 获取指定楼盘的置业顾问聊天列表
	 * @param vo
	 * @return
	 * @throws
	 */
	public List<UserByChatVo> getSalesToChatByProj(UserByChatListVo vo);
}
