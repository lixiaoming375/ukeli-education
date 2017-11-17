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
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.javaworker.yeming.core.jdbc.service.impl.BaseServiceImpl;
import cn.javaworker.yeming.core.util.StrUtils;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.ukeli.dao.SubjectOptionsDao;
import cn.javaworker.yeming.ukeli.pojo.SubjectOptionsDo;
import cn.javaworker.yeming.ukeli.service.SubjectOptionsService;

/**
* 创建日期：2017-09-23 16:04:08
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
public class SubjectOptionsServiceImpl extends BaseServiceImpl  implements SubjectOptionsService {

	@Autowired
	private SubjectOptionsDao subjectoptionsDao;

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(SubjectOptionsDo obj,int type){
		long id=  subjectoptionsDao.save(obj,SubjectOptionsDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}
	
	@Override
	@Transactional
	public void saveSubjectOptions(Map<String, Object> mapSubjectOptions,Serializable id,String answer) {
		if(mapSubjectOptions!=null&&mapSubjectOptions.size()>0) {
			if(StringUtils.isNoneBlank(answer)){
				updateSubjectAnswer(id,answer);
			}
			String sql = "delete from t_subject_options where subjectid=?";
			subjectoptionsDao.executeUpdate(new StringBuilder(sql), id);
			sql = "insert into t_subject_options(subjectid,optiionname,optioncontext) values(?,?,?)";
	        List<Object[]> list = new ArrayList<Object[]>(mapSubjectOptions.size());
	        for (Entry<String, Object> entry : mapSubjectOptions.entrySet()) {  
	        	list.add(new Object[] {id,entry.getKey(),entry.getValue()});
	        }  
	        subjectoptionsDao.updateBatch(sql, list);
		}
	}
	
	   private void updateSubjectAnswer(Serializable id,String answer){
		   String sql = "update  t_subject set answer=? where id=?";
		subjectoptionsDao.executeUpdate(new StringBuilder(sql),answer ,id);
	   }

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = subjectoptionsDao.del(SubjectOptionsDo.getTable(),id);
		return i>0 ? true : false;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(SubjectOptionsDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = subjectoptionsDao.update(SubjectOptionsDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return subjectoptionsDao.update(SubjectOptionsDo.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(SubjectOptionsDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		subjectoptionsDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		subjectoptionsDao.updateByItems(SubjectOptionsDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public SubjectOptionsDo get(Serializable id){
		return subjectoptionsDao.get(id,SubjectOptionsDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<SubjectOptionsDo> getAll(){
		return subjectoptionsDao.getAll(SubjectOptionsDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(SubjectOptionsDo.getTable()).append(" where 1=1 ");
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
		int count = subjectoptionsDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public SubjectOptionsDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(SubjectOptionsDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<SubjectOptionsDo> list = subjectoptionsDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	
	@Override
	public List<SubjectOptionsDo> getByItems(Map<String, Object> itemsMap) {
		StringBuilder sql =new StringBuilder("select * from ").append(SubjectOptionsDo.getTable()).append(" where ");
		int i=1;
		for (Map.Entry<String, Object> entry : itemsMap.entrySet()) {
			   if(itemsMap.size()-1==i){
				   sql.append( entry.getKey()).append("=").append(entry.getValue()).append(" and ");
			   }else{
				   sql.append( entry.getKey()).append("=").append(entry.getValue());
			   }
			   i++;
			  }
		sql.append("  order by id desc");
		List<SubjectOptionsDo> list = subjectoptionsDao.getList(sql, rowMapper);
		return list;
	}
  
  private RowMapper<SubjectOptionsDo> rowMapper = new RowMapper<SubjectOptionsDo>() {
		@Override
		public SubjectOptionsDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			SubjectOptionsDo subjectoptions=new SubjectOptionsDo();
			subjectoptions.setId(rs.getLong("id"));
			subjectoptions.setSubjectid(rs.getLong("subjectid"));
			subjectoptions.setOptiionname(rs.getString("optiionname"));
			subjectoptions.setOptioncontext(rs.getString("optioncontext"));
			subjectoptions.setViewtimes(rs.getInt("viewtimes"));
			subjectoptions.setIorder(rs.getLong("iorder"));
			subjectoptions.setStatus(rs.getShort("status"));
			subjectoptions.setEdittime(rs.getTimestamp("edittime"));
			subjectoptions.setSlock(rs.getShort("slock"));

			return subjectoptions;
		}
	};
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.ISubjectOptionsService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<SubjectOptionsDo> getPage( String keyword,int status,int slock, long start, int limit) {
		
	   StringBuilder sql = new StringBuilder("select * from ").append(SubjectOptionsDo.getTable()).append(" where 1=1 ");
		StringBuilder sqlCount = new StringBuilder("select count(id) from ").append(SubjectOptionsDo.getTable()).append(" where 1=1 ");
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
		
		int count = subjectoptionsDao.queryForInt(sqlCount,params.toArray());
		List<SubjectOptionsDo> list = null;
		if(count > 0){
			sql.append(" order by id desc limit ?,?");params.add(start);params.add(limit);
			list = subjectoptionsDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(SubjectOptionsDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public SubjectOptionsDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(SubjectOptionsDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<SubjectOptionsDo> list = subjectoptionsDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(SubjectOptionsDo subjectoptionsDo, SubjectOptionsDo vsubjectoptionsDo) {
		StringBuilder sql = new StringBuilder("update  ").append(SubjectOptionsDo.getTable()).append(" set iorder=? where id =?");
		subjectoptionsDao.executeUpdate(sql, subjectoptionsDo.getIorder(),vsubjectoptionsDo.getId());
		subjectoptionsDao.executeUpdate(sql, vsubjectoptionsDo.getIorder(),subjectoptionsDo.getId());
	}
	
}