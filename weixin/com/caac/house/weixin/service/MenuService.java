package com.caac.house.weixin.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.caac.house.weixin.entity.Button;
import com.caac.house.weixin.entity.ComplexButton;
import com.caac.house.weixin.entity.Menu;
import com.caac.house.weixin.entity.ViewButton;
import com.caac.house.weixin.vo.AccessTokenVo;
import com.kernle.engine.entity.ConfigEntity;
import com.kernle.engine.utils.HttpsClient;
import com.kernle.engine.utils.JsonMapper;
import com.kernle.engine.utils.WeixinUtil;

@Service("menuService")
public class MenuService {
	private static final Logger logger = Logger.getLogger(MenuService.class);
	
	@Resource
	private RedisTemplate<String, AccessTokenVo> accessTokenTemplate;
	
	public String createMenu(){
		AccessTokenVo accessTokenVo = WeixinUtil.getLocalAccessToken(accessTokenTemplate);
		if(accessTokenVo != null && StringUtils.isNotEmpty(accessTokenVo.getAccess_token())){
			Menu menu = new Menu();
			// 房亲登录（一级）
			ViewButton fqdl = new ViewButton();
			fqdl.setName("房亲登录");
			fqdl.setType(ConfigEntity.WEIXIN_BUTTON_VIEW);
			fqdl.setUrl(ConfigEntity.WEIXIN_APP_URL_ROOT + "/weixin/code.do?state=/html/broker/index.html&type=1");
					
			// 房亲会佣金（二级）
			ViewButton fqhyj = new ViewButton();
			fqhyj.setName("房亲会佣金");
			fqhyj.setType(ConfigEntity.WEIXIN_BUTTON_VIEW);
			fqhyj.setUrl(ConfigEntity.WEIXIN_APP_URL_ROOT + "/weixin/code.do?state=/html/about/fqhCommission.html");
			
			// 房亲会协议（二级）
			ViewButton fqhxy = new ViewButton();
			fqhxy.setName("房亲会协议");
			fqhxy.setType(ConfigEntity.WEIXIN_BUTTON_VIEW);
			fqhxy.setUrl(ConfigEntity.WEIXIN_APP_URL_ROOT + "/weixin/code.do?state=/html/about/fqhAgreement.html");
			
			// 关于房亲会（二级）
			ViewButton gyfqh = new ViewButton();
			gyfqh.setName("关于房亲会");
			gyfqh.setType(ConfigEntity.WEIXIN_BUTTON_VIEW);
			gyfqh.setUrl(ConfigEntity.WEIXIN_APP_URL_ROOT + "/weixin/code.do?state=/html/about/fqhAbout.html");
			
			// 联系房亲会（二级）
			ViewButton lxfqh = new ViewButton();
			lxfqh.setName("联系房亲会");
			lxfqh.setType(ConfigEntity.WEIXIN_BUTTON_VIEW);
			lxfqh.setUrl(ConfigEntity.WEIXIN_APP_URL_ROOT + "/weixin/code.do?state=/html/about/fqhContact.html");
			
			// 房亲会攻略（二级）
			ViewButton fqxgl = new ViewButton();
			fqxgl.setName("房亲会攻略");
			fqxgl.setType(ConfigEntity.WEIXIN_BUTTON_VIEW);
			fqxgl.setUrl(ConfigEntity.WEIXIN_APP_URL_ROOT + "/weixin/code.do?state=/readme.html");
			
			// 房亲会（一级）
			ComplexButton fqh = new ComplexButton();
			fqh.setName("房亲会");

			fqh.setSub_button(new Button[]{fqhyj, fqhxy, gyfqh, lxfqh, fqxgl});
			
			// 添加房亲菜单
			menu.setButton(new Button[]{fqdl, fqh});
			
			// 菜单对象转换成json字符串
			JsonMapper jsonMapper = new JsonMapper();
			String menuJson = jsonMapper.writeValueAsString(menu);
			logger.debug(menuJson);
			
			// 提交菜单JSON字符串，创建菜单
			String uri = String.format(ConfigEntity.WEIXIN_MENU_CREATE, accessTokenVo.getAccess_token());
			logger.info(accessTokenVo.getAccess_token());
			String response = HttpsClient.invoke(uri, "POST", menuJson);
			logger.debug(response);
			return response;
		}
		return "无法获取微信AccessTokenVo，创建微信菜单失败。";
	}
	
}
