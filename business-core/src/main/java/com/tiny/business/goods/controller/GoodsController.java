package com.tiny.business.goods.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tiny.business.goods.model.GoodsAttrModel;
import com.tiny.business.goods.model.GoodsModel;
import com.tiny.business.goods.service.GoodsAttrService;
import com.tiny.business.goods.service.GoodsService;

/**
 * 商品controller
 * @author ThinkPad
 *
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
	private static final Log logger = LogFactory.getLog(GoodsController.class);
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GoodsAttrService goodsAttrService;
	/**
	 * 商品列表初始化页面
	 * @return
	 */
	@RequestMapping("/init")
	public ModelAndView init(){
		return new ModelAndView("goods/index");
	}
	
	/**
	 * 商品列表
	 * @return
	 */
	@RequestMapping("/getGoods")
	@ResponseBody
	public Map<String,Object> getGoods(GoodsModel model){
		Map<String, Object> rmap = new HashMap<String, Object>();
		try{
			List<GoodsModel>  listGoods = goodsService.getGoods(model);
			rmap.put("goods", listGoods);
		}catch(Exception e){
			logger.error("============查询商品信息异常==========",e);
			rmap.put("status", "fail");
			e.printStackTrace();
		}
		return  rmap;
	}
	
	/**
	 * 商品详情
	 * @param model
	 * @return
	 */
	@RequestMapping("/getGoodDetils/{goodsId}/{suppliersId}")
	public ModelAndView getGoodDetils(@PathVariable("goodsId") String  goodsId
			,@PathVariable("suppliersId") String suppliersId){
		logger.info("===========GoodsController-getGoodDetils start============");
		Map<String, Object> rmap = new HashMap<String, Object>();
		GoodsModel model = new GoodsModel();
		model.setGoodsId(goodsId);
		model.setSuppliersId(suppliersId);
		try{
			GoodsModel goods = goodsService.getGood(model);
			if(null == goods){
				rmap.put("status", "fail");
				return new ModelAndView("goods/goodsDetils");//返回到统一的404页面后期处理
			}
			List<GoodsAttrModel> goodsAttr = goodsAttrService.getListGoodsAttr(goods);
			rmap.put("goods", goods);
			rmap.put("goodsAttr", goodsAttr);
		}catch(Exception e){
			logger.error("============查询商品详情异常==========",e);
			e.printStackTrace();
			rmap.put("status", "fail");
		}
		return new ModelAndView("goods/goodsDetils",rmap);
	}
	
	/**
	 * 
	 * 商品模糊查询
	 */
	@RequestMapping("goodsFuzzyQuery")
	@ResponseBody
	public Map<String,Object> goodsFuzzyQuery(GoodsModel model){
		logger.info("===========GoodsController-getGoodDetils start 商品模糊查询============");
		Map<String, Object> rmap = new HashMap<String, Object>();
		try{
			List<GoodsModel> result = goodsService.goodsFuzzyQuery(model);
			rmap.put("result", result);
		}catch(Exception e){
			logger.error("============商品模糊查询异常  GoodsController-goodsFuzzyQuery error==========");
			rmap.put("status", "error");
			e.printStackTrace();
		}
		return rmap;
	}
}
