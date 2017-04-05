package com.caac.house.message.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

public class MessageConfig implements WebSocketConfigurer{

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(messageHandler(), "/chat/socket.do")
			.addInterceptors(messageInterceptor());	
	}

	@Bean
	public WebSocketHandler messageHandler(){
		return new MessageEndPoint();
	}
	
	@Bean
	public HandInterceptor messageInterceptor(){
		return new HandInterceptor();
	}
}
