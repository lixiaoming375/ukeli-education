/**
* <p> * Title: 商城管理信息系统 *</p>
* <p> * Description: * </p>
* <p> * Copyright: Copyright (c) 2012-2017* </p>
* <p> * Company: 苏州明翔信息科技有限公司 * </p>
* @author 叶明（开发）
* @version 0.1
*/
package cn.javaworker.yeming.ukeli.controller.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.javaworker.yeming.core.base.controller.RestBaseController;
import cn.javaworker.yeming.core.util.Constant;
import cn.javaworker.yeming.core.util.Encrypt;
import cn.javaworker.yeming.core.util.StrUtils;
import cn.javaworker.yeming.core.web.CookiesUtil;
import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.ukeli.service.UserService;
import cn.javaworker.yeming.ukeli.pojo.UserDo;
import cn.javaworker.yeming.pojo.ErrorInfo;
import cn.javaworker.yeming.pojo.Page;

/**
* 创建日期：2017-09-12 14:28:25
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
@RequestMapping("/user/")
public class UserRestController  implements RestBaseController<UserDo> {

	private final static Logger LOGGER =LogManager.getLogger(UserRestController.class);
	@Autowired
	private UserService userService;
	
    
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#del(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="del.json",method =RequestMethod.POST)
	public ErrorInfo<UserDo> del(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<UserDo> errorInfo = new ErrorInfo<>();
        try{
            LOGGER.info("需要删除的用户信息id=" + ids);
            String[] str = StringUtils.split(ids, ",");
            userService.delByIds(str);
            errorInfo.setSuccess(true);
            errorInfo.setErrorinfo("删除成功");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，删除用户信息失败，原因:"+ex.getMessage());
            LOGGER.error("删除一个用户信息发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#list(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="list.json",method =RequestMethod.POST)
	public Page<UserDo> list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<UserDo> page = null;
        try{
        	 long start = RequestUtil.getLong("start", request);
             int limit = RequestUtil.getInt("limit", request);
             int pageVal=RequestUtil.getInt("page", request);
             if(limit < Constant.SMALL_PAGE_SIZE){
             	limit = Constant.SMALL_PAGE_SIZE;
             }
             if(start < 0){
             	start =0;
             }else{
             	start =(pageVal-1)*limit;
             }
            short status =RequestUtil.getShort("status", request);
            short slock =RequestUtil.getShort("slock",(short)-1, request);
            String username = RequestUtil.getString("username",request);
            String nickname = RequestUtil.getString("nickname",request);
            String reservationtime = RequestUtil.getString("reservationtime",request);
            String isvip = RequestUtil.getString("isvip",request);
            Date startTime= null;
            Date endTime=null;
            if(StringUtils.isNoneBlank(reservationtime)){
            	 DateFormat  df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	 startTime=df.parse(reservationtime.split("-")[0].replace("/", "-"));
            	 endTime=df.parse(reservationtime.split("-")[1].replace("/", "-"));
            }
            Map<String, Object> kaywordMap=new HashMap<String,Object>();
            kaywordMap.put("username",username);
            kaywordMap.put("nickname",nickname);
            kaywordMap.put("isvip",isvip);
            kaywordMap.put("startTime",startTime);
            kaywordMap.put("endTime",endTime);
            LOGGER.info("获取用户信息status=" + status + ",username="+username + ",nickname="+nickname + ",isvip="+isvip + ",startTime="+startTime + ",start=" + start +",limit=" + limit);
            page = userService.getPage(kaywordMap,status,slock,start,limit);
            if(null != page){
            }
        }catch (Exception ex){
            LOGGER.error("获取用户信息分页发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return page;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#save(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="save.json",method =RequestMethod.POST)
	public ErrorInfo<UserDo> save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<UserDo> errorInfo = new ErrorInfo<>();
        try{
            long id =RequestUtil.getLong("id", request);
            /*
            String username = RequestUtil.getString("username", request);
            if(StrUtils.isBlank(username)) {
            	errorInfo.setSuccess(false);
            	errorInfo.setIreturn(900);
            	errorInfo.setErrorinfo("对不起，用户信息登录用户名不能为空");
            	return errorInfo;
            }
            if(adminService.check(username,id,1)) {
            	errorInfo.setSuccess(false);
            	errorInfo.setIreturn(900);
            	errorInfo.setErrorinfo("对不起，您输入的登录用户名已经存在");
            	return errorInfo;
            }
            */
            long adminid =CookiesUtil.getLong3Des(Constant.ADMIN_COOKIES_ID, request);
            String adminName =CookiesUtil.getString3Des(Constant.ADMIN_COOKIES_NAME, request);
            if(id > 0) {
            	Map<String, Object> map= RequestUtil.getParams(request);
            	map.put("sex",map.get("sex")==null?"2":"1");
            	if(null==map.get("vipstarttime")||map.get("vipstarttime").toString().isEmpty()){
            		map.remove("vipstarttime");
            	}
            	if(null==map.get("vipendtime")||map.get("vipendtime").toString().isEmpty()){
            		map.remove("vipendtime");
            	}
            	map.put("editer", adminName);
            	map.put("editerid", adminid);
            	userService.update(map, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("恭喜您，更新成功");
            }else {
            	UserDo userDo = RequestUtil.form(UserDo.class,request);
            	if(null !=userDo) {
            		userDo.setAdderid(adminid);
            		userDo.setAdder(adminName);
            		if(null==userDo.getSex()||userDo.getSex().toString().isEmpty()){
                		userDo.setSex(2+"");
            		}
            		if(null==userDo.getIsvip()||userDo.getIsvip().toString().isEmpty()){
                		userDo.setIsvip(1+"");
            		}
            		UserDo user=userService.getByItem("username", userDo.getUsername().trim());
            		if(null!=user){
            			errorInfo.setSuccess(false);
                    	errorInfo.setErrorinfo("对不起，该用户名已存在");
            		}else{
            			userService.save(userDo);
                        errorInfo.setSuccess(true);
                        errorInfo.setErrorinfo("恭喜您，保存用户信息成功");
            		}
            		
            	}else {
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，参数错误，请检查");
            	}
            }
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，新增一个用户信息信息失败，原因:"+ex.getMessage());
            LOGGER.error("新增一个用户信息信息发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#status(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="status.json",method =RequestMethod.POST)
	public ErrorInfo<UserDo> status(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<UserDo> errorInfo = new ErrorInfo<>();
        try{
        	long id =RequestUtil.getLong("id", request);
        	int status =RequestUtil.getInt("status", request);
        	int type =RequestUtil.getInt("type", request);
            if (id < 1 ||(status!=0 && status !=1)) {
            	errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
			}
            if (type==0) {
            	userService.updateByItem("status", status, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("更新状态成功");
			}else {
				userService.updateByItem("auditstatus", status, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("更改审核状态成功");
			}
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，更新用户信息状态失败，原因:"+ex.getMessage());
            LOGGER.error("更新用户信息状态失败发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	
	@RequestMapping(value="order.json",method =RequestMethod.POST)
	@ResponseBody
	public ErrorInfo<UserDo> order(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<UserDo> errorInfo = new ErrorInfo<>();
        try{
        	long id =RequestUtil.getLong("id", request);
        	int type =RequestUtil.getInt("type", request);
        	if (id < 1 || type < 0) {
            	errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
			}
        	UserDo userDo = userService.get(id);
        	if(null ==userDo){
        		errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
        	}
        	UserDo vuserDo = userService.getByOrderType(userDo.getIorder(),type);
        	if(null == vuserDo){
        		if(type ==1){
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最顶层了");
        		}else{
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最底层了");
        		}
        		return errorInfo;
        	}
        	if(null != userDo && null != vuserDo){
        		userService.order(userDo,vuserDo);
        	}
        	errorInfo.setSuccess(true);
        	errorInfo.setErrorinfo("排序成功!");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，更新用户信息排序失败，原因:"+ex.getMessage());
            LOGGER.error("更新用户信息排序失败发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	
	
	@RequestMapping(value="editpw.json",method =RequestMethod.POST)
	@ResponseBody
	public ErrorInfo<UserDo> doPass(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<UserDo> errorInfo = new ErrorInfo<>();
        try{
        	long id =RequestUtil.getLong("id", request);
        	String password =RequestUtil.getString("password", request);
            if (id < 1 || StrUtils.isBlank(password)) {
            	errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
			}
            password =Encrypt.getPasswd(password);
            userService.updateByItem("password", password, id);
            errorInfo.setSuccess(true);
            errorInfo.setErrorinfo("更改审核状态成功");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，更新用户信息失败，原因:"+ex.getMessage());
            LOGGER.error("更新用户信息失败发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	
	
	 @RequestMapping(value="editvip.json",method =RequestMethod.POST)
		@ResponseBody
		public ErrorInfo<UserDo> dovip(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ErrorInfo<UserDo> errorInfo = new ErrorInfo<>();
	        try{
	        	long id =RequestUtil.getLong("id", request);
	        	String isvip=RequestUtil.getString("isvip", request);
	        	String vipstarttime=RequestUtil.getString("vipstarttime", request);
	        	String vipendtime=RequestUtil.getString("vipendtime", request);
	        	Map<String, Object> map=new HashMap<String, Object>();
	            if(StrUtils.isNoBlank(isvip)){
	            	map.put("isvip",isvip);
	            }else{
	            	map.put("isvip",1);
	            }
	            if(StrUtils.isNoBlank(vipstarttime)){
	        		map.put("vipstarttime",vipstarttime);
	        	}
	        	if(StrUtils.isNoBlank(vipendtime)){
	        		map.put("vipendtime",vipendtime);
	        	}
	        	userService.update(map, id);
	            errorInfo.setSuccess(true);
	            errorInfo.setErrorinfo("更改审核状态成功");
	        }catch (Exception ex){
	            errorInfo.setErrorinfo("对不起，更新用户vip信息失败，原因:"+ex.getMessage());
	            LOGGER.error("更新用户vip信息失败发生错误:"+ex.getMessage());
	            LOGGER.debug(ex);
	        }
	        return errorInfo;
		}
	
}