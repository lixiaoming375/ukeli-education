/**
* <p> * Title: 优课力信息管理系统*</p>
* <p> * Description: 优课力信息管理系统 * </p>
* <p> * Copyright: Copyright (c) 2012-2018* </p>
* <p> * Company:苏州明翔信息科技有限公司 * </p>
* @author 叶明（开发）
* @version 0.1
*/
package cn.javaworker.yeming.ukeli.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;

import cn.javaworker.yeming.core.web.CookiesUtil;
import cn.javaworker.yeming.ukeli.UserUtil;
import cn.javaworker.yeming.ukeli.pojo.UserDo;
import cn.javaworker.yeming.ukeli.service.UserService;

/**
 * 创建日期：2017年10月3日 下午8:43:21
 * 开发者：叶明(MSN:guming123416@hotmail.com,QQ:47043760)
 * 修改者：
 * 修改时间：
 * 程序作用：
 * 1、
 * 2、
 * 修改说明：
 * 1、
 * 2、
 * 版本：@version 0.1
 * @author 叶明
 */
public class BaseController {
	
	@Value("${system.web.path}")
	protected String webPath;				//网站框架
	@Value("${system.web.version}")
	protected String version;				//系统资源版本
	@Value("${system.isdebug}")
	protected int isDebug;				//是否是debug 0 是  1 不是
	@Value("${system.web.resurl}")
	/**
	 * 用户资源
	 */
	protected String resWebUrl;
	@Value("${system.web.name}")
	protected String productName;		//产品名称
	@Value("${system.image.path}")		
	protected String imagePath;			//本地文件路径
	@Value("${system.images.url}")	
	protected String imageurl;			//图片访问url
	
	@Autowired
	private UserService userService;
	
	protected void setting(ModelMap map,HttpServletRequest request,HttpServletResponse response) {
		map.put("version", version);
		map.put("webPath", webPath);
		map.put("productName", productName);
		map.put("resWebUrl", resWebUrl);
		map.put("imagePath", imagePath);
		map.put("imageurl", imageurl);
		if(isDebug ==0){
			map.put("version",System.currentTimeMillis()+"");
		}
	}
	
	/**
	 * 设置用户信息
	 * @param map
	 * @param request
	 * @param response
	 */
	protected void setUser(ModelMap map,HttpServletRequest request,HttpServletResponse response) {
		UserDo userDo = null;
		long userid =0;
		try {
			userid =CookiesUtil.getLong3Des(UserUtil.USER_COOKIESID, request);
		} catch (Exception ex) {}
		if (userid > 0) {
			userDo =userService.get(userid);
		}
		map.put("userDo", userDo);
		map.put("userid", userid);

	}

}
