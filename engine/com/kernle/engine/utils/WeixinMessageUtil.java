package com.kernle.engine.utils;

import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.caac.house.weixin.entity.Article;
import com.caac.house.weixin.entity.Image;
import com.caac.house.weixin.entity.Music;
import com.caac.house.weixin.entity.ResponseImageMessage;
import com.caac.house.weixin.entity.ResponseMusicMessage;
import com.caac.house.weixin.entity.ResponseNewsMessage;
import com.caac.house.weixin.entity.ResponseTextMessage;
import com.caac.house.weixin.entity.ResponseVideoMessage;
import com.caac.house.weixin.entity.ResponseVoiceMessage;
import com.caac.house.weixin.entity.Video;
import com.caac.house.weixin.entity.Voice;
import com.kernle.engine.entity.ConfigEntity;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class WeixinMessageUtil {
	private static final Logger logger = Logger.getLogger(WeixinMessageUtil.class);
	private static final String[] alias = new String[]{"xml", "image", "voice", "video", "music", "item"};
			
	@SuppressWarnings("unchecked")
	public static Map<String, String> streamToMap(InputStream inputStream){
		Map<String, String> fields = new HashMap<String, String>();
		try {				
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			Element root = document.getRootElement();
			List<Element> elementList = root.elements();				
			for (Element e : elementList){  
				fields.put(e.getName(), e.getText());
				logger.debug(e.getName() + "|" + e.getText());
			}					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fields;
	}
	
	/**
	 * @Title: replyTextMessage
	 * @Description: 回复文本消息
	 * @param textMessage
	 * @return
	 * @throws
	 */
	public static String replyTextMessage(ResponseTextMessage textMessage) {  
        xstream.alias("xml", textMessage.getClass());  
        return xstream.toXML(textMessage);  
    }
	
	/**
	 * @Title: replyTextMessage
	 * @Description: 回复文本消息
	 * @param fields
	 * @param content
	 * @return
	 * @throws
	 */
	public static String replyTextMessage(Map<String, String> fields, String content){
		ResponseTextMessage text = new ResponseTextMessage();
		text.setToUserName(fields.get("FromUserName"));
		text.setFromUserName(fields.get("ToUserName"));
		text.setCreateTime((new Date()).getTime());
		text.setMsgType(ConfigEntity.WEIXIN_TYPE_TEXT);
		text.setContent(content);
		
		xstream.alias("xml", text.getClass());
		return xstream.toXML(text);
	}
	
	/**
	 * @Title: replyImageMessage
	 * @Description: 回复图片消息
	 * @param fields
	 * @param mediaId
	 * @return
	 * @throws
	 */
	public static String replyImageMessage(Map<String, String> fields, String mediaId){
		ResponseImageMessage imageMessage = new ResponseImageMessage();
		imageMessage.setToUserName(fields.get("FromUserName"));
		imageMessage.setFromUserName(fields.get("ToUserName"));
		imageMessage.setCreateTime((new Date()).getTime());
		imageMessage.setMsgType(ConfigEntity.WEIXIN_TYPE_IMAGE);
		
		Image image = new Image();
		image.setMediaId(mediaId);		
		imageMessage.setImage(image);
		
		xstream.alias("xml", imageMessage.getClass());	
		xstream.alias("image", image.getClass());
		return xstream.toXML(imageMessage);
	}
	
	/**
	 * @Title: replyVoiceMessage
	 * @Description: 回复语音消息
	 * @param fields
	 * @param mediaId
	 * @return
	 * @throws
	 */
	public static String replyVoiceMessage(Map<String, String> fields, String mediaId){
		ResponseVoiceMessage voiceMessage = new ResponseVoiceMessage();
		voiceMessage.setToUserName(fields.get("FromUserName"));
		voiceMessage.setFromUserName(fields.get("ToUserName"));
		voiceMessage.setCreateTime((new Date()).getTime());
		voiceMessage.setMsgType(ConfigEntity.WEIXIN_TYPE_VOICE);
		
		Voice voice = new Voice();
		voice.setMediaId(mediaId);
		voiceMessage.setVoice(voice);
		
		xstream.alias("xml", voiceMessage.getClass());	
		xstream.alias("voice", voice.getClass());
		return xstream.toXML(voiceMessage);
	}
	
	/**
	 * @Title: replyVideoMessage
	 * @Description: 回复视频消息
	 * @param fields
	 * @param video
	 * @return
	 * @throws
	 */
	public static String replyVideoMessage(Map<String, String> fields, Video video){
		ResponseVideoMessage videoMessage = new ResponseVideoMessage();
		videoMessage.setToUserName(fields.get("FromUserName"));
		videoMessage.setFromUserName(fields.get("ToUserName"));
		videoMessage.setCreateTime((new Date()).getTime());
		videoMessage.setMsgType(ConfigEntity.WEIXIN_TYPE_VIDEO);
		videoMessage.setVideo(video);
		
		xstream.alias("xml", videoMessage.getClass());
		xstream.alias("video", video.getClass());
		return xstream.toXML(videoMessage);
	}
	
	/**
	 * @Title: replyMusicMessage
	 * @Description: 回复音乐消息
	 * @param fields
	 * @param music
	 * @return
	 * @throws
	 */
	public static String replyMusicMessage(Map<String, String> fields, Music music){
		ResponseMusicMessage musicMessage = new ResponseMusicMessage();
		musicMessage.setToUserName(fields.get("FromUserName"));
		musicMessage.setFromUserName(fields.get("ToUserName"));
		musicMessage.setCreateTime((new Date()).getTime());
		musicMessage.setMsgType(ConfigEntity.WEIXIN_TYPE_MUSIC);
		musicMessage.setMusic(music);
		
		xstream.alias("xml", musicMessage.getClass());
		xstream.alias("music", music.getClass());
		return xstream.toXML(musicMessage);
	}
	
	/**
	 * @Title: replyNewsMessage
	 * @Description: 回复图文消息
	 * @param fields
	 * @param article
	 * @return
	 * @throws
	 */
	public static String replyNewsMessage(Map<String, String> fields, Article article) {
		List<Article> articles = new ArrayList<Article>();
		articles.add(article);
		return replyNewsMessage(fields, articles);
	}
	
	/**
	 * @Title: replyNewsMessage
	 * @Description: 回复多条图文消息
	 * @param fields
	 * @param articles
	 * @return
	 * @throws
	 */
	public static String replyNewsMessage(Map<String, String> fields, List<Article> articles) {
		ResponseNewsMessage news = new ResponseNewsMessage();
		news.setToUserName(fields.get("FromUserName"));
		news.setFromUserName(fields.get("ToUserName"));
		news.setCreateTime((new Date()).getTime());
		news.setMsgType(ConfigEntity.WEIXIN_TYPE_NEWS);		
		news.setArticles(articles);
		news.setArticleCount(articles.size());
		
		xstream.alias("xml", news.getClass());  
        xstream.alias("item", new Article().getClass());  
        String str = xstream.toXML(news);
        return str;
	}	
	
	private static XStream xstream = new XStream(new XppDriver() {  
        public HierarchicalStreamWriter createWriter(Writer out) {  
            return new PrettyPrintWriter(out) {  
                // 对那些xml节点的转换增加CDATA标记 true增加 false反之  
                boolean cdata = false;  
  
                @SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
                	if(!Arrays.asList(alias).contains(name)){
                		char[] arr = name.toCharArray();
                		if(arr[0] >= 'a' && arr[0] <= 'z'){
                			arr[0] = (char)((int)arr[0] - 32);
                		}
                		name = new String(arr);
                	}
                    logger.debug("xstream.startNode:" + name);
               	
                	super.startNode(name, clazz);                      
                }
                
                @Override
                public void setValue(String text){
                	if(text != null && !"".equals(text)){
                		// 浮点型判断
                		Pattern patternInt = Pattern.compile("[0-9]*(\\.?)[0-9]*");
                		// 整型判断
                		Pattern patternFloat = Pattern.compile("[0-9]+");
                		// 若为整数或浮点数就不要加[CDATA[]]了
                		if(patternInt.matcher(text).matches() || patternFloat.matcher(text).matches()){
                			cdata = false;
                		}else{
                			cdata = true;
                		}                		
                	}
                	super.setValue(text);
                }
  
                protected void writeText(QuickWriter writer, String text) {  
                    if (cdata) {  
                        writer.write("<![CDATA[");  
                        writer.write(text);  
                        writer.write("]]>");  
                    } else {  
                        writer.write(text);  
                    }  
                }  
            };  
        }  
    });
	
}
