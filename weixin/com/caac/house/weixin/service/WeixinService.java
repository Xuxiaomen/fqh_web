package com.caac.house.weixin.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.caac.house.system.dao.BrokerUserDao;
import com.caac.house.system.entity.WxRecommend;
import com.caac.house.weixin.entity.Article;
import com.kernle.engine.entity.ConfigEntity;
import com.kernle.engine.entity.User;
import com.kernle.engine.utils.LogUtil;
import com.kernle.engine.utils.WeixinMessageUtil;

@Service("weixinService")
public class WeixinService {
	private static final Logger logger = Logger.getLogger(WeixinService.class);
	
	@Resource
	private BrokerUserDao brokerUserDao;
	@Resource
	private RedisTemplate<String, String> stringTemplate;
	@Resource
	private RedisTemplate<String, User> userTemplate;
	
	/**
	 * @Title: replyMessage
	 * @Description: 统一回复微信消息
	 * @param fields
	 * @return
	 * @throws
	 */
	public String replyMessage(Map<String, String> fields){
		logger.debug("Reply Message To Weixin Service");
		// 消息类型		
		String msgType = fields.get("MsgType");
		if ("event".equals(msgType)) {
			// 事件类型
			String event = fields.get("Event");			
			if (event == null) return null;
			String fromUserName = fields.get("FromUserName");
			String reply = "";
			switch (event) {
			case ConfigEntity.WEIXIN_EVENT_SUBSCRIBE : // 添加关注
				Article article = new Article();
				String eventKey = fields.get("EventKey");
				if(StringUtils.isEmpty(eventKey)){
					article.setTitle("首创三级房亲体系 月入十万不是梦");
					article.setDescription("房亲会O2O平台，一劳永逸：您只需推荐一定数量的房亲，你推荐的房亲将成为你终身员工。高额回报：您只需提供购房客户联系方式，成交后可以获得房款2%的收益。");
					article.setPicUrl(ConfigEntity.WEIXIN_APP_URL_ROOT + "/images/welcome.png");
					article.setUrl(ConfigEntity.WEIXIN_APP_URL_ROOT + "/welcome.html");
					LogUtil.log(brokerUserDao, "0", "0", fromUserName, null, null);
				}else{
					article.setTitle("点击完成注册");
					article.setDescription("只要点击我，填写名字和手机号码后，设置密码，即刻开启财富快车。");
					article.setPicUrl(ConfigEntity.WEIXIN_APP_URL_ROOT + "/images/invite.jpg");
					String brokerId = eventKey.substring(8);
					String url = ConfigEntity.WEIXIN_APP_URL_ROOT + "/html/broker/qconfirm.html?id=" 
						+ brokerId + "&oid=" + fromUserName;					
					article.setUrl(url);
					//保存推荐关系，以便注册时判断
					brokerUserDao.deleteWxRecommend(fromUserName);
					WxRecommend wxRecommend = new WxRecommend();
					wxRecommend.setBroker(brokerId);
					wxRecommend.setOpenId(fromUserName);
					brokerUserDao.insertWxRecommend(wxRecommend);
					LogUtil.log(brokerUserDao, "0", "1", fromUserName, brokerId, null);
				}
				reply = WeixinMessageUtil.replyNewsMessage(fields, article);	
				break;
			case ConfigEntity.WEIXIN_EVENT_UNSUBSCRIBE : // 取消关注				
				// 删除REDIS中的对应OPENID
				String key = ConfigEntity.PREFIX_BROKER_SESSION + fromUserName;
				userTemplate.delete(key);
				// 删除表中的对应OPENID
				User user = brokerUserDao.getUserBaseByOpenId(fromUserName);
				brokerUserDao.deleteOpenId(fromUserName);
				// 删除微信推荐关系
				brokerUserDao.deleteWxRecommend(fromUserName);
				// 添加取消关注日志				
				LogUtil.log(brokerUserDao, "1", null, fromUserName, user);
				break;
			}
			return reply;
		}
		return null;
	}
	
	/**
	 * @Title: syncUserToRedis
	 * @Description: 判断并同步与openId对应的用户到redis中
	 * @param openId
	 * @return
	 * @throws
	 */
	public User syncBrokerToRedis(String openId){
		// 判断是否已加载到redis中
		ValueOperations<String, User> userOps = userTemplate.opsForValue();
		String key = ConfigEntity.PREFIX_BROKER_SESSION + openId;
		User user = userOps.get(key);
		if (user == null) {
			// 否，则查看经纪人微信联系表中是否存在，存在则加载
			user = brokerUserDao.getUserByOpenId(openId);
			if (user == null){
				return null;
			}
			// 将用户信息加载到redis中
			userOps.set(key, user);
		}
		return user;
	}
	
}
