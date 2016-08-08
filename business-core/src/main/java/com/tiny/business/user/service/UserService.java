package com.tiny.business.user.service;

import java.util.Map;

import com.tiny.business.user.vo.UserVo;

/** 
 * @Title: UserService.java 
 * @authorluowenwu 
 * @date  2016-1-21  
 * @Description :  TODO
 * @version 1.0
 * @since JDK 1.6 
 */
public interface UserService {

	void add(UserVo userVo) throws Exception;

	Map<String, Object> login(UserVo userVo) throws Exception;

	int updatePws(UserVo userVo) throws Exception;

}
