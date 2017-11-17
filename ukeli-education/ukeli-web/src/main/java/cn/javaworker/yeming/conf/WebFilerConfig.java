/**
* <p> * Title: 优课力信息管理系统*</p>
* <p> * Description: 优课力信息管理系统 * </p>
* <p> * Copyright: Copyright (c) 2012-2018* </p>
* <p> * Company:苏州明翔信息科技有限公司 * </p>
* @author 叶明（开发）
* @version 0.1
*/
package cn.javaworker.yeming.conf;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.javaworker.yeming.ukeli.filter.UserFilter;

/**
 * 创建日期：2017年10月16日 下午5:22:45
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
@Configuration
public class WebFilerConfig {
	private final static Logger LOGGER = LogManager.getLogger(WebFilerConfig.class);
	

	@Value("${web.filter.ignore}")
	private String ignore;
	
	@Bean
    public FilterRegistrationBean indexFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new UserFilter());
        registration.addUrlPatterns("*.html","*.json");
        Map<String, String> params = new HashMap<>(5);
        params.put("ignore", ignore);
        registration.setInitParameters(params);
        LOGGER.info("初始化filter参数:" + params);
        return registration;
    }

}
