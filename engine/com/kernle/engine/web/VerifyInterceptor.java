package com.kernle.engine.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kernle.engine.entity.ConfigEntity;
import com.kernle.engine.entity.User;
import com.kernle.engine.ext.RespMessage;
import com.kernle.engine.utils.Constant;
import com.kernle.engine.utils.JsonMapper;

public class VerifyInterceptor extends HandlerInterceptorAdapter {	
	/** 设置验证拦截器是否启动 **/
	private boolean enabled;
	
	@Resource
	private RedisTemplate<String, User> redisTemplate;
	@Resource
	private RedisTemplate<String, String> configTemplate;	
	@Resource
	private JsonMapper jsonMapper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		//判断是否启动权限验证，不启动则跳过
		if(!enabled) return true; 
		
		//判断Session是否存在微信用户的openId
		Object openId = request.getSession().getAttribute(ConfigEntity.WEIXIN_OPEN_ID);
		if(openId == null){
			//判断Cookie是否存在微信用户的openId
			Cookie[] cookies = request.getCookies();
			if(cookies != null){
				for(int i=0; i<cookies.length; i++){
					Cookie cookie = cookies[i];
					if(cookie.getName().equals(ConfigEntity.WEIXIN_OPEN_ID)){
						openId = cookie.getValue();
						request.getSession().setAttribute(ConfigEntity.WEIXIN_OPEN_ID, openId);
					}
				}
			}
			if(openId == null){
				return outputResponse(response, "登录超时，请重新登录。");
			}			
		}
		
		//判断是否为被推荐人登录
		String key = Constant.PREFIX_CONFIG + openId;
		ValueOperations<String, String> configOps = configTemplate.opsForValue();
		String configId = configOps.get(key);
		if(StringUtils.isNotEmpty(configId)) return true; 
		
		//判断是否为经纪人用户
		key = ConfigEntity.PREFIX_BROKER_SESSION + openId;
		ValueOperations<String, User> valueOps = redisTemplate.opsForValue();
		User user = valueOps.get(key);
		if(user == null){
				return outputResponse(response, "登录超时，请重新登录。");
		}		
		
		return true;		
	}
	
	private boolean outputResponse(HttpServletResponse response, String msg){
		try {
			RespMessage rmSession = new RespMessage(-10001, msg);
			jsonMapper.writeValue(response.getOutputStream(), rmSession);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
