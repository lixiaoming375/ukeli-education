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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.javaworker.yeming.core.web.CookiesUtil;
import cn.javaworker.yeming.ukeli.UserUtil;

/**
 * 创建日期：2017年10月3日 下午8:53:37
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
@Controller
public class IndexConttoller extends BaseController {

	private final static Logger LOGGER = LogManager.getLogger(IndexConttoller.class);
	
	@RequestMapping(value= {"/","/index.html"},method=RequestMethod.GET)
	public String index(ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		LOGGER.info("系统进入首页:");
		
		setUser(map, request, response);
		setting(map, request, response);
		return "index";
	}


	@RequestMapping(value= {"/forbidden.html"},method=RequestMethod.GET)
	public  String forbidden(ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		CookiesUtil.setString(UserUtil.USER_COOKIESID, 0+"", request, response);
		CookiesUtil.setString(UserUtil.USER_COOKIES_NAME,"", request, response);
		CookiesUtil.setString(UserUtil.USER_COOKIES_REALNAME,"", request, response);
		setting(map, request, response);
		return "forbidden";
	}


	
	@RequestMapping(value= {"/404.html"},method=RequestMethod.GET)
	public  String error(ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		setting(map, request, response);
		return "error";
	}
}
