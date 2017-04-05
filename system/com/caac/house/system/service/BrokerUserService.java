package com.caac.house.system.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.caac.house.broker.dao.HouseMoneryDao;
import com.caac.house.broker.dao.RecommendDao;
import com.caac.house.broker.dao.SpreadDao;
import com.caac.house.broker.entity.HouseMonery;
import com.caac.house.broker.entity.MoneryDetails;
import com.caac.house.broker.entity.Recommend;
import com.caac.house.broker.service.HouseMoneryService;
import com.caac.house.broker.service.RecommendService;
import com.caac.house.project.dao.ProjectDao;
import com.caac.house.system.dao.BrokerUserDao;
import com.caac.house.system.entity.BrokerUser;
import com.caac.house.system.vo.BrokerChildPageVo;
import com.caac.house.system.vo.BrokerGrandsonPageVo;
import com.caac.house.system.vo.BrokerTotalVo;
import com.caac.house.system.vo.FinanceVo;
import com.caac.house.system.vo.IndexInfoVo;
import com.caac.house.system.vo.LoginVo;
import com.caac.house.system.vo.LogoVo;
import com.caac.house.system.vo.MonthVo;
import com.caac.house.system.vo.OpenIdVo;
import com.caac.house.system.vo.QrcodeVo;
import com.caac.house.system.vo.SendCodeVo;
import com.caac.house.system.vo.TotalInfoVo;
import com.caac.house.system.vo.UserVo;
import com.caac.house.weixin.entity.Info;
import com.caac.house.weixin.entity.ResponseQrcode;
import com.caac.house.weixin.vo.AccessTokenVo;
import com.kernle.engine.entity.ConfigEntity;
import com.kernle.engine.entity.User;
import com.kernle.engine.ext.RespMessage;
import com.kernle.engine.utils.Constant;
import com.kernle.engine.utils.DateTimeUtil;
import com.kernle.engine.utils.HttpsClient;
import com.kernle.engine.utils.JsonMapper;
import com.kernle.engine.utils.LogUtil;
import com.kernle.engine.utils.RandomUtil;
import com.kernle.engine.utils.SecurityAES;
import com.kernle.engine.utils.SmsUtil;
import com.kernle.engine.utils.WeixinUtil;

@Service("brokerUserService")
public class BrokerUserService {	
	@Resource
	private BrokerUserDao brokerUserDao;
	@Resource
	private RecommendDao recommendDao;
	@Resource
	private HouseMoneryDao houseMoneryDao;
	@Resource
	private RecommendService recommendService;
	@Resource
	private ProjectDao projectDao;
	@Resource
	private SpreadDao spreadDao;
	@Resource
	private RedisTemplate<String, String> stringTemplate;
	@Resource
	private RedisTemplate<String, User> userTemplate;
	@Resource
	private HouseMoneryService moneryService;
	@Resource
	private RedisTemplate<String, AccessTokenVo> tokenTemplate;
	
	@Value("${sms.validate}")
	private String smsValidate;
	
	/**
	 * @Title: login
	 * @Description: 微信用户登录方法
	 * @param request
	 * @param vo 用户账号 用户密码
	 * @return
	 * @throws
	 */
	public RespMessage login(HttpServletRequest request, LoginVo vo) {
		// 判断session中是否存在微信用户openId
		Object obj = WeixinUtil.getOpenId(request);
		if(obj == null){
			// 当前微信用户的openId不存在，则需要提示重新刷新
			return new RespMessage(-10001, "登录超时，请重新登录。");
		}
		String openId = (String) obj;
		// 将密码加密，以便比对经纪人表中的密码
		vo.setPwd(SecurityAES.encryptAES(vo.getPwd(), Constant.DECRYPT_PASSWORD));
		// 查询经纪人
		User user = brokerUserDao.getBroker(vo);
		// 经纪人表中不存在对应信息，则需要判断是否为被推荐人登录
		if(user == null){
			String recommendId = brokerUserDao.getRecommend(vo);
			if(recommendId == null){
				LogUtil.log(brokerUserDao, "3", null, openId, null, vo.getPhone());
				return RespMessage.error("登录失败，请检查手机号码或密码");
			}
			request.getSession().setAttribute("configId", recommendId);
			//保存到redis中
			ValueOperations<String, String> configOps = stringTemplate.opsForValue();
			String key = Constant.PREFIX_CONFIG + openId;
			configOps.set(key, recommendId, Constant.REDIS_TIMEOUT, TimeUnit.SECONDS);
			return RespMessage.success("1");
		}
		brokerUserDao.updateLoginTime(user.getId());
		//将当前用户保存到redis中
		ValueOperations<String, User> userOps = userTemplate.opsForValue();
		String key = ConfigEntity.PREFIX_BROKER_SESSION + openId;
		userOps.set(key, user);
		//添加到经纪人微信关联表中
		brokerUserDao.deleteOpenId(openId);
		OpenIdVo openIdVo = new OpenIdVo();
		openIdVo.setBrokerId(user.getId());
		openIdVo.setOpenId(openId);
		brokerUserDao.saveOpenId(openIdVo);
		brokerUserDao.updateAutoLogin("0", user.getId());
		LogUtil.log(brokerUserDao, "2", null, openId, user);
		return RespMessage.success("0");
	}
	
