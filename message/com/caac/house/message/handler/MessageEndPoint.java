package com.caac.house.message.handler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.caac.house.message.dao.MessageDao;
import com.caac.house.message.entity.ImBrokerUser;
import com.caac.house.message.entity.ReceiveMessage;
import com.caac.house.message.entity.SendMessage;
import com.caac.house.message.entity.Socket;
import com.caac.house.message.entity.Subject;
import com.caac.house.message.vo.MessageVo;
import com.kernle.engine.utils.JsonMapper;

public class MessageEndPoint extends TextWebSocketHandler{
	// KEY值：以WebSocketSession的ID作为键，存放对应的接收者和发送者ID信息
	public static Map<String, Subject> subjects = new ConcurrentHashMap<String, Subject>();
	// KEY值：以发送者ID为主键，存放对应的socket
	public static Map<String, WebSocketSession> sockets = new ConcurrentHashMap<String, WebSocketSession>();
		
	@Resource
	private JsonMapper jsonMapper;
	@Resource
	private MessageDao messageDao;
	@Resource
	private RedisTemplate<String, ReceiveMessage> listTemplate;
	@Resource
	private RedisTemplate<String, String> stringTemplate;
	@Value("${message.sendUrl}")
	private String sendUrl;
	
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {	
		// 从集合中获取Subject
		Subject subject = subjects.get(session.getId());
		// 删除socket
		sockets.remove(subject.getBrokerId());		
		// 删除连接
		subjects.remove(session.getId());
		super.afterConnectionClosed(session, status);
	}
	
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		String payload = message.getPayload();
		if(StringUtils.isEmpty(payload)) return;
		// 判断微信openId是否存在，不存在则返回
		Object openId = session.getHandshakeAttributes();
		if(openId == null) return;
		// 转化JSON为对象
		SendMessage sendMessage = jsonMapper.readValue(payload, SendMessage.class);
		if(StringUtils.isEmpty(sendMessage.getBrokerId())) return;
		if(StringUtils.isEmpty(sendMessage.getSalesId())) return;
		if(StringUtils.isEmpty(sendMessage.getType())) return;	
		if(StringUtils.isEmpty(sendMessage.getFlag())) return; 		
		// 根据消息标识进行处理 0-连接消息 1-历史消息 2-交谈消息
		// 处理连接消息
		if("0".equals(sendMessage.getFlag())){ 
			// 保存发送者与接收者信息，以便从redis中查询消息			
			Subject subject = new Subject();
			subject.setBrokerId(sendMessage.getBrokerId());
			subject.setSalesId(sendMessage.getSalesId());
			subjects.put(session.getId(), subject);
			sockets.put(sendMessage.getBrokerId(), session);
			// 默认查询第一页消息，发送给当前用户
			send(session, sendMessage.getBrokerId(), sendMessage.getSalesId(), 0);
			// 设置未读消息为已读
			messageDao.updateUnread(sendMessage.getSalesId(), sendMessage.getBrokerId(), "1");
			return;
		}
		
		// 处理请求获取历史消息
		if("1".equals(sendMessage.getFlag())){
			// 根据客户发送过来的参数获取历史消息
			send(session,sendMessage.getBrokerId(), sendMessage.getSalesId(), sendMessage.getIndex());
			return;
		}
		
		// 处理交谈信息
		if("2".equals(sendMessage.getFlag())){
			// 若消息内容为空，则取消发送
			if(StringUtils.isEmpty(sendMessage.getContent())) return;
			Date date = new Date();
			// 将消息保存到数据库中
			ImBrokerUser imBrokerUser = new ImBrokerUser();
			imBrokerUser.setUser(sendMessage.getSalesId());
			imBrokerUser.setBroker(sendMessage.getBrokerId());
			imBrokerUser.setTime(date);
			imBrokerUser.setContent(sendMessage.getContent());
			imBrokerUser.setType(sendMessage.getType());			
			imBrokerUser.setStatus("0");			
			messageDao.insertMessage(imBrokerUser);
			// 调用接口发送消息
			Socket socket = new Socket();
			socket.setBrokerId(sendMessage.getBrokerId());
			socket.setSalesId(sendMessage.getSalesId());			
			socket.setContent(sendMessage.getContent());
			socket.setTime(date);
			socket.setType(sendMessage.getType());
			socket.setFlag("0");			
			sendSocketMessage(socket);			
			return;
		}

	}
	
	private void send(WebSocketSession session, String broker, String user, int index){
		MessageVo vo = new MessageVo();
		vo.setBroker(broker);
		vo.setUser(user);
		vo.setIndex(index);
		vo.setLength(10);
		List<ReceiveMessage> receives = messageDao.getMessage(vo);
		if(receives != null && receives.size() > 0){
			String msg = jsonMapper.writeValueAsString(receives);
			TextMessage tm = new TextMessage(msg);
			try {
				session.sendMessage(tm);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void sendSocketMessage(Socket socket) {
		try{
			HttpClient httpClient=new HttpClient();
			httpClient.getParams().setContentCharset("utf-8");
			PostMethod postMethod = new PostMethod(sendUrl);
			postMethod.addParameter("brokerId", socket.getBrokerId());
			postMethod.addParameter("salesId", socket.getSalesId());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			postMethod.addParameter("time", simpleDateFormat.format(socket.getTime()));
			postMethod.addParameter("content", socket.getContent());
			postMethod.addParameter("type", socket.getType());
			postMethod.addParameter("flag", socket.getFlag());
			httpClient.executeMethod(postMethod);		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
