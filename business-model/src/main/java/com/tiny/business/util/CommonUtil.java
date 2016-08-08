package com.tiny.business.util;

import java.io.File;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
	public static String getUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static String stringCap(String str){
		return str.substring(0,1).toLowerCase() + str.substring(1);
	}
	/**
	 * 创建几位随机数
	 * @param number
	 * @return
	 */
	public static String createRandomNumber(int number) {
		long range = (new Date().getTime() - 5);
		double rand = Math.random();
		long x = Math.round(Math.random() * 9999);
		long y = Math.round((rand * range) * x);
		String result = String.valueOf(y);
		if (result.length() > number) {
			result = result.substring(0, number);
		}
		if (result.length() < number) {
			result = createRandomNumber(number);
		}
		return result;
	}
	/**
	 * md5加密
	 * @param s
	 * @return
	 */
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	/**
	 * 获取非负浮点数
	 * @return
	 */
	public static boolean isNum(String number){
		Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?$"); 
		   Matcher isNum = pattern.matcher(number);
		   if( !isNum.matches() ){
			  return false;
		   } else{
			  return true;
		   }
	}
	/**
	 * 金额格式化成.00
	 * @param number
	 * @return
	 */
	public static String moneyNum(String number){
		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern(".00");
		return myformat.format(Integer.valueOf(number));
	}
	
	/**
	 * 判断对象是否为空
	 * @param obj
	 * @return
	 */
	public static boolean isNnll(Object obj){
		if(null == obj || "".equals(obj)){
			return false;
		}
		return true;
	}
	
	
	public static String getPayName(String name){
		String str = "";
		if("alipay_pc_direct".equals(name)){
			str = "支付宝";
		}else if("wx_pub_qr".equals(name)){
			str = "微信支付";
		}else{
			str = "下线支付";
		}
		return str;
	}
	
	public static boolean deleteDirectory(String sPath) {  
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    boolean    flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    return flag;
	    //删除当前目录  
	    /* 
	     *   if (!flag) return false;  
	   	if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  */
	} 
	
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String sPath) {  
	  boolean  flag = false;  
	  File   file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  
	
	public static void main(String[] args) {
		String str = createRandomNumber();
		System.out.println(str);
	}
}