	public RespMessage logout(HttpServletRequest request){
		// 判断session中是否存在微信用户openId
		Object obj = WeixinUtil.getOpenId(request);
		if(obj == null){
			// 当前微信用户的openId不存在，则需要提示重新刷新
			return new RespMessage(-10001, "登录超时，请重新登录。");
		}
		String openId = (String) obj;				
		// 删除REDIS中的对应OPENID
		String key = ConfigEntity.PREFIX_BROKER_SESSION + openId;
		userTemplate.delete(key);
		User user = brokerUserDao.getUserBaseByOpenId(openId);
		// 更新经纪人的自动登录
		if(user != null){			
			brokerUserDao.updateAutoLogin("1", user.getId());
		}
		LogUtil.log(brokerUserDao, "4", null, openId, user);
		
		return RespMessage.success();
	}
	
	/**
	 * @Title: register
	 * @Description: 微信用户注册方法
	 * @param request
	 * @param user
	 * @param code
	 * @return
	 * @throws
	 */
	public RespMessage register(HttpServletRequest request, BrokerUser brokerUser, String code){
		String pid = null;
		// 如果是手填推荐手机号
		if(StringUtils.isNotBlank(brokerUser.getRecommendPhone())){
			if(brokerUser.getPhone().equals(brokerUser.getRecommendPhone())){
				return RespMessage.error("注册号码不能与推荐人号码一致.");
			}
			pid = brokerUserDao.getUserIdByPhone(brokerUser.getRecommendPhone());
			if(StringUtils.isBlank(pid)) return RespMessage.error("填写的推荐人手机号找不到对应用户,请检查.");
		}
		//检查手机验证码是否正确
		ValueOperations<String, String> smsOps = stringTemplate.opsForValue();
		String random = smsOps.get(Constant.PREFIX_SMS + brokerUser.getPhone());
		if(random == null || !random.equalsIgnoreCase(code)) return RespMessage.error("您输入的手机验证码不正确，请确认后重新输入");
		//注册前应判断经纪人表和荐客表中是否已存在了相同的手机号
		int phoneCount = brokerUserDao.getExistPhone(brokerUser.getPhone());
		if(phoneCount > 0) return RespMessage.error("您已经是房亲网平台的经纪人，请到登录页面进行登录");
		phoneCount = recommendDao.getExistPhone(brokerUser.getPhone());
		if(phoneCount > 0) return RespMessage.error("您正被房亲网平台的经纪人所推荐了，请注意查看手机短信的密码，使用手机号码和密码就可以登录了");		
		// 访问Session，获取名为"openId"的对象
		Object obj = WeixinUtil.getOpenId(request);
		if(obj == null) return RespMessage.error("注册页面已超时，请关闭页面再重新进来注册");
		String openId = (String) obj;
		List<String> brokerId = brokerUserDao.getOpenId(openId);
		if(brokerId != null && brokerId.size() > 0) return RespMessage.error("您的微信已绑定了房亲账号,请登录.");
		//保存经纪人信息
		brokerUser.setId(UUID.randomUUID().toString());
		brokerUser.setPwd(SecurityAES.encryptAES(brokerUser.getPwd(), Constant.DECRYPT_PASSWORD));
		brokerUser.setSource("0");
		//检查微信推荐关系，是否存在推荐经纪人
		String broker = brokerUserDao.getWxRecommend(openId);
		//如果该经纪人存在,则直接成为注册人的上级
		if(StringUtils.isNotBlank(pid)){
			brokerUser.setPid(pid);
			brokerUser.setSource("3");
		}else{
			if(StringUtils.isNotEmpty(broker)){
				brokerUser.setPid(broker);
				brokerUser.setSource("2");
			}
		}
		brokerUserDao.register(brokerUser);
		//成功注册后,给该经纪人送5房币
		HouseMonery hm = new HouseMonery();
		hm.setId(UUID.randomUUID().toString());
		hm.setBrokerId(brokerUser.getId());
		houseMoneryDao.save(hm);
		//保存房币记录明细表
		MoneryDetails mgd = new MoneryDetails();
		mgd.setMasterId(hm.getId());
		mgd.setType("0");
		if(StringUtils.isNotBlank(pid)){
			mgd.setType("5");
			mgd.setRecommend(brokerUser.getId());
		}else{
			if(StringUtils.isNotEmpty(broker)){
				mgd.setType("4");
				mgd.setRecommend(brokerUser.getId());
			}
		}
		mgd.setMonery(888);
		houseMoneryDao.saveDetails(mgd);
		//给填写的推荐人送房币
		if(StringUtils.isNotEmpty(pid)){
			//添加推荐人房币
			houseMoneryDao.addMoneryByBrokerId(pid);
			String hmId = houseMoneryDao.getMoneryIdByBrokerId(pid);
			//保存推荐人的房币明细
			MoneryDetails details = new MoneryDetails();
			details.setType("5");
			details.setMasterId(hmId);
			details.setRecommend(brokerUser.getId());
			details.setMonery(5);
			houseMoneryDao.saveDetails(details);
		}else{
			//给微信推荐人送房币
			if(StringUtils.isNotEmpty(broker)){
				//添加推荐人房币
				houseMoneryDao.addMoneryByBrokerId(broker);
				String hmId = houseMoneryDao.getMoneryIdByBrokerId(broker);
				//保存推荐人的房币明细
				MoneryDetails details = new MoneryDetails();
				details.setType("4");
				details.setMasterId(hmId);
				details.setRecommend(brokerUser.getId());
				details.setMonery(5);
				houseMoneryDao.saveDetails(details);
			}
		}
		//添加当前注册用户到redis中
		//删除redis中存在的信息
		String key = ConfigEntity.PREFIX_BROKER_SESSION + openId;
		userTemplate.delete(key);
		//添加到经纪人微信关联表中			
		//brokerUserDao.deleteOpenId(openId);
		OpenIdVo openIdVo = new OpenIdVo();
		openIdVo.setBrokerId(brokerUser.getId());
		openIdVo.setOpenId(openId);
		brokerUserDao.saveOpenId(openIdVo);
		User user = brokerUserDao.getUserByOpenId(openId);						
		ValueOperations<String, User> valueOps = userTemplate.opsForValue();
		valueOps.set(key, user);
		// 删除微信推荐关系
		brokerUserDao.deleteWxRecommend(openId);
		stringTemplate.delete(Constant.PREFIX_SMS + user.getPhone());
		if(StringUtils.isNotEmpty(broker)){
			LogUtil.log(brokerUserDao, "5", "2", openId, user);
		}else{
			LogUtil.log(brokerUserDao, "5", "0", openId, user);
		}		
		return RespMessage.success();
	}
	
