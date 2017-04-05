package com.caac.house.project.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.caac.house.project.dao.EvaluateDao;
import com.caac.house.project.entity.OrderEvaluate;
import com.caac.house.project.entity.SalesPerformance;
import com.caac.house.project.vo.EvaluateNoteVo;
import com.caac.house.project.vo.EvaluateVo;
import com.caac.house.project.vo.OrderInfoVo;
import com.kernle.engine.ext.RespMessage;

@Service("evaluateService")
public class EvaluateService {
	
	@Resource
	private EvaluateDao evaluateDao;
	
	public RespMessage insertEvaluate(EvaluateVo vo){
		// 根据跟单ID获取楼盘ID和销售人员ID
		OrderInfoVo orderInfoVo = evaluateDao.findOrderInfo(vo.getRecommend());
		if(orderInfoVo == null){
			return RespMessage.error("非法的房源推荐记录");
		}
		
		EvaluateNoteVo oe = evaluateDao.findEvaluate(vo.getLinkId());
		if(oe != null) return RespMessage.error("已经对此跟单评价过了");
		
		int attitude = vo.getAttitude() == null ? 0 : vo.getAttitude();//态度
		int efficiency = vo.getEfficiency() == null ? 0 : vo.getEfficiency();//效率
		int specialty = vo.getSpecialty() == null ? 0 : vo.getSpecialty();//专业
		
		// 获取楼盘销售人员的业绩ID
		List<SalesPerformance> performances = evaluateDao.findPerformance(orderInfoVo);
		int evaluateScore = (attitude + efficiency + specialty)/3;//态度,效率,专业
		if(performances.size() > 0){
			SalesPerformance salesPerformance = performances.get(0);
			int brokerScore = salesPerformance.getBrokerScore() + evaluateScore;
			int brokerCount = salesPerformance.getBrokerCount() + 1;
			salesPerformance.setBrokerScore(brokerScore);
			salesPerformance.setBrokerCount(brokerCount);
			salesPerformance.setScore(salesPerformance.getScore() + evaluateScore);
			evaluateDao.updatePerformance(salesPerformance);
		}else{
			SalesPerformance salesPerformance = new SalesPerformance();
			salesPerformance.setProjectId(orderInfoVo.getProjectId());
			salesPerformance.setSales(orderInfoVo.getUserId());
			salesPerformance.setBargain(0);
			salesPerformance.setBrokerCount(1);
			salesPerformance.setBrokerScore(evaluateScore);
			salesPerformance.setScore(evaluateScore);
			evaluateDao.insertPerformance(salesPerformance);
		}		
		// 添加经纪人评价信息
		OrderEvaluate orderEvaluate = new OrderEvaluate();
		orderEvaluate.setRecommend(vo.getRecommend());
		orderEvaluate.setLinkId(vo.getLinkId());
		orderEvaluate.setLinkType(vo.getLinkType());
		orderEvaluate.setNote(vo.getNote());
		orderEvaluate.setAttitude(vo.getAttitude());//态度
		orderEvaluate.setEfficiency(vo.getEfficiency());//效率
		orderEvaluate.setSpecialty(vo.getSpecialty());//专业
		orderEvaluate.setScore(evaluateScore);
		evaluateDao.insertEvaluate(orderEvaluate);
		
		return RespMessage.success();
	}
	
	/**
	 * @Title: findEvaluate
	 * @Description: 查询推荐跟单服务评价
	 * @param linkId 推荐环节id
	 * @param userId 置业顾问id
	 * @return
	 * @throws
	 */
	public RespMessage findEvaluate(String linkId,String userId){
		EvaluateNoteVo vo = evaluateDao.findEvaluate(linkId);
		if(vo == null){
			vo = new EvaluateNoteVo();
		}
		vo.setEvaluateUserVo(evaluateDao.getUserByEvaluate(linkId, userId));
		return RespMessage.success(vo);
	}
	
}
