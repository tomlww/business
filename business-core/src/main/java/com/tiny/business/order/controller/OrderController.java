package com.tiny.business.order.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiny.business.goods.controller.CartController;
import com.tiny.business.order.model.CartModel;
import com.tiny.business.order.model.OrderInfoModel;
import com.tiny.business.order.service.OrderService;
import com.tiny.business.sys.model.AddressModel;
/**
 * 订单controller
 * @author user
 *
 */
@RequestMapping("/order")
public class OrderController {
	private static final Log logger = LogFactory.getLog(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	/**
	 * 生成订单
	 * @param orderInfoModel
	 */
	@RequestMapping("/add")
	public void add(HttpServletRequest request,String oderInfo){
		
		try {
			orderService.add(request,oderInfo);
		} catch (Exception e) {
			logger.error("======提交订单异常=====",e);
		}
		
	}
}
