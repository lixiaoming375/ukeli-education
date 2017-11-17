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
import cn.javaworker.yeming.ukeli.pojo.UserSubjectRightDo;
import cn.javaworker.yeming.ukeli.dao.UserSubjectRightDao;
import cn.javaworker.yeming.ukeli.service.UserSubjectRightService;
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
public class UserSubjectRightServiceImpl extends BaseServiceImpl  implements UserSubjectRightService {

	@Autowired
	private UserSubjectRightDao usersubjectrightDao;

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(UserSubjectRightDo obj,int type){
		long id=  usersubjectrightDao.save(obj,UserSubjectRightDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = usersubjectrightDao.del(UserSubjectRightDo.getTable(),id);
		return i>0 ? true : false;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(UserSubjectRightDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = usersubjectrightDao.update(UserSubjectRightDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return usersubjectrightDao.update(UserSubjectRightDo.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(UserSubjectRightDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		usersubjectrightDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		usersubjectrightDao.updateByItems(UserSubjectRightDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public UserSubjectRightDo get(Serializable id){
		return usersubjectrightDao.get(id,UserSubjectRightDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<UserSubjectRightDo> getAll(){
		return usersubjectrightDao.getAll(UserSubjectRightDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(UserSubjectRightDo.getTable()).append(" where 1=1 ");
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
		int count = usersubjectrightDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public UserSubjectRightDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(UserSubjectRightDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<UserSubjectRightDo> list = usersubjectrightDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
  
  private RowMapper<UserSubjectRightDo> rowMapper = new RowMapper<UserSubjectRightDo>() {
		@Override
		public UserSubjectRightDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserSubjectRightDo usersubjectright=new UserSubjectRightDo();
			usersubjectright.setId(rs.getLong("id"));
			usersubjectright.setUserid(rs.getLong("userid"));
			usersubjectright.setSubjectid(rs.getLong("subjectid"));
			usersubjectright.setExt_userName(rs.getString("ext_userName"));
			usersubjectright.setExt_subjectName(rs.getString("ext_subjectName"));
			usersubjectright.setSubjectid(rs.getLong("subjectid"));
			usersubjectright.setVtype(rs.getInt("vtype"));
			usersubjectright.setIsanswer(rs.getString("isanswer"));
			usersubjectright.setIswrong(rs.getString("iswrong"));
			usersubjectright.setIorder(rs.getLong("iorder"));
			usersubjectright.setStatus(rs.getShort("status"));
			usersubjectright.setEdittime(rs.getTimestamp("edittime"));
			usersubjectright.setSlock(rs.getShort("slock"));

			return usersubjectright;
		}
	};
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.IUserSubjectRightService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<UserSubjectRightDo> getPage(Map<String, Object> kaywordMap ,int status,int slock, long start, int limit) {
		
	   StringBuilder sql = new StringBuilder("select usr.*,u.username as ext_userName, s.name as ext_subjectName from t_user_subject_right usr,t_user u ,t_subject s where usr.userid=u.id and  usr.subjectid=s.id ");
		StringBuilder sqlCount = new StringBuilder("select count(usr.id) from t_user_subject_right usr,t_user u ,t_subject s where usr.userid=u.id and  usr.subjectid=s.id");
		List<Object> params = new ArrayList<Object>();

		if(kaywordMap!=null) {
			if(null!=kaywordMap.get("username")&&StringUtils.isNotBlank(kaywordMap.get("username").toString())){
				sql.append(" and (u.username like ? )");
				sqlCount.append(" and (u.username like ? ) ");
				params.add("%"+kaywordMap.get("username").toString()+"%");
			}
			if(null!=kaywordMap.get("name")&&StringUtils.isNotBlank(kaywordMap.get("name").toString())){
				sql.append(" and (s.name like ? )");
				sqlCount.append(" and (s.name like ? ) ");
				params.add("%"+kaywordMap.get("name").toString()+"%");
			}
		}
		
		if (status > -1) {
			sql.append(" and usr.status =? ");
			sqlCount.append(" and usr.status=? ");
			params.add(status);
		}
		
		if (slock > -1) {
			sql.append(" and usr.slock =? ");
			sqlCount.append(" and usr.slock=? ");
			params.add(slock);
		}else{
			sql.append(" and usr.slock < 2 ");
			sqlCount.append(" and usr.slock < 2 ");
		}
		
		int count = usersubjectrightDao.queryForInt(sqlCount,params.toArray());
		List<UserSubjectRightDo> list = null;
		if(count > 0){
			sql.append(" order by usr.id desc limit ?,?");params.add(start);params.add(limit);
			list = usersubjectrightDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(UserSubjectRightDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public UserSubjectRightDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(UserSubjectRightDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<UserSubjectRightDo> list = usersubjectrightDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(UserSubjectRightDo usersubjectrightDo, UserSubjectRightDo vusersubjectrightDo) {
		StringBuilder sql = new StringBuilder("update  ").append(UserSubjectRightDo.getTable()).append(" set iorder=? where id =?");
		usersubjectrightDao.executeUpdate(sql, usersubjectrightDo.getIorder(),vusersubjectrightDo.getId());
		usersubjectrightDao.executeUpdate(sql, vusersubjectrightDo.getIorder(),usersubjectrightDo.getId());
	}
	
}