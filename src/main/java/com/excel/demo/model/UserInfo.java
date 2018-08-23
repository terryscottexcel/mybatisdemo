package com.excel.demo.model;

import java.util.Date;
import java.util.List;

import com.excel.demo.util.DateUtil;

public class UserInfo {
	private Integer id;
	private String userCode;
	private String userName;
	private Date birthday;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		
		return "UserInfo [id=" + id + ", userCode=" + userCode + ", userName=" + userName 
				+ ", birthday=" + DateUtil.convertToStringDate(birthday)
				+ "]";
	}
	
	
	
	

}
