/**
* <p> * Title: 商城管理信息系统 *</p>
* <p> * Description: * </p>
* <p> * Copyright: Copyright (c) 2012-2017* </p>
* <p> * Company: 苏州明翔信息科技有限公司 * </p>
* @author 叶明（开发）
* @version 0.1
*/
package cn.javaworker.yeming.ukeli.controller.rest;

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
import cn.javaworker.yeming.core.web.CookiesUtil;
import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.ukeli.service.ProductService;
import cn.javaworker.yeming.ukeli.pojo.ProductDo;
import cn.javaworker.yeming.pojo.ErrorInfo;
import cn.javaworker.yeming.pojo.Page;

/**
* 创建日期：2017-09-12 14:28:24
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
@RequestMapping("/product/")
public class ProductRestController  implements RestBaseController<ProductDo> {

	private final static Logger LOGGER =LogManager.getLogger(ProductRestController.class);
	@Autowired
	private ProductService productService;
	
    
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#del(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="del.json",method =RequestMethod.POST)
	public ErrorInfo<ProductDo> del(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<ProductDo> errorInfo = new ErrorInfo<>();
        try{
            LOGGER.info("需要删除的产品名称id=" + ids);
            String[] str = StringUtils.split(ids, ",");
            productService.delByIds(str);
            errorInfo.setSuccess(true);
            errorInfo.setErrorinfo("删除成功");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，删除产品名称失败，原因:"+ex.getMessage());
            LOGGER.error("删除一个产品名称发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#list(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="list.json",method =RequestMethod.POST)
	public Page<ProductDo> list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<ProductDo> page = null;
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
            String keyword = RequestUtil.getString("keyword",request);
            LOGGER.info("获取产品名称status=" + status + ",关键字="+keyword + ",start=" + start +",limit=" + limit);
            page = productService.getPage(keyword,status,slock,start,limit);
            if(null != page){
            	/*List<AdminDo> list = page.getList();
            	if(null != list){
					Map<Long, String> roleNameMap = roleAction.getRoleNameByAdminid();
            		for (Admin admin : list) {
            			String name = roleNameMap.get(admin.getId());
            			if(null != name){
            				admin.setExt_rolename(name);
            			}
					}
            	}*/
            }
        }catch (Exception ex){
            LOGGER.error("获取产品名称分页发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return page;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#save(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="save.json",method =RequestMethod.POST)
	public ErrorInfo<ProductDo> save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<ProductDo> errorInfo = new ErrorInfo<>();
        try{
            long id =RequestUtil.getLong("id", request);
            /*
            String username = RequestUtil.getString("username", request);
            if(StrUtils.isBlank(username)) {
            	errorInfo.setSuccess(false);
            	errorInfo.setIreturn(900);
            	errorInfo.setErrorinfo("对不起，产品名称登录用户名不能为空");
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
            	map.put("marketprice", map.get("marketprice").toString().replaceAll(",", "").trim());
            	map.put("price", map.get("price").toString().replaceAll(",", "").trim());
            	map.put("vipprice", map.get("vipprice").toString().replaceAll(",", "").trim());
            	
            	map.put("editer", adminName);
            	map.put("editerid", adminid);
            	productService.update(map, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("恭喜您，更新成功");
            }else {
            	ProductDo productDo = RequestUtil.form(ProductDo.class,request);
            	if(null !=productDo) {
            		productDo.setAdderid(adminid);
            		productDo.setAdder(adminName);
            		productService.save(productDo);
                    errorInfo.setSuccess(true);
                    errorInfo.setErrorinfo("恭喜您，保存产品名称成功");
            	}else {
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，参数错误，请检查");
            	}
            }
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，新增一个产品名称信息失败，原因:"+ex.getMessage());
            LOGGER.error("新增一个产品名称信息发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#status(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="status.json",method =RequestMethod.POST)
	public ErrorInfo<ProductDo> status(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<ProductDo> errorInfo = new ErrorInfo<>();
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
            	productService.updateByItem("status", status, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("更新状态成功");
			}else {
				productService.updateByItem("auditstatus", status, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("更改审核状态成功");
			}
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，更新产品名称状态失败，原因:"+ex.getMessage());
            LOGGER.error("更新产品名称状态失败发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	
	@RequestMapping(value="order.json",method =RequestMethod.POST)
	@ResponseBody
	public ErrorInfo<ProductDo> order(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<ProductDo> errorInfo = new ErrorInfo<>();
        try{
        	long id =RequestUtil.getLong("id", request);
        	int type =RequestUtil.getInt("type", request);
        	if (id < 1 || type < 0) {
            	errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
			}
        	ProductDo productDo = productService.get(id);
        	if(null ==productDo){
        		errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
        	}
        	ProductDo vproductDo = productService.getByOrderType(productDo.getIorder(),type);
        	if(null == vproductDo){
        		if(type ==1){
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最顶层了");
        		}else{
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最底层了");
        		}
        		return errorInfo;
        	}
        	if(null != productDo && null != vproductDo){
        		productService.order(productDo,vproductDo);
        	}
        	errorInfo.setSuccess(true);
        	errorInfo.setErrorinfo("排序成功!");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，更新产品名称排序失败，原因:"+ex.getMessage());
            LOGGER.error("更新产品名称排序失败发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	
}