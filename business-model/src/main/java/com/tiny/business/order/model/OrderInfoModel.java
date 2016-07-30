package com.tiny.business.order.model;
/**
 * 订单基础信息
 * @author ThinkPad
 *
 */
public class OrderInfoModel {
	private String orderId; //订单ID
	private String userId;//所属用户
	private String orderStatus;//订单状态
	private String shippingStatus;//配送状态
	private String payStatus;//款项状态
	private String consignee;//收货人
	private String province;//省份
	private String city;//城市
	private String district;//区域
	private String address;//地址
	private String mobile;//手机
	private String shippingId;//配送方式
	private String shippingName;//配送方式名称
	private String payId;//支付方式
	private String payName;//支付方式名称
	private String invPayee;//发票抬头
	private String invContent;//发票内容
	private String goodsAmount;//商品总金额
	private String shippingFee;//运费
	private String moneyPaid;//已付款金额
	private Integer integral;//积分
	private String confirmTime;//确认时间
	private String payTime;//支付时间
	private String shippingTime;//发货时间
	private String extensionCode;//操作ID
	private String payNote;//支付备注
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getShippingStatus() {
		return shippingStatus;
	}
	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getShippingId() {
		return shippingId;
	}
	public void setShippingId(String shippingId) {
		this.shippingId = shippingId;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public String getInvPayee() {
		return invPayee;
	}
	public void setInvPayee(String invPayee) {
		this.invPayee = invPayee;
	}
	public String getInvContent() {
		return invContent;
	}
	public void setInvContent(String invContent) {
		this.invContent = invContent;
	}
	public String getGoodsAmount() {
		return goodsAmount;
	}
	public void setGoodsAmount(String goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	public String getShippingFee() {
		return shippingFee;
	}
	public void setShippingFee(String shippingFee) {
		this.shippingFee = shippingFee;
	}
	public String getMoneyPaid() {
		return moneyPaid;
	}
	public void setMoneyPaid(String moneyPaid) {
		this.moneyPaid = moneyPaid;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public String getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getShippingTime() {
		return shippingTime;
	}
	public void setShippingTime(String shippingTime) {
		this.shippingTime = shippingTime;
	}
	public String getExtensionCode() {
		return extensionCode;
	}
	public void setExtensionCode(String extensionCode) {
		this.extensionCode = extensionCode;
	}
	public String getPayNote() {
		return payNote;
	}
	public void setPayNote(String payNote) {
		this.payNote = payNote;
	}
	
}
