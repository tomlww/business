package com.tiny.business.util;

public class StringUtil {
	
	public static boolean isStrEmpty(String str){
		if(str == null || str.length()<=0)
			return false;
		else
			return true;
	}
	/**
	 * obj 转成integer
	 * @param obj
	 * @return
	 */
	public static Integer objConvertInteger(Object obj){
		if(obj == null || "".equals(obj)){
			return 0;
		}
		return Integer.valueOf(String.valueOf(obj));
	}
}
