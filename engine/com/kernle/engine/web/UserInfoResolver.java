package com.kernle.engine.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.caac.house.system.dao.BrokerUserDao;
import com.kernle.engine.entity.ConfigEntity;
import com.kernle.engine.entity.User;
import com.kernle.engine.ext.BusinessException;

public class UserInfoResolver implements HandlerMethodArgumentResolver {

	@Resource
	private RedisTemplate<String, User> userTemplate;
	@Resource
	private BrokerUserDao brokerUserDao;

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, 
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		Object obj = request.getSession().getAttribute(ConfigEntity.WEIXIN_OPEN_ID);
		if (obj == null) {
			throw new BusinessException("登录超时，请重新登录。", -10001);
		}
		String id = (String) obj;
		ValueOperations<String, User> valueOps = userTemplate.opsForValue();
		String key = ConfigEntity.PREFIX_BROKER_SESSION + id;
		User user = valueOps.get(key);
		if (user == null) {
			user = brokerUserDao.getUserByOpenId(id);
			if (user == null) {
				throw new BusinessException("登录超时，请重新登录。", -10001);
			}
		}
		return user;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return User.class.isAssignableFrom(parameter.getParameterType());
	}

}
