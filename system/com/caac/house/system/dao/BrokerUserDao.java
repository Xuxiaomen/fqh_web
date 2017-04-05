package com.caac.house.system.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.caac.house.system.entity.BrokerLog;
import com.caac.house.system.entity.BrokerUser;
import com.caac.house.system.entity.WxRecommend;
import com.caac.house.system.vo.BrokerChildPageVo;
import com.caac.house.system.vo.BrokerChildVo;
import com.caac.house.system.vo.BrokerGrandsonPageVo;
import com.caac.house.system.vo.BrokerGrandsonVo;
import com.caac.house.system.vo.IndexInfoVo;
import com.caac.house.system.vo.LoginVo;
import com.caac.house.system.vo.MonthVo;
import com.caac.house.system.vo.OpenIdVo;
import com.caac.house.system.vo.TotalInfoVo;
import com.caac.house.system.vo.UserRecommendVo;
import com.caac.house.system.vo.UserVo;
import com.kernle.engine.entity.User;

@Component
public interface BrokerUserDao {
	/**
	 * @Title: getBroker
	 * @Description: 读取经纪人信息
	 * @param vo 手机号码、登录密码
	 * @return User
	 * @throws
	 */
	public User getBroker(LoginVo vo);
	
	/**
	 * @Title: getRecommend
	 * @Description: 读取被推荐人信息
	 * @param vo
	 * @return 被推荐人ID
	 * @throws
	 */
	public String getRecommend(LoginVo vo);
	
	/**
	 * @Title: updateLoginTime
	 * @Description: 更新经纪人登录时间
	 * @param id 经纪人ID
	 * @throws
	 */
	public void updateLoginTime(String id);
	
	/**
	 * @Title: getExistPhone
	 * @Description: 判断在经纪人表中指定的手机号码是否已存在
	 * @param phone 手机号码
	 * @return 返回符合指定手机号码数量
	 * @throws
	 */
	public int getExistPhone(String phone);
	
	/**
	 * @Title: register
	 * @Description: 注册经纪人
	 * @param user
	 * @throws
	 */
	public void register(BrokerUser user);
	
	/**
	 * @Title: updatePassword
	 * @Description: 根据手机号码更新密码
	 * @param phone
	 * @param pwd
	 * @throws
	 */
	public void updatePassword(String phone,String pwd);
	
	/**
	 * @Title: deleteOpenId
	 * @Description: 删除微信ID对应的经纪人关联信息
	 * @param openId
	 * @throws
	 */
	public void deleteOpenId(String openId);
	
	/**
	 * @Title: saveOpenId
	 * @Description: 保存微信与经纪人关联信息
	 * @param openIdVo
	 * @throws
	 */
	public void saveOpenId(OpenIdVo openIdVo);
	
	/**
	 * @Title: getUserByOpenId
	 * @Description: 读取经纪人信息
	 * @param id 微信OpenID
	 * @return
	 * @throws
	 */
	public User getUserByOpenId(String id);
	
	/**
	 * @Title: getUserBaseByOpenId
	 * @Description: 读取经纪人信息（id，手机号码）
	 * @param id 微信OpenID
	 * @return
	 * @throws
	 */
	public User getUserBaseByOpenId(String id);
	
	/**
	 * 
	 * @Title: getUserInfo
	 * @Description: 获取个人资料
	 * @param id
	 * @throws
	 */
	UserVo getUserInfo(String id);
	
	/**
	 * @Title: getUserToIndex
	 * @Description: 获取首页的个人资料
	 * @param id
	 * @return
	 * @throws
	 */
	public IndexInfoVo getUserToIndex(String id);
	
	/**
	 * 
	 * @Title: updateUserInfo
	 * @Description: 修改个人资料
	 * @param user
	 * @throws
	 */
	void updateUserInfo(UserVo user);
	
	/**
	 * 
	 * @Title: modifyUserOtherInfo
	 * @Description: 修改个人其他信息资料
	 * @param user
	 * @throws
	 */
	void updateUserOtherInfo(UserVo user);
	
