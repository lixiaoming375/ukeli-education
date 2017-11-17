/**
* <p> * Title: 叶明开发的代码系统*</p>
* <p> * Description: 复旦大学类脑智能科学与技术研究院* </p>
* <p> * Copyright: Copyright (c) 2012-2018* </p>
* <p> * Company: 苏州明翔信息科技 * </p>
* @author 叶明（开发）
* @version 1.0.1
*/
package cn.javaworker.yeming.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.javaworker.yeming.core.jdbc.dialect.Dialect;
import cn.javaworker.yeming.core.jdbc.dialect.impl.MysqlDialectImpl;

/**
 * 创建日期：2017年7月18日 下午4:45:47
 * 开发者：叶明(MSN:guming123416@hotmail.com,QQ:47043760)
 * 修改者：
 * 修改时间：
 * 程序作用：
 * 1、
 * 2、
 * 修改说明：
 * 1、
 * 2、
 * 版本：@version 1.0.1
 * @author 叶明
 */
@Configuration
public class WebConfig {
	
	@Bean
	public Dialect getDialect(){
		return new MysqlDialectImpl();
	}
}
