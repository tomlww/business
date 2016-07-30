package com.tiny.business.sys.vo;

import java.io.Serializable;
/**
 * 广告类vo
 * @author ThinkPad
 *
 */
public class AdPositionVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String positionId; 	//位置编号
	private String positionName; //位置名称
	private String adWidth;   //广告宽度
	private String adHeight;  //广告高度
	private String positionDesc; //位置描述
	private String mediaType; //广告类型
	private String userId;//用户id或者公众号id
	private String clickCount;//点击次数
	private String enabled;//是否启用
	private String srcPath;//图片路径
	
	public String getSrcPath() {
		return srcPath;
	}
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getAdWidth() {
		return adWidth;
	}
	public void setAdWidth(String adWidth) {
		this.adWidth = adWidth;
	}
	public String getAdHeight() {
		return adHeight;
	}
	public void setAdHeight(String adHeight) {
		this.adHeight = adHeight;
	}
	public String getPositionDesc() {
		return positionDesc;
	}
	public void setPositionDesc(String positionDesc) {
		this.positionDesc = positionDesc;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getClickCount() {
		return clickCount;
	}
	public void setClickCount(String clickCount) {
		this.clickCount = clickCount;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
}
