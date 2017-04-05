package com.caac.house.project.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.caac.house.project.dao.ProjectDao;
import com.caac.house.project.dao.SalesDao;
import com.caac.house.project.entity.OrderMark;
import com.caac.house.project.entity.ProjectPic;
import com.caac.house.project.entity.ProjectRecommend;
import com.caac.house.project.entity.SendOrders;
import com.caac.house.project.vo.GroupInfoVo;
import com.caac.house.project.vo.MapVo;
import com.caac.house.project.vo.ProjectBaseVo;
import com.caac.house.project.vo.ProjectListVo;
import com.caac.house.project.vo.ProjectPicVo;
import com.caac.house.project.vo.ProjectVo;
import com.caac.house.project.vo.UserExtVo;
import com.kernle.engine.entity.User;
import com.kernle.engine.ext.RespMessage;
import com.kernle.engine.utils.SmsUtil;

@Service
public class ProjectService {
	
	@Resource
	private ProjectDao projectDao;
	
	@Resource
	private SalesDao salesDao;
	
	@Resource
	private SalesService salesService;
	
	@Value("${sms.recommendCustomer}")
	private String smsRecommendCustomer;
	
	
	/**
	 * @Title: getIndexProject
	 * @Description: 首页房源展示
	 * @return
	 * @throws
	 */
	public RespMessage getIndexProject(){
		return RespMessage.success(projectDao.getIndexProject());
	}
	
	/**
	 * @Title: getProject
	 * @Description: 房源列表
	 * @return
	 * @throws
	 */
	public RespMessage getProject(){
		List<ProjectListVo> projects = projectDao.getProject();		
		return RespMessage.success(projects);
	}
	
	/**
	 * 
	 * @Title: addRecommend
	 * @Description: 新增推荐
	 * @param pr
	 * @param chooseUser 置业顾问id(可选)
	 * @return
	 * @throws
	 */
	public RespMessage addRecommend(ProjectRecommend pr, User user, String chooseUser){
		pr.setBrokerId(user.getId());
		int count = projectDao.getRecommendOrNot(pr);
		if(count > 0)//如果该客户已经被推荐过,而且还未成交或未被弃单
			return RespMessage.error("推荐的客户手机号码【"+pr.getPhone()+"】已被推荐过且未成交，不能推荐");
		int num = projectDao.getVisitNum(pr.getPhone());
		if(num > 0)//已经存在报备表中
			return RespMessage.error("推荐的客户" + pr.getName() + "已被报备过了,不能推荐");
		if(StringUtils.isNotBlank(chooseUser)){//如果选择了置业顾问,表示已派单
			UserExtVo userExtVo = salesDao.getUserInfo(chooseUser, pr.getProjectId());
			if(userExtVo == null) return RespMessage.error("请选择楼盘的其他置业顾问.");
			pr.setStatus("1");
			projectDao.addRecommend(pr);
			SendOrders so = new SendOrders();
			so.setId(UUID.randomUUID().toString());
			so.setRecommend(pr.getId());//房源推荐id
			so.setSupervisor(chooseUser);//接单人
			so.setSend(pr.getBrokerId());//派单人
			so.setSendTime(new Date());
			projectDao.addSendOrders(so);
			//新增派单记录
			OrderMark om = new OrderMark();
			om.setRecommend(pr.getId());
			om.setOrder(so.getId());
			om.setSupervisor(chooseUser);
			om.setSend(pr.getBrokerId());
			om.setSendTime(so.getSendTime());
			projectDao.addOrderMark(om);
			salesService.saveOrUpdateSalesPerformanace(pr.getId(), chooseUser,1);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("a", userExtVo.getName());
			paramMap.put("b", user.getName());
			paramMap.put("c", calendar.get(Calendar.MONTH) + 1);
			paramMap.put("d", calendar.get(Calendar.DATE));
			SmsUtil.send(userExtVo.getMobile(), "房亲会", paramMap, smsRecommendCustomer);
		}else{
			pr.setStatus("0");
			projectDao.addRecommend(pr);
		}
		return RespMessage.success();
	}
	
	/**
	 * 
	 * @Title: getProjectDetail
	 * @Description: 获取楼盘详细信息
	 * @param proj 楼盘id
	 * @return
	 * @throws
	 */
	public RespMessage getProjectDetail(String proj){
		if(StringUtils.isEmpty(proj)){
			return RespMessage.error("楼盘关键字不能为空");
		}
		ProjectVo vo = projectDao.getProjectById(proj);
		return RespMessage.success(vo);
	}
	
	/**
	 * 
	 * @Title: updateView
	 * @Description: 增加浏览次数
	 * @param proj 楼盘id
	 * @throws
	 */
	public RespMessage updateView(String proj){
		projectDao.updateView(proj);
		return RespMessage.success();
	}
	
	/**
	 * 
	 * @Title: getImgNote
	 * @Description: 获取指定图片说明
	 * @param id 图片id
	 * @return
	 * @throws
	 */
	public RespMessage getImgNote(String id){
		return RespMessage.success(projectDao.getImgNote(id));
	}
	
	public RespMessage getPic(String id){
		if(StringUtils.isEmpty(id)){
			return RespMessage.error("楼盘关键字不能为空");
		}
		ProjectPicVo vo = new ProjectPicVo();
		List<ProjectBaseVo> baseList = projectDao.getProjectBase(id);
		if(baseList.size() >= 0){
			vo.setName(baseList.get(0).getName());
			vo.setLogo(baseList.get(0).getLogo());
		}		
		List<ProjectPic> picList = projectDao.getProjectPic(id);
		vo.setPic(picList);		
		return RespMessage.success(vo);
	}
	
	public RespMessage getMap(String id){
		if(StringUtils.isEmpty(id)){
			return RespMessage.error("楼盘关键字不能为空");
		}
		MapVo vo = projectDao.getMap(id);
		return RespMessage.success(vo);
	}

	public RespMessage getGroupInfo(){
		List<GroupInfoVo> groupInfos = projectDao.getGroupInfo();
		return RespMessage.success(groupInfos);
	}
}
