package com.tiny.business.order.model;

import java.io.Serializable;
import java.util.List;

import com.tiny.business.goods.model.GoodsAttrModel;

/**
 * 购物车
 * @author user
 *
 */
public class CartModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String recId;//购物车记录编号
	private String userId; //用户ID
	private String goodsId; //商品ID
	private String goodsSn;//商品型号
	private String goodsName;//商品名称
	private String sessionId;//用户SessionID
	private double goodsPrice;//商品价格
	private int goodsNumber;//购买数量
	private String goodsAttr;//商品属性编号 多个商品属性用-隔开
	private String isGift;//是否为赠品
	private List<GoodsAttrModel> listGoodsAttr; //商品属性
	
	public String getRecId() {
		return recId;
	}
	public void setRecId(String recId) {
		this.recId = recId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public int getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(int goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	public String getGoodsAttr() {
		return goodsAttr;
	}
	public void setGoodsAttr(String goodsAttr) {
		this.goodsAttr = goodsAttr;
	}
	public String getGoodsSn() {
		return goodsSn;
	}
	public void setGoodsSn(String goodsSn) {
		this.goodsSn = goodsSn;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getIsGift() {
		return isGift;
	}
	public void setIsGift(String isGift) {
		this.isGift = isGift;
	}
	public List<GoodsAttrModel> getListGoodsAttr() {
		return listGoodsAttr;
	}
	public void setListGoodsAttr(List<GoodsAttrModel> listGoodsAttr) {
		this.listGoodsAttr = listGoodsAttr;
	}
	
	
}
