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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 创建日期：2017年10月12日 下午5:07:06
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
@RequestMapping("/user")
@Controller
public class UserController extends BaseController {
	

	@RequestMapping(value= "/index.html",method=RequestMethod.GET)
	public String index(ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		setUser(map, request, response);
		setting(map, request, response);
		return "user/index";
	}

	@RequestMapping(value= "/password.html",method=RequestMethod.GET)
	public String password(ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		setUser(map, request, response);
		setting(map, request, response);
		return "user/password";
	}
	

	@RequestMapping(value= "/vip.html",method=RequestMethod.GET)
	public String vip(ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		setUser(map, request, response);
		setting(map, request, response);
		return "user/vip";
	}
	
	
//	course
	
	
	@RequestMapping(value= "/course.html",method=RequestMethod.GET)
	public String course(ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		setUser(map, request, response);
		setting(map, request, response);
		return "user/course";
	}
}
