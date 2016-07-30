package com.tiny.business.wx.controller;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.tiny.business.sys.dao.DictitemMapper;
import com.tiny.business.sys.model.DictitemModel;
import com.tiny.business.wx.api.process.WxMemoryCacheClient;
import com.tiny.business.wx.model.MpAccount;

@Component("SpringBeanDefineConfigue")
public class SpringBeanDefineConfigue implements ApplicationListener<ContextRefreshedEvent>{
	private static final Log logger = LogFactory.getLog(SpringBeanDefineConfigue.class);
	@Autowired
	private DictitemMapper dictitemMapper;
	/**
	 * ContextRefreshedEvent为初始化完毕事件 ;被初始化或刷新触发
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			if(dictitemMapper != null){
				List<DictitemModel> list = dictitemMapper.getDictitem("WX_INfo");
				MpAccount account = new MpAccount();
				//Class<?> cls=Class.forName("com.tiny.business.wx.model.Account");////////////////////////通过类的名称反射类
				for(DictitemModel d:list){
					Field field=account.getClass().getDeclaredField(d.getItemCode());/////这个对应的是属性
					field.setAccessible(true);
					field.set(account, d.getItemName()); //赋值
				}
				WxMemoryCacheClient.addMpAccount(account);
			}
		} catch (Exception e) {
			logger.error("==========onApplicationEvent ==== 初始化微信信息异常==",e);
		}
	}
	public static void main(String[] args) {
		try {
			MpAccount account = new MpAccount();
			//Class<?> cls = Class.forName("com.tiny.business.wx.model.Account");
			Field field=account.getClass().getDeclaredField("token");
			field.setAccessible(true);
			field.set(account, "2323"); //赋值
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}////////////////////////通过类的名称反射类
	}
	
}
