package com.tiny.business.sys.service.impl;



import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiny.business.sys.dao.AdPositionMapper;
import com.tiny.business.sys.model.AdPositionModel;
import com.tiny.business.sys.service.IndexService;
import com.tiny.business.sys.vo.AdPositionVo;

@Service("indexService")
public class IndexServiceImpl implements IndexService{
	
	@Autowired
	private AdPositionMapper adPositionMapper;
	
	@Override
	public List<AdPositionModel> getAdPositionInfo(AdPositionVo adv) throws Exception{
		AdPositionModel adpModel = new AdPositionModel();
		BeanUtils.copyProperties(adv, adpModel);
		return adPositionMapper.queryForList(adpModel);
	}

}
