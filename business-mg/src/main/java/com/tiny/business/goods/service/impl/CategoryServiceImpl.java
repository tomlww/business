package com.tiny.business.goods.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.tiny.business.goods.dao.CategoryMapper;
import com.tiny.business.goods.model.CategoryModel;
import com.tiny.business.goods.service.CategoryService;
import com.tiny.business.sys.service.DictitemService;
import com.tiny.business.util.FtpUtil;
import com.tiny.business.util.StringUtil;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	private static final Log logger = LogFactory.getLog(CategoryServiceImpl.class);
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private DictitemService dictitemService;
	/**
	 * 添加商品分类
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> add(Map<String, Object> rmap,String categoryListString,HttpServletRequest request) throws Exception {
		logger.info("==== begin添加商品分类 ====");
		String suppliersId = "2";//店主微信号
		rmap = getDictitem(request);//获取上传的路径
		JSONObject jsonObj = JSONObject.parseObject(categoryListString);
		String strCategory = jsonObj.get("category").toString();
		List<CategoryModel>	 listCategory = JSONObject.parseArray(strCategory, CategoryModel.class);
		if(null == listCategory){
			rmap.put("status", "fail");//添加失败
			return rmap;
		}
		for(int i=0;i<listCategory.size();i++){
			List<String> listPaht = (List<String>) rmap.get("listPaht");
			CategoryModel categoryModel = listCategory.get(i);
			categoryModel.setSuppliersId(suppliersId);
			categoryModel.setImgUrl(listPaht.get(i));//图片路径
			//如果分类已经存在就update 否则就添加
			if(categoryModel.getCatId() != null && !"".equals(categoryModel.getCatId())){
				categoryMapper.updateData(categoryModel);
			}else{
				categoryModel.setCatId(StringUtil.getGuid());//主键
				int count = categoryMapper.insertData(categoryModel);//添加商品分类
				if(count<=0){
					rmap.put("status", "fail");//添加失败
					return rmap;
				}
			}
			
		}
		rmap.put("status", "success");
		logger.info("==== end添加商品分类 ====");
		return rmap;
	}
	
	private Map<String, Object> getDictitem(HttpServletRequest request){
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap = dictitemService.getFtpInfo();
		//rmap = FtpUtil.upload(rmap, request);//上传图片
		List<String> listPaht = new ArrayList<String>();
		try {
			//String suppliersId = "2";//从session中获取
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  //创建一个通用的多部分解析器  
			if(multipartResolver.isMultipart(request)){  //判断 request 是否有文件上传,即多部分请求  
			    MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  //转换成多部分request  
			    Iterator<String> iter = multiRequest.getFileNames();  //取得request中的所有文件名  
			    while(iter.hasNext()){  
			    MultipartFile file = multiRequest.getFile(iter.next());    //取得上传文件  
			    String path = FtpUtil.upload(file, multiRequest);
			    listPaht.add(path);
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
	}
	
	/**
	 * 获取商品分类列表
	 */
	@Override
	public List<CategoryModel> getCategoryList(HttpServletRequest request) throws Exception{
		//request.getSession().getAttribute(arg0) //获取微信公众号
		String suppliersId = "2";//店主微信号
		return categoryMapper.queryForList(suppliersId);
	}

	@Override
	public int delete(String catId) throws Exception{
		return categoryMapper.deleteData(catId);
	}
	
}