	/**
	 * 获取旧密码
	 * @Title: getOldPasswordById
	 * @Description: 
	 * @param id
	 * @return
	 * @throws
	 */
	String getOldPasswordById(String id);
	
	/**
	 * 
	 * @Title: modifyPassword
	 * @Description: 修改密码
	 * @param id
	 * @param pwd
	 * @throws
	 */
	void updatePasswordByOldPwd(UserVo vo);
	
	/**
	 * 
	 * @Title: getOtherInfo
	 * @Description: 根据经纪人查出附属信息
	 * @param id
	 * @throws
	 */
	int getOtherInfo(String id);
	
	/**
	 * 
	 * @Title: insertOtherInfo
	 * @Description: 新增经纪人附属信息
	 * @throws
	 */
	void insertOtherInfo(UserVo vo);
	
	/**
	 * 
	 * @Title: getBankByUserId
	 * @Description: 根据经纪人id查出关联的银行账号
	 * @param brokerId 经纪人id
	 * @throws
	 */
	String getBankByUserId(String brokerId);
	
	/**
	 * 
	 * @Title: updateBankInfo
	 * @Description: 修改银行账号属信息
	 * @param vo
	 * @throws
	 */
	void updateBankInfo(UserVo vo);
	
	/**
	 * @Title: getBrokerMonery
	 * @Description: 经纪人可用房币
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public Double getBrokerMonery(String brokerId);
	
	/**
	 * @Title: getBrokerChildCount
	 * @Description: 经纪人直接房亲总数
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public int getBrokerChildCount(String brokerId);
	
	/**
	 * @Title: getBrokerChildInfo
	 * @Description: 经纪人直接房亲总数和房亲的佣金
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public TotalInfoVo getBrokerChildInfo(String brokerId);
	
	/**
	 * @Title: getBrokerChildOfFive
	 * @Description: 经纪人直接房亲前5位 
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public List<BrokerChildVo> getBrokerChildOfFive(String brokerId);
	
	/**
	 * @Title: getBrokerChild
	 * @Description: 经纪人直接房亲
	 * @param vo
	 * @return
	 * @throws
	 */
	public List<BrokerChildVo> getBrokerChild(BrokerChildPageVo vo);
	
	/**
	 * @Title: getBrokerGrandsonCount
	 * @Description: 经纪人间接房亲总数 
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public int getBrokerGrandsonCount(String brokerId);
	
	/**
	 * @Title: getBrokerGrandsonInfo
	 * @Description:经纪人间接房亲总数和房亲的佣金 
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public TotalInfoVo getBrokerGrandsonInfo(String brokerId);
	
	/**
	 * @Title: getBrokerGrandsonOfFive
	 * @Description: 经纪人间接房亲前5位
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public List<BrokerGrandsonVo> getBrokerGrandsonOfFive(String brokerId);
	
	/**
	 * @Title: getBrokerGrandson
	 * @Description: 经纪人间接房亲
	 * @param vo
	 * @return
	 * @throws
	 */
	public List<BrokerGrandsonVo> getBrokerGrandson(BrokerGrandsonPageVo vo);
	
