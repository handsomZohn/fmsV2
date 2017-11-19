package com.casic.fms.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casic.fms.bean.SystemConfig;
import com.casic.fms.service.BaseDataService;
import com.casic.fms.service.SystemConfigService;

/**
 * Urls:
 * 
 * @author crazylion
 *
 */
@Controller
@RequestMapping(value = "/web")
public class SystemConfigController  extends BaseWebController{

	
	private BaseDataService		baseDataService;
	private SystemConfigService systemConfigService;
	@Autowired
	public void setsystemConfigService(
			SystemConfigService systemConfigService) {
		this.systemConfigService = systemConfigService;
	}

	@Autowired
	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}
	
	@RequiresPermissions("systemconfig:list")
	@RequestMapping(value = {"systemconfig"},method=RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("models", systemConfigService.getAllLevels());
		return "systemconfig/index";
	}
	
	@RequiresPermissions("systemconfig:create")
	@RequestMapping(value = "/sc/create",method=RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("model", new SystemConfig());
		return "systemconfig/form";
	}
	@RequiresPermissions("systemconfig:update")
	@RequestMapping(value = "/sc/{code}",method=RequestMethod.GET)
	public String updateForm(Model model,@PathVariable("code")String code) {
		model.addAttribute("model", this.systemConfigService.getOneByCode(code));
		return "systemconfig/form";
	}
		
	@RequiresPermissions("systemconfig:save")
	@RequestMapping(value = "/sc/save",method=RequestMethod.POST)
	public String save(SystemConfig slmodel,RedirectAttributes redirectAttributes) {
		this.systemConfigService.save(slmodel);
		return "redirect:/web/systemconfig";
	}
	@RequiresPermissions("systemconfig:delete")
	@RequestMapping(value = "sc/delete/{id}")
	public String delete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		this.systemConfigService.delete(id);
		redirectAttributes.addFlashAttribute("tipinfo", "删除成功");
		return "redirect:/web/systemconfig";
	}


}
