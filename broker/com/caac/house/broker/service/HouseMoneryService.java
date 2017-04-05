package com.caac.house.broker.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.caac.house.broker.dao.HouseMoneryDao;
import com.caac.house.broker.dao.RecommendDao;
import com.caac.house.broker.vo.BankVo;
import com.caac.house.broker.vo.BargainToExtractRecordVo;
import com.caac.house.broker.vo.BrokerDetailPageVo;
import com.caac.house.broker.vo.CashAccountVo;
import com.caac.house.broker.vo.CashDetailsVo;
import com.caac.house.broker.vo.ClientDetailPageVo;
import com.caac.house.broker.vo.DetailsByIdsVo;
import com.caac.house.broker.vo.ExtractByIdVo;
import com.caac.house.broker.vo.ExtractCashVo;
import com.caac.house.broker.vo.ExtractInfoVo;
import com.caac.house.broker.vo.ExtractRecordPageVo;
import com.caac.house.broker.vo.ExtractRecordVo;
import com.caac.house.broker.vo.ExtractVo;
import com.caac.house.broker.vo.HouseCashVo;
import com.caac.house.broker.vo.IncomeTotalVo;
import com.caac.house.broker.vo.MyRevenuePageVo;
import com.caac.house.broker.vo.UpdateApplyVo;
import com.kernle.engine.entity.User;
import com.kernle.engine.ext.RespMessage;
import com.kernle.engine.utils.Constant;
import com.kernle.engine.utils.SmsUtil;

@Service("houseMoneryService")
public class HouseMoneryService {
	@Resource
	private HouseMoneryDao houseMoneryDao;
	@Resource
	private RecommendDao recommendDao;	
	@Resource
	private RedisTemplate<String, String> stringTemplate;
	
	@Value("${sms.extract}")
	private String smsExtract;
	
