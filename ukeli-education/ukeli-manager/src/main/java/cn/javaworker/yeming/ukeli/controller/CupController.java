/**
* <p> * Title: 商城管理信息系统 *</p>
* <p> * Description: * </p>
* <p> * Copyright: Copyright (c) 2012-2017* </p>
* <p> * Company: 苏州明翔信息科技有限公司 * </p>
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.javaworker.yeming.core.base.controller.WebBaseController;
import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.manager.controller.BaseControllerImpl;
import cn.javaworker.yeming.manager.pojo.ClazzDo;
import cn.javaworker.yeming.manager.service.ClazzService;
import cn.javaworker.yeming.ukeli.ConstantUkeli;
import cn.javaworker.yeming.ukeli.pojo.CupDo;
import cn.javaworker.yeming.ukeli.pojo.SubjectDo;
import cn.javaworker.yeming.ukeli.service.CupService;
import cn.javaworker.yeming.ukeli.service.SubjectService;

/**
* 创建日期：2017-09-08 14:05:56
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
@RequestMapping("/cup/")
public class CupController  extends BaseControllerImpl implements WebBaseController<CupDo> {

	private final static Logger LOGGER =LogManager.getLogger(CupController.class);
	private final static int GRADECLAZZTYPEID=3;
	private final static int CUPTYPECLAZZTYPEID=5;
	@Autowired
	private CupService cupService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private ClazzService clazzService;
	
    
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#page(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "page.html",method=RequestMethod.GET)
	public String page(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		setting(map,request,response);
		setItems(map, request, response);
		List<ClazzDo> clazzDoList =clazzService.getListByTypeid(ConstantUkeli.CUPTYPECLAZZTYPEID,ConstantUkeli.ONLINE_STATUS);//查询所有杯赛类型
        map.put("clazzDoList", clazzDoList);
		return "/cup/page";
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#edit(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "edit.html",method=RequestMethod.POST)
	public String edit(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong("id",request);
		LOGGER.info("需要修改密码的权限编号:"+id);
        boolean edit =false;
        CupDo cup =new CupDo();
        if(id > 0){
            cup = cupService.get(id);
            if(null == cup){
                cup = new CupDo();
            }else{
                edit=true;
            }
        }
        List<ClazzDo> clazzDoList =clazzService.getListByTypeid(GRADECLAZZTYPEID,ConstantUkeli.ONLINE_STATUS);//查询所有年级
        List<ClazzDo> clazzDoListByClazzTypeid =clazzService.getListByTypeid(CUPTYPECLAZZTYPEID, ConstantUkeli.ONLINE_STATUS);//查询所有杯赛类型
        map.put("cup",cup );
        map.put("clazzDoList", clazzDoList);
        map.put("clazzDoListByClazzTypeid", clazzDoListByClazzTypeid);
        map.put("edit",edit);
		setting(map,request,response);
        return "/cup/edit";
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#show(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "show.html", method = RequestMethod.POST)
	public String show(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong("id", request);
		CupDo cup = cupService.get(id);
		List<ClazzDo> clazzDoList =clazzService.getListByTypeid(GRADECLAZZTYPEID,ConstantUkeli.ONLINE_STATUS);//查询所有年纪
		List<ClazzDo> clazzDoListByClazzTypeid =clazzService.getListByTypeid(CUPTYPECLAZZTYPEID, ConstantUkeli.ONLINE_STATUS);//查询所有杯赛类型
		map.put("clazzDoList", clazzDoList);
		map.put("clazzDoListByClazzTypeid", clazzDoListByClazzTypeid);
		map.put("cup",cup );
		setting(map, request, response);
		return "/cup/show";
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#edit(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value = "bindDialog.html",method=RequestMethod.POST)
	public String addAttr(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long cupId = RequestUtil.getLong("id",request);
		LOGGER.info("需要绑定题目的杯赛编号:"+cupId);
        map.put("cupId",cupId );
		setting(map,request,response);
        return "/cup/bind";
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#edit(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value = "bindAddScore.html",method=RequestMethod.POST)
	public String AddScore(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long cupId = RequestUtil.getLong("cupId",request);
		Long subjectId=RequestUtil.getLong("subjectId", request);
		LOGGER.info("需要绑定编号:cupId="+cupId+"  subjectId="+subjectId);
		SubjectDo subject=subjectService.get(subjectId);
        map.put("cupId",cupId);
        map.put("subject",subject);
		setting(map,request,response);
        return "/cup/addscore";
	}
    
    
}