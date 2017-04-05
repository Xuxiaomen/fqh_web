package com.kernle.engine.utils;

import com.caac.house.system.dao.BrokerUserDao;
import com.caac.house.system.entity.BrokerLog;
import com.kernle.engine.entity.User;

public class LogUtil {
	
	public static void log(BrokerUserDao dao, String type, String subType, String openId, String brokerId, String phone){
		BrokerLog brokerLog = new BrokerLog();
		brokerLog.setBrokerId(brokerId);
		brokerLog.setPhone(phone);
		brokerLog.setOpenId(openId);
		brokerLog.setType(type);
		brokerLog.setSubType(subType);		
		dao.insertBrokerLog(brokerLog);
	}
	
	public static void log(BrokerUserDao dao, String type, String subType, String openId, User user){
		String brokerId = null;
		String phone = null;
		if(user != null){
			brokerId = user.getId();
			phone = user.getPhone();
		}
		log(dao, type, subType, openId, brokerId, phone);
	}
}