	public RespMessage getWaitCash(User user){
		CashAccountVo cashAccountVo = houseMoneryDao.getCashAccount(user.getId());		
		if(cashAccountVo == null){
			return RespMessage.error("现金账户信息不存在，请联系房亲会客服。");
		}
		ExtractInfoVo extractInfoVo = new ExtractInfoVo();
		extractInfoVo.setPhone(user.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
		extractInfoVo.setAccount(cashAccountVo);
		List<HouseCashVo> houseCashVo = houseMoneryDao.getHouseByBargainToCash(user.getId());
		extractInfoVo.setHouse(houseCashVo);
		return RespMessage.success(extractInfoVo);
	}
	
	/**
	 * 
	 * @Title: extractCash
	 * @Description: 提取现金
	 * @param id
	 * @param cash
	 * @return
	 * @throws
	 */
	public RespMessage extractCash(User user, ExtractCashVo extractCashVo){
		if(StringUtils.isEmpty(extractCashVo.getCode())){
			return RespMessage.error("请输入验证码");
		}
		
		int cash = 0;
		if(extractCashVo.getCash() != null){
			cash = extractCashVo.getCash();
		}
		if(cash == 0 && extractCashVo.getIds().size() == 0){
			return RespMessage.error("请输入提现金额或选择成交收益");
		}
		
		if(cash > 0 && cash < 100){
			return RespMessage.error("邀请房亲的提现金额不能低于100元");
		}
		
		if(cash % 100 != 0){
			return RespMessage.error("邀请房亲的提现金额必须为100的倍数");
		}
		
		ValueOperations<String, String> smsOps = stringTemplate.opsForValue();
		String randow = smsOps.get(Constant.PREFIX_SMS + user.getPhone());
		if(!extractCashVo.getCode().equals(randow)){
			return RespMessage.error("您输入的验证码有误，请确认后重新输入");
		}
		
		CashAccountVo cashAccountVo = houseMoneryDao.getCashAccount(user.getId());
		if(cashAccountVo.getTotalBargain() <= 0){
			return RespMessage.error("首次提现须有购房客户通过房亲会成交");
		}		
		if(cashAccountVo.getCanCash() <= 0){
			return RespMessage.error("账户内没有可提现金额");
		}		
		if(cash > cashAccountVo.getMoneryBroker()){
			return RespMessage.error("邀请房亲的提现金额不能高于" + cashAccountVo.getMoneryBroker());
		}
		
		BankVo bankVo = houseMoneryDao.getAccountByBroker(user.getId());
		if(bankVo == null){
			return RespMessage.error("请先完善个人银行账户信息.");
		}
		if(StringUtils.isBlank(bankVo.getAccount())){
			return RespMessage.error("请先完善银行账号.");
		}
		if(StringUtils.isBlank(bankVo.getBank())){
			return RespMessage.error("请先完善开户行名称.");
		}
		if(StringUtils.isBlank(bankVo.getIdcard())){
			return RespMessage.error("请先完善身份证号.");
		}
		
		List<DetailsByIdsVo> detailsByIdsVo = null;
		int applyBargain = 0; 
		if(extractCashVo.getIds() != null && extractCashVo.getIds().size() > 0){
			detailsByIdsVo = houseMoneryDao.getDetailsByIds(extractCashVo.getIds());
			if(detailsByIdsVo.size() != extractCashVo.getIds().size()){
				return RespMessage.error("提现申请中房源已涉及别的申请提交，请刷新页面再提交申请");
			}
			for(int i=0; i<detailsByIdsVo.size(); i++){
				applyBargain += detailsByIdsVo.get(i).getMonery();
			}			
		}
		
		ExtractVo extractVo = new ExtractVo();
		extractVo.setId(UUID.randomUUID().toString());
		extractVo.setBrokerId(user.getId());
		extractVo.setApplyBroker(cash);
		extractVo.setApplyBargain(applyBargain);
		extractVo.setApplyMonery(applyBargain + cash);
		houseMoneryDao.insertExtract(extractVo);
		
		if(applyBargain > 0){
			for(int i=0; i<detailsByIdsVo.size(); i++){
				CashDetailsVo cashDetailsVo = new CashDetailsVo(); 
				cashDetailsVo.setExtractCashId(extractVo.getId());
				cashDetailsVo.setMoneryDetailsId(detailsByIdsVo.get(i).getId());
				cashDetailsVo.setRecommend(detailsByIdsVo.get(i).getRecommend());
				cashDetailsVo.setMonery(detailsByIdsVo.get(i).getMonery());
				houseMoneryDao.insertCashDetails(cashDetailsVo);				
			}
		}
		
		UpdateApplyVo updateApplyVo = new UpdateApplyVo();
		updateApplyVo.setBrokerId(user.getId());
		updateApplyVo.setApply(extractVo.getApplyMonery());
		updateApplyVo.setApplyBroker(extractVo.getApplyBroker());
		updateApplyVo.setApplyBargain(extractVo.getApplyBargain());
		houseMoneryDao.updateApply(updateApplyVo);
		
		stringTemplate.delete(Constant.PREFIX_SMS + user.getPhone());
		
		//给相应人员发送短信
		List<String> phones = houseMoneryDao.getCashPhone();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		paramMap.put("a", user.getName());
		paramMap.put("b", calendar.get(Calendar.MONTH) + 1);
		paramMap.put("c", calendar.get(Calendar.DATE));
		paramMap.put("d", extractVo.getApplyMonery());
		if(phones != null && phones.size() > 0){
			for(int i=0; i<phones.size(); i++){
				SmsUtil.send(phones.get(i), "房亲会", paramMap, smsExtract);
			}
		}
		
		return RespMessage.success();
	}
	
	public void getExtractRecord(User user, ExtractRecordPageVo extractRecordPageVo){
		extractRecordPageVo.setBrokerId(user.getId());
		houseMoneryDao.getExtractRecord(extractRecordPageVo);
		List<ExtractRecordVo> extractRecord = extractRecordPageVo.getResult().getRows();
		for(int i=0; i<extractRecord.size(); i++){
			if(extractRecord.get(i).getApplyBargain() > 0){
				List<BargainToExtractRecordVo> vo = houseMoneryDao.getBargainToExtractRecord(user.getId(), extractRecord.get(i).getId());
				extractRecord.get(i).setBargain(vo);
			}
		}
	}
	
	public RespMessage cancelExtract(User user, String id) {
		if(StringUtils.isBlank(id)){
			return RespMessage.error();
		}
		
		ExtractByIdVo extractByIdVo = houseMoneryDao.getExtractById(id);
		if(extractByIdVo == null || StringUtils.isBlank(extractByIdVo.getType())){
			return RespMessage.error();
		}
		if("1".equals(extractByIdVo.getType())){
			return RespMessage.error("此提现申请已被取消，不能再次取消");
		}
		if("2".equals(extractByIdVo.getType())){
			return RespMessage.error("此提现申请审核失败，不能被取消");
		}
		if("3".equals(extractByIdVo.getType())){
			return RespMessage.error("此提现申请已被审核通过，不能被取消");
		}
		if("4".equals(extractByIdVo.getType())){
			return RespMessage.error("此提现申请已成功支付了成交收益，不能被取消");
		}
		
		houseMoneryDao.cancelExtract(id);
		
		UpdateApplyVo updateApplyVo = new UpdateApplyVo();
		updateApplyVo.setBrokerId(user.getId());
		updateApplyVo.setApply(extractByIdVo.getApplyMonery());
		updateApplyVo.setApplyBroker(extractByIdVo.getApplyBroker());
		updateApplyVo.setApplyBargain(extractByIdVo.getApplyBargain());		
		houseMoneryDao.resetMonery(updateApplyVo);
		
		return RespMessage.success();
	}
	
	public RespMessage getIncomeTotal(User user){
		IncomeTotalVo vo = houseMoneryDao.getIncomeTotal(user.getId());
		return RespMessage.success(vo);
	}
	
	public void getBrokerIncome(User user, BrokerDetailPageVo vo){
		vo.setBroker(user.getId());
		houseMoneryDao.getBrokerIncome(vo);
	}
	
	public void getClientIncome(User user, ClientDetailPageVo vo){
		vo.setBroker(user.getId());
		houseMoneryDao.getClientIncome(vo);
	}
	
	/**
	 * 
	 * @Title: getMyRevenue
	 * @Description: 我的收入
	 * @param page
	 * @return
	 * @throws
	 */
	public void getMyRevenue(User user, MyRevenuePageVo page){
		page.setBroker(user.getId());
		houseMoneryDao.getMyRevenue(page);
	}
}

