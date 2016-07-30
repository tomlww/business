package com.tiny.business.goods.controller;

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

import com.tiny.business.goods.service.CartService;
import com.tiny.business.order.model.CartModel;

@RequestMapping("/cart")
@Controller
public class CartController {
	
	private static final Log logger = LogFactory.getLog(CartController.class);
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/init")
	public ModelAndView initCart(HttpServletRequest request){
		ModelAndView model = new ModelAndView("goods/cart");
		try {
			List<CartModel> listCart = cartService.getListCartInfo(request);
			model.addObject("list", listCart);
		} catch (Exception e) {
			logger.error("====================获取购物车列表异常=================",e);
		}
		return model;
	}
	
	/**
	 * 添加购物车
	 */
	@RequestMapping("/addCart")
	@ResponseBody
	public Map<String, String> addCart(HttpServletRequest request,CartModel cart){
		Map<String, String>  map = new HashMap<String, String>();
		try {
			int	upCount = cartService.addCart(request,cart);
			if(upCount>0){
				map.put("status", "success");
			}else{
				map.put("status", "fail");
			}
		} catch (Exception e) {
			logger.info("================addCart=========添加购物车失败",e);
			map.put("status", "error");
		}
		return map;
	}
}
