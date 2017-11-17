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
import cn.javaworker.yeming.ukeli.pojo.ExamDo;
import cn.javaworker.yeming.ukeli.dao.ExamDao;
import cn.javaworker.yeming.ukeli.service.ExamService;
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
public class ExamServiceImpl extends BaseServiceImpl  implements ExamService {

	@Autowired
	private ExamDao examDao;

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(ExamDo obj,int type){
		long id=  examDao.save(obj,ExamDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = examDao.del(ExamDo.getTable(),id);
		return i>0 ? true : false;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(ExamDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = examDao.update(ExamDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return examDao.update(ExamDo.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(ExamDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		examDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		examDao.updateByItems(ExamDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public ExamDo get(Serializable id){
		return examDao.get(id,ExamDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<ExamDo> getAll(){
		return examDao.getAll(ExamDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(ExamDo.getTable()).append(" where 1=1 ");
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
		int count = examDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public ExamDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(ExamDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<ExamDo> list = examDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
  
  private RowMapper<ExamDo> rowMapper = new RowMapper<ExamDo>() {
		@Override
		public ExamDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExamDo exam=new ExamDo();
			exam.setId(rs.getLong("id"));
			exam.setName(rs.getString("name"));
			exam.setGradeid(rs.getLong("gradeid"));
			exam.setTimes(rs.getInt("times"));
			exam.setTotalexam(rs.getInt("totalexam"));
			exam.setRemark(rs.getString("remark"));
			exam.setIorder(rs.getLong("iorder"));
			exam.setStatus(rs.getShort("status"));
			exam.setEdittime(rs.getTimestamp("edittime"));
			exam.setSlock(rs.getShort("slock"));

			return exam;
		}
	};
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.IExamService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<ExamDo> getPage( String keyword,int status,int slock, long start, int limit) {
		
	   StringBuilder sql = new StringBuilder("select * from ").append(ExamDo.getTable()).append(" where 1=1 ");
		StringBuilder sqlCount = new StringBuilder("select count(id) from ").append(ExamDo.getTable()).append(" where 1=1 ");
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
		
		int count = examDao.queryForInt(sqlCount,params.toArray());
		List<ExamDo> list = null;
		if(count > 0){
			sql.append(" order by id desc limit ?,?");params.add(start);params.add(limit);
			list = examDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(ExamDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public ExamDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(ExamDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<ExamDo> list = examDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(ExamDo examDo, ExamDo vexamDo) {
		StringBuilder sql = new StringBuilder("update  ").append(ExamDo.getTable()).append(" set iorder=? where id =?");
		examDao.executeUpdate(sql, examDo.getIorder(),vexamDo.getId());
		examDao.executeUpdate(sql, vexamDo.getIorder(),examDo.getId());
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.ExamService#getList(long, int, int)
	 */
	@Override
	public List<ExamDo> getList(long gradeid, int start, int limit) {
		StringBuilder sql = new StringBuilder("select * from ").append(ExamDo.getTable()).append(" where 1=1  ");
		List<Object> params = new ArrayList<>();
		if(gradeid > 0) {
			sql.append(" and gradeid =? ");
			params.add(gradeid);
		}
		sql.append(" and slock <2 order by iorder desc limit ?,? ");
		params.add(start);params.add(limit);
		return examDao.getList(sql, rowMapper, params.toArray());
	}
	
}