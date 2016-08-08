package com.tiny.business.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tiny.business.user.service.UserService;
import com.tiny.business.user.vo.UserVo;

/** 
 * @Title: UserController.java 
 * @author luowenwu 
 * @date 2016-1-21 9:45:55 
 * @Description :  TODO
 * @version 1.0
 * @since JDK 1.6 
 */
@RequestMapping("/user")
@Controller
public class UserController {
	private static final Log logger = LogFactory.getLog(UserController.class);
	@Autowired
	private UserService userService;
	/**
	 * 注册
	 * @param userVo
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, String> addUser(UserVo userVo){
		Map<String, String> rmap = new HashMap<String, String>();
		try {
			logger.debug("UserController==addUser===");
			userService.add(userVo);
			rmap.put("status", "success");
		} catch (Exception e) {
			logger.error("UserController==addUser异常===",e);
			rmap.put("status", "fail");
			return rmap;
		}
		
		return rmap;
	}
	
	/**
	 * 跳转到注册页面
	 * @return
	 */
	@RequestMapping("/reg")
	public ModelAndView register(){
		return new ModelAndView("register");
	}
	
	/**
	 * 登录
	 * @param userVo
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, String> login(UserVo userVo,HttpServletRequest request){
		Map<String, String> rmap = new HashMap<String, String>();
		try {
			if(null == userVo){
				logger.error("=========UserVo is null登录===========");
				rmap.put("status", "fail");
				return rmap;
			}
			if(userVo.getUserCode() == null || "".equals(userVo.getUserCode())){
				logger.error("=========userCode is null登录===========");
				rmap.put("status", "fail");
				return rmap;
			}
			if(userVo.getPassword() == null || "".equals(userVo.getPassword())){
				logger.error("=========password is null登录===========");
				rmap.put("status", "fail");
				return rmap;
			}
			Map<String, Object> map = userService.login(userVo);
			int count = Integer.valueOf(String.valueOf(map.get("count")));
			if(count>0){
				request.getSession().setAttribute("userName", userVo.getUserName());
				request.getSession().setAttribute("userCode", userVo.getUserCode());
				request.getSession().setAttribute("isAdmin", map.get("isAdmin"));//是否是管理员 即是买家也是卖家
				rmap.put("status", "success");
			}else{
				rmap.put("status", "fail");
			}
		} catch (Exception e) {
			logger.error("===========用户登录异常=========",e);
			rmap.put("status", "fail");
			return rmap;
		}
		return rmap;
	}
	/**
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping("/logIndex")
	public ModelAndView logIndex(){
		return new ModelAndView("user/login");
	} 
	/**
	 * 修改用户密码
	 * @param userVo
	 * @return
	 */
	@RequestMapping("/updatePws")
	@ResponseBody
	public Map<String,String> updatePassword(UserVo userVo){
		Map<String, String> rmap = new HashMap<String, String>();
		try {
			int count = userService.updatePws(userVo);
			if(count>0){
				rmap.put("status", "success");
			}else{
				rmap.put("status", "fail");
			}
		} catch (Exception e) {
			logger.error("===========用户修改密码异常=========",e);
			rmap.put("status", "fail");
		}
		return rmap;
	}
	
	/**
	 * 跳转到个人中心页面 
	 * @return
	 */
	@RequestMapping("/myInfo")
	public ModelAndView myInfo(){
		return new ModelAndView("user/myInfo");
	}
	
}
