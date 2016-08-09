package com.tiny.business.util;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSAUtil {
	//加解密类
	static  Cipher  cipher = null;//Cipher.getInstance("RSA/ECB/PKCS1Padding");
	 /**
     * 得到公钥
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
          byte[] keyBytes;
          keyBytes = (new BASE64Decoder()).decodeBuffer(key);

          X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
          KeyFactory keyFactory = KeyFactory.getInstance("RSA");
          PublicKey publicKey = keyFactory.generatePublic(keySpec);
          return publicKey;
    }
    /**
     * 得到私钥
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
          byte[] keyBytes;
          keyBytes = (new BASE64Decoder()).decodeBuffer(key);

          PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
          KeyFactory keyFactory = KeyFactory.getInstance("RSA");
          PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
          return privateKey;
    }
    
    /**
     * 得到密钥字符串（经过base64编码）
     * @return
     */
    public static String getKeyString(Key key) throws Exception {
          byte[] keyBytes = key.getEncoded();
          String s = (new BASE64Encoder()).encode(keyBytes);
          return s;
    }
    /**
     * 解密
     * @param content 内容
     * @param publicKeyString 公钥
     * @return  string
     * @throws Exception
     */
    public static String publicEncrypt (String content,String publicKeyString) throws Exception {
    	PublicKey publicKey = getPublicKey(publicKeyString);
    	byte[] contentByte =  Base64.decodeBase64(content);
    	 //解密
    	cipher = Cipher.getInstance("RSA");//Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] deBytes = cipher.doFinal(contentByte);
        return new String(deBytes);
    }
    
    /**
     * 加密
     * @param content 内容
     * @param publicKeyString 公钥
     * @return  string
     * @throws Exception
     */
    public static String privateEncrypt (String content,String privateKeyString) throws Exception {
    	PrivateKey privateKey = getPrivateKey(privateKeyString);
    	byte[] contentByte = content.getBytes();
    	 //加密
    	cipher = Cipher.getInstance("RSA");//Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] enBytes = cipher.doFinal(contentByte);
       return  Base64.encodeBase64String(enBytes);
    }
    
}
