package com.tiny.business.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiny.business.order.model.OrderInfoModel;
import com.tiny.business.order.service.OrderService;
/**
 * 订单controller
 * @author user
 *
 */
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	/**
	 * 生成订单
	 * @param orderInfoModel
	 */
	@RequestMapping("/add")
	public void add(OrderInfoModel orderInfoModel){
		
		
	}
}
