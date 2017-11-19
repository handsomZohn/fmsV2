package com.casic.fms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casic.fms.bean.Message;
import com.casic.fms.bean.PasswordLog;
import com.casic.fms.service.AuthorizeService;
import com.casic.fms.service.BaseDataService;
import com.casic.fms.service.PasswordLogService;

/**
 * Urls:
 * 
 * @author crazylion
 *
 */
@Controller
@RequestMapping(value = "/web")
public class PasswordLogController  extends BaseWebController{

	private PasswordLogService 	passwordLogService;
	private AuthorizeService		authorizeService;
	
	
	@Autowired
	public void setAuthorizeService(AuthorizeService authorizeService) {
		this.authorizeService = authorizeService;
	}
	
	
	
	@Autowired
	public void setPasswordLogService(PasswordLogService passwordLogService) {
		this.passwordLogService = passwordLogService;
	}
	
	@RequiresPermissions("passwordlogs:list")
	@RequestMapping(value = {"passwordlogs/{pageIndex}"})
	public String list(@PathVariable("pageIndex") Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize,
			@RequestParam(value="recall", required=false) String recall,
			Model model,HttpSession session,HttpServletRequest request) {
		if(pageIndex == null)	pageIndex = 0;
		if(pageSize == null)	pageSize= 10;
		Page<PasswordLog> pageModels=this.passwordLogService.getLogs(pageIndex, pageSize);
		model.addAttribute("models", pageModels.getContent());
		addPageinfo(model, pageModels);
		return "passwordlog/index";
	}
	
}
