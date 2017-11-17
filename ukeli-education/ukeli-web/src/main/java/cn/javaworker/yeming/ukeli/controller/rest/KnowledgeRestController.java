package cn.javaworker.yeming.ukeli.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.pojo.ErrorInfo;
import cn.javaworker.yeming.ukeli.ConstantUkeli;
import cn.javaworker.yeming.ukeli.pojo.UserKnowledgeDo;
import cn.javaworker.yeming.ukeli.service.KnowledgeService;
import cn.javaworker.yeming.ukeli.service.UserKnowledgeService;
import cn.javaworker.yeming.ukeli.service.UserService;
import cn.javaworker.yeming.ukeli.util.ConstantWebUkeli;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeRestController {
	
	private final static Logger LOGGER = LogManager.getLogger(KnowledgeRestController.class);
	@Autowired
	private UserKnowledgeService userKnowledgeService;
	@Autowired
	private UserService userService;
	@Autowired
	private KnowledgeService knowledgeService;

	@RequestMapping("/addcourse.json")
	public ErrorInfo<Object> addCourse(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ErrorInfo<Object> errorInfo = new ErrorInfo<>();
		try {
			long userid=RequestUtil.getLong("userid", request);
			long knowledgeid=RequestUtil.getLong("knowledgeid", request);
			UserKnowledgeDo  userKnowledgeDo=userKnowledgeService.addUserKnowledge(userService.get(userid),knowledgeService.get(knowledgeid),ConstantUkeli.ISADDCOURSE,"");
			errorInfo.setObj(userKnowledgeDo);
			errorInfo.setSuccess(true);
		} catch (Exception ex) {
			errorInfo.setSuccess(false);
			errorInfo.setErrorinfo(ConstantWebUkeli.SYSTEM_ERROR);
			LOGGER.error("用户添加课程发生错误:"+ex.getMessage());
			LOGGER.debug(ex);
		}
		return errorInfo;
	}
	
	
	@RequestMapping("/doneknowledge.json")
	public ErrorInfo<Object> doneknowledge(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ErrorInfo<Object> errorInfo = new ErrorInfo<>();
		try {
			long userid=RequestUtil.getLong("userid", request);
			long knowledgeid=RequestUtil.getLong("knowledgeid", request);
			String isdone=RequestUtil.getString("isdone", request); 
			UserKnowledgeDo  userknowledgeDo=userKnowledgeService.addUserKnowledge(userService.get(userid),knowledgeService.get(knowledgeid),0,isdone);
			errorInfo.setObj(userknowledgeDo);
			errorInfo.setSuccess(true);
		} catch (Exception ex) {
			errorInfo.setSuccess(false);
			errorInfo.setErrorinfo(ConstantWebUkeli.SYSTEM_ERROR);
			LOGGER.error("用户完成视频发生错误:"+ex.getMessage());
			LOGGER.debug(ex);
		}
		return errorInfo;
	
	}
	
}
