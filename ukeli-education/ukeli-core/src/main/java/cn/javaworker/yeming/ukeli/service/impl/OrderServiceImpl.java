/**
* <p> * Title: 商城管理信息系统 *</p>
* <p> * Description: * </p>
* <p> * Copyright: Copyright (c) 2012-2017* </p>
* <p> * Company: 苏州明翔信息科技有限公司 * </p>
* @author 叶明（开发）
* @version 0.1
*/
package cn.javaworker.yeming.ukeli.service.impl;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.javaworker.yeming.core.jdbc.service.impl.BaseServiceImpl;
import cn.javaworker.yeming.ukeli.pojo.OrderDo;
import cn.javaworker.yeming.ukeli.dao.OrderDao;
import cn.javaworker.yeming.ukeli.service.OrderService;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.core.util.StrUtils;

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
@Service
@Transactional
public class OrderServiceImpl extends BaseServiceImpl  implements OrderService {

	@Autowired
	private OrderDao orderDao;

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(OrderDo obj,int type){
		long id=  orderDao.save(obj,OrderDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = orderDao.del(OrderDo.getTable(),id);
		return i>0 ? true : false;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(OrderDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = orderDao.update(OrderDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return orderDao.update(OrderDo.getTable(), params, id, type);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.core.jdbc.service.IBaseService#delByIds(java.lang.Object[])
	*/
	@Override
	@Transactional
	public void delByIds(Object[] ids){
		if(null == ids) {
			return;
		}
		if(ids.length  <1) {
			return;
		}
		String val = StringUtils.join(ids,",");
		StringBuilder sql = new StringBuilder("update ").append(OrderDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		orderDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		orderDao.updateByItems(OrderDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public OrderDo get(Serializable id){
		return orderDao.get(id,OrderDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<OrderDo> getAll(){
		return orderDao.getAll(OrderDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(OrderDo.getTable()).append(" where 1=1 ");
		List<Object> params = new ArrayList<>();
		switch (type) {
//			case 1:sql.append(" and username=? ");params.add(val);break;
//			case 2:sql.append(" and realname=? ");params.add(val);break;
		}
		if(id > 0) {
			sql.append(" and id <> ?");
			params.add(id);
		}
		sql.append(" and slock < 2");
		int count = orderDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public OrderDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(OrderDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<OrderDo> list = orderDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
  
  private RowMapper<OrderDo> rowMapper = new RowMapper<OrderDo>() {
		@Override
		public OrderDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderDo order=new OrderDo();
			order.setId(rs.getLong("id"));
			order.setOrdernumber(rs.getString("ordernumber"));
			order.setUserid(rs.getLong("userid"));
			order.setUsername(rs.getString("username"));
			order.setNickname(rs.getString("nickname"));
			order.setProductid(rs.getLong("productid"));
			order.setProductname(rs.getString("productname"));
			order.setAmount(rs.getInt("amount"));
			order.setTotalmarketprice(rs.getInt("totalmarketprice"));
			order.setTotalprice(rs.getInt("totalprice"));
			order.setTotalvipprice(rs.getInt("totalvipprice"));
			order.setPayprice(rs.getInt("payprice"));
			order.setCouponprice(rs.getInt("couponprice"));
			order.setOrdertime(rs.getTimestamp("ordertime"));
			order.setOrderstatus(rs.getInt("orderstatus"));
			order.setPaystatus(rs.getShort("paystatus"));
			order.setPaytime(rs.getTimestamp("paytime"));
			order.setPaytype(rs.getShort("paytype"));
			order.setPayorder(rs.getString("payorder"));
			order.setDetail(rs.getString("detail"));
			order.setIorder(rs.getLong("iorder"));
			order.setStatus(rs.getShort("status"));
			order.setEdittime(rs.getTimestamp("edittime"));
			order.setSlock(rs.getShort("slock"));

			return order;
		}
	};
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.IOrderService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<OrderDo> getPage( Map<String, Object> kaywordMap ,int status,int slock, long start, int limit) {
		
	   StringBuilder sql = new StringBuilder("select * from ").append(OrderDo.getTable()).append(" where 1=1 ");
		StringBuilder sqlCount = new StringBuilder("select count(id) from ").append(OrderDo.getTable()).append(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();

		if(kaywordMap!=null) {
			if(null!=kaywordMap.get("username")&&StringUtils.isNotBlank(kaywordMap.get("username").toString())){
				sql.append(" and (username like ? )");
				sqlCount.append(" and (username like ? ) ");
				params.add("%"+kaywordMap.get("username").toString()+"%");
			}
			if(null!=kaywordMap.get("ordernumber")&&StringUtils.isNotBlank(kaywordMap.get("ordernumber").toString())){
				sql.append(" and (ordernumber like ? )");
				sqlCount.append(" and (ordernumber like ? ) ");
				params.add(kaywordMap.get("ordernumber").toString());
			}
			if(null!=kaywordMap.get("productid")&&StringUtils.isNotBlank(kaywordMap.get("productid").toString())){
				sql.append(" and (productid = ? )");
				sqlCount.append(" and (productid = ? ) ");
				params.add(Long.valueOf((String) kaywordMap.get("productid")) );
			}
			if(null!=kaywordMap.get("paystatus")&&StringUtils.isNotBlank(kaywordMap.get("paystatus").toString())){
				sql.append(" and (paystatus = ? )");
				sqlCount.append(" and (paystatus = ? ) ");
				params.add( (String)kaywordMap.get("paystatus"));
			}
			
			if(null!=kaywordMap.get("startTime")){
				sql.append(" and (ordertime > ?)");
				sqlCount.append(" and (ordertime > ? ) ");
				params.add(kaywordMap.get("startTime"));
			}
			if(null!=kaywordMap.get("endTime")){
				sql.append(" and (ordertime < ?)");
				sqlCount.append(" and (ordertime < ? ) ");
				params.add(kaywordMap.get("endTime"));
			}
			
		}
		
		if (status > -1) {
			sql.append(" and status =? ");
			sqlCount.append(" and status=? ");
			params.add(status);
		}
		
		if (slock > -1) {
			sql.append(" and slock =? ");
			sqlCount.append(" and slock=? ");
			params.add(slock);
		}else{
			sql.append(" and slock < 2 ");
			sqlCount.append(" and slock < 2 ");
		}
		
		int count = orderDao.queryForInt(sqlCount,params.toArray());
		List<OrderDo> list = null;
		if(count > 0){
			sql.append(" order by iorder desc limit ?,?");params.add(start);params.add(limit);
			list = orderDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(OrderDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public OrderDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(OrderDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<OrderDo> list = orderDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(OrderDo orderDo, OrderDo vorderDo) {
		StringBuilder sql = new StringBuilder("update  ").append(OrderDo.getTable()).append(" set iorder=? where id =?");
		orderDao.executeUpdate(sql, orderDo.getIorder(),vorderDo.getId());
		orderDao.executeUpdate(sql, vorderDo.getIorder(),orderDo.getId());
	}
	
}