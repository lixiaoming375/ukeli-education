/**
* <p> * Title: 商城管理信息系统 *</p>
* <p> * Description: * </p>
* <p> * Copyright: Copyright (c) 2012-2017* </p>
* <p> * Company: 苏州明翔信息科技有限公司 * </p>
* @author 叶明（开发）
* @version 0.1
*/
package cn.javaworker.yeming.ukeli.service;


import java.util.Map;

import cn.javaworker.yeming.core.jdbc.service.BaseService;
import cn.javaworker.yeming.ukeli.pojo.OrderDo;
import cn.javaworker.yeming.pojo.Page;

/**
* 创建日期：2017-09-12 14:28:24
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
public interface OrderService extends BaseService<OrderDo>{

	/**
	 * 获取订单产品分页
	 * @param kaywordMap  关键字
	 * @param status 状态
	 * @param slock 锁定标志
	 * @param start 开始数量
	 * @param limit 获取数量
	 * @return
	 */
	Page<OrderDo> getPage(Map<String, Object> kaywordMap,int status,int slock,long start, int limit);
	
	
	/**
	 * 排序使用
	 * @param id
	 * @param type
	 * @return
	 */
	OrderDo getByOrderType(long iorder, int type);
	/**
	 * 处理排序
	 * @param orderDo
	 * @param vorderDo
	 */
	void order(OrderDo orderDo,OrderDo vorderDo);
}