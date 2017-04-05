package com.caac.house.broker.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.caac.house.broker.vo.ScrollInfoVo;
import com.caac.house.broker.vo.ScrollListVo;
import com.caac.house.broker.vo.ScrollParamVo;
import com.caac.house.broker.vo.SpreadVo;

@Component
public interface SpreadDao {

	/**
	 * 
	 * @Title: getScrollInfo
	 * @Description: 获取滚动信息
	 * @return
	 * @throws
	 */
	List<ScrollInfoVo> getScrollInfo();
	
	/**
	 * 
	 * @Title: getPictureInfo
	 * @Description: 获取推广图片
	 * @return
	 * @throws
	 */
	List<SpreadVo> getPictureInfo();
	
	/**
	 * 
	 * @Title: getScrollList
	 * @Description: 获取滚动信息列表
	 * @return
	 * @throws
	 */
	List<ScrollListVo> getScrollList(ScrollParamVo scrollParamVo);
}
