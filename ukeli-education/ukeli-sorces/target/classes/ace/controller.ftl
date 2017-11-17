<#include "copyright.ftl"/>

package ${spackage}.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.javaworker.yeming.manager.controller.BaseControllerImpl;

import cn.javaworker.yeming.core.base.controller.WebBaseController;
import cn.javaworker.yeming.core.web.RequestUtil;
import ${spackage}.service.${pojo}Service;
import ${spackage}.pojo.${pojo}Do;

<#include "version.ftl"/>

@Controller
@RequestMapping("<#if model??>/${model}</#if>/${pojo?lower_case}/")
public class ${pojo}Controller  extends BaseControllerImpl implements WebBaseController<${pojo}Do> {

	private final static Logger LOGGER =LogManager.getLogger(${pojo}Controller.class);
	@Autowired
	private ${pojo}Service ${pojo?lower_case}Service;
	
    
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#page(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "page.html",method=RequestMethod.GET)
	public String page(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		setting(map,request,response);
		setItems(map, request, response);
		return "<#if model??>/${model}</#if>/${pojo?lower_case}/page";
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#edit(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "edit.html",method=RequestMethod.POST)
	public String edit(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong("id",request);
		LOGGER.info("需要修改密码的权限编号:"+id);
        boolean edit =false;
        ${pojo}Do ${pojo?lower_case} =new ${pojo}Do();
        if(id > 0){
            ${pojo?lower_case} = ${pojo?lower_case}Service.get(id);
            if(null == ${pojo?lower_case}){
                ${pojo?lower_case} = new ${pojo}Do();
            }else{
                edit=true;
            }
        }
        map.put("${pojo?lower_case}",${pojo?lower_case} );
        map.put("edit",edit);
		setting(map,request,response);
        return "<#if model??>/${model}</#if>/${pojo?lower_case}/edit";
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#show(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "show.html", method = RequestMethod.POST)
	public String show(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong("id", request);
		${pojo}Do ${pojo?lower_case} = ${pojo?lower_case}Service.get(id);
		map.put("${pojo?lower_case}",${pojo?lower_case} );
		setting(map, request, response);
		return "<#if model??>/${model}</#if>/${pojo?lower_case}/show";
	}
    
}