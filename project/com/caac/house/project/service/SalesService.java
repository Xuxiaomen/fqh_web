package com.caac.house.project.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.caac.house.broker.entity.Recommend;
import com.caac.house.project.dao.SalesDao;
import com.caac.house.project.entity.SalesPerformance;
import com.caac.house.project.vo.UserByChatListVo;
import com.caac.house.project.vo.UserByChatVo;
import com.caac.house.project.vo.UserEvaluatePageVo;
import com.caac.house.project.vo.UserPageVo;
import com.kernle.engine.entity.User;
import com.kernle.engine.ext.RespMessage;

@Service("salesService")
public class SalesService {
	
	@Resource
	private SalesDao salesDao;

	/**
	 * 
	 * @Title: saveOrUpdateSalesPerformanace
	 * @Description: 更新销售人员业绩
	 * @param recommend 推荐id
	 * @param supervisor 接单人
	 * @param index 1(派单),2(约访),3(到访),4(回访)
	 * @throws
	 */
	public void saveOrUpdateSalesPerformanace(String recommend,String supervisor,int index){
		Recommend r = salesDao.getProjIdByRecommend(recommend);
		SalesPerformance sp = salesDao.getSalesPerformance(r.getProjId(),supervisor);
		if(sp == null){//还未有相关记录,新增
			sp = new SalesPerformance();
			sp.setProjectId(r.getProjId());
			sp.setSales(supervisor);
			switch(index){
			case 1:
				sp.setSend(1);//派单次数
				break;
			case 2:
				sp.setAppoint(1);//约访次数
				break;
			case 3:
				sp.setArrive(1);//到访次数
				break;
			case 4:
				sp.setVisit(1);//回访次数
				break;
			case 5:
				sp.setSign(1);
				break;
			}
			salesDao.insertSalesPerformanace(sp);
		}else{
			switch(index){
			case 1:
				salesDao.updateSalesPerformanaceSend(sp);//派单
				break;
			case 2:
				salesDao.updateSalesPerformanaceAppoint(sp);//约访
				break;
			case 3:
				salesDao.updateSalesPerformanaceArrive(sp);//到访
				break;
			case 4:
				salesDao.updateSalesPerformanaceVisit(sp);//回访
				break;
			case 5:
				salesDao.updateSalesPerformanaceSign(sp);//认购
				break;
			}
		}
	}
	
	/**
	 * 
	 * @Title: getUserByGroup
	 * @Description: 获取指定楼盘下的置业顾问
	 * @param vo 楼盘所属项目组
	 * @return
	 * @throws
	 */
	public void getUserByGroup(UserPageVo vo){
		salesDao.getUserByGroup(vo);
	}
	
	/**
	 * 
	 * @Title: getConsultantInfo
	 * @Description: 经纪人查看置业顾问信息集合
	 * @return
	 * @throws
	 */
	public RespMessage getConsultantInfo(String userId){
		return RespMessage.success(salesDao.getUserOtherInfo(userId));
	}	

	/**
	 * 
	 * @Title: getEvaluate
	 * @Description: 获取经纪人对置业顾问评论
	 * @param page
	 * @return
	 * @throws
	 */
	public void getEvaluate(UserEvaluatePageVo page){
		salesDao.getEvaluate(page);
	}
	
	public RespMessage getSalesByProj(User user,  UserByChatListVo vo){
		vo.setBrokerId(user.getId());
		List<UserByChatVo> sales = salesDao.getSalesToChatByProj(vo);
		return RespMessage.success(sales);
	}
	
	
}