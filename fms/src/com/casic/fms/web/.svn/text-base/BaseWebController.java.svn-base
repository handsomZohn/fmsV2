package com.casic.fms.web;

import java.util.Locale;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import com.casic.fms.bean.User;

/**
 * 
 * @author crazylion
 *
 */
public class BaseWebController  {

	protected MessageSource messageSource;
	
	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public Locale getLocale(){
		return null;
	}
	
	public String getMessage(String code){
		return this.messageSource.getMessage(code, null, getLocale());
	}
	
	public String getMessage(String code, Object[] args){
		return this.messageSource.getMessage(code, args, getLocale());
	}
	/**
	 * 增加翻页信息给前台显示
	 * @param model
	 * @param pageModels
	 */
	public <T> void addPageinfo(Model model,Page<T> pageModels ) {
		model.addAttribute("totalpages", pageModels.getTotalPages());
		model.addAttribute("total", pageModels.getTotalElements());
		model.addAttribute("pagesize", pageModels.getSize());
		model.addAttribute("pageindex", pageModels.getNumber());
	}

	/**
	 * 获取当前登陆用户信息
	 * @return
	 */
	protected  String getLoginUserid(){
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			User user = (User)subject.getPrincipal();
			return user.getId();
		}
		return null;
	}
	

}
