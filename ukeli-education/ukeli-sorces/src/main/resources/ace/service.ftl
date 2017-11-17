<#include "copyright.ftl"/>

package ${spackage}.service;


import cn.javaworker.yeming.core.jdbc.service.BaseService;
import ${spackage}.pojo.${pojo}Do;
import cn.javaworker.yeming.pojo.Page;

<#include "version.ftl"/>

public interface ${pojo}Service extends BaseService<${pojo}Do>{

	/**
	 * 获取<#if tabdesp??>${tabdesp}</#if>分页
	 * @param keyword  关键字
	 * @param status 状态
	 * @param slock 锁定标志
	 * @param start 开始数量
	 * @param limit 获取数量
	 * @return
	 */
	Page<${pojo}Do> getPage(String keyword,int status,int slock,long start, int limit);
	
	
	/**
	 * 排序使用
	 * @param id
	 * @param type
	 * @return
	 */
	${pojo}Do getByOrderType(long iorder, int type);
	/**
	 * 处理排序
	 * @param ${pojo?lower_case}Do
	 * @param v${pojo?lower_case}Do
	 */
	void order(${pojo}Do ${pojo?lower_case}Do,${pojo}Do v${pojo?lower_case}Do);
}