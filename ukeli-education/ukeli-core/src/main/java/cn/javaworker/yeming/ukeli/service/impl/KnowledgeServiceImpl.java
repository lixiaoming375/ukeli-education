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

import cn.javaworker.yeming.core.util.StrUtils;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.ukeli.dao.KnowledgeDao;
import cn.javaworker.yeming.ukeli.pojo.KnowledgeDo;
import cn.javaworker.yeming.ukeli.service.KnowledgeService;

@Service
@Transactional
public class KnowledgeServiceImpl implements KnowledgeService{

	@Autowired
	private KnowledgeDao knowledgeDao;
	
	/* (non-Javadoc)
	* @see cn.javaworker.yeming.shop.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	public long save(KnowledgeDo obj,int type){
		long id=  knowledgeDao.save(obj,KnowledgeDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		knowledgeDao.updateByItems(KnowledgeDo.getTable(), itemName, itemValue, id);
	}


	@Override
	public boolean check(String val,int type,long id) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("select count(id) from ").append(KnowledgeDo.getTable()).append(" where 1=1 ");
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
		int count = knowledgeDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}


	/* (non-Javadoc)
	* @see cn.javaworker.yeming.shop.IBaseService#del(java.io.Serializable)
	*/
	@Override
	public boolean del(Serializable id){
		int i = knowledgeDao.del(KnowledgeDo.getTable(),id);
		return i>0 ? true : false;
	}


	@Override
	public void delByIds(Object[] ids) {
		// TODO Auto-generated method stub
		if(null == ids) {
			return;
		}
		if(ids.length  <1) {
			return;
		}
		String val = StringUtils.join(ids,",");
		StringBuilder sql = new StringBuilder("update ").append(KnowledgeDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		knowledgeDao.executeUpdate(sql);
	}


	/* (non-Javadoc)
	* @see cn.javaworker.yeming.shop.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public KnowledgeDo get(Serializable id){
		return knowledgeDao.get(id,KnowledgeDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.shop.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<KnowledgeDo> getAll(){
		return knowledgeDao.getAll(KnowledgeDo.getTable(),rowMapper);
	}

	@Override
	public KnowledgeDo getByItem(String itemName, Object itemValue) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("select * from ").append(KnowledgeDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<KnowledgeDo> list = knowledgeDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}


	/* (non-Javadoc)
	* @see cn.javaworker.yeming.shop.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	public boolean update(KnowledgeDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = knowledgeDao.update(KnowledgeDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}

	@Override
	public int update(Map<String, Object> params, Serializable id, int type) {
		// TODO Auto-generated method stub
		return knowledgeDao.update(KnowledgeDo.getTable(), params, id, type);
	}


	@Override
	public Page<KnowledgeDo> getPage(Map<String, Object> kaywordMap, int status, int slock, long start, int limit) {
		// TODO Auto-generated method stub
		 StringBuilder sql = new StringBuilder("select * from ").append(KnowledgeDo.getTable()).append(" where 1=1 ");
			StringBuilder sqlCount = new StringBuilder("select count(id) from ").append(KnowledgeDo.getTable()).append(" where 1=1 ");
			List<Object> params = new ArrayList<Object>();

			if(kaywordMap!=null) {
				if(null!=kaywordMap.get("nodename")&&StringUtils.isNotBlank(kaywordMap.get("nodename").toString())){
					sql.append(" and (nodename like ? )");
					sqlCount.append(" and (nodename like ? ) ");
					params.add("%"+kaywordMap.get("nodename").toString()+"%");
				}
				if(Long.valueOf(String.valueOf(kaywordMap.get("typeid")))>0){
					sql.append(" and (typeid = ? )");
					sqlCount.append(" and (typeid = ? ) ");
					params.add(kaywordMap.get("typeid"));
				}
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
			
			int count = knowledgeDao.queryForInt(sqlCount,params.toArray());
			List<KnowledgeDo> list = null;
			if(count > 0){
				sql.append(" order by id desc limit ?,?");params.add(start);params.add(limit);
				list = knowledgeDao.getList(sql,rowMapper,params.toArray());
			}
			return Page.getPage(KnowledgeDo.class, start, limit, count, list);
	}


	@Override
	@Transactional(readOnly=true)
	public KnowledgeDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(KnowledgeDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<KnowledgeDo> list = knowledgeDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(KnowledgeDo brandDo, KnowledgeDo vbrandDo) {
		StringBuilder sql = new StringBuilder("update  ").append(KnowledgeDo.getTable()).append(" set iorder=? where id =?");
		knowledgeDao.executeUpdate(sql, brandDo.getIorder(),vbrandDo.getId());
		knowledgeDao.executeUpdate(sql, vbrandDo.getIorder(),brandDo.getId());
	}

	
	
	
	 private RowMapper<KnowledgeDo> rowMapper = new RowMapper<KnowledgeDo>() {
			@Override
			public KnowledgeDo mapRow(ResultSet rs, int rowNum) throws SQLException {
				KnowledgeDo knowledge=new KnowledgeDo();
				knowledge.setId(rs.getLong("id"));
				knowledge.setGradeid(rs.getLong("gradeid"));
				knowledge.setNodename(rs.getString("nodename"));
				knowledge.setTypeid(rs.getLong("typeid"));
				knowledge.setRemark(rs.getString("remark"));
				knowledge.setVideodetail(rs.getString("videodetail"));
				knowledge.setVideopath(rs.getString("videopath"));
				knowledge.setTotalexam(rs.getInt("totalexam"));
				knowledge.setIorder(rs.getLong("iorder"));
				knowledge.setStatus(rs.getShort("status"));
				knowledge.setAdderid(rs.getLong("adderid"));
				knowledge.setAdder(rs.getString("adder"));
				knowledge.setEditer(rs.getString("editer"));
				knowledge.setEditerid(rs.getLong("editerid"));
				knowledge.setRemark1(rs.getString("remark1"));
				knowledge.setRemark2(rs.getString("remark2"));
				knowledge.setAddtime(rs.getTimestamp("addtime"));
				knowledge.setEdittime(rs.getTimestamp("edittime"));
				knowledge.setSlock(rs.getShort("slock"));

				return knowledge;
			}
		};
}
