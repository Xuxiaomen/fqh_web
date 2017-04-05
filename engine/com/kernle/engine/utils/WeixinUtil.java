package com.kernle.engine.utils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.caac.house.weixin.entity.RequestQrcode;
import com.caac.house.weixin.vo.AccessTokenVo;
import com.caac.house.weixin.vo.JsapiTicketVo;
import com.caac.house.weixin.vo.Oauth2Vo;
import com.kernle.engine.entity.ConfigEntity;

public class WeixinUtil {
	private static final Logger logger = Logger.getLogger(WeixinUtil.class);

	/**
	 * @Title: checkSignature
	 * @Description: 微信服务器配置校验
	 * @param request
	 * @return 通过检验signature对请求进行校验，若校验成功则返回echostr，否则失败
	 * @throws
	 */
	public static String checkSignature(HttpServletRequest request){
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		// 校验微信加密签名
		if (StringUtils.isNotEmpty(signature) && StringUtils.isNotEmpty(timestamp) 
				&& StringUtils.isNotEmpty(nonce) && StringUtils.isNotEmpty(echostr)){
			if (WeixinSignUtil.checkSignature(signature, timestamp, nonce)) {
				return echostr;
			}
		}		
		return null;
	}
	
	public static Object getOpenId(HttpServletRequest request){
		// 访问Session，获取名为"openId"的对象
		Object openId = request.getSession().getAttribute(ConfigEntity.WEIXIN_OPEN_ID);
		//访问Cookie，获取名为"openId"的值
		if(openId == null){
			Cookie[] cookies = request.getCookies();
			if(cookies != null){
				for(int i=0; i<cookies.length; i++){
					Cookie cookie = cookies[i];
					if(cookie.getName().equals(ConfigEntity.WEIXIN_OPEN_ID)){
						openId = cookie.getValue();
					}
				}
			}
		}
		return openId;
	}
	
	/**
	 * @Title: GetOauth2CodeUrl
	 * @Description: 获取OAUTH2的CODE地址
	 * @return
	 * @throws
	 */
	public static String getOauth2CodeUrl(String parameter){
		try {
			String url = String.format(ConfigEntity.WEIXIN_OAUTH2_REDIRECT_URL, parameter);
			url = URLEncoder.encode(url, "UTF-8");
			//获取OAUTH2的CODE码 
			return String.format(ConfigEntity.WEIXIN_OAUTH2_CODE, ConfigEntity.WEIXIN_APPID, url);		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Oauth2Vo getOauth2OpenId(String code){		
		String url = String.format(ConfigEntity.WEIXIN_OAUTH2, ConfigEntity.WEIXIN_APPID, 
				ConfigEntity.WEIXIN_APPSECRET, code);
		String response = HttpsClient.invoke(url, "GET", null);
		JsonMapper jsonMapper = new JsonMapper();
		Oauth2Vo oauth2Vo = jsonMapper.readValue(response, Oauth2Vo.class);
		return oauth2Vo;		
	}
	
	/**
	 * @Title: getAccessToken
	 * @Description: 获取AccessToken，方便以后接口调用
	 * @return
	 * @throws
	 */
	public static AccessTokenVo getAccessToken() {
		String uri = String.format(ConfigEntity.WEIXIN_ACCESS_TOKEN, ConfigEntity.WEIXIN_APPID, 
				ConfigEntity.WEIXIN_APPSECRET);
		String json = HttpsClient.invoke(uri, "GET", null);
		logger.debug(json);
		JsonMapper jsonMapper = new JsonMapper();
		AccessTokenVo accessTokenVo = jsonMapper.readValue(json, AccessTokenVo.class);
		return accessTokenVo;
	}
	
	/**
	 * @Title: getJsapiTicket
	 * @Description: 获取JsapiTicket，方便以后接口调用
	 * @return
	 * @throws
	 */
	public static JsapiTicketVo getJsapiTicket(String accessToken) {
		String uri = String.format(ConfigEntity.WEIXIN_JSAPI_TICKET, accessToken);
		String json = HttpsClient.invoke(uri, "GET", null);
		JsonMapper jsonMapper = new JsonMapper();
		JsapiTicketVo jsapiTicketVo = jsonMapper.readValue(json, JsapiTicketVo.class);
		return jsapiTicketVo;
	}
	
	public static AccessTokenVo getLocalAccessToken(RedisTemplate<String, AccessTokenVo> accessTokenTemplate) {
		ValueOperations<String, AccessTokenVo> tokenOps = accessTokenTemplate.opsForValue();
		return tokenOps.get(ConfigEntity.PREFIX_ACCESS_TOKEN);
	}
	
	/**
	 * @Title: receiveMessage
	 * @Description: 接收消息
	 * @param request
	 * @return
	 * @throws
	 */
	public static Map<String, String> receiveMessage(HttpServletRequest request){
		logger.debug("Receive Message From Weixin Service");
		try {
			InputStream inputStream = request.getInputStream();
			try{								
				return WeixinMessageUtil.streamToMap(inputStream);					
			}finally{
				inputStream.close();  
			    inputStream = null;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @Title: encrypt
	 * @Description: 加密字符串
	 * @param content
	 * @return
	 * @throws
	 */
	public static String encrypt(String content){
		return SecurityAES.encryptAES(content, Constant.DECRYPT_PASSWORD);
	}
	
	/**
	 * @Title: decrypt
	 * @Description: 解密字符串
	 * @param content
	 * @return
	 * @throws
	 */
	public static String decrypt(String content){
		return SecurityAES.decrypt(content, Constant.DECRYPT_PASSWORD);
	}
	
	public static String getRequestQrcode(String type, String key){
		RequestQrcode requestQrcode = new RequestQrcode();
		requestQrcode.setAction_name(type);
		Map<String, String> scene = new HashMap<String, String>();
		scene.put("scene_str", key);		
		Map<String, Object> actionInfo = new HashMap<String, Object>();
		actionInfo.put("scene", scene);
		requestQrcode.setAction_info(actionInfo);
		JsonMapper jsonMapper = new JsonMapper();
		return jsonMapper.writeValueAsString(requestQrcode);
	}
	
}
