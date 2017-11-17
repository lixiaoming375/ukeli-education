<#include "copyright.ftl"/>

package ${spackage}.controller.rest;

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
import ${spackage}.service.${pojo}Service;
import ${spackage}.pojo.${pojo}Do;
import cn.javaworker.yeming.pojo.ErrorInfo;
import cn.javaworker.yeming.pojo.Page;

<#include "version.ftl"/>

@RestController
@RequestMapping("<#if model??>/${model}</#if>/${pojo?lower_case}/")
public class ${pojo}RestController  implements RestBaseController<${pojo}Do> {

	private final static Logger LOGGER =LogManager.getLogger(${pojo}RestController.class);
	@Autowired
	private ${pojo}Service ${pojo?lower_case}Service;
	
    
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#del(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="del.json",method =RequestMethod.POST)
	public ErrorInfo<${pojo}Do> del(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<${pojo}Do> errorInfo = new ErrorInfo<>();
        try{
            LOGGER.info("需要删除的<#if tabdesp??>${tabdesp}</#if>id=" + ids);
            String[] str = StringUtils.split(ids, ",");
            ${pojo?lower_case}Service.delByIds(str);
            errorInfo.setSuccess(true);
            errorInfo.setErrorinfo("删除成功");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，删除<#if tabdesp??>${tabdesp}</#if>失败，原因:"+ex.getMessage());
            LOGGER.error("删除一个<#if tabdesp??>${tabdesp}</#if>发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#list(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="list.json",method =RequestMethod.POST)
	public Page<${pojo}Do> list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<${pojo}Do> page = null;
        try{
            long start = RequestUtil.getLong("start", request);
            int limit = RequestUtil.getInt("limit", request);
            if(start < 0){
            	start =0;
            }
            if(limit < Constant.SMALL_PAGE_SIZE){
            	limit = Constant.SMALL_PAGE_SIZE;
            }
            short status =RequestUtil.getShort("status",(short)-1, request);
            short slock =RequestUtil.getShort("slock",(short)-1, request);
            String keyword = RequestUtil.getString("keyword",request);
            LOGGER.info("获取<#if tabdesp??>${tabdesp}</#if>status=" + status + ",关键字="+keyword + ",start=" + start +",limit=" + limit);
            page = ${pojo?lower_case}Service.getPage(keyword,status,slock,start,limit);
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
            LOGGER.error("获取<#if tabdesp??>${tabdesp}</#if>分页发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return page;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#save(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="save.json",method =RequestMethod.POST)
	public ErrorInfo<${pojo}Do> save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<${pojo}Do> errorInfo = new ErrorInfo<>();
        try{
            long id =RequestUtil.getLong("id", request);
            /*
            String username = RequestUtil.getString("username", request);
            if(StrUtils.isBlank(username)) {
            	errorInfo.setSuccess(false);
            	errorInfo.setIreturn(900);
            	errorInfo.setErrorinfo("对不起，<#if tabdesp??>${tabdesp}</#if>登录用户名不能为空");
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
            	${pojo?lower_case}Service.update(map, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("恭喜您，更新成功");
            }else {
            	${pojo}Do ${pojo?lower_case}Do = RequestUtil.form(${pojo}Do.class,request);
            	if(null !=${pojo?lower_case}Do) {
            		${pojo?lower_case}Do.setAdderid(adminid);
            		${pojo?lower_case}Do.setAdder(adminName);
            		${pojo?lower_case}Service.save(${pojo?lower_case}Do);
                    errorInfo.setSuccess(true);
                    errorInfo.setErrorinfo("恭喜您，保存<#if tabdesp??>${tabdesp}</#if>成功");
            	}else {
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，参数错误，请检查");
            	}
            }
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，新增一个<#if tabdesp??>${tabdesp}</#if>信息失败，原因:"+ex.getMessage());
            LOGGER.error("新增一个<#if tabdesp??>${tabdesp}</#if>信息发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#status(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="status.json",method =RequestMethod.POST)
	public ErrorInfo<${pojo}Do> status(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<${pojo}Do> errorInfo = new ErrorInfo<>();
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
            	${pojo?lower_case}Service.updateByItem("status", status, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("更新状态成功");
			}else {
				${pojo?lower_case}Service.updateByItem("auditstatus", status, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("更改审核状态成功");
			}
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，更新<#if tabdesp??>${tabdesp}</#if>状态失败，原因:"+ex.getMessage());
            LOGGER.error("更新<#if tabdesp??>${tabdesp}</#if>状态失败发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	
	@RequestMapping(value="order.json",method =RequestMethod.POST)
	@ResponseBody
	public ErrorInfo<${pojo}Do> order(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<${pojo}Do> errorInfo = new ErrorInfo<>();
        try{
        	long id =RequestUtil.getLong("id", request);
        	int type =RequestUtil.getInt("type", request);
        	if (id < 1 || type < 0) {
            	errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
			}
        	${pojo}Do ${pojo?lower_case}Do = ${pojo?lower_case}Service.get(id);
        	if(null ==${pojo?lower_case}Do){
        		errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
        	}
        	${pojo}Do v${pojo?lower_case}Do = ${pojo?lower_case}Service.getByOrderType(${pojo?lower_case}Do.getIorder(),type);
        	if(null == v${pojo?lower_case}Do){
        		if(type ==1){
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最顶层了");
        		}else{
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最底层了");
        		}
        		return errorInfo;
        	}
        	if(null != ${pojo?lower_case}Do && null != v${pojo?lower_case}Do){
        		${pojo?lower_case}Service.order(${pojo?lower_case}Do,v${pojo?lower_case}Do);
        	}
        	errorInfo.setSuccess(true);
        	errorInfo.setErrorinfo("排序成功!");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，更新<#if tabdesp??>${tabdesp}</#if>排序失败，原因:"+ex.getMessage());
            LOGGER.error("更新<#if tabdesp??>${tabdesp}</#if>排序失败发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	
}