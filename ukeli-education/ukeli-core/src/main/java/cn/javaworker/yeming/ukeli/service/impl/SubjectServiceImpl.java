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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.javaworker.yeming.core.jdbc.service.impl.BaseServiceImpl;
import cn.javaworker.yeming.core.util.StrUtils;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.ukeli.ConstantUkeli;
import cn.javaworker.yeming.ukeli.dao.SubjectDao;
import cn.javaworker.yeming.ukeli.dao.SubjectOptionsDao;
import cn.javaworker.yeming.ukeli.pojo.SubjectDo;
import cn.javaworker.yeming.ukeli.pojo.SubjectOptionsDo;
import cn.javaworker.yeming.ukeli.service.SubjectService;
import cn.javaworker.yeming.ukeli.vo.SubjectVo;

/**
 * 创建日期：2017-09-04 11:03:02 开发者：叶明(MSN:guming123416@hotmail.com,QQ:47043760)
 * 修改者： 修改时间： 程序作用： 1、 2、 修改说明： 1、 2、 版本：@version 0.1
 * 
 * @author 叶明
 */
@Service
@Transactional
public class SubjectServiceImpl extends BaseServiceImpl implements SubjectService {
	private final static Logger LOGGER = LogManager.getLogger(SubjectServiceImpl.class);

