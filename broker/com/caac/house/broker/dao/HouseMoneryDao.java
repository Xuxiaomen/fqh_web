package com.caac.house.broker.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.caac.house.broker.entity.HouseMonery;
import com.caac.house.broker.entity.MoneryDetails;
import com.caac.house.broker.vo.BankVo;
import com.caac.house.broker.vo.BargainToExtractRecordVo;
import com.caac.house.broker.vo.BrokerDetailPageVo;
import com.caac.house.broker.vo.CashAccountVo;
import com.caac.house.broker.vo.CashDetailsVo;
import com.caac.house.broker.vo.ClientDetailPageVo;
import com.caac.house.broker.vo.DetailsByIdsVo;
import com.caac.house.broker.vo.ExtractByIdVo;
import com.caac.house.broker.vo.ExtractRecordPageVo;
import com.caac.house.broker.vo.ExtractVo;
import com.caac.house.broker.vo.HouseCashVo;
import com.caac.house.broker.vo.IncomeTotalVo;
import com.caac.house.broker.vo.MoneryDetailVo;
import com.caac.house.broker.vo.MyRevenuePageVo;
import com.caac.house.broker.vo.UpdateApplyVo;

@Component
public interface HouseMoneryDao {

	/**
	 * @Title: save
	 * @Description:新增房币 
	 * @param hm
	 * @throws
	 */
	public void save(HouseMonery hm);
	
	/**
	 * @Title: saveDetails
	 * @Description: 新增房币明细
	 * @param mgd
	 * @throws
	 */
	public void saveDetails(MoneryDetails mgd);
	
	/**
	 * @Title: addMoneryByBrokerId
	 * @Description: 增加5点房币
	 * @param brokerId 经纪人id
	 * @throws
	 */
	public void addMoneryByBrokerId(String brokerId);
	
	/**
	 * @Title: getMoneryIdByBrokerId
	 * @Description: 根据经纪人获取房币主表ID
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public String getMoneryIdByBrokerId(String brokerId);
	
	/**
	 * @Title: getCashAccount
	 * @Description: 获取提现账户金额
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public CashAccountVo getCashAccount(String brokerId);
	
	/**
	 * @Title: getHouseByBargainToCash
	 * @Description: 获取可提现的成交房源信息
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public List<HouseCashVo> getHouseByBargainToCash(String brokerId);
	
	/**
	 * @Title: getDetailsByIds
	 * @Description: 根据ID获取未提现的成交房币明细信息 
	 * @param ids
	 * @return
	 * @throws
	 */
	public List<DetailsByIdsVo> getDetailsByIds(List<String> ids);
	
	/**
	 * 
	 * @Title: insertExtract
	 * @Description: 添加提现表记录
	 * @param vo
	 * @throws
	 */
	public void insertExtract(ExtractVo vo);
	
	/**
	 * @Title: insertCashDetails
	 * @Description: 添加提现房源记录
	 * @param vo
	 * @throws
	 */
	public void insertCashDetails(CashDetailsVo vo);
	
	/**
	 * @Title: updateApply
	 * @Description: 添加申请总金额，并扣除申请金额
	 * @param updateApplyVo
	 * @throws
	 */
	public void updateApply(UpdateApplyVo updateApplyVo);
	
	/**
	 * 
	 * @Title: getExtractRecord
	 * @Description: 获取申请提现记录
	 * @return
	 * @throws
	 */
	public List<ExtractVo> getExtractRecord(ExtractRecordPageVo vo);
	
	/**
	 * @Title: getBargainToExtractRecord
	 * @Description: 获取申请提现的房源
	 * @param brokerId
	 * @param extractId
	 * @return
	 * @throws
	 */
	public List<BargainToExtractRecordVo> getBargainToExtractRecord(String brokerId, String extractId); 
	
	/**
	 * @Title: getExtractType
	 * @Description: 获取提现记录状态
	 * @param id
	 * @return
	 * @throws
	 */
	public ExtractByIdVo getExtractById(String id);
	
	/**
	 * 
	 * @Title: cancelExtract
	 * @Description: 取消提现
	 * @param id
	 * @throws
	 */
	public void cancelExtract(String id);
	
	/**
	 * 
	 * @Title: resetMonery
	 * @Description: 取消提现后恢复房币表的金额
	 * @param brokerId 经纪人
	 * @param monery 金额
	 * @throws
	 */
	public void resetMonery(UpdateApplyVo updateApplyVo);
	
	/**
	 * 
	 * @Title: getAccountByBroker
	 * @Description: 获取银行账号
	 * @param brokerId 经纪人id
	 * @return
	 * @throws
	 */
	BankVo getAccountByBroker(String brokerId);
	
	/**
	 * @Title: getBrokerIncome
	 * @Description: 房亲收入列表
	 * @param vo
	 * @return
	 * @throws
	 */
	public List<MoneryDetailVo> getBrokerIncome(BrokerDetailPageVo vo);
	
	/**
	 * @Title: getClientIncome
	 * @Description: 客户收入列表
	 * @param vo
	 * @return
	 * @throws
	 */
	public List<MoneryDetailVo> getClientIncome(ClientDetailPageVo vo);
	
	/**
	 * @Title: getIncomeTotal
	 * @Description: 收入列表汇总
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public IncomeTotalVo getIncomeTotal(String brokerId);
	
	/**
	 * 
	 * @Title: getMyRevenue
	 * @Description: 我的收入
	 * @param page
	 * @return
	 * @throws
	 */
	List<MoneryDetailVo> getMyRevenue(MyRevenuePageVo page);
	
	/**
	 * 
	 * @Title: getCashPhone
	 * @Description: 获取提现需要发送短信的相关人员
	 * @return
	 * @throws
	 */
	List<String> getCashPhone();
	
}
