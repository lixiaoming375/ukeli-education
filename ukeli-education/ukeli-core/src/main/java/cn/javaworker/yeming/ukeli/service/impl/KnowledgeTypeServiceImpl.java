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
import cn.javaworker.yeming.ukeli.pojo.KnowledgeTypeDo;
import cn.javaworker.yeming.ukeli.dao.KnowledgeTypeDao;
import cn.javaworker.yeming.ukeli.service.KnowledgeTypeService;
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
public class KnowledgeTypeServiceImpl extends BaseServiceImpl  implements KnowledgeTypeService {

	@Autowired
	private KnowledgeTypeDao knowledgetypeDao;

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(KnowledgeTypeDo obj,int type){
		long id=  knowledgetypeDao.save(obj,KnowledgeTypeDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = knowledgetypeDao.del(KnowledgeTypeDo.getTable(),id);
		return i>0 ? true : false;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(KnowledgeTypeDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = knowledgetypeDao.update(KnowledgeTypeDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return knowledgetypeDao.update(KnowledgeTypeDo.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(KnowledgeTypeDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		knowledgetypeDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		knowledgetypeDao.updateByItems(KnowledgeTypeDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public KnowledgeTypeDo get(Serializable id){
		return knowledgetypeDao.get(id,KnowledgeTypeDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<KnowledgeTypeDo> getAll(){
		return knowledgetypeDao.getAll(KnowledgeTypeDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(KnowledgeTypeDo.getTable()).append(" where 1=1 ");
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
		int count = knowledgetypeDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public KnowledgeTypeDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(KnowledgeTypeDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<KnowledgeTypeDo> list = knowledgetypeDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
  
  private RowMapper<KnowledgeTypeDo> rowMapper = new RowMapper<KnowledgeTypeDo>() {
		@Override
		public KnowledgeTypeDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			KnowledgeTypeDo knowledgetype=new KnowledgeTypeDo();
			knowledgetype.setId(rs.getLong("id"));
			knowledgetype.setTypename(rs.getString("typename"));
			knowledgetype.setIorder(rs.getLong("iorder"));
			knowledgetype.setStatus(rs.getShort("status"));
			knowledgetype.setEdittime(rs.getTimestamp("edittime"));
			knowledgetype.setSlock(rs.getShort("slock"));

			return knowledgetype;
		}
	};
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.IKnowledgeTypeService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<KnowledgeTypeDo> getPage( String keyword,int status,int slock, long start, int limit) {
		
	   StringBuilder sql = new StringBuilder("select * from ").append(KnowledgeTypeDo.getTable()).append(" where 1=1 ");
		StringBuilder sqlCount = new StringBuilder("select count(id) from ").append(KnowledgeTypeDo.getTable()).append(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();

		if(StrUtils.isNoBlank(keyword)) {
			sql.append(" and (typename like ? )");
			sqlCount.append(" and (typename like ? ) ");
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
		
		int count = knowledgetypeDao.queryForInt(sqlCount,params.toArray());
		List<KnowledgeTypeDo> list = null;
		if(count > 0){
			sql.append(" order by id desc limit ?,?");params.add(start);params.add(limit);
			list = knowledgetypeDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(KnowledgeTypeDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public KnowledgeTypeDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(KnowledgeTypeDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<KnowledgeTypeDo> list = knowledgetypeDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(KnowledgeTypeDo knowledgetypeDo, KnowledgeTypeDo vknowledgetypeDo) {
		StringBuilder sql = new StringBuilder("update  ").append(KnowledgeTypeDo.getTable()).append(" set iorder=? where id =?");
		knowledgetypeDao.executeUpdate(sql, knowledgetypeDo.getIorder(),vknowledgetypeDo.getId());
		knowledgetypeDao.executeUpdate(sql, vknowledgetypeDo.getIorder(),knowledgetypeDo.getId());
	}
	
}