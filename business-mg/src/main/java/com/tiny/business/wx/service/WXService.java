package com.tiny.business.wx.service;

import com.alibaba.fastjson.JSONObject;
import com.tiny.business.wx.api.vo.MsgRequest;
import com.tiny.business.wx.model.AccountFans;
import com.tiny.business.wx.model.MpAccount;

public interface WXService {
		//消息处理
		public String processMsg(MsgRequest msgRequest,MpAccount mpAccount);

		//发布菜单
		public JSONObject publishMenu(String gid,MpAccount mpAccount);
		
		//删除菜单
		public JSONObject deleteMenu(MpAccount mpAccount);
		
		//获取用户列表
		public boolean syncAccountFansList(MpAccount mpAccount);
		
		//获取单个用户信息
		public AccountFans syncAccountFans(String openId, MpAccount mpAccount, boolean merge);
		
		//根据openid 获取粉丝，如果没有，同步粉丝
		public AccountFans getFansByOpenId(String openid,MpAccount mpAccount);
		
}
