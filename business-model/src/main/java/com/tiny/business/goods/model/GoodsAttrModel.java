package com.tiny.business.goods.model;

import java.io.Serializable;
/**
 * 商品属性model
 */
public class GoodsAttrModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String goodsAttrId;//商品属性编号
	private String goodsId;//商品编号
	private String attrName;//属性名称
	private String attrValue;//属性值
	private String goodsSn;//商品型号
	
	public String getGoodsAttrId() {
		return goodsAttrId;
	}
	public void setGoodsAttrId(String goodsAttrId) {
		this.goodsAttrId = goodsAttrId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	public String getGoodsSn() {
		return goodsSn;
	}
	public void setGoodsSn(String goodsSn) {
		this.goodsSn = goodsSn;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
