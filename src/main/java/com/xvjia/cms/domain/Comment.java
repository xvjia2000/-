package com.xvjia.cms.domain;

import java.util.Date;

/**
 * @author xvjia
 * 	时间2019年9月21日
 * 
 */
public class Comment {

	private int id;
	private User users;
	
	
	private int article_id;
	private Article articles;
	private int user_id;
	private String content;
	private Date created;
	
	
	
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
	public Article getArticles() {
		return articles;
	}
	public void setArticles(Article articles) {
		this.articles = articles;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", users=" + users + ", article_id=" + article_id + ", articles=" + articles
				+ ", user_id=" + user_id + ", content=" + content + ", created=" + created + "]";
	}
	
	
	
	
	
}