	/**
	 * @Title: config
	 * @Description: 经纪人被推荐确认方法
	 * @param request
	 * @return
	 * @throws
	 */
	public RespMessage config(HttpServletRequest request){
		Object objConfigId = request.getSession().getAttribute("configId");
		Object objOpenId = WeixinUtil.getOpenId(request);
		if(objConfigId == null || objOpenId == null){
			return RespMessage.error("账号登录超时或不存在待确认的用户，请重新登录.");
		}
		String configId = (String) objConfigId;
		String openId = (String) objOpenId;
		List<String> bId = brokerUserDao.getOpenId(openId);
		if(bId != null && bId.size() > 0){
			return RespMessage.error("您的微信已绑定了房亲账号,请登录.");
		}
		Recommend recommend = recommendDao.getInfo(configId);
		//添加到经纪人表
		String brokerId = UUID.randomUUID().toString();
		BrokerUser brokerUser = new BrokerUser();
		brokerUser.setId(brokerId);
		brokerUser.setPid(recommend.getBrokerId());
		brokerUser.setName(recommend.getName());
		brokerUser.setPhone(recommend.getPhone());
		brokerUser.setPwd(recommend.getPwd());
		brokerUser.setSource("1");
		brokerUser.setStatus("0");
		brokerUser.setRecommend(recommend.getId());
		brokerUser.setRecommendTime(recommend.getApplyTime());		
		recommendDao.appendBroker(brokerUser);
		//更新推荐表信息
		recommendDao.config(configId);
		//添加推荐人房币
		houseMoneryDao.addMoneryByBrokerId(recommend.getBrokerId());
		String hmId = houseMoneryDao.getMoneryIdByBrokerId(recommend.getBrokerId());
		//保存推荐人的房币明细
		MoneryDetails details = new MoneryDetails();
		details.setType("1");
		details.setMasterId(hmId);
		details.setRecommend(recommend.getId());
		details.setMonery(5);
		houseMoneryDao.saveDetails(details);		
		//添加被推荐人房币
		HouseMonery h = new HouseMonery();
		h.setId(UUID.randomUUID().toString());
		h.setBrokerId(brokerUser.getId());
		houseMoneryDao.save(h);
		//添加被推荐人房币明细
		MoneryDetails d = new MoneryDetails();
		d.setType("1");
		d.setMasterId(h.getId());
		d.setRecommend(recommend.getId());
		d.setMonery(888);
		houseMoneryDao.saveDetails(d);
		//保存微信OpenId对应关系
		OpenIdVo openIdVo = new OpenIdVo();
		openIdVo.setBrokerId(brokerId);
		openIdVo.setOpenId(openId);
		brokerUserDao.saveOpenId(openIdVo);
		//将注册用户保存到redis中
		User user = brokerUserDao.getUserByOpenId(openId);
		ValueOperations<String, User> userOps = userTemplate.opsForValue();
		String key = ConfigEntity.PREFIX_BROKER_SESSION + openId;
		userOps.set(key, user);
		//记录日志
		LogUtil.log(brokerUserDao, "5", "1", openId, user);
		return RespMessage.success();
	}
	
