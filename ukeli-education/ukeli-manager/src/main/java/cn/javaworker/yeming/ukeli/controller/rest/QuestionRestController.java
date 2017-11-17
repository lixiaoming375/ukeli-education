/**
* <p> * Title: 商城管理信息系统 *</p>
* <p> * Description: * </p>
* <p> * Copyright: Copyright (c) 2012-2017* </p>
* <p> * Company: 苏州明翔信息科技有限公司 * </p>
* @author 叶明（开发）
* @version 0.1
*/
package cn.javaworker.yeming.ukeli.controller.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.javaworker.yeming.core.base.controller.RestBaseController;
import cn.javaworker.yeming.core.util.Constant;
import cn.javaworker.yeming.core.util.StrUtils;
import cn.javaworker.yeming.core.web.CookiesUtil;
import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.manager.pojo.ClazzDo;
import cn.javaworker.yeming.manager.service.ClazzService;
import cn.javaworker.yeming.ukeli.service.QuestionService;
import cn.javaworker.yeming.ukeli.ConstantUkeli;
import cn.javaworker.yeming.ukeli.pojo.QuestionDo;
import cn.javaworker.yeming.pojo.ErrorInfo;
import cn.javaworker.yeming.pojo.Page;

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
@RestController
@RequestMapping("/question/")
public class QuestionRestController  implements RestBaseController<QuestionDo> {

