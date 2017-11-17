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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.javaworker.yeming.manager.pojo.ClazzDo;
import cn.javaworker.yeming.manager.service.ClazzService;
import cn.javaworker.yeming.ukeli.ConstantUkeli;
import cn.javaworker.yeming.ukeli.pojo.ExamDo;
import cn.javaworker.yeming.ukeli.service.ExamService;
import cn.javaworker.yeming.ukeli.service.SubjectService;
import cn.javaworker.yeming.ukeli.vo.SubjectVo;

/**
 * 创建日期：2017年10月15日 下午10:43:43
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
@RequestMapping("/exam")
public class ExamController extends BaseController{
	private final static Logger LOGGER = LogManager.getLogger(ExamController.class);
	@Autowired
	private ExamService examService;
	@Autowired
	private ClazzService clazzService;
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping("/start/{id}.html")
	public String start(@PathVariable(name="id") long id,ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		if(id < 0) {
			response.sendRedirect(webPath + "/404.html");
			return null;
		}
		ExamDo examDo = examService.get(id);
		if(null == examDo) {
			response.sendRedirect(webPath + "/404.html");
			return null;
		}
		long gradeid = examDo.getGradeid();
		ClazzDo clazzDo = clazzService.get(gradeid);
		List<ExamDo> list = examService.getList(gradeid,0,30);
		LOGGER.info("list="+list);
		map.put("examDo", examDo);
		map.put("list", list);
		map.put("clazzDo", clazzDo);
		setting(map, request, response);
		setUser(map, request, response);
		return "exam/start";
	}

	/**
	 * 获取一个考试记录
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/{id}.html")
	public String exam(@PathVariable(name="id") long id,ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(id < 0) {
			response.sendRedirect(webPath + "/404.html");
			return null;
		}
		ExamDo examDo = examService.get(id);
		if(null == examDo) {
			response.sendRedirect(webPath + "/404.html");
			return null;
		}
		map.put("examDo", examDo);
		//开始处理
		List<SubjectVo> list = subjectService.getByExamid(id);
		LOGGER.info("list="+list);
		map.put("list", list);
		map.put("questionVtype", ConstantUkeli.QUESTIONVTYPEFOREXAM);
		setting(map, request, response);
		setUser(map, request, response);
		return "exam/exam";
	}

}
