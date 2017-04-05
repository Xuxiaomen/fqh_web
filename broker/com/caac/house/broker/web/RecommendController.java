package com.caac.house.broker.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caac.house.broker.service.RecommendService;
import com.caac.house.broker.vo.ClientPageVo;
import com.caac.house.broker.vo.ClientVo;
import com.caac.house.broker.vo.InviteVo;
import com.kernle.engine.entity.Page;
import com.kernle.engine.entity.User;
import com.kernle.engine.ext.RespMessage;

@Controller
@RequestMapping("/recommend")
public class RecommendController {

	@Resource
	private RecommendService recommendService;
	
	/**
	 * @Title: getConfirmInfo
	 * @Description: 荐客页面数据
	 * @param request
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/getConfirmInfo", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getConfirmInfo(HttpServletRequest request){
		return recommendService.getConfirmInfo(request);
	}

	@RequestMapping(value="/apply", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage apply(User user,HttpServletRequest request){
		return recommendService.apply(user, request);
	}
	
	@RequestMapping(value="/invite", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage invite(@Valid InviteVo vo){
		return recommendService.invite(vo);
	}
	
	@RequestMapping(value="/getClient", method=RequestMethod.POST)
	@ResponseBody
	public Page<ClientVo> getClient(User user, ClientPageVo vo){
		recommendService.getClient(user, vo);
		return vo.getResult();
	}
	
}
