package com.caac.house.weixin.service;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.caac.house.weixin.vo.TicketVo;
import com.kernle.engine.entity.ConfigEntity;
import com.kernle.engine.ext.RespMessage;
import com.kernle.engine.utils.WeixinSignUtil;

@Service("jssdkService")
public class JssdkService {
	@Resource
	private RedisTemplate<String, String> template;

	public RespMessage ticket(String url){
		if(StringUtils.isEmpty(url)){
			return RespMessage.error("发起调用页面的网址为空，获取Ticket失败。");
		}
		ValueOperations<String, String> valueOps = template.opsForValue();
		String ticket = valueOps.get(ConfigEntity.PREFIX_TICKET);
		if(StringUtils.isEmpty(ticket)){
			return RespMessage.error("获取Ticket失败");
		}
		TicketVo ticketVo = new TicketVo();
		ticketVo.setAppId(ConfigEntity.WEIXIN_APPID);
		ticketVo.setNonceStr(valueOps.get(ConfigEntity.PREFIX_NONCE));
		ticketVo.setTimestamp(valueOps.get(ConfigEntity.PREFIX_TIMESTAMP));
		String signature = WeixinSignUtil.ticketSignature(ticketVo.getTimestamp(), 
			ticketVo.getNonceStr(), ticket, url);
		ticketVo.setSignature(signature);
		
		return RespMessage.success(ticketVo);
	}

}
