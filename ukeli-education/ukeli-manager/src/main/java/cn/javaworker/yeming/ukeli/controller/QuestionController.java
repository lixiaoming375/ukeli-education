/**
* <p> * Title: 商城管理信息系统 *</p>
* <p> * Description: * </p>
* <p> * Copyright: Copyright (c) 2012-2017* </p>
* <p> * Company: 苏州明翔信息科技有限公司 * </p>
* @author 叶明（开发）
* @version 0.1
*/
package cn.javaworker.yeming.ukeli.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.javaworker.yeming.manager.controller.BaseControllerImpl;
import cn.javaworker.yeming.manager.pojo.ClazzDo;
import cn.javaworker.yeming.manager.service.ClazzService;
import cn.javaworker.yeming.core.base.controller.WebBaseController;
import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.ukeli.service.QuestionService;
import cn.javaworker.yeming.ukeli.service.UserService;
import cn.javaworker.yeming.ukeli.ConstantUkeli;
import cn.javaworker.yeming.ukeli.pojo.QuestionDo;
import cn.javaworker.yeming.ukeli.pojo.UserDo;

/**
* 创建日期：2017-10-20 10:03:56
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
@RequestMapping("/question/")
public class QuestionController  extends BaseControllerImpl implements WebBaseController<QuestionDo> {

	private final static Logger LOGGER =LogManager.getLogger(QuestionController.class);
	@Autowired
	private QuestionService questionService;
	@Autowired
	private ClazzService clazzService;
	@Autowired
	private UserService userService;
	
    
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#page(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "page.html",method=RequestMethod.GET)
	public String page(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		setting(map,request,response);
		List<ClazzDo> clazzDoList =clazzService.getListByTypeid(ConstantUkeli.QUESTIONTYPECLAZZTYPEID,ConstantUkeli.ONLINE_STATUS);//查询所有题目类型
		setItems(map, request, response);
	    map.put("clazzDoList", clazzDoList);
		return "/question/page";
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#edit(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "edit.html",method=RequestMethod.POST)
	public String edit(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong("id",request);
		LOGGER.info("需要修改密码的权限编号:"+id);
        boolean edit =true;
        QuestionDo  question = questionService.get(id);
        UserDo user=null;
        if(null!=question&&question.getUserid()>0){
        	  user=userService.get(question.getUserid());
        }
        map.put("question",question );
        map.put("user",user );
        map.put("edit",edit);
		setting(map,request,response);
        return "/question/edit";
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#show(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "show.html", method = RequestMethod.POST)
	public String show(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong("id", request);
		QuestionDo question = questionService.get(id);
		UserDo user=null;
		if(null!=question){
			question.setExt_addtime(question.getAddtime());
			user=userService.get(question.getUserid());
		}
		map.put("user",user );
		map.put("question",question );
		setting(map, request, response);
		return "/question/show";
	}
    
}