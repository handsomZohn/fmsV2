package com.casic.common.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.LogoutFilter;

import com.casic.fms.service.LoginUserManager;

public class FacadeLogoutFilter extends LogoutFilter {
    private static final Logger log = LoggerFactory.getLogger(FacadeLogoutFilter.class);

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        String redirectUrl = getRedirectUrl(request, response, subject);
        try {
        	String token = (String)((HttpServletRequest)request).getParameter("token");
        	token = StringUtils.trimToNull(token);
        	if(token != null){
        		LoginUserManager.getInstance().logoutUserToken(token);
        	}
            subject.logout();
        } catch (SessionException ise) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
        issueRedirect(request, response, redirectUrl);
        return false;
    }

    /**
     * 获取客户端请求退出时传递过来的path参数，这个path是一个完整的URL（目的是指定退出后跳转的地址）.
     */
    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
        String url = ((HttpServletRequest)request).getParameter("path");
		if(StringUtils.isNotBlank(url)){
			if(url.startsWith("https")) return url;
			if(!url.startsWith("http"))	url = "http://" + url;
			log.info("redirect target is: " + url);
			return url;
		}else{
			return getRedirectUrl();
		}
    }

}
