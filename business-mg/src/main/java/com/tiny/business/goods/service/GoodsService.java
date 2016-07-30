package com.tiny.business.goods.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tiny.business.goods.model.GoodsModel;

public interface GoodsService {
	/**
	 * 添加商品
	 * @param request
	 * @param goods
	 * @return
	 */
	boolean add(HttpServletRequest request, GoodsModel goods,String listAttrStr) throws Exception;
	/**
	 * 获取商品列表
	 * @param goods
	 * @return
	 * @throws Exception
	 */
	List<GoodsModel> getListGoods(GoodsModel goods) throws Exception;
	/**
	 * 根据商品id获取商品详情
	 * @param goodsId
	 * @return
	 */
	GoodsModel getGood(String goodsId) throws Exception;
	/**
	 * 批量上下架商品
	 * @param listGoods
	 * @return
	 * @throws Exception
	 */
	int batchOutGoods(String listGoods)throws Exception;
	/**
	 * 把商品添加到一个分类中
	 * @param catId
	 * @param listGoodsString
	 * @return 
	 */
	int toCategoryGoods(String catId, String listGoodsString) throws Exception;
	/**
	 * 单件商品上下架
	 * @param goodsId
	 * @return
	 * @throws Exception
	 */
	int batethGoods(GoodsModel goods) throws Exception;
	
	/**
	 * 删除单个商品
	 * @param goodsId
	 * @throws Exception
	 */
	void detGoods(String goodsId) throws Exception;
	
}
