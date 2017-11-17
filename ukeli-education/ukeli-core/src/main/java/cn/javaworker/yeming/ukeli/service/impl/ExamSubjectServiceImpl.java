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
import cn.javaworker.yeming.ukeli.pojo.ExamSubjectDo;
import cn.javaworker.yeming.ukeli.dao.ExamDao;
import cn.javaworker.yeming.ukeli.dao.ExamSubjectDao;
import cn.javaworker.yeming.ukeli.service.ExamSubjectService;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.core.util.StrUtils;

/**
* 创建日期：2017-09-06 13:18:22
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
public class ExamSubjectServiceImpl extends BaseServiceImpl  implements ExamSubjectService {

	@Autowired
	private ExamSubjectDao examsubjectDao;
	@Autowired
	private ExamDao examDao;
	

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(ExamSubjectDo obj,int type){
		long id=  examsubjectDao.save(obj,ExamSubjectDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}

	
	@Override
	@Transactional
	public void save(Long examId, String ids){
		if(StrUtils.isNoBlank(ids)) {
		ExamSubjectDo previousOne=getByItem("examid", examId);
		int indexSerial=1;
		if(null!=previousOne){
			indexSerial=previousOne.getSerial();
		}
		String[] idsArray = StringUtils.split(ids,",");
		String sql = "insert into t_exam_subject(examid,subjectid,serial) values(?,?,?)";
	    List<Object[]> list = new ArrayList<Object[]>(idsArray.length);
	        for (String subjectid : idsArray) {
	        	list.add(new Object[] {examId,subjectid,++indexSerial});
			}
	         examsubjectDao.updateBatch(sql, list);
		     sql="update t_exam set totalexam=totalexam+? where id=?";
		     List<Object> params = new ArrayList<Object>();
		     params.add(idsArray.length);
		     params.add(examId);
		     examDao.executeUpdate(new StringBuilder(sql), params.toArray());
		}
	}
	
	@Override
	@Transactional
	public void saveSubject(Long examId, Long subjectId,int score){
		ExamSubjectDo previousOne=getByItem("examid", examId);
		int indexSerial=1;
		if(null!=previousOne){
		indexSerial=previousOne.getSerial()+1;
		}
	    ExamSubjectDo examsubject= new ExamSubjectDo();
	    examsubject.setExamid(examId);
	    examsubject.setSubjectid(subjectId);
	    examsubject.setScore(score);
	    examsubject.setSerial(indexSerial);
	    examsubjectDao.save(examsubject, ExamSubjectDo.getTable(),1);
		String sql = "update t_exam set totalexam=totalexam+? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(1);
		params.add(examId);
		examDao.executeUpdate(new StringBuilder(sql), params.toArray());
	}
	
	
	
	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = examsubjectDao.del(ExamSubjectDo.getTable(),id);
		return i>0 ? true : false;
	}
	
	@Override
	@Transactional
	public void delete(Long examid,Long id){
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("delete from  ").append(ExamSubjectDo.getTable()).append(" where id=?" );
		params.add(id);
		examsubjectDao.executeUpdate(sql, params.toArray());
		String updatesql="update t_exam set totalexam=totalexam-1 where id=?";
	    List<Object> updateparams = new ArrayList<Object>();
	    updateparams.add(examid);
	    examDao.executeUpdate(new StringBuilder(updatesql), updateparams.toArray()); 
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(ExamSubjectDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = examsubjectDao.update(ExamSubjectDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return examsubjectDao.update(ExamSubjectDo.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(ExamSubjectDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		examsubjectDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		examsubjectDao.updateByItems(ExamSubjectDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public ExamSubjectDo get(Serializable id){
		return examsubjectDao.get(id,ExamSubjectDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<ExamSubjectDo> getAll(){
		return examsubjectDao.getAll(ExamSubjectDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(ExamSubjectDo.getTable()).append(" where 1=1 ");
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
		int count = examsubjectDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public ExamSubjectDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(ExamSubjectDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<ExamSubjectDo> list = examsubjectDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
  
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	public List<ExamSubjectDo> getByItems(Map<String, Object> itemsMap) {
		StringBuilder sql =new StringBuilder("select * from ").append(ExamSubjectDo.getTable()).append(" where ");
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
		List<ExamSubjectDo> list = examsubjectDao.getList(sql, rowMapper);
		return list;
	}
	
	
  private RowMapper<ExamSubjectDo> rowMapper = new RowMapper<ExamSubjectDo>() {
		@Override
		public ExamSubjectDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExamSubjectDo examsubject=new ExamSubjectDo();
			examsubject.setId(rs.getLong("id"));
			examsubject.setExamid(rs.getLong("examid"));
			examsubject.setSubjectid(rs.getLong("subjectid"));
			examsubject.setSerial(rs.getInt("serial"));
			examsubject.setIorder(rs.getLong("iorder"));
			examsubject.setStatus(rs.getShort("status"));
			examsubject.setEdittime(rs.getTimestamp("edittime"));
			examsubject.setSlock(rs.getShort("slock"));

			return examsubject;
		}
	};
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.IExamSubjectService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<ExamSubjectDo> getPage( String keyword,int status,int slock, long start, int limit) {
		
	   StringBuilder sql = new StringBuilder("select * from ").append(ExamSubjectDo.getTable()).append(" where 1=1 ");
		StringBuilder sqlCount = new StringBuilder("select count(id) from ").append(ExamSubjectDo.getTable()).append(" where 1=1 ");
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
		
		int count = examsubjectDao.queryForInt(sqlCount,params.toArray());
		List<ExamSubjectDo> list = null;
		if(count > 0){
			sql.append(" order by id desc limit ?,?");params.add(start);params.add(limit);
			list = examsubjectDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(ExamSubjectDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public ExamSubjectDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(ExamSubjectDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<ExamSubjectDo> list = examsubjectDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	@Transactional(readOnly=true)
	public ExamSubjectDo getByOrderSerialType(int serial,Long examid, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(ExamSubjectDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append("and examid=? and serial > ?  order by serial asc limit 0,1");
		}else{
			sql.append("and examid=? and serial <  ? order by serial desc limit 0,1");
		}
		List<ExamSubjectDo> list = examsubjectDao.getList(sql, rowMapper, examid,serial);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(ExamSubjectDo examsubjectDo, ExamSubjectDo vexamsubjectDo) {
		StringBuilder sql = new StringBuilder("update  ").append(ExamSubjectDo.getTable()).append(" set iorder=? where id =?");
		examsubjectDao.executeUpdate(sql, examsubjectDo.getIorder(),vexamsubjectDo.getId());
		examsubjectDao.executeUpdate(sql, vexamsubjectDo.getIorder(),examsubjectDo.getId());
	}
	
	
	@Override
	@Transactional
	public void orderSerial(ExamSubjectDo examsubjectDo, ExamSubjectDo vexamsubjectDo) {
		StringBuilder sql = new StringBuilder("update  ").append(ExamSubjectDo.getTable()).append(" set serial=? where id =?");
		examsubjectDao.executeUpdate(sql, examsubjectDo.getSerial(),vexamsubjectDo.getId());
		examsubjectDao.executeUpdate(sql, vexamsubjectDo.getSerial(),examsubjectDo.getId());
	}
}