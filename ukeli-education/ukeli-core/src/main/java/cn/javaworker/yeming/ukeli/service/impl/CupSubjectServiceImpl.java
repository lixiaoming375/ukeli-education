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
import cn.javaworker.yeming.ukeli.pojo.CupSubjectDo;
import cn.javaworker.yeming.ukeli.dao.CupDao;
import cn.javaworker.yeming.ukeli.dao.CupSubjectDao;
import cn.javaworker.yeming.ukeli.service.CupSubjectService;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.core.util.StrUtils;

/**
* 创建日期：2017-09-08 14:05:56
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
public class CupSubjectServiceImpl extends BaseServiceImpl  implements CupSubjectService {

	@Autowired
	private CupSubjectDao cupsubjectDao;
	@Autowired
	private CupDao cupDao;

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(CupSubjectDo obj,int type){
		long id=  cupsubjectDao.save(obj,CupSubjectDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}
	
	
	@Override
	@Transactional
	public void save(Long cupId, String ids){
		if(StrUtils.isNoBlank(ids)) {
		CupSubjectDo previousOne=getByItem("cupid", cupId);
		int indexSerial=1;
		if(null!=previousOne){
			indexSerial=previousOne.getSerial();
		}
		String[] idsArray = StringUtils.split(ids,",");
		String sql = "insert into t_cup_subject(cupid,subjectid,serial) values(?,?,?)";
	    List<Object[]> list = new ArrayList<Object[]>(idsArray.length);
	        for (String subjectid : idsArray) {
	        	list.add(new Object[] {cupId,subjectid,++indexSerial});
			}
	         cupsubjectDao.updateBatch(sql, list);
		     sql="update t_cup set totalsubject=totalsubject+? where id=?";
		     List<Object> params = new ArrayList<Object>();
		     params.add(idsArray.length);
		     params.add(cupId);
		     cupDao.executeUpdate(new StringBuilder(sql), params.toArray());
		}
	}

	@Override
	@Transactional
	public void saveSubject(Long cupId, Long subjectId,int score){
		CupSubjectDo previousOne=getByItem("cupid", cupId);
		int indexSerial=1;
		if(null!=previousOne){
		indexSerial=previousOne.getSerial()+1;
		}
		CupSubjectDo cupsubject= new CupSubjectDo();
		cupsubject.setCupid(cupId);
		cupsubject.setSubjectid(subjectId);
		cupsubject.setScore(score);
		cupsubject.setSerial(indexSerial);
		cupsubjectDao.save(cupsubject, CupSubjectDo.getTable(),1);
		String sql = "update t_cup set totalsubject=totalsubject+? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(1);
		params.add(cupId);
		cupDao.executeUpdate(new StringBuilder(sql), params.toArray());
	}
	
	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = cupsubjectDao.del(CupSubjectDo.getTable(),id);
		return i>0 ? true : false;
	}
	
	@Override
	@Transactional
	public void delete(Long cupid,Long id){
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("delete from  ").append(CupSubjectDo.getTable()).append(" where id=?" );
		params.add(id);
		cupsubjectDao.executeUpdate(sql, params.toArray());
		String updatesql="update t_cup set totalsubject=totalsubject-1 where id=?";
	    List<Object> updateparams = new ArrayList<Object>();
	    updateparams.add(cupid);
	    cupDao.executeUpdate(new StringBuilder(updatesql), updateparams.toArray()); 
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(CupSubjectDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = cupsubjectDao.update(CupSubjectDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return cupsubjectDao.update(CupSubjectDo.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(CupSubjectDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		cupsubjectDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		cupsubjectDao.updateByItems(CupSubjectDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public CupSubjectDo get(Serializable id){
		return cupsubjectDao.get(id,CupSubjectDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<CupSubjectDo> getAll(){
		return cupsubjectDao.getAll(CupSubjectDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(CupSubjectDo.getTable()).append(" where 1=1 ");
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
		int count = cupsubjectDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public CupSubjectDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(CupSubjectDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<CupSubjectDo> list = cupsubjectDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	public List<CupSubjectDo> getByItems(Map<String, Object> itemsMap) {
		StringBuilder sql =new StringBuilder("select * from ").append(CupSubjectDo.getTable()).append(" where ");
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
		List<CupSubjectDo> list = cupsubjectDao.getList(sql, rowMapper);
		return list;
	}
  
  private RowMapper<CupSubjectDo> rowMapper = new RowMapper<CupSubjectDo>() {
		@Override
		public CupSubjectDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CupSubjectDo cupsubject=new CupSubjectDo();
			cupsubject.setId(rs.getLong("id"));
			cupsubject.setCupid(rs.getLong("cupid"));
			cupsubject.setSubjectid(rs.getLong("subjectid"));
			cupsubject.setSerial(rs.getInt("serial"));
			cupsubject.setIorder(rs.getLong("iorder"));
			cupsubject.setStatus(rs.getShort("status"));
			cupsubject.setEdittime(rs.getTimestamp("edittime"));
			cupsubject.setSlock(rs.getShort("slock"));

			return cupsubject;
		}
	};
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.ICupSubjectService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<CupSubjectDo> getPage( String keyword,int status,int slock, long start, int limit) {
		
	   StringBuilder sql = new StringBuilder("select * from ").append(CupSubjectDo.getTable()).append(" where 1=1 ");
		StringBuilder sqlCount = new StringBuilder("select count(id) from ").append(CupSubjectDo.getTable()).append(" where 1=1 ");
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
		
		int count = cupsubjectDao.queryForInt(sqlCount,params.toArray());
		List<CupSubjectDo> list = null;
		if(count > 0){
			sql.append(" order by id desc limit ?,?");params.add(start);params.add(limit);
			list = cupsubjectDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(CupSubjectDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public CupSubjectDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(CupSubjectDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<CupSubjectDo> list = cupsubjectDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	@Transactional(readOnly=true)
	public CupSubjectDo getByOrderSerialType(int serial,Long cupid, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(CupSubjectDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append("and cupid=? and serial > ?  order by serial asc limit 0,1");
		}else{
			sql.append("and cupid=? and serial <  ? order by serial desc limit 0,1");
		}
		List<CupSubjectDo> list = cupsubjectDao.getList(sql, rowMapper, cupid,serial);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}


	@Override
	@Transactional
	public void order(CupSubjectDo cupsubjectDo, CupSubjectDo vcupsubjectDo) {
		StringBuilder sql = new StringBuilder("update  ").append(CupSubjectDo.getTable()).append(" set iorder=? where id =?");
		cupsubjectDao.executeUpdate(sql, cupsubjectDo.getIorder(),vcupsubjectDo.getId());
		cupsubjectDao.executeUpdate(sql, vcupsubjectDo.getIorder(),cupsubjectDo.getId());
	}
	
	@Override
	@Transactional
	public void orderSerial(CupSubjectDo cupsubjectDo, CupSubjectDo vcupsubjectDo) {
		StringBuilder sql = new StringBuilder("update  ").append(CupSubjectDo.getTable()).append(" set serial=? where id =?");
		cupsubjectDao.executeUpdate(sql, cupsubjectDo.getSerial(),vcupsubjectDo.getId());
		cupsubjectDao.executeUpdate(sql, vcupsubjectDo.getSerial(),cupsubjectDo.getId());
	}
	
}