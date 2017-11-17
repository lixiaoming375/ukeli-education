/**
* <p> * Title: 优课力信息管理系统*</p>
* <p> * Description: 优课力信息管理系统 * </p>
* <p> * Copyright: Copyright (c) 2012-2018* </p>
* <p> * Company:苏州明翔信息科技有限公司 * </p>
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

import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.ukeli.ConstantUkeli;
import cn.javaworker.yeming.ukeli.pojo.QuestionDo;
import cn.javaworker.yeming.ukeli.service.QuestionService;

/**
 * 创建日期：2017年10月18日 下午2:53:05
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
public class QuestionController extends BaseController{
	
	
	private final static Logger LOGGER = LogManager.getLogger(QuestionController.class);
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(value= {"/question.html"},method=RequestMethod.GET)
	public String index(ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		LOGGER.info("系统进入问答:");
		int iPage =RequestUtil.getInt("page", request);
		int limit =10;
		if(iPage < 1) {
			iPage =1;
		}
		long start = (iPage -1) * limit;
		
		Page<QuestionDo> page = questionService.getPage(null,(short)ConstantUkeli.QUESTIONVTYPEFOR, 0, 0, 0, -1, -1, start, limit);
		List<QuestionDo> list = page.getList();
		map.put("list", list);
		map.put("page", page);
		map.put("questionVtype", ConstantUkeli.QUESTIONVTYPEFOR);
		setUser(map, request, response);
		setting(map, request, response);
		return "question";
	}

}
