package com.casic.common.shiro;

import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.casic.common.MD5Util;
import com.casic.common.SimpleUtils;
import com.casic.fms.bean.User;
import com.casic.fms.dao.UserDao;
import com.casic.fms.service.LoginUserManager;
import com.casic.fms.service.UserLoginContext;

public class RedirectAuthenticationFilter extends FormAuthenticationFilter {
	
	private final Logger logger = Logger.getLogger(RedirectAuthenticationFilter.class);
	
	private UserDao userDao;

	@Override
	protected boolean isLoginSubmission(ServletRequest request,
			ServletResponse response) {
		//CustomSettionContext.setReDirectUrl(request.getParameter("path"));
		HttpServletRequest req = (HttpServletRequest) request;
		if(StringUtils.isNotBlank(request.getParameter("path"))){
			req.getSession().setAttribute("path", request.getParameter("path"));
		}
		
		Boolean isTokenValid = false; 
		String tokenStr = request.getParameter("token");
		if(StringUtils.isNotBlank(tokenStr)){
			User user = UserLoginContext.getInstance().getUser(tokenStr);
			if(user != null){
				String username = user.getLoginName();
				String password = user.getPassword();
				isTokenValid = true;
				request.setAttribute("username", username);
				request.setAttribute("password", password);
				request.setAttribute("isTokenValid", isTokenValid);
				return true;
			}
		}
		request.setAttribute("isTokenValid", isTokenValid);
		return super.isLoginSubmission(request, response);
	}
	
	/**
	 * 用户登录时候的password需要做MD5运算
	 */
	@Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		HttpServletRequest req = (HttpServletRequest) request;
		String username = getUsername(request);
		String pwd = MD5Util.getMD5String(getPassword(request));
		boolean isTokenValid = false;
		if(request.getAttribute("isTokenValid") != null){
			isTokenValid = (Boolean)request.getAttribute("isTokenValid");
		}
		if(isTokenValid){
			username = (String) request.getAttribute("username");
			pwd = (String) request.getAttribute("password");
			return createToken(username, pwd, request, response);
		}
		
		if(username != null){
			//判断用户输入的登录名称是否是邮箱，邮箱也可以登录
			boolean isEmail = SimpleUtils.isEmail(username);
			User user = null;
			if(isEmail){
				user = userDao.findByEmail(username);
				if(user != null){
					username = user.getLoginName();
					request.setAttribute("isAuthEmail", true);
				}else{
					request.setAttribute("isAuthEmail", false);
				}
			}
			request.setAttribute(DEFAULT_USERNAME_PARAM, username);
		}
		return createToken(username, pwd, request, response);
    }

	@Override
	protected void issueSuccessRedirect(ServletRequest request,
			ServletResponse response) throws Exception {
		//String url = CustomSettionContext.getReDirectUrl();
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		boolean isTokenValid = false;
		if(request.getAttribute("isTokenValid") != null){
			isTokenValid = (Boolean)request.getAttribute("isTokenValid");
		}
		String url = (String)session.getAttribute("path");
		String username = getUsername(request);
		if(isTokenValid){
			username = (String) request.getAttribute("username");
		}
		
		boolean isEmail = SimpleUtils.isEmail(username);
		User user = null;
		if(isEmail){
			user = userDao.findByEmail(username);
		}else{
			user = userDao.findByLoginName(username);
		}
		
		String tokenStr = request.getParameter("token");
		String token = null;
		if(isTokenValid == true && StringUtils.isNotBlank(tokenStr)){
			token = tokenStr;
			LoginUserManager.getInstance().updateUserState(token);
		}else{
			token = UUID.randomUUID().toString();
			UserLoginContext.getInstance().putUser(token, user);
		}
		
		session.setAttribute("token", token);
		session.setAttribute(token, user);
		
		if(StringUtils.isNotBlank(url)){
			session.removeAttribute("path");
			if(url.indexOf("?")==-1){
				url += "?";
			}else{
				url += "&";
			}
			url += "token="+token;
			if(!url.startsWith("http")) url = "http://"+url;
			logger.info("Redirect the URl : " +url);
			WebUtils.issueRedirect(request, response, url, null, false, false);
		}else{
			url = getSuccessUrl();
			WebUtils.redirectToSavedRequest(request, response, "/index");
		}

	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
