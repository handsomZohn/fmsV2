package com.casic.fms.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casic.fms.bean.Permission;
import com.casic.fms.bean.ReInfo;
import com.casic.fms.bean.Role;
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
public class PermissionController extends BaseWebController{

	private AuthorizeService		authorizeService;


	@Autowired
	public void setAuthorizeService(AuthorizeService authorizeService) {
		this.authorizeService = authorizeService;
	}

	@RequiresPermissions("permission:list")
	@RequestMapping(value = { "/permissions" },method=RequestMethod.GET)
	public String list(Model model) {
		List<Permission> pers = authorizeService.getAllPermissions();
		model.addAttribute("models", pers);
		return "permission/index";
	}

	@RequiresPermissions("permission:create")
	@RequestMapping(value = "/permission/create",method=RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("model", new Permission());
		return "permission/form";
	}
	@RequiresPermissions("permission:update")
	@RequestMapping(value = "/permission/{id}",method=RequestMethod.GET)
	public String updateForm(Model model,@PathVariable("id")String id) {
		Permission per = this.authorizeService.getPermission(id);
		model.addAttribute("model", per);
		return "permission/form";
	}
	@RequiresPermissions("permission:save")
	@RequestMapping(value = "/permission/save",method=RequestMethod.POST)
	public String save(Permission model,RedirectAttributes redirectAttributes) {
		model = authorizeService.savePermission(model);
		return "redirect:/web/permissions";
	}

	@RequiresPermissions("permission:remove")
	@RequestMapping(value = "/permission/remove/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		Role role = new Role();
		role.setId(id);
		ReInfo ri = authorizeService.deletePermission(id);
		redirectAttributes.addFlashAttribute("reinfo", ri);
		return "redirect:/web/permissions";
	}

	


}
