package com.caac.house.project.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caac.house.project.service.EvaluateService;
import com.caac.house.project.vo.EvaluateVo;
import com.kernle.engine.ext.RespMessage;

@Controller
@RequestMapping("/evaluate")
public class EvaluateController {
	
	@Resource
	private EvaluateService evaluateService;
	
	@RequestMapping(value="/insertEvaluate", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage insertEvaluate(@Valid EvaluateVo vo){
		return evaluateService.insertEvaluate(vo);
	}
	
	/**
	 * @Title: findEvaluate
	 * @Description: 查询推荐跟单服务评价
	 * @param linkId 推荐环节id
	 * @param userId 置业顾问id
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/findEvaluate", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage findEvaluate(String linkId,String userId){
		return evaluateService.findEvaluate(linkId,userId);
	}
}
