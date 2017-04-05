package com.caac.house.message.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caac.house.message.entity.Socket;
import com.caac.house.message.service.MessageService;
import com.kernle.engine.ext.RespMessage;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Resource
	private MessageService messageService;

	@RequestMapping(value="/send", method=RequestMethod.POST)
	@ResponseBody
	public RespMessage send(Socket socket, HttpServletRequest request){
		return messageService.send(socket, request);
	}
}
