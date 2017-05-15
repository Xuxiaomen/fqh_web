package com.caac.house.system.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caac.house.system.entity.BrokerUser;
import com.caac.house.system.service.BrokerUserService;
import com.caac.house.system.vo.BrokerChildPageVo;
import com.caac.house.system.vo.BrokerChildVo;
import com.caac.house.system.vo.BrokerGrandsonPageVo;
import com.caac.house.system.vo.BrokerGrandsonVo;
import com.caac.house.system.vo.LoginVo;
import com.caac.house.system.vo.SendCodeVo;
import com.caac.house.system.vo.UserVo;
import com.kernle.engine.entity.Page;
import com.kernle.engine.entity.User;
import com.kernle.engine.ext.RespMessage;

@Controller
@RequestMapping("/broker")
public class BrokerUserController {
	@Resource
	private BrokerUserService brokerUserService;
	
	/**
	 * @Title: login
	 * @Description: 微信用户登录
	 * @param vo
	 * @param request
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage login(@Valid LoginVo vo, HttpServletRequest request){
		return brokerUserService.login(request, vo);
	}
	
	/**
	 * @Title: logout
	 * @Description: 微信用户退出登录
	 * @param request
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage logout(HttpServletRequest request){
		return brokerUserService.logout(request);
	}
	
	/**
	 * @Title: register
	 * @Description: 微信注册
	 * @param request
	 * @param user
	 * @param code
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage register(HttpServletRequest request, @Valid BrokerUser user,String code){
		return brokerUserService.register(request, user, code);
	}
	
	/**
	 * @Title: config
	 * @Description: 微信确认
	 * @param request
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/config", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage config(HttpServletRequest request){
		return brokerUserService.config(request);
	}
	
	/**
	 * @Title: sendCode
	 * @Description: 发送短信验证码
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sendCode", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage sendCode(SendCodeVo vo, HttpServletRequest request){
		return brokerUserService.sendCode(vo, request);
	}
	
	/**
	 * @Title: updatePassword
	 * @Description: 根据手机号码更新密码
	 * @param phone
	 * @param password
	 * @param code
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	@ResponseBody
	public RespMessage updatePassword(String phone, String password, String code){
		return brokerUserService.updatePassword(phone, password,code);
	}
	
	/**
	 * 获取个人资料
	 * @return
	 */
	@RequestMapping(value="/getUserInfo", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getUserInfo(User user){
		return brokerUserService.getUserInfo(user);
	}
	
	/**
	 * 修改个人资料
	 * @return
	 */
	@RequestMapping(value="/updateUserInfo", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage updateUserInfo(@Valid UserVo vo, User user, HttpServletRequest request){
		return brokerUserService.updateUserInfo(vo,user, request);
	}
	
	/**
	 * 修改密码(根据旧密码改密码)
	 * @param oldPwd 旧密码
	 * @param newPwd 新密码
	 * @return
	 */
	@RequestMapping(value="/updatePasswordByOldPwd", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage updatePasswordByOldPwd(@Valid UserVo vo, User user, HttpServletRequest request){
		return brokerUserService.updatePasswordByOldPwd(vo,user,request);
	}
	
	/**
	 * 修改银行账号信息
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/updateBank", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage updateBank(@Valid UserVo vo, User user, HttpServletRequest request){
		return brokerUserService.updateBank(vo,user,request);
	}
	
	@RequestMapping(value="/index", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage index(User user, HttpServletRequest request){
		return brokerUserService.index(user, request);
	}
	
	@RequestMapping(value="/getBrokerTotal", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getBrokerTotal(User user){
		return brokerUserService.getBrokerTotal(user);
	}
	
	@RequestMapping(value="/getBrokerChild", method=RequestMethod.POST)
	@ResponseBody
	public Page<BrokerChildVo> getBrokerChild(User user, BrokerChildPageVo vo){
		vo.setBrokerId(user.getId());
		brokerUserService.getBrokerChild(vo);
		return vo.getResult();
	}
	
	@RequestMapping(value="/getBrokerGrandson", method=RequestMethod.POST)
	@ResponseBody
	public Page<BrokerGrandsonVo> getBrokerGrandson(User user, BrokerGrandsonPageVo vo){
		vo.setBrokerId(user.getId());
		brokerUserService.getBrokerGrandson(vo);
		return vo.getResult();
	}
	
	@RequestMapping(value="/getBrokerFinance", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getBrokerFinance(User user){
		return brokerUserService.getBrokerFinance(user);
	}
	
	@RequestMapping(value="/qrcode", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage qrcode(User user){
		return brokerUserService.qrcode(user);
	}
	
	@RequestMapping(value="/getConfirm", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getConfirm(String id, String oid){
		return brokerUserService.getConfirm(id, oid);
	}
	
	/**
	 * 
	 * @Title: saveImg
	 * @Description: 保存用户头像
	 * @param mediaId 媒体文件上传后，获取时的唯一标识
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/saveImg", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage saveImg(String mediaId,HttpServletRequest request,User user){
		return brokerUserService.saveImg(mediaId,request,user);
	}
	
	@RequestMapping(value="/file")
	public void downFile(String fileUrl, HttpServletResponse response){
		brokerUserService.downFile(fileUrl, response);
	}
	
	/**
	 * 
	 * @Title: getUserImg
	 * @Description: 获取用户图片
	 * @param user
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/getUserImg", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getUserImg(User user){
		return brokerUserService.getUserImg(user);
	}
	
	/**
	 * 
	 * @Title: getUserbyRecommend
	 * @Description: 获取经纪人推荐页信息
	 * @param userId 经纪人id
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/getUserbyRecommend", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getUserbyRecommend(User user){
		return brokerUserService.getUserbyRecommend(user);
	}
	
	@RequestMapping(value="/getLogo", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getLogo(User user){
		return brokerUserService.getLogo(user);
	}
}
