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
import cn.javaworker.yeming.ukeli.pojo.SubjectVideoDo;
import cn.javaworker.yeming.ukeli.dao.SubjectVideoDao;
import cn.javaworker.yeming.ukeli.service.SubjectVideoService;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.core.util.StrUtils;

/**
* 创建日期：2017-08-23 18:57:30
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
public class SubjectVideoServiceImpl extends BaseServiceImpl  implements SubjectVideoService {

	@Autowired
	private SubjectVideoDao subjectvideoDao;

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(SubjectVideoDo obj,int type){
		long id=  subjectvideoDao.save(obj,SubjectVideoDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}

	@Override
	public Long insert(SubjectVideoDo obj){
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("insert into  ").append(SubjectVideoDo.getTable()).append(" (subjectid,videoid)  values( ? , ?) " );
		params.add(obj.getSubjectid());params.add(obj.getVideoid());
		return (long) subjectvideoDao.executeUpdate(sql, params.toArray());
	}
	
	
	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = subjectvideoDao.del(SubjectVideoDo.getTable(),id);
		return i>0 ? true : false;
	}

	
	@Override
	@Transactional
	public long delete(Serializable id){
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("delete from  ").append(SubjectVideoDo.getTable()).append(" where id=?" );
		params.add(id);
		return (long) subjectvideoDao.executeUpdate(sql, params.toArray());
	}
	
	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(SubjectVideoDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = subjectvideoDao.update(SubjectVideoDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return subjectvideoDao.update(SubjectVideoDo.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(SubjectVideoDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		subjectvideoDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		subjectvideoDao.updateByItems(SubjectVideoDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public SubjectVideoDo get(Serializable id){
		return subjectvideoDao.get(id,SubjectVideoDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<SubjectVideoDo> getAll(){
		return subjectvideoDao.getAll(SubjectVideoDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(SubjectVideoDo.getTable()).append(" where 1=1 ");
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
		int count = subjectvideoDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public SubjectVideoDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(SubjectVideoDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<SubjectVideoDo> list = subjectvideoDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<SubjectVideoDo> getByItems(Map<String, Object> itemsMap) {
		StringBuilder sql =new StringBuilder("select * from ").append(SubjectVideoDo.getTable()).append(" where ");
		int i=1;
		for (Map.Entry<String, Object> entry : itemsMap.entrySet()) {
			  // System.out.println("key= " +  + " and value= " + entry.getValue());
			   if(itemsMap.size()-1==i){
				   sql.append( entry.getKey()).append("=").append(entry.getValue()).append(" and ");
			   }else{
				   sql.append( entry.getKey()).append("=").append(entry.getValue());
			   }
			   i++;
			  }
		sql.append("  order by id desc");
		List<SubjectVideoDo> list = subjectvideoDao.getList(sql, rowMapper);
		return list;
	}
	
  
  private RowMapper<SubjectVideoDo> rowMapper = new RowMapper<SubjectVideoDo>() {
		@Override
		public SubjectVideoDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			SubjectVideoDo subjectvideo=new SubjectVideoDo();
			subjectvideo.setId(rs.getLong("id"));
			subjectvideo.setSubjectid(rs.getLong("subjectid"));
			subjectvideo.setVideoid(rs.getLong("videoid"));

			return subjectvideo;
		}
	};
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.ISubjectVideoService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<SubjectVideoDo> getPage( String keyword,int status,int slock, long start, int limit) {
		
	   StringBuilder sql = new StringBuilder("select * from ").append(SubjectVideoDo.getTable()).append(" where 1=1 ");
		StringBuilder sqlCount = new StringBuilder("select count(id) from ").append(SubjectVideoDo.getTable()).append(" where 1=1 ");
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
		
		int count = subjectvideoDao.queryForInt(sqlCount,params.toArray());
		List<SubjectVideoDo> list = null;
		if(count > 0){
			sql.append(" order by id desc limit ?,?");params.add(start);params.add(limit);
			list = subjectvideoDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(SubjectVideoDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public SubjectVideoDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(SubjectVideoDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<SubjectVideoDo> list = subjectvideoDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(SubjectVideoDo subjectvideoDo, SubjectVideoDo vsubjectvideoDo) {
		StringBuilder sql = new StringBuilder("update  ").append(SubjectVideoDo.getTable()).append(" set iorder=? where id =?");
		subjectvideoDao.executeUpdate(sql, subjectvideoDo.getIorder(),vsubjectvideoDo.getId());
		subjectvideoDao.executeUpdate(sql, vsubjectvideoDo.getIorder(),subjectvideoDo.getId());
	}

	
	
}