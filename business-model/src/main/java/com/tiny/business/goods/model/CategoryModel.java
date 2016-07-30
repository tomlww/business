package com.tiny.business.goods.model;

import java.io.Serializable;

/**
 * 商品分类表
 * @author ThinkPad
 *
 */
public class CategoryModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String catId;  //分类编号
	private String catName; //类别名称
	private String parentId; //上级分类
	private String sortOrder;//排序序号
	private String suppliersId;//供货商 id
	private String imgUrl;  //  分类图片地址
	
	public String getCatId() {
		return catId;
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getSuppliersId() {
		return suppliersId;
	}
	public void setSuppliersId(String suppliersId) {
		this.suppliersId = suppliersId;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
}
