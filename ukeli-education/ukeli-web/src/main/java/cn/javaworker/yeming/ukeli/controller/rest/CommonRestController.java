/**
* <p> * Title: 优课力信息管理系统*</p>
* <p> * Description: 优课力信息管理系统 * </p>
* <p> * Copyright: Copyright (c) 2012-2018* </p>
* <p> * Company:苏州明翔信息科技有限公司 * </p>
* @author 叶明（开发）
* @version 0.1
*/
package cn.javaworker.yeming.ukeli.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.javaworker.yeming.core.util.Encrypt;
import cn.javaworker.yeming.core.util.StrUtils;
import cn.javaworker.yeming.core.web.CookiesUtil;
import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.pojo.ErrorInfo;
import cn.javaworker.yeming.ukeli.UserUtil;
import cn.javaworker.yeming.ukeli.pojo.UserDo;
import cn.javaworker.yeming.ukeli.service.UserService;

/**
 * 创建日期：2017年10月10日 下午4:34:40
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
@RestController
public class CommonRestController {
	
	private final static Logger LOGGER = LogManager.getLogger(CommonRestController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	
	@RequestMapping(value="/login.json",method =RequestMethod.POST)
	public ErrorInfo<UserDo> login(HttpServletRequest request,HttpServletResponse response){
		ErrorInfo<UserDo> errorInfo =new ErrorInfo<>();
		try {
			String username =RequestUtil.getString("username", request);
			String password =RequestUtil.getString("password", request);
			LOGGER.info("用户登录:username="+username+",password="+password);
			if(StrUtils.isBlank(username)) {
				errorInfo.setErrorinfo("对不起，登录用户名不能为空！");
				return errorInfo;
			}
			if(StrUtils.isBlank(password)) {
				errorInfo.setErrorinfo("对不起，登录密码不能为空！");
				return errorInfo;
			}
			
			UserDo userDo =userService.getByItem("username", username);
			if(null == userDo) {
				errorInfo.setErrorinfo("对不起，您输入的用户名不正确");
				return errorInfo;
			}
			String pass =Encrypt.getPasswd(password);
			String userpass = userDo.getPassword();
			LOGGER.info("pass="+pass);
			if(!pass.equalsIgnoreCase(userpass)) {
				errorInfo.setErrorinfo("对不起，您输入的密码不正确");
				return errorInfo;
			}
			
			if (userDo.getStatus() !=1) {
				errorInfo.setErrorinfo("对不起，您的状态不正确");
				return errorInfo;
			}
			
//			int times = 60 * 60 * 1000;
			//记录用户登录的Cookies
			LOGGER.info("userDo="+userDo);
			CookiesUtil.setString3Des(UserUtil.USER_COOKIESID, userDo.getId() +"", request, response);
			CookiesUtil.setString3Des(UserUtil.USER_COOKIES_NAME, userDo.getUsername(), request, response);
			CookiesUtil.setString3Des(UserUtil.USER_COOKIES_REALNAME, userDo.getNickname(), request, response);
			//增加当前的
			HttpSession session = request.getSession();
			String sessionid = session.getId();
			//讲用户的登录信息保存到缓存中
			stringRedisTemplate.opsForValue().set(UserUtil.user_session_key + userDo.getId(), sessionid);
			errorInfo.setSuccess(true);
		} catch (Exception ex) {
			LOGGER.error("用户登录时间发生错误:"+ex.getMessage());
			LOGGER.debug(ex);
			errorInfo.setErrorinfo("对不起，系统错误！");
		}
		return errorInfo;
	}

}
