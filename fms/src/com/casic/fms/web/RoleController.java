package com.casic.fms.web;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.ArrayUtil;
import org.apache.poi.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casic.fms.bean.ReInfo;
import com.casic.fms.bean.Role;
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
public class RoleController extends BaseWebController{

	private AuthenticationService authenticationService;
	private AuthorizeService		authorizeService;


	@Autowired
	public void setAuthorizeService(AuthorizeService authorizeService) {
		this.authorizeService = authorizeService;
	}

	@RequiresPermissions("role:list")
	@RequestMapping(value = { "/roles" },method=RequestMethod.GET)
	public String list(Model model) {
		List<Role> roles = authorizeService.getRoles();
		model.addAttribute("models", roles);
		return "role/index";
	}

	@RequiresPermissions("role:create")
	@RequestMapping(value = "/role/create",method=RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("model", new Role());
		model.addAttribute("permissions", this.authorizeService.getAllPermissions());
		return "role/form";
	}
	@RequiresPermissions("role:update")
	@RequestMapping(value = "/role/{id}",method=RequestMethod.GET)
	public String updateForm(Model model,@PathVariable("id")String id) {
		Role role = this.authorizeService.getRole(id);
		model.addAttribute("model", role);
		model.addAttribute("permissions", this.authorizeService.getAllPermissions());
		List<String>  ls = this.authorizeService.getRolePermission(id);
		model.addAttribute("rolepermission",  StringUtils.join(ls.toArray(), ","));
		return "role/form";
	}
	@RequiresPermissions("role:save")
	@RequestMapping(value = "/role/save",method=RequestMethod.POST)
	public String save(Role model,@RequestParam("permissionid")String permissionid, RedirectAttributes redirectAttributes) {
		model = authorizeService.saveRole(model,permissionid);
		return "redirect:/web/roles";
	}

	@RequiresPermissions("role:delete")
	@RequestMapping(value = "/role/remove/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		Role role = new Role();
		role.setId(id);
		ReInfo ri = authorizeService.deleteRole(role);
		redirectAttributes.addFlashAttribute("reinfo", ri);
		return "redirect:/web/roles";
	}

	
	@Autowired
	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}


}
