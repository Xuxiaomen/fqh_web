package com.caac.house.project.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caac.house.project.service.HouseTrackService;
import com.caac.house.project.vo.HouseTrackVo;
import com.caac.house.project.vo.TrackListVo;
import com.kernle.engine.entity.Page;
import com.kernle.engine.entity.User;
import com.kernle.engine.ext.RespMessage;

@Controller
@RequestMapping("/track")
public class HouseTrackController {
	
	@Resource
	private HouseTrackService houseTrackService;

	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Page<HouseTrackVo> getTracks(User user, TrackListVo vo){
		vo.setBrokerId(user.getId());
		houseTrackService.getTracks(vo);
		return vo.getResult();
	}
	
	@RequestMapping(value="/info",method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getTrackInfo(String id){
		return houseTrackService.getTrackInfo(id);
	}
	
	/**
	 * 
	 * @Title: getTracksCount
	 * @Description: 获取推荐房源跟进列表数量
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/getTracksCount",method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getTracksCount(User user){
		return houseTrackService.getTracksCount(user.getId());
	}
}
