package com.caac.house.weixin.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caac.house.weixin.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Resource
	private MenuService menuService;

	@RequestMapping(value="/create", method = RequestMethod.GET)
	@ResponseBody
	public String createMenu(){
		return menuService.createMenu();
	}
	
}
