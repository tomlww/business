package com.tiny.business.goods.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tiny.business.goods.model.CategoryModel;
import com.tiny.business.goods.model.GoodsModel;
import com.tiny.business.goods.service.CategoryService;
import com.tiny.business.goods.service.GoodsService;


/**
 * 商品controller
 * @author ThinkPad
 *
 */
@Controller
@RequestMapping("/mgGoods")
public class MgGoodsController {
	private static final Log logger = LogFactory.getLog(MgGoodsController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping("/init")
	public ModelAndView init(){
		return new ModelAndView("goods/mgCategory");
	}
	
	/**
	 * 添加商品分类
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addCategory(HttpServletRequest request,String categoryListString) {
		Map<String, Object> rmap = new HashMap<String, Object>();
		try {
			rmap = categoryService.add(rmap,categoryListString,request);//添加商品分类
		} catch (Exception e) {
			logger.error("====添加商品分类异常===",e);
			rmap.put("status", "fail");//添加失败
		}
		return rmap;
	}
	/**
	 * 获取商品分类列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCategoryList")
	@ResponseBody
	public Map<String, Object> getCategoryList(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<CategoryModel> list = categoryService.getCategoryList(request);
			map.put("result", list);//结果集
			map.put("status", "success");//状态
		} catch (Exception e) {
			logger.error("====查询商品分类异常===",e);
		}
		return map;
	}
	
	/**
	 * 删除分类 进行逻辑删除
	 * @param catId
	 * @return
	 */
	@RequestMapping("/det")
	@ResponseBody
	public Map<String, Object> delete(String catId){
		Map<String, Object> rmap = new HashMap<String, Object>();
		try {
			int count = categoryService.delete(catId);
			if(count>0)
				rmap.put("status", "success");
			else
				rmap.put("status", "fail");
		} catch (Exception e) {
			logger.error("====删除商品分类异常===catId=="+catId,e);
		}
		return rmap;
	}
	
	/**
	 * 从分类跳转到添加商品的页面
	 * @return
	 */
	@RequestMapping("/addGoodView/{catId}")
	public ModelAndView addGood(String catId){
		ModelAndView model = new ModelAndView("goods/mgGoodAdd");
		model.addObject("catId", catId);//分类id
		return model;
	}
	/**
	 * 添加商品
	 * @return
	 */
	@RequestMapping("/addGoodViewInit")
	public ModelAndView addGoodViewInit(){
		return new ModelAndView("goods/mgGoodAdd");
	}
	/**
	 * 添加商品
	 * @return
	 */
	@RequestMapping("/addgoods")
	@ResponseBody
	public Map<String, Object>  addGoods(HttpServletRequest request,GoodsModel goods,String listAttrStr){
		Map<String, Object> rmap = new HashMap<String, Object>();
		try {
			boolean bl = goodsService.add(request,goods,listAttrStr);
			if(bl)
				rmap.put("status", "success");
			else
				rmap.put("status", "fail");
		} catch (Exception e) {
			logger.error("====添加商品异常===goods=="+goods,e);
			rmap.put("status", "error");
		}
		return rmap;
	}
	
	/**
	 * 获取商家自己所以的商品列表商品列表
	 * @return
	 */
	@RequestMapping("/listGoods")
	@ResponseBody
	public Map<String, Object> listGoods(GoodsModel goods){
		Map<String, Object> rmap = new HashMap<String, Object>();
		 try {
			 List<GoodsModel> list = goodsService.getListGoods(goods);
			 rmap.put("status", "success");
			 rmap.put("result", list);
		} catch (Exception e) {
			logger.error("====商品列表商品列表异常===listGoods()==",e);
			rmap.put("status", "error");
		}
		 return rmap;
	}
	
	/**
	 * 获取商品详情
	 * @return
	 */
	@RequestMapping("/getGood/{goodsId}")
	public ModelAndView getGood(@PathVariable String goodsId){
		ModelAndView model = new ModelAndView("goods/mgGoodUpdate");
		try {
			GoodsModel goodModel = goodsService.getGood(goodsId);
			model.addObject("good", goodModel);
		} catch (Exception e) {
			logger.error("====商品详情异常===getGood()==goodsId=="+goodsId,e);
			model.setViewName("error");//统一的错误页面
		}
		return model;
	}
	
	/**
	 * 商品列表初始化页面
	 * @return
	 */
	@RequestMapping("/initGoodList")
	public ModelAndView initGoodList(){
		return new ModelAndView("goods/mgGoodList");
	}
	
	/**
	 * 获取分类下面的所有商品
	 * @param catId
	 * @return
	 */
	@RequestMapping("/{catId}/{catName}/ctgGoodList")
	public ModelAndView ctgGoodList(@PathVariable("catId") String catId, @PathVariable("catName")  String catName){
		ModelAndView model = new ModelAndView("goods/mgCtgGoodList");
		model.addObject("catId", catId);
		model.addObject("catName", catName);
		return model;
	}
	/**
	 * 批量商品管理页面
	 * @return
	 */
	@RequestMapping("/batchGood")
	public ModelAndView batchGoodView(GoodsModel goods){
		ModelAndView model = new ModelAndView("goods/mgBatchGood");
		model.addObject("goods", goods);
		return model;
	}
	/**
	 * 批量上下架商品
	 * @param goods
	 */
	@RequestMapping("/batchOutGoods")
	@ResponseBody
	public Map<String, Object> batchOutGoods(String listGoods){
		Map<String, Object> rmap = new HashMap<String, Object>();
		try {
			int count = goodsService.batchOutGoods(listGoods);
			if(count>0){
				rmap.put("status", "success");
			}else{
				rmap.put("status", "fail");
			}
		} catch (Exception e) {
			logger.error("====批量上下架商品异常===batchOutGood==GoodsModel=="+listGoods,e);
			rmap.put("status", "error");
		}
		return rmap;
	}
	/**
	 * 把商品分类到每个大分类下面
	 * @param catId
	 * @param listGoodsString
	 * @return
	 */
	@RequestMapping("/toCatGoods")
	@ResponseBody
	public Map<String, Object> toCategoryGoods(String catId,String listGoodsString){
		Map<String, Object> rmap = new HashMap<String, Object>();
		try {
			int count = goodsService.toCategoryGoods(catId,listGoodsString);
			if(count>0)
				rmap.put("status", "success");
			else
				rmap.put("status", "fail");
		} catch (Exception e) {
			logger.error("====把商品分类到每个大分类下面异常===toCategoryGoods==catId=="+catId+"==listGoodsString=="+listGoodsString,e);
			rmap.put("status", "error");
		}
		return rmap;
		
	}
	
	/**
	 * 单件商品上下架
	 * @param goodsId
	 * @return
	 */
	@RequestMapping("/batechGoods")
	@ResponseBody
	public Map<String, Object> batethGoods(GoodsModel goods){
		Map<String, Object> rmap = new HashMap<String, Object>();
		try {
			int count = goodsService.batethGoods(goods);
			if(count>0)
				rmap.put("status", "success");
			else
				rmap.put("status", "fail");
		} catch (Exception e) {
			logger.error("====单件商品上下架异常===batethGoods==goods=="+goods,e);
			rmap.put("status", "error");
		}
		return rmap;
	}
	
	/**
	 * 删除单个商品
	 * @param goodsId
	 * @return
	 */
	@RequestMapping("/detGoods/{goodsId}")
	public ModelAndView detGoods(@PathVariable String goodsId){
		ModelAndView model = new ModelAndView("goods/mgGoodList");
		try {
			goodsService.detGoods(goodsId);
		} catch (Exception e) {
			logger.error("====删除商品异常===batethGoods==goodsId=="+goodsId,e);
			model.setViewName("error");
		}
		return model;
	}
	
}
