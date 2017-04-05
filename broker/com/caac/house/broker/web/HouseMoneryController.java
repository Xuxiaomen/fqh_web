package com.caac.house.broker.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caac.house.broker.service.HouseMoneryService;
import com.caac.house.broker.vo.BrokerDetailPageVo;
import com.caac.house.broker.vo.ClientDetailPageVo;
import com.caac.house.broker.vo.ExtractCashVo;
import com.caac.house.broker.vo.ExtractRecordPageVo;
import com.caac.house.broker.vo.ExtractRecordVo;
import com.caac.house.broker.vo.MoneryDetailVo;
import com.caac.house.broker.vo.MyRevenuePageVo;
import com.kernle.engine.entity.Page;
import com.kernle.engine.entity.User;
import com.kernle.engine.ext.RespMessage;

@Controller
@RequestMapping("/monery")
public class HouseMoneryController {

	@Resource
	private HouseMoneryService houseMoneryService;
	
	/**
	 * 
	 * @Title: getWaitCash
	 * @Description: 获取经纪人申请提现信息
	 * @param id
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/getWaitCash",method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getWaitCash(User user){
		return houseMoneryService.getWaitCash(user);
	}
	
	/**
	 * 
	 * @Title: extractCash
	 * @Description: 提取现金
	 * @param user
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/extractCash",method=RequestMethod.POST)
	@ResponseBody
	public RespMessage extractCash(User user, @RequestBody ExtractCashVo extractCashVo){
		return houseMoneryService.extractCash(user, extractCashVo);
	}
	
	@RequestMapping(value="/getExtractRecord",method=RequestMethod.POST)
	@ResponseBody
	public Page<ExtractRecordVo> getExtractRecord(User user, ExtractRecordPageVo extractRecordPageVo){
		houseMoneryService.getExtractRecord(user, extractRecordPageVo);
		return extractRecordPageVo.getResult();
	}
	
	@RequestMapping(value="/cancelExtract",method=RequestMethod.POST)
	@ResponseBody
	public RespMessage cancelExtract(User user, String id){
		return houseMoneryService.cancelExtract(user, id);
	}
	
	@RequestMapping(value="/getIncomeTotal",method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getIncomeTotal(User user){
		return houseMoneryService.getIncomeTotal(user);
	}
	
	@RequestMapping(value="/getBrokerIncome",method=RequestMethod.POST)
	@ResponseBody
	public Page<MoneryDetailVo> getBrokerIncome(User user, BrokerDetailPageVo vo){
		houseMoneryService.getBrokerIncome(user, vo);
		return vo.getResult();
	}
	
	@RequestMapping(value="/getClientIncome",method=RequestMethod.POST)
	@ResponseBody
	public Page<MoneryDetailVo> getClientIncome(User user, ClientDetailPageVo vo){
		houseMoneryService.getClientIncome(user, vo);
		return vo.getResult();
	}
	
	/**
	 * 
	 * @Title: getMyRevenue
	 * @Description: 我的收入
	 * @param page
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/getMyRevenue",method=RequestMethod.POST)
	@ResponseBody
	public Page<MoneryDetailVo> getMyRevenue(User user, MyRevenuePageVo page){
		houseMoneryService.getMyRevenue(user, page);
		return page.getResult();
	}
}