	private final static Logger LOGGER =LogManager.getLogger(QuestionRestController.class);
	@Autowired
	private QuestionService questionService;
	@Autowired
	private ClazzService clazzService;
	
    
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#del(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="del.json",method =RequestMethod.POST)
	public ErrorInfo<QuestionDo> del(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<QuestionDo> errorInfo = new ErrorInfo<>();
        try{
            LOGGER.info("需要删除的id=" + ids);
            String[] str = StringUtils.split(ids, ",");
            questionService.delByIds(str);
            errorInfo.setSuccess(true);
            errorInfo.setErrorinfo("删除成功");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，删除失败，原因:"+ex.getMessage());
            LOGGER.error("删除一个发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#list(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="list.json",method =RequestMethod.POST)
	public Page<QuestionDo> list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<QuestionDo> page = null;
        try{
        	long start = RequestUtil.getLong("start", request);
            int limit = RequestUtil.getInt("limit", request);
            int pageVal=RequestUtil.getInt("page", request);
            if(limit < Constant.SMALL_PAGE_SIZE){
            	limit = Constant.SMALL_PAGE_SIZE;
            }
            if(start < 0){
            	start =0;
            }else{
            	start =(pageVal-1)*limit;
            }
            short status =RequestUtil.getShort("status",(short)-1, request);
            short slock =RequestUtil.getShort("slock",(short)-1, request);
            String reservationtime = RequestUtil.getString("reservationtime", request);
			Date startTime = null;
			Date endTime = null;
			if (StringUtils.isNoneBlank(reservationtime)) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				startTime = df.parse(reservationtime.split("-")[0].replace("/", "-"));
				endTime = df.parse(reservationtime.split("-")[1].replace("/", "-"));
			}
			String question=RequestUtil.getString("question",request);
			String vtype=RequestUtil.getString("vtype",request);
			String isanswer=RequestUtil.getString("isanswer",request);
			long relationid=RequestUtil.getLong("relationid",request);
			String nickname=RequestUtil.getString("nickname",request);
            LOGGER.info("获取status=" + status + ",start=" + start +",limit=" + limit);
            page = questionService.getPage(question,vtype,isanswer,relationid,nickname,startTime,endTime,status,slock,start,limit);
        	if (null != page) {
				List<QuestionDo> list = page.getList();
				if (null != list) {
					List<ClazzDo> clazzDolist=clazzService.getListByTypeid(ConstantUkeli.QUESTIONTYPECLAZZTYPEID, ConstantUkeli.ONLINE_STATUS);
					for (QuestionDo ques : list) {
						ques.setExt_addtime(ques.getAddtime());
						for (ClazzDo clazz : clazzDolist) {
							if((int)ques.getVtype()==clazz.getId()){
								ques.setExt_vtype(clazz.getName());
            					break;
            				}
						}
					}
				}
			}
        }catch (Exception ex){
            LOGGER.error("获取分页发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return page;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#save(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="save.json",method =RequestMethod.POST)
	public ErrorInfo<QuestionDo> save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<QuestionDo> errorInfo = new ErrorInfo<>();
        try{
            long id =RequestUtil.getLong("id", request);
            /*
            String username = RequestUtil.getString("username", request);
            if(StrUtils.isBlank(username)) {
            	errorInfo.setSuccess(false);
            	errorInfo.setIreturn(900);
            	errorInfo.setErrorinfo("对不起，登录用户名不能为空");
            	return errorInfo;
            }
            if(adminService.check(username,id,1)) {
            	errorInfo.setSuccess(false);
            	errorInfo.setIreturn(900);
            	errorInfo.setErrorinfo("对不起，您输入的登录用户名已经存在");
            	return errorInfo;
            }
            */
            long adminid =CookiesUtil.getLong3Des(Constant.ADMIN_COOKIES_ID, request);
            String adminName =CookiesUtil.getString3Des(Constant.ADMIN_COOKIES_NAME, request);
            if(id > 0) {
            	Map<String, Object> map= RequestUtil.getParams(request);
            	map.put("editer", adminName);
            	map.put("editerid", adminid);
            	questionService.update(map, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("恭喜您，更新成功");
            }else {
            	QuestionDo questionDo = RequestUtil.form(QuestionDo.class,request);
            	if(null !=questionDo) {
            		questionDo.setAdderid(adminid);
            		questionDo.setAdder(adminName);
            		questionService.save(questionDo);
                    errorInfo.setSuccess(true);
                    errorInfo.setErrorinfo("恭喜您，保存成功");
            	}else {
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，参数错误，请检查");
            	}
            }
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，新增一个信息失败，原因:"+ex.getMessage());
            LOGGER.error("新增一个信息发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	
	 @RequestMapping(value="doback.json",method =RequestMethod.POST)
		@ResponseBody
		public ErrorInfo<QuestionDo> doback(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ErrorInfo<QuestionDo> errorInfo = new ErrorInfo<>();
	        try{
	        	long id =RequestUtil.getLong("id", request);
	        	//long userid=RequestUtil.getLong("userid", request);
	        	String answer=RequestUtil.getString("answer", request);
	        	Map<String, Object> map=new HashMap<String, Object>();
	        	if(StrUtils.isNoBlank(answer)){
	        		map.put("answer",answer);
	        	}
	        	map.put("isanswer",1);
	        	map.put("answer_time", new Date());
	        	questionService.update(map, id);
	            errorInfo.setSuccess(true);
	            errorInfo.setErrorinfo("更改审核状态成功");
	        }catch (Exception ex){
	            errorInfo.setErrorinfo("对不起，更新留言信息失败，原因:"+ex.getMessage());
	            LOGGER.error("更新留言信息失败发生错误:"+ex.getMessage());
	            LOGGER.debug(ex);
	        }
	        return errorInfo;
		}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#status(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="status.json",method =RequestMethod.POST)
	public ErrorInfo<QuestionDo> status(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<QuestionDo> errorInfo = new ErrorInfo<>();
        try{
        	long id =RequestUtil.getLong("id", request);
        	int status =RequestUtil.getInt("status", request);
        	int type =RequestUtil.getInt("type", request);
            if (id < 1 ||(status!=0 && status !=1)) {
            	errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
			}
            if (type==0) {
            	questionService.updateByItem("status", status, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("更新状态成功");
			}else {
				questionService.updateByItem("auditstatus", status, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("更改审核状态成功");
			}
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，更新状态失败，原因:"+ex.getMessage());
            LOGGER.error("更新状态失败发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	
	@RequestMapping(value="order.json",method =RequestMethod.POST)
	@ResponseBody
	public ErrorInfo<QuestionDo> order(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<QuestionDo> errorInfo = new ErrorInfo<>();
        try{
        	long id =RequestUtil.getLong("id", request);
        	int type =RequestUtil.getInt("type", request);
        	if (id < 1 || type < 0) {
            	errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
			}
        	QuestionDo questionDo = questionService.get(id);
        	if(null ==questionDo){
        		errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
        	}
        	QuestionDo vquestionDo = questionService.getByOrderType(questionDo.getIorder(),type);
        	if(null == vquestionDo){
        		if(type ==1){
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最顶层了");
        		}else{
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最底层了");
        		}
        		return errorInfo;
        	}
        	if(null != questionDo && null != vquestionDo){
        		questionService.order(questionDo,vquestionDo);
        	}
        	errorInfo.setSuccess(true);
        	errorInfo.setErrorinfo("排序成功!");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，更新排序失败，原因:"+ex.getMessage());
            LOGGER.error("更新排序失败发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	
}