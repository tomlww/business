package com.tiny.business.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tiny.business.order.dao.CartMapper;
import com.tiny.business.order.dao.OrderMapper;
import com.tiny.business.order.model.CartModel;
import com.tiny.business.order.model.OrderGoods;
import com.tiny.business.order.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	/**
	 * 提交订单
	 */
	@Override
	public void add(HttpServletRequest request,String oderInfo) throws Exception {
		//1.获取购物车信息，生成订单
		Map<String, Object> map = getCartInfo(oderInfo);//获取购物车信息
		List<CartModel> cart = (List<CartModel>) map.get("CartModel");
		for(int i=0;i<cart.size();i++){
			CartModel cartModel = cart.get(i);
			OrderGoods order = new OrderGoods();
			order.setGoodsId(cartModel.getGoodsId());//商品id
			order.setGoodsNumber(cartModel.getGoodsNumber());//商品数量
			orderMapper.addOrderGoods(order);
		}
		map.get("total");
	}
	/**
	 * 获取购物车信息
	 * @param oderInfo
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> getCartInfo(String oderInfo) throws Exception{
		Map<String, Object> rmap = new HashMap<String, Object>();
		JSONObject json = JSONObject.parseObject(oderInfo);
		String goods = json.get("goods").toString();
		List<CartModel> listCart = JSONObject.parseArray(goods, CartModel.class);
		double total = 0;
		for (int i = 0; i < listCart.size(); i++) {
			CartModel cart = listCart.get(i);
			CartModel cartModel = cartMapper.queryCartById(cart.getRecId());
			double price = cartModel.getGoodsPrice();//价格
			int number  = cartModel.getGoodsNumber();//数量
			cart.setGoodsPrice(price);//商品的价格
			cart.setGoodsNumber(number);//数量
			total += price * number; //计算总价格
		}
		total = total/100;//转成元;存的是分
		rmap.put("total", total);
		rmap.put("listCart", listCart);
		return rmap;
	}
}