	/**
	 * @Title: sendCode
	 * @Description: 发送短信【验证随机码】，发送前将其保存到redis中，以便检验
	 * @param phone 手机号码
	 * @return
	 * @throws
	 */
	public RespMessage sendCode(SendCodeVo vo, HttpServletRequest request){
		System.out.println("===sendCode===");
		String phone = vo.getPhone();
		Object obj = null;
		System.out.println(phone);
		System.out.println(vo.getBroker());
		System.out.println(vo.getType());		
	
		if(StringUtils.isBlank(vo.getBroker())){
			obj = WeixinUtil.getOpenId(request);	
			if(obj == null){
				return RespMessage.error();
			}
		}else{
			int count = recommendDao.getBroker(vo.getBroker(), null);
			if(count <= 0){
				System.out.println("没有找到相对应的经纪人" + vo.getBroker());
				return RespMessage.error();
			}
		}
		System.out.println(vo.getType());
		if(vo.getType() != null && vo.getType() == 1){
			if(obj == null){
				return RespMessage.error();
			}
			String openId = (String) obj;
			ValueOperations<String, User> valueOps = userTemplate.opsForValue();
			String key = ConfigEntity.PREFIX_BROKER_SESSION + openId;
			User user = valueOps.get(key);
			if(user == null){
				return RespMessage.error();
			}
			phone = user.getPhone();
		}
		System.out.println(phone);
		if(StringUtils.isBlank(phone)){
			return RespMessage.error("请填写手机号码");
		}
		
		if(vo.getType() != null && vo.getType() == 2){
			int count = brokerUserDao.getExistPhone(phone);
			if(count <= 0){
				return RespMessage.error("输入的手机号码须为已注册的房亲手机");
			}
		}
		
		if(vo.getType() != null && vo.getType() == 3){
			int count = brokerUserDao.getExistPhone(phone);
			if(count > 0){
				return RespMessage.error("此号码已被注册，请使用其他手机号码");
			}
			count = recommendDao.getExistPhone(phone);
			if(count > 0){
				return RespMessage.error("此号码已被推荐，请使用其他手机号码");	
			}
		}
		
		ValueOperations<String, String> smsOps = stringTemplate.opsForValue();
		String key = Constant.PREFIX_SMS + phone;
		String random = RandomUtil.getRandom();
		smsOps.set(key, random, 1800, TimeUnit.SECONDS);
		try {
			smsValidate = new String(smsValidate.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("a", random);
		SmsUtil.send(phone, "房亲会", paramMap, smsValidate);
		return RespMessage.success();
	}
	
	/**
	 * @Title: updatePassword
	 * @Description: 根据手机号码更新经纪人密码
	 * @param phone 手机号码
	 * @param pwd 新密码
	 * @param code 手机验证码
	 * @return
	 * @throws
	 */
	public RespMessage updatePassword(String phone, String pwd, String code){
		//校验输入数值
		if(StringUtils.isBlank(phone)) return RespMessage.error("请输入手机号");
		Pattern p = Pattern.compile("[0-9a-zA-Z!@#$%^&*]{6,16}");
		if(!p.matcher(pwd).matches()) return RespMessage.error("密码由6-16位数字、字母组成");
		//取出redis中验证码
		ValueOperations<String, String> smsOps = stringTemplate.opsForValue();
		String key = Constant.PREFIX_SMS + phone;
		String random = smsOps.get(key);
		if(random == null || !random.equalsIgnoreCase(code)) return RespMessage.error("验证码不正确");
		//更新用户密码
		brokerUserDao.updatePassword(phone, SecurityAES.encryptAES(pwd, Constant.DECRYPT_PASSWORD));
		stringTemplate.delete(Constant.PREFIX_SMS + phone);
		return RespMessage.success();
	}
	
	/**
	 * @Title: getUserInfo
	 * @Description: 获取个人资料
	 * @return
	 * @throws
	 */
	public RespMessage getUserInfo(User user){
		// 获取经纪人可使用的房币
		Double brokerMonery = brokerUserDao.getBrokerMonery(user.getId());
		if(brokerMonery == null){
			brokerMonery = 0.0;
		}
		UserVo vo = brokerUserDao.getUserInfo(user.getId());
		vo.setMonery(brokerMonery);		
		return RespMessage.success(vo);
	}
	
	/**
	 * @Title: modifyInfo
	 * @Description: 修改个人资料
	 * @return
	 * @throws
	 */
	public RespMessage updateUserInfo(UserVo vo, User user, HttpServletRequest request){
		Object id = WeixinUtil.getOpenId(request);
		if(id == null) return null;
		vo.setId(user.getId());
		brokerUserDao.updateUserInfo(vo);
		int num = brokerUserDao.getOtherInfo(user.getId());
		if(num < 1){//如果没有关联的其他信息则新增
			vo.setBrokerId(user.getId());
			vo.setId(UUID.randomUUID().toString());
			brokerUserDao.insertOtherInfo(vo);
		}else{//有则修改
			vo.setBrokerId(user.getId());
			brokerUserDao.updateUserOtherInfo(vo);
		}
		String openId = (String) id;
		User u = brokerUserDao.getUserByOpenId(openId);
		ValueOperations<String, User> userOps = userTemplate.opsForValue();
		String key = ConfigEntity.PREFIX_BROKER_SESSION + openId;
		userOps.set(key, u);
		return RespMessage.success();
	}
	
	/**
	 * 密码修改
	 * @param id  登录人
	 * @param oldPwd 旧密码
	 * @param newPwd 新密码
	 */
	public RespMessage updatePasswordByOldPwd(UserVo vo, User user, HttpServletRequest request){
		if(StringUtils.isBlank(vo.getOldPwd())) return RespMessage.error("旧密码不能为空");
		if(StringUtils.isBlank(vo.getNewPwd())) return RespMessage.error("新密码不能为空");
		String pwd = brokerUserDao.getOldPasswordById(user.getId());
		if(!pwd.equalsIgnoreCase(SecurityAES.encryptAES(vo.getOldPwd(), Constant.DECRYPT_PASSWORD))) 
			return RespMessage.error("旧密码不正确");
		Pattern p = Pattern.compile("[0-9a-zA-Z!@#$%^&*]{6,16}");
		if(!p.matcher(vo.getNewPwd()).matches()) return RespMessage.error("密码由6-16位数字、字母组成");
		vo.setId(user.getId());
		vo.setNewPwd(SecurityAES.encryptAES(vo.getNewPwd(), Constant.DECRYPT_PASSWORD));
		brokerUserDao.updatePasswordByOldPwd(vo);
		return RespMessage.success();
	}
	
	/**
	 * 
	 * @Title: updateBank
	 * @Description:修改银行账号信息 
	 * @param vo
	 * @param user
	 * @param request
	 * @return
	 * @throws
	 */
	public RespMessage updateBank(UserVo vo, User user, HttpServletRequest request){		
		if(StringUtils.isBlank(vo.getAccount()) || StringUtils.isBlank(vo.getBank()))
			return RespMessage.error("开户行或银行卡号不能为空.");
		if(StringUtils.isBlank(vo.getIdcard())) return RespMessage.error("身份证号不能为空.");
		vo.setAccountName(user.getName());
		ValueOperations<String, String> smsOps = stringTemplate.opsForValue();
		String key = Constant.PREFIX_SMS + user.getPhone();
		String randow = smsOps.get(key);
		if(randow ==null){
			return RespMessage.error("验证码已过期,请重新获取.");
		}else if(!randow.equalsIgnoreCase(vo.getCode())){
			return RespMessage.error("验证码不正确,请重新输入.");
		}
		int num = brokerUserDao.getOtherInfo(user.getId());
		if(num < 1){//如果没有关联的银行账号则新增
			vo.setBrokerId(user.getId());
			brokerUserDao.addBankInfo(vo);
		}else{//有则修改
			vo.setBrokerId(user.getId());
			brokerUserDao.updateBankInfo(vo);
		}
		stringTemplate.delete(key);
		return RespMessage.success();
	}
	
	public RespMessage index(User user, HttpServletRequest request){
		IndexInfoVo indexInfoVo = brokerUserDao.getUserToIndex(user.getId());
		//若头像为空，则获取用户微信头像
		if(StringUtils.isEmpty(indexInfoVo.getLogo())){
			AccessTokenVo accessTokenVo = WeixinUtil.getLocalAccessToken(tokenTemplate);
			if(accessTokenVo != null && StringUtils.isNotEmpty(accessTokenVo.getAccess_token())){
				String openId = (String) WeixinUtil.getOpenId(request);
				String url = String.format(ConfigEntity.WEIXIN_USER_INFO, accessTokenVo.getAccess_token(), openId);
				String response = HttpsClient.invoke(url, "GET", null);
				JsonMapper jsonMapper = new JsonMapper();
				Info info = jsonMapper.readValue(response, Info.class);
				brokerUserDao.updateUserLogo(user.getId(), info.getHeadimgurl());
				indexInfoVo.setLogo(info.getHeadimgurl());
			}			
		}
		// 获取楼盘信息
		indexInfoVo.setProject(projectDao.getIndexProject());
		// 获取滚动文本信息
		indexInfoVo.setText(spreadDao.getScrollInfo());
		// 获取广告图片信息
		indexInfoVo.setSpread(spreadDao.getPictureInfo());		
		
		return RespMessage.success(indexInfoVo);
	}
	
	public RespMessage getBrokerTotal(User user){
		BrokerTotalVo vo = new BrokerTotalVo();
		TotalInfoVo totalInfoVo = brokerUserDao.getBrokerChildInfo(user.getId());		
		vo.setChild(totalInfoVo.getTotal());
		vo.setChildMonery(totalInfoVo.getTotalBargain());
		totalInfoVo = brokerUserDao.getBrokerGrandsonInfo(user.getId());
		vo.setGrandson(totalInfoVo.getTotal());
		vo.setGrandsonMonery(totalInfoVo.getTotalBargain());
		return RespMessage.success(vo);		
	}
	
	public void getBrokerChild(BrokerChildPageVo vo){
		brokerUserDao.getBrokerChild(vo);
	}
	
	public void getBrokerGrandson(BrokerGrandsonPageVo vo){
		brokerUserDao.getBrokerGrandson(vo);
	}
	
	public RespMessage getBrokerFinance(User user){
		FinanceVo financeVo = new FinanceVo();
		String brokerId = user.getId();
		// 获取直接房亲数据
		int childTotal = brokerUserDao.getChildNoCount(brokerId);
		List<MonthVo> childMonth = brokerUserDao.getChildOfMonth(brokerId);
		int childBargainTotal = brokerUserDao.getChildBargainNoCount(brokerId);
		List<MonthVo> childBargainMonth = brokerUserDao.getChildBargainOfMonth(brokerId);
		int grandsonTotal = brokerUserDao.getGrandsonNoCount(brokerId);
		List<MonthVo> grandsonMonth = brokerUserDao.getGrandsonOfMonth(brokerId);
		int grandsonBargainTotal = brokerUserDao.getGrandsonBargainNoCount(brokerId);
		List<MonthVo> grandsonBargainMonth = brokerUserDao.getGrandsonBargainOfMonth(brokerId);		
		// 添加包含当前月份在内的前6个月份
		List<String> months = new ArrayList<String>();
		List<Integer> child = new ArrayList<Integer>();
		List<Integer> childBargain = new ArrayList<Integer>();
		List<Integer> grandson = new ArrayList<Integer>();
		List<Integer> grandsonBargain = new ArrayList<Integer>();
		
		for(int i=6; i>0; i--){
			Date date = DateTimeUtil.getDateForMonthInterval(new Date(), 1-i);
			months.add(DateTimeUtil.getFormatDate(date, "MM月"));
			String yyyyMM = DateTimeUtil.getFormatDate(date, "yyyyMM");
			// 直接房亲活跃度
			Integer int1 = 0;
			if(childMonth != null){
				for(int j=0; j<childMonth.size(); j++){
					if(yyyyMM.equals(childMonth.get(j).getMonths())){
						int1 = childMonth.get(j).getCounts();
						break;
					}
				}
			}
			childTotal += int1;
			child.add(childTotal);
			// 直接房亲成交量
			Integer int2 = 0;
			if(childBargainMonth != null){
				for(int j=0; j<childBargainMonth.size(); j++){
					if(childBargainMonth.get(j).getMonths().equals(yyyyMM)){
						int2 = childBargainMonth.get(j).getCounts();
						break;
					}
				}
			}
			childBargainTotal += int2;
			childBargain.add(childBargainTotal);
			// 间接房亲活跃度
			Integer int3 = 0;
			if(grandsonMonth != null){
				for(int j=0; j<grandsonMonth.size(); j++){
					if(yyyyMM.equals(grandsonMonth.get(j).getMonths())){
						int3 = grandsonMonth.get(j).getCounts();
						break;
					}
				}
			}
			grandsonTotal += int3;
			grandson.add(grandsonTotal);
			// 间接房亲成交量
			Integer int4 = 0;
			if(grandsonBargainMonth != null){
				for(int j=0; j<grandsonBargainMonth.size(); j++){
					if(grandsonBargainMonth.get(j).getMonths().equals(yyyyMM)){
						int4 = grandsonBargainMonth.get(j).getCounts();
						break;
					}
				}
			}
			grandsonBargainTotal += int4;
			grandsonBargain.add(grandsonBargainTotal);
		}
		
		financeVo.setChildTotal(brokerUserDao.getBrokerChildCount(user.getId()));
		financeVo.setGrandsonTotal(brokerUserDao.getBrokerGrandsonCount(user.getId()));
		financeVo.setMonths(months);
		financeVo.setChild(child);
		financeVo.setChildBargain(childBargain);
		financeVo.setGrandson(grandson);
		financeVo.setGrandsonBargain(grandsonBargain);
		
		return RespMessage.success(financeVo);
	}
	
	public RespMessage qrcode(User user){
		QrcodeVo qrcodeVo = new QrcodeVo();
		qrcodeVo.setId(user.getId());
		// 获取经纪人的二维码ticket
		String ticket = brokerUserDao.getTicket(user.getId());
		// 存在则直接返回页面
		if(StringUtils.isNotEmpty(ticket)){
			qrcodeVo.setTicket(ticket);
			return RespMessage.success(qrcodeVo);
		}
		// 不存在则需要访问微信接口获取
		ValueOperations<String, AccessTokenVo> tokenOps = tokenTemplate.opsForValue();
		AccessTokenVo accessTokenVo = tokenOps.get(ConfigEntity.PREFIX_ACCESS_TOKEN);
		if(accessTokenVo == null || StringUtils.isEmpty(accessTokenVo.getAccess_token())){
			return RespMessage.error("无法获取微信AccessTokenVo，请重新尝试。");
		}
		String url = String.format(ConfigEntity.WEIXIN_QRCODE_TICKET, accessTokenVo.getAccess_token());
		String qrcode = WeixinUtil.getRequestQrcode("QR_LIMIT_STR_SCENE", user.getId());			
		String response = HttpsClient.invoke(url, "GET", qrcode);
		JsonMapper jsonMapper = new JsonMapper();
		ResponseQrcode responseQrcode = jsonMapper.readValue(response, ResponseQrcode.class);
		System.out.println("responseQrcode:" + responseQrcode.getUrl());
		// 存放到经纪人信息表中
		brokerUserDao.updateTicket(responseQrcode.getTicket(), user.getId());		
		qrcodeVo.setTicket(responseQrcode.getTicket());
		return RespMessage.success(qrcodeVo);
	}
	
	public RespMessage share(String id){
		if(StringUtils.isEmpty(id)){
			return RespMessage.error("关键字不能为空");
		}
		// 获取经纪人的二维码ticket
		String ticket = brokerUserDao.getTicket(id);
		return RespMessage.success(ticket);
	}
	
	public RespMessage getConfirm(String id, String oid){
		if(StringUtils.isEmpty(oid)){
			return RespMessage.error("关键字不能为空");
		}
		if(StringUtils.isEmpty(id)){
			return RespMessage.error("关键字不能为空");
		}
		// 判断当前微信用户是否已有账户登录
		List<String> broker = brokerUserDao.getOpenId(oid);
		if(broker != null && broker.size() > 0){
			return new RespMessage(10005);
		}
		
		return RespMessage.success(brokerUserDao.getBrokerName(id));
	}
	
	/**
	 * 
	 * @Title: saveImg
	 * @Description: 保存用户头像
	 * @param mediaId 媒体文件上传后，获取时的唯一标识
	 * @return
	 * @throws
	 */
	public RespMessage saveImg(String mediaId,HttpServletRequest request,User user){
		// 不存在则需要访问微信接口获取
		ValueOperations<String, AccessTokenVo> tokenOps = tokenTemplate.opsForValue();
		AccessTokenVo accessTokenVo = tokenOps.get(ConfigEntity.PREFIX_ACCESS_TOKEN);
		if(accessTokenVo == null || StringUtils.isEmpty(accessTokenVo.getAccess_token())){
			return RespMessage.error("无法获取微信AccessTokenVo，请重新尝试。");
		}
		String imgUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="
					+accessTokenVo.getAccess_token()+"&media_id="+mediaId+"";
		
		try {
			URL url = new URL(imgUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5 * 1000);
			String contentDisposition = connection.getHeaderField("Content-Disposition");
			String filename = contentDisposition.split("filename=")[1].replace("\"", "");
			String ext = filename.substring(filename.lastIndexOf("."));
			InputStream in = connection.getInputStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len=in.read(buffer))!=-1){
				out.write(buffer,0,len);
			}
			
			String currDate = DateTimeUtil.getSysDate("yyyyMMdd");
			String saveUrl = File.separator+"alidata"+File.separator+"htgl"+File.separator+"uploadFile" + File.separator + currDate;
			String uuidName = UUID.randomUUID().toString();
			String logo = currDate + File.separator + uuidName + ext;
			brokerUserDao.updateUserLogo(user.getId(), logo);
			
			// 判断文件夹是否存在，不存在则创建
			File dir = new File(saveUrl);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			File imgFile = new File(saveUrl + File.separator + uuidName + ext);
			FileOutputStream fileOut = new FileOutputStream(imgFile);
			fileOut.write(out.toByteArray());
			fileOut.flush();
			fileOut.close();
			out.close();
			in.close();
		} catch (MalformedURLException e) {
			return RespMessage.error("图片地址不存在,请重新选择.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return RespMessage.success();
	}
	
	public void downFile(String fileUrl, HttpServletResponse response) {
		if(StringUtils.isBlank(fileUrl)) return;
		int index = 0;
		if(fileUrl.lastIndexOf(File.separator) != -1) index = fileUrl.lastIndexOf(File.separator);
		String url = fileUrl.substring(0,index);
		File file = new File(url);
		if(!file.exists())  throw new RuntimeException("图片可能已经被删除.");
		try {
			InputStream is = new FileInputStream(fileUrl);
			String ext = fileUrl.substring(fileUrl.lastIndexOf(".")+1);
			response.setHeader("Content-Disposition","attachment; filename=logo." + ext);
			response.setContentType(getContentType(ext));
			int read = 0;
			byte[] bytes = new byte[1024];
			OutputStream os = response.getOutputStream();
			while ((read = is.read(bytes)) != -1) {
				os.write(bytes, 0, read);
			}
			os.flush();
			os.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title: getUserImg
	 * @Description: 获取用户图片
	 * @param user
	 * @return
	 * @throws
	 */
	public RespMessage getUserImg(User user){
		return RespMessage.success(brokerUserDao.getUserImg(user.getId()));
	}
	
	/**
	 * 
	 * @Title: getUserbyRecommend
	 * @Description: 获取经纪人推荐页信息
	 * @param userId 经纪人id
	 * @return
	 * @throws
	 */
	public RespMessage getUserbyRecommend(User user){
		return RespMessage.success(brokerUserDao.getUserbyRecommend(user.getId()));
	}
	
	public RespMessage getLogo(User user){
		String logo = brokerUserDao.getLogo(user.getId());
		LogoVo vo = new LogoVo();
		vo.setBroker(user.getId());
		vo.setLogo(logo);
		return RespMessage.success(vo);		
	}
	
	private String getContentType(String ext) {
		if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("jpe")) {
			return "image/jpeg";
		}
		if (ext.equals("png")) {
			return "image/x-png";
		}
		if (ext.equals("txt")) {
			return "text/html";
		}
		if (ext.equals("pdf")) {
			return "application/pdf";
		}
		if (ext.equals("doc")) {
			return "application/msword";
		}
		if (ext.equals("docx")) {
			return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		}
		if (ext.equals("xls")) {
			return "application/vnd.ms-excel";
		}
		if (ext.equals("xlsx")) {
			return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		}
		if (ext.equals("ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (ext.equals("pptx")) {
			return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
		}
		if (ext.equals("zip")) {
			return "application/zip";
		}
		if (ext.equals("rar")) {
			return "application/rar";
		}

		return "application/octet-stream";
	}
}
