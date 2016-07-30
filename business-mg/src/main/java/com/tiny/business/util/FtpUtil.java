package com.tiny.business.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 实现FTP 客户端的各种操作
 * 
 * 其实JDK里面也有支持FTP操作的包【jre/lib下的rt.jar】，但是SUN的DOC里面并没有提供相应文档，
 * 因为这里面的包，不被官方支持，建议不要使用。我们可以使用第三方提供的包apache.commons。 apache.commons的包，都有文档，方便使用
 * 另外IBM也有提供一个ftp包，我没有用过，有兴趣的可以去研究一下
 * 
 * @commons-net：http://apache.mirror.phpchina.com/commons/net/binaries/commons-net-1.4.1.zip
 * @jakarta-oro：http://mirror.vmmatrix.net/apache/jakarta/oro/source/jakarta-oro-2.0.8.zip
 * @commons-io：http://apache.mirror.phpchina.com/commons/io/binaries/commons-io-1.3.2-bin.zip
 * 
 * @author
 * @version 2008-06-10 Ftp.java
 * 
 */
public class FtpUtil {
	private static Log logger = LogFactory.getLog(FtpUtil.class);;

	/**
	 * FTP 登录用户名
	 */
	private static String UserName;

	/**
	 * FTP 登录密码
	 */
	private static String Password;

	/**
	 * FTP 服务器地址IP地址
	 */
	private static String Ip;

	/**
	 * FTP 端口
	 */
	private static int Port;

	/**
	 * 属性集
	 */
	private static Property Property = null;

	/**
	 * 配置文件的路径名
	 */
	private static String ConfigFile = "serverImg.properties";

	/**
	 * FTP 客户端代理
	 */
	private static FTPClient FtpClient = null;

	/**
	 * 传输模式为二进制文件.
	 */
	public static final int BINARY_FILE_TYPE = FTP.BINARY_FILE_TYPE;

	/**
	 * 传输模式为ASCII，默认为ASCII
	 */
	public static final int ASCII_FILE_TYPE = FTP.ASCII_FILE_TYPE;
	/**
	 * spring mvc上传
	 * @param in
	 * @param path
	 * @return
	 */
	public static boolean upload(InputStream in,String path,Map map){
			boolean flag = true;
			try {
			logger.info("----------  图片上传： 即将连接到服务ing ------- ");
			System.out.println("----------  图片上传： 即将连接到服务ing ------- ");
			newConnectServer(map);
			FtpClient.setFileType(BINARY_FILE_TYPE);
			FtpClient.enterLocalPassiveMode();
			FtpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
			File newFile = new File(path);
			String dir = "";
			if (newFile.getParentFile() != null) {
				dir = newFile.getParentFile().getPath();
			}
			if (!FtpClient.changeWorkingDirectory(dir)) {// 如果不能进入dir下，说明此目录不存在！
				if (!makeDirectory(newFile.getParentFile().getPath())) {
					System.out.println("创建文件目录【" + dir + "】 失败！");
				}
			}
			// 回到FTP根目录
			changeWorkingDirectory("/");
			if(Ip.equals("10.56.1.13")){ //生产地址
				path = "/fileserver/"+path;
			}
			flag = FtpClient.storeFile(new String(path.getBytes("UTF-8"),"iso-8859-1"), in);
			if (flag) {
				System.out.println("upload File succeed");
			} else {
				System.out.println("upload File false");
			}
			in.close();
			logger.info("----------  图片上传： 上传图片服务器结束 ------- ");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("本地文件上传失败！", e);
		}
			finally { 
	          try { 
	        	  if(null != FtpClient && FtpClient.isConnected()){
		        	  FtpClient.disconnect();
	        	  }
	          } catch (IOException e) { 
	              e.printStackTrace(); 
	              throw new RuntimeException("关闭FTP连接发生异常！" , e); 
	          } 
	      } 
		return flag;
	}

