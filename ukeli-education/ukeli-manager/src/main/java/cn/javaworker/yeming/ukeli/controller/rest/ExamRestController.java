/**
* <p> * Title: 商城管理信息系统 *</p>
* <p> * Description: * </p>
* <p> * Copyright: Copyright (c) 2012-2017* </p>
* <p> * Company: 苏州明翔信息科技有限公司 * </p>
* @author 叶明（开发）
* @version 0.1
*/
package cn.javaworker.yeming.ukeli.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.javaworker.yeming.core.base.controller.RestBaseController;
import cn.javaworker.yeming.core.util.Constant;
import cn.javaworker.yeming.core.web.CookiesUtil;
import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.manager.pojo.ClazzDo;
import cn.javaworker.yeming.manager.service.ClazzService;
import cn.javaworker.yeming.pojo.ErrorInfo;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.ukeli.ConstantUkeli;
import cn.javaworker.yeming.ukeli.pojo.ExamDo;
import cn.javaworker.yeming.ukeli.pojo.ExamSubjectDo;
import cn.javaworker.yeming.ukeli.service.ExamService;
import cn.javaworker.yeming.ukeli.service.ExamSubjectService;

/**
* 创建日期：2017-09-06 13:18:22
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
@RequestMapping("/exam/")
public class ExamRestController  implements RestBaseController<ExamDo> {

	private final static Logger LOGGER =LogManager.getLogger(ExamRestController.class);
	private final static int GRADECLAZZTYPEID=3;
	@Autowired
	private ExamService examService;
	@Autowired
	private ClazzService clazzService;
	@Autowired
	private ExamSubjectService examSubjectService;
	
    
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#del(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="del.json",method =RequestMethod.POST)
	public ErrorInfo<ExamDo> del(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<ExamDo> errorInfo = new ErrorInfo<>();
        try{
            LOGGER.info("需要删除的id=" + ids);
            String[] str = StringUtils.split(ids, ",");
            examService.delByIds(str);
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
	public Page<ExamDo> list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<ExamDo> page = null;
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
            short status =RequestUtil.getShort("status", request);
            short slock =RequestUtil.getShort("slock",(short)-1, request);
            String keyword = RequestUtil.getString("examName",request);
            LOGGER.info("获取status=" + status + ",关键字="+keyword + ",start=" + start +",limit=" + limit);
            page = examService.getPage(keyword,status,slock,start,limit);
            if(null != page){
            	List<ExamDo> list = page.getList();
            	if(null != list){
            		List<ClazzDo> clazzDolist= clazzService.getListByTypeid(GRADECLAZZTYPEID,ConstantUkeli.ONLINE_STATUS);
            		for (ExamDo exam : list) {
            			for(ClazzDo  clazz :clazzDolist){
            				if(exam.getGradeid()==clazz.getId()){
            					exam.setExt_grade(clazz.getName());
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
	public ErrorInfo<ExamDo> save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<ExamDo> errorInfo = new ErrorInfo<>();
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
            	map.remove("file");
            	examService.update(map, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("恭喜您，更新成功");
            }else {
            	ExamDo examDo = RequestUtil.form(ExamDo.class,request);
            	if(null !=examDo) {
            		examDo.setAdderid(adminid);
            		examDo.setAdder(adminName);
            		examService.save(examDo);
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
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#status(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="status.json",method =RequestMethod.POST)
	public ErrorInfo<ExamDo> status(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<ExamDo> errorInfo = new ErrorInfo<>();
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
            	examService.updateByItem("status", status, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("更新状态成功");
			}else {
				examService.updateByItem("auditstatus", status, id);
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
	public ErrorInfo<ExamDo> order(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<ExamDo> errorInfo = new ErrorInfo<>();
        try{
        	long id =RequestUtil.getLong("id", request);
        	int type =RequestUtil.getInt("type", request);
        	if (id < 1 || type < 0) {
            	errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
			}
        	ExamDo examDo = examService.get(id);
        	if(null ==examDo){
        		errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
        	}
        	ExamDo vexamDo = examService.getByOrderType(examDo.getIorder(),type);
        	if(null == vexamDo){
        		if(type ==1){
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最顶层了");
        		}else{
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最底层了");
        		}
        		return errorInfo;
        	}
        	if(null != examDo && null != vexamDo){
        		examService.order(examDo,vexamDo);
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
	
	/**
	 * 调整被绑定的题目排序
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="orderserial.json",method =RequestMethod.POST)
	@ResponseBody
	public ErrorInfo<ExamDo> orderserial(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<ExamDo> errorInfo = new ErrorInfo<>();
        try{
        	long id =RequestUtil.getLong("id", request);
        	Long examid=RequestUtil.getLong("examId", request);
        	int type =RequestUtil.getInt("type", request);
        	if (id < 1 || type < 0) {
            	errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
			}
        	Map<String, Object> itemsMap=new HashMap<String, Object>();
        	itemsMap.put("subjectid", id);
        	itemsMap.put("examid", examid);
        	ExamSubjectDo examSubjectDo=null;
        	List<ExamSubjectDo>	examSubjectList=examSubjectService.getByItems(itemsMap);
        	if(null ==examSubjectList||examSubjectList.size()!=1){
        		errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
        	}else{
        		examSubjectDo=examSubjectList.get(0);
        	}
        	ExamSubjectDo  vExamSubjectDo = examSubjectService.getByOrderSerialType(examSubjectDo.getSerial(),examSubjectDo.getExamid(), type);
        	if(null == vExamSubjectDo){
        		if(type ==1){
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最顶层了");
        		}else{
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最底层了");
        		}
        		return errorInfo;
        	}
        	if(null != examSubjectDo && null != vExamSubjectDo){
        		examSubjectService.orderSerial(examSubjectDo, vExamSubjectDo);
        	}
        	errorInfo.setInput(examid+"");
        	errorInfo.setSuccess(true);
        	errorInfo.setErrorinfo("排序成功!");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，更新知识点排序失败，原因:"+ex.getMessage());
            LOGGER.error("更新知识点排序失败发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#save(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	/*
	@RequestMapping(value="bindSubject.json",method =RequestMethod.POST)
	@Transactional
	public ErrorInfo<ExamDo> bindSubject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<ExamDo> errorInfo = new ErrorInfo<>();
        try{
            long examId =RequestUtil.getLong("idfirst", request);
            String ids =RequestUtil.getString("idsecond", request);
           
                LOGGER.info("需要绑定的id=" + ids);
                examSubjectService.save(examId,ids);
                errorInfo.setSuccess(true);
                errorInfo.setInput(examId+"");
                errorInfo.setErrorinfo("恭喜您，绑定试题成功");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，绑定试题信息失败，原因:"+ex.getMessage());
            LOGGER.error("绑定试题信息失败:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	*/
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#save(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value="bindSubject.json",method =RequestMethod.POST)
	@Transactional
	public ErrorInfo<ExamDo> bindSubject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<ExamDo> errorInfo = new ErrorInfo<>();
        try{
        	int score=RequestUtil.getInt("score", request);
            long examId =RequestUtil.getLong("examId", request);
            long subjectId =RequestUtil.getLong("subjectId", request);
            LOGGER.info("需要绑定的subjectId=" + subjectId);
            examSubjectService.saveSubject(examId,subjectId,score);
            errorInfo.setSuccess(true);
            errorInfo.setInput(examId+"");
            errorInfo.setErrorinfo("恭喜您，绑定试题成功");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，绑定试题信息失败，原因:"+ex.getMessage());
            LOGGER.error("绑定试题信息失败:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	

	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#save(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value="cancelBind.json",method =RequestMethod.POST)
	@Transactional
	public ErrorInfo<ExamDo> cancelBind(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<ExamDo> errorInfo = new ErrorInfo<>();
        try{
        	long examId =RequestUtil.getLong("idfirst", request);
            long subjectId =RequestUtil.getLong("idsecond", request);
           
            Map<String, Object> itemsMap=new HashMap<String ,Object>();
            itemsMap.put("examid", examId);
            itemsMap.put("subjectid", subjectId);
            List<ExamSubjectDo>   examSubjectList=examSubjectService.getByItems(itemsMap);
            
            if(examSubjectList!=null&&examSubjectList.size()==1) {
            	examSubjectService.delete(examId,examSubjectList.get(0).getId());
            	errorInfo.setSuccess(true);
            	errorInfo.setInput(examId+"");
                errorInfo.setErrorinfo("恭喜您，解除绑定试题成功");
            }else{
            	errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，解除绑定试题失败");
            }
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，解除绑定试题失败，原因:"+ex.getMessage());
            LOGGER.error("解除绑定试题失败:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
}