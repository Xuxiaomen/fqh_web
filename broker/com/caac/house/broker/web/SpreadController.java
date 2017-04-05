package com.caac.house.broker.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caac.house.broker.service.SpreadService;
import com.caac.house.broker.vo.ScrollInfoVo;
import com.caac.house.broker.vo.ScrollParamVo;
import com.kernle.engine.entity.Page;
import com.kernle.engine.ext.RespMessage;

@Controller
@RequestMapping("/spread")
public class SpreadController {
	
	@Resource
	private SpreadService spreadService;

	/**
	 * 
	 * @Title: getIndexInfo
	 * @Description: 获取首页推广滚动信息
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/getIndexInfo", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getIndexInfo(){
		return spreadService.getIndexInfo();
	}
	
	/**
	 * 
	 * @Title: getScrollList
	 * @Description: 获取滚动信息列表
	 * @param scrollParamVo
	 * @throws
	 */
	@RequestMapping(value="/getScrollList", method=RequestMethod.POST)
	@ResponseBody
	public Page<ScrollInfoVo> getScrollList(ScrollParamVo scrollParamVo){
		spreadService.getScrollList(scrollParamVo);
		return scrollParamVo.getResult();
	}
}
