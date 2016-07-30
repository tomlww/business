package com.tiny.business.goods.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiny.business.goods.dao.GoodsMapper;
import com.tiny.business.goods.model.GoodsModel;
import com.tiny.business.goods.service.GoodsService;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService{
	private static final Log logger = LogFactory.getLog(GoodsServiceImpl.class);
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Override
	public List<GoodsModel> getGoods(GoodsModel model) throws Exception {
		return goodsMapper.queryForList(model);
	}

	
	public GoodsModel getGood(GoodsModel model) throws Exception {
		return (GoodsModel) goodsMapper.queryObject(model);
	}

	@Override
	public List<GoodsModel> goodsFuzzyQuery(GoodsModel model) throws Exception {
		return goodsMapper.goodsFuzzyQuery(model);
	}

}
