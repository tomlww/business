package com.tiny.business.util;

import java.lang.reflect.Field;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import sun.misc.BASE64Encoder;

import com.tiny.business.sys.dao.DictitemMapper;
import com.tiny.business.sys.model.DictitemModel;
import com.tiny.business.wx.model.MpAccount;

@Component("SpringBeanDefineConfigue")
public class SpringBeanDefineConfigue implements ApplicationListener<ContextRefreshedEvent>{
	private static final Log logger = LogFactory.getLog(SpringBeanDefineConfigue.class);
	/*@Autowired
	private DictitemMapper dictitemMapper;*/
	/**
	 * ContextRefreshedEvent为初始化完毕事件 ;被初始化或刷新触发
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			/*if(dictitemMapper != null){
				List<DictitemModel> list = dictitemMapper.getDictitem("WX_INfo");
				
			}*/
	         JedisPool pool = JedisPoolUtile.getPool();
			 Jedis jedis = pool.getResource();
			 
	         if(!jedis.exists("publicKey")&&!jedis.exists("privateKey")){
	        	 KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		         //密钥位数
		         keyPairGen.initialize(1024);
		         //密钥对
		         KeyPair keyPair = keyPairGen.generateKeyPair();
		         // 公钥
		         PublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		         // 私钥
		         PrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		         
		         String publicKeyString = RSAUtil.getKeyString(publicKey);
		         String privateKeyString = RSAUtil.getKeyString(privateKey);
		         
		         jedis.sadd("publicKey", publicKeyString);
		         jedis.sadd("privateKey", privateKeyString);
	         }
			
		} catch (Exception e) {
			logger.error("==========onApplicationEvent ==== 初始化公钥私钥一场==",e);
		}
	}
	public static void main(String[] args) {
		try {
			MpAccount account = new MpAccount();
			//Class<?> cls = Class.forName("com.tiny.business.wx.model.Account");
			Field field=account.getClass().getDeclaredField("token");
			field.setAccessible(true);
			field.set(account, "2323"); //赋值
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}////////////////////////通过类的名称反射类
	}
	
}
