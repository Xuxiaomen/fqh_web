package com.caac.house.weixin.entity;

public class ResponseVoiceMessage extends ResponseMessage {
	/** 语音 **/
	private Voice voice;

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

}
