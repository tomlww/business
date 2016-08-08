package com.tiny.business.order.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tiny.business.order.dao.CartMapper;
import com.tiny.business.order.dao.OrderMapper;
import com.tiny.business.order.model.CartModel;
import com.tiny.business.order.model.OrderGoodsModel;
import com.tiny.business.order.model.OrderInfoModel;
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
	public void add(HttpServletRequest request,String orderInfo) throws Exception {
		//1.获取购物车信息，生成订单
		List<CartModel> cart = getCartInfo(orderInfo);//获取购物车信息
	/*	List<CartModel> cart = (List<CartModel>) map.get("CartModel");*/
		String orderId = "";//订单id
		double total = 0;
		for(int i=0;i<cart.size();i++){
			CartModel cartModel = cart.get(i);
			OrderGoodsModel order = new OrderGoodsModel();
			int goodsNumber = cartModel.getGoodsNumber();
			double goodsPrice =  cartModel.getGoodsPrice();
			order.setGoodsId(cartModel.getGoodsId());//商品id
			order.setGoodsNumber(goodsNumber);//商品数量
			order.setIsGift("0");//是否赠品 0不是1是
			order.setSendNumber(goodsNumber);//发货数量
			order.setOrderId(orderId);//订单id
			order.setGoodsPrice(goodsPrice);
			total += goodsPrice * goodsNumber; //计算总价格
			orderMapper.addOrderGoods(order);
			//2.删除购物车信息
			int count = cartMapper.deleteById(cartModel.getRecId());
			if(count<=0){
				throw new Exception("====删除购物车失败==recId"+cartModel.getRecId());
			}
		}
		//3.生产主订单信息
		OrderInfoModel orderInfoModel = getOrderInfo(orderInfo);
		orderInfoModel.setOrderId(orderId);//订单id
		orderInfoModel.setGoodsAmount(total);//商品总金额
		double orderAmount = total+orderInfoModel.getShippingFee();//商品总金额+上运费
		orderInfoModel.setOrderAmount(orderAmount);//订单总金额
		orderMapper.insertData(orderInfoModel);//添加订单信息
		
		
		
	}
	
	/**
	 * 获取订单信息
	 * @param oderInfo
	 */
	private OrderInfoModel getOrderInfo(String orderInfo){
		JSONObject json = JSONObject.parseObject(orderInfo);
		String order = json.get("order").toString();
		OrderInfoModel orderInfoModel = JSONObject.parseObject(order, OrderInfoModel.class);
		return orderInfoModel;
		
	}
	/**
	 * 获取购物车信息
	 * @param oderInfo
	 * @return
	 * @throws Exception
	 */
	private List<CartModel> getCartInfo(String oderInfo) throws Exception{
		/*Map<String, Object> rmap = new HashMap<String, Object>();*/
		JSONObject json = JSONObject.parseObject(oderInfo);
		String goods = json.get("goods").toString();
		List<CartModel> listCart = JSONObject.parseArray(goods, CartModel.class);
		for (int i = 0; i < listCart.size(); i++) {
			CartModel cart = listCart.get(i);
			CartModel cartModel = cartMapper.queryCartById(cart.getRecId());
			double price = cartModel.getGoodsPrice();//价格
			int number  = cartModel.getGoodsNumber();//数量
			cart.setGoodsPrice(price);//商品的价格
			cart.setGoodsNumber(number);//数量
			
		}
		/*total = total/100;//转成元;存的是分
		rmap.put("total", total);
		rmap.put("listCart", listCart);*/
		return listCart;
	}
}
