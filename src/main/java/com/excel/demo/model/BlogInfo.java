package com.excel.demo.model;

import java.io.Serializable;

public class BlogInfo implements Serializable{
	private Integer id;
	private String title;
	private String context;
	private String keyword;
	private Integer userId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "BlogInfo [id=" + id + ", title=" + title + ", context=" + context + ", keyword=" + keyword + ", userId="
				+ userId + "]";
	}
	
	
	
	
	
	
	
}
