/**
* <p> * Title: 优课力信息管理系统*</p>
* <p> * Description: 优课力信息管理系统 * </p>
* <p> * Copyright: Copyright (c) 2012-2018* </p>
* <p> * Company:苏州明翔信息科技有限公司 * </p>
* @author 叶明（开发）
* @version 0.1
*/
package cn.javaworker.yeming.ukeli.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.javaworker.yeming.core.web.CookiesUtil;
import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.pojo.ErrorInfo;
import cn.javaworker.yeming.ukeli.UserUtil;
import cn.javaworker.yeming.ukeli.pojo.ExamDo;
import cn.javaworker.yeming.ukeli.pojo.UserExamDo;
import cn.javaworker.yeming.ukeli.service.ExamService;
import cn.javaworker.yeming.ukeli.service.UserExamService;
import cn.javaworker.yeming.ukeli.util.ConstantWebUkeli;

/**
 * 创建日期：2017年10月16日 下午5:39:42
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
@RestController
@RequestMapping("/exam")
public class ExamRestController {

	private final static Logger LOGGER = LogManager.getLogger(ExamRestController.class);
	@Autowired
	private ExamService examService;
	@Autowired
	private UserExamService userExamService;
	
	/**
	 * 用户参与考试
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user.json")
	public ErrorInfo<Object> doExam(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ErrorInfo<Object> errorInfo = new ErrorInfo<>();
		try {
			long examid =RequestUtil.getLong("examid", request);
			long userid = CookiesUtil.getLong3Des(UserUtil.USER_COOKIESID, request);
			LOGGER.info("用户参与考试examid="+examid + ",userid="+userid);
			if(examid < 1) {
				errorInfo.setErrorinfo("对不起，考试信息不正确");
				return errorInfo;
			}
			ExamDo examDo  = examService.get(examid);
			if(null == examDo) {
				errorInfo.setErrorinfo("对不起，考试信息不正确");
				return errorInfo;
			}
			
			UserExamDo userExamDo = new UserExamDo();
			userExamDo.setUserid(userid);
			userExamDo.setExamid(examid);
			
			int count = userExamService.count(userid,examid);
			
			userExamDo.setVtime(count +1);
			long userexamid =userExamService.save(userExamDo);
			errorInfo.setObj(userexamid+"");
			errorInfo.setSuccess(true);
		} catch (Exception ex) {
			errorInfo.setErrorinfo(ConstantWebUkeli.SYSTEM_ERROR);
			LOGGER.error("用户参与考试发生错误:"+ex.getMessage());
			LOGGER.debug(ex);
		}
		return errorInfo;
	}
	

}
