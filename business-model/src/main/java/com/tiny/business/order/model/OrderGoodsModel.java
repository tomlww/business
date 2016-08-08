package com.tiny.business.order.model;

import java.io.Serializable;
/**
 * 订单商品
 * @author user
 *
 */
public class OrderGoodsModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String recId;//记录编号
	private String orderId;//订单ID
	private String goodsId;//商品ID
	private String goodsName;//商品名称
	private int goodsNumber;//商品数量
	private double goodsPrice;//交易时的价格
	private int sendNumber;//已发货数
	private String isGift;//是否赠品
	private String goodsAttr;//商品规格
	public String getRecId() {
		return recId;
	}
	public void setRecId(String recId) {
		this.recId = recId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	
	public int getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(int goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	public double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public int getSendNumber() {
		return sendNumber;
	}
	public void setSendNumber(int sendNumber) {
		this.sendNumber = sendNumber;
	}
	public String getIsGift() {
		return isGift;
	}
	public void setIsGift(String isGift) {
		this.isGift = isGift;
	}
	public String getGoodsAttr() {
		return goodsAttr;
	}
	public void setGoodsAttr(String goodsAttr) {
		this.goodsAttr = goodsAttr;
	}
	
	
}
