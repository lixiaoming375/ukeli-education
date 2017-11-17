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
import cn.javaworker.yeming.ukeli.pojo.UserExamDo;
import cn.javaworker.yeming.ukeli.dao.UserExamDao;
import cn.javaworker.yeming.ukeli.service.UserExamService;
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
public class UserExamServiceImpl extends BaseServiceImpl  implements UserExamService {

	@Autowired
	private UserExamDao userexamDao;

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(UserExamDo obj,int type){
		long id=  userexamDao.save(obj,UserExamDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = userexamDao.del(UserExamDo.getTable(),id);
		return i>0 ? true : false;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(UserExamDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = userexamDao.update(UserExamDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return userexamDao.update(UserExamDo.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(UserExamDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		userexamDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		userexamDao.updateByItems(UserExamDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public UserExamDo get(Serializable id){
		return userexamDao.get(id,UserExamDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<UserExamDo> getAll(){
		return userexamDao.getAll(UserExamDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(UserExamDo.getTable()).append(" where 1=1 ");
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
		int count = userexamDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public UserExamDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(UserExamDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<UserExamDo> list = userexamDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
  
  private RowMapper<UserExamDo> rowMapper = new RowMapper<UserExamDo>() {
		@Override
		public UserExamDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserExamDo userexam=new UserExamDo();
			userexam.setId(rs.getLong("id"));
			userexam.setUserid(rs.getLong("userid"));
			userexam.setExamid(rs.getLong("examid"));
			userexam.setUsetime(rs.getInt("usetime"));
			userexam.setScore(rs.getInt("score"));
			userexam.setVtime(rs.getInt("vtime"));
			userexam.setIorder(rs.getLong("iorder"));
			userexam.setStatus(rs.getShort("status"));
			userexam.setEdittime(rs.getTimestamp("edittime"));
			userexam.setSlock(rs.getShort("slock"));
			
			try {
				userexam.setExt_userName(rs.getString("ext_userName"));
				userexam.setExt_examName(rs.getString("ext_examName"));
			} catch (Exception ex) {}

			return userexam;
		}
	};
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.IUserExamService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<UserExamDo> getPage( Map<String, Object> kaywordMap ,int status,int slock, long start, int limit) {
		
	    StringBuilder sql = new StringBuilder("select ue.*,u.username as ext_userName,e.name as ext_examName from t_user_exam ue,t_user u,t_exam e where ue.userid=u.id and ue.examid=e.id");
		StringBuilder sqlCount = new StringBuilder("select count(ue.id) from t_user_exam ue,t_user u,t_exam e where ue.userid=u.id and ue.examid=e.id ");
		List<Object> params = new ArrayList<Object>();

		if(kaywordMap!=null) {
			if(null!=kaywordMap.get("username")&&StringUtils.isNotBlank(kaywordMap.get("username").toString())){
				sql.append(" and (u.username like ? )");
				sqlCount.append(" and (u.username like ? ) ");
				params.add("%"+kaywordMap.get("username").toString()+"%");
			}
			if(null!=kaywordMap.get("name")&&StringUtils.isNotBlank(kaywordMap.get("name").toString())){
				sql.append(" and (e.name like ? )");
				sqlCount.append(" and (e.name like ? ) ");
				params.add("%"+kaywordMap.get("name").toString()+"%");
			}
		}
		
		if (status > -1) {
			sql.append(" and ue.status =? ");
			sqlCount.append(" and ue.status=? ");
			params.add(status);
		}
		
		if (slock > -1) {
			sql.append(" and ue.slock =? ");
			sqlCount.append(" and ue.slock=? ");
			params.add(slock);
		}else{
			sql.append(" and ue.slock < 2 ");
			sqlCount.append(" and ue.slock < 2 ");
		}
		
		int count = userexamDao.queryForInt(sqlCount,params.toArray());
		List<UserExamDo> list = null;
		if(count > 0){
			sql.append(" order by ue.id desc limit ?,?");params.add(start);params.add(limit);
			list = userexamDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(UserExamDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public UserExamDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(UserExamDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<UserExamDo> list = userexamDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(UserExamDo userexamDo, UserExamDo vuserexamDo) {
		StringBuilder sql = new StringBuilder("update  ").append(UserExamDo.getTable()).append(" set iorder=? where id =?");
		userexamDao.executeUpdate(sql, userexamDo.getIorder(),vuserexamDo.getId());
		userexamDao.executeUpdate(sql, vuserexamDo.getIorder(),userexamDo.getId());
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.UserExamService#count(long, long)
	 */
	@Override
	public int count(long userid, long examid) {
		StringBuilder sql = new StringBuilder("select count(*) from t_user_exam  where userid=? and examid=? ");
		return userexamDao.queryForInt(sql, userid,examid);
	}
	
}