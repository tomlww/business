package com.tiny.business.goods.model;

import java.io.Serializable;

public class GoodsTypeModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String catId; //商品类型ID
	private String catName; //商品类型名称
	private String goodsTypeImg;//商品分类广告图
	private String hnysjy; //微信公众号
	private String parentCatId;//父级id
	
	public String getParentCatId() {
		return parentCatId;
	}
	public void setParentCatId(String parentCatId) {
		this.parentCatId = parentCatId;
	}
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
	public String getGoodsTypeImg() {
		return goodsTypeImg;
	}
	public void setGoodsTypeImg(String goodsTypeImg) {
		this.goodsTypeImg = goodsTypeImg;
	}
	public String getHnysjy() {
		return hnysjy;
	}
	public void setHnysjy(String hnysjy) {
		this.hnysjy = hnysjy;
	}
	
}
