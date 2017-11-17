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

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.javaworker.yeming.core.util.Constant;
import cn.javaworker.yeming.core.util.StrUtils;
import cn.javaworker.yeming.core.web.CookiesUtil;
import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.pojo.ErrorInfo;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.ukeli.UserUtil;
import cn.javaworker.yeming.ukeli.pojo.QuestionDo;
import cn.javaworker.yeming.ukeli.service.QuestionService;
import cn.javaworker.yeming.ukeli.util.ConstantWebUkeli;

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
@RestController
@RequestMapping("/question")
public class QuestionRestController{

	private final static Logger LOGGER = LogManager.getLogger(QuestionRestController.class);
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(value="save.json",method=RequestMethod.POST)
	public ErrorInfo<Object> save(HttpServletRequest request,HttpServletResponse response){
		ErrorInfo<Object> errorInfo = new ErrorInfo<>();
		try {
			String question =RequestUtil.getString("question", request);
			Short vtype =RequestUtil.getShort("vtype", request);
			long userid = CookiesUtil.getLong3Des(UserUtil.USER_COOKIESID, request);
			long relationid =RequestUtil.getLong("relationid", request);
			LOGGER.info("保存用户提问的问题vtype="+vtype + ",userid="+userid + ",question="+question);
			if(StrUtils.isBlank(question)) {
				errorInfo.setErrorinfo("对不起，请输入问题!");
				return errorInfo;
			}else {
				question =StringEscapeUtils.escapeHtml4(question);
			}
			QuestionDo questionDo = new QuestionDo();
			questionDo.setQuestion(question);
			questionDo.setUserid(userid);
			questionDo.setVtype(vtype);
			questionDo.setRelationid(relationid);
			questionDo.setIsanswer("0");
			questionService.save(questionDo);
			errorInfo.setErrorinfo("恭喜您，提问成功");
			errorInfo.setSuccess(true);
		} catch (Exception ex) {
			LOGGER.error("保存问题发生错误:"+ex.getMessage(),ex);
			errorInfo.setErrorinfo(ConstantWebUkeli.SYSTEM_ERROR);
		}
		return errorInfo;
	}
	
	
	@RequestMapping(value="list.json",method =RequestMethod.POST)
	public ErrorInfo<Object> list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<Object> errorInfo = new ErrorInfo<>();
		Page<QuestionDo> page = null;
        try{
        	long start = RequestUtil.getLong("start", request);
            int limit = RequestUtil.getInt("limit", request);
            int pageVal=RequestUtil.getInt("page", request);
            if(limit < Constant.SMALL_PAGE_SIZE){
            	limit = 5;
            }
            if(start < 0){
            	start =0;
            }else{
            	start =(pageVal-1)*limit;
            }
            short status =RequestUtil.getShort("status",(short)-1, request);
            //short slock =RequestUtil.getShort("slock",(short)-1, request);
            short vtype=RequestUtil.getShort("vtype",request);
			long relationid=RequestUtil.getLong("relationid",request);
            LOGGER.info("获取status=" + status + ",start=" + start +",limit=" + limit+",relationid=" + relationid+",vtype="+ vtype);
            page = questionService.getPage(null, vtype, relationid, 0, -1, -1, -1, start, limit);
        	if (null != page) {
        		errorInfo.setObj(page);
        		errorInfo.setSuccess(true);
			}
        }catch (Exception ex){
            LOGGER.error("获取分页发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	

}
