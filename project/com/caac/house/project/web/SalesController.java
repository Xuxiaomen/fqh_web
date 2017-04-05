package com.caac.house.project.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caac.house.project.service.SalesService;
import com.caac.house.project.vo.UserByChatListVo;
import com.caac.house.project.vo.UserEvaluatePageVo;
import com.caac.house.project.vo.UserEvaluateVo;
import com.caac.house.project.vo.UserPageVo;
import com.caac.house.project.vo.UserVo;
import com.kernle.engine.entity.Page;
import com.kernle.engine.entity.User;
import com.kernle.engine.ext.RespMessage;

@Controller
@RequestMapping("/sales")
public class SalesController {
	@Resource
	private SalesService salesService;
	
	/**
	 * 
	 * @Title: getUserByGroup
	 * @Description: 获取指定楼盘下的置业顾问
	 * @param vo 楼盘所属项目组
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/getUserByGroup",method=RequestMethod.POST)
	@ResponseBody
	public Page<UserVo> getUserByGroup(UserPageVo vo){
		salesService.getUserByGroup(vo);
		return vo.getResult();
	}
	
	/**
	 * 
	 * @Title: getConsultantInfo
	 * @Description: 经纪人查看置业顾问信息集合
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/getConsultantInfo",method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getConsultantInfo(String userId){
		return salesService.getConsultantInfo(userId);
	}
	
	/**
	 * 
	 * @Title: getEvaluate
	 * @Description: 获取经纪人对置业顾问评论
	 * @param page
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/getEvaluate",method=RequestMethod.POST)
	@ResponseBody
	public Page<UserEvaluateVo> getEvaluate(UserEvaluatePageVo page){
		salesService.getEvaluate(page);
		return page.getResult();
	}
	
	@RequestMapping(value="/getSalesByProj",method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getSalesByProj(User user,  UserByChatListVo vo){
		return salesService.getSalesByProj(user, vo); 
	}
	
}
