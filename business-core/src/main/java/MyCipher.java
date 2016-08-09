import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

import com.tiny.business.util.RSAUtil;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



public class MyCipher {
	public static void main(String[] args) throws Exception{
		/*KeyPairGenerator keyPairGen = null; 
	    keyPairGen = KeyPairGenerator.getInstance("RSA");  
		 // 初始化密钥对生成器，密钥大小为96-1024位  
        keyPairGen.initialize(1024,new SecureRandom());  
        // 生成一个密钥对，保存在keyPair中  
        KeyPair keyPair = keyPairGen.generateKeyPair();  
        // 得到私钥  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
       System.out.println("私钥"+privateKey);
       System.out.println("公钥"+publicKey);
        byte[] b=privateKey.getEncoded();
        // 得到公钥字符串  
        byte[] publicKeyStsring = Base64.encodeBase64(b);  
        String s=new String(publicKeyStsring);
        System.out.println( Base64.encodeBase64String(b)); ;
        System.out.println(s);
      //加解密类
        Cipher cipher = Cipher.getInstance("RSA");//Cipher.getInstance("RSA/ECB/PKCS1Padding");
        //明文
        byte[] plainText = "我们都很好！邮件：@sina.com".getBytes();
        //加密
        cipher.init();
        byte[] enBytes = cipher.doFinal(plainText);
        //System.out.println("加密之后"+ Base64.encodeBase64String(enBytes));
        //解密
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[]deBytes = cipher.doFinal(enBytes);
*/
       /* publicKeyString = getKeyString(publicKey);
        System.out.println("public:\n" +publicKeyString);

        privateKeyString = getKeyString(privateKey);
        System.out.println("private:\n" + privateKeyString);*/

       // s2 = new String(deBytes);
       // System.out.println(s2);
        // 得到公钥  
		/*Cipher cipher = Cipher.getInstance("RSA");
		 cipher.init(Cipher.ENCRYPT_MODE, publicKey); */ 
       // System.out.println(RSASignature.sign("admin", Base64.encodeBase64String(b), "utf-8"));;
		
		
		 KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
         //密钥位数
         keyPairGen.initialize(1024);
         //密钥对
         KeyPair keyPair = keyPairGen.generateKeyPair();

         // 公钥
         PublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

         // 私钥
         PrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

         String publicKeyString = getKeyString(publicKey);
        // System.out.println("public:\n" + publicKeyString);

         String privateKeyString = getKeyString(privateKey);
        // System.out.println("private:\n" + privateKeyString);

        /* //加解密类
         Cipher cipher = Cipher.getInstance("RSA");//Cipher.getInstance("RSA/ECB/PKCS1Padding");

         //明文
         byte[] plainText = "我们都很好！邮件：@sina.com".getBytes();

         publicKey = getPublicKey(publicKeyString);
         privateKey = getPrivateKey(privateKeyString);
         System.out.println("public:\n" + publicKey);
         System.out.println("private:\n" + privateKey);
         //加密
         cipher.init(Cipher.ENCRYPT_MODE, publicKey);
         byte[] enBytes = cipher.doFinal(plainText);
         enBytes =  Base64.decodeBase64(Base64.encodeBase64String(enBytes));

//通过密钥字符串得到密钥

         //解密
         cipher.init(Cipher.DECRYPT_MODE, privateKey);
         byte[]deBytes = cipher.doFinal(enBytes);

         publicKeyString = getKeyString(publicKey);
         System.out.println("public:\n" +publicKeyString);

         privateKeyString = getKeyString(privateKey);
         System.out.println("private:\n" + privateKeyString);

         String s = new String(deBytes);
         System.out.println(s);*/
        String str =  RSAUtil.privateEncrypt("账上无3", privateKeyString);
         System.out.println(RSAUtil.publicEncrypt(str, publicKeyString));
         
	}
	
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
}
