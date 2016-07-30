package com.tiny.business.goods.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tiny.business.goods.model.CategoryModel;

public interface CategoryService {
	/**
	 * 添加商品分类
	 * @param rmap
	 * @param categoryListString
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> add(Map<String, Object> rmap,String  categoryListString, HttpServletRequest request) throws Exception;
	/**
	 * 获取商品分类
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<CategoryModel> getCategoryList(HttpServletRequest request) throws Exception;
	/**
	 * 删除商品分类
	 * @param catId
	 * @return
	 */
	public int delete(String catId) throws Exception;

}
