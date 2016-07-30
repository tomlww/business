package com.tiny.business.goods.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tiny.business.order.model.CartModel;

public interface CartService {
	public int addCart(HttpServletRequest request, CartModel cart) throws Exception;

	public List<CartModel> getListCartInfo(HttpServletRequest request) throws Exception;
}
