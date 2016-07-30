package com.tiny.business.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

public class Property {
	Properties props = new Properties();

	InputStream in = null;

	public Property(String file) {
		try {
			in = getClass().getClassLoader().getResourceAsStream(file);
			props.load(in);
		} catch (Exception e) {
		}
	}

	public String getValue(String name) throws Exception {
		return props.getProperty(name);
		//return new String(props.getProperty(name).getBytes("ISO8859-1"), "UTF-8");
	}

	public String[] getPropertyNames() {
		ArrayList<String> a = (ArrayList<String>) Collections.list(props.propertyNames());
		String[] keys = (String[]) a.toArray(new String[a.size()]);
		return keys;
	}
	// public static void main(String[] args) throws SQLException {
	// Property props1 = new Property("jdbc.properties");
	// for(String a:props1.getPropertyNames())
	// System.out.println(a);
	// }
}
