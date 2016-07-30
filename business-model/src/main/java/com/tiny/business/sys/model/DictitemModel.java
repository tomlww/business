package com.tiny.business.sys.model;
/**
 * 数据字典model
 * @author ThinkPad
 *
 */
public class DictitemModel {
	private String guid; //主键
	private String typeCode;//类型
	private String itemCode;//key
	private String itemName;//值
	private String parentItem;//描述
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getParentItem() {
		return parentItem;
	}
	public void setParentItem(String parentItem) {
		this.parentItem = parentItem;
	}
	
	
}
