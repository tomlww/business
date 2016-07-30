package com.tiny.business.wx.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.tiny.business.goods.controller.MgGoodsController;
import com.tiny.business.wx.api.process.ErrCode;
import com.tiny.business.wx.api.process.WxApiClient;
import com.tiny.business.wx.api.process.WxMemoryCacheClient;
import com.tiny.business.wx.api.process.WxSign;
import com.tiny.business.wx.api.util.SignUtil;
import com.tiny.business.wx.model.MpAccount;
import com.tiny.business.wx.service.WXService;

@Controller
@RequestMapping("/wxapi")
public class WXController {
	private static final Log logger = LogFactory.getLog(MgGoodsController.class);
	
	@Autowired
	private WXService wXService;
	/**
	 * GET请求：进行URL、Tocken 认证；
	 * 1. 将token、timestamp、nonce三个参数进行字典序排序
	 * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
	 * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	 */
	/*@RequestMapping(value = "/{account}/message",  method = RequestMethod.GET)
	public @ResponseBody String doGet(HttpServletRequest request,@PathVariable String account) {
		//如果是多账号，根据url中的account参数获取对应的MpAccount处理即可
		MpAccount mpAccount = WxMemoryCacheClient.getSingleMpAccount();//获取缓存中的唯一账号
		if(mpAccount != null){
			String token = mpAccount.getToken();//获取token，进行验证；
			String signature = request.getParameter("signature");// 微信加密签名
			String timestamp = request.getParameter("timestamp");// 时间戳
			String nonce = request.getParameter("nonce");// 随机数
			String echostr = request.getParameter("echostr");// 随机字符串
			
			// 校验成功返回  echostr，成功成为开发者；否则返回error，接入失败
			if (SignUtil.validSign(signature, token, timestamp, nonce)) {
				return echostr;
			}
		}
		return "error";
	}*/
	
	/**
	 * 微信接入验证接口
	 * GET请求：进行URL、Tocken 认证；
	 * 1. 将token、timestamp、nonce三个参数进行字典序排序
	 * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
	 * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	 */
	@RequestMapping(value = "/validate",  method = RequestMethod.GET)
	public @ResponseBody String validate(HttpServletRequest request,HttpServletResponse response) {
			String signature = request.getParameter("signature");// 微信加密签名
			String timestamp = request.getParameter("timestamp");// 时间戳
			String nonce = request.getParameter("nonce");// 随机数
			String echostr = request.getParameter("echostr");// 随机字符串
			String token = "test123";
			// 校验成功返回  echostr，成功成为开发者；否则返回error，接入失败
			if (SignUtil.validSign(signature, token, timestamp, nonce)) {
				return echostr;
			}
		return "error";
	}
	
	//创建微信公众账号菜单
	@RequestMapping(value = "/publishMenu")
	public ModelAndView publishMenu(HttpServletRequest request,String gid) {
		JSONObject rstObj = null;
		MpAccount mpAccount = WxMemoryCacheClient.getSingleMpAccount();
		if(mpAccount != null){
			rstObj = wXService.publishMenu(gid,mpAccount);
			if(rstObj != null){//成功，更新菜单组
				if(rstObj.containsKey("menu_id")){
					ModelAndView mv = new ModelAndView("common/success");
					mv.addObject("successMsg", "创建菜单成功");
					return mv;
				}else if(rstObj.containsKey("errcode") && rstObj.getInteger("errcode") == 0){
					ModelAndView mv = new ModelAndView("common/success");
					mv.addObject("successMsg", "创建菜单成功");
					return mv;
				}
			}
		}
		
		ModelAndView mv = new ModelAndView("common/failure");
		String failureMsg = "创建菜单失败，请检查菜单：可创建最多3个一级菜单，每个一级菜单下可创建最多5个二级菜单。";
		if(rstObj != null){
			failureMsg += ErrCode.errMsg(rstObj.getInteger("errcode"));
		}
		mv.addObject("failureMsg", failureMsg);
		return mv;
	}
	
	@RequestMapping("/wxUploadImg/{images}")
	public void wxUploadImg(@PathVariable String images,HttpServletRequest request){
		String account = "845979924@qq.com";
		MpAccount mpaccount =	WxMemoryCacheClient.getMpAccount(account);
		String acc_token = WxApiClient.getAccessToken(mpaccount);
		System.out.println(images);
		JSONObject json =  JSONObject.parseObject(images);
		List<String> listMediaId = JSONObject.parseArray(json.getString("serverId"), String.class);
		 InputStream is = null;
		try {
			for(String s:listMediaId){
				String action = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+acc_token+"&media_id="+s;
				logger.info("===================action===================="+action);
				   URL url = new URL(action);
			      // String result = null;
			       HttpURLConnection http = (HttpURLConnection) url
		                   .openConnection();
		           http.setRequestMethod("GET"); // 必须是get方式请求
		           http.setRequestProperty("Content-Type",
		                   "application/x-www-form-urlencoded");
		           http.setDoOutput(true);
		           http.setDoInput(true);
		           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
		           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
		           http.connect();
		           // 获取文件转化为byte流
		           is = http.getInputStream();
		           byte[] data = new byte[1024];
		           int len = 0;
		           FileOutputStream fileOutputStream = null;
		           String extension ="jpg";
		           // 保存文件到本地
					String fileName = String.valueOf(new Date().getTime())+ "." +extension;
					String realPath ="/WebContent/updload/";
					 String imageUploadPath="D:/svnWork/business/"+request.getContextPath()+realPath+"\\";
					//String imageUploadPath = "D:\\svnWork\\image\\";
					File localFile = new File(imageUploadPath, fileName);
		           try {
		               fileOutputStream = new FileOutputStream(localFile);
		               while ((len = is.read(data)) != -1) {
		                   fileOutputStream.write(data, 0, len);
		               }
		               System.out.println(is.available()); 
		           } catch (IOException e) {
		               e.printStackTrace();
		           } finally {
		               if (is != null) {
		                   try {
		                	   is.close();
		                   } catch (IOException e) {
		                       e.printStackTrace();
		                   }
		               }
		               if (fileOutputStream != null) {
		                   try {
		                       fileOutputStream.close();
		                   } catch (IOException e) {
		                       e.printStackTrace();
		                   }
		               }
		           }
		         }
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) throws Exception{
		String filePath = "D:/wx";
	       File file = new File(filePath);

	       if (!file.exists()) {

	           throw new IOException("上传的文件不存在");

	       }
	}
	/**
	 * 测试微信图片上传
	 * @return
	 */
	@RequestMapping("/testWxImage")
	public ModelAndView testWxImamge(){
		ModelAndView model = new ModelAndView("testImg");
		String account = "845979924@qq.com";
		MpAccount mpaccount =	WxMemoryCacheClient.getMpAccount(account);
		model.addObject("myaccount", mpaccount);
		String jsTicket = WxApiClient.getJSTicket(mpaccount);
		String url = "http://1497530jn5.iask.in/business-mg/wxapi/testWxImage";
		WxSign wxSign = new WxSign(mpaccount.getAppid(), jsTicket, url);//微信签名
		model.addObject("wxSign", wxSign);//签名时的时间
		return model;
	}
    
    
}
