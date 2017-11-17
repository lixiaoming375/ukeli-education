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
import cn.javaworker.yeming.ukeli.dao.SubjectVtypeDao;
import cn.javaworker.yeming.ukeli.pojo.SubjectVtypeDo;
import cn.javaworker.yeming.ukeli.service.SubjectVtypeService;

/**
* 创建日期：2017-09-09 13:03:16
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
public class SubjectVtypeServiceImpl extends BaseServiceImpl  implements SubjectVtypeService {

	@Autowired
	private SubjectVtypeDao subjectvtypeDao;

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(SubjectVtypeDo obj,int type){
		long id=  subjectvtypeDao.save(obj,SubjectVtypeDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = subjectvtypeDao.del(SubjectVtypeDo.getTable(),id);
		return i>0 ? true : false;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(SubjectVtypeDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = subjectvtypeDao.update(SubjectVtypeDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return subjectvtypeDao.update(SubjectVtypeDo.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(SubjectVtypeDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		subjectvtypeDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		subjectvtypeDao.updateByItems(SubjectVtypeDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public SubjectVtypeDo get(Serializable id){
		return subjectvtypeDao.get(id,SubjectVtypeDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<SubjectVtypeDo> getAll(){
		return subjectvtypeDao.getAll(SubjectVtypeDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(SubjectVtypeDo.getTable()).append(" where 1=1 ");
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
		int count = subjectvtypeDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public SubjectVtypeDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(SubjectVtypeDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<SubjectVtypeDo> list = subjectvtypeDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
  

  private RowMapper<SubjectVtypeDo> rowMapper = new RowMapper<SubjectVtypeDo>() {
		@Override
		public SubjectVtypeDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			SubjectVtypeDo subjectvtype=new SubjectVtypeDo();
			subjectvtype.setId(rs.getLong("id"));
			subjectvtype.setSubjectid(rs.getLong("subjectid"));
			subjectvtype.setVtype(rs.getInt("vtype"));
			try {
				subjectvtype.setExt_typename(rs.getString("name"));
			} catch (Exception ex) {}
			
			return subjectvtype;
		}
	};
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.ISubjectVtypeService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<SubjectVtypeDo> getPage( String keyword,int status,int slock, long start, int limit) {
		
	   StringBuilder sql = new StringBuilder("select * from ").append(SubjectVtypeDo.getTable()).append(" where 1=1 ");
		StringBuilder sqlCount = new StringBuilder("select count(id) from ").append(SubjectVtypeDo.getTable()).append(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();

		if(StrUtils.isNoBlank(keyword)) {
			sql.append(" and (name like ? )");
			sqlCount.append(" and (name like ? ) ");
			params.add("%"+keyword+"%");
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
		
		int count = subjectvtypeDao.queryForInt(sqlCount,params.toArray());
		List<SubjectVtypeDo> list = null;
		if(count > 0){
			sql.append(" order by id desc limit ?,?");params.add(start);params.add(limit);
			list = subjectvtypeDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(SubjectVtypeDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public SubjectVtypeDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(SubjectVtypeDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<SubjectVtypeDo> list = subjectvtypeDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(SubjectVtypeDo subjectvtypeDo, SubjectVtypeDo vsubjectvtypeDo) {
		StringBuilder sql = new StringBuilder("update  ").append(SubjectVtypeDo.getTable()).append(" set iorder=? where id =?");
		subjectvtypeDao.executeUpdate(sql, subjectvtypeDo.getIorder(),vsubjectvtypeDo.getId());
		subjectvtypeDao.executeUpdate(sql, vsubjectvtypeDo.getIorder(),subjectvtypeDo.getId());
	}

	@Override
	public Map<Long, String> getMapBySubjectids(List<Object> ids) {
		Map<Long, String> map = new HashMap<>();
		if(null == ids) {
			return map;
		}
		if(ids.size()  <1) {
			return map;
		}
		StringBuilder sql = new StringBuilder("SELECT t.*,t1.name from t_subject_vtype t left JOIN t_clazz t1 on t.vtype =t1.id where subjectid in(:subjectid)");
		
		List<SubjectVtypeDo> list =  subjectvtypeDao.getListByIds(sql, rowMapper, "subjectid", ids);
		for (SubjectVtypeDo subjectVtypeDo : list) {
			String name  =subjectVtypeDo.getExt_typename();
			Long id = subjectVtypeDo.getSubjectid();
			String vanme = map.get(id);
			if(StringUtils.isNoneBlank(name)) {
				if(StringUtils.isNotBlank(vanme)) {
					map.put(id, vanme + "," + name);
				}else {
					map.put(id, name);
				}
			}
		}
		return map;
	}
	
}