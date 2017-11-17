/**
* <p> * Title: </p>
* <p> * Description: 素问轩* </p>
* <p> * Copyright: Copyright (c) 2012-2018* </p>
* <p> * Company: 苏州明翔信息科技有限公司 * </p>
* @author 叶明（开发）
* @version 0.0.1
*/
package cn.javaworker.yeming;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 创建日期：2017年7月31日 上午8:33:17 
 * 开发者：叶明(MSN:guming123416@hotmail.com,QQ:47043760)
 * 修改者： 
 * 修改时间： 
 * 程序作用： 1、 2、 
 * 修改说明： 1、 2、 
 * 版本：@version 0.0.1
 * 
 * @author 叶明
 */
public class ServletInitializer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
}
