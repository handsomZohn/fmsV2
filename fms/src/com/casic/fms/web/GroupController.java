package com.casic.fms.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casic.fms.bean.Group;
import com.casic.fms.bean.Permission;
import com.casic.fms.service.AuthenticationService;

@Controller
@RequestMapping(value = "/account/group")
public class GroupController {

	private AuthenticationService authenticationService;

	@RequiresPermissions("group:view")
	@RequestMapping(value = { "list", "" })
	public String list(Model model) {
		List<Group> groups = authenticationService.getAllGroup();
		model.addAttribute("groups", groups);
		return "account/groupList";
	}

	@RequiresPermissions("group:edit")
	@RequestMapping(value = "create")
	public String createForm(Model model) {
		model.addAttribute("group", new Group());
		//model.addAttribute("allPermissions", Permission.values());
		return "account/groupForm";
	}

	@RequiresPermissions("group:edit")
	@RequestMapping(value = "save")
	public String save(Group group, RedirectAttributes redirectAttributes) {
		authenticationService.saveGroup(group);
		redirectAttributes.addFlashAttribute("message", "创建权限组" + group.getName() + "成功");
		return "redirect:/account/group/";
	}

	@RequiresPermissions("group:edit")
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		authenticationService.deleteGroup(id);
		redirectAttributes.addFlashAttribute("message", "删除权限组成功");
		return "redirect:/account/group/";
	}
	
	@RequiresPermissions("group:edit")
	@RequestMapping(value = "update/{id}")
	public String updateForm(@PathVariable("id") String id,Model model) {
		model.addAttribute("group",authenticationService.getGroup(id));
		//model.addAttribute("allPermissions", Permission.values());
		return "account/groupForm";
	}

	@RequiresPermissions("group:edit")
	@RequestMapping(value = "save/{id}")
	public String saveGrop(@ModelAttribute("group") Group group, RedirectAttributes redirectAttributes) {
		authenticationService.saveGroup(group);
		redirectAttributes.addFlashAttribute("message", "修改权限组" + group.getName() + "成功");
		return "redirect:/account/group/";
	}


	@Autowired
	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
}
