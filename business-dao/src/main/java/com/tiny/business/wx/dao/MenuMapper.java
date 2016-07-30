package com.tiny.business.wx.dao;

import java.util.List;

import com.tiny.business.wx.model.AccountMenu;

public interface MenuMapper {

	List<AccountMenu> listWxMenus(String gid);

}
