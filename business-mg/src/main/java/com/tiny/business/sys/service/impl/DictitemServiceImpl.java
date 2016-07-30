package com.tiny.business.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiny.business.sys.dao.DictitemMapper;
import com.tiny.business.sys.model.DictitemModel;
import com.tiny.business.sys.service.DictitemService;

@Service("dictitemService")
public class DictitemServiceImpl implements DictitemService{
	
	@Autowired
	private DictitemMapper dictitemMapper;
	
	@Override
	public Map<String, Object> getFtpInfo() {
		Map<String, Object> rmap = new HashMap<String, Object>();
		List<DictitemModel> listDictitem = dictitemMapper.getDictitem("FTP_INfO");
		for(DictitemModel d:listDictitem){
			rmap.put(d.getItemCode(), d.getItemName());//获取上传ftp信息
		}
		return rmap;
	}

}
