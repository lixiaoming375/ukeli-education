<#include "copyright.ftl"/>

package ${spackage}.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;

import cn.javaworker.yeming.core.util.Constant;
import cn.javaworker.yeming.core.util.StrUtils;
import cn.javaworker.yeming.core.web.CookiesUtil;
import cn.javaworker.yeming.manager.service.PermissionService;
import cn.javaworker.yeming.manager.vo.PermissionVo;

<#include "version.ftl"/>

public class BaseControllerImpl {
	

	@Value("${r'${resWebUrl}'}")
	private String resWebUrl;
	@Value("${r'${adminPath}'}")
	private String adminPath;
	@Value("${r'${version}'}")
	private String version;
	@Value("${r'${stlplat}'}")
	private String stlplat;
	@Value("${r'${productName}'}")
	private String productName;
	@Autowired
	private PermissionService permissionService;
	
 	/**
	 * @param map
	 * @param request
	 * @param response
	 */
	public void setting(ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		map.put("resWebUrl",resWebUrl);
		map.put("adminPath",adminPath);
		map.put("version",version);
		map.put("stlplat",stlplat);
		map.put("productName",productName);
		String username =CookiesUtil.getString3Des(Constant.ADMIN_COOKIES_REALNAME, request);
		if(StrUtils.isNoBlank(username)) {
			map.put("username", username);
		}
	}
	
	/**
	 * 设置菜单
	 * @param map
	 * @param request
	 * @param response
	 */
	public void setItems(ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		long adminid =CookiesUtil.getLong3Des(Constant.ADMIN_COOKIES_ID, request);
		List<PermissionVo> list = new ArrayList<>();
		if (adminid > 0) {
			list = permissionService.getVoByAdmin(adminid);
		}
		map.put("treeItemlist", list);
	}

}