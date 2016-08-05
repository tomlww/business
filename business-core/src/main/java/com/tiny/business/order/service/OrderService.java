package com.tiny.business.order.service;

import javax.servlet.http.HttpServletRequest;

import com.tiny.business.order.model.CartModel;
import com.tiny.business.sys.model.AddressModel;

public interface OrderService {
	/**
	 * 提交订单
	 * @param request
	 * @param cartModel
	 * @param addressModel
	 * @throws Exception
	 */
	void add(HttpServletRequest request,String oderInfo) throws Exception;

}
