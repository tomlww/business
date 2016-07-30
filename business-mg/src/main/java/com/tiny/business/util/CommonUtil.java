package com.tiny.business.util;

import java.util.Date;
import java.util.UUID;

public class CommonUtil {

	public static String getUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static String stringCap(String str){
		return str.substring(0,1).toLowerCase() + str.substring(1);
	}

	public static String createRandomNumber() {
		long range = (new Date().getTime() - 5);
		double rand = Math.random();
		long x = Math.round(Math.random() * 9999999);
		long y = Math.round((rand * range) * x);
		String result = String.valueOf(y);
		if (result.length() > 8) {
			result = result.substring(0, 8);
		}
		if (result.length() < 8) {
			result = createRandomNumber();
		}
		return result;
	}
	
}
