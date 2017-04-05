package com.kernle.engine.entity;

public class ConfigEntity {
	// 请求消息类型：文本
	public static final String WEIXIN_TYPE_TEXT = "text";
	// 请求消息类型：音乐 
	public static final String WEIXIN_TYPE_MUSIC = "music";
	// 请求消息类型：图文 
	public static final String WEIXIN_TYPE_NEWS = "news";
	// 请求消息类型：图片 
	public static final String WEIXIN_TYPE_IMAGE = "image";
	// 请求消息类型：链接 
	public static final String WEIXIN_TYPE_LINK = "link";
	// 请求消息类型：地理位置 
	public static final String WEIXIN_TYPE_LOCATION = "location";
	// 请求消息类型：音频 
	public static final String WEIXIN_TYPE_VOICE = "voice";
	// 请求消息类型：视频 
	public static final String WEIXIN_TYPE_VIDEO = "video";
	// 请求消息类型：推送 
	public static final String WEIXIN_TYPE_EVENT = "event";
	// 事件类型：关注 
	public static final String WEIXIN_EVENT_SUBSCRIBE = "subscribe";
	// 事件类型：取消关注 
	public static final String WEIXIN_EVENT_UNSUBSCRIBE = "unsubscribe";
	// 二维码扫描
	public static final String WEIXIN_EVENT_SCAN = "SCAN";
	// 事件类型：自定义菜单点击事件 
	public static final String WEIXIN_EVENT_CLICK = "CLICK";
	// 事件类型：自定义菜单链接跳转事件 
	public static final String WEIXIN_EVENT_VIEW = "VIEW";	
	// 点击推事件
	public static final String WEIXIN_BUTTON_CLICK = "click";
	// 跳转URL 
	public static final String WEIXIN_BUTTON_VIEW = "view";
	// 扫码推事件 
	public static final String WEIXIN_BUTTON_SCANCODE_PUSH = "scancode_push";
	// 扫码推事件且弹出“消息接收中”提示框 
	public static final String WEIXIN_BUTTON_SCANCODE_WAITMSG = "scancode_waitmsg";
	// 弹出系统拍照发图 
	public static final String WEIXIN_BUTTON_PIC_SYSPHOTO = "pic_sysphoto";
	// 弹出拍照或者相册发图 
	public static final String WEIXIN_BUTTON_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
	// 弹出微信相册发图器 
	public static final String WEIXIN_BUTTON_PIC_WEIXIN = "pic_weixin";
	// 弹出地理位置选择器 
	public static final String WEIXIN_BUTTON_LOCATION_SELECT = "location_select";
	// 下发消息（除文本消息）
	public static final String WEIXIN_BUTTON_MEDIA_ID = "media_id";
	// 跳转图文消息URL
	public static final String WEIXIN_BUTTON_VIEW_LIMITED = "view_limited";		
	// access token地址
	public static String WEIXIN_ACCESS_TOKEN;
	// 微信服务号ID
	public static String WEIXIN_APPID;
	// 微信服务号秘钥
	public static String WEIXIN_APPSECRET;
	// jsapi ticket地址
	public static String WEIXIN_JSAPI_TICKET;
	// 二维码的ticket地址 
	public static String WEIXIN_QRCODE_TICKET;
	// redis中Access Token标识
	public static String PREFIX_ACCESS_TOKEN;
	// redis中Ticket标识
	public static String PREFIX_TICKET; 
	// oauth2验证地址
	public static String WEIXIN_OAUTH2;
	// 网页授权验证跳转地址
	public static String WEIXIN_OAUTH2_REDIRECT_URL;
	// 获取OAUTH2的CODE码 
	public static String WEIXIN_OAUTH2_CODE;
	// OpenId关键字标识
	public static String WEIXIN_OPEN_ID;
	// redis中销售人员账号前缀标识
	public static String PREFIX_SALES_SESSION;
	// redis中signature的前缀标识
	public static String PREFIX_SIGNATURE;
	// redis中timestamp的前缀标识 
	public static String PREFIX_TIMESTAMP;
	// redis中nonce的前缀标识 
	public static String PREFIX_NONCE;
	// 应用URL
	public static String WEIXIN_APP_URL_ROOT;
	// 创建自定义菜单
	public static String WEIXIN_MENU_CREATE;
	// 查询自定义菜单
	public static String WEIXIN_MENU_GET;
	/** 微信用户信息 **/
	public static String WEIXIN_USER_INFO;
	// redis中用户前缀标识
	public static String PREFIX_BROKER_SESSION;
	
	public static void setPrefixBrokerSession(String prefixBrokerSession){
		PREFIX_BROKER_SESSION = prefixBrokerSession;
	}
	
	public static void setMenuCreate(String menuCreate){
		WEIXIN_MENU_CREATE = menuCreate;
	}
	
	public static void setMenuGet(String menuGet){
		WEIXIN_MENU_GET = menuGet;
	}
	
	public static void setUserInfo(String userInfo){
		WEIXIN_USER_INFO = userInfo;
	}
	
	public static void setAppUrlRoot(String appUrlRoot){
		WEIXIN_APP_URL_ROOT = appUrlRoot;
	}
	
	public static void setPrefixNonce(String prefixNonce){
		PREFIX_NONCE = prefixNonce;
	}
	
	public static void setPrefixTimestamp(String prefixTimestamp){
		PREFIX_TIMESTAMP = prefixTimestamp;
	}
	
	public static void setPrefixSignature(String prefixSignature){
		PREFIX_SIGNATURE = prefixSignature;
	}
	
	public static void setPrefixSalesSession(String prefixSalesSession){
		PREFIX_SALES_SESSION = prefixSalesSession;
	}
	
	public static void setOpenId(String openId){
		WEIXIN_OPEN_ID = openId;
	}
	
	public static void setOauth2Code(String oauth2Code){
		WEIXIN_OAUTH2_CODE = oauth2Code;
	}
	
	public static void setOauth2RedirectUrl(String oauth2RedirectUrl){
		WEIXIN_OAUTH2_REDIRECT_URL = oauth2RedirectUrl;
	}
	
	public static void setOauth2(String oauth2){
		WEIXIN_OAUTH2 = oauth2;
	}
	
	public static void setPrefixAccessToken(String prefixAccessToken){
		PREFIX_ACCESS_TOKEN = prefixAccessToken;
	}
	
	public static void setPrefixTicket(String prefixTicket){
		PREFIX_TICKET = prefixTicket;
	}
	
	public static void setAccessToken(String accessToken){
		WEIXIN_ACCESS_TOKEN = accessToken;
	}
	
	public static void setAppid(String appid) {
		WEIXIN_APPID = appid;
	}

	public static void setAppsecret(String appsecret) {
		WEIXIN_APPSECRET = appsecret;
	}
	
	public static void setJsapiTicket(String jsapiTicket){
		WEIXIN_JSAPI_TICKET = jsapiTicket;
	}
	
	public static void setQrcodeTicket(String qrcodeTicket){
		WEIXIN_QRCODE_TICKET = qrcodeTicket;
	}
	
}
