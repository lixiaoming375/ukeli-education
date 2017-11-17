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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.javaworker.yeming.manager.controller.BaseControllerImpl;

/**
 * 创建日期：2017年8月21日 下午10:27:31
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
public class UkeliController  extends BaseControllerImpl{

	@Value("${system.manager.path}")
	private String adminPath;
	
	@RequestMapping(value = "/index.html")
	public String index(ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//开始处理一些常用的数据
		setting(map,request,response);
		setItems(map, request, response);
		return "index";
	}
	
	
	@RequestMapping(value = "/")
	public void vindex(ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.sendRedirect(adminPath + "/login.html");
	}

}
