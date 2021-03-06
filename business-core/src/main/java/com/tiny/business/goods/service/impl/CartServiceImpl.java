package com.tiny.business.goods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tiny.business.goods.dao.GoodsMapper;
import com.tiny.business.goods.model.GoodsModel;
import com.tiny.business.goods.service.CartService;
import com.tiny.business.order.dao.CartMapper;
import com.tiny.business.order.model.CartModel;
import com.tiny.business.util.CommonUtil;
import com.tiny.business.util.StringUtil;
/**
 * 购物车service
 */
@Service("cartService")
public class CartServiceImpl  implements CartService{
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	/**
	 * 添加购物车信息
	 */
	@Override
	public int addCart(HttpServletRequest request, CartModel cart) throws Exception{
		HttpSession session = request.getSession();//获取session
		String userCode = (String) session.getAttribute("userCode");
		if(!StringUtil.isStrEmpty(userCode)){
			String sessionId = session.getId();
			cart.setSessionId(sessionId);//如果没有登录 就根据sessionid来保存
		}else{
			cart.setUserId(userCode);//用户code
		}
		
	
		int count = cartMapper.cartCountByGoodsId(cart);
		if(count>0){//每次点击添加购物车如果存在数量加1 否则update
			cart.setGoodsNumber(count+1);
			return cartMapper.updateCartNnmber(cart);//修改购物车的数量
		}else{
			String recId = CommonUtil.createRandomNumber();//产生8位随机数 购物车id
			cart.setRecId(recId);
			cart.setGoodsNumber(count+1);
			
			return cartMapper.insertData(cart);
		}
	}
	
	private CartModel getCartModel(HttpServletRequest request,String goodsId) throws Exception{
		CartModel cart = new CartModel();
	//	String suppliersId = (String) request.getSession().getAttribute("");//商家的实体店id
		String suppliersId = "2";
		GoodsModel goods =  new GoodsModel();
		goods.setGoodsId(goodsId);
		goods.setSuppliersId(suppliersId);
		goods = (GoodsModel) goodsMapper.queryObject(goods);
		//cart.set
		return cart;
		
	}
	
	/**
	 * 获取当前用户的购物车列表
	 */
	@Override
	public List<CartModel> getListCartInfo(HttpServletRequest request) throws Exception {
		//如果用户登录了  就用用户的id去查询 否则从sessionid 存在于cookie取值
		String sessionId = (String) request.getSession().getAttribute("sessionId");
		String userId =(String) request.getSession().getAttribute("userId");
		if(StringUtil.isStrEmpty(sessionId) && StringUtil.isStrEmpty(userId) ){
			return null;
		}
		CartModel cart = new CartModel();
		cart.setUserId(userId);
		cart.setSessionId(sessionId);
		return cartMapper.queryForList(cart);
	}
	
	/**
	 * 确应订单
	 */
	@Override
	public Map<String, Object> confirmOrder(HttpServletRequest request, String cartInfo)
			throws Exception {
		Map<String, Object> rmap = new HashMap<String, Object>();
		JSONObject json = JSONObject.parseObject(cartInfo);
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
