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
import java.util.ArrayList;
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
import cn.javaworker.yeming.pojo.ErrorInfo;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.ukeli.ConstantUkeli;
import cn.javaworker.yeming.ukeli.pojo.CupSubjectDo;
import cn.javaworker.yeming.ukeli.pojo.ExamSubjectDo;
import cn.javaworker.yeming.ukeli.pojo.SubjectDo;
import cn.javaworker.yeming.ukeli.service.CupSubjectService;
import cn.javaworker.yeming.ukeli.service.ExamSubjectService;
import cn.javaworker.yeming.ukeli.service.SubjectService;
import cn.javaworker.yeming.ukeli.service.SubjectVtypeService;
import cn.javaworker.yeming.ukeli.service.impl.SubjectOptionsServiceImpl;

/**
 * 创建日期：2017-09-04 11:03:02 开发者：叶明(MSN:guming123416@hotmail.com,QQ:47043760)
 * 修改者： 修改时间： 程序作用： 1、 2、 修改说明： 1、 2、 版本：@version 0.1
 * 
 * @author 叶明
 */
@RestController
@RequestMapping("/subject/")
public class SubjectRestController implements RestBaseController<SubjectDo> {

	private final static Logger LOGGER = LogManager.getLogger(SubjectRestController.class);
	private final static int SINGALINDEX = 1;
	private final static int MOREINDEX = 2;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private SubjectVtypeService subjectVtypeService;
	@Autowired
	private ClazzService clazzService;
	@Autowired
	private ExamSubjectService examSubjectService;
	@Autowired
	private CupSubjectService cupSubjectService;
	@Autowired
	private SubjectOptionsServiceImpl subjectOptionsServiceImpl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#del(
	 * java.lang.String, javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "del.json", method = RequestMethod.POST)
	public ErrorInfo<SubjectDo> del(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<SubjectDo> errorInfo = new ErrorInfo<>();
		try {
			LOGGER.info("需要删除的题目id=" + ids);
			String[] str = StringUtils.split(ids, ",");
			subjectService.delByIds(str);
			errorInfo.setSuccess(true);
			errorInfo.setErrorinfo("删除成功");
		} catch (Exception ex) {
			errorInfo.setErrorinfo("对不起，删除题目失败，原因:" + ex.getMessage());
			LOGGER.error("删除一个题目发生错误:" + ex.getMessage());
			LOGGER.debug(ex);
		}
		return errorInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#list(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "list.json", method = RequestMethod.POST)
	public Page<SubjectDo> list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<SubjectDo> page = null;
		try {
			long start = RequestUtil.getLong("start", request);
			int limit = RequestUtil.getInt("limit", request);
			int pageVal = RequestUtil.getInt("page", request);
			if (limit < Constant.SMALL_PAGE_SIZE) {
				limit = 10;
			}
			if (start < 0) {
				start = 0;
			} else {
				start = (pageVal - 1) * limit;
			}
			short status = RequestUtil.getShort("status", (short) -1, request);
			short slock = RequestUtil.getShort("slock", (short) -1, request);
			int stype = RequestUtil.getInt("stype", request);
			String name = RequestUtil.getString("name", request);
			int vtype = RequestUtil.getInt("vtype", request);
			String reservationtime = RequestUtil.getString("reservationtime", request);
			Date startTime = null;
			Date endTime = null;
			if (StringUtils.isNoneBlank(reservationtime)) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				startTime = df.parse(reservationtime.split("-")[0].replace("/", "-"));
				endTime = df.parse(reservationtime.split("-")[1].replace("/", "-"));
			}
			LOGGER.info("获取题目stype="+stype+",name="+name+",startTime="+startTime+",endTime="+endTime+",status=" + status + ",name=" + name + ",vtype=" + vtype + ",start=" + start + ",limit=" + limit);
			
			page = subjectService.getPage(startTime,endTime,name,stype,vtype, status, slock, start, limit);
			if (null != page) {
				List<SubjectDo> list = page.getList();
				if (null != list) {
					List<Object> ids = new ArrayList<>();
					for (SubjectDo subject : list) {
						ids.add(subject.getId());
					}
					Map<Long, ClazzDo> clazzMap = clazzService.getMapByClazzTypeid(ConstantUkeli.SUBJECTCLAZZTYPEID, ConstantUkeli.ONLINE_STATUS);
					Map<Long,String> vtypeMap = subjectVtypeService.getMapBySubjectids(ids);
					for (SubjectDo subject : list) {
						ClazzDo clazz  = clazzMap.get((long)subject.getStype());
						if(null != clazz) {
							subject.setExt_stype(clazz.getName());
						}
						String vtypes = vtypeMap.get(subject.getId());
						if(StrUtils.isNoBlank(vtypes)) {
							subject.setExt_vtype(vtypes);
						}
						
					}
				}
			}
		} catch (Exception ex) {
			LOGGER.error("获取题目分页发生错误:" + ex.getMessage());
			LOGGER.debug(ex);
		}
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#list(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value = "listbyexam.json", method = RequestMethod.POST)
	public Page<SubjectDo> listByExam(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<SubjectDo> page = null;
		try {
			long start = RequestUtil.getLong("start", request);
			int limit = RequestUtil.getInt("limit", request);
			int pageVal = RequestUtil.getInt("page", request);
			long examId = RequestUtil.getInt("examId", request);
			if (limit < Constant.SMALL_PAGE_SIZE) {
				limit = 200;
			}
			if (start < 0) {
				start = 0;
			} else {
				start = (pageVal - 1) * limit;
			}
			short status = RequestUtil.getShort("status", (short) -1, request);
			short slock = RequestUtil.getShort("slock", (short) -1, request);
			LOGGER.info("获取商品属性status=" + status + ",start=" + start + ",limit=" + limit + ",examId=" + examId);
			page = subjectService.getSubList(ConstantUkeli.SEARCHSUBJECTBYEXAM,examId, status, slock, start, limit);
			if (null != page) {
				List<SubjectDo> list = page.getList();
				if (null != list) {
					Map<Long, ClazzDo> clazzMap = clazzService.getMapByClazzTypeid(ConstantUkeli.SUBJECTCLAZZTYPEID, ConstantUkeli.ONLINE_STATUS);
					for (SubjectDo subject : list) {
						ClazzDo clazz  = clazzMap.get((long)subject.getStype());
						if(null != clazz) {
							subject.setExt_stype(clazz.getName());
						}
					}
				}
			}
		} catch (Exception ex) {
			LOGGER.error("获取考试试题发生错误:" + ex.getMessage());
			LOGGER.debug(ex);
		}
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#list(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value = "listbycup.json", method = RequestMethod.POST)
	public Page<SubjectDo> listByCup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<SubjectDo> page = null;
		try {
			long start = RequestUtil.getLong("start", request);
			int limit = RequestUtil.getInt("limit", request);
			int pageVal = RequestUtil.getInt("page", request);
			long cupId = RequestUtil.getInt("cupId", request);
			if (limit < Constant.SMALL_PAGE_SIZE) {
				limit = 200;
			}
			if (start < 0) {
				start = 0;
			} else {
				start = (pageVal - 1) * limit;
			}
			short status = RequestUtil.getShort("status", (short) -1, request);
			short slock = RequestUtil.getShort("slock", (short) -1, request);
			LOGGER.info("获取商品属性status=" + status + ",start=" + start + ",limit=" + limit + ",cupId=" + cupId);
			page = subjectService.getSubList(ConstantUkeli.SEARCHSUBJECTBYCUP,cupId, status, slock, start, limit);
			if (null != page) {
				List<SubjectDo> list = page.getList();
				if (null != list) {
					List<ClazzDo> clazzDolist = clazzService.getListByTypeid(ConstantUkeli.SUBJECTCLAZZTYPEID, ConstantUkeli.ONLINE_STATUS);
					for (SubjectDo subject : list) {
						for (ClazzDo clazz : clazzDolist) {
							if (subject.getStype() == clazz.getId()) {
								subject.setExt_stype(clazz.getName());
								break;
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			LOGGER.error("获取考试试题发生错误:" + ex.getMessage());
			LOGGER.debug(ex);
		}
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#list(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value = "listbyknow.json", method = RequestMethod.POST)
	public Page<SubjectDo> listByKnow(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<SubjectDo> page = null;
		try {
			long start = RequestUtil.getLong("start", request);
			int limit = RequestUtil.getInt("limit", request);
			int pageVal = RequestUtil.getInt("page", request);
			long knowledgeId = RequestUtil.getInt("knowledgeId", request);
			if (limit < Constant.SMALL_PAGE_SIZE) {
				limit = 1000;
			}
			if (start < 0) {
				start = 0;
			} else {
				start = (pageVal - 1) * limit;
			}
			short status = RequestUtil.getShort("status", (short) -1, request);
			short slock = RequestUtil.getShort("slock", (short) -1, request);
			LOGGER.info("获取商品属性status=" + status + ",start=" + start + ",limit=" + limit + ",knowledgeId=" + knowledgeId);
			page = subjectService.getSubList(ConstantUkeli.SEARCHSUBJECTBYKNOW,knowledgeId, status, slock, start, limit);
			if (null != page) {
				List<SubjectDo> list = page.getList();
				if (null != list) {
					Map<Long, ClazzDo> clazzMap = clazzService.getMapByClazzTypeid(ConstantUkeli.SUBJECTCLAZZTYPEID, ConstantUkeli.ONLINE_STATUS);
					for (SubjectDo subject : list) {
						ClazzDo clazz  = clazzMap.get((long)subject.getStype());
						if(null != clazz) {
							subject.setExt_stype(clazz.getName());
						}
					}
				}
			}
		} catch (Exception ex) {
			LOGGER.error("获取考试试题发生错误:" + ex.getMessage());
			LOGGER.debug(ex);
		}
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#list(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value = "bindSubList.json", method = RequestMethod.POST)
	public Page<SubjectDo> listForBind(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<SubjectDo> page = null;
		try {
			long start = RequestUtil.getLong("start", request);
			int limit = RequestUtil.getInt("limit", request);
			int pageVal = RequestUtil.getInt("page", request);
			if (limit < Constant.SMALL_PAGE_SIZE) {
				limit = Constant.SMALL_PAGE_SIZE;
			}
			if (start < 0) {
				start = 0;
			} else {
				start = (pageVal - 1) * limit;
			}

			short status = RequestUtil.getShort("status", (short) -1, request);
			short slock = RequestUtil.getShort("slock", (short) -1, request);
			String keyword = RequestUtil.getString("keywordForBind", request);
			long examId = RequestUtil.getLong("examId", request);
			long cupId = RequestUtil.getLong("cupId", request);
			LOGGER.info("获取题目属性status=" + status + ",关键字=" + keyword + ",start=" + start + ",limit=" + limit + ",examId=" + examId
					+ ",cupId=" + cupId);
			page = subjectService.getBindPage(keyword, status, slock, start, limit, examId, cupId);
			if (null != page) {
				List<SubjectDo> subjectList = page.getList();
				if (null != subjectList) {
					Map<Long, ClazzDo> clazzMap = clazzService.getMapByClazzTypeid(ConstantUkeli.SUBJECTCLAZZTYPEID, ConstantUkeli.ONLINE_STATUS);
					List<CupSubjectDo> cupSubjectList = null;
					List<ExamSubjectDo> examSubjectList = null;

					if (cupId > 0) {
						Map<String, Object> itemsMap = new HashMap<String, Object>();
						itemsMap.put("cupid", cupId);
						cupSubjectList = cupSubjectService.getByItems(itemsMap);
					} else if (examId > 0) {
						Map<String, Object> itemsMap = new HashMap<String, Object>();
						itemsMap.put("examid", examId);
						examSubjectList = examSubjectService.getByItems(itemsMap);
					}

					for (SubjectDo subject : subjectList) {
						ClazzDo clazz  = clazzMap.get((long)subject.getStype());
						if(null != clazz) {
							subject.setExt_stype(clazz.getName());
						}
						if (cupId > 0) {
							for (CupSubjectDo cupSubject : cupSubjectList) {
								if (subject.getId() == cupSubject.getSubjectid()) {
									subject.setExt_isbind("y");
									break;
								}
							}
						} else if (examId > 0) {
							for (ExamSubjectDo examSubject : examSubjectList) {
								if (subject.getId() == examSubject.getSubjectid()) {
									subject.setExt_isbind("y");
									break;
								}
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			LOGGER.error("获取商品属性分页发生错误:" + ex.getMessage());
			LOGGER.debug(ex);
		}
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#list(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value = "bindSubListForKnowledge.json", method = RequestMethod.POST)
	public Page<SubjectDo> listForBindKnowledge(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<SubjectDo> page = null;
		try {
			long start = RequestUtil.getLong("start", request);
			int limit = RequestUtil.getInt("limit", request);
			int pageVal = RequestUtil.getInt("page", request);
			if (limit < Constant.SMALL_PAGE_SIZE) {
				limit = Constant.SMALL_PAGE_SIZE;
			}
			if (start < 0) {
				start = 0;
			} else {
				start = (pageVal - 1) * limit;
			}

			short status = RequestUtil.getShort("status", (short) -1, request);
			short slock = RequestUtil.getShort("slock", (short) -1, request);
			String keyword = RequestUtil.getString("keywordForBind", request);
			int vtype = RequestUtil.getInt("vtype", request);
			long knowledgeId = RequestUtil.getLong("knowledgeId", request);
			LOGGER.info(
					"获取题目属性status=" + status + ",关键字=" + keyword + ",start=" + start + ",limit=" + limit + ",knowledgeId=" + knowledgeId);
			page = subjectService.getBindPageForKnowledge(keyword, vtype, status, slock, start, limit, knowledgeId);
			if (null != page) {
				List<SubjectDo> subjectList = page.getList();
				if (null != subjectList) {
					List<Object> ids = new ArrayList<>();
					for (SubjectDo subject : subjectList) {
						ids.add(subject.getId());
					}
					Map<Long,String> vtypeMap = subjectVtypeService.getMapBySubjectids(ids);
					Map<Long, ClazzDo> clazzMap = clazzService.getMapByClazzTypeid(ConstantUkeli.SUBJECTCLAZZTYPEID, ConstantUkeli.ONLINE_STATUS);
					for (SubjectDo subject : subjectList) {
						ClazzDo clazz  = clazzMap.get((long)subject.getStype());
						if(null != clazz) {
							subject.setExt_stype(clazz.getName());
						}
						String vtypes = vtypeMap.get(subject.getId());
						if(StrUtils.isNoBlank(vtypes)) {
							subject.setExt_vtype(vtypes);
						}
					}
				}
			}
		} catch (Exception ex) {
			LOGGER.error("获取题目分页发生错误:" + ex.getMessage());
			LOGGER.debug(ex);
		}
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.core.base.controller.inspinia.BaseController#save(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "save.json", method = RequestMethod.POST)
	public ErrorInfo<SubjectDo> save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<SubjectDo> errorInfo = new ErrorInfo<>();
		try {
			long id = RequestUtil.getLong("id", request);
			long adminid = CookiesUtil.getLong3Des(Constant.ADMIN_COOKIES_ID, request);
			String adminName = CookiesUtil.getString3Des(Constant.ADMIN_COOKIES_NAME, request);
			String vtypes = RequestUtil.getString("vtype", request);
			int stype = RequestUtil.getInt("stype", request);
			if (id > 0) {
				Map<String, Object> map = RequestUtil.getParams(request);
				map.put("vtypes", vtypes);
				map.put("editer", adminName);
				map.put("editerid", adminid);
				map.remove("file");
				subjectService.update(map, id);
				if (stype == SINGALINDEX || stype == MOREINDEX) {
					errorInfo.setInput(id + "");
				}
				errorInfo.setSuccess(true);
				errorInfo.setErrorinfo("恭喜您，更新成功");
			} else {
				SubjectDo subjectDo = RequestUtil.form(SubjectDo.class, request);
				if (null != subjectDo) {
					subjectDo.setEditer(vtypes);
					subjectDo.setAdderid(adminid);
					subjectDo.setAdder(adminName);
					Long subjectid = subjectService.save(subjectDo);
					if (stype == SINGALINDEX || stype == MOREINDEX) {
						errorInfo.setInput(subjectid + "");
					}
					errorInfo.setSuccess(true);
					errorInfo.setErrorinfo("恭喜您，保存题目成功");
				} else {
					errorInfo.setSuccess(false);
					errorInfo.setErrorinfo("对不起，参数错误，请检查");
				}
			}
		} catch (Exception ex) {
			errorInfo.setErrorinfo("对不起，新增一个题目信息失败，原因:" + ex.getMessage());
			LOGGER.error("新增一个题目信息发生错误:" + ex.getMessage());
			LOGGER.debug(ex);
		}
		return errorInfo;
	}

	@RequestMapping(value = "saveForOption.json", method = RequestMethod.POST)
	public ErrorInfo<SubjectDo> saveForOption(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<SubjectDo> errorInfo = new ErrorInfo<>();
		try {
			long subjectid = RequestUtil.getLong("subjectid", request);
			long adminid = CookiesUtil.getLong3Des(Constant.ADMIN_COOKIES_ID, request);
			String adminName = CookiesUtil.getString3Des(Constant.ADMIN_COOKIES_NAME, request);
			if (subjectid > 0) {
				Map<String, Object> map = RequestUtil.getParams(request);
				map.put("editer", adminName);
				map.put("editerid", adminid);
				map.remove("file");
				Map<String, Object> mapSubjectOptions = dealNextMap(map);
				subjectOptionsServiceImpl.saveSubjectOptions(mapSubjectOptions, subjectid, map.get("answer").toString());
				errorInfo.setSuccess(true);
				errorInfo.setErrorinfo("恭喜您，更新成功");
			}
		} catch (Exception ex) {
			errorInfo.setErrorinfo("对不起，新增一个题目答案选项信息失败，原因:" + ex.getMessage());
			LOGGER.error("新增一个题目答案选项信息发生错误:" + ex.getMessage());
			LOGGER.debug(ex);
		}
		return errorInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.core.base.controller.inspinia.BaseController#status(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "status.json", method = RequestMethod.POST)
	public ErrorInfo<SubjectDo> status(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<SubjectDo> errorInfo = new ErrorInfo<>();
		try {
			long id = RequestUtil.getLong("id", request);
			int status = RequestUtil.getInt("status", request);
			int type = RequestUtil.getInt("type", request);
			if (id < 1 || (status != 0 && status != 1)) {
				errorInfo.setSuccess(false);
				errorInfo.setErrorinfo("对不起，参数错误!");
				return errorInfo;
			}
			if (type == 0) {
				subjectService.updateByItem("status", status, id);
				errorInfo.setSuccess(true);
				errorInfo.setErrorinfo("更新状态成功");
			} else {
				subjectService.updateByItem("auditstatus", status, id);
				errorInfo.setSuccess(true);
				errorInfo.setErrorinfo("更改审核状态成功");
			}
		} catch (Exception ex) {
			errorInfo.setErrorinfo("对不起，更新题目状态失败，原因:" + ex.getMessage());
			LOGGER.error("更新题目状态失败发生错误:" + ex.getMessage());
			LOGGER.debug(ex);
		}
		return errorInfo;
	}

	@RequestMapping(value = "order.json", method = RequestMethod.POST)
	@ResponseBody
	public ErrorInfo<SubjectDo> order(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<SubjectDo> errorInfo = new ErrorInfo<>();
		try {
			long id = RequestUtil.getLong("id", request);
			int type = RequestUtil.getInt("type", request);
			if (id < 1 || type < 0) {
				errorInfo.setSuccess(false);
				errorInfo.setErrorinfo("对不起，参数错误!");
				return errorInfo;
			}
			SubjectDo subjectDo = subjectService.get(id);
			if (null == subjectDo) {
				errorInfo.setSuccess(false);
				errorInfo.setErrorinfo("对不起，参数错误!");
				return errorInfo;
			}
			SubjectDo vsubjectDo = subjectService.getByOrderType(subjectDo.getIorder(), type);
			if (null == vsubjectDo) {
				if (type == 1) {
					errorInfo.setSuccess(false);
					errorInfo.setErrorinfo("对不起，已经处理最顶层了");
				} else {
					errorInfo.setSuccess(false);
					errorInfo.setErrorinfo("对不起，已经处理最底层了");
				}
				return errorInfo;
			}
			if (null != subjectDo && null != vsubjectDo) {
				subjectService.order(subjectDo, vsubjectDo);
			}
			errorInfo.setSuccess(true);
			errorInfo.setErrorinfo("排序成功!");
		} catch (Exception ex) {
			errorInfo.setErrorinfo("对不起，更新题目排序失败，原因:" + ex.getMessage());
			LOGGER.error("更新题目排序失败发生错误:" + ex.getMessage());
			LOGGER.debug(ex);
		}
		return errorInfo;
	}

	

	private Map<String, Object> dealNextMap(Map<String, Object> oldMap) {
		Map<String, Object> newMap = new HashMap<String, Object>();
		for (int i = 1; i <= 10; i++) {
			if (null != oldMap.get("optiionname_none_" + i) && StringUtils.isNoneBlank(oldMap.get("optiionname_none_" + i).toString())) {
				newMap.put((String) oldMap.get("optiionname_none_" + i), oldMap.get("optioncontext_none_" + i));
			}
		}
		for (int i = 0; i <= 10; i++) {
			if (null != oldMap.get("optiionname_" + i) && StringUtils.isNoneBlank(oldMap.get("optiionname_" + i).toString())) {
				newMap.put((String) oldMap.get("optiionname_" + i), oldMap.get("optioncontext_" + i));
			}
		}
		return newMap;

	}
	


}