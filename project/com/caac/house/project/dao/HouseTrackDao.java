package com.caac.house.project.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.caac.house.project.vo.HouseTrackVo;
import com.caac.house.project.vo.SupervisorVo;
import com.caac.house.project.vo.TrackInfoVo;
import com.caac.house.project.vo.TrackListVo;

@Component
public interface HouseTrackDao {

	/**
	 * @Title: getTracks
	 * @Description: 推荐房源跟进列表
	 * @param vo
	 * @return List<HouseTrackVo>
	 * @throws
	 */
	public List<HouseTrackVo> getTracks(TrackListVo vo);
	
	/**
	 * @Title: getTrackInfo
	 * @Description: 房源跟进详情
	 * @param id 房源推荐ID
	 * @return
	 * @throws
	 */
	public List<TrackInfoVo> getTrackInfo(String id);
	
	/**
	 * @Title: getSupervisor
	 * @Description: 获取当前跟单人id
	 * @param id
	 * @return
	 * @throws
	 */
	public SupervisorVo getSupervisor(String id);
	
	/**
	 * 
	 * @Title: getTracksCount
	 * @Description: 获取推荐房源跟进列表数量
	 * @param brokerId 经纪人ID
	 * @return
	 * @throws
	 */
	Integer getTracksCount(String brokerId);
}
