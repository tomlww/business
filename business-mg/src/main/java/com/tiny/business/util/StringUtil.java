package com.tiny.business.util;

import java.util.UUID;

/**
 * 工具类
 * @author ThinkPad
 *
 */
public class StringUtil {
	/**
	 * 获取去掉-的guid
	 * @return
	 */
	public static String getGuid(){
		return UUID.randomUUID().toString().replaceAll("-", "");  
	}
}
