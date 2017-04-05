package com.caac.house.project.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.caac.house.project.entity.EvaluateReply;
import com.caac.house.project.entity.OrderEvaluate;
import com.caac.house.project.entity.SalesPerformance;
import com.caac.house.project.vo.EvaluateNoteVo;
import com.caac.house.project.vo.EvaluateUserVo;
import com.caac.house.project.vo.OrderInfoVo;

@Component
public interface EvaluateDao {
	
	/**
	 * @Title: insertEvaluate
	 * @Description: 添加推荐跟单服务评价
	 * @param orderEvaluate
	 * @throws
	 */
	public void insertEvaluate(OrderEvaluate orderEvaluate);
	
	/**
	 * @Title: findEvaluate
	 * @Description: 查询推荐跟单服务评价
	 * @param linkId 推荐环节id
	 * @return
	 * @throws
	 */
	public EvaluateNoteVo findEvaluate(String linkId);
	
	/**
	 * @Title: insertEvaluateReply
	 * @Description: 添加推荐跟单服务评价回复
	 * @param evaluateReply
	 * @throws
	 */
	public void insertEvaluateReply(EvaluateReply evaluateReply);
	
	/**
	 * @Title: updateEvaluateReply
	 * @Description: 修改推荐跟单服务评价回复
	 * @param evaluateReply
	 * @throws
	 */
	public void updateEvaluateReply(EvaluateReply evaluateReply);
	
	/**
	 * @Title: findEvaluateReply
	 * @Description: 查询推荐跟单服务评价回复
	 * @param id
	 * @return
	 * @throws
	 */
	public EvaluateReply findEvaluateReply(String evaluateId);
	
	/**
	 * @Title: delEvaluateReply
	 * @Description: 删除推荐跟单服务评价回复
	 * @param id
	 * @throws
	 */
	public void delEvaluateReply(String id);
	
	/**
	 * @Title: findOrderInfo
	 * @Description: 根据跟单ID获取楼盘ID和销售人员ID
	 * @param orderId
	 * @return
	 * @throws
	 */
	public OrderInfoVo findOrderInfo(String orderId);
	
	/**
	 * @Title: findPerformanceId
	 * @Description: 获取销售人员业绩ID
	 * @param vo
	 * @return
	 * @throws
	 */
	public List<SalesPerformance> findPerformance(OrderInfoVo vo);
	
	/**
	 * @Title: insertPerformance
	 * @Description: 添加销售人员业绩
	 * @param salesPerformance
	 * @throws
	 */
	public void insertPerformance(SalesPerformance salesPerformance);
	
	/**
	 * @Title: updatePerformance
	 * @Description: 更新销售人员业绩
	 * @param salesPerformance
	 * @throws
	 */
	public void updatePerformance(SalesPerformance salesPerformance);
	
	/**
	 * 
	 * @Title: getUserByEvaluate
	 * @Description: 
	 * @param linkId 推荐环节id
	 * @param userId 置业顾问id
	 * @return
	 * @throws
	 */
	EvaluateUserVo getUserByEvaluate(String linkId,String userId);
}
