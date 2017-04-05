package com.caac.house.weixin.vo;

import java.util.List;

public class RequestNewsVo {
	/** 图文消息列表 **/
	private List<NewsVo> articles;

	public List<NewsVo> getArticles() {
		return articles;
	}

	public void setArticles(List<NewsVo> articles) {
		this.articles = articles;
	}

}
