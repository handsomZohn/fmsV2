package com.casic.fms.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casic.fms.bean.SecurityLevel;
import com.casic.fms.bean.SecurityLimit;
import com.casic.fms.service.BaseDataService;
import com.casic.fms.service.FileSecurityLevelService;

/**
 * Urls:
 * 
 * @author crazylion
 *
 */
@Controller
@RequestMapping(value = "/web")
public class FileSecurityLevelController  extends BaseWebController{


	private FileSecurityLevelService	fileSecurityLevelService;
	private BaseDataService		baseDataService;

	@Autowired
	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}
	
	@Autowired
	public void setFileSecurityLevelService(
			FileSecurityLevelService fileSecurityLevelService) {
		this.fileSecurityLevelService = fileSecurityLevelService;
	}
	@RequiresPermissions("securitylevel:list")
	@RequestMapping(value = {"sls"},method=RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("models", fileSecurityLevelService.getAllLevels());
		return "securitylevel/index";
	}
	
	@RequiresPermissions("securitylevel:create")
	@RequestMapping(value = "/sl/create",method=RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("model", new SecurityLevel());
		return "securitylevel/form";
	}
	@RequiresPermissions("securitylevel:update")
	@RequestMapping(value = "/sl/{code}",method=RequestMethod.GET)
	public String updateForm(Model model,@PathVariable("code")String code) {
		model.addAttribute("model", this.fileSecurityLevelService.getOneByCode(code));
		return "securitylevel/form";
	}
		
	@RequiresPermissions("securitylevel:save")
	@RequestMapping(value = "/sl/save",method=RequestMethod.POST)
	public String save(SecurityLevel slmodel,RedirectAttributes redirectAttributes) {
		this.fileSecurityLevelService.save(slmodel);
		return "redirect:/web/sls";
	}

	@RequiresPermissions("securitylimit:list")
	@RequestMapping(value = {"securitylimits/{pageIndex}"},method=RequestMethod.GET)
	public String listSecurityLimit(@PathVariable("pageIndex") Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize,Model model) {
		if(pageIndex == null)	pageIndex = 0;
		if(pageSize == null)	pageSize= 10;
		Page<SecurityLimit>  pageModels = baseDataService.getSecurityLimitList(pageIndex, pageSize);
		model.addAttribute("models", pageModels.getContent());
		super.addPageinfo(model, pageModels);
		return "securitylimit/index";
	}
	
	@RequiresPermissions("securitylimit:create")
	@RequestMapping(value = "/securitylimit/create",method=RequestMethod.GET)
	public String createSecurityLimitForm(Model model) {
		model.addAttribute("model", new SecurityLimit());
		model.addAttribute("levelmodels", this.baseDataService.getSecurityLevels());
		return "securitylimit/form";
	}
	@RequiresPermissions("securitylimit:update")
	@RequestMapping(value = "/securitylimit/{id}",method=RequestMethod.GET)
	public String updateSecurityLimitForm(Model model,@PathVariable("id")String id) {
		model.addAttribute("model", this.baseDataService.getSecurityLimit(id));
		model.addAttribute("levelmodels", this.baseDataService.getSecurityLevels());
		return "securitylimit/form";
	}
		
	@RequiresPermissions("securitylimit:save")
	@RequestMapping(value = "/securitylimit/save",method=RequestMethod.POST)
	public String saveSecurityLimit(SecurityLimit slmodel,RedirectAttributes redirectAttributes) {
		this.baseDataService.addSecurityLimit(slmodel);
		return "redirect:/web/securitylimits/0";
	}


}
