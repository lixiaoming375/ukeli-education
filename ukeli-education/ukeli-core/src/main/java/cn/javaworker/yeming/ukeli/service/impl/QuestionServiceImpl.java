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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.javaworker.yeming.core.jdbc.service.impl.BaseServiceImpl;
import cn.javaworker.yeming.core.util.StrUtils;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.ukeli.dao.QuestionDao;
import cn.javaworker.yeming.ukeli.pojo.QuestionDo;
import cn.javaworker.yeming.ukeli.pojo.UserDo;
import cn.javaworker.yeming.ukeli.service.QuestionService;

/**
* 创建日期：2017-10-18 14:44:07
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
public class QuestionServiceImpl extends BaseServiceImpl  implements QuestionService {

	@Autowired
	private QuestionDao questionDao;

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(QuestionDo obj,int type){
		long id=  questionDao.save(obj,QuestionDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = questionDao.del(QuestionDo.getTable(),id);
		return i>0 ? true : false;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(QuestionDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = questionDao.update(QuestionDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return questionDao.update(QuestionDo.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(QuestionDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		questionDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		questionDao.updateByItems(QuestionDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public QuestionDo get(Serializable id){
		return questionDao.get(id,QuestionDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<QuestionDo> getAll(){
		return questionDao.getAll(QuestionDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(QuestionDo.getTable()).append(" where 1=1 ");
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
		int count = questionDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public QuestionDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(QuestionDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<QuestionDo> list = questionDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
  
  private RowMapper<QuestionDo> rowMapper = new BeanPropertyRowMapper<>(QuestionDo.class);
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.IQuestionService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<QuestionDo> getPage(String keyword,short vtype,long relationid,long userid,int isanswer,int status,int slock, long start, int limit) {
		
	   StringBuilder sql = new StringBuilder("SELECT t.*,t1.realname as ext_realname from ").append(QuestionDo.getTable()).append(" t left join ").append(UserDo.getTable()).append(" t1 on t.userid =t1.id  where 1=1 ");
		StringBuilder sqlCount = new StringBuilder("select count(t.id) from ").append(QuestionDo.getTable()).append(" t left join ").append(UserDo.getTable()).append(" t1 on t.userid =t1.id   where 1=1 ");
		List<Object> params = new ArrayList<Object>();

		if(StrUtils.isNoBlank(keyword)) {
			sql.append(" and (t.question like ? )");
			sqlCount.append(" and (t.question like ? ) ");
			params.add("%"+keyword+"%");
		}
		if(vtype > 0) {
			sql.append(" and t.vtype =? ");
			sqlCount.append(" and t.vtype=? ");
			params.add(vtype);
		}
		if (relationid > 0) {
			sql.append(" and t.relationid =? ");
			sqlCount.append(" and t.relationid=? ");
			params.add(relationid);
		}
		if (isanswer >0) {
			sql.append(" and t.isanswer =? ");
			sqlCount.append(" and t.isanswer=? ");
			params.add(relationid);
		}
		if (userid > 0 ) {
			sql.append(" and t.userid =? ");
			sqlCount.append(" and t.userid=? ");
			params.add(userid);
		}
		if (status > -1) {
			sql.append(" and status =? ");
			sqlCount.append(" and status=? ");
			params.add(status);
		}
		if (slock > -1) {
			sql.append(" and t.slock =? ");
			sqlCount.append(" and t.slock=? ");
			params.add(slock);
		}else{
			sql.append(" and t.slock < 2 ");
			sqlCount.append(" and t.slock < 2 ");
		}
		int count = questionDao.queryForInt(sqlCount,params.toArray());
		List<QuestionDo> list = null;
		if(count > 0){
			sql.append(" order by t.id desc limit ?,?");params.add(start);params.add(limit);
			list = questionDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(QuestionDo.class, start, limit, count, list);
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.IQuestionService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<QuestionDo> getPage(String question,String vtype,String isanswer,long relationid, String nickname,Date startTime,Date endTime,int status,int slock, long start, int limit) {
		
		StringBuilder sql = new StringBuilder("SELECT t.*,t1.realname as ext_realname from ").append(QuestionDo.getTable()).append(" t left join ").append(UserDo.getTable()).append(" t1 on t.userid =t1.id  where 1=1 ");
		StringBuilder sqlCount = new StringBuilder("select count(t.id) from ").append(QuestionDo.getTable()).append(" t left join ").append(UserDo.getTable()).append(" t1 on t.userid =t1.id   where 1=1 ");
		List<Object> params = new ArrayList<Object>();

		if(StrUtils.isNoBlank(question)) {
			sql.append(" and (t.question like ? )");
			sqlCount.append(" and (t.question like ? ) ");
			params.add("%"+question+"%");
		}
		
		if(StrUtils.isNoBlank(vtype)) {
			sql.append(" and (t.vtype = ? )");
			sqlCount.append(" and (t.vtype = ? ) ");
			params.add(vtype);
		}
		if (status > -1) {
			sql.append(" and t.status =? ");
			sqlCount.append(" and t.status=? ");
			params.add(status);
		}
		if (StrUtils.isNoBlank(isanswer)) {
			sql.append(" and t.isanswer =? ");
			sqlCount.append(" and t.isanswer=? ");
			params.add(isanswer);
		}
		
		if(StrUtils.isNoBlank(nickname)) {
			sql.append(" and (t1.nickname like ? )");
			sqlCount.append(" and (t1.nickname like ? ) ");
			params.add("%"+nickname.trim()+"%");
		}
		if (null!=startTime) {
			sql.append(" and t.addtime >? ");
			sqlCount.append(" and t.addtime>? ");
			params.add(startTime);
		}
		if (null!=endTime) {
			sql.append(" and t.addtime <? ");
			sqlCount.append(" and t.addtime<? ");
			params.add(endTime);
		}
		
		
		if (slock > -1) {
			sql.append(" and t.slock =? ");
			sqlCount.append(" and t.slock=? ");
			params.add(slock);
		}else{
			sql.append(" and t.slock < 2 ");
			sqlCount.append(" and t.slock < 2 ");
		}
		
		int count = questionDao.queryForInt(sqlCount,params.toArray());
		List<QuestionDo> list = null;
		if(count > 0){
			sql.append(" order by t.iorder desc limit ?,?");params.add(start);params.add(limit);
			list = questionDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(QuestionDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public QuestionDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(QuestionDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<QuestionDo> list = questionDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(QuestionDo questionDo, QuestionDo vquestionDo) {
		StringBuilder sql = new StringBuilder("update  ").append(QuestionDo.getTable()).append(" set iorder=? where id =?");
		questionDao.executeUpdate(sql, questionDo.getIorder(),vquestionDo.getId());
		questionDao.executeUpdate(sql, vquestionDo.getIorder(),questionDo.getId());
	}
	
}