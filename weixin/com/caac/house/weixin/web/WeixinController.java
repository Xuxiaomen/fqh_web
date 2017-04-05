package com.caac.house.weixin.web;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caac.house.system.dao.BrokerUserDao;
import com.caac.house.system.service.BrokerUserService;
import com.caac.house.weixin.service.WeixinService;
import com.caac.house.weixin.vo.Oauth2Vo;
import com.kernle.engine.entity.ConfigEntity;
import com.kernle.engine.entity.User;
import com.kernle.engine.ext.RespMessage;
import com.kernle.engine.utils.LogUtil;
import com.kernle.engine.utils.WeixinSignUtil;
import com.kernle.engine.utils.WeixinUtil;

@Controller
@RequestMapping("/weixin")
public class WeixinController {
	private static final Logger logger = Logger.getLogger(WeixinController.class);
	
	@Resource
	private WeixinService weixinService;
	@Resource
	private RedisTemplate<String,String> stringTemplate;
	@Resource
	private BrokerUserService brokerUserService;
	@Resource
	private BrokerUserDao brokerUserDao;
	
	/**
	 * @Title: get
	 * @Description:提供微信验证途径 
	 * @param request
	 * @return
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String get(HttpServletRequest request){
		return WeixinUtil.checkSignature(request);
	}
	
	/**
	 * @Title: post
	 * @Description: 微信消息统一处理函数
	 * @param request
	 * @return
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String post(HttpServletRequest request){
		// 验证微信发送过来的消息
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		if (WeixinSignUtil.checkSignature(signature, timestamp, nonce)) {
			ValueOperations<String, String> value = stringTemplate.opsForValue();
			//存储signature
			value.set(ConfigEntity.PREFIX_SIGNATURE, signature);
			//存储timestamp
			value.set(ConfigEntity.PREFIX_TIMESTAMP, timestamp);
			//存储nonce
			value.set(ConfigEntity.PREFIX_NONCE, nonce);
			// 验证通过后，进入消息接收函数
			Map<String, String> fields = WeixinUtil.receiveMessage(request);
			if(fields == null || fields.size() <= 0){
				logger.debug("接收消息的参数为空，返回null");
				return null;
			}
			// 回复消息
			String result = weixinService.replyMessage(fields);
			return result;
		}
		logger.debug("非法访问，略过执行业务代码");
		return null;		
	}
	
	/**
	 * @Title: code
	 * @Description: 此方法主要控制微信的菜单，获取微信的openId，详细步骤为下：
	 * 1、访问Session，取出名为"openId"的对象，若此对象不存在，则跳转到2，存在则（此时应写入cookie）提取对应用户相关信息，若不存在相关信息，则跳转到登录页面。
	 * 2、访问Cookie，取出名为"openId"的对象，此对象不存在，则跳到3，存在则（此时应写入Session）提取对应用户相关信息，若不存在相关信息，则跳转到登录页面。
	 * 3、访问微信接口，获取当前用户的openId，获取到后，写入Session和Cookie中，并跳转到对应页面。
	 * @param request
	 * @param response
	 * @throws
	 */
	@RequestMapping(value="/code")
	public void code(HttpServletRequest request, HttpServletResponse response){

		//跳转URL
		String state = request.getParameter("state");
		//验证类型
		String type = request.getParameter("type");
		// 若跳转的URL为空，则直接返回
		if(StringUtils.isEmpty(state)) return;	
		Object openId = WeixinUtil.getOpenId(request);
		String url = state;
		//访问微信接口，获取当前用户的openId
		if(openId == null){
			String parameter = "?state=" + state + "&type=" + type;
			url = WeixinUtil.getOauth2CodeUrl(parameter);
		}else{
			String id = (String) openId;
			//设置Session
			request.getSession().setAttribute(ConfigEntity.WEIXIN_OPEN_ID, id);
			//设置Cookie
			Cookie cookie = new Cookie(ConfigEntity.WEIXIN_OPEN_ID, id);
			cookie.setHttpOnly(false);
			cookie.setPath("/");
			cookie.setMaxAge(31536000);
			response.addCookie(cookie);
			//判断当前用户是否为经纪人
			if(StringUtils.isNotEmpty(type) && "1".equals(type)){
				//同步openId的用户信息到redis
				User user = weixinService.syncBrokerToRedis(id);
				if(user == null || "1".equals(user.getAutoLogin())){
					url = "/login.html";
				}else{
					brokerUserDao.updateLoginTime(user.getId());
					LogUtil.log(brokerUserDao, "2", null, id, user);
				}
			}
		}
			
		// 跳转到指定页面
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * @Title: openId
	 * @Description: 通过code换取微信openId，并存到Session、Cookie中
	 * @param request
	 * @param response
	 * @throws
	 */
	@RequestMapping(value="/openId")
	public void openId(HttpServletRequest request, HttpServletResponse response){
		//获取跳转路径		
		String state = request.getParameter("state");
		if(StringUtils.isEmpty(state)) return;
		//获取微信接口返回的code参数
		String code = request.getParameter("code");
		//参数code为空，则直接返回
		if (StringUtils.isEmpty(code)) return;
		//通过微信接口获取用户openID
		Oauth2Vo oauth2Vo = WeixinUtil.getOauth2OpenId(code);
		//获取失败，则直接返回
		if(oauth2Vo == null || StringUtils.isEmpty(oauth2Vo.getOpenid())){return;}
		//把openId存放到Session
		request.getSession().setAttribute(ConfigEntity.WEIXIN_OPEN_ID, oauth2Vo.getOpenid());
		//把openId存放到Cookie
		Cookie cookie = new Cookie(ConfigEntity.WEIXIN_OPEN_ID, oauth2Vo.getOpenid());
		cookie.setHttpOnly(false);
		cookie.setPath("/");
		cookie.setMaxAge(31536000);
		response.addCookie(cookie);
		//初始化url
		String url = state;
		//判断是否要进行验证
		String type = request.getParameter("type");	
		//判断当前用户是否为经纪人
		if(StringUtils.isNotEmpty(type) && "1".equals(type)){
			//同步当前微信用户信息到redis
			User user = weixinService.syncBrokerToRedis(oauth2Vo.getOpenid());
			if(user == null || "1".equals(user.getAutoLogin())){
				url = "/login.html";
			}else{
				brokerUserDao.updateLoginTime(user.getId());
				LogUtil.log(brokerUserDao, "2", null, oauth2Vo.getOpenid(), user);
			}
		}
		
		//跳转到指定页面
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/share", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage share(String id){
		return brokerUserService.share(id);
	}
	
}
