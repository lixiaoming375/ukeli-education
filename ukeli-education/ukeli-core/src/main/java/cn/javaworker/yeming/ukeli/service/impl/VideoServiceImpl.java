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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.javaworker.yeming.core.jdbc.service.impl.BaseServiceImpl;
import cn.javaworker.yeming.ukeli.pojo.VideoDo;
import cn.javaworker.yeming.ukeli.dao.VideoDao;
import cn.javaworker.yeming.ukeli.service.VideoService;
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
public class VideoServiceImpl extends BaseServiceImpl  implements VideoService {
	
	
	private final static Logger LOGGER =LogManager.getLogger(VideoServiceImpl.class);

	@Autowired
	private VideoDao videoDao;

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#save(java.lang.Object,int)
	*/
	@Override
	@Transactional
	public long save(VideoDo obj,int type){
		long id=  videoDao.save(obj,VideoDo.getTable(), type);
		updateByItem("iorder", id, id);
		return id;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.IBaseService#del(java.io.Serializable)
	*/
	@Override
	@Transactional
	public boolean del(Serializable id){
		int i = videoDao.del(VideoDo.getTable(),id);
		return i>0 ? true : false;
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#update(java.lang.Object)
	*/
	@Override
	@Transactional
	public boolean update(VideoDo obj,int type){
		Map<String, Object> map = StrUtils.ListInsertableFields(obj);
		int i = videoDao.update(VideoDo.getTable(), map, obj.getId(), type);
		return i>0 ? true : false;
	}
	
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.IBaseService#update(java.util.Map, java.io.Serializable, int)
	 */
	@Override
	@Transactional
	public int update(Map<String, Object> params, Serializable id, int type) {
		return videoDao.update(VideoDo.getTable(), params, id, type);
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
		StringBuilder sql = new StringBuilder("update ").append(VideoDo.getTable()).append(" set slock=2 where id in(" );
		if(val.length() > 0) {
			if(val.endsWith(",")) {
				sql.append(val.substring(0,val.length()-1));
			}else {
				sql.append(val);
			}
		}
		sql.append(")");
		videoDao.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.core.jdbc.service.BaseService#updateByItems(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public void updateByItem(String itemName,Object itemValue,Serializable id) {
		videoDao.updateByItems(VideoDo.getTable(), itemName, itemValue, id);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#get(java.io.Serializable)
	*/
	@Override
	@Transactional(readOnly=true)
	public VideoDo get(Serializable id){
		return videoDao.get(id,VideoDo.getTable(),rowMapper);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.ukeli.service.IBaseService#getAll()
	*/
	@Override
	@Transactional(readOnly=true)
	public List<VideoDo> getAll(){
		return videoDao.getAll(VideoDo.getTable(),rowMapper);
	}
  
  	@Override
	@Transactional(readOnly=true)
	public boolean check(String val,int type,long id) {
		StringBuilder sql = new StringBuilder("select count(id) from ").append(VideoDo.getTable()).append(" where 1=1 ");
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
		int count = videoDao.queryForInt(sql, params.toArray());
		return count > 0 ?true :false;
	}
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.manager.service.AdminService#getByItem(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public VideoDo getByItem(String itemName, Object itemValue) {
		StringBuilder sql = new StringBuilder("select * from ").append(VideoDo.getTable()).append(" where ").append(itemName).append("=? and slock <2 order by id desc");
		List<VideoDo> list = videoDao.getList(sql, rowMapper,itemValue);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
  
  private RowMapper<VideoDo> rowMapper = new RowMapper<VideoDo>() {
		@Override
		public VideoDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			VideoDo video=new VideoDo();
			video.setId(rs.getLong("id"));
			video.setVideoname(rs.getString("videoname"));
			video.setVideourl(rs.getString("videourl"));
			video.setVideotype(rs.getString("videotype"));
			video.setDetail(rs.getString("detail"));
			video.setVideotime(rs.getInt("videotime"));
			video.setIorder(rs.getLong("iorder"));
			video.setStatus(rs.getShort("status"));
			video.setEdittime(rs.getTimestamp("edittime"));
			video.setSlock(rs.getShort("slock"));

			return video;
		}
	};
	
	/* (non-Javadoc)
	 * @see cn.javaworker.yeming.ukeli.service.IVideoService#getPage(java.lang.String,short,short,int, int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Page<VideoDo> getPage( String keyword,int status,int slock, long start, int limit) {
		
	   StringBuilder sql = new StringBuilder("select * from ").append(VideoDo.getTable()).append(" where 1=1 ");
		StringBuilder sqlCount = new StringBuilder("select count(id) from ").append(VideoDo.getTable()).append(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();

		if(StrUtils.isNoBlank(keyword)) {
			sql.append(" and (videoname like ? )");
			sqlCount.append(" and (videoname like ? ) ");
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
		
		int count = videoDao.queryForInt(sqlCount,params.toArray());
		List<VideoDo> list = null;
		if(count > 0){
			sql.append(" order by id desc limit ?,?");params.add(start);params.add(limit);
			list = videoDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(VideoDo.class, start, limit, count, list);
	}
	
	
	@Override
	public Page<VideoDo> getVideoListByKnowId(List<Long> videoList,int status,int slock, long start, int limit) {
		
	   StringBuilder sql = new StringBuilder("select * from ").append(VideoDo.getTable()).append(" where slock=0 and id in(");
		StringBuilder sqlCount = new StringBuilder("select count(id) from ").append(VideoDo.getTable()).append(" where slock=0 and id in(");
		List<Object> params = new ArrayList<Object>();
	      for(int i=0;i<videoList.size();i++){
	    	  if(i==videoList.size()-1){
	    		  sql.append( videoList.get(i) );
	    		  sqlCount.append( videoList.get(i) );
	    	  }else{
	    		  sql.append( videoList.get(i)+", ");
	    		  sqlCount.append( videoList.get(i)+" , ");
	    	  }
	      }
		
		sql.append(" )");
		sqlCount.append(" )");
		LOGGER.info("sql="+sql);
		
		int count = 0;
		if(videoList.size()>0){
			count = videoDao.queryForInt(sqlCount,params.toArray());
		}
		List<VideoDo> list = null;
		if(count > 0){
			sql.append(" order by id desc ");
			list = videoDao.getList(sql,rowMapper,params.toArray());
		}
		return Page.getPage(VideoDo.class, start, limit, count, list);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public VideoDo getByOrderType(long iorder, int type) {
		StringBuilder sql = new StringBuilder("select * from ").append(VideoDo.getTable()).append(" where 1=1 ");
		if(type ==1){
			sql.append(" and iorder > ? order by iorder asc limit 0,1");
		}else{
			sql.append(" and iorder <  ? order by iorder desc limit 0,1");
		}
		List<VideoDo> list = videoDao.getList(sql, rowMapper, iorder);
		if(null !=list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void order(VideoDo videoDo, VideoDo vvideoDo) {
		StringBuilder sql = new StringBuilder("update  ").append(VideoDo.getTable()).append(" set iorder=? where id =?");
		videoDao.executeUpdate(sql, videoDo.getIorder(),vvideoDo.getId());
		videoDao.executeUpdate(sql, vvideoDo.getIorder(),videoDo.getId());
	}
	
}