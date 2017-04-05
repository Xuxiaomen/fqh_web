package com.caac.house.message.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.caac.house.message.entity.ImBrokerUser;
import com.caac.house.message.entity.ReceiveMessage;
import com.caac.house.message.vo.MessageVo;

@Component
public interface MessageDao {

	public List<ReceiveMessage> getMessage(MessageVo vo);
	
	public void insertMessage(ImBrokerUser imBrokerUser);
	
	public void updateUnread(String user, String broker, String type);
	
}
