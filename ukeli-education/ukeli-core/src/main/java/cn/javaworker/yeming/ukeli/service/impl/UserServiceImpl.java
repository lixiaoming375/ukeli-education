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
import cn.javaworker.yeming.ukeli.pojo.UserDo;
import cn.javaworker.yeming.ukeli.dao.UserDao;
import cn.javaworker.yeming.ukeli.service.UserService;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.core.util.StrUtils;

/**
* 创建日期：2017-09-12 14:28:25
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
public class UserServiceImpl extends BaseServiceImpl  implements UserService {

	@Autowired
	private UserDao userDao;

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(UserDo obj,int type){
		long id=  userDao.save(obj,UserDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = userDao.del(UserDo.getTable(),id);
		return i>0 ? true : false;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(UserDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = userDao.update(UserDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return userDao.update(UserDo.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(UserDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		userDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		userDao.updateByItems(UserDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public UserDo get(Serializable id){
		return userDao.get(id,UserDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<UserDo> getAll(){
		return userDao.getAll(UserDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(UserDo.getTable()).append(" where 1=1 ");
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
		int count = userDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public UserDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(UserDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<UserDo> list = userDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
  
  private RowMapper<UserDo> rowMapper = new RowMapper<UserDo>() {
		@Override
		public UserDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserDo user=new UserDo();
			user.setId(rs.getLong("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setNickname(rs.getString("nickname"));
			user.setRealname(rs.getString("realname"));
			user.setBidthday(rs.getString("bidthday"));
			user.setSex(rs.getString("sex"));
			user.setTelephone(rs.getString("telephone"));
			user.setMobile(rs.getString("mobile"));
			user.setEmail(rs.getString("email"));
			user.setAddress(rs.getString("address"));
			user.setPostcode(rs.getString("postcode"));
			user.setFirstletter(rs.getString("firstletter"));
			user.setSpell(rs.getString("spell"));
			user.setAllspell(rs.getString("allspell"));
			user.setDetail(rs.getString("detail"));
			user.setWechat(rs.getString("wechat"));
			user.setQq(rs.getString("qq"));
			user.setHeadpic(rs.getString("headpic"));
			user.setVcode(rs.getString("vcode"));
			user.setInventcode(rs.getString("inventcode"));
			user.setLogintimes(rs.getInt("logintimes"));
			user.setLastlogintime(rs.getTimestamp("lastlogintime"));
			user.setLastloginip(rs.getString("lastloginip"));
			user.setPlatform(rs.getString("platform"));
			user.setIsvip(rs.getString("isvip"));
			user.setVipstarttime(rs.getTimestamp("vipstarttime"));
			user.setVipendtime(rs.getTimestamp("vipendtime"));
			user.setIorder(rs.getLong("iorder"));
			user.setStatus(rs.getShort("status"));
			user.setAddtime(rs.getTimestamp("addtime"));
			user.setExt_addtime(rs.getTimestamp("addtime"));
			user.setEdittime(rs.getTimestamp("edittime"));
			user.setSlock(rs.getShort("slock"));

			return user;
		}
	};
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.IUserService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<UserDo> getPage( Map<String, Object> kaywordMap ,int status,int slock, long start, int limit) {
		
	   StringBuilder sql = new StringBuilder("select * from ").append(UserDo.getTable()).append(" where 1=1 ");
		StringBuilder sqlCount = new StringBuilder("select count(id) from ").append(UserDo.getTable()).append(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();

		if(kaywordMap!=null) {
			if(null!=kaywordMap.get("username")&&StringUtils.isNotBlank(kaywordMap.get("username").toString())){
				sql.append(" and (username like ? )");
				sqlCount.append(" and (username like ? ) ");
				params.add("%"+kaywordMap.get("username").toString()+"%");
			}
			if(null!=kaywordMap.get("nickname")&&StringUtils.isNotBlank(kaywordMap.get("nickname").toString())){
				sql.append(" and (nickname like ? )");
				sqlCount.append(" and (nickname like ? ) ");
				params.add("%"+kaywordMap.get("nickname").toString()+"%");
			}
			if(null!=kaywordMap.get("isvip")&&StringUtils.isNotBlank(kaywordMap.get("isvip").toString())){
				sql.append(" and (isvip = ? )");
				sqlCount.append(" and (isvip = ? ) ");
				params.add(kaywordMap.get("isvip").toString());
			}
			if(null!=kaywordMap.get("startTime")){
				sql.append(" and (addtime > ?)");
				sqlCount.append(" and (addtime > ? ) ");
				params.add(kaywordMap.get("startTime"));
			}
			if(null!=kaywordMap.get("endTime")){
				sql.append(" and (addtime < ?)");
				sqlCount.append(" and (addtime < ? ) ");
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
		
		int count = userDao.queryForInt(sqlCount,params.toArray());
		List<UserDo> list = null;
		if(count > 0){
			sql.append(" order by iorder desc limit ?,?");params.add(start);params.add(limit);
			list = userDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(UserDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public UserDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(UserDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<UserDo> list = userDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(UserDo userDo, UserDo vuserDo) {
		StringBuilder sql = new StringBuilder("update  ").append(UserDo.getTable()).append(" set iorder=? where id =?");
		userDao.executeUpdate(sql, userDo.getIorder(),vuserDo.getId());
		userDao.executeUpdate(sql, vuserDo.getIorder(),userDo.getId());
	}
	
}