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
import cn.javaworker.yeming.pojo.ErrorInfo;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.ukeli.pojo.OrderDo;
import cn.javaworker.yeming.ukeli.pojo.ProductDo;
import cn.javaworker.yeming.ukeli.pojo.UserDo;
import cn.javaworker.yeming.ukeli.service.OrderService;
import cn.javaworker.yeming.ukeli.service.ProductService;
import cn.javaworker.yeming.ukeli.service.UserService;

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
@RequestMapping("/order/")
public class OrderRestController  implements RestBaseController<OrderDo> {

	private final static Logger LOGGER =LogManager.getLogger(OrderRestController.class);
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService; 
	@Autowired
	private ProductService productService;
	
    
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#del(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="del.json",method =RequestMethod.POST)
	public ErrorInfo<OrderDo> del(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<OrderDo> errorInfo = new ErrorInfo<>();
        try{
            LOGGER.info("需要删除的订单产品id=" + ids);
            String[] str = StringUtils.split(ids, ",");
            orderService.delByIds(str);
            errorInfo.setSuccess(true);
            errorInfo.setErrorinfo("删除成功");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，删除订单产品失败，原因:"+ex.getMessage());
            LOGGER.error("删除一个订单产品发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.IBaseController#list(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="list.json",method =RequestMethod.POST)
	public Page<OrderDo> list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Page<OrderDo> page = null;
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
            String username = RequestUtil.getString("username",request);
            String ordernumber = RequestUtil.getString("ordernumber",request);
            String reservationtime = RequestUtil.getString("reservationtime",request);
            String productid = RequestUtil.getString("productid",request);
            String paystatus = RequestUtil.getString("paystatus",request);
            LOGGER.info("获取订单产品status=" + status + ",username="+username+ ",ordernumber="+ordernumber+ ",reservationtime="+reservationtime+ ",productid="+productid+ ",paystatus="+paystatus + ",start=" + start +",limit=" + limit);
            Date startTime= null;
            Date endTime=null;
            if(StringUtils.isNoneBlank(reservationtime)){
            	 DateFormat  df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	 startTime=df.parse(reservationtime.split("-")[0].replace("/", "-"));
            	 endTime=df.parse(reservationtime.split("-")[1].replace("/", "-"));
            } 
            Map<String, Object> kaywordMap=new HashMap<String,Object>();
            kaywordMap.put("username",username);
            kaywordMap.put("ordernumber",ordernumber);
            kaywordMap.put("productid",productid);
            kaywordMap.put("paystatus",paystatus);
            kaywordMap.put("startTime",startTime);
            kaywordMap.put("endTime",endTime);
            
            page = orderService.getPage(kaywordMap,status,slock,start,limit);
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
            LOGGER.error("获取订单产品分页发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return page;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#save(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="save.json",method =RequestMethod.POST)
	public ErrorInfo<OrderDo> save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<OrderDo> errorInfo = new ErrorInfo<>();
		UserDo user=null;
		ProductDo product=null;
        try{
            long id =RequestUtil.getLong("id", request);
            String username = RequestUtil.getString("username", request);
            Long productid = RequestUtil.getLong("productid", request);
            if(StrUtils.isNoBlank(username)) {
                user= userService.getByItem("username", username.trim());
            	if(user==null){
            		errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，为查询到该用户信息,请检查！");
                	return errorInfo;
        		}
            }
            if(productid>0){
            	 product=productService.get(productid);
            }
            
            long adminid =CookiesUtil.getLong3Des(Constant.ADMIN_COOKIES_ID, request);
            String adminName =CookiesUtil.getString3Des(Constant.ADMIN_COOKIES_NAME, request);
            if(id > 0) {
            	Map<String, Object> map= RequestUtil.getParams(request);
            	map.put("totalmarketprice", map.get("totalmarketprice").toString().replaceAll(",", "").trim());
            	map.put("totalprice", map.get("totalprice").toString().replaceAll(",", "").trim());
            	map.put("payprice", map.get("payprice").toString().replaceAll(",", "").trim());
            	map.put("productname", product.getProductname());
            	map.put("editer", adminName);
            	map.put("editerid", adminid);
            	orderService.update(map, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("恭喜您，更新成功");
            }else {
            	OrderDo orderDo = RequestUtil.form(OrderDo.class,request);
            	if(null !=orderDo) {
            			orderDo.setUserid(user.getId());
            			orderDo.setProductname(product.getProductname());
            			orderDo.setOrdertime(new Date());
            			orderDo.setAdderid(adminid);
                		orderDo.setAdder(adminName);
                		orderService.save(orderDo);
                        errorInfo.setSuccess(true);
                        errorInfo.setErrorinfo("恭喜您，保存订单产品成功");
            	}else {
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，参数错误，请检查");
            	}
            }
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，新增一个订单产品信息失败，原因:"+ex.getMessage());
            LOGGER.error("新增一个订单产品信息发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.base.controller.inspinia.BaseController#status(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value="status.json",method =RequestMethod.POST)
	public ErrorInfo<OrderDo> status(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<OrderDo> errorInfo = new ErrorInfo<>();
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
            	orderService.updateByItem("status", status, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("更新状态成功");
			}else {
				orderService.updateByItem("auditstatus", status, id);
                errorInfo.setSuccess(true);
                errorInfo.setErrorinfo("更改审核状态成功");
			}
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，更新订单产品状态失败，原因:"+ex.getMessage());
            LOGGER.error("更新订单产品状态失败发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	
	@RequestMapping(value="order.json",method =RequestMethod.POST)
	@ResponseBody
	public ErrorInfo<OrderDo> order(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ErrorInfo<OrderDo> errorInfo = new ErrorInfo<>();
        try{
        	long id =RequestUtil.getLong("id", request);
        	int type =RequestUtil.getInt("type", request);
        	if (id < 1 || type < 0) {
            	errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
			}
        	OrderDo orderDo = orderService.get(id);
        	if(null ==orderDo){
        		errorInfo.setSuccess(false);
            	errorInfo.setErrorinfo("对不起，参数错误!");
            	return errorInfo;
        	}
        	OrderDo vorderDo = orderService.getByOrderType(orderDo.getIorder(),type);
        	if(null == vorderDo){
        		if(type ==1){
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最顶层了");
        		}else{
                	errorInfo.setSuccess(false);
                	errorInfo.setErrorinfo("对不起，已经处理最底层了");
        		}
        		return errorInfo;
        	}
        	if(null != orderDo && null != vorderDo){
        		orderService.order(orderDo,vorderDo);
        	}
        	errorInfo.setSuccess(true);
        	errorInfo.setErrorinfo("排序成功!");
        }catch (Exception ex){
            errorInfo.setErrorinfo("对不起，更新订单产品排序失败，原因:"+ex.getMessage());
            LOGGER.error("更新订单产品排序失败发生错误:"+ex.getMessage());
            LOGGER.debug(ex);
        }
        return errorInfo;
	}
	
}