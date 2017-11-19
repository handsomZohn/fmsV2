package com.casic.fms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.casic.fms.service.InitService;

/**
 *
 * 系统默认主页 
 * @author crazylion
 */
@Controller

@RequestMapping(value = "/init")
public class InitController {
	
	private InitService	initService;
	
	@Autowired
	public void setInitService(InitService initService) {
		this.initService = initService;
	}


	@RequestMapping(value = "/rbac")
	public String succeed(Model model){
		this.initService.initRBAC();
		return "/init";
	}
	
	

}
