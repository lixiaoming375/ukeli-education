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
import cn.javaworker.yeming.ukeli.pojo.UserSubjectDo;
import cn.javaworker.yeming.ukeli.service.SubjectService;
import cn.javaworker.yeming.ukeli.service.UserService;
import cn.javaworker.yeming.ukeli.service.UserSubjectService;
import cn.javaworker.yeming.ukeli.util.ConstantWebUkeli;

@RestController
@RequestMapping("/subject")
public class SubjectRestController {

	private final static Logger LOGGER = LogManager.getLogger(SubjectRestController.class);
	@Autowired
	private UserSubjectService userSubjectService;
	@Autowired
	private UserService userService;
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping("/addcourse.json")
	public ErrorInfo<Object> addCourse(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ErrorInfo<Object> errorInfo = new ErrorInfo<>();
		try {
			long userid=RequestUtil.getLong("userid", request);
			long subjectid=RequestUtil.getLong("subjectid", request);
			UserSubjectDo  userSubjectDo=userSubjectService.addUsersubject(userService.get(userid),subjectService.get(subjectid),ConstantUkeli.ISADDCOURSE,"");
			errorInfo.setObj(userSubjectDo);
			errorInfo.setSuccess(true);
		} catch (Exception ex) {
			errorInfo.setSuccess(false);
			errorInfo.setErrorinfo(ConstantWebUkeli.SYSTEM_ERROR);
			LOGGER.error("用户添加课程发生错误:"+ex.getMessage());
			LOGGER.debug(ex);
		}
		return errorInfo;
	}
	
	
	@RequestMapping("/donesubject.json")
	public ErrorInfo<Object> donesubject(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ErrorInfo<Object> errorInfo = new ErrorInfo<>();
		try {
			long userid=RequestUtil.getLong("userid", request);
			long subjectid=RequestUtil.getLong("subjectid", request);
			String isdone=RequestUtil.getString("isdone", request); 
			UserSubjectDo  userSubjectDo=userSubjectService.addUsersubject(userService.get(userid),subjectService.get(subjectid),0,isdone);
			errorInfo.setObj(userSubjectDo);
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
