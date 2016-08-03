package com.tiny.business.user.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiny.business.user.dao.UserMapper;
import com.tiny.business.user.model.UserModel;
import com.tiny.business.user.service.UserService;
import com.tiny.business.user.vo.UserVo;
import com.tiny.business.util.ClassUtil;

/** 
 * @Title: UserServiceImpl.java 
 * @author luowenwu 
 * @date 2016-1-21 
 * @Description :  TODO
 * @version 1.0
 * @since JDK 1.6 
 */
@Service("/userService")
public class UserServiceImpl implements UserService{
	private static final Log logger = LogFactory.getLog(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public void add(UserVo userVo) throws Exception {
		logger.debug("====添加用户service开始===");
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(userVo,userModel);//vo copy到model
		userModel	= (UserModel) ClassUtil.returnClassObject(userVo, userModel);
		userMapper.add(userModel);
		logger.debug("====添加用户service结束===");
	}

	@Override
	public int login(UserVo userVo) throws Exception {
		logger.debug("====用户login开始===");
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(userVo, userModel);
		
		int count = userMapper.login(userModel);
		logger.debug("====用户login结束===count==="+count);
		return count;
	}

	@Override
	public int updatePws(UserVo userVo) throws Exception {
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(userVo,userModel);
		return userMapper.updatePws(userModel);
	}
	
}
