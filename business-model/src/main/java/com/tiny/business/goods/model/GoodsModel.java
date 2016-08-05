package com.tiny.business.goods.model;

import java.util.List;


/**
 * 商品
 * @author ThinkPad
 *
 */
public class GoodsModel {
	private String goodsId;//商品编号主键
	private String catId;//商品分类ID
	private String catName;//分类名称
	private String goodsSn;//商品货号
	private String goodsName;//商品名称
	private String goodsNameStyle;//商品名称显示样式
	private String clickCount;//点击次数
	private String brandId;//品牌id
	private String providerName;//供货商名称
	private int goodsNumber;//库存数量
	private int goodsWeight;//商品重量
	private double shopPrice;//商店售价
	private String keywords;//商品关键词
	private String goodsBrief;//商品简单说明
	private String isReal;//是否实体商品
	private String extensionCode;//虚拟商品代码
	private String isOnSale;//能否销售(上架、下架)
	private int integral;//商品的积分
	private String suppliersId;//供货商 id
	private String goodsTypeId;//商品型号
	private GoodsTypeModel goodsTypeModel;//商品类型model
	private List<GoodsDetailImgModel> goodsDetailImg;//商品详情图集合
	private List<GoodsGalleryModel> goodsGallery;//商品图片集合
	private String goodsAttr;//商品属性
	
	public GoodsTypeModel getGoodsTypeModel() {
		return goodsTypeModel;
	}
	public void setGoodsTypeModel(GoodsTypeModel goodsTypeModel) {
		this.goodsTypeModel = goodsTypeModel;
	}
	
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getCatId() {
		return catId;
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	public String getGoodsSn() {
		return goodsSn;
	}
	public void setGoodsSn(String goodsSn) {
		this.goodsSn = goodsSn;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsNameStyle() {
		return goodsNameStyle;
	}
	public void setGoodsNameStyle(String goodsNameStyle) {
		this.goodsNameStyle = goodsNameStyle;
	}
	public String getClickCount() {
		return clickCount;
	}
	public void setClickCount(String clickCount) {
		this.clickCount = clickCount;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getGoodsBrief() {
		return goodsBrief;
	}
	public void setGoodsBrief(String goodsBrief) {
		this.goodsBrief = goodsBrief;
	}
	public String getIsReal() {
		return isReal;
	}
	public void setIsReal(String isReal) {
		this.isReal = isReal;
	}
	public String getExtensionCode() {
		return extensionCode;
	}
	public void setExtensionCode(String extensionCode) {
		this.extensionCode = extensionCode;
	}
	public String getIsOnSale() {
		return isOnSale;
	}
	public void setIsOnSale(String isOnSale) {
		this.isOnSale = isOnSale;
	}
	public int getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(int goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	public int getGoodsWeight() {
		return goodsWeight;
	}
	public void setGoodsWeight(int goodsWeight) {
		this.goodsWeight = goodsWeight;
	}
	public double getShopPrice() {
		return shopPrice;
	}
	public void setShopPrice(double shopPrice) {
		this.shopPrice = shopPrice;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	public String getSuppliersId() {
		return suppliersId;
	}
	public void setSuppliersId(String suppliersId) {
		this.suppliersId = suppliersId;
	}
	public List<GoodsDetailImgModel> getGoodsDetailImg() {
		return goodsDetailImg;
	}
	public void setGoodsDetailImg(List<GoodsDetailImgModel> goodsDetailImg) {
		this.goodsDetailImg = goodsDetailImg;
	}
	public List<GoodsGalleryModel> getGoodsGallery() {
		return goodsGallery;
	}
	public void setGoodsGallery(List<GoodsGalleryModel> goodsGallery) {
		this.goodsGallery = goodsGallery;
	}
	public String getGoodsTypeId() {
		return goodsTypeId;
	}
	public void setGoodsTypeId(String goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}
	public String getGoodsAttr() {
		return goodsAttr;
	}
	public void setGoodsAttr(String goodsAttr) {
		this.goodsAttr = goodsAttr;
	}
	
	
}
