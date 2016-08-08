package com.tiny.business.order.dao;

import com.tiny.business.order.model.CartModel;
import com.tiny.business.util.BaseDao;

public interface CartMapper extends BaseDao{
	/**
	 * 根据商品id获取此商品购物车的数量
	 */
	int cartCountByGoodsId(CartModel cart);

	int updateCartNnmber(CartModel cart);
	
	/**
	 * 根据购物车id获取信息
	 * @param recId
	 * @return
	 * @throws Exception
	 */
	CartModel queryCartById(String recId) throws Exception;
	/**
	 * 删除购物车
	 * @param recId 购物车id
	 * @return
	 * @throws Exception
	 */
	int deleteById(String recId) throws Exception;

}
