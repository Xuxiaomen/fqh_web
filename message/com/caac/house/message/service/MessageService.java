package com.caac.house.message.service;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.caac.house.message.dao.MessageDao;
import com.caac.house.message.entity.Socket;
import com.caac.house.message.handler.MessageEndPoint;
import com.kernle.engine.ext.RespMessage;
import com.kernle.engine.utils.JsonMapper;

@Service("messageService")
public class MessageService {
	
	@Resource
	private JsonMapper jsonMapper;
	@Resource
	private MessageDao messageDao;
	
	public RespMessage send(Socket socket, HttpServletRequest request){
		System.out.println("======1");
		System.out.println(jsonMapper.writeValueAsString(socket));
		// 获取接收者session
		WebSocketSession session = MessageEndPoint.sockets.get(socket.getBrokerId());
		if(session == null) return RespMessage.error();
		// 发送消息
		String msg = jsonMapper.writeValueAsString(socket);
		System.out.println("======2");
		System.out.println(msg);
		TextMessage tm = new TextMessage(msg);
		try {
			session.sendMessage(tm);
			// 更新状态
			messageDao.updateUnread(socket.getSalesId(), socket.getBrokerId(), "1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return RespMessage.success();
	}

}
