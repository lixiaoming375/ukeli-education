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
import cn.javaworker.yeming.ukeli.pojo.KnowledgeDo;
import cn.javaworker.yeming.ukeli.pojo.KnowledgeSubjectDo;
import cn.javaworker.yeming.ukeli.service.KnowledgeService;
import cn.javaworker.yeming.ukeli.service.KnowledgeSubjectService;


@RestController
@RequestMapping("/knowledge/")
public class KnowledgeRestController implements RestBaseController<KnowledgeDo>{

	private final static Logger LOGGER =LogManager.getLogger(KnowledgeRestController.class);
	
	@Autowired
	private KnowledgeService knowledgeService;
	@Autowired
	private ClazzService clazzService;
	@Autowired
	private KnowledgeSubjectService knowledgeSubjectService;
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#del(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="del.json",method =RequestMethod.POST)
	public ErrorInfo<KnowledgeDo> del(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<KnowledgeDo> errorInfo = new ErrorInfo<>();
        try{
            LOGGER.info("需要删除的知识点id=" + ids);
            String[] str = StringUtils.split(ids, ",");
            knowledgeService.delByIds(str);
            errorInfo.setSuccess(true);
            errorInfo.setErrorinfo("删除成功");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，删除知识点失败，原因:"+ex.getMessage());
            LOGGER.error("删除一个知识点发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#list(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="list.json",method =RequestMethod.POST)
	public Page<KnowledgeDo> list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<KnowledgeDo> page = null;
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
            short status =RequestUtil.getShort("status",request);
            short slock =RequestUtil.getShort("slock",(short)-1, request);
            String keyword = RequestUtil.getString("keyword",request);
            Long typeid = RequestUtil.getLong("typeid",request);
            LOGGER.info("获取知识点status=" + status + ",关键字="+keyword + ",typeid=" + typeid+ ",start=" + start +",limit=" + limit);
            Map<String, Object> kaywordMap=new HashMap<String,Object>();
            kaywordMap.put("nodename",keyword);
            kaywordMap.put("typeid",typeid);
            page = knowledgeService.getPage(kaywordMap,status,slock,start,limit);
            if(null != page){
            	List<KnowledgeDo> list = page.getList();
            	if(null != list){
            		//clazzService
            		List<ClazzDo> clazzList=clazzService.getListByTypeid(2,ConstantUkeli.ONLINE_STATUS);
            		List<ClazzDo> clazzListForGrade=clazzService.getListByTypeid(3,ConstantUkeli.ONLINE_STATUS);
            		clazzList.addAll(clazzListForGrade);
            		for (KnowledgeDo knowledgeDo : list) {
            			for(ClazzDo clazzDo : clazzList){
            				if(knowledgeDo.getTypeid()==clazzDo.getId()){
            					knowledgeDo.setExt_knowtype(clazzDo.getName());
                			}
            				if(knowledgeDo.getGradeid()==clazzDo.getId()){
            					knowledgeDo.setExt_grade(clazzDo.getName());
            					break;
            				}
            			}
					}
            	}
            }
        }catch (Exception ex){
            LOGGER.error("获取知识点分页发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return page;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#save(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="save.json",method =RequestMethod.POST)
	public ErrorInfo<KnowledgeDo> save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<KnowledgeDo> errorInfo = new ErrorInfo<>();
        try{
            long id =RequestUtil.getLong("id", request);
            long adminid =CookiesUtil.getLong3Des(Constant.ADMIN_COOKIES_ID, request);
            String adminName =CookiesUtil.getString3Des(Constant.ADMIN_COOKIES_NAME, request);
            if(id > 0) {
            	Map<String, Object> map= RequestUtil.getParams(request);
            	map.put("editer", adminName);
            	map.put("editerid", adminid);
            	map.remove("file");
            	knowledgeService.update(map, id);
            	//errorInfo.setFlushType(1);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("恭喜您，更新成功");
            }else {
            	KnowledgeDo knowledgeDo = RequestUtil.form(KnowledgeDo.class,request);
            	if(null !=knowledgeDo) {
            		knowledgeDo.setAdderid(adminid);
            		knowledgeDo.setAdder(adminName);
            		knowledgeService.save(knowledgeDo);
                    errorInfo.setSuccess(true);
                    errorInfo.setErrorinfo("恭喜您，保存知识点成功");
            	}else {
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，参数错误，请检查");
            	}
            }
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，新增一个知识点信息失败，原因:"+ex.getMessage());
            LOGGER.error("新增一个知识点信息发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#status(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="status.json",method =RequestMethod.POST)
	public ErrorInfo<KnowledgeDo> status(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<KnowledgeDo> errorInfo = new ErrorInfo<>();
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
            	knowledgeService.updateByItem("status", status, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("更新状态成功");
			}else {
				knowledgeService.updateByItem("auditstatus", status, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("更改审核状态成功");
			}
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，更新知识点状态失败，原因:"+ex.getMessage());
            LOGGER.error("更新知识点状态失败发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	
	@RequestMapping(value="order.json",method =RequestMethod.POST)
	@ResponseBody
	public ErrorInfo<KnowledgeDo> order(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<KnowledgeDo> errorInfo = new ErrorInfo<>();
        try{
        	long id =RequestUtil.getLong("id", request);
        	int type =RequestUtil.getInt("type", request);
        	if (id < 1 || type < 0) {
            	errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
			}
        	KnowledgeDo KnowledgeDo = knowledgeService.get(id);
        	if(null ==KnowledgeDo){
        		errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
        	}
        	KnowledgeDo vKnowledgeDo = knowledgeService.getByOrderType(KnowledgeDo.getIorder(),type);
        	if(null == vKnowledgeDo){
        		if(type ==1){
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最顶层了");
        		}else{
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最底层了");
        		}
        		return errorInfo;
        	}
        	if(null != KnowledgeDo && null != vKnowledgeDo){
        		knowledgeService.order(KnowledgeDo,vKnowledgeDo);
        	}
        	errorInfo.setSuccess(true);
        	errorInfo.setErrorinfo("排序成功!");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，更新知识点排序失败，原因:"+ex.getMessage());
            LOGGER.error("更新知识点排序失败发生错误:"+ex.getMessage());
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
	public ErrorInfo<KnowledgeDo> orderserial(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<KnowledgeDo> errorInfo = new ErrorInfo<>();
        try{
        	long id =RequestUtil.getLong("id", request);
        	Long knowledgeId=RequestUtil.getLong("knowledgeId", request);
        	int type =RequestUtil.getInt("type", request);
        	if (id < 1 || type < 0) {
            	errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
			}
        	Map<String, Object> itemsMap=new HashMap<String, Object>();
        	itemsMap.put("subjectid", id);
        	itemsMap.put("knowledgeid", knowledgeId);
        	KnowledgeSubjectDo knowledgeSubjectDo=null;
        	List<KnowledgeSubjectDo>	knowledgeSubjectList=knowledgeSubjectService.getByItems(itemsMap);
        	if(null ==knowledgeSubjectList||knowledgeSubjectList.size()!=1){
        		errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
        	}else{
        		knowledgeSubjectDo=knowledgeSubjectList.get(0);
        	}
        	KnowledgeSubjectDo  vKnowledgeSubjectDo = knowledgeSubjectService.getByOrderSerialType(knowledgeSubjectDo.getSerial(),knowledgeSubjectDo.getKnowledgeid(), type);
        	//KnowledgeDo vKnowledgeDo = knowledgeService.getByOrderType(KnowledgeDo.getIorder(),type);
        	if(null == vKnowledgeSubjectDo){
        		if(type ==1){
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最顶层了");
        		}else{
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最底层了");
        		}
        		return errorInfo;
        	}
        	if(null != knowledgeSubjectDo && null != vKnowledgeSubjectDo){
        		knowledgeSubjectService.orderSerial(knowledgeSubjectDo, vKnowledgeSubjectDo);
        	}
        	errorInfo.setInput(knowledgeId+"");
        	errorInfo.setSuccess(true);
        	errorInfo.setErrorinfo("排序成功!");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，更新知识点排序失败，原因:"+ex.getMessage());
            LOGGER.error("更新知识点排序失败发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}

//	/* (non-Javadoc)
//	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#save(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
//	 */
//	@RequestMapping(value="bindSubject.json",method =RequestMethod.POST)
//	@Transactional
//	public ErrorInfo<KnowledgeDo> bindSubject(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ErrorInfo<KnowledgeDo> errorInfo = new ErrorInfo<>();
//        try{
//            long knowledgeId =RequestUtil.getLong("idfirst", request);
//            String ids =RequestUtil.getString("idsecond", request);
//           
//                LOGGER.info("需要绑定的id=" + ids);
//                knowledgeSubjectService.save(knowledgeId,ids);
//                errorInfo.setSuccess(true);
//                errorInfo.setInput(knowledgeId+"");
//                errorInfo.setErrorinfo("恭喜您，绑定试题成功");
//        }catch (Exception ex){
//            errorInfo.setErrorinfo("对不起，绑定试题信息失败，原因:"+ex.getMessage());
//            LOGGER.error("绑定试题信息失败:"+ex.getMessage());
//            LOGGER.debug(ex);
//        }
//        return errorInfo;
//	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#save(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value="bindSubject.json",method =RequestMethod.POST)
	@Transactional
	public ErrorInfo<ExamDo> bindSubject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<ExamDo> errorInfo = new ErrorInfo<>();
        try{
        	int score=RequestUtil.getInt("score", request);
        	long knowledgeId =0;
        	long subjectId =0;
        	if(score>0){
        		 knowledgeId =RequestUtil.getLong("knowledgeId", request);
                 subjectId =RequestUtil.getLong("subjectId", request);
        	}else{
        		 knowledgeId =RequestUtil.getLong("idfirst", request);
                 subjectId =RequestUtil.getLong("idsecond", request);
        	}
            LOGGER.info("需要绑定的subjectId=" + subjectId);
            knowledgeSubjectService.saveSubject(knowledgeId,subjectId,score);
            errorInfo.setSuccess(true);
            errorInfo.setInput(knowledgeId+"");
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
	public ErrorInfo<KnowledgeDo> cancelBind(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<KnowledgeDo> errorInfo = new ErrorInfo<>();
        try{
        	long knowledgeId =RequestUtil.getLong("idfirst", request);
            long subjectId =RequestUtil.getLong("idsecond", request);
           
            Map<String, Object> itemsMap=new HashMap<String ,Object>();
            itemsMap.put("knowledgeid", knowledgeId);
            itemsMap.put("subjectid", subjectId);
            List<KnowledgeSubjectDo>   knowledgSubjectList=knowledgeSubjectService.getByItems(itemsMap);
            
            if(knowledgSubjectList!=null&&knowledgSubjectList.size()==1) {
            	knowledgeSubjectService.delete(knowledgeId,knowledgSubjectList.get(0).getId());
            	errorInfo.setSuccess(true);
            	errorInfo.setInput(knowledgeId+"");
            	errorInfo.setIreturn(9);
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
