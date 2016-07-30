package com.tiny.business.util;

import org.springframework.beans.BeanUtils;

public class ClassUtil {

	public static <T> T returnClassObject(T t1,T t2){
		BeanUtils.copyProperties(t1, t2);
		return t2;
	}
}
