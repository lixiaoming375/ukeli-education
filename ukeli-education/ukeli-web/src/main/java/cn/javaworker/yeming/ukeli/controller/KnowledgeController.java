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
import org.springframework.web.bind.annotation.RequestMethod;

import cn.javaworker.yeming.core.web.CookiesUtil;
import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.ukeli.ConstantUkeli;
import cn.javaworker.yeming.ukeli.UserUtil;
import cn.javaworker.yeming.ukeli.pojo.KnowledgeDo;
import cn.javaworker.yeming.ukeli.pojo.SubjectDo;
import cn.javaworker.yeming.ukeli.pojo.UserKnowledgeDo;
import cn.javaworker.yeming.ukeli.pojo.UserSubjectDo;
import cn.javaworker.yeming.ukeli.service.KnowledgeService;
import cn.javaworker.yeming.ukeli.service.SubjectService;
import cn.javaworker.yeming.ukeli.service.UserKnowledgeService;
import cn.javaworker.yeming.ukeli.service.UserSubjectService;
import cn.javaworker.yeming.ukeli.vo.SubjectVo;

/**
 * 创建日期：2017年10月12日 下午4:28:35 开发者：叶明(MSN:guming123416@hotmail.com,QQ:47043760)
 * 修改者： 修改时间： 程序作用： 1、 2、 修改说明： 1、 2、 版本：@version 0.1
 * 
 * @author 叶明
 */
@Controller
@RequestMapping("/knowledge")
public class KnowledgeController extends BaseController {

	private final static Logger LOGGER = LogManager.getLogger(KnowledgeController.class);

	@Autowired
	private KnowledgeService knowledgeService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private UserKnowledgeService userKnowledgeService;
	@Autowired
	private UserSubjectService userSubjectService;

	@RequestMapping(value = "/{id}.html", method = RequestMethod.GET)
	public String index(@PathVariable(name = "id") long id, ModelMap map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("id=" + id);
		long userid = 0;
		UserKnowledgeDo userKnowledgeDo = null;
		List<SubjectDo> subjectList = null;
		try {
			userid = CookiesUtil.getInt3Des(UserUtil.USER_COOKIESID, request);
		} catch (Exception ex) {
			LOGGER.error("从cookies中获取用户信息发生错误:" + ex.getMessage());
		}
		if (id < 0) {
			response.sendRedirect(webPath + "/404.html");
			return null;
		}
		KnowledgeDo knowledgeDo = knowledgeService.get(1);
		if (null == knowledgeDo) {
			response.sendRedirect(webPath + "/404.html");
			return null;
		}
		// 查询所有例题
		subjectList = getAllSubjectByKnowVtype(knowledgeDo.getId(), ConstantUkeli.SUBJECTVTYPELITI, userid);
		if (userid > 0) {
			userKnowledgeDo = userKnowledgeService.getUserKnowledge(userid, knowledgeDo.getId());
		}
		map.put("userKnowledge", userKnowledgeDo);
		map.put("subjectList", subjectList);
		map.put("questionVtype", ConstantUkeli.QUESTIONVTYPEFORKNOW);
		map.put("knowledge", knowledgeDo);
		setUser(map, request, response);
		setting(map, request, response);
		return "knowledge/detail";
	}

	@RequestMapping(value = "subject/{id}.html", method = RequestMethod.GET)
	public String indexSubject(@PathVariable(name = "id") long id, ModelMap map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("id=" + id);
		long userid = 0;
		UserSubjectDo userSubject = null;
		UserKnowledgeDo userKnowledge = null;
		List<SubjectDo> subjectList = null;
		long knowledgeid = RequestUtil.getLong("knowledgeid", request);
		try {
			userid = CookiesUtil.getInt3Des(UserUtil.USER_COOKIESID, request);
		} catch (Exception ex) {
			LOGGER.error("从cookies中获取用户信息发生错误:" + ex.getMessage());
		}

		if (id < 0) {
			response.sendRedirect(webPath + "/404.html");
			return null;
		}
		SubjectDo indexSubject = subjectService.get(id);
		if (null == indexSubject) {
			response.sendRedirect(webPath + "/404.html");
			return null;
		}
		KnowledgeDo knowledgeDo = knowledgeService.get(knowledgeid);
		if (null == knowledgeDo) {
			response.sendRedirect(webPath + "/404.html");
			return null;
		}
		if (userid > 0 && null != indexSubject) {
			userSubject = userSubjectService.getUsersubject(userid, indexSubject.getId());
		}
		if (userid > 0) {
			userKnowledge = userKnowledgeService.getUserKnowledge(userid, knowledgeDo.getId());
		}
		// 查询所有例题
		subjectList = getAllSubjectByKnowVtype(knowledgeDo.getId(), ConstantUkeli.SUBJECTVTYPELITI, userid);
		map.put("subjectList", subjectList);
		map.put("indexSubject", indexSubject);
		map.put("knowledge", knowledgeDo);
		map.put("userid", userid);
		map.put("userKnowledge", userKnowledge);
		map.put("userSubject", userSubject);
		map.put("questionVtype", ConstantUkeli.QUESTIONVTYPEFORSUBJECT);
		setUser(map, request, response);
		setting(map, request, response);
		return "knowledge/subject";
	}

	@RequestMapping(value = "allsubject/{id}.html", method = RequestMethod.GET)
	public String allsubject(@PathVariable(name = "id") long id, ModelMap map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("id=" + id);
		int score = 0;
		if (id < 0) {
			response.sendRedirect(webPath + "/404.html");
			return null;
		}
		KnowledgeDo knowledge = knowledgeService.get(id);
		List<SubjectVo> list = subjectService.getByKnowid(id, ConstantUkeli.SUBJECTVTYPEZICE);
		for (SubjectVo subject : list) {
			score += subject.getSubjectDo().getExt_score();
		}
		map.put("knowledge", knowledge);
		map.put("list", list);
		map.put("totalsubject", list.size());
		map.put("score", score);
		map.put("questionVtype", ConstantUkeli.QUESTIONVTYPEFORZICE);
		setUser(map, request, response);
		setting(map, request, response);
		return "knowledge/exam";
	}

	public List<SubjectDo> getAllSubjectByKnowVtype(long id, int vtype, long userid) {
		List<SubjectDo> subjectList = subjectService.getSubjectByKnowVtype(id, vtype);
		List<UserSubjectDo> userSubjectList = userSubjectService.getListByItem("userid", userid);
		if (subjectList != null) {
			for (SubjectDo subjectDo : subjectList) {
				for (UserSubjectDo userSubjectDo : userSubjectList) {
					if (subjectDo.getId() == userSubjectDo.getSubjectid()) {
						subjectDo.setExt_isdone(userSubjectDo.getIsdone());
						break;
					}
				}
			}
		}
		return subjectList;
	}

}