	/**
	 * @Title: getChildNoCount
	 * @Description: 经纪人财务报表 - 直接房亲活跃度不统计月份总数
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public int getChildNoCount(String brokerId);
	
	/**
	 * @Title: getChildOfMonth
	 * @Description: 经纪人财务报表-直接房亲活跃度
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public List<MonthVo> getChildOfMonth(String brokerId);
	
	/**
	 * @Title: getChildBargainNoCount
	 * @Description: 经纪人财务报表 - 直接房亲不统计月的总成交量
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public int getChildBargainNoCount(String brokerId);
	
	/**
	 * @Title: getChildBargainOfMonth
	 * @Description: 经纪人财务报表-直接房亲成交量
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public List<MonthVo> getChildBargainOfMonth(String brokerId);
	
	/**
	 * @Title: getGrandsonNoCount
	 * @Description: 经纪人财务报表 - 间接房亲不统计月的总活跃度 
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public int getGrandsonNoCount(String brokerId);
	
	/**
	 * @Title: getGrandsonOfMonth
	 * @Description: 经纪人财务报表-间接房亲活跃度
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public List<MonthVo> getGrandsonOfMonth(String brokerId);
	
	/**
	 * @Title: getGrandsonBargainNoCount
	 * @Description: 经纪人财务报表-间接房亲不统计月的总成交量 
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public int getGrandsonBargainNoCount(String brokerId);
	
	/**
	 * @Title: getGrandsonBargainOfMonth
	 * @Description: 经纪人财务报表-间接房亲成交量
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public List<MonthVo> getGrandsonBargainOfMonth(String brokerId);
	
	/**
	 * @Title: getTicket
	 * @Description: 获取经纪人的二维码Ticket
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public String getTicket(String brokerId);
	
	/**
	 * @Title: updateTicket
	 * @Description: 存放经纪人的二维码Ticket
	 * @param ticket
	 * @param brokerId
	 * @throws
	 */
	public void updateTicket(String ticket, String brokerId);
	
	/**
	 * @Title: getBrokerName
	 * @Description: 获取经纪人名称
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public String getBrokerName(String brokerId);
	
	/**
	 * @Title: getOpenId
	 * @Description: 判断用户是否登录
	 * @param openId
	 * @return
	 * @throws
	 */
	public List<String> getOpenId(String openId);
	
	/**
	 * 
	 * @Title: updateUserLogo
	 * @Description: 修改经纪人头像
	 * @param userId 经纪人id
	 * @param logo 头像id
	 * @throws
	 */
	void updateUserLogo(String userId,String logo);
	
	/**
	 * 
	 * @Title: getUserImg
	 * @Description: 获取用户头像
	 * @param userId 用户id
	 * @return
	 * @throws
	 */
	String getUserImg(String userId);
	
	/**
	 * 
	 * @Title: getUserbyRecommend
	 * @Description: 获取经纪人推荐页信息
	 * @param userId 经纪人id
	 * @return
	 * @throws
	 */
	UserRecommendVo getUserbyRecommend(String userId);
	
	/**
	 * @Title: getWxRecommend
	 * @Description: 查询经纪人之间的微信推荐关系
	 * @param openId
	 * @return
	 * @throws
	 */
	public String getWxRecommend(String openId);
	
	/**
	 * @Title: insertWxRecommend
	 * @Description: 保存经纪人之间的微信推荐关系
	 * @param wxRecommend
	 * @throws
	 */
	public void insertWxRecommend(WxRecommend wxRecommend);
	
	/**
	 * @Title: deleteWxRecommend
	 * @Description: 删除经纪人之间的微信推荐关系
	 * @param wxRecommend
	 * @throws
	 */
	public void deleteWxRecommend(String openId);
	
	/**
	 * 
	 * @Title: getUserIdByPhone
	 * @Description: 根据手机号获取经纪人id
	 * @param phone 手机号
	 * @return
	 * @throws
	 */
	String getUserIdByPhone(String phone);
	
	/**
	 * 
	 * @Title: addBankInfo
	 * @Description: 新增银行账号信息
	 * @param vo
	 * @throws
	 */
	void addBankInfo(UserVo vo);
	
	/**
	 * 
	 * @Title: insertBrokerLog
	 * @Description: 添加经纪人日志
	 * @param brokerLog
	 * @throws
	 */
	public void insertBrokerLog(BrokerLog brokerLog);
	
	/**
	 * 
	 * @Title: updateAutoLogin
	 * @Description: 更新经纪人自动登录标示
	 * @param autoLogin
	 * @param brokerId 
	 * @throws
	 */
	public void updateAutoLogin(String autoLogin, String brokerId);
	
	/**
	 * @Title: getLogo
	 * @Description: 获取经纪人Logo
	 * @param brokerId
	 * @return
	 * @throws
	 */
	public String getLogo(String brokerId);
	
}
