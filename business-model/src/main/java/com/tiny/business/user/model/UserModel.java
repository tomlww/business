package com.tiny.business.user.model;

import java.io.Serializable;

/** 
 * @Title: UserModel.java 
 * @author luowenwu 
 * @date2016-1-21 :49:04 
 * @Description :  TODO
 * @version 1.0
 * @since JDK 1.6 
 */
public class UserModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;//主键
	private String userName;//用户呢称
	private String userCode;//用户code
	private String password;//用户密码
	private String mobile;//手机号码
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
