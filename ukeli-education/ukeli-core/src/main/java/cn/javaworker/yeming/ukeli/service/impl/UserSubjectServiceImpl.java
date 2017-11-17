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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.javaworker.yeming.core.jdbc.service.impl.BaseServiceImpl;
import cn.javaworker.yeming.core.util.StrUtils;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.ukeli.dao.UserSubjectDao;
import cn.javaworker.yeming.ukeli.pojo.SubjectDo;
import cn.javaworker.yeming.ukeli.pojo.UserDo;
import cn.javaworker.yeming.ukeli.pojo.UserSubjectDo;
import cn.javaworker.yeming.ukeli.service.UserSubjectService;

/**
* 创建日期：2017-10-24 10:41:59
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
public class UserSubjectServiceImpl extends BaseServiceImpl  implements UserSubjectService {

	@Autowired
	private UserSubjectDao usersubjectDao;

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(UserSubjectDo obj,int type){
		long id=  usersubjectDao.save(obj,UserSubjectDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = usersubjectDao.del(UserSubjectDo.getTable(),id);
		return i>0 ? true : false;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(UserSubjectDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = usersubjectDao.update(UserSubjectDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return usersubjectDao.update(UserSubjectDo.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(UserSubjectDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		usersubjectDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		usersubjectDao.updateByItems(UserSubjectDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public UserSubjectDo get(Serializable id){
		return usersubjectDao.get(id,UserSubjectDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<UserSubjectDo> getAll(){
		return usersubjectDao.getAll(UserSubjectDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(UserSubjectDo.getTable()).append(" where 1=1 ");
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
		int count = usersubjectDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public UserSubjectDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(UserSubjectDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<UserSubjectDo> list = usersubjectDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<UserSubjectDo> getListByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(UserSubjectDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<UserSubjectDo> list = usersubjectDao.getList(sql, rowMapper,itemValue);
		return list;
	}
  
  private RowMapper<UserSubjectDo> rowMapper = new RowMapper<UserSubjectDo>() {
		@Override
		public UserSubjectDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserSubjectDo usersubject=new UserSubjectDo();
			usersubject.setId(rs.getLong("id"));
			usersubject.setUserid(rs.getLong("userid"));
			usersubject.setSubjectid(rs.getLong("subjectid"));
			usersubject.setVtype(rs.getInt("vtype"));
			usersubject.setIsdone(rs.getString("isdone"));
			usersubject.setIsadd(rs.getString("isadd"));
			usersubject.setIorder(rs.getLong("iorder"));
			usersubject.setStatus(rs.getShort("status"));
			usersubject.setEdittime(rs.getTimestamp("edittime"));
			usersubject.setSlock(rs.getShort("slock"));
			return usersubject;
		}
	};
	
	private RowMapper<UserSubjectDo> rowMapper1 = new RowMapper<UserSubjectDo>() {
		@Override
		public UserSubjectDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserSubjectDo usersubject=new UserSubjectDo();
			usersubject.setId(rs.getLong("id"));
			usersubject.setUserid(rs.getLong("userid"));
			usersubject.setSubjectid(rs.getLong("subjectid"));
			usersubject.setVtype(rs.getInt("vtype"));
			usersubject.setIsdone(rs.getString("isdone"));
			usersubject.setIsadd(rs.getString("isadd"));
			usersubject.setIorder(rs.getLong("iorder"));
			usersubject.setStatus(rs.getShort("status"));
			usersubject.setEdittime(rs.getTimestamp("edittime"));
			usersubject.setSlock(rs.getShort("slock"));
			usersubject.setExt_username(rs.getString("ext_username"));
			usersubject.setExt_nickname(rs.getString("ext_nickname"));
			usersubject.setExt_subjectname(rs.getString("ext_subjectname"));
			return usersubject;
		}
	};
	 
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.IUserSubjectService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<UserSubjectDo> getPage( String username,String subjectname,String isdone,String isadd, int status,int slock, long start, int limit) {
		
	   StringBuilder sql = new StringBuilder("select t1.*,t2.username as ext_username,t2.nickname ext_nickname,t3.name as ext_subjectname from t_user_subject t1,t_user t2,t_subject t3  where t1.userid=t2.id and t1.subjectid=t3.id");
		StringBuilder sqlCount = new StringBuilder("select count(t1.id) from t_user_subject t1,t_user t2,t_subject t3 where t1.userid=t2.id and t1.subjectid=t3.id ");
		List<Object> params = new ArrayList<Object>();

		if(StrUtils.isNoBlank(username )) {
			sql.append(" and (t2.username like ? )");
			sqlCount.append(" and (t2.username like ? ) ");
			params.add("%"+username+"%");
		}
		if(StrUtils.isNoBlank(subjectname )) {
			sql.append(" and (t3.name like ? )");
			sqlCount.append(" and (t3.name like ? ) ");
			params.add("%"+subjectname+"%");
		}
		
		if (StrUtils.isNoBlank(isdone )) {
			sql.append(" and t1.isdone =? ");
			sqlCount.append(" and t1.isdone=? ");
			params.add(Integer.parseInt(isdone));
		}
		
		if (StrUtils.isNoBlank(isadd )) {
			sql.append(" and t1.isadd =? ");
			sqlCount.append(" and t1.isadd=? ");
			params.add(Integer.parseInt(isadd));
		}
		
		if (status > -1) {
			sql.append(" and t1.status =? ");
			sqlCount.append(" and t1.status=? ");
			params.add(status);
		}
		
		if (slock > -1) {
			sql.append(" and t1.lock =? ");
			sqlCount.append(" and t1.slock=? ");
			params.add(slock);
		}else{
			sql.append(" and t1.slock < 2 ");
			sqlCount.append(" and t1.slock < 2 ");
		}
		
		int count = usersubjectDao.queryForInt(sqlCount,params.toArray());
		List<UserSubjectDo> list = null;
		if(count > 0){
			sql.append(" order by t1.id desc limit ?,?");params.add(start);params.add(limit);
			list = usersubjectDao.getList(sql,rowMapper1,params.toArray());
		}
		return Page.getPage(UserSubjectDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public UserSubjectDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(UserSubjectDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<UserSubjectDo> list = usersubjectDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(UserSubjectDo usersubjectDo, UserSubjectDo vusersubjectDo) {
		StringBuilder sql = new StringBuilder("update  ").append(UserSubjectDo.getTable()).append(" set iorder=? where id =?");
		usersubjectDao.executeUpdate(sql, usersubjectDo.getIorder(),vusersubjectDo.getId());
		usersubjectDao.executeUpdate(sql, vusersubjectDo.getIorder(),usersubjectDo.getId());
	}

	@Override
	@Transactional
	public UserSubjectDo addUsersubject(UserDo user, SubjectDo subject,int type,String isdown) {
		StringBuilder sql = new StringBuilder("select * from ").append(UserSubjectDo.getTable()).append(" where 1=1 and userid= ? and subjectid= ?");
		List<Object> params = new ArrayList<Object>();
		params.add(user.getId());
		params.add(subject.getId());
		List<UserSubjectDo> list = usersubjectDao.getList(sql,rowMapper,params.toArray());
		UserSubjectDo usersubject=null;
		if(null!=list&&list.size()==0){
		    usersubject=new UserSubjectDo();
			usersubject.setUserid(user.getId());
			usersubject.setSubjectid(subject.getId());
			usersubject.setAdder(user.getUsername());
			usersubject.setAdderid(user.getId());
			usersubject.setAddtime(new Date());
			if(type==1){
				usersubject.setIsadd("1");
			}else if(type==0){
				usersubject.setIsdone("1");
			}
			usersubjectDao.save(usersubject, UserSubjectDo.getTable());
		}else if(list.size()>0){
			usersubject=list.get(0);
			Map<String,Object> map=new HashMap<String,Object>();
			if(type==1){
				map.put("isadd", "1");
			}else if(type==0){
				if(StringUtils.isNoneBlank(isdown)&&isdown.equals("1")){
					map.put("isdone", "0");
				}else if(StringUtils.isNoneBlank(isdown)&&isdown.equals("0")){
					map.put("isdone", "1");
				}
			}
			usersubjectDao.update(UserSubjectDo.getTable(), map, usersubject.getId());
		}
		return usersubject;
	}
	
	@Override
	@Transactional
	public UserSubjectDo getUsersubject(long userid, long subjectid){
		StringBuilder sql = new StringBuilder("select * from ").append(UserSubjectDo.getTable()).append(" where 1=1 and userid= ? and subjectid= ?");
		List<Object> params = new ArrayList<Object>();
		params.add(userid);
		params.add(subjectid);
		List<UserSubjectDo> list = usersubjectDao.getList(sql,rowMapper,params.toArray());
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
}