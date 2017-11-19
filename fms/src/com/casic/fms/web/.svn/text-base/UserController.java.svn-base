package com.casic.fms.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casic.common.MD5Util;
import com.casic.fms.bean.ReInfo;
import com.casic.fms.bean.Role;
import com.casic.fms.bean.User;
import com.casic.fms.bean.UserRole;
import com.casic.fms.service.AuthenticationService;
import com.casic.fms.service.AuthorizeService;

/**
 * Urls:
 * List   page        : GET  /web/users/{pageIndex}?pageSize={pageSize}
 * Create page        : GET  /web/user/create
 * Create action      : POST /web/user/save
 * Update page        : GET  /web/user/update/{id}
 * Update action      : POST /web/user/save/{id}
 * Delete action      : POST /web/user/delete/{id}
 * 
 * @author crazylion
 *
 */
@Controller
@RequestMapping(value = "/web")
public class UserController extends BaseWebController{

	private AuthenticationService authenticationService;
	private AuthorizeService		authorizeService;
	
	private GroupListEditor groupListEditor;

	@Autowired
	public void setAuthorizeService(AuthorizeService authorizeService) {
		this.authorizeService = authorizeService;
	}
	@InitBinder
	public void initBinder(WebDataBinder b) {
		b.registerCustomEditor(List.class, "groupList", groupListEditor);
	}


	@RequiresPermissions("user:list")
	@RequestMapping(value = { "/users/{pageIndex}" },method=RequestMethod.GET)
	public String list(Model model,@PathVariable(value="pageIndex") Integer pageIndex, 
			@RequestParam(value="pageSize", required=false)Integer pageSize) {
		if(pageSize == null)	pageSize = 10;
		Page<User> users = authenticationService.getUsers(pageIndex, pageSize);
		model.addAttribute("models", users.getContent());
		addPageinfo(model, users);
		return "user/list";
	}

	@RequiresPermissions("user:create")
	@RequestMapping(value = "/user/create",method=RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", authorizeService.getRoles());
		return "user/form";
	}

	@RequiresPermissions("user:save")
	@RequestMapping(value = "/user/save",method=RequestMethod.POST)
	public String save(User user,@RequestParam("userroleid")String userroleid, RedirectAttributes redirectAttributes) {
		UserRole userRole = new UserRole();
		if(userroleid != null){
			userRole.setRoleId(userroleid);
		}
		user = authenticationService.saveUser(user,userRole);
		return "redirect:/web/users/0";
	}

	@RequiresPermissions("user:remove")
	@RequestMapping(value = "/user/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		ReInfo ri = authenticationService.deleteUser(id);
		redirectAttributes.addFlashAttribute("reinfo", ri);
		return "redirect:/web/users/0";
	}

	

	@RequiresPermissions("user:update")
	@RequestMapping(value = "/user/update/{id}",method=RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,Model model) {
		model.addAttribute("user",authenticationService.getUser(id));
		model.addAttribute("roles", authorizeService.getRoles());
		List<UserRole>  userroles = authorizeService.getUserRoles(id);
		if(userroles!= null){
			model.addAttribute("roleid", userroles.get(0).getRoleId());
		}else{
			model.addAttribute("roleid", "");
		}
		return "user/form";
	}

	@RequestMapping(value = "/user/resetpwd/{token}",method=RequestMethod.GET)
	public String modPwdForm(@PathVariable("token") String token, Model model, HttpSession session) {
		User user = (User)session.getAttribute(token);
		if(user == null){
			return "/login";
		}
		model.addAttribute("user", user);
		model.addAttribute("token", token);
		return "web/user";
	}

	/**
	 * 显示登录密码
	 * @param redirectAttributes
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/widget/roles/{userid}",method=RequestMethod.GET)
	public String showUserRolename(@PathVariable("userid")String userid, RedirectAttributes redirectAttributes, Model model) {
		Role role = this.authenticationService.getUserRoleByUserid(userid);
		if(role == null)
			model.addAttribute("name", "暂无角色");
		else
			model.addAttribute("name", role.getName());
		return "widget/user/role";
	}
	/**
	 * 显示登录密码
	 * @param redirectAttributes
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/user/changepwd",method=RequestMethod.GET)
	public String changepwd(RedirectAttributes redirectAttributes, HttpSession session) {
		return "user/changepwd";
	}
	
	@RequestMapping(value = "/user/changepwd",method=RequestMethod.POST)
	public String changepwd(@RequestParam("password") String oldPwd, 
			@RequestParam("newpwd") String password, @RequestParam("confirmpwd") String passwordConfirm,
			Model model, HttpSession session) {
		User user = (User)SecurityUtils.getSubject().getPrincipal();
		if(user == null){
			return "/login";
		}
		user.setPassword(oldPwd);
		if(password == null || passwordConfirm==null || oldPwd==null){
			//返回错误提示
			ReInfo ri = ReInfo.getFailed();
			ri.setMessage(getMessage("user.passwordnotnull"));//"输入密码不能为空！"
			model.addAttribute("reinfo", ri);
			return "user/changepwd";
		}else if(!password.equals(passwordConfirm)){
			//返回错误提示
			ReInfo ri = ReInfo.getFailed();
			ri.setMessage(getMessage("user.2pwdnotequal"));//"两次输入的密码不一致，请修改后重新保存！"
			model.addAttribute("reinfo", ri);
			return "user/changepwd";
		}else if(!this.authenticationService.checkPassword(user)){
			//返回错误提示
			ReInfo ri = ReInfo.getFailed();
			ri.setMessage(getMessage("user.updatepwdfailed"));//"密码修改失败，旧密码输入错误，请联系管理员进行密码重置！"
			model.addAttribute("reinfo", ri);
			return "user/changepwd";
		}else{
			user.setPassword(passwordConfirm);
			authenticationService.saveUser(user,null);
			ReInfo ri = ReInfo.getSucceed();
			ri.setMessage(getMessage("user.updatepwdsuccess"));//"密码修改成功！"
			model.addAttribute("reinfo", ri);
			return "user/changepwd";
		}
	}

	@Autowired
	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@Autowired
	public void setGroupListEditor(GroupListEditor groupListEditor) {
		this.groupListEditor = groupListEditor;
	}

}
