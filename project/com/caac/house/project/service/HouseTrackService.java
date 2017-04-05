package com.caac.house.project.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.caac.house.project.dao.HouseTrackDao;
import com.caac.house.project.vo.SupervisorVo;
import com.caac.house.project.vo.TrackInfoVo;
import com.caac.house.project.vo.TrackListVo;
import com.caac.house.project.vo.TrackVo;
import com.kernle.engine.ext.RespMessage;

@Service
public class HouseTrackService {

	@Resource
	private HouseTrackDao houseTrackDao;
	
	public void getTracks(TrackListVo vo){
		houseTrackDao.getTracks(vo);
	}
	
	public RespMessage getTrackInfo(String id){
		if(StringUtils.isEmpty(id)){
			return RespMessage.error("关键字不能为空");
		}
		TrackVo vo = new TrackVo();
		List<TrackInfoVo> list = houseTrackDao.getTrackInfo(id);
		vo.setTrackInfo(list);
		SupervisorVo supervisorVo = houseTrackDao.getSupervisor(id);
		vo.setSupervisorInfo(supervisorVo);
		return RespMessage.success(vo);
	}

	/**
	 * 
	 * @Title: getTracksCount
	 * @Description: 获取推荐房源跟进列表数量
	 * @param brokerId 经纪人ID
	 * @return
	 * @throws
	 */
	public RespMessage getTracksCount(String brokerId){
		return RespMessage.success(houseTrackDao.getTracksCount(brokerId));
	}
}
