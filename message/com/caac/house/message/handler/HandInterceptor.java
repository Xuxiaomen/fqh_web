package com.caac.house.message.handler;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.kernle.engine.utils.WeixinUtil;

public class HandInterceptor extends HttpSessionHandshakeInterceptor{

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, 
			WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		if(request.getHeaders().containsKey("Sec-WebSocket-Extensions")) {
			request.getHeaders().set("Sec-WebSocket-Extensions", "permessage-deflate");
        }
		
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			Object openId = WeixinUtil.getOpenId(servletRequest.getServletRequest());
			attributes.put("openId", openId);
		}
		
		return super.beforeHandshake(request, response, wsHandler, attributes);
	}
}
