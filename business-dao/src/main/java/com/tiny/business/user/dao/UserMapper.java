package com.tiny.business.user.dao;

import com.tiny.business.user.model.UserModel;

/** 
 * @Title: UserMapper.java 
 * @author 作者:luowenwu 
 * @date 创建时间：2016-1-21 上午10:36:03 
 * @Description :  TODO
 * @version 1.0
 * @since JDK 1.6 
 */
public interface UserMapper {
	/**
	 * 注册
	 * @param userModel
	 * @throws Exception
	 */
	public void add(UserModel userModel) throws Exception;
	/**
	 * 登录
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	public int login(UserModel userModel) throws Exception;
	/**
	 * 修改用户密码
	 * @param userModel
	 * @return
	 */
	public int updatePws(UserModel userModel);
	
}
