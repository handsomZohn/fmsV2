package com.casic.fms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.casic.fms.bean.ReInfo;
import com.casic.fms.service.FileLogService;
import com.casic.fms.service.LogStatisticService;
import com.casic.fms.service.PasswordLogService;

/**
 *
 * 系统默认主页 
 * @author crazylion
 */
@Controller

@RequestMapping(value = "")
public class IndexController extends BaseWebController {
	
	private FileLogService	fileLogService;
	private LogStatisticService	logStatisticService;
	private PasswordLogService	passwordLogService;
	@Autowired
	public void setLogStatisticService(LogStatisticService logStatisticService) {
		this.logStatisticService = logStatisticService;
	}
	@Autowired
	public void setPasswordLogService(PasswordLogService passwordLogService) {
		this.passwordLogService = passwordLogService;
	}
	@Autowired
	public void setFileLogService(FileLogService fileLogService) {
		this.fileLogService = fileLogService;
	}

	@RequestMapping(value = "/index")
	public String succeed(Model model){
		//判断是否提醒密码修改
		String userid= getLoginUserid();
		if(passwordLogService.checkPasswordNeedUpdate(userid)!=null){
			//跳转到密码修改页面，带着提示
			ReInfo ri = ReInfo.getFailed();
			ri.code="1";
			ri.setMessage(getMessage("index.passwordperiod"));//"：初次登陆或密码已过期，请立即修改密码！"
			model.addAttribute("reinfo", ri);
			return "user/changepwd";
		}
		model.addAttribute("totalmember", logStatisticService.getClientCount());
		model.addAttribute("totallog", logStatisticService.getLogCount());
		model.addAttribute("totalsecurity", logStatisticService.getSecurityCount());
		return "/index";
	}
	

}
