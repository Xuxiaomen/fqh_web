package com.kernle.engine.utils;

import java.util.Map;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SmsUtil {
	
	/**
	 * 发送短信验证码
	 * @param phone 手机号码
	 * @param signName 签名
	 * @param paramMap 参数
	 * @param templateCode 模板编号
	 * @throws Exception
	 */
	public static void send(String phone, String signName, Map<String, Object> paramMap, String templateCode){
		StringBuilder sb = new StringBuilder("{");
		for(Map.Entry<String, Object> entry : paramMap.entrySet()){
			sb.append("\"").append(entry.getKey()).append("\":").append("\"").append(entry.getValue()).append("\"").append(",");
		}
		String paramString = sb.substring(0, sb.length() -1) + "}";
		String url = "http://gw.api.taobao.com/router/rest";
		String appkey = "23351224";
		String secret = "d853981556a612440ab1ea47096cdb5e";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName(signName);
		req.setSmsParamString(paramString);
		req.setRecNum(phone);
		req.setSmsTemplateCode(templateCode);
		AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			System.out.println(rsp.getBody());
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}
}
