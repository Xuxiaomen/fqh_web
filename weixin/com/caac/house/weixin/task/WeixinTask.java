package com.caac.house.weixin.task;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.caac.house.weixin.vo.AccessTokenVo;
import com.caac.house.weixin.vo.JsapiTicketVo;
import com.kernle.engine.entity.ConfigEntity;
import com.kernle.engine.utils.DateTimeUtil;
import com.kernle.engine.utils.WeixinUtil;

@Service("WeixinTask")
public class WeixinTask {
	private Logger logger = Logger.getLogger(WeixinTask.class);
	@Resource
	private RedisTemplate<String, AccessTokenVo> accessTokenTemplate;
	@Resource
	private RedisTemplate<String, String> ticketTemplate;

	/**
	 * @Title: weixinTask
	 * @Description: 定时检查微信同步信息
	 * @throws
	 */
	/** 时间按毫秒算，启动延迟3分钟：60*1000=300000 间隔时间1分钟：60*1000=60000 **/
	@Scheduled(initialDelay = 60000, fixedDelay = 60000)
	public void weixinTask() {
		getAccessToken();
	}
	
	private void getAccessToken(){
		logger.debug(DateTimeUtil.getSysDate("yyyy-MM-dd HH:mm:ss") + " 定时检查微信同步信息");
		/** 定时同步微信的Access Token **/
		ValueOperations<String, AccessTokenVo> tokenOps = accessTokenTemplate.opsForValue();
		ValueOperations<String, String> ticketOps = ticketTemplate.opsForValue();
		AccessTokenVo accessTokenVo = tokenOps.get(ConfigEntity.PREFIX_ACCESS_TOKEN);
		if (accessTokenVo == null) {
			accessTokenVo = WeixinUtil.getAccessToken();
			Date date = new Date();
			accessTokenVo.setExpires(DateTimeUtil.getDateForSecondInterval(date, accessTokenVo.getExpires_in()));
			tokenOps.set(ConfigEntity.PREFIX_ACCESS_TOKEN, accessTokenVo);
			JsapiTicketVo ticketVo = WeixinUtil.getJsapiTicket(accessTokenVo.getAccess_token());
			if(ticketVo.getErrcode() == 0){				
				ticketOps.set(ConfigEntity.PREFIX_TICKET, ticketVo.getTicket());
			}			
		} else {
			Date date = new Date();
			Date expires = accessTokenVo.getExpires();
			long second = DateTimeUtil.getSecondIntervalBetweenDate(date, expires);
			if (second <= 300) {
				accessTokenVo = WeixinUtil.getAccessToken();
				accessTokenVo.setExpires(DateTimeUtil.getDateForSecondInterval(date, accessTokenVo.getExpires_in()));
				tokenOps.set(ConfigEntity.PREFIX_ACCESS_TOKEN, accessTokenVo);
				JsapiTicketVo ticketVo = WeixinUtil.getJsapiTicket(accessTokenVo.getAccess_token());
				if(ticketVo.getErrcode() == 0){				
					ticketOps.set(ConfigEntity.PREFIX_TICKET, ticketVo.getTicket());
				}
			}
			String ticket = ticketOps.get(ConfigEntity.PREFIX_TICKET);
			if(StringUtils.isEmpty(ticket)){
				JsapiTicketVo ticketVo = WeixinUtil.getJsapiTicket(accessTokenVo.getAccess_token());
				if(ticketVo.getErrcode() == 0){				
					ticketOps.set(ConfigEntity.PREFIX_TICKET, ticketVo.getTicket());
				}
			}			
		}
	}

}