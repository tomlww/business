package com.tiny.business.order.dao;

import com.tiny.business.order.model.OrderGoodsModel;
import com.tiny.business.util.BaseDao;

public interface OrderMapper extends BaseDao{

	void addOrderGoods(OrderGoodsModel order);
	
	
}
