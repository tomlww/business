package com.tiny.business.order.dao;

import com.tiny.business.order.model.CartModel;
import com.tiny.business.util.BaseDao;

public interface CartMapper extends BaseDao{
	/**
	 * 根据商品id获取此商品购物车的数量
	 */
	int cartCountByGoodsId(CartModel cart);

	int updateCartNnmber(CartModel cart);

}
