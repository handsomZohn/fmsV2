package com.casic.fms.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casic.fms.bean.User;
import com.casic.fms.service.UserLoginContext;

@Controller
@RequestMapping(value = "/upload/face")
public class FaceUploadController {
	@RequiresPermissions("user:view")
	@RequestMapping(value = "preUploadFace/{token}")
	public String faceUploadForm(@PathVariable("token") String token, Model model, 
			RedirectAttributes redirectAttributes, HttpServletRequest request) throws IOException{
		User user = UserLoginContext.getInstance().getUser(token);
		
		Subject subject = SecurityUtils.getSubject();
		boolean isAuth = subject.isAuthenticated();
		
		if(user != null && !isAuth){
			String msg = "认证已过期";
			model.addAttribute("message", msg);
		}
		
		if(user == null){
			redirectAttributes.addFlashAttribute("message", "不在登录状态，请重新登录后上传.");
			return "redirect:/login";
		}else{
			//request.getSession().setAttribute("token", token);
		}
		
		String msg = (String)request.getAttribute("msgOfUploadFail");
		msg = StringUtils.trimToNull(msg);
		if(msg != null){
			model.addAttribute("message", msg);
		}
		
		msg = (String)request.getAttribute("msgOfLarge");
		msg = StringUtils.trimToNull(msg);
		if(msg != null){
			model.addAttribute("message", msg);
		}
		
		msg = (String)request.getAttribute("msgOfNotImage");
		msg = StringUtils.trimToNull(msg);
		if(msg != null){
			model.addAttribute("message", msg);
		}
		
		model.addAttribute("userId", user.getId());
		model.addAttribute("token", token);
		return "account/faceUpload";
	}
	
	@RequiresPermissions("user:view")
	@RequestMapping(value = "success")
	public String uploadFaceSuccess(Model model, HttpServletRequest request){
		//显式的赋值，方便调试
		String token = (String)request.getAttribute("token");
		String userId = (String)request.getAttribute("userId");
		String faceId = (String)request.getAttribute("faceId");
		model.addAttribute("token", token);
		model.addAttribute("userId", userId);
		model.addAttribute("faceId", faceId);
		return "account/faceUploadSuccess";
	}
	
	@RequiresPermissions("user:view")
	@RequestMapping(value = "fail")
	public String uploadFaceFail(Model model, HttpServletRequest request){
		String msg = (String)request.getAttribute("msg");
		model.addAttribute("message", msg);
		return "account/faceUploadFail";
	}

}
