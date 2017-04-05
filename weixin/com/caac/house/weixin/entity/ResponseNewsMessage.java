package com.caac.house.weixin.entity;

import java.util.List;

public class ResponseNewsMessage extends ResponseMessage {
	/** 图文消息个数 **/
	private int articleCount;
	/** 图文消息信息 **/
	private List<Article> articles;

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "ResponseNewsMessage [articleCount=" + articleCount + ", articles=" + articles + ", toString()="
				+ super.toString() + "]";
	}



}
