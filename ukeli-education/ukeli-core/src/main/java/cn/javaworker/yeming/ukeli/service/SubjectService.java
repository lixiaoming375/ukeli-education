/**
* <p> * Title: 商城管理信息系统 *</p>
* <p> * Description: * </p>
* <p> * Copyright: Copyright (c) 2012-2017* </p>
* <p> * Company: 苏州明翔信息科技有限公司 * </p>
* @author 叶明（开发）
* @version 0.1
*/
package cn.javaworker.yeming.ukeli.service;


import java.util.Date;
import java.util.List;

import cn.javaworker.yeming.core.jdbc.service.BaseService;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.ukeli.pojo.SubjectDo;
import cn.javaworker.yeming.ukeli.vo.SubjectVo;

/**
* 创建日期：2017-09-04 11:03:02
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
public interface SubjectService extends BaseService<SubjectDo>{

	/**
	 * 获取题目分页
	 * @param kaywordMap  关键字
	 * @param status 状态
	 * @param slock 锁定标志
	 * @param start 开始数量
	 * @param limit 获取数量
	 * @return
	 */
	Page<SubjectDo> getPage(Date startTime, Date endTime, String name, int stype, int vtype,int status,int slock,long start, int limit);
	
	/**
	 * 排序使用
	 * @param id
	 * @param type
	 * @return
	 */
	SubjectDo getByOrderType(long iorder, int type);
	/**
	 * 处理排序
	 * @param subjectDo
	 * @param vsubjectDo
	 */
	void order(SubjectDo subjectDo,SubjectDo vsubjectDo);


	List<Long> getVtypes(Long id);



	Page<SubjectDo> getBindPage(String keyword, int status, int slock, long start, int limit, Long examId, Long cupId);


	Page<SubjectDo> getBindPageForKnowledge(String keyword, int vtype, int status, int slock, long start, int limit,
			Long knowledgeId);

	/**
	 * @param id
	 * @return
	 */
	List<SubjectVo> getByExamid(long examid);

	Page<SubjectDo> getSubList(int type, Long id, int status, int slock, long start, int limit);

	List<SubjectVo> getByCupid(long cupid);

	List<SubjectDo> getSubjectByKnowVtype(long id, int vtype);

	List<SubjectVo> getByKnowid(long knowledgeid, int vtype);
	
}