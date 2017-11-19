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
import com.casic.fms.bean.ProcessList;
import com.casic.fms.service.BaseDataService;

/**
 * Urls:
 * 
 * @author crazylion
 *
 */
@Controller
@RequestMapping(value = "/web")
public class ProcessListController  extends BaseWebController{

	private BaseDataService		baseDataService;
	
	
	@Autowired
	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}
	
	@RequiresPermissions("processlist:list")
	@RequestMapping(value = {"processes"},method=RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("models", this.baseDataService.getAllProcessList());
		return "pl/index";
	}
	
	@RequiresPermissions("processlist:edit")
	@RequestMapping(value = "/process/{id}",method=RequestMethod.GET)
	public String editForm(Model model,@PathVariable("id")String id) {
		model.addAttribute("model", this.baseDataService.getProcessList(id));
		return "pl/form";
	}
	@RequiresPermissions("processlist:delete")
	@RequestMapping(value = "/process/delete/{id}",method=RequestMethod.GET)
	public String deleteForm(Model model,@PathVariable("id")String id) {
		this.baseDataService.deleteProcessList(id);
		return "redirect:/web/processes";
	}
	@RequiresPermissions("processlist:create")
	@RequestMapping(value = "/process/create",method=RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("model", new ProcessList());
		return "pl/form";
	}
	
	@RequiresPermissions("processlist:save")
	@RequestMapping(value = "/process/save",method=RequestMethod.POST)
	public String save(ProcessList ft,RedirectAttributes redirectAttributes) {
		this.baseDataService.addProcessList(ft);
		return "redirect:/web/processes";
	}



}
