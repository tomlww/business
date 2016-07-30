package com.tiny.business.wx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tiny.business.wx.api.process.WxApiClient;
import com.tiny.business.wx.api.vo.Matchrule;
import com.tiny.business.wx.api.vo.MsgRequest;
import com.tiny.business.wx.dao.MenuMapper;
import com.tiny.business.wx.model.AccountFans;
import com.tiny.business.wx.model.AccountMenu;
import com.tiny.business.wx.model.MpAccount;
import com.tiny.business.wx.service.WXService;

@Service("wXService")
public class WXServiceImpl implements WXService{
	
	@Autowired
	private MenuMapper menuMapper;
	
	/*@Autowired
	private */
	
	@Override
	public String processMsg(MsgRequest msgRequest, MpAccount mpAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject publishMenu(String gid, MpAccount mpAccount) {
		List<AccountMenu> menus = menuMapper.listWxMenus(gid);
		
		Matchrule matchrule = new Matchrule();
		String menuJson = prepareMenus(menus,matchrule);
		JSONObject rstObj = WxApiClient.publishMenus(menuJson,mpAccount);//创建普通菜单
		
		//以下为创建个性化菜单demo，只为男创建菜单；其他个性化需求 设置 Matchrule 属性即可
//		matchrule.setSex("1");//1-男 ；2-女
//		JSONObject rstObj = WxApiClient.publishAddconditionalMenus(menuJson,mpAccount);//创建个性化菜单
		
		if(rstObj != null){//成功，更新菜单组
			/*if(rstObj.containsKey("menu_id")){
				.updateMenuGroupDisable();
				menuGroupDao.updateMenuGroupEnable(gid);
			}else if(rstObj.containsKey("errcode") && rstObj.getInt("errcode") == 0){
				menuGroupDao.updateMenuGroupDisable();
				menuGroupDao.updateMenuGroupEnable(gid);
			}*/
		}
		return rstObj;
	}

	@Override
	public JSONObject deleteMenu(MpAccount mpAccount) {
		JSONObject rstObj = WxApiClient.deleteMenu(mpAccount);
		/*if(rstObj != null && rstObj.getInteger("errcode") == 0){//成功，更新菜单组
			menuGroupDao.updateMenuGroupDisable();
		}*/
		return rstObj;
	}

	@Override
	public boolean syncAccountFansList(MpAccount mpAccount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AccountFans syncAccountFans(String openId, MpAccount mpAccount,
			boolean merge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountFans getFansByOpenId(String openid, MpAccount mpAccount) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 获取微信公众账号的菜单
	 * @param menus	菜单列表
	 * @param matchrule	个性化菜单配置
	 * @return
	 */
	private String prepareMenus(List<AccountMenu> menus,Matchrule matchrule) {
		if(!CollectionUtils.isEmpty(menus)){
			List<AccountMenu> parentAM = new ArrayList<AccountMenu>();
			Map<Long,List<JSONObject>> subAm = new HashMap<Long,List<JSONObject>>();
			for(AccountMenu m : menus){
				if(m.getParentid() == 0L){//一级菜单
					parentAM.add(m);
				}else{//二级菜单
					if(subAm.get(m.getParentid()) == null){
						subAm.put(m.getParentid(), new ArrayList<JSONObject>());
					}
					List<JSONObject> tmpMenus = subAm.get(m.getParentid());
					tmpMenus.add(getMenuJSONObj(m));
					subAm.put(m.getParentid(), tmpMenus);
				}
			}
			JSONArray arr = new JSONArray();
			for(AccountMenu m : parentAM){
				if(subAm.get(m.getId()) != null){//有子菜单
					arr.add(getParentMenuJSONObj(m,subAm.get(m.getId())));
				}else{//没有子菜单
					arr.add(getMenuJSONObj(m));
				}
			}
			JSONObject root = new JSONObject();
			root.put("button", arr);
			root.put("matchrule", JSONObject.toJSON(matchrule).toString());
			return JSONObject.toJSON(root).toString();
		}
		return "error";
	}
	private JSONObject getParentMenuJSONObj(AccountMenu menu,List<JSONObject> subMenu){
		JSONObject obj = new JSONObject();
		obj.put("name", menu.getName());
		obj.put("sub_button", subMenu);
		return obj;
	}
	
	/**
	 * 此方法是构建菜单对象的；构建菜单时，对于  key 的值可以任意定义；
	 * 当用户点击菜单时，会把key传递回来；对已处理就可以了
	 * @param menu
	 * @return
	 */
	private JSONObject getMenuJSONObj(AccountMenu menu){
		JSONObject obj = new JSONObject();
		obj.put("name", menu.getName());
		obj.put("type", menu.getMtype());
		if("click".equals(menu.getMtype())){//事件菜单
			if("fix".equals(menu.getEventType())){//fix 消息
				obj.put("key", "_fix_" + menu.getMsgId());//以 _fix_ 开头
			}else{
				if(StringUtils.isEmpty(menu.getInputcode())){//如果inputcode 为空，默认设置为 subscribe，以免创建菜单失败
					obj.put("key", "subscribe");
				}else{
					obj.put("key", menu.getInputcode());
				}
			}
		}else{//链接菜单-view
			obj.put("url", menu.getUrl());
		}
		return obj;
	}
}
