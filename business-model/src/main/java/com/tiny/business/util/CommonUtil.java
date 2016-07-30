package com.tiny.business.util;

import java.util.Date;

public class CommonUtil {
	/**
	 * 获取6位随机数
	 * @return
	 */
	public static String createRandomNumber() {
		long range = (new Date().getTime() - 5);
		double rand = Math.random();
		long x = Math.round(Math.random() * 9999999);
		long y = Math.round((rand * range) * x);
		String result = String.valueOf(y);
		if (result.length() >= 8) {
			result = result.substring(0, 8);
		}
		if (result.length() < 8) {
			result = createRandomNumber();
		}
		return result;
	}
	public static void main(String[] args) {
		String str = createRandomNumber();
		System.out.println(str);
	}
}
