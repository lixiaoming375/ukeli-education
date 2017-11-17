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
import cn.javaworker.yeming.ukeli.dao.KnowledgeDao;
import cn.javaworker.yeming.ukeli.dao.KnowledgeSubjectDao;
import cn.javaworker.yeming.ukeli.pojo.ExamSubjectDo;
import cn.javaworker.yeming.ukeli.pojo.KnowledgeSubjectDo;
import cn.javaworker.yeming.ukeli.service.KnowledgeSubjectService;

/**
* 创建日期：2017-09-09 22:05:11
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
public class KnowledgeSubjectServiceImpl extends BaseServiceImpl  implements KnowledgeSubjectService {

	@Autowired
	private KnowledgeSubjectDao knowledgesubjectDao;
	
	@Autowired
	private KnowledgeDao knowledgeDao;

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(KnowledgeSubjectDo obj,int type){
		long id=  knowledgesubjectDao.save(obj,KnowledgeSubjectDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}
	
	
	@Override
	@Transactional
	public void save(Long knowledgeId, String ids){
		if(StrUtils.isNoBlank(ids)) {
		KnowledgeSubjectDo previousOne=getByItem("knowledgeid", knowledgeId);
		int indexSerial=1;
		if(null!=previousOne){
			indexSerial=previousOne.getSerial();
		}
		String[] idsArray = StringUtils.split(ids,",");
		String sql = "insert into t_knowledge_subject(knowledgeid,subjectid,serial) values(?,?,?)";
	    List<Object[]> list = new ArrayList<Object[]>(idsArray.length);
	        for (String subjectid : idsArray) {
	        	list.add(new Object[] {knowledgeId,subjectid,++indexSerial});
			}
	        knowledgesubjectDao.updateBatch(sql, list);
		     sql="update t_knowledge set totalexam=totalexam+? where id=?";
		     List<Object> params = new ArrayList<Object>();
		     params.add(idsArray.length);
		     params.add(knowledgeId);
		     knowledgeDao.executeUpdate(new StringBuilder(sql), params.toArray());
		}
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = knowledgesubjectDao.del(KnowledgeSubjectDo.getTable(),id);
		return i>0 ? true : false;
	}
	
	@Override
	@Transactional
	public void delete(Long knowledgeid,Long id){
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("delete from  ").append(KnowledgeSubjectDo.getTable()).append(" where id=?" );
		params.add(id);
		knowledgesubjectDao.executeUpdate(sql, params.toArray());
		String updatesql="update t_knowledge set totalexam=totalexam-1 where id=?";
	    List<Object> updateparams = new ArrayList<Object>();
	    updateparams.add(knowledgeid);
	    knowledgeDao.executeUpdate(new StringBuilder(updatesql), updateparams.toArray()); 
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(KnowledgeSubjectDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = knowledgesubjectDao.update(KnowledgeSubjectDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return knowledgesubjectDao.update(KnowledgeSubjectDo.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(KnowledgeSubjectDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		knowledgesubjectDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		knowledgesubjectDao.updateByItems(KnowledgeSubjectDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public KnowledgeSubjectDo get(Serializable id){
		return knowledgesubjectDao.get(id,KnowledgeSubjectDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<KnowledgeSubjectDo> getAll(){
		return knowledgesubjectDao.getAll(KnowledgeSubjectDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(KnowledgeSubjectDo.getTable()).append(" where 1=1 ");
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
		int count = knowledgesubjectDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public KnowledgeSubjectDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(KnowledgeSubjectDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<KnowledgeSubjectDo> list = knowledgesubjectDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	public List<KnowledgeSubjectDo> getByItems(Map<String, Object> itemsMap) {
		StringBuilder sql =new StringBuilder("select * from ").append(KnowledgeSubjectDo.getTable()).append(" where ");
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
		List<KnowledgeSubjectDo> list = knowledgesubjectDao.getList(sql, rowMapper);
		return list;
	}
  
  private RowMapper<KnowledgeSubjectDo> rowMapper = new RowMapper<KnowledgeSubjectDo>() {
		@Override
		public KnowledgeSubjectDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			KnowledgeSubjectDo knowledgesubject=new KnowledgeSubjectDo();
			knowledgesubject.setId(rs.getLong("id"));
			knowledgesubject.setKnowledgeid(rs.getLong("knowledgeid"));
			knowledgesubject.setSubjectid(rs.getLong("subjectid"));
			knowledgesubject.setSerial(rs.getInt("serial"));
			knowledgesubject.setIorder(rs.getLong("iorder"));
			knowledgesubject.setStatus(rs.getShort("status"));
			knowledgesubject.setEdittime(rs.getTimestamp("edittime"));
			knowledgesubject.setSlock(rs.getShort("slock"));

			return knowledgesubject;
		}
	};
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.IKnowledgeSubjectService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<KnowledgeSubjectDo> getPage( String keyword,int status,int slock, long start, int limit) {
		
	   StringBuilder sql = new StringBuilder("select * from ").append(KnowledgeSubjectDo.getTable()).append(" where 1=1 ");
		StringBuilder sqlCount = new StringBuilder("select count(id) from ").append(KnowledgeSubjectDo.getTable()).append(" where 1=1 ");
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
		
		int count = knowledgesubjectDao.queryForInt(sqlCount,params.toArray());
		List<KnowledgeSubjectDo> list = null;
		if(count > 0){
			sql.append(" order by id desc limit ?,?");params.add(start);params.add(limit);
			list = knowledgesubjectDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(KnowledgeSubjectDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public KnowledgeSubjectDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(KnowledgeSubjectDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<KnowledgeSubjectDo> list = knowledgesubjectDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	@Transactional(readOnly=true)
	public KnowledgeSubjectDo getByOrderSerialType(int serial,Long knowledgeid, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(KnowledgeSubjectDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append("and knowledgeid=? and serial > ?  order by serial asc limit 0,1");
		}else{
			sql.append("and knowledgeid=? and serial <  ? order by serial desc limit 0,1");
		}
//		Map<String,Object> param=new HashMap<String,Object>();
//		param.put("knowledgeid", knowledgeid);
//		param.put("serial", serial);
		List<KnowledgeSubjectDo> list = knowledgesubjectDao.getList(sql, rowMapper, knowledgeid,serial);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(KnowledgeSubjectDo knowledgesubjectDo, KnowledgeSubjectDo vknowledgesubjectDo) {
		StringBuilder sql = new StringBuilder("update  ").append(KnowledgeSubjectDo.getTable()).append(" set iorder=? where id =?");
		knowledgesubjectDao.executeUpdate(sql, knowledgesubjectDo.getIorder(),vknowledgesubjectDo.getId());
		knowledgesubjectDao.executeUpdate(sql, vknowledgesubjectDo.getIorder(),knowledgesubjectDo.getId());
	}
	
	
	@Override
	@Transactional
	public void orderSerial(KnowledgeSubjectDo knowledgesubjectDo, KnowledgeSubjectDo vknowledgesubjectDo) {
		StringBuilder sql = new StringBuilder("update  ").append(KnowledgeSubjectDo.getTable()).append(" set serial=? where id =?");
		knowledgesubjectDao.executeUpdate(sql, knowledgesubjectDo.getSerial(),vknowledgesubjectDo.getId());
		knowledgesubjectDao.executeUpdate(sql, vknowledgesubjectDo.getSerial(),knowledgesubjectDo.getId());
	}
	
	
	@Override
	@Transactional
	public void saveSubject(Long knowledgeId, Long subjectId,int score){
		KnowledgeSubjectDo previousOne=getByItem("knowledgeid", knowledgeId);
		int indexSerial=1;
		if(null!=previousOne){
		indexSerial=previousOne.getSerial()+1;
		}
		KnowledgeSubjectDo knowledgesubject= new KnowledgeSubjectDo();
		knowledgesubject.setKnowledgeid(knowledgeId);
		knowledgesubject.setSubjectid(subjectId);
		knowledgesubject.setScore(score);
		knowledgesubject.setSerial(indexSerial);
		knowledgesubjectDao.save(knowledgesubject, KnowledgeSubjectDo.getTable(),1);
		String sql = "update t_knowledge set totalexam=totalexam+? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(1);
		params.add(knowledgeId);
		knowledgeDao.executeUpdate(new StringBuilder(sql), params.toArray());
	}
}