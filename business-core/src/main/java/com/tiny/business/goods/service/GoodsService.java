package com.tiny.business.goods.service;

import java.util.List;

import com.tiny.business.goods.model.GoodsModel;

public interface GoodsService {
	public List<GoodsModel> getGoods(GoodsModel model) throws Exception;
	
	public GoodsModel getGood(GoodsModel model) throws Exception; 
	
	public List<GoodsModel> goodsFuzzyQuery(GoodsModel model) throws Exception;
}
