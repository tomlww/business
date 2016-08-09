package com.tiny.business.sys.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.tiny.business.util.JedisPoolUtile;
import com.tiny.business.util.RSAUtil;

@RequestMapping("/sys")
@Controller
public class SysMgController {
	private static final Log logger = LogFactory.getLog(SysMgController.class);
	/**
	 * 店铺首页
	 * @param request
	 * @param value
	 * @return
	 */
	@RequestMapping("/init/{value}")
	public ModelAndView initIndex(HttpServletRequest request,@PathVariable String value){
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession();
	    try {
	    	if("".equals(value)){
	    		model.setViewName("error");
	    		return model;
	    	}
		    JedisPool pool = JedisPoolUtile.getPool();
		    Jedis jedis = pool.getResource();
		    String publicKeyString = jedis.srandmember("publicKey");
			String user = RSAUtil.publicEncrypt(value, publicKeyString);
			//List<String> rsmap = jedis.hmget(user, "name");
			Map<String, String> map = new HashMap<String, String>();
			Iterator<String> iter=jedis.hkeys(user).iterator();//获取缓存数据库中的数据
            while (iter.hasNext()){
                String key = iter.next();
                map.put(key, String.valueOf(jedis.hmget(user,key).get(0)));
                //System.out.println(key+":"+jedis.hmget("user",key));
            }
           if(!"1".equals(map.get("isAdmin"))){//不是店主
        	   model.setViewName("error");
	    		return model;
           } 
			if(map.size()>0){
				session.setAttribute("userName", map.get("userName"));
				session.setAttribute("userCode", map.get("userCode"));
				session.setAttribute("isAdmin", map.get("isAdmin"));//是否是管理员 即是买家也是卖家
			}
			model.setViewName("index");
		} catch (Exception e) {
			logger.error("========= 店铺首页异常=======",e);
		}
	    return model;
	}
}
