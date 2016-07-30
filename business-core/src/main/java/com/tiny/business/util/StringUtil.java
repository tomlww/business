package com.tiny.business.util;

public class StringUtil {
	
	public static boolean isStrEmpty(String str){
		if(str == null || str.length()<=0)
			return false;
		else
			return true;
	}
}
