package com.tiny.business.goods.model;
/**
 * 商品相册model
 * @author ThinkPad
 *
 */
public class GoodsGalleryModel {
	private String imgId;//商品相片编号
	private String goodsId; //所属商品编号
	private String imgUrl;//图片链接URL
	private String thumbUrl;//缩略图url
	private String imgOriginal;//原始图片
	public String getImgId() {
		return imgId;
	}
	public void setImgId(String imgId) {
		this.imgId = imgId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getThumbUrl() {
		return thumbUrl;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	public String getImgOriginal() {
		return imgOriginal;
	}
	public void setImgOriginal(String imgOriginal) {
		this.imgOriginal = imgOriginal;
	}
	
}
