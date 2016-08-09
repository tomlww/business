package com.tiny.business.sys.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.tiny.business.sys.model.AdPositionModel;
import com.tiny.business.sys.service.IndexService;
import com.tiny.business.sys.vo.AdPositionVo;
import com.tiny.business.util.JedisPoolUtile;
import com.tiny.business.util.RSAUtil;

@Controller
@RequestMapping("/sys")
public class IndexController {
	private static final Log logger = LogFactory.getLog(IndexController.class);
	
	@Autowired
	private IndexService indexService;
	
	@RequestMapping("/index")
	public ModelAndView returnIndex(){
		return new ModelAndView("index");
	}
	/**
	 * 查询广告轮播图片
	 * @return
	 */
	@RequestMapping("/getAdPosition")
	@ResponseBody
	public Map<String, Object> getAdPosition(){
		Map<String, Object> rmap = new HashMap<String, Object>();
		try {
			String userId = "2";//商家的微信公众号的图片
			AdPositionVo adv = new AdPositionVo();
			adv.setUserId(userId);//用户名
			List<AdPositionModel> 	list = indexService.getAdPositionInfo(adv);
			rmap.put("list", list);
			rmap.put("status", "success");
		} catch (Exception e) {
			logger.error("查询广告轮播图片异常...",e);
			rmap.put("status", "fail");
		}
		return rmap;
	}
	
	/**
	 * 获取分类类型广告图
	 * @return
	 */
	@RequestMapping("/getGoodsTypeAd")
	@ResponseBody
	public Map<String, Object> getGoodsTypeAd(){
		Map<String, Object> rmap = new HashMap<String, Object>();
		String userId = "2";//商家的微信公众号的图片
		AdPositionModel adModel = new AdPositionModel();
		adModel.setUserId(userId);//微信公众号
		//List<E> listGoods = indexService.getGoodsTypeAd(adModel);
		return rmap;
	}
	
	/**
	 * 收货地址
	 * @return
	 */
	@RequestMapping("/intiAdr")
	public ModelAndView initAdr(){
		ModelAndView model = new ModelAndView("sys/address");
		return model;
	}
	/**
	 * 进入我的店铺
	 * @param request
	 * @return
	 */
	@RequestMapping("/myShop")
	public ModelAndView toMyShop(HttpServletRequest request){
		ModelAndView model = new ModelAndView("redirect:http://127.0.0.1:8080/business-mg");
		String userCode;
		try {
			userCode = (String) request.getSession().getAttribute("userCode");
			if(null==userCode || "".equals(userCode)){
				model.setViewName("user/login");
				return model;
			}
			 JedisPool pool = JedisPoolUtile.getPool();
			 Jedis jedis = pool.getResource();
			 String privateKeyString = jedis.srandmember("privateKey");
			 String value = RSAUtil.privateEncrypt(userCode, privateKeyString);//用户名加密之后
			 model.setViewName("redirect:http://127.0.0.1:8080/business-mg/sys/init/"+value);
		} catch (Exception e) {
			logger.error("进入我的店铺异常");
		}
	//	request.get
		return model;
	}
}