	@Autowired
	private SubjectDao subjectDao;
	@Autowired
	private SubjectOptionsDao subjectOptionsDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.manager.service.BaseService#save(java.lang.Object,
	 * int)
	 */
	@Override
	@Transactional
	public long save(SubjectDo obj, int type) {
		String vtypes = obj.getEditer();
		obj.setEditer(null);
		Long id = subjectDao.save(obj, SubjectDo.getTable(), type);
		updateByItem("iorder", id, id);
		if (StrUtils.isNoBlank(vtypes)) {
			saveVtype(vtypes, id);
		}
		return id;
	}

	/**
	 * 保存权限信息
	 * 
	 * @param roleid
	 * @param id
	 */
	private void saveVtype(String vtypes, Serializable id) {
		if (StrUtils.isNoBlank(vtypes)) {
			String sql = "delete from t_subject_vtype where subjectid=?";
			subjectDao.executeUpdate(new StringBuilder(sql), id);
			sql = "insert into t_subject_vtype(subjectid,vtype) values(?,?)";
			String[] vtype = StringUtils.split(vtypes, ",");
			List<Object[]> list = new ArrayList<Object[]>(vtype.length);
			for (String string : vtype) {
				list.add(new Object[] { id, string });
			}
			subjectDao.updateBatch(sql, list);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	 */
	@Override
	@Transactional
	public boolean del(Serializable id) {
		int i = subjectDao.del(SubjectDo.getTable(), id);
		return i > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	 */
	@Override
	@Transactional
	public boolean update(SubjectDo obj, int type) {
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = subjectDao.update(SubjectDo.getTable(), map, obj.getId(), type);
		return i > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.core.jdbc.service.BaseService#update(java.util.Map,
	 * java.io.Serializable, int)
	 */
	@Override
	public int update(Map<String, Object> params, Serializable id, int type) {
		Object vtypes = params.get("vtypes");
		params.remove("vtypes");
		params.remove("vtype");
		int ids = subjectDao.update(SubjectDo.getTable(), params, id, type);
		if (null != vtypes) {
			saveVtype(vtypes.toString(), id);
		}
		return ids;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.core.jdbc.service.IBaseService#delByIds(java.lang.
	 * Object[])
	 */
	@Override
	@Transactional
	public void delByIds(Object[] ids) {
		if (null == ids) {
			return;
		}
		if (ids.length < 1) {
			return;
		}
		String val = StringUtils.join(ids, ",");
		StringBuilder sql = new StringBuilder("update ").append(SubjectDo.getTable())
				.append(" set slock=2 where id in(");
		if (val.length() > 0) {
			if (val.endsWith(",")) {
				sql.append(val.substring(0, val.length() - 1));
			} else {
				sql.append(val);
			}
		}
		sql.append(")");
		subjectDao.executeUpdate(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.
	 * lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName, Object itemValue, Serializable id) {
		subjectDao.updateByItems(SubjectDo.getTable(), itemName, itemValue, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	 */
	@Override
	@Transactional(readOnly = true)
	public SubjectDo get(Serializable id) {
		return subjectDao.get(id, SubjectDo.getTable(), rowMapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SubjectDo> getAll() {
		return subjectDao.getAll(SubjectDo.getTable(), rowMapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.shop.service.IAttributeService#getPage(java.lang.
	 * String,short,short,int, int)
	 */
	@Override
	public Page<SubjectDo> getSubList(int type, Long id, int status, int slock, long start, int limit) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = null;
		StringBuilder sqlCount = null;
		if (type == ConstantUkeli.SEARCHSUBJECTBYEXAM) {
			sql = new StringBuilder(
					"select s.*,es.serial as ext_serial ,es.score as ext_score from t_subject s,t_exam_subject es   where 1=1");
			sqlCount = new StringBuilder("select count(s.id)from t_subject s,t_exam_subject es where 1=1");
			if (id > 0) {
				sql.append(" and es.cupid=? ");
				sqlCount.append(" and es.cupid=? ");
				params.add(id);
			}
			sql.append(" and  s. id=es.subjectid order by es.serial asc;");
			sqlCount.append(" and  s. id=es.subjectid ;");
		} else if (type == ConstantUkeli.SEARCHSUBJECTBYCUP) {
			sql = new StringBuilder(
					"select s.*,cs.serial as ext_serial ,cs.score as ext_score from t_subject s,t_cup_subject cs   where 1=1");
			sqlCount = new StringBuilder("select count(s.id)from t_subject s,t_cup_subject cs where 1=1");
			if (id > 0) {
				sql.append(" and cs.cupid=? ");
				sqlCount.append(" and cs.cupid=? ");
				params.add(id);
			}
			sql.append(" and  s. id=cs.subjectid order by cs.serial asc;");
			sqlCount.append(" and  s. id=cs.subjectid ;");

		} else if (type == ConstantUkeli.SEARCHSUBJECTBYKNOW) {
			sql = new StringBuilder(
					"select s.*,ks.serial as ext_serial ,ks.score as ext_score  from t_subject s,t_knowledge_subject ks   where 1=1");
			sqlCount = new StringBuilder("select count(s.id)from t_subject s,t_knowledge_subject ks where 1=1");
			if (id > 0) {
				sql.append(" and ks.knowledgeid=? ");
				sqlCount.append(" and ks.knowledgeid=? ");
				params.add(id);
			}
			sql.append(" and  s. id=ks.subjectid order by ks.serial asc;");
			sqlCount.append(" and  s. id=ks.subjectid ;");
		}

		LOGGER.info("sql=" + sql);
		int count = subjectDao.queryForInt(sqlCount, params.toArray());
		List<SubjectDo> list = null;
		if (count > 0) {
			list = subjectDao.getList(sql, rowMapper2, params.toArray());
		}
		return Page.getPage(SubjectDo.class, start, limit, count, list);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SubjectDo> getSubjectByKnowVtype(long id, int vtype) {
		StringBuilder sql = null;
		StringBuilder sqlCount = null;
		List<Object> params = new ArrayList<Object>();
		sql = new StringBuilder(
				"select s.*,ks.serial as ext_serial  from t_subject s,t_knowledge_subject ks   where 1=1");
		sqlCount = new StringBuilder("select count(s.id)from t_subject s,t_knowledge_subject ks where 1=1");
		if (id > 0) {
			sql.append(" and ks.knowledgeid=? ");
			sqlCount.append(" and ks.knowledgeid=? ");
			params.add(id);
		}
		if (vtype > 0) {
			sql.append(" and  s.id  in (SELECT subjectid from t_subject_vtype where vtype=?)  ");
			sqlCount.append(" and  s.id  in (SELECT subjectid from t_subject_vtype where vtype=?) ");
			params.add(vtype);
		}
		sql.append(" and  s. id=ks.subjectid order by ks.serial asc;");
		sqlCount.append(" and  s. id=ks.subjectid ;");
		LOGGER.info("sql=" + sql);
		int count = subjectDao.queryForInt(sqlCount, params.toArray());
		List<SubjectDo> list = null;
		if (count > 0) {
			list = subjectDao.getList(sql, rowMapper3, params.toArray());
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean check(String val, int type, long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(SubjectDo.getTable())
				.append(" where 1=1 ");
		List<Object> params = new ArrayList<>();
		switch (type) {
		// case 1:sql.append(" and username=? ");params.add(val);break;
		// case 2:sql.append(" and realname=? ");params.add(val);break;
		}
		if (id > 0) {
			sql.append(" and id <> ?");
			params.add(id);
		}
		sql.append(" and slock < 2");
		int count = subjectDao.queryForInt(sql, params.toArray());
		return count > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.manager.service.AdminService#getRoleids(java.lang.
	 * Long)
	 */
	@Override
	public List<Long> getVtypes(Long id) {
		return subjectDao.getidList(new StringBuilder("SELECT vtype as id from t_subject_vtype  where subjectid=?"),
				longRowMapper, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public SubjectDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(SubjectDo.getTable()).append(" where ")
				.append(itemName).append("=? and slock <2 order by id desc");
		List<SubjectDo> list = subjectDao.getList(sql, rowMapper, itemValue);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	private RowMapper<SubjectDo> rowMapper = new RowMapper<SubjectDo>() {
		@Override
		public SubjectDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			SubjectDo subject = new SubjectDo();
			subject.setId(rs.getLong("id"));
			subject.setStype(rs.getInt("stype"));
			subject.setName(rs.getString("name"));
			subject.setContent(rs.getString("content"));
			subject.setAnswer(rs.getString("answer"));
			subject.setAnalysis(rs.getString("analysis"));
			subject.setImgurl(rs.getString("imgurl"));
			subject.setViewtimes(rs.getInt("viewtimes"));
			subject.setStudytimes(rs.getLong("studytimes"));
			subject.setDetail(rs.getString("detail"));
			subject.setVideopath(rs.getString("videopath"));
			subject.setIorder(rs.getLong("iorder"));
			subject.setStatus(rs.getShort("status"));
			subject.setEdittime(rs.getTimestamp("edittime"));
			subject.setSlock(rs.getShort("slock"));
			return subject;
		}
	};

	private RowMapper<SubjectDo> rowMapper2 = new RowMapper<SubjectDo>() {
		@Override
		public SubjectDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			SubjectDo subject = new SubjectDo();
			subject.setId(rs.getLong("id"));
			subject.setStype(rs.getInt("stype"));
			subject.setName(rs.getString("name"));
			subject.setContent(rs.getString("content"));
			subject.setAnswer(rs.getString("answer"));
			subject.setAnalysis(rs.getString("analysis"));
			subject.setImgurl(rs.getString("imgurl"));
			subject.setViewtimes(rs.getInt("viewtimes"));
			subject.setStudytimes(rs.getLong("studytimes"));
			subject.setDetail(rs.getString("detail"));
			subject.setVideopath(rs.getString("videopath"));
			subject.setIorder(rs.getLong("iorder"));
			subject.setExt_serial(rs.getInt("ext_serial"));
			subject.setExt_score(rs.getInt("ext_score"));
			return subject;
		}
	};
	private RowMapper<SubjectDo> rowMapper3 = new RowMapper<SubjectDo>() {
		@Override
		public SubjectDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			SubjectDo subject = new SubjectDo();
			subject.setId(rs.getLong("id"));
			subject.setStype(rs.getInt("stype"));
			subject.setName(rs.getString("name"));
			subject.setIorder(rs.getLong("iorder"));
			subject.setExt_serial(rs.getInt("ext_serial"));
			return subject;
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.ukeli.service.ISubjectService#getPage(java.lang.
	 * String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<SubjectDo> getPage(Date startTime, Date endTime, String name, int stype, int vtype, int status,
			int slock, long start, int limit) {

		StringBuilder sql = new StringBuilder("select * from ").append(SubjectDo.getTable()).append(" where 1=1 ");
		StringBuilder sqlCount = new StringBuilder("select count(id) from ").append(SubjectDo.getTable())
				.append("  where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (null != startTime) {
			sql.append(" and edittime >= ? ");
			sqlCount.append(" and edittime >= ?  ");
			params.add(startTime);
		}

		if (null != endTime) {
			sql.append(" and edittime <= ? ");
			sqlCount.append(" and edittimee <=  ?  ");
			params.add(endTime);
		}

		if (StrUtils.isNoBlank(name)) {
			sql.append(" and name like ? ");
			sqlCount.append(" and s.name like ? ");
			params.add("%" + name + "%");
		}

		if (stype > 0) {
			sql.append(" and stype = ? ");
			sqlCount.append(" and stype = ?  ");
			params.add(stype);
		}

		if (vtype > 0) {
			sql.append(" and  id  in (SELECT subjectid from t_subject_vtype where vtype=?) ");
			sqlCount.append(" and  id  in (SELECT subjectid from t_subject_vtype where vtype=?) ");
			params.add(vtype);
		}

		if (status > -1) {
			sql.append(" and status = ? ");
			sqlCount.append(" and status = ?  ");
			params.add(status);
		}
		if (slock > -1) {
			sql.append(" and slock =? ");
			sqlCount.append(" and slock=? ");
			params.add(slock);
		} else {
			sql.append(" and slock < 2 ");
			sqlCount.append(" and slock < 2 ");
		}

		int count = subjectDao.queryForInt(sqlCount, params.toArray());
		List<SubjectDo> list = null;
		if (count > 0) {
			sql.append(" order by iorder desc limit ?,?");
			params.add(start);
			params.add(limit);
			list = subjectDao.getList(sql, rowMapper, params.toArray());
		}
		return Page.getPage(SubjectDo.class, start, limit, count, list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.ukeli.service.ISubjectService#getPage(java.lang.
	 * String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<SubjectDo> getBindPage(String keyword, int status, int slock, long start, int limit, Long examId,
			Long cupId) {

		StringBuilder sql = new StringBuilder("select s.* from t_subject s , t_subject_vtype sv  ")
				.append(" where s.id=sv.subjectid ");
		StringBuilder sqlCount = new StringBuilder("select count(s.id) from t_subject s , t_subject_vtype sv ")
				.append(" where  s.id=sv.subjectid  ");
		List<Object> params = new ArrayList<Object>();

		if (StrUtils.isNoBlank(keyword)) {
			sql.append(" and (s.name like ? )");
			sqlCount.append(" and (s.name like ? ) ");
			params.add("%" + keyword + "%");
		}
		if (status > -1) {
			sql.append(" and s.status =? ");
			sqlCount.append(" and s.status=? ");
			params.add(status);
		}
		if (slock > -1) {
			sql.append(" and s.slock =? ");
			sqlCount.append(" and s.slock=? ");
			params.add(slock);
		} else {
			sql.append(" and s.slock < 2 ");
			sqlCount.append(" and s.slock < 2 ");
		}
		if (examId > 0) {
			sql.append(" and sv.vtype =11 and s.id not in (select subjectid from t_exam_subject where examid =?)");
			sqlCount.append(
					" and sv.vtype =11 and s.id not in (select subjectid from t_exam_subject where examid =?);");
			params.add(examId);
		} else if (cupId > 0) {
			sql.append(" and sv.vtype =10 and s.id not in (select subjectid from t_cup_subject where cupid =?)");
			sqlCount.append(" and sv.vtype =10 and s.id not in (select subjectid from t_cup_subject where cupid =? );");
			params.add(cupId);
		}

		int count = subjectDao.queryForInt(sqlCount, params.toArray());
		List<SubjectDo> list = null;
		if (count > 0) {
			sql.append(" order by s.iorder desc limit ?,?");
			params.add(start);
			params.add(limit);
			LOGGER.info("sql=" + sql.toString());
			list = subjectDao.getList(sql, rowMapper, params.toArray());
		}
		return Page.getPage(SubjectDo.class, start, limit, count, list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.javaworker.yeming.ukeli.service.ISubjectService#getPage(java.lang.
	 * String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<SubjectDo> getBindPageForKnowledge(String keyword, int vtype, int status, int slock, long start,
			int limit, Long knowledgeId) {

		StringBuilder sql = new StringBuilder("select s.* from t_subject s , t_subject_vtype sv   ")
				.append(" where s.id=sv.subjectid  ");
		List<Object> params = new ArrayList<Object>();

		if (StrUtils.isNoBlank(keyword)) {
			sql.append(" and (s.name like ? )");
			params.add("%" + keyword + "%");
		}
		if (status > -1) {
			sql.append(" and s.status =? ");
			params.add(status);
		}
		if (slock > -1) {
			sql.append(" and s.slock =? ");
			params.add(slock);
		} else {
			sql.append(" and s.slock < 2 ");
		}
		if (vtype == 0) {
			sql.append(
					" and sv.vtype in (7,8,9) and s.id not in (select subjectid from t_knowledge_subject where knowledgeid =?) group by s.id");
			params.add(knowledgeId);
		} else if (vtype == 7) {
			sql.append(
					" and sv.vtype =7 and s.id not in (select subjectid from t_knowledge_subject where knowledgeid =?) ");
			params.add(knowledgeId);
		} else if (vtype == 8) {
			sql.append(
					" and sv.vtype =8 and s.id not in (select subjectid from t_knowledge_subject where knowledgeid =?) ");
			params.add(knowledgeId);
		} else if (vtype == 9) {
			sql.append(
					" and sv.vtype =9 and s.id not in (select subjectid from t_knowledge_subject where knowledgeid =?) ");
			params.add(knowledgeId);
		}

		List<SubjectDo> list = null;
		sql.append(" order by s.iorder desc limit ?,?");
		params.add(start);
		params.add(limit);
		LOGGER.info("sql=" + sql.toString());
		list = subjectDao.getList(sql, rowMapper, params.toArray());
		return Page.getPage(SubjectDo.class, start, limit, list.size(), list);
	}

	@Override
	@Transactional(readOnly = true)
	public SubjectDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(SubjectDo.getTable()).append(" where 1=1 ");
		if (type == 1) {
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		} else {
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<SubjectDo> list = subjectDao.getList(sql, rowMapper, iorder);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(SubjectDo subjectDo, SubjectDo vsubjectDo) {
		StringBuilder sql = new StringBuilder("update  ").append(SubjectDo.getTable())
				.append(" set iorder=? where id =?");
		subjectDao.executeUpdate(sql, subjectDo.getIorder(), vsubjectDo.getId());
		subjectDao.executeUpdate(sql, vsubjectDo.getIorder(), subjectDo.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.javaworker.yeming.ukeli.service.SubjectService#getByExamid(long)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional(readOnly = true)
	public List<SubjectVo> getByExamid(long examid) {
		List<SubjectVo> list = new ArrayList<>();
		Map<Long, SubjectVo> map = new HashMap<>();
		StringBuilder sql = new StringBuilder(
				"SELECT t.* from t_subject t left join t_exam_subject t1 on t.id = t1.subjectid where t1.examid =? and t.status =1 order by t1.iorder asc");
		List<SubjectDo> sublist = subjectDao.getList(sql, rowMapper, examid);
		if (sublist.size() < 1) {
			return list;
		}
		List<Object> ids = new ArrayList<>();
		for (SubjectDo subjectDo : sublist) {
			SubjectVo vo = new SubjectVo();
			vo.setSubjectDo(subjectDo);
			map.put(subjectDo.getId(), vo);
			ids.add(subjectDo.getId());
			list.add(vo);
		}
		System.out.println("sublist=" + sublist);
		sql = new StringBuilder("select * from ").append(SubjectOptionsDo.getTable())
				.append(" where  subjectid  in (:subjectid )");
		List<SubjectOptionsDo> suboptList = subjectOptionsDao.getListByIds(sql,
				new BeanPropertyRowMapper(SubjectOptionsDo.class), "subjectid", ids);
		for (SubjectOptionsDo subjectOptionsDo : suboptList) {
			SubjectVo vo = map.get(subjectOptionsDo.getSubjectid());
			if (null != vo) {
				vo.getList().add(subjectOptionsDo);
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.javaworker.yeming.ukeli.service.SubjectService#getByExamid(long)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional(readOnly = true)
	public List<SubjectVo> getByCupid(long cupid) {
		List<SubjectVo> list = new ArrayList<>();
		Map<Long, SubjectVo> map = new HashMap<>();
		StringBuilder sql = new StringBuilder(
				"SELECT t.* from t_subject t left join t_cup_subject t1 on t.id = t1.subjectid where t1.cupid =? and t.status =1 order by t1.iorder asc");
		List<SubjectDo> sublist = subjectDao.getList(sql, rowMapper, cupid);
		if (sublist.size() < 1) {
			return list;
		}
		List<Object> ids = new ArrayList<>();
		for (SubjectDo subjectDo : sublist) {
			SubjectVo vo = new SubjectVo();
			vo.setSubjectDo(subjectDo);
			map.put(subjectDo.getId(), vo);
			ids.add(subjectDo.getId());
			list.add(vo);
		}
		System.out.println("sublist=" + sublist);
		sql = new StringBuilder("select * from ").append(SubjectOptionsDo.getTable())
				.append(" where  subjectid  in (:subjectid )");
		List<SubjectOptionsDo> suboptList = subjectOptionsDao.getListByIds(sql,
				new BeanPropertyRowMapper(SubjectOptionsDo.class), "subjectid", ids);
		for (SubjectOptionsDo subjectOptionsDo : suboptList) {
			SubjectVo vo = map.get(subjectOptionsDo.getSubjectid());
			if (null != vo) {
				vo.getList().add(subjectOptionsDo);
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.javaworker.yeming.ukeli.service.SubjectService#getByExamid(long)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional(readOnly = true)
	public List<SubjectVo> getByKnowid(long knowledgeid, int vtype) {
		List<Object> params = new ArrayList<Object>();
		List<SubjectVo> list = new ArrayList<>();
		Map<Long, SubjectVo> map = new HashMap<>();
		StringBuilder sql = new StringBuilder(
				"SELECT t.* from t_subject t left join t_knowledge_subject t1 on t.id = t1.subjectid where t1.knowledgeid =? and t.status =1 ");
		sql.append(" and  t.id  in (SELECT subjectid from t_subject_vtype where vtype=?)  order by t1.iorder asc");
		params.add(knowledgeid);
		params.add(vtype);
		List<SubjectDo> sublist = subjectDao.getList(sql, rowMapper, params.toArray());
		if (sublist.size() < 1) {
			return list;
		}
		List<Object> ids = new ArrayList<>();
		for (SubjectDo subjectDo : sublist) {
			SubjectVo vo = new SubjectVo();
			vo.setSubjectDo(subjectDo);
			map.put(subjectDo.getId(), vo);
			ids.add(subjectDo.getId());
			list.add(vo);
		}
		System.out.println("sublist=" + sublist);
		sql = new StringBuilder("select * from ").append(SubjectOptionsDo.getTable())
				.append(" where  subjectid  in (:subjectid )");
		List<SubjectOptionsDo> suboptList = subjectOptionsDao.getListByIds(sql,
				new BeanPropertyRowMapper(SubjectOptionsDo.class), "subjectid", ids);
		for (SubjectOptionsDo subjectOptionsDo : suboptList) {
			SubjectVo vo = map.get(subjectOptionsDo.getSubjectid());
			if (null != vo) {
				vo.getList().add(subjectOptionsDo);
			}
		}
		return list;
	}

}