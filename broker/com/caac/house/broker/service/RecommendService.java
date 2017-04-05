package com.caac.house.broker.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.caac.house.broker.dao.HouseMoneryDao;
import com.caac.house.broker.dao.RecommendDao;
import com.caac.house.broker.entity.HouseMonery;
import com.caac.house.broker.entity.MoneryDetails;
import com.caac.house.broker.entity.Recommend;
import com.caac.house.broker.vo.ClientPageVo;
import com.caac.house.broker.vo.InviteVo;
import com.caac.house.system.dao.BrokerUserDao;
import com.caac.house.system.entity.BrokerUser;
import com.caac.house.system.vo.OpenIdVo;
import com.kernle.engine.entity.User;
import com.kernle.engine.ext.RespMessage;
import com.kernle.engine.utils.Constant;
import com.kernle.engine.utils.LogUtil;
import com.kernle.engine.utils.RandomUtil;
import com.kernle.engine.utils.SecurityAES;
import com.kernle.engine.utils.SmsUtil;

@Service("recommendService")
public class RecommendService {
	
	@Resource
	private RecommendDao recommendDao;
	@Resource
	private HouseMoneryDao houseMoneryDao;
	@Resource
	private BrokerUserDao brokerUserDao;
	@Resource
	private RedisTemplate<String, String> template;
	
	@Value("${sms.invite}")
	private String smsInvite;
	
	/**
	 * @Title: getConfirmInfo
	 * @Description: 获取被推荐信息
	 * @param request
	 * @return
	 * @throws
	 */
	public RespMessage getConfirmInfo(HttpServletRequest request){
		Object obj = request.getSession().getAttribute("configId");
		if(obj == null) return RespMessage.error("推荐信息不存在");
		String configId = (String) obj;
		return RespMessage.success(recommendDao.getConfirmInfo(configId));
	}
	
	/**
	 * @Title: apply
	 * @Description: 荐客申请
	 * @param user
	 * @return RespMessage
	 * @throws
	 */
	public RespMessage apply(User user,HttpServletRequest request){
		String[] names = request.getParameterValues("names");
		String[] phones = request.getParameterValues("phones");
		if(names == null || phones == null) return RespMessage.error("姓名和手机号不能为空。");
		String patt = "1[0-9]{10}";
		Pattern p = Pattern.compile(patt);
		//经纪人表错误信息提示
		StringBuilder userMsg = new StringBuilder();
		//荐客表错误信息提示
		StringBuilder recommendMsg = new StringBuilder();
		List<Recommend> list = new LinkedList<Recommend>();
		for(int i=0;i<names.length;i++){
			if(StringUtils.isBlank(names[i]) || StringUtils.isBlank(phones[i])) return RespMessage.error("姓名和手机号不能为空。");
			if(names[i].length() < 2 || names[i].length() > 10) return RespMessage.error("姓名长度为2-10个字符。");
			if(!(p.matcher(phones[i]).matches())) return RespMessage.error("手机号码格式不正确。");
			int recommendCount = recommendDao.getExistPhone(phones[i]);
			if(recommendCount > 0){// 判断荐客表是否已存在相同的手机号
				recommendMsg.append(names[i]).append(":").append(phones[i]).append(";");
			}else if(brokerUserDao.getExistPhone(phones[i]) > 0){// 判断经纪人表是否已存在相同的手机号
				userMsg.append(names[i]).append(":").append(phones[i]).append(";");
			}else{
				Recommend recommend = new Recommend();
				recommend.setId(UUID.randomUUID().toString());
				recommend.setName(names[i]);
				recommend.setPhone(phones[i]);
				recommend.setBrokerId(user.getId());
				// 随机密码
				String pwd = RandomUtil.getRandom();
				recommend.setOldPwd(pwd);
				recommend.setPwd(SecurityAES.encryptAES(pwd, Constant.DECRYPT_PASSWORD));
				list.add(recommend);
			}
		}
		if(recommendMsg.length() > 0){
			String msg = recommendMsg.toString().substring(0, recommendMsg.length() - 1);
			return RespMessage.error("["+msg+"]正处于申请状态。");
		}else if(userMsg.length() > 0){
			String msg = userMsg.toString().substring(0, userMsg.length() - 1);
			return RespMessage.error("["+msg+"]已是房亲网平台经纪人。");
		}else if(list.size() > 0){
			recommendDao.batchInsertApply(list);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("a", user.getName());
			for(int i=0;i<list.size();i++){
				//保存成功后给被推荐人以短信的方式发送密码
				paramMap.put("b", list.get(i).getOldPwd());
				SmsUtil.send(list.get(i).getPhone(), "房亲会", paramMap, smsInvite);
			}
		}
		return RespMessage.success();
	}
	
