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
import cn.javaworker.yeming.ukeli.dao.UserKnowledgeDao;
import cn.javaworker.yeming.ukeli.pojo.KnowledgeDo;
import cn.javaworker.yeming.ukeli.pojo.KnowledgeSubjectDo;
import cn.javaworker.yeming.ukeli.pojo.SubjectDo;
import cn.javaworker.yeming.ukeli.pojo.UserDo;
import cn.javaworker.yeming.ukeli.pojo.UserKnowledgeDo;
import cn.javaworker.yeming.ukeli.pojo.UserSubjectDo;
import cn.javaworker.yeming.ukeli.pojo.UserKnowledgeDo;
import cn.javaworker.yeming.ukeli.service.UserKnowledgeService;

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
public class UserKnowledgeServiceImpl extends BaseServiceImpl  implements UserKnowledgeService {

	@Autowired
	private UserKnowledgeDao userknowledgeDao;

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(UserKnowledgeDo obj,int type){
		long id=  userknowledgeDao.save(obj,UserKnowledgeDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = userknowledgeDao.del(UserKnowledgeDo.getTable(),id);
		return i>0 ? true : false;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(UserKnowledgeDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = userknowledgeDao.update(UserKnowledgeDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return userknowledgeDao.update(UserKnowledgeDo.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(UserKnowledgeDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		userknowledgeDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		userknowledgeDao.updateByItems(UserKnowledgeDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public UserKnowledgeDo get(Serializable id){
		return userknowledgeDao.get(id,UserKnowledgeDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<UserKnowledgeDo> getAll(){
		return userknowledgeDao.getAll(UserKnowledgeDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(UserKnowledgeDo.getTable()).append(" where 1=1 ");
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
		int count = userknowledgeDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public UserKnowledgeDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(UserKnowledgeDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<UserKnowledgeDo> list = userknowledgeDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
  
  private RowMapper<UserKnowledgeDo> rowMapper = new RowMapper<UserKnowledgeDo>() {
		@Override
		public UserKnowledgeDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserKnowledgeDo userknowledge=new UserKnowledgeDo();
			userknowledge.setId(rs.getLong("id"));
			userknowledge.setUserid(rs.getLong("userid"));
			userknowledge.setKnowledgeid(rs.getLong("knowledgeid"));
			userknowledge.setIsdone(rs.getString("isdone"));
			userknowledge.setIsadd(rs.getString("isadd"));
			userknowledge.setIorder(rs.getLong("iorder"));
			userknowledge.setStatus(rs.getShort("status"));
			userknowledge.setEdittime(rs.getTimestamp("edittime"));
			userknowledge.setSlock(rs.getShort("slock"));

			return userknowledge;
		}
	};
	
	private RowMapper<UserKnowledgeDo> rowMapper1 = new RowMapper<UserKnowledgeDo>() {
		@Override
		public UserKnowledgeDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserKnowledgeDo userknowledge=new UserKnowledgeDo();
			userknowledge.setId(rs.getLong("id"));
			userknowledge.setUserid(rs.getLong("userid"));
			userknowledge.setExt_username(rs.getString("ext_username"));
			userknowledge.setExt_nickname(rs.getString("ext_nickname"));
			userknowledge.setKnowledgeid(rs.getLong("knowledgeid"));
			userknowledge.setExt_nodename(rs.getString("ext_nodename"));
			userknowledge.setIsdone(rs.getString("isdone"));
			userknowledge.setIsadd(rs.getString("isadd"));
			userknowledge.setIorder(rs.getLong("iorder"));
			userknowledge.setStatus(rs.getShort("status"));
			userknowledge.setEdittime(rs.getTimestamp("edittime"));
			userknowledge.setSlock(rs.getShort("slock"));
			return userknowledge;
		}
	};
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.IUserSubjectService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<UserKnowledgeDo> getPage( String username,String nodename,String isdone,String isadd, int status,int slock, long start, int limit) {
		
	   StringBuilder sql = new StringBuilder("select t1.*,t2.username as ext_username,t2.nickname ext_nickname,t3.nodename as ext_nodename from t_user_knowledge t1,t_user t2,t_knowledge t3  where t1.userid=t2.id and t1.knowledgeid=t3.id");
		StringBuilder sqlCount = new StringBuilder("select count(t1.id) from t_user_knowledge t1,t_user t2,t_knowledge t3 where t1.userid=t2.id and t1.knowledgeid=t3.id ");
		List<Object> params = new ArrayList<Object>();

		if(StrUtils.isNoBlank(username )) {
			sql.append(" and (t2.username like ? )");
			sqlCount.append(" and (t2.username like ? ) ");
			params.add("%"+username+"%");
		}
		if(StrUtils.isNoBlank(nodename )) {
			sql.append(" and (t3.nodename like ? )");
			sqlCount.append(" and (t3.nodename like ? ) ");
			params.add("%"+nodename+"%");
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
		
		int count = userknowledgeDao.queryForInt(sqlCount,params.toArray());
		List<UserKnowledgeDo> list = null;
		if(count > 0){
			sql.append(" order by t1.id desc limit ?,?");params.add(start);params.add(limit);
			list = userknowledgeDao.getList(sql,rowMapper1,params.toArray());
		}
		return Page.getPage(UserKnowledgeDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public UserKnowledgeDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(UserKnowledgeDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<UserKnowledgeDo> list = userknowledgeDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(UserKnowledgeDo userknowledgeDo, UserKnowledgeDo vuserknowledgeDo) {
		StringBuilder sql = new StringBuilder("update  ").append(UserKnowledgeDo.getTable()).append(" set iorder=? where id =?");
		userknowledgeDao.executeUpdate(sql, userknowledgeDo.getIorder(),vuserknowledgeDo.getId());
		userknowledgeDao.executeUpdate(sql, vuserknowledgeDo.getIorder(),userknowledgeDo.getId());
	}
	
	@Override
	@Transactional
	public UserKnowledgeDo addUserKnowledge(UserDo user, KnowledgeDo knowledge,int type,String isdone) {
		StringBuilder sql = new StringBuilder("select * from ").append(UserKnowledgeDo.getTable()).append(" where 1=1 and userid= ? and knowledgeid= ?");
		List<Object> params = new ArrayList<Object>();
		params.add(user.getId());
		params.add(knowledge.getId());
		List<UserKnowledgeDo> list = userknowledgeDao.getList(sql,rowMapper,params.toArray());
		UserKnowledgeDo UserKnowledge=null;
		if(null!=list&&list.size()==0){
		    UserKnowledge=new UserKnowledgeDo();
			UserKnowledge.setUserid(user.getId());
			UserKnowledge.setKnowledgeid(knowledge.getId());
			UserKnowledge.setAdder(user.getUsername());
			UserKnowledge.setAdderid(user.getId());
			UserKnowledge.setAddtime(new Date());
			if(type==1){
				UserKnowledge.setIsadd("1");
			}else if(type==0){
				UserKnowledge.setIsdone("1");
			}
			userknowledgeDao.save(UserKnowledge, UserKnowledgeDo.getTable());
		}else if(list.size()>0){
			UserKnowledge=list.get(0);
			Map<String,Object> map=new HashMap<String,Object>();
			if(type==1){
				map.put("isadd", "1");
			}else if(type==0){
				if(StringUtils.isNoneBlank(isdone)&&isdone.equals("1")){
					map.put("isdone", "0");
				}else if(StringUtils.isNoneBlank(isdone)&&isdone.equals("0")){
					map.put("isdone", "1");
				}
			}
			userknowledgeDao.update(UserKnowledgeDo.getTable(), map, UserKnowledge.getId());
		}
		return UserKnowledge;
	}
	
	
	
	@Override
	@Transactional
	public UserKnowledgeDo getUserKnowledge(long userid, long knowledgeid){
		StringBuilder sql = new StringBuilder("select * from ").append(UserKnowledgeDo.getTable()).append(" where 1=1 and userid= ? and knowledgeid= ?");
		List<Object> params = new ArrayList<Object>();
		params.add(userid);
		params.add(knowledgeid);
		List<UserKnowledgeDo> list = userknowledgeDao.getList(sql,rowMapper,params.toArray());
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}