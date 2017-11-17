/**
* <p> * Title: 商城管理信息系统 *</p>
* <p> * Description: * </p>
* <p> * Copyright: Copyright (c) 2012-2017* </p>
* <p> * Company: 苏州明翔信息科技有限公司 * </p>
* @author 叶明（开发）
* @version 0.1
*/
package cn.javaworker.yeming.ukeli.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.javaworker.yeming.core.base.controller.WebBaseController;
import cn.javaworker.yeming.core.util.Encrypt;
import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.manager.controller.BaseControllerImpl;
import cn.javaworker.yeming.manager.pojo.ClazzDo;
import cn.javaworker.yeming.manager.service.ClazzService;
import cn.javaworker.yeming.ukeli.ConstantUkeli;
import cn.javaworker.yeming.ukeli.pojo.SubjectDo;
import cn.javaworker.yeming.ukeli.pojo.SubjectOptionsDo;
import cn.javaworker.yeming.ukeli.service.SubjectOptionsService;
import cn.javaworker.yeming.ukeli.service.SubjectService;

/**
* 创建日期：2017-09-04 11:03:02
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
@RequestMapping("/subject/")
public class SubjectController  extends BaseControllerImpl implements WebBaseController<SubjectDo> {

	private final static Logger LOGGER =LogManager.getLogger(SubjectController.class);
	
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private ClazzService clazzService;
	@Autowired
	private SubjectOptionsService  subjectOptionsService;
	@Value("${video.play.url}")
	private String videoUrl;
	@Value("${video.access.key}")
	private String accessKey;
	@Value("${video.link.key}")
	private String linkKey;
	
    
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#page(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "page.html",method=RequestMethod.GET)
	public String page(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		setting(map,request,response);
		setItems(map, request, response);
		List<ClazzDo> clazzDoList =clazzService.getListByTypeid(ConstantUkeli.SUBJECTCLAZZTYPEID,ConstantUkeli.ONLINE_STATUS);//查询所有题目类型
		List<ClazzDo> clazzDoVtypeList =clazzService.getListByTypeid(ConstantUkeli.SUBJECTCLAZZVTYPEID,ConstantUkeli.ONLINE_STATUS);//查询所有题目类别
        map.put("clazzDoList", clazzDoList);
        map.put("clazzDoVtypeList", clazzDoVtypeList);
		return "/subject/page";
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
        SubjectDo subject =new SubjectDo();
        if(id > 0){
            subject = subjectService.get(id);
            if(null == subject){
                subject = new SubjectDo();
            }else{
                edit=true;
            }
        }
        if(edit) {
         List<Long> vtypes = subjectService.getVtypes(id);
         map.put("vtypes", vtypes);
        }
        
        List<ClazzDo> clazzDoList =clazzService.getListByTypeid(ConstantUkeli.SUBJECTCLAZZTYPEID,ConstantUkeli.ONLINE_STATUS);//查询所有题目类型
        List<ClazzDo> clazzDoVtypeList =clazzService.getListByTypeid(ConstantUkeli.SUBJECTCLAZZVTYPEID,ConstantUkeli.ONLINE_STATUS);//查询所有题目类别
        map.put("subject",subject );
        map.put("clazzDoList", clazzDoList);
        map.put("clazzDoVtypeList", clazzDoVtypeList);
        map.put("edit",edit);
		setting(map,request,response);
        return "/subject/edit";
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#show(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "show.html", method = RequestMethod.POST)
	public String show(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong("id", request);
		SubjectDo subject = subjectService.get(id);
		List<Long> vtypes = subjectService.getVtypes(id);
        map.put("vtypes", vtypes);
		List<ClazzDo> clazzDoList =clazzService.getListByTypeid(ConstantUkeli.SUBJECTCLAZZTYPEID,ConstantUkeli.ONLINE_STATUS);//查询所有题目类型
		List<ClazzDo> clazzDoVtypeList =clazzService.getListByTypeid(ConstantUkeli.SUBJECTCLAZZVTYPEID,ConstantUkeli.ONLINE_STATUS);//查询所有题目类别
		map.put("subject",subject );
		map.put("clazzDoList", clazzDoList);
		map.put("clazzDoVtypeList", clazzDoVtypeList);
		setting(map, request, response);
		return "/subject/show";
	}
    
	
	@RequestMapping(value = "savenext.html",method=RequestMethod.POST)
	public String saveNext(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            boolean edit =false;
            long subjectid =RequestUtil.getLong("subjectid", request);
            String isshow=RequestUtil.getString("isshow", request);
            List<SubjectOptionsDo> subjectOptionsDoList =null;
            if(subjectid > 0) { 
            	SubjectDo subject= subjectService.get(subjectid);
            	Map<String,Object> itemsMap =new HashMap<String,Object>();
            	itemsMap.put("subjectid", subjectid);
                subjectOptionsDoList =subjectOptionsService.getByItems(itemsMap);
            	if(subjectOptionsDoList!=null&&subjectOptionsDoList.size()>0){
            		edit=true;
            	}
            	map.put("subjectid", subjectid);
            	if(subject!=null){
            		map.put("answer",subject.getAnswer());
            	}
            }
            map.put("edit",edit);
            map.put("subjectOptionsDoList",subjectOptionsDoList );
            setting(map,request,response);
            
            if(StringUtils.isNoneBlank(isshow)&&isshow.equals("true")){
            	return "/subject/shownext";
            }
            
        }catch (Exception ex){
            LOGGER.error("新增一个题目信息发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        
		return "/subject/savenext";
	}
	

	@RequestMapping(value = "video.html", method = RequestMethod.POST)
	public String video(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong("id", request);
		SubjectDo subject = subjectService.get(id);
		long vt = System.currentTimeMillis()/1000 +60;
		StringBuilder val = new StringBuilder();
		String ipadd =RequestUtil.getIpAddr(request);
		String accessSign =Encrypt.md5(accessKey +ipadd);
		if(null != subject) {
			StringBuilder signKey = new StringBuilder();
			signKey.append(linkKey).append("/hls").append(subject.getVideopath()).append(vt);
			String md5 = Base64.encodeBase64URLSafeString(DigestUtils.md5(signKey.toString()));
			val.append("/hls").append(subject.getVideopath()).append("?key=").append(accessSign).append("&vt=").append(vt).append("&vkey=").append(md5);
		}
		System.out.println("000" + val);
		map.put("videopath", videoUrl + val.toString());
		map.put("subject",subject );
		setting(map, request, response);
		return "subject/video";
	}
}