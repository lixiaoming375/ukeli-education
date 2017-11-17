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
import cn.javaworker.yeming.ukeli.service.UserKnowledgeService;
import cn.javaworker.yeming.ukeli.pojo.UserKnowledgeDo;
import cn.javaworker.yeming.pojo.ErrorInfo;
import cn.javaworker.yeming.pojo.Page;

/**
* 创建日期：2017-10-24 10:41:59
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
@RequestMapping("/userknowledge/")
public class UserKnowledgeRestController  implements RestBaseController<UserKnowledgeDo> {

	private final static Logger LOGGER =LogManager.getLogger(UserKnowledgeRestController.class);
	@Autowired
	private UserKnowledgeService userknowledgeService;
	
    
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#del(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="del.json",method =RequestMethod.POST)
	public ErrorInfo<UserKnowledgeDo> del(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<UserKnowledgeDo> errorInfo = new ErrorInfo<>();
        try{
            LOGGER.info("需要删除的id=" + ids);
            String[] str = StringUtils.split(ids, ",");
            userknowledgeService.delByIds(str);
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
	public Page<UserKnowledgeDo> list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<UserKnowledgeDo> page = null;
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
            String username=RequestUtil.getString("username", request);
            String nodename=RequestUtil.getString("nodename", request);
            String isdone=RequestUtil.getString("isdone", request);
            String isadd=RequestUtil.getString("isadd", request);
            short status =RequestUtil.getShort("status",(short)-1, request);
            short slock =RequestUtil.getShort("slock",(short)-1, request);
            LOGGER.info("获取status=" + status + ",关键字="+username + ",start=" + start +",limit=" + limit);
            page = userknowledgeService.getPage(username, nodename, isdone, isadd, status, slock, start, limit);
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
	public ErrorInfo<UserKnowledgeDo> save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<UserKnowledgeDo> errorInfo = new ErrorInfo<>();
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
            	userknowledgeService.update(map, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("恭喜您，更新成功");
            }else {
            	UserKnowledgeDo userknowledgeDo = RequestUtil.form(UserKnowledgeDo.class,request);
            	if(null !=userknowledgeDo) {
            		userknowledgeDo.setAdderid(adminid);
            		userknowledgeDo.setAdder(adminName);
            		userknowledgeService.save(userknowledgeDo);
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
	public ErrorInfo<UserKnowledgeDo> status(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<UserKnowledgeDo> errorInfo = new ErrorInfo<>();
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
            	userknowledgeService.updateByItem("status", status, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("更新状态成功");
			}else {
				userknowledgeService.updateByItem("auditstatus", status, id);
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
	public ErrorInfo<UserKnowledgeDo> order(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<UserKnowledgeDo> errorInfo = new ErrorInfo<>();
        try{
        	long id =RequestUtil.getLong("id", request);
        	int type =RequestUtil.getInt("type", request);
        	if (id < 1 || type < 0) {
            	errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
			}
        	UserKnowledgeDo userknowledgeDo = userknowledgeService.get(id);
        	if(null ==userknowledgeDo){
        		errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
        	}
        	UserKnowledgeDo vuserknowledgeDo = userknowledgeService.getByOrderType(userknowledgeDo.getIorder(),type);
        	if(null == vuserknowledgeDo){
        		if(type ==1){
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最顶层了");
        		}else{
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最底层了");
        		}
        		return errorInfo;
        	}
        	if(null != userknowledgeDo && null != vuserknowledgeDo){
        		userknowledgeService.order(userknowledgeDo,vuserknowledgeDo);
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