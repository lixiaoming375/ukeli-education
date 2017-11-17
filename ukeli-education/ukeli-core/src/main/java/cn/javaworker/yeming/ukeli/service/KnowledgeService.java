package cn.javaworker.yeming.ukeli.service;

import java.util.Map;

import cn.javaworker.yeming.core.jdbc.service.BaseService;
import cn.javaworker.yeming.pojo.Page;
import cn.javaworker.yeming.ukeli.pojo.KnowledgeDo;

public interface KnowledgeService extends BaseService<KnowledgeDo>{
	/**
	 * 获取知识点分页
	 * @param keyword  关键字
	 * @param status 状态
	 * @param slock 锁定标志
	 * @param start 开始数量
	 * @param limit 获取数量
	 * @return
	 */
	Page<KnowledgeDo> getPage(Map<String, Object> kaywordMap,int status,int slock,long start, int limit);
	
	/**
	 * 排序使用
	 * @param id
	 * @param type
	 * @return
	 */
	KnowledgeDo getByOrderType(long iorder, int type);
	/**
	 * 处理排序
	 * @param knowledgeDo
	 * @param vknowledgeDo
	 */
	void order(KnowledgeDo knowledgeDo,KnowledgeDo vknowledgeDo);
}
