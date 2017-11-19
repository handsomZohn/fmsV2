package com.casic.fms.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casic.fms.bean.FileType;
import com.casic.fms.bean.SecurityLevel;
import com.casic.fms.bean.VersionInfo;
import com.casic.fms.service.BaseDataService;
import com.casic.fms.service.FileSecurityLevelService;
import com.casic.fms.service.VersionInfoService;

/**
 * Urls:
 * 
 * @author crazylion
 *
 */
@Controller
@RequestMapping(value = "/web")
public class VersionInfoController  extends BaseWebController{

	private VersionInfoService		versionInfoService;
	
	
	@Autowired
	public void setVersionInfoService(VersionInfoService versionInfoService) {
		this.versionInfoService = versionInfoService;
	}
	
	@RequiresPermissions("versions:list")
	@RequestMapping(value = {"versions"},method=RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("models", this.versionInfoService.getVersions());
		return "version/index";
	}
	
	@RequiresPermissions("versions:edit")
	@RequestMapping(value = "/version/{id}",method=RequestMethod.GET)
	public String editForm(Model model,@PathVariable("id")String id) {
		model.addAttribute("model", this.versionInfoService.getOneByID(id));
		return "version/form";
	}
	@RequiresPermissions("versions:create")
	@RequestMapping(value = "/version/create",method=RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("model", new VersionInfo());
		return "version/form";
	}
	
	@RequiresPermissions("versions:save")
	@RequestMapping(value = "/version/save",method=RequestMethod.POST)
	public String save(VersionInfo vi,RedirectAttributes redirectAttributes) {
		this.versionInfoService.addVersionInfo(vi);
		return "redirect:/web/versions";
	}





}
