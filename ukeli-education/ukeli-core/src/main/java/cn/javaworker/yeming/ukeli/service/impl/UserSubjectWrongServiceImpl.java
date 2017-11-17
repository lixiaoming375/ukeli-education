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
import cn.javaworker.yeming.ukeli.pojo.UserSubjectWrongDo;
import cn.javaworker.yeming.ukeli.dao.UserSubjectWrongDao;
import cn.javaworker.yeming.ukeli.service.UserSubjectWrongService;
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
public class UserSubjectWrongServiceImpl extends BaseServiceImpl  implements UserSubjectWrongService {

	@Autowired
	private UserSubjectWrongDao usersubjectwrongDao;

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(UserSubjectWrongDo obj,int type){
		long id=  usersubjectwrongDao.save(obj,UserSubjectWrongDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = usersubjectwrongDao.del(UserSubjectWrongDo.getTable(),id);
		return i>0 ? true : false;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(UserSubjectWrongDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = usersubjectwrongDao.update(UserSubjectWrongDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return usersubjectwrongDao.update(UserSubjectWrongDo.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(UserSubjectWrongDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		usersubjectwrongDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		usersubjectwrongDao.updateByItems(UserSubjectWrongDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public UserSubjectWrongDo get(Serializable id){
		return usersubjectwrongDao.get(id,UserSubjectWrongDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<UserSubjectWrongDo> getAll(){
		return usersubjectwrongDao.getAll(UserSubjectWrongDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(UserSubjectWrongDo.getTable()).append(" where 1=1 ");
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
		int count = usersubjectwrongDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public UserSubjectWrongDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(UserSubjectWrongDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<UserSubjectWrongDo> list = usersubjectwrongDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
  
  private RowMapper<UserSubjectWrongDo> rowMapper = new RowMapper<UserSubjectWrongDo>() {
		@Override
		public UserSubjectWrongDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserSubjectWrongDo usersubjectwrong=new UserSubjectWrongDo();
			usersubjectwrong.setId(rs.getLong("id"));
			usersubjectwrong.setUserid(rs.getLong("userid"));
			usersubjectwrong.setSubjectid(rs.getLong("subjectid"));
			usersubjectwrong.setExt_userName(rs.getString("ext_userName"));
			usersubjectwrong.setExt_subjectName(rs.getString("ext_subjectName"));
			usersubjectwrong.setVtype(rs.getInt("vtype"));
			usersubjectwrong.setIsmod(rs.getString("ismod"));
			usersubjectwrong.setIorder(rs.getLong("iorder"));
			usersubjectwrong.setStatus(rs.getShort("status"));
			usersubjectwrong.setEdittime(rs.getTimestamp("edittime"));
			usersubjectwrong.setSlock(rs.getShort("slock"));

			return usersubjectwrong;
		}
	};
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.IUserSubjectWrongService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<UserSubjectWrongDo> getPage( Map<String, Object> kaywordMap ,int status,int slock, long start, int limit) {
		
		 StringBuilder sql = new StringBuilder("select usw.*,u.username as ext_userName, s.name as ext_subjectName from t_user_subject_wrong usw,t_user u ,t_subject s where usw.userid=u.id and  usw.subjectid=s.id ");
			StringBuilder sqlCount = new StringBuilder("select count(usw.id) from t_user_subject_wrong usw,t_user u ,t_subject s where usw.userid=u.id and  usw.subjectid=s.id");
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
				sql.append(" and usw.status =? ");
				sqlCount.append(" and usw.status=? ");
				params.add(status);
			}
			
			if (slock > -1) {
				sql.append(" and usw.slock =? ");
				sqlCount.append(" and usw.slock=? ");
				params.add(slock);
			}else{
				sql.append(" and usw.slock < 2 ");
				sqlCount.append(" and usw.slock < 2 ");
			}
		
		int count = usersubjectwrongDao.queryForInt(sqlCount,params.toArray());
		List<UserSubjectWrongDo> list = null;
		if(count > 0){
			sql.append(" order by usw.id desc limit ?,?");params.add(start);params.add(limit);
			list = usersubjectwrongDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(UserSubjectWrongDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public UserSubjectWrongDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(UserSubjectWrongDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<UserSubjectWrongDo> list = usersubjectwrongDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(UserSubjectWrongDo usersubjectwrongDo, UserSubjectWrongDo vusersubjectwrongDo) {
		StringBuilder sql = new StringBuilder("update  ").append(UserSubjectWrongDo.getTable()).append(" set iorder=? where id =?");
		usersubjectwrongDao.executeUpdate(sql, usersubjectwrongDo.getIorder(),vusersubjectwrongDo.getId());
		usersubjectwrongDao.executeUpdate(sql, vusersubjectwrongDo.getIorder(),usersubjectwrongDo.getId());
	}
	
}