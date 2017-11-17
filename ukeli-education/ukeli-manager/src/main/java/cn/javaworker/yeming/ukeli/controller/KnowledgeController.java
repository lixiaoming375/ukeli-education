package cn.javaworker.yeming.ukeli.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
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
import cn.javaworker.yeming.ukeli.pojo.KnowledgeDo;
import cn.javaworker.yeming.ukeli.pojo.SubjectDo;
import cn.javaworker.yeming.ukeli.pojo.VideoDo;
import cn.javaworker.yeming.ukeli.service.KnowledgeService;
import cn.javaworker.yeming.ukeli.service.SubjectService;
import cn.javaworker.yeming.ukeli.service.VideoService;

@Controller
@RequestMapping("/knowledge/")
public class KnowledgeController  extends BaseControllerImpl implements WebBaseController<KnowledgeDo> {

	private final static Logger LOGGER =LogManager.getLogger(KnowledgeController.class);
	
	private final static int GRADECLAZZTYPEID=3;
	
	private final static int KNOWLEDGECLAZZTYPEID=2;
	
	@Autowired
	private KnowledgeService knowledgeService;
	@Autowired
	private VideoService videoService;
	@Autowired
	private ClazzService clazzService;
	@Autowired
	private SubjectService subjectService;
	
	@Value("${video.play.url}")
	private String videoUrl;
	@Value("${video.access.key}")
	private String accessKey;
	@Value("${video.link.key}")
	private String linkKey;
	
	@Override
	@RequestMapping(value = "page.html",method=RequestMethod.GET)
	public String page(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		setting(map,request,response);
		setItems(map, request, response);
		List<ClazzDo> clazzDoList =clazzService.getListByTypeid(ConstantUkeli.KNOWLEDGECLAZZTYPEID,ConstantUkeli.ONLINE_STATUS);//查询所有杯赛类型
        map.put("clazzDoList", clazzDoList);
		return "/knowledge/page";
	}

	
	@Override
	@RequestMapping(value = "edit.html",method=RequestMethod.POST)
	public String edit(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Long id = RequestUtil.getLong("id",request);
		LOGGER.info("需要修改knowledge的id:"+id);
        boolean edit =false;
        KnowledgeDo knowledge =new KnowledgeDo();
        if(id > 0){
        	knowledge = knowledgeService.get(id);
            if(null == knowledge){
            	knowledge = new KnowledgeDo();
            }else{
                edit=true;
            }
        }
        List<ClazzDo> clazzDoList =clazzService.getListByTypeid(GRADECLAZZTYPEID,ConstantUkeli.ONLINE_STATUS);//查询所有年级
        List<ClazzDo> knowledgeClazzDoList =clazzService.getListByTypeid(KNOWLEDGECLAZZTYPEID,ConstantUkeli.ONLINE_STATUS);//查询所有知识点类型
        map.put("knowledge",knowledge );
        map.put("knowledgeClazzDoList",knowledgeClazzDoList);
        map.put("clazzDoList", clazzDoList);
        map.put("edit",edit);
		setting(map,request,response);
        return "/knowledge/edit";
	}

	@RequestMapping(value = "bindvideo.html",method=RequestMethod.POST)
	public String bindVideo(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Long videoid = RequestUtil.getLong("videoid",request);
		Long knowledgeid = RequestUtil.getLong("knowledgeid",request);
		LOGGER.info("需要修改视频的videoid:"+videoid);
        boolean edit =false;
        VideoDo video =new VideoDo();
        if(videoid > 0){
        	video = videoService.get(videoid);
            if(null == video){
            	video = new VideoDo();
            }else{
                edit=true;
            }
        }
        KnowledgeDo knowledge=knowledgeService.get(knowledgeid);
        map.put("video",video );
        map.put("knowledge", knowledge);
        map.put("edit",edit);
		setting(map,request,response);
        return "/knowledge/bindvideo";
	}
	
	@Override
	@RequestMapping(value = "show.html", method = RequestMethod.POST)
	public String show(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Long id = RequestUtil.getLong("id", request);
		List<ClazzDo> clazzDoList =clazzService.getListByTypeid(GRADECLAZZTYPEID,ConstantUkeli.ONLINE_STATUS);//查询所有年级
		List<ClazzDo> knowledgeClazzDoList =clazzService.getListByTypeid(KNOWLEDGECLAZZTYPEID,ConstantUkeli.ONLINE_STATUS);//查询所有知识点类型
		KnowledgeDo knowledge = knowledgeService.get(id);
		map.put("knowledge",knowledge );
		map.put("clazzDoList",clazzDoList );
		map.put("knowledgeClazzDoList",knowledgeClazzDoList );
		setting(map, request, response);
		return "/knowledge/show";
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#edit(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value = "bindDialog.html",method=RequestMethod.POST)
	public String addAttr(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long knowledgeId = RequestUtil.getLong("id",request);
		LOGGER.info("需要绑定题目的考试编号:"+knowledgeId);
        map.put("knowledgeId",knowledgeId );
		setting(map,request,response);
        return "/knowledge/bind";
	}
	
	
	@RequestMapping(value = "video.html", method = RequestMethod.POST)
	public String video(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong("id", request);
		KnowledgeDo knowledge = knowledgeService.get(id);
		long vt = System.currentTimeMillis()/1000 +60;
		StringBuilder val = new StringBuilder();
		String ipadd =RequestUtil.getIpAddr(request);
		String accessSign =Encrypt.md5(accessKey +ipadd);
		if(null != knowledge) {
			StringBuilder signKey = new StringBuilder();
			signKey.append(linkKey).append("/hls").append(knowledge.getVideopath()).append(vt);
			String md5 = Base64.encodeBase64URLSafeString(DigestUtils.md5(signKey.toString()));
			val.append("/hls").append(knowledge.getVideopath()).append("?key=").append(accessSign).append("&vt=").append(vt).append("&vkey=").append(md5);
		}
		System.out.println("000" + val);
		map.put("videopath", videoUrl + val.toString());
		map.put("knowledge",knowledge );
		setting(map, request, response);
		return "knowledge/video";
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#edit(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value = "bindAddScore.html",method=RequestMethod.POST)
	public String AddScore(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long knowledgeId = RequestUtil.getLong("knowledgeId",request);
		Long subjectId=RequestUtil.getLong("subjectId", request);
		LOGGER.info("需要绑定编号:knowledgeId="+knowledgeId+"  subjectId="+subjectId);
		SubjectDo subject=subjectService.get(subjectId);
        map.put("knowledgeId",knowledgeId);
        map.put("subject",subject);
		setting(map,request,response);
        return "/knowledge/addscore";
	}
}
