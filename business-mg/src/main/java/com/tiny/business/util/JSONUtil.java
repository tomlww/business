package com.tiny.business.util;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONObject;

public class JSONUtil {

	public static String renderString(JSONObject jsonObj){
		try {
			return new String(jsonObj.toString().getBytes("UTF-8"),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