	public RespMessage invite(InviteVo vo){
		// 检验手机验证码是否正确
		String key = Constant.PREFIX_SMS + vo.getPhone();
		ValueOperations<String,String> valueOps = template.opsForValue();
		String code = valueOps.get(key);
		if(!vo.getCode().equals(code)){
			return RespMessage.error("手机验证码不正确，请确认后重新输入");
		}
		if(!vo.getPasswd().equals(vo.getConfigpwd())){
			return RespMessage.error("密码和确认密码不一致，请确认后重新输入");
		}
		// 判断父经纪人是否存在
		int i = recommendDao.getBroker(vo.getId(), null);
		if(i <= 0){
			return RespMessage.error("推荐的房亲不存在，请确认是否有效");
		}		
		// 判断手机号码是否已被注册
		i = recommendDao.getBroker(null, vo.getPhone());
		if(i > 0){
			return RespMessage.error("输入的手机号码已经是房亲，请点击“房亲登录”菜单登录。");
		}
		List<String> brokerId = brokerUserDao.getOpenId(vo.getOid());
		if(brokerId != null && brokerId.size() > 0) return RespMessage.error("您的微信已经绑定了房亲,不能注册.");
		// 添加经纪人信息
		BrokerUser brokerUser = new BrokerUser();
		brokerUser.setId(UUID.randomUUID().toString());
		brokerUser.setPid(vo.getId());
		brokerUser.setName(vo.getName());
		brokerUser.setPhone(vo.getPhone());
		brokerUser.setPwd(SecurityAES.encryptAES(vo.getPasswd(), Constant.DECRYPT_PASSWORD));
		brokerUser.setSource("2");
		brokerUser.setStatus("0");
		recommendDao.appendBroker(brokerUser);
		// 添加到OPENID关系表
		OpenIdVo openIdVo = new OpenIdVo();
		openIdVo.setBrokerId(brokerUser.getId());
		openIdVo.setOpenId(vo.getOid());
		brokerUserDao.saveOpenId(openIdVo);
		//添加推荐人房币
		houseMoneryDao.addMoneryByBrokerId(vo.getId());
		String hmId = houseMoneryDao.getMoneryIdByBrokerId(vo.getId());
		//保存推荐人的房币明细
		MoneryDetails details = new MoneryDetails();
		details.setType("4");
		details.setMasterId(hmId);
		details.setRecommend(brokerUser.getId());
		details.setMonery(5);
		houseMoneryDao.saveDetails(details);		
		//添加被推荐人房币
		HouseMonery h = new HouseMonery();
		h.setId(UUID.randomUUID().toString());
		h.setBrokerId(brokerUser.getId());
		houseMoneryDao.save(h);
		//添加被推荐人房币明细
		MoneryDetails d = new MoneryDetails();
		d.setType("4");
		d.setMasterId(h.getId());
		d.setRecommend(brokerUser.getId());
		d.setMonery(888);
		houseMoneryDao.saveDetails(d);
		// 删除微信推荐关系
		brokerUserDao.deleteWxRecommend(vo.getOid());
		LogUtil.log(brokerUserDao, "5", "2", vo.getOid(), brokerUser.getId(), brokerUser.getPhone());
		return RespMessage.success();
	}
	
	public void getClient(User user, ClientPageVo vo){
		vo.setId(user.getId());
		recommendDao.getClient(vo);
	}
}
