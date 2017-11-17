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
import cn.javaworker.yeming.ukeli.pojo.GradeDo;
import cn.javaworker.yeming.ukeli.dao.GradeDao;
import cn.javaworker.yeming.ukeli.service.GradeService;
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
public class GradeServiceImpl extends BaseServiceImpl  implements GradeService {

	@Autowired
	private GradeDao gradeDao;

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(GradeDo obj,int type){
		long id=  gradeDao.save(obj,GradeDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = gradeDao.del(GradeDo.getTable(),id);
		return i>0 ? true : false;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(GradeDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = gradeDao.update(GradeDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return gradeDao.update(GradeDo.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(GradeDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		gradeDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		gradeDao.updateByItems(GradeDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public GradeDo get(Serializable id){
		return gradeDao.get(id,GradeDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<GradeDo> getAll(){
		return gradeDao.getAll(GradeDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(GradeDo.getTable()).append(" where 1=1 ");
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
		int count = gradeDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public GradeDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(GradeDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<GradeDo> list = gradeDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
  
  private RowMapper<GradeDo> rowMapper = new RowMapper<GradeDo>() {
		@Override
		public GradeDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			GradeDo grade=new GradeDo();
			grade.setId(rs.getLong("id"));
			grade.setGradename(rs.getString("gradename"));
			grade.setIorder(rs.getLong("iorder"));
			grade.setStatus(rs.getShort("status"));
			grade.setEdittime(rs.getTimestamp("edittime"));
			grade.setSlock(rs.getShort("slock"));

			return grade;
		}
	};
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.IGradeService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<GradeDo> getPage( String keyword,int status,int slock, long start, int limit) {
		
	   StringBuilder sql = new StringBuilder("select * from ").append(GradeDo.getTable()).append(" where 1=1 ");
		StringBuilder sqlCount = new StringBuilder("select count(id) from ").append(GradeDo.getTable()).append(" where 1=1 ");
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
		
		int count = gradeDao.queryForInt(sqlCount,params.toArray());
		List<GradeDo> list = null;
		if(count > 0){
			sql.append(" order by id desc limit ?,?");params.add(start);params.add(limit);
			list = gradeDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(GradeDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public GradeDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(GradeDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<GradeDo> list = gradeDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(GradeDo gradeDo, GradeDo vgradeDo) {
		StringBuilder sql = new StringBuilder("update  ").append(GradeDo.getTable()).append(" set iorder=? where id =?");
		gradeDao.executeUpdate(sql, gradeDo.getIorder(),vgradeDo.getId());
		gradeDao.executeUpdate(sql, vgradeDo.getIorder(),gradeDo.getId());
	}
	
}