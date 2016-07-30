package com.tiny.business.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiny.business.goods.dao.GoodsAttrMapper;
import com.tiny.business.goods.model.GoodsAttrModel;
import com.tiny.business.goods.model.GoodsModel;
import com.tiny.business.goods.service.GoodsAttrService;

/**
 * @author ThinkPad 商品属性service
 */
@Service("goodsAttrService")
public class GoodsAttrServiceImpl implements GoodsAttrService {

	@Autowired
	private GoodsAttrMapper goodsAttrMapper;
	/**
	 * 根据商品id获取商品属性
	 */
	@Override
	public List<GoodsAttrModel> getListGoodsAttr(GoodsModel goods)
			throws Exception {
		GoodsAttrModel goodsAttr = new GoodsAttrModel();
		goodsAttr.setGoodsId(goods.getGoodsId());//商品Id
		goodsAttr.setGoodsSn(goods.getGoodsSn());//商品型号
		return goodsAttrMapper.queryForList(goodsAttr);
	}

}
