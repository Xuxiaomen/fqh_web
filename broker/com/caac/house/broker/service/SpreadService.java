package com.caac.house.broker.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.caac.house.broker.dao.SpreadDao;
import com.caac.house.broker.vo.IndexInfoVo;
import com.caac.house.broker.vo.ScrollParamVo;
import com.kernle.engine.ext.RespMessage;

@Service
public class SpreadService {

	@Resource
	private SpreadDao spreadDao;
	
	/**
	 * 
	 * @Title: getIndexInfo
	 * @Description: 获取首页推广滚动信息
	 * @return
	 * @throws
	 */
	public RespMessage getIndexInfo(){
		IndexInfoVo vo = new IndexInfoVo();
		vo.setScroll(spreadDao.getScrollInfo());
		vo.setSpread(spreadDao.getPictureInfo());
		return RespMessage.success(vo);
	}
	
	/**
	 * 
	 * @Title: getScrollList
	 * @Description: 获取滚动信息列表
	 * @param scrollParamVo
	 * @throws
	 */
	public void getScrollList(ScrollParamVo scrollParamVo){
		spreadDao.getScrollList(scrollParamVo);
	}
}
