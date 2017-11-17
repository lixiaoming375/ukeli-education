<#include "copyright.ftl"/>

package ${spackage}.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.javaworker.yeming.core.util.Constant;
import cn.javaworker.yeming.core.util.Encrypt;
import cn.javaworker.yeming.core.web.CookiesUtil;
import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.manager.pojo.AdminDo;
import cn.javaworker.yeming.manager.service.AdminService;
import cn.javaworker.yeming.pojo.ErrorInfo;

<#include "version.ftl"/>

@Controller
public class CommonController extends BaseControllerImpl{
	
	private final static Logger LOGGER =LogManager.getLogger(CommonController.class);
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/login.html" ,method = RequestMethod.GET)
    public String login(ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String sign =RandomStringUtils.randomAlphanumeric(40);
		map.put("validatecode", sign);
		setting(map,request,response);
        return "login";
    }

    /**
     * 验证后台管理员登录
     * @param request
     * @param response
     * @return
     */
	@RequestMapping(value = "/login.json",method = RequestMethod.POST)
	@ResponseBody
	public Object doLogin(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ErrorInfo<AdminDo> info = new ErrorInfo<>();
        try {
            String username = RequestUtil.getString("username", request);
            String password = RequestUtil.getString("password", request);
            String vcode = RequestUtil.getString("vcode", request);
            int isrecomend =RequestUtil.getInt("isrecomend", request);

            LOGGER.info("后台管理员开始登陆:" + username );
            if(StringUtils.isBlank(username)){
                info.setErrorinfo("您输入的用户名或者昵称不能为空!");
                return info;
            }
            if(StringUtils.isBlank(password)){
                info.setErrorinfo("登录密码不能为空!");
                return info;
            }
            if(StringUtils.isBlank(vcode)){
                info.setErrorinfo("验证码不能为空!");
                return info;
            }
            String validateCode = CookiesUtil.getString3Des(Constant.VALIDATE_CODE, request);
            if(StringUtils.isBlank(validateCode) || !vcode.equalsIgnoreCase(validateCode)) {
                info.setErrorinfo("您输入的验证码不正确!");
                return info;
            }
            //开始验证
            AdminDo admin =adminService.getByItem("username",username);		 //adminService.//.getAdmin(username,1);
            if(null != admin) {
                String userpass= Encrypt.getPasswd(password);
                LOGGER.info("用户登录密码加密："+userpass);
                if(admin.getPassword().equals(userpass)) {
                	if(admin.getStatus() != 1){
                        info.setErrorinfo("对全部，管理员状态不在正确，请联系总管理员");
                        LOGGER.info("对不起，您的状态不正确！");
                        return info;
                	}
                	LOGGER.info("用户登录成功");
                    info.setObj(admin);
                    adminService.updateAdminLoginInfo(RequestUtil.getIpAddr(request),admin);
                    int times =0;
                    if(isrecomend==1) {
                    	times =Constant.ONE_DAY * 365;
                    }
                    CookiesUtil.setString3Des(Constant.ADMIN_COOKIES_ID, String.valueOf(admin.getId()),times,request, response);
                    CookiesUtil.setString3Des(Constant.ADMIN_COOKIES_NAME, username,times,request, response);
                    String realname =admin.getRealname();
                    if(null == realname){
                        realname = "";
                    }
                    CookiesUtil.setString3Des(Constant.ADMIN_COOKIES_REALNAME, realname,times, request, response);
                    info.setSuccess(true);
                    return info;
                }else{
                    info.setErrorinfo("您输入的用户名对应的密码不正确");
                    LOGGER.info("登陆对应的密码不正确");
                    return info;
                }
            }else {
                info.setErrorinfo("您输入用户名不存在");
                LOGGER.info("用户名不存在");
                return info;
            }
        } catch (Exception ex) {
            LOGGER.error("判断后台管理员登录发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
            info.setErrorinfo("后台管理员登录时间发生错误:"+ex.getMessage());
        }
        return info;
	}

	
	@RequestMapping("/index.html")
	public String index(ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//开始处理一些常用的数据
		setting(map,request,response);
		setItems(map, request, response);
		map.put("version", System.currentTimeMillis()+"");
		return "index";
	}
	
	
	@RequestMapping(value = "/logout.html" ,method = RequestMethod.GET)
    public void logout(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 CookiesUtil.setString3Des(Constant.ADMIN_COOKIES_ID, "0", request, response);
         CookiesUtil.setString3Des(Constant.ADMIN_COOKIES_NAME, "", request, response);
         CookiesUtil.setString3Des(Constant.ADMIN_COOKIES_REALNAME, "", request, response);
         response.sendRedirect("login.html");
    }
	
	/**
	 * 获取所有的按钮属性图标
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/icons.html" ,method = RequestMethod.GET)
    public String icons(ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String icon =RequestUtil.getString("icon", request);
		setting(map,request,response);
		map.put("icon", icon);
        return "icons";
    }
	
	
	/**
	 * 获取所有的按钮属性图标
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tree.html" ,method = RequestMethod.GET)
    public String tree(ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String path =RequestUtil.getString("path", request);
		int type =RequestUtil.getInt("type", request);
		String params = RequestUtil.getString("params", request);
		map.put("path", path);
		map.put("type", type);
		map.put("params", params);
		setting(map,request,response);
        return "tree";
    }
	
}