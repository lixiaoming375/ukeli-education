/**
 * 
 */
package cn.javaworker.yeming.ukeli.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.javaworker.yeming.core.web.CookiesUtil;
import cn.javaworker.yeming.core.web.RequestUtil;
import cn.javaworker.yeming.ukeli.UserUtil;

/**
 * @author ye
 *
 */
@SuppressWarnings("serial")
public class UserFilter extends HttpServlet implements Filter {

	
	private final static Logger LOGGER = LogManager.getLogger(UserFilter.class);
	
	private ApplicationContext ac;
	private StringRedisTemplate stringRedisTemplate = null;
	private List<String> ignoreURIs = new ArrayList<String>();
	
	private final static String FORBIDDEN_URL = "/forbidden.html";
	private final static String LOGIN_URL = "/index.html";
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {

		String ignores = config.getInitParameter("ignore");
		if (ignores != null) {
			String[] str = StringUtils.split(ignores, ',');
			for (String ig : str) {
				ignoreURIs.add(ig.trim());
			}
		}
		ServletContext context = config.getServletContext();
		ac = WebApplicationContextUtils.getWebApplicationContext(context);
		if(null != ac){
			stringRedisTemplate =ac.getBean(StringRedisTemplate.class);
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;

		String reqUri = request.getRequestURI();
		for (String ignoreURI : ignoreURIs) {
			if (reqUri.equals(ignoreURI)) {
				chain.doFilter(request, response);
				return;
			}
		}
		String path = RequestUtil.getWebRealPath(request);
		boolean check =false;
		long userid =0;
		try {
			userid = CookiesUtil.getInt3Des(UserUtil.USER_COOKIESID,request);
		}catch (Exception ex){
			LOGGER.error("从cookies中获取用户信息发生错误:"+ex.getMessage());
		}
		LOGGER.info("cookies中的用户id:"+userid);
		if (userid < 1) {
			LOGGER.debug("path=" + path + ",loginUrl="+LOGIN_URL);
			response.sendRedirect(path+ LOGIN_URL);
			return;
		}
		if(userid  > 0){
			String sessionid = request.getSession().getId();
			LOGGER.info("cookies中的用户sessionid:"+sessionid);
			String cacheSessiobn = stringRedisTemplate.opsForValue().get(UserUtil.user_session_key + userid);
			LOGGER.info("缓存中的sessionid="+cacheSessiobn);
			if (sessionid.equals(cacheSessiobn)){
				check =true;
			}
		}
		if(check){
			chain.doFilter(request,response);
		}else{
			LOGGER.debug("path=" + path + ",loginUrl="+LOGIN_URL);
			response.sendRedirect(path+ LOGIN_URL);
			return;
		}
	}

}
