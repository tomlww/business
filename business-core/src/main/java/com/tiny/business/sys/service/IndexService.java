package com.tiny.business.sys.service;

import java.util.List;

import com.tiny.business.sys.model.AdPositionModel;
import com.tiny.business.sys.vo.AdPositionVo;

public interface IndexService {
	/**
	 * 查询广告轮播图片地址
	 * @param adv
	 * @return
	 * @throws Exception 
	 */
	List<AdPositionModel> getAdPositionInfo(AdPositionVo adv) throws Exception;

}
