package com.caac.house.project.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caac.house.project.entity.ProjectRecommend;
import com.caac.house.project.service.ProjectService;
import com.kernle.engine.entity.User;
import com.kernle.engine.ext.RespMessage;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Resource
	private ProjectService projectService;
	
	/**
	 * 
	 * @Title: getIndexProject
	 * @Description: 首页房源展示
	 * @throws
	 */
	@RequestMapping(value="/getIndexProject", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getIndexProject(){		
		return projectService.getIndexProject();
	}
	
	/**
	 * 
	 * @Title: list
	 * @Description: 房源列表
	 * @throws
	 */
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage list(){		
		return projectService.getProject();
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
	@RequestMapping(value="/addRecommend",method=RequestMethod.POST)
	@ResponseBody
	public RespMessage addRecommend(@Valid ProjectRecommend pr,User user,String chooseUser){
		return projectService.addRecommend(pr,user,chooseUser);
	}
	
	/**
	 * 
	 * @Title: getProjectById
	 * @Description: 获取指定楼盘信息
	 * @param proj 楼盘id
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/getProjectDetail",method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getProjectDetail(String proj){
		return projectService.getProjectDetail(proj);
	}
	
	/**
	 * 
	 * @Title: updateView
	 * @Description: 增加浏览次数
	 * @param proj 楼盘id
	 * @throws
	 */
	@RequestMapping(value="/updateView",method=RequestMethod.POST)
	@ResponseBody
	public RespMessage updateView(String proj){
		return projectService.updateView(proj);
	}
	
	/**
	 * 
	 * @Title: getImgNote
	 * @Description: 获取指定图片说明
	 * @param id 图片id
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/getImgNote",method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getImgNote(String id){
		return projectService.getImgNote(id);
	}
	
	@RequestMapping(value="/getPic",method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getPic(String id){
		return projectService.getPic(id);
	}
	
	@RequestMapping(value="/getMap",method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getMap(String id){
		return projectService.getMap(id);
	}
	
	@RequestMapping(value="/getGroupInfo", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage getGroupInfo(){
		return projectService.getGroupInfo();
	}
}
