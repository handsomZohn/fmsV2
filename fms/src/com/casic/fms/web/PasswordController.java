package com.casic.fms.web;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casic.common.SimpleUtils;
import com.casic.email.ModPwdMailService;
import com.casic.fms.bean.User;
import com.casic.fms.service.AuthenticationService;
import com.casic.fms.service.UserLoginContext;

@Controller
@RequestMapping(value = "/web/user/password")
public class PasswordController {
	private AuthenticationService authenticationService;
	
	private ModPwdMailService modPwdMailService;

	@Autowired
	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@Autowired
	public void setModPwdMailService(ModPwdMailService modPwdMailService) {
		this.modPwdMailService = modPwdMailService;
	}
	
	@RequestMapping(value = "isRegistLoginName/{loginName}")
	@ResponseBody
	public String isRegistLoginName(@PathVariable("loginName") String loginName) {
		if(authenticationService.findUserByLoginName(loginName) != null){
			return "true";
		}
		return "false";
	}
	
	@RequestMapping(value = "isOwerEmail/{loginName}")
	@ResponseBody
	public String isOwerEmail(@PathVariable("loginName") String loginName, String email) {
		if(authenticationService.findUserByLoginName(loginName) != null){
			User user = authenticationService.findUserByLoginName(loginName);
			if(user.getEmail() != null && !"".equals(user.getEmail()) ){
				if(user.getEmail().equals(email)){
					return "true";
				}
			}
		}
		return "false";
	}
	
	@RequestMapping(value = "preRetrievePwd/{loginName}")
	public String retrievePwdForm(@PathVariable("loginName") String loginName, Model model, 
			RedirectAttributes redirectAttributes) {
		loginName = SimpleUtils.unicodeToString(loginName);
		String isRegist = this.isRegistLoginName(loginName);
		if(!"true".equals(isRegist)){
			redirectAttributes.addFlashAttribute("message", "登录名：" + loginName + " 未注册，请重新填写！");
			return "redirect:/login";
		}
		model.addAttribute("loginName", loginName);
		return "/forgetPwd";
	}
	
	@RequestMapping(value = "sendMailRetrievePwd")
	public String mailRetrievePwdForm(@RequestParam("loginName") String loginName, @RequestParam("email") String email, 
			Model model, RedirectAttributes redirectAttributes) {
		//验证用户填写的找回邮箱是否有效
		if(email == null || "".equals(email)){
			model.addAttribute("loginName", loginName);
			model.addAttribute("message", "请填写注册的邮箱");
			return "/forgetPwd";
		}
		if(!"true".equals(this.isOwerEmail(loginName, email))){
			User user = authenticationService.findUserByLoginName(loginName);
			if(user.getEmail() != null && !"".equals(user.getEmail()) ){
				if(!user.getEmail().equals(email)){
					model.addAttribute("loginName", loginName);
					model.addAttribute("message", "页面填写的邮箱与您在这里注册的邮箱不符");
					return "/forgetPwd";
				}
			}else{
				model.addAttribute("loginName", loginName);
				model.addAttribute("message", "您在这里没有注册邮箱, 您也可以选择注册新账户，或与我们联系");
				return "/forgetPwd";
			}
		}
		User user = authenticationService.findUserByLoginName(loginName);
		String token = UUID.randomUUID().toString();
		UserLoginContext.getInstance().putUser(token, user);
		String activityUrl = "reset/user/preModForgetPwd/" + token;
		modPwdMailService.sendModPwdMail(email, loginName, activityUrl);
		model.addAttribute("message", "用户" + loginName + "，您的重置密码邮件已发送，请注意查收！");
		model.addAttribute("email", email);
		return "account/mailSendSuccess";
	}
	
}