	private static boolean newConnectServer(Map map) {
		boolean flag = true;
		System.out.println("----------  图片上传： connectServer() -------");
		if (FtpClient == null) {
			int reply;
			try {
				newSetArg(map);
				FtpClient = new FTPClient();
				FtpClient.setControlEncoding("UTF-8");
				FtpClient.setDefaultPort(Port);
				FtpClient.configure(getFtpConfig());
				FtpClient.connect(Ip);
				FtpClient.login(UserName, Password);
				reply = FtpClient.getReplyCode();
				FtpClient.setDataTimeout(120000);
				if (!FTPReply.isPositiveCompletion(reply)) {
					FtpClient.disconnect();
					System.err.println("FTP server refused connection.");
					flag = false;
				}
			} catch (SocketException e) {
				flag = false;
				e.printStackTrace();
				System.err.println("登录ftp服务器【" + Ip + "】失败,连接超时！");
			} catch (IOException e) {
				flag = false;
				e.printStackTrace();
				System.err.println("登录ftp服务器【" + Ip + "】失败，FTP服务器无法打开！");
			}

		}else{
			System.out.println("----------  图片上传： 检测FTP是否连通 isConnected ? ------- ");
			if(!FtpClient.isConnected())
			{
				System.out.println("----------  图片上传： 进入isConnected ------- ");
				try {
					FtpClient.connect(Ip);
					System.out.println("----------  图片上传： 完成FtpClient.connect(Ip); ------- ");
					FtpClient.login(UserName, Password);
					FtpClient.setDefaultPort(Port);
				}catch(Exception e){
					flag = false;
				}
			}
		}
		System.out.println("----------  图片上传： 检测FTP是否连通 isConnected ? ------- "+flag);
		return flag;
	}
	private static void newSetArg(Map map) {
		try {
			UserName = (String) map.get("CIFTPUSERNAME");
			Password = (String) map.get("CIFTPPASSWORD");
			Ip = (String) map.get("CIFTPHOST");
			Port = Integer.parseInt((String) map.get("CIFTPPORT"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	/**
	 * 关闭连接
	 */
	public static void closeConnect() {
		try {
			if (FtpClient != null) {
				FtpClient.logout();
				FtpClient.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置配置文件
	 * 
	 * @param configFile
	 */
	public static void setConfigFile(String configFile) {
		FtpUtil.ConfigFile = configFile;
	}

	/**
	 * 设置传输文件的类型[文本文件或者二进制文件]
	 * 
	 * @param fileType--BINARY_FILE_TYPE、ASCII_FILE_TYPE
	 */
	public static void setFileType(int fileType) {
		try {
			connectServer();
			FtpClient.setFileType(fileType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 扩展使用
	 * 
	 * @return
	 */
	public static FTPClient getFtpClient() {
		connectServer();
		return FtpClient;
	}

	/**
	 * 设置参数
	 * 
	 * @param configFile
	 *            --参数的配置文件
	 */
	private static void setArg(String configFile) {
		Property = new Property(configFile);
		try {
			UserName = Property.getValue("ciftpUserName");
			Password = Property.getValue("ciftpPassword");
			Ip = Property.getValue("ciftpHost");
			Port = Integer.parseInt(Property.getValue("ciftpPort"));
		} catch (Exception e1) {
			System.out.println("配置文件 【" + configFile + "】不存在！");
			e1.printStackTrace();
		}
	}

	/**
	 * 连接到服务器
	 * 
	 * @return true 连接服务器成功，false 连接服务器失败
	 */
	public static boolean connectServer() {
		boolean flag = true;
		System.out.println("----------  图片上传： connectServer() -------");
		System.out.print("FtpClient==null :");
		System.out.println(FtpClient==null);
		System.out.print("FtpClient :");
		System.out.println(FtpClient);
		if (FtpClient == null) {
			int reply;
			try {
				setArg(ConfigFile);
				FtpClient = new FTPClient();
				FtpClient.setControlEncoding("GBK");
				FtpClient.setDefaultPort(Port);
				FtpClient.configure(getFtpConfig());
				FtpClient.connect(Ip);
				FtpClient.login(UserName, Password);
				FtpClient.setDefaultPort(Port);
				reply = FtpClient.getReplyCode();
				FtpClient.setDataTimeout(120000);
				if (!FTPReply.isPositiveCompletion(reply)) {
					FtpClient.disconnect();
					System.err.println("FTP server refused connection.");
					flag = false;
				}
			} catch (SocketException e) {
				flag = false;
				e.printStackTrace();
				System.err.println("登录ftp服务器【" + Ip + "】失败,连接超时！");
			} catch (IOException e) {
				flag = false;
				e.printStackTrace();
				System.err.println("登录ftp服务器【" + Ip + "】失败，FTP服务器无法打开！");
			}
		}else{
			System.out.println("----------  图片上传： 检测FTP是否连通 isConnected ? ------- ");
			if(!FtpClient.isConnected())
			{
				System.out.println("----------  图片上传： 进入isConnected ------- ");
				try {
					FtpClient.connect(Ip);
					System.out.println("----------  图片上传： 完成FtpClient.connect(Ip); ------- ");
					FtpClient.login(UserName, Password);
					FtpClient.setDefaultPort(Port);
				}catch(Exception e){
					flag = false;
				}
			}
		}
		System.out.println("----------  图片上传： 检测FTP是否连通 isConnected ? ------- "+flag);
		return flag;
	}

	/**
	 * 进入到服务器的某个目录下
	 * 
	 * @param directory
	 */
	public static void changeWorkingDirectory(String directory) {
		try {
			connectServer();
			FtpClient.changeWorkingDirectory(directory);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}


	/**
	 * 重命名文件
	 * 
	 * @param oldFileName
	 *            --原文件名
	 * @param newFileName
	 *            --新文件名
	 */
	public static void renameFile(String oldFileName, String newFileName) {
		try {
			connectServer();
			FtpClient.rename(oldFileName, newFileName);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * 设置FTP客服端的配置--一般可以不设置
	 * 
	 * @return
	 */
	private static FTPClientConfig getFtpConfig() {
		FTPClientConfig ftpConfig = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
		ftpConfig.setServerLanguageCode(FTP.DEFAULT_CONTROL_ENCODING);
		return ftpConfig;
	}

	/**
	 * 转码[ISO-8859-1 -> GBK] 不同的平台需要不同的转码
	 * 
	 * @param obj
	 * @return
	 */
	public static String iso8859togbk(Object obj) {
		try {
			if (obj == null)
				return "";
			else
				return new String(obj.toString().getBytes("iso-8859-1"), "GBK");
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 在服务器上创建一个文件夹
	 * 
	 * @param dir
	 *            文件夹名称，不能含有特殊字符，如 \ 、/ 、: 、* 、?、 "、 <、>...
	 */
	public static boolean makeDirectory(String dir) {
		connectServer();
		boolean flag = true;
		try {
			String[] paths = dir.split("\\\\");
			for (String path : paths) {
				if (!FtpClient.changeWorkingDirectory(path)) {
					FtpClient.makeDirectory(path);
					if (!FtpClient.changeWorkingDirectory(path))
						break;
				}
			}
			if (flag) {
				System.out.println("make Directory " + dir + " succeed");
			} else {
				System.out.println("make Directory " + dir + " false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/*public static Map<String, Object> upload(Map<String, Object> rmap,HttpServletRequest request){
		List<String> listPaht = new ArrayList<String>();
		try {
			String suppliersId = "2";//从session中获取
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  //创建一个通用的多部分解析器  
			if(multipartResolver.isMultipart(request)){  //判断 request 是否有文件上传,即多部分请求  
			    MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  //转换成多部分request  
			    Iterator<String> iter = multiRequest.getFileNames();  //取得request中的所有文件名  
			   
			    while(iter.hasNext()){  
			    MultipartFile file = multiRequest.getFile(iter.next());    //取得上传文件  
			    String extension = FilenameUtils.getExtension(file
						.getOriginalFilename());
			    // 保存文件到本地
				String fileName = String.valueOf(new Date().getTime())+ "." +extension;
				String realPath ="/upload/";
				 String imageUploadPath=request.getSession().getServletContext().getRealPath(realPath)+"\\";
				//String imageUploadPath = "D:\\svnWork\\image\\";
				File localFile = new File(imageUploadPath, fileName);
				if (localFile != null) {
					OutputStream os = FileUtils.openOutputStream(localFile);
					os.write(file.getBytes());
					IOUtils.closeQuietly(os);
				}
				String attPath = realPath+fileName;
			    listPaht.add(attPath);//获取上传的路径
			    if(file != null){  
			    	rmap = addUploadInfo(rmap, suppliersId, "Category", file);//上传文件
			    	listPaht.add((String)rmap.get("attPath"));//获取上传的路径
				}else{
					rmap.put("status", false);
					rmap.put("msg", "上传失败");
					return  rmap;
				}
			  }
			}
		} catch (Exception e) {
			logger.error("==== CommonUploadController 上传异常===",e);
			rmap.put("msg", "上传失败");
			rmap.put("status", false);
			return  rmap;
		}  
		rmap.put("listPaht", listPaht);
		return rmap;
	}*/
	public static String upload(MultipartFile file,HttpServletRequest request){
		System.out.println("===上传图片的大小===size==="+file.getSize());
		  String extension = FilenameUtils.getExtension(file
					.getOriginalFilename());
		  if(null == extension || "".equals(extension)){
			  extension = "jpg";
		  }
		    // 保存文件到本地
			String fileName = String.valueOf(new Date().getTime())+ "." +extension;
			String realPath ="/upload/";
			 String imageUploadPath=request.getSession().getServletContext().getRealPath(realPath)+"\\";
			//String imageUploadPath = "D:\\svnWork\\image\\";
			File localFile = new File(imageUploadPath, fileName);
			try {
				if (localFile != null) {
					OutputStream os = FileUtils.openOutputStream(localFile);
					os.write(file.getBytes());
					IOUtils.closeQuietly(os);
				}
			} catch (IOException e) {
				logger.error("========上传图片异常=====upload===",e);
			}
			String attPath = realPath+fileName;
			return attPath;
	}
	
	private static Map<String, Object> addUploadInfo(Map<String, Object>  ftpMap, String userCode,
			String orgCode, MultipartFile file)
			throws IOException, Exception {
		Map<String, Object> resMap = new HashMap<String, Object>();
		 String myFileName = file.getOriginalFilename(); //取得当前上传文件的文件名称  
		String type = myFileName.substring(myFileName.lastIndexOf('.'), myFileName.length());
		String [] noTypes = {".exe",".bat",".sys",".com"};
		List<String> stooges = Arrays.asList(noTypes);
		if(type!=null && stooges.contains(type.toLowerCase())){
			resMap.put("msg", "非法文件");
			return resMap;
		}
		InputStream in = file.getInputStream();
		if(in.available()>5120000){
			resMap.put("msg", "上传文件不能大于5Mb");
			return  resMap;
		}
		String guid = UUID.randomUUID().toString();
		String hostPath = (String) ftpMap.get("CIFTPPATH");//图片地址
		String path = "";
		if("http://vas.baosaas.com/pic/".equals(hostPath)){
			path = "ttp/"+orgCode+"/"+guid+type; //图片服务器地址
		}else{
			path = "/pic/ttp/"+orgCode+"/"+guid+type; //图片服务器地址
		}
		boolean bl = FtpUtil.upload(file.getInputStream(),path,ftpMap); //上传到图片服务器
		if(!bl){
			resMap.put("status", false);
			resMap.put("msg", "上传失败");
			return  resMap;
		}
		resMap.put("status", true);
		resMap.put("attPath", path); //存储路径
		return resMap;
	}

	public static Log getLogger() {
		return logger;
	}

	public static void setLogger(Log logger) {
		FtpUtil.logger = logger;
	}
	
}