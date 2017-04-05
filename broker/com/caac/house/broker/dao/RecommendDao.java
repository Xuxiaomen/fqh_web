package com.caac.house.broker.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.caac.house.broker.entity.Recommend;
import com.caac.house.broker.vo.ClientPageVo;
import com.caac.house.broker.vo.ClientVo;
import com.caac.house.broker.vo.ConfirmInfoVo;
import com.caac.house.system.entity.BrokerUser;

@Component
public interface RecommendDao {

	/**
	 * @Title: getExistPhone
	 * @Description: 判断推荐表中指定的手机号码数量且次数不能超5次
	 * @param phone 手机号码
	 * @return 返回符合指定手机号码数量
	 * @throws
	 */
	public int getExistPhone(String phone);
	
	/**
	 * @Title: getInfo
	 * @Description: 获取荐客信息
	 * @param id 
	 * @return Recommend
	 * @throws
	 */
	public Recommend getInfo(String id);
	
	/**
	 * @Title: appendBroker
	 * @Description: 添加到经纪人表
	 * @param recommend
	 * @throws
	 */
	public void appendBroker(BrokerUser brokerUser);
	
	/**
	 * @Title: config
	 * @Description: 荐客确认
	 * @param id
	 * @throws
	 */
	public void config(String id);
	
	/**
	 * @Title: getConfirmInfo
	 * @Description: 荐客页面数据
	 * @param recommendId
	 * @return
	 * @throws
	 */
	public ConfirmInfoVo getConfirmInfo(String recommendId);
	
	/**
	 * @Title: apply
	 * @Description: 荐客申请
	 * @param recommend
	 * @throws
	 */
	public void apply(Recommend recommend);
	
	/**
	 * @Title: getBroker
	 * @Description: 判断经纪人是否存在
	 * @param id
	 * @param phone
	 * @return
	 * @throws
	 */
	public int getBroker(String id, String phone);
	
	/**
	 * @Title: getClient
	 * @Description: 我的客户列表
	 * @param vo
	 * @return
	 * @throws
	 */
	public List<ClientVo> getClient(ClientPageVo vo);
	
	/** 
	 * @Title: batchInsertApply
	 * @Description: 批量新增推荐
	 * @param list
	 * @throws
	 */
	void batchInsertApply(List<Recommend> list);
	
	public int getBargainCount(String brokerId);
}
