package com.tiny.business.goods.service;

import java.util.List;

import com.tiny.business.goods.model.GoodsAttrModel;
import com.tiny.business.goods.model.GoodsModel;

public interface GoodsAttrService {
	/**
	 * 根据商品id获取商品属性
	 */
	List<GoodsAttrModel> getListGoodsAttr(GoodsModel goods) throws Exception;

}
