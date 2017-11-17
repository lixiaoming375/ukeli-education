/**
* <p> * Title: 商城管理信息系统 *</p>
* <p> * Description: * </p>
* <p> * Copyright: Copyright (c) 2012-2017* </p>
* <p> * Company: 苏州明翔信息科技有限公司 * </p>
* @author 叶明（开发）
* @version 0.1
*/
package cn.javaworker.yeming.ukeli.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.javaworker.yeming.core.base.controller.WebBaseController;
import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.manager.controller.BaseControllerImpl;
import cn.javaworker.yeming.ukeli.pojo.KnowledgeDo;
import cn.javaworker.yeming.ukeli.pojo.UserDo;
import cn.javaworker.yeming.ukeli.pojo.UserKnowledgeDo;
import cn.javaworker.yeming.ukeli.service.KnowledgeService;
import cn.javaworker.yeming.ukeli.service.UserKnowledgeService;
import cn.javaworker.yeming.ukeli.service.UserService;

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
@Controller
@RequestMapping("/userknowledge/")
public class UserKnowledgeController  extends BaseControllerImpl implements WebBaseController<UserKnowledgeDo> {

	private final static Logger LOGGER =LogManager.getLogger(UserKnowledgeController.class);
	@Autowired
	private UserKnowledgeService userknowledgeService;
	@Autowired
	private UserService userService;
	@Autowired
	private KnowledgeService knowledgeService;
	
    
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#page(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "page.html",method=RequestMethod.GET)
	public String page(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		setting(map,request,response);
		setItems(map, request, response);
		return "/userknowledge/page";
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
        UserKnowledgeDo userknowledge =new UserKnowledgeDo();
        if(id > 0){
            userknowledge = userknowledgeService.get(id);
            if(null == userknowledge){
                userknowledge = new UserKnowledgeDo();
            }else{
                edit=true;
            }
        }
        map.put("userknowledge",userknowledge );
        map.put("edit",edit);
		setting(map,request,response);
        return "/userknowledge/edit";
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#show(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "show.html", method = RequestMethod.POST)
	public String show(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong("id", request);
		UserKnowledgeDo userknowledge = userknowledgeService.get(id);
		UserDo user=userService.get(userknowledge.getUserid());
		KnowledgeDo knowledge=knowledgeService.get(userknowledge.getKnowledgeid());
		map.put("userknowledge",userknowledge);
		map.put("user",user);
		map.put("knowledge",knowledge);
		setting(map, request, response);
		return "/userknowledge/show";
	}
    
}