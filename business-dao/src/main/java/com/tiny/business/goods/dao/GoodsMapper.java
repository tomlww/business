package com.tiny.business.goods.dao;

import java.util.List;

import com.tiny.business.goods.model.GoodsModel;
import com.tiny.business.util.BaseDao;

public interface GoodsMapper  extends BaseDao{
	
	int outGoods(GoodsModel g) throws Exception;
	
	List<GoodsModel>goodsFuzzyQuery(GoodsModel g) throws Exception;
	

}
