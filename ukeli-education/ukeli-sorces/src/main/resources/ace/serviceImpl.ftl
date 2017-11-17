<#include "copyright.ftl"/>

package ${spackage}.service.impl;

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
import ${spackage}.pojo.${pojo}Do;
import ${spackage}.dao.${pojo}Dao;
import ${spackage}.service.${pojo}Service;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.core.util.StrUtils;

<#include "version.ftl"/>

@Service
@Transactional
public class ${pojo}ServiceImpl extends BaseServiceImpl  implements ${pojo}Service {

	@Autowired
	private ${pojo}Dao ${pojo?lower_case}Dao;

	/* (non-Javadoc)
	* @see ${spackage}.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(${pojo}Do obj,int type){
		long id=  ${pojo?lower_case}Dao.save(obj,${pojo}Do.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}

	/* (non-Javadoc)
	* @see ${spackage}.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = ${pojo?lower_case}Dao.del(${pojo}Do.getTable(),id);
		return i>0 ? true : false;
	}

	/* (non-Javadoc)
	* @see ${spackage}.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(${pojo}Do obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = ${pojo?lower_case}Dao.update(${pojo}Do.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return ${pojo?lower_case}Dao.update(${pojo}Do.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(${pojo}Do.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		${pojo?lower_case}Dao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		${pojo?lower_case}Dao.updateByItems(${pojo}Do.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see ${spackage}.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public ${pojo}Do get(Serializable id){
		return ${pojo?lower_case}Dao.get(id,${pojo}Do.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see ${spackage}.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<${pojo}Do> getAll(){
		return ${pojo?lower_case}Dao.getAll(${pojo}Do.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(${pojo}Do.getTable()).append(" where 1=1 ");
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
		int count = ${pojo?lower_case}Dao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public ${pojo}Do getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(${pojo}Do.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<${pojo}Do> list = ${pojo?lower_case}Dao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
  
  private RowMapper<${pojo}Do> rowMapper = new RowMapper<${pojo}Do>() {
		@Override
		public ${pojo}Do mapRow(ResultSet rs, int rowNum) throws SQLException {
			${pojo}Do ${pojo?lower_case}=new ${pojo}Do();
${values}
			return ${pojo?lower_case};
		}
	};
	
	/* (non-Javadoc)
	 * @see ${spackage}.service.I${pojo}Service#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<${pojo}Do> getPage( String keyword,int status,int slock, long start, int limit) {
		
	   ${pageStart}
		StringBuilder sqlCount = new StringBuilder("select count(id) from ").append(${pojo}Do.getTable()).append(" where 1=1 ");
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
		
		int count = ${pojo?lower_case}Dao.queryForInt(sqlCount,params.toArray());
		List<${pojo}Do> list = null;
		if(count > 0){
			${pageend}
			list = ${pojo?lower_case}Dao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(${pojo}Do.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public ${pojo}Do getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(${pojo}Do.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<${pojo}Do> list = ${pojo?lower_case}Dao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(${pojo}Do ${pojo?lower_case}Do, ${pojo}Do v${pojo?lower_case}Do) {
		StringBuilder sql = new StringBuilder("update  ").append(${pojo}Do.getTable()).append(" set iorder=? where id =?");
		${pojo?lower_case}Dao.executeUpdate(sql, ${pojo?lower_case}Do.getIorder(),v${pojo?lower_case}Do.getId());
		${pojo?lower_case}Dao.executeUpdate(sql, v${pojo?lower_case}Do.getIorder(),${pojo?lower_case}Do.getId());
	}
	
}