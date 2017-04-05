package com.caac.house.weixin.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caac.house.weixin.service.JssdkService;
import com.kernle.engine.ext.RespMessage;

@Controller
@RequestMapping("/jssdk")
public class JssdkController {
	@Resource
	private JssdkService jssdkService;

	@RequestMapping(value="/ticket", method = RequestMethod.POST)
	@ResponseBody
	public RespMessage ticket(String url){
		return jssdkService.ticket(url);
	}
	
}
