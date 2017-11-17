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

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.javaworker.yeming.core.web.CookiesUtil;
import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.manager.pojo.ClazzDo;
import cn.javaworker.yeming.manager.service.ClazzService;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.ukeli.ConstantUkeli;
import cn.javaworker.yeming.ukeli.UserUtil;
import cn.javaworker.yeming.ukeli.pojo.CupDo;
import cn.javaworker.yeming.ukeli.pojo.ExamDo;
import cn.javaworker.yeming.ukeli.pojo.SubjectDo;
import cn.javaworker.yeming.ukeli.pojo.UserSubjectDo;
import cn.javaworker.yeming.ukeli.service.CupService;
import cn.javaworker.yeming.ukeli.service.SubjectService;
import cn.javaworker.yeming.ukeli.service.UserSubjectService;
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
@RequestMapping("/cup")
public class CupController extends BaseController{
	private final static Logger LOGGER = LogManager.getLogger(CupController.class);
	@Autowired
	private CupService cupService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private UserSubjectService userSubjectService;
	@Autowired
	private ClazzService clazzService;
	
	@RequestMapping("/subject/{id}.html")
	public String subject(@PathVariable(name="id") long id,ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		long userid=0;
		String subjectId=RequestUtil.getString("subjectid", request);
		List<SubjectDo> list = null;
		SubjectDo indexSubject=null;
		UserSubjectDo userSubject=null;
		try {
			userid = CookiesUtil.getInt3Des(UserUtil.USER_COOKIESID,request);
		}catch (Exception ex){
			LOGGER.error("从cookies中获取用户信息发生错误:"+ex.getMessage());
		}
		if(id < 0) {
			response.sendRedirect(webPath + "/404.html");
			return null;
		}
		CupDo cupDo = cupService.get(id);
		if(null == cupDo) {
			response.sendRedirect(webPath + "/404.html");
			return null;
		}
		
		Page<SubjectDo> page=subjectService.getSubList(ConstantUkeli.SEARCHSUBJECTBYCUP, id, 0, 0, 0, ConstantUkeli.NOPAGELIMIT);
		if(null!=page){
			list=page.getList();
		}
		if(StringUtils.isBlank(subjectId)&&null!=list&&list.size()>0){
			indexSubject=list.get(0);
		}else{
			indexSubject=subjectService.get(subjectId);
		}
		if(userid>0&&null!=indexSubject){
			userSubject=userSubjectService.getUsersubject(userid, indexSubject.getId());
		}
		
		
		LOGGER.info("list="+list);
		map.put("cup", cupDo);
		map.put("list", list);
		map.put("userid", userid);
		map.put("indexSubject", indexSubject);
		map.put("userSubject", userSubject);
		map.put("questionVtype", ConstantUkeli.QUESTIONVTYPEFORSUBJECT);
		setting(map, request, response);
		setUser(map, request, response);
		return "cup/subject";
	}

	/**
	 * 获取一个杯赛考试记录
	 * @param id
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/start/{id}.html")
	public String start(@PathVariable(name="id") long id,ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(id < 0) {
			response.sendRedirect(webPath + "/404.html");
			return null;
		}
		CupDo cupDo = cupService.get(id);
		if(null == cupDo) {
			response.sendRedirect(webPath + "/404.html");
			return null;
		}
		map.put("cup", cupDo);
		setting(map, request, response);
		setUser(map, request, response);
		return "cup/start";
	}
	
	@RequestMapping("/allsubject/{id}.html")
	public String allSubject(@PathVariable(name="id") long id,ModelMap map,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		if(id < 0) {
			response.sendRedirect(webPath + "/404.html");
			return null;
		}
		CupDo cupDo = cupService.get(id);
		if(null == cupDo) {
			response.sendRedirect(webPath + "/404.html");
			return null;
		}
		map.put("cup", cupDo);
		//开始处理
		List<SubjectVo> list = subjectService.getByCupid(id);
		map.put("list", list);
		setting(map, request, response);
		setUser(map, request, response);
		return "cup/exam";
	}

}
