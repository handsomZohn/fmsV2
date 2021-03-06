package com.casic.fms.web;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.casic.common.SimpleUtils;
import com.casic.fms.bean.User;
import com.casic.fms.service.AuthenticationService;
import com.casic.fms.service.LoginUserManager;
import com.casic.fms.service.UserLoginContext;

/**
 * LoginController负责打开登录页面(GET请求)、快速登录和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完成,
 * 
 * @author crazylion
 */
@Controller

@RequestMapping(value = "")
public class LoginController {
	
	private AuthenticationService authenticationService;
	
	@RequestMapping(value = "/logined", method = RequestMethod.POST)
	public String logined(@RequestParam("path") String path,
			@RequestParam("token") String token, Model model, HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		boolean isAuth = subject.isAuthenticated();
		boolean isRememberMe = subject.isRemembered();
		User user = null;
		if (StringUtils.isNotBlank(token)) {
			user = UserLoginContext.getInstance().getUser(token);
		}
		
		//    sso
		if (isAuth) {
			if (user == null) {
				token = (String) session.getAttribute("token");
				LoginUserManager.getInstance().updateUserState(token);
			} else {
				LoginUserManager.getInstance().updateUserState(token);
			}

			String url = createUrl(path, token);
			return "redirect:/index";
			
			
		}else if(!isAuth && isRememberMe && user != null){
			LoginUserManager.getInstance().updateUserState(token);
			session.setAttribute("token", token);
			session.setAttribute(token, user);
			return "redirect:/web/index";
		}
		subject.logout();
		model.addAttribute("message", "快速登录失败，请重新登录！");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(String path, String token, Model model) {
		 //从SecurityUtils里创建一个subject 
		Subject subject = SecurityUtils.getSubject();
		//是否认证通过  
		boolean isAuth = subject.isAuthenticated();
		boolean isRememberMe = subject.isRemembered();
		if(isRememberMe){
			if(!isAuth){
				User shiroUser = (User)subject.getPrincipal();
				if(shiroUser != null){
					String loginName = shiroUser.getLoginName();
					User user = authenticationService.findUserByLoginName(loginName);
					if(user != null){
						token = UUID.randomUUID().toString();
						UserLoginContext.getInstance().putUserTransient(token, user);
					}
				}
			}
		}
		model.addAttribute("path", path);
		model.addAttribute("token", token);
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String fail(
			@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName,
			Model model, HttpServletRequest request) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM,
				userName);
		
		boolean isEmail = SimpleUtils.isEmail(userName);
	
		
		if (isEmail) {
			boolean isAuthEmail = false;
			if (request.getAttribute("isAuthEmail") != null) {
				isAuthEmail = (Boolean) request.getAttribute("isAuthEmail");
			}
			if (isAuthEmail == true) {
				model.addAttribute("message", "邮箱或密码输入错误，请重试！");
			} else {
				model.addAttribute("message", "此邮箱不是注册邮箱，请注册后重新登录！");
			}
		} else {
			model.addAttribute("message", "用户名或密码输入错误，请重试！");
		}
		
        return "login";
	}
	
	private String createUrl(String path, String token){
		String url = path;
		if (StringUtils.isNotBlank(url)) {
			if (url.indexOf("?") == -1) {
				url += "?";
			} else {
				url += "&";
			}
			url += "token=" + token;
			if (!url.startsWith("http"))
				url = "http://" + url;
		}
		return url;
	}
	
	@Autowired
	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}


}
