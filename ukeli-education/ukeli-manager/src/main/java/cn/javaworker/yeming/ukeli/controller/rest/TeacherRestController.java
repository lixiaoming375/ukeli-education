/**
* <p> * Title: 商城管理信息系统 *</p>
* <p> * Description: * </p>
* <p> * Copyright: Copyright (c) 2012-2017* </p>
* <p> * Company: 苏州明翔信息科技有限公司 * </p>
* @author 叶明（开发）
* @version 0.1
*/
package cn.javaworker.yeming.ukeli.controller.rest;

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
import cn.javaworker.yeming.core.web.CookiesUtil;
import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.ukeli.service.TeacherService;
import cn.javaworker.yeming.ukeli.pojo.TeacherDo;
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
@RequestMapping("/teacher/")
public class TeacherRestController  implements RestBaseController<TeacherDo> {

	private final static Logger LOGGER =LogManager.getLogger(TeacherRestController.class);
	@Autowired
	private TeacherService teacherService;
	
    
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#del(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="del.json",method =RequestMethod.POST)
	public ErrorInfo<TeacherDo> del(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<TeacherDo> errorInfo = new ErrorInfo<>();
        try{
            LOGGER.info("需要删除的id=" + ids);
            String[] str = StringUtils.split(ids, ",");
            teacherService.delByIds(str);
            errorInfo.setSuccess(true);
            errorInfo.setErrorinfo("删除成功");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，删除失败，原因:"+ex.getMessage());
            LOGGER.error("删除一个发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#list(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="list.json",method =RequestMethod.POST)
	public Page<TeacherDo> list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<TeacherDo> page = null;
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
            short status =RequestUtil.getShort("status",(short)-1, request);
            short slock =RequestUtil.getShort("slock",(short)-1, request);
            String keyword = RequestUtil.getString("keyword",request);
            LOGGER.info("获取status=" + status + ",关键字="+keyword + ",start=" + start +",limit=" + limit);
            page = teacherService.getPage(keyword,status,slock,start,limit);
            if(null != page){
            	/*List<AdminDo> list = page.getList();
            	if(null != list){
					Map<Long, String> roleNameMap = roleAction.getRoleNameByAdminid();
            		for (Admin admin : list) {
            			String name = roleNameMap.get(admin.getId());
            			if(null != name){
            				admin.setExt_rolename(name);
            			}
					}
            	}*/
            }
        }catch (Exception ex){
            LOGGER.error("获取分页发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return page;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#save(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="save.json",method =RequestMethod.POST)
	public ErrorInfo<TeacherDo> save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<TeacherDo> errorInfo = new ErrorInfo<>();
        try{
            long id =RequestUtil.getLong("id", request);
            /*
            String username = RequestUtil.getString("username", request);
            if(StrUtils.isBlank(username)) {
            	errorInfo.setSuccess(false);
            	errorInfo.setIreturn(900);
            	errorInfo.setErrorinfo("对不起，登录用户名不能为空");
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
            	map.put("editer", adminName);
            	map.put("editerid", adminid);
            	map.remove("file");
            	teacherService.update(map, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("恭喜您，更新成功");
            }else {
            	TeacherDo teacherDo = RequestUtil.form(TeacherDo.class,request);
            	if(null !=teacherDo) {
            		teacherDo.setAdderid(adminid);
            		teacherDo.setAdder(adminName);
            		teacherService.save(teacherDo);
                    errorInfo.setSuccess(true);
                    errorInfo.setErrorinfo("恭喜您，保存成功");
            	}else {
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，参数错误，请检查");
            	}
            }
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，新增一个信息失败，原因:"+ex.getMessage());
            LOGGER.error("新增一个信息发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#status(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="status.json",method =RequestMethod.POST)
	public ErrorInfo<TeacherDo> status(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<TeacherDo> errorInfo = new ErrorInfo<>();
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
            	teacherService.updateByItem("status", status, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("更新状态成功");
			}else {
				teacherService.updateByItem("auditstatus", status, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("更改审核状态成功");
			}
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，更新状态失败，原因:"+ex.getMessage());
            LOGGER.error("更新状态失败发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	
	@RequestMapping(value="order.json",method =RequestMethod.POST)
	@ResponseBody
	public ErrorInfo<TeacherDo> order(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<TeacherDo> errorInfo = new ErrorInfo<>();
        try{
        	long id =RequestUtil.getLong("id", request);
        	int type =RequestUtil.getInt("type", request);
        	if (id < 1 || type < 0) {
            	errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
			}
        	TeacherDo teacherDo = teacherService.get(id);
        	if(null ==teacherDo){
        		errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
        	}
        	TeacherDo vteacherDo = teacherService.getByOrderType(teacherDo.getIorder(),type);
        	if(null == vteacherDo){
        		if(type ==1){
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最顶层了");
        		}else{
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最底层了");
        		}
        		return errorInfo;
        	}
        	if(null != teacherDo && null != vteacherDo){
        		teacherService.order(teacherDo,vteacherDo);
        	}
        	errorInfo.setSuccess(true);
        	errorInfo.setErrorinfo("排序成功!");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，更新排序失败，原因:"+ex.getMessage());
            LOGGER.error("更新排序失败发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	
}